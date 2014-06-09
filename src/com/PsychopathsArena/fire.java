package com.PsychopathsArena;

import javax.microedition.khronos.opengles.GL10;

class fire
{
	int size=50;
	particles[] particle=new particles[size];
	float x,y,z;
	
	fire()
	{
		
	}
	
	fire(float xoff,float yoff,float zoff)
	{
		x=xoff;
		y=yoff;
		z=zoff;
		
		for (int loop=0;loop<size;loop++)                   // Initialize All The Textures
		{
			particle[loop]=new particles();
			particle[loop].initpart(x,y,z,1,10);
		}
	}
	void updatefire(GL10 gl)
	{
		//glBlendFunc(GL10.GL_SRC_ALPHA,GL_ONE);
		for (int loop=0;loop<size;loop++)					// Loop Through All The Particles
		{
			particle[loop].updatepart(gl);
		}
		//glBlendFunc(GL_SRC_ALPHA,GL_ZERO);
	}
} 