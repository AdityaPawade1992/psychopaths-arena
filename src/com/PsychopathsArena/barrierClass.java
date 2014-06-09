package com.PsychopathsArena;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.PsychopathsHelp.Texture;
import com.PsychopathsHelp.Vertices3;

public class barrierClass
{
	float x,y,z,l,w,h,roty;
	Vertices3 model,cmodel;
	Texture texture,ctexture;
	int typ,dead=0,rctr=0,styp=0;
	float life=3;
	float resx=0, resy=0, resz=0;
	public barrierClass()
	{
		
	}

	public void setoffs(Vertices3 model,Texture texture,float x,float y,float z,float roty,int typ)
	{
		this.typ=typ;
		this.roty=roty;
		this.x=x;
		this.y=y;
		this.z=z;
		this.model=model;
		this.texture=texture;
		float[] dimensions={0,0,0};
		model.getDimensions(dimensions);
		this.l=dimensions[0]/2.0f;
		this.w=dimensions[1]/2.0f;
		this.h=dimensions[2]/2.0f;
		this.y+=this.h;
		if(typ!=3)
		{
			this.cmodel=model;
			this.ctexture=texture;
		}
	}
	void render(GL10 gl,playerClass p)
	{
		if(typ==3&&dead==0)
		{
			cmodel=model;
			ctexture=texture;
			float dirc=(float) (Math.atan(-(z-p.z)/(x-p.x))*180/Math.PI);
			dirc+=90;
			if(x<p.x)
				dirc+=180;
			float dirc2=p.dirc-180;
			if(dirc2<0)
				dirc2+=360;
			if(dirc2>360)
				dirc2-=360;
			if((Math.abs(dirc-dirc2)<=5)&&p.bctr>3&&(Math.abs(y-p.y)<=10))
			{
				life-=1;
			}
			
			if(life<0)
			{
				dead=1;
				y-=1;
				Random rand=new Random();
				int ran=rand.nextInt(7);
				if(ran==0)
					styp=0;
				if(ran==1||ran==2)
					styp=1;
				if(ran==3||ran==4)
					styp=2;
				if(ran==5||ran==6)
					styp=3;
				if(styp==0)
				{
					ctexture=assets.grndTex;
					cmodel=assets.hlth;
				}
				if(styp==1)
				{
					ctexture=assets.m4;
					cmodel=assets.bgun;
				}
				if(styp==2)
				{
					ctexture=assets.pistol;
					cmodel=assets.bm4;
				}
				if(styp==3)
				{
					cmodel=assets.grenade;
					ctexture=assets.gre;
					y+=1;
				}
			}
		}
		if(typ==3&&dead==1)
		{
			float plydist=(float) Math.sqrt(((x-p.x)*(x-p.x)+(z-p.z)*(z-p.z)));
			if(plydist<2)
			{
				if(styp==0)
				{
					p.life=100;
				}
				if(styp==1)
				{
					p.wepen[1][0]=1;
					p.wepen[1][1]+=50;
				}
				if(styp==2)
				{
					p.wepen[2][0]=1;
					p.wepen[2][1]+=50;
				}
				if(styp==3)
				{
					p.gavail+=3;
					y-=1;
				}
				dead=2;

				y+=1;
			}
		}
		if(dead==2)
		{
			rctr++;
			if(rctr>250)
			{
				life=3;
				rctr=0;
				dead=0;
				cmodel=model;
				ctexture=texture;
			}
		}
		else
		{
			ctexture.bind();
			cmodel.bind();
			gl.glPushMatrix();
			gl.glTranslatef(x, y, z);
			gl.glRotatef(roty ,0, 1, 0);
			
			cmodel.draw(GL10.GL_TRIANGLES, 0, cmodel.getNumVertices());
			gl.glPopMatrix();
			cmodel.unbind();
		}
	}
}