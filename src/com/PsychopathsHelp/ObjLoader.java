package com.PsychopathsHelp;

import java.io.InputStream;
import java.io.ObjectInputStream;

import com.PsychopathsArena.modify;

public class ObjLoader 
{

	public static Vertices3 load(GLGame game, String file)
	{
		InputStream In = null;
		try 
		{
			In = game.getFileIO().readAsset(file);
			ObjectInputStream obIn = new ObjectInputStream(In);
			
			modify obj = (modify)obIn.readObject();
			//mod obj = new mod(); 
			Vertices3 model = new Vertices3(game.getGLGraphics(), obj.numFaces * 3,0, false, obj.numUV > 0, obj.numNormals > 0);
			model.setVertices(obj.verts, 0, obj.verts.length);
			
			model.setDimensions(obj.dimensions);
			
			return model;
		} 
		catch (Exception ex) 
		{
			throw new RuntimeException("couldn't load '" + file + "'", ex);
		} 
		finally 
		{
			if (In != null)
			try 
			{
				In.close();
			} 
			catch (Exception ex) 
			{
			}
		}
	}
	
	static int getIndex(String index, int size) 
	{
		int idx = Integer.parseInt(index);
		if (idx < 0)
		return size + idx;
		else
		return idx - 1;
	}
	
	
}

