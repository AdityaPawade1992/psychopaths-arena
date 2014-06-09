package com.PsychopathsArena;

import javax.microedition.khronos.opengles.GL10;

import com.PsychopathsHelp.Texture;
import com.PsychopathsHelp.Vertices3;

public class objectClass
{
	public float x,y,z,l,w,rotx,rotz,roty,heightu,heightl,dirc,depth,typ;
	Vertices3 model;
	Texture texture,water;
	int cntr=0;
	int climb=0;
	public objectClass()
	{
		
	}
	public void setclimb()
	{
		climb=1;
	}
	public void setoffs(Vertices3 model,Texture texture,float x,float y,float z,float rotx,float roty,float rotz,int typ,int dirc)
	{
		this.water=assets.water2;
		this.typ=typ;
		this.dirc=dirc;
		this.x=x;
		this.y=y;
		this.z=z;
		this.rotx=rotx;
		this.roty=roty;
		this.rotz=rotz;
		this.model=model;
		this.texture=texture;
		float[] dimensions={0,0,0};
		model.getDimensions(dimensions);
		this.l=dimensions[0]/2.0f;
		this.w=dimensions[1]/2.0f;
		float heightux=(float) ((w)*Math.tan(rotz*22.0/1260));
		float heightuz=(float) (-1*(l)*Math.tan(rotx*22.0/1260));
		float heightlx=(float) ((w-(2*w))*Math.tan(rotz*22.0/1260));
		float heightlz=(float) (-1*(l-(2*l))*Math.tan(rotx*22.0/1260));
		heightu=y+heightux+heightuz;
		heightl=y+heightlx+heightlz;
		if(heightu<heightl)
		{
			heightu=heightu+heightl;
			heightl=heightu-heightl;
			heightu=heightu-heightl;
		}
		heightl+=.16;
	}
	void render(GL10 gl)
	{
		if(typ==-1)
		cntr++;
		if(cntr>25)
			water.bind();
		else
			texture.bind();
		model.bind();
		gl.glPushMatrix();
		gl.glTranslatef(x, y, z);
		gl.glRotatef(rotx, 1, 0, 0);
		gl.glRotatef(rotz, 0, 0, 1);
		gl.glRotatef(roty, 0, 1, 0);
		model.draw(GL10.GL_TRIANGLES, 0, model.getNumVertices());
		gl.glPopMatrix();
		model.unbind();
		if(cntr>50)
			cntr=0;
	}
}