package com.PsychopathsArena;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.PsychopathsHelp.GLGame;

public class FileOp
{	
	static List<String> readLines(InputStream in) throws IOException 
	{
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = reader.readLine()) != null)
		lines.add(line);
		return lines;
	}

	static int getIndex(String index, int size) 
	{
		int idx = Integer.parseInt(index);
		if (idx < 0)
		return size + idx;
		else
		return idx - 1;
	}
	static void Dimensions(float[] dimensions,int numVertices,float[] vertices)
	{
	    int i;
	    float maxx, minx, maxy, miny, maxz, minz;
	    
	    maxx = minx = vertices[3 + 0];
	    maxy = miny = vertices[3 + 1];
	    maxz = minz = vertices[3 + 2];
	    for (i = 1; i <=numVertices; i++) {
	        if (maxx < vertices[3 * i + 0])
	            maxx = vertices[3 * i + 0];
	        if (minx > vertices[3 * i + 0])
	            minx = vertices[3 * i + 0];
	        
	        if (maxy < vertices[3 * i + 1])
	            maxy = vertices[3 * i + 1];
	        if (miny > vertices[3 * i + 1])
	            miny = vertices[3 * i + 1];
	        
	        if (maxz < vertices[3 * i + 2])
	            maxz = vertices[3 * i + 2];
	        if (minz > vertices[3 * i + 2])
	            minz = vertices[3 * i + 2];
	    }
	    
	    // calculate model width, height, and depth 
	    dimensions[0] = Abs(maxx) + Abs(minx);
	    dimensions[1] = Abs(maxy) + Abs(miny);
	    dimensions[2] = Abs(maxz) + Abs(minz);
	}

	public static float Abs(float f)
	{
	    if (f < 0)
	        return -f;
	    return f;
	}
	public static void load(GLGame game,String file)
	{
		
		OutputStream obOut;
		ObjectOutputStream ob;
		//while(1!=0)
		{
		//System.out.println("Enter the full file path :: ");
		//String path = br.readLine();
	
		try
		{
			// Create a new file output stream
			// connected to "myfile.txt"
			//out = new FileOutputStream(path);
			
			//File file = new File("crate.obj");

			InputStream in = game.getFileIO().readAsset(file+".obj");
			// Convert our input stream to a
			// DataInputStream
			List<String> lines = readLines(in);
			float[] vertices = new float[lines.size() * 3];
			float[] normals = new float[lines.size() * 3];
			float[] uv = new float[lines.size() * 2];
			int numVertices = 0;
			int numNormals = 0;
			int numUV = 0;
			int numFaces = 0;
			
			int vertexIndex=0;
			int normalIndex=0;
			int uvIndex=0;
			int faceIndex=0;
			int[] facesVerts = new int[lines.size() * 3];
			int[] facesNormals= new int[lines.size() * 3];
			int[] facesUV= new int[lines.size() * 3];
			
			
			
			for (int i = 0; i < lines.size(); i++) 
			{
				String line = lines.get(i);
				if (line.startsWith("v ")) 
				{
					String[] tokens = line.split("[ ]+");
					vertices[vertexIndex] = Float.parseFloat(tokens[1]);
					vertices[vertexIndex + 1] = Float.parseFloat(tokens[2]);
					vertices[vertexIndex + 2] = Float.parseFloat(tokens[3]);
					vertexIndex += 3;
					numVertices++;
					continue;
				}
				
				if (line.startsWith("vn ")) 
				{
					String[] tokens = line.split("[ ]+");
					normals[normalIndex] = Float.parseFloat(tokens[1]);
					normals[normalIndex + 1] = Float.parseFloat(tokens[2]);
					normals[normalIndex + 2] = Float.parseFloat(tokens[3]);
					normalIndex += 3;
					numNormals++;
					continue;
				}
				if (line.startsWith("vt")) 
				{
					String[] tokens = line.split("[ ]+");
					uv[uvIndex] = Float.parseFloat(tokens[1]);
					uv[uvIndex + 1] = Float.parseFloat(tokens[2]);
					uvIndex += 2;
					numUV++;
					continue;
				}
				if (line.startsWith("f ")) 
				{
					String[] tokens = line.split("[ ]+");
					String[] parts = tokens[1].split("/");
					facesVerts[faceIndex] = getIndex(parts[0], numVertices);
					if (parts.length > 2)
						facesNormals[faceIndex] = getIndex(parts[2], numNormals);
					if (parts.length > 1)
						facesUV[faceIndex] = getIndex(parts[1], numUV);
					faceIndex++;
					
					parts = tokens[2].split("/");
					facesVerts[faceIndex] = getIndex(parts[0], numVertices);
					if (parts.length > 2)
					facesNormals[faceIndex] = getIndex(parts[2], numNormals);
					if (parts.length > 1)
					facesUV[faceIndex] = getIndex(parts[1], numUV);
					faceIndex++;
					
					parts = tokens[3].split("/");
					facesVerts[faceIndex] = getIndex(parts[0], numVertices);
					if (parts.length > 2)
					facesNormals[faceIndex] = getIndex(parts[2], numNormals);
					if (parts.length > 1)
					facesUV[faceIndex] = getIndex(parts[1], numUV);
					faceIndex++;
					numFaces++;
					continue;
				}
			}
			
			float[] verts = new float[(numFaces * 3)* (3 + (numNormals > 0 ? 3 : 0) + (numUV > 0 ? 2 : 0))];
			for (int i = 0, vi = 0; i < numFaces * 3; i++) 
			{
				int vertexIdx = facesVerts[i] * 3;
				verts[vi++] = vertices[vertexIdx];
				verts[vi++] = vertices[vertexIdx + 1];
				verts[vi++] = vertices[vertexIdx + 2];
				if (numUV > 0) 
				{
					int uvIdx = facesUV[i] * 2;
					verts[vi++] = uv[uvIdx];
					verts[vi++] = 1 - uv[uvIdx + 1];
				}
				if (numNormals > 0) 
				{
					int normalIdx = facesNormals[i] * 3;
					verts[vi++] = normals[normalIdx];
					verts[vi++] = normals[normalIdx + 1];
					verts[vi++] = normals[normalIdx + 2];
				}
			}
	
	
			float[] dimensions={0,0,0};
			Dimensions(dimensions,numVertices,vertices);
			
			modify mod1=new modify();
			mod1.numFaces=numFaces;
			mod1.numUV=numUV;
			mod1.numNormals=numNormals;
			mod1.numVertices=numVertices;
			mod1.verts=verts;
			mod1.dimensions=dimensions;
			
			//obOut = new FileOutputStream("crate.adi");
			obOut=game.getFileIO().writeFile(file+".adi");
			ob = new ObjectOutputStream(obOut);
			
			//Test myObj = new Test();
			
			ob.writeObject(mod1);
			//end Rahul
			// Connect print stream to the output stream
			//p = new PrintStream( out );
			
			//p.println ("This is written to a file");
			ob.close();
			//p.close();
			System.out.println(file+" done ");
			System.out.println();
			
			
		}
		catch (Exception e)
		{
			System.err.println ("Error writing to file  "+e);
		}
		}
		
	}

}

