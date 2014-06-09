package com.PsychopathsArena;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.PsychopathsHelp.Vertices3;

public class particles                      // Create A Structure For Particle
{
    public boolean    active;                 // Active (Yes/No)
    public float   life;                   // Particle Life
    public float   fade;
    int randop;
    public float x,y,z,xi,yi,zi,xg,yg,zg,r,g,b,xset,yset,zset,psize,srate,pset,xspeed=0,yspeed=0;
    Random random=new Random();
    Vertices3 model=assets.wall1;
    private float vertices[] = {
			-0.1f ,0.1f,
			0.1f,0.1f,
			-0.1f,-0.1f,
			
		};
	
	private FloatBuffer vertBuff;
	
	private short[] pIndex={0,1,2};
	ShortBuffer pBuff;
		
    public particles()
    {
    	ByteBuffer bBuff=ByteBuffer.allocateDirect(vertices.length*4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff=bBuff.asFloatBuffer();
		vertBuff.put(vertices);
		vertBuff.position(0);
		
		ByteBuffer pbBuff=ByteBuffer.allocateDirect(pIndex.length*2);
		pbBuff.order(ByteOrder.nativeOrder());
		pBuff=pbBuff.asShortBuffer();
		pBuff.put(pIndex);
		pBuff.position(0);
    }
    public int rand(int n)
    {
    	randop=random.nextInt(n);
    	return randop;
    }
	void initpart(float xoff,float yoff,float zoff,float poff,float soff)
	{
		xset=xoff;
		yset=yoff;
		zset=zoff;
		x=xset;
		y=yset;
		z=zset;
		active=true;                
		life=50.0f; 
		fade=(float)(rand(10))+1;
		//fade=5;
		//fade=(float)(rand(10))/1000.0f+0.003f;
		xi=(float)(rand(10))-5;       
		yi=(float)(rand(10))-2;       
		zi=(float)(rand(10))-5; 
		
		xg=0.0f;                    
		yg=0.074f;                   
		zg=0.0f;   
		srate=(float) (Math.pow(10,2));
		//srate=1;
		psize=poff;
		pset=poff;
		
		System.out.println("xi  "+xi);
		System.out.println("srate  "+srate);
	}
	void updatepart(GL10 gl)
	{
		if (active)							// If The Particle Is Active
		{
			if(psize>-.005)
				psize-=.00001;
			// Draw The Particle Using Our RGB Values, Fade The Particle Based On It's Life
			/*glBegin(GL_TRIANGLE_STRIP);						// Build Quad From A Triangle Strip
				glTexCoord2d(1,1); glVertex3f(x+0.005f+psize,y+0.005f+psize,z); // Top Right
				glTexCoord2d(0,1); glVertex3f(x-0.005f-psize,y+0.005f+psize,z); // Top Left
				glTexCoord2d(1,0); glVertex3f(x+0.005f+psize,y-0.005f-psize,z); // Bottom Right
				glTexCoord2d(0,0); glVertex3f(x-0.005f-psize,y-0.005f-psize,z); // Bottom Left
				glNormal3d(0, 0, 1);
			glEnd();*/
			model.bind();
			gl.glPushMatrix();
			gl.glTranslatef(x, y, z);
			gl.glScalef(psize, psize, psize);
			//model.draw(GL10.GL_TRIANGLES, 0, model.getNumVertices());
			gl.glFrontFace(GL10.GL_CW);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
			gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glPopMatrix();
			model.unbind();
			 
			//System.out.println("oldere  "+y);
			
			x+=xi/(srate);// Move On The X Axis By X Speed
			y+=yi/(srate);// Move On The Y Axis By Y Speed
			z+=zi/(srate);// Move On The Z Axis By Z Speed

			System.out.println("newer  "+y);
			
			xi+=xg;			// Take Pull On X Axis Into Account
			yi+=yg;			// Take Pull On Y Axis Into Account
			zi+=zg;			// Take Pull On Z Axis Into Account
			
			life-=fade;		// Reduce Particles Life By 'Fade'

			if (life<0.0f)					// If Particle Is Burned Out
			{
				//System.out.println("fffuuu");
				life=50.0f;	
				psize=pset;

				x=xset;
				y=yset;
				z=zset;

				fade=(float)(rand(10))+1;
				//fade=10;
				//fade=(float)(rand(10))/1000.0f+0.003f;
				xi=(float)(rand(10))-5;       
				yi=(float)(rand(10))-2;       
				zi=(float)(rand(10))-5;
			}
					
		}
	}
} ;