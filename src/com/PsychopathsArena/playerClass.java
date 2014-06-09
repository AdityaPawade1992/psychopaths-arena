package com.PsychopathsArena;

import javax.microedition.khronos.opengles.GL10;

import com.PsychopathsHelp.EulerCamera;
import com.PsychopathsHelp.Texture;
import com.PsychopathsHelp.Vector3;
import com.PsychopathsHelp.Vertices3;

public class playerClass
{
	float x,y,z,dirc,updirc,speed,rad,mspeed,lstraf,rstraf,bulx,buly,bulz,buldirc,bulspeed,bvel,bulmov=1,jctr,bctr=0;
	int act,swim=0,jump=0,jumpen=1,swimup=0,lswim=0,shoot=0,shooten=1,styp=0,exchen=1,excntr=0,gren=0;
	int crouch=0,jblk=0,jblkc=0,zoom=0,zoomen=1,zoomcntr=0,zoomlev=67,life=100,gavail=3,respctr=0,kills=0;
	int underw=0,dead=0,pos=0,tag=6,immune=0,immunectr=0,atkl=0,atkr=0,dry=1,chkdis=0,chkctr=0;
	int[][] wepen=new int[][]{{1,100},{0,0},{0,0}};
	
	Vertices3 model,curgun;
	Texture texture;
	public playerClass()
	{
		if(settings.difficulty==0)
		{
			wepen[1][0]=0;
			wepen[1][1]=0;
			wepen[2][0]=1;
			wepen[2][1]=100;
			styp=1;
			gavail=7;
		}
		if(settings.difficulty==1)
		{
			wepen[1][0]=0;
			wepen[1][1]=0;
			wepen[2][0]=1;
			wepen[2][1]=50;
			styp=1;
			gavail=5;
		}
		if(settings.difficulty==2)
		{
			wepen[1][0]=0;
			wepen[1][1]=0;
			wepen[2][0]=0;
			wepen[2][1]=0;
			styp=0;
			gavail=3;
		}
	}
	void render(GL10 gl,EulerCamera cam)
	{
		dirc=cam.getYaw();
		if(styp==0)
		{
			zoomlev=57;
			texture=assets.pistol;
			model=assets.player2;
		}
		if(styp==1)
		{
			zoomlev=10;
			texture=assets.m4;
			model=assets.player;
		}
		if(styp==2)
		{
			zoomlev=37;
			texture=assets.pistol;
			model=assets.player1;
		}
		texture.bind();
		model.bind();
		gl.glPushMatrix();
		if(crouch==0)
			gl.glTranslatef(x, y-.6f, z);
		else
			gl.glTranslatef(x, y-.6f-0.5f, z);
		gl.glRotatef(180+cam.getYaw(), 0, 1, 0);
		model.draw(GL10.GL_TRIANGLES, 0, model.getNumVertices());
		gl.glPopMatrix();
		model.unbind();
	}
	int update(objectClass[] ob,int ocount,barrierClass[] bar,int bcount,botClass[] bot,int btcount,EulerCamera cam,float deltaTime,GL10 gl,int pause)
	{
		//tag=btcount;
		if(y<-7)
		{
			life=-10;
			dead=1;
			respctr=110;
		}
		if(swim==1)
			styp=0;
		while(wepen[styp][0]!=1)
		{
			styp++;
			if(styp>2)
				styp=0;
		}
		if(exchen==0)
		{
			excntr++;
			if(excntr>20)
			{
				excntr=0;
				exchen=1;
			}
		}
		if(zoomen==0)
		{
			zoomcntr++;
			if(zoomcntr>20)
			{
				zoomcntr=0;
				zoomen=1;
			}
		}
		
		Vector3 direction=cam.getPosition();
		x=direction.x;
		y=direction.y;
		z=direction.z;
		dirc=cam.getYaw();
		if(life>0)
		render(gl, cam);
		if(pause==0)
		{
			float[] ret=level(ob,ocount,x,y,z,0,2,deltaTime);
			int objhit=(int) ret[0];
			x=ret[1];
			y=ret[2];
			z=ret[3];
			ret=collision(bar,bcount,x,y,z,0,1);
			if(objhit==0)
			objhit=(int) ret[0];
			x=ret[1];
			y=ret[2];
			z=ret[3];
			if(jblk==1)
			{
				jblkc++;
				if(jblkc>20)
				{
					jblkc=0;
					jblk=0;
				}
			}
			else
				chkjump(deltaTime);
			chkbul(bar,bcount,ob,ocount,cam, deltaTime, gl);
			if(crouch==1)
				jumpen=0;
			if(swim==1)
				crouch=0;
			
			cam.getPosition().set(x, y, z);
			chkhit(bot,btcount,cam);
			if(swim==1&&swimup==0)
			{
				underw++;
				if(underw>100)
					life-=0.2;
				if(life<=0&&dead==0)
				{
					dead=1;
				}
			}
			else
				underw=0;
		
			return objhit;
		}
		else
			return 1;
	}
	
	public void chkhit(botClass[] bot,int btcount,EulerCamera cam)
	{
		atkl=0;
		atkr=0;
		if(immune==0)
		for(int i=0;i<btcount;i++)
		{
			float buldist=(float) Math.sqrt(((x-bot[i].bulx)*(x-bot[i].bulx)+(z-bot[i].bulz)*(z-bot[i].bulz)));
			if(bot[i].gren==0&&buldist<4&&bot[i].bctr>=1&&bot[i].bulmov!=0&&((bot[i].jump==0&&jump==0)||(bot[i].jump==1&&jump==1))&&Math.abs(y-bot[i].buly)<3)
			{

				float dirc1=(float) (Math.atan(-(z-bot[i].z)/(x-bot[i].x))*180/Math.PI);
				dirc1+=90;
				if(bot[i].x<x)
					dirc1+=180;
				float dirc2=dirc-180;
				if(dirc2<0)
					dirc2+=360;
				if(dirc2>360)
					dirc2-=360;
				if(Math.abs(dirc2-dirc1)>=5)
				{
					if(dirc2>dirc1)
						atkr=1;
					else
						atkl=1;
				}
				if(bot[i].crouch==1)
				{
					if(settings.difficulty==0)
					{
						life-=4;
					}
					if(settings.difficulty==1)
					{
						life-=6;
					}
					if(settings.difficulty==2)
					{
						life-=8;
					}	
				}
				else
				{
					if(settings.difficulty==0)
					{
						life-=3;
					}
					if(settings.difficulty==1)
					{
						life-=5;
					}
					if(settings.difficulty==2)
					{
						life-=7;
					}
				}
				bot[i].bulmov=0;
				if(life<=0&&dead==0)
				{
					dead=1;
					bot[i].kills++;
				}
			}
			if(bot[i].gren==1&&buldist<4.5&&bot[i].bctr>50&&(y-bot[i].buly)<3&&(y-bot[i].buly)>0)
			{
				life-=10;
				if(life<=0&&dead==0)
				{
					dead=1;
					bot[i].kills++;
				}
			}
		}
		if(gren==1&&bctr>50)
		{
			float buldist=(float) Math.sqrt(((x-bulx)*(x-bulx)+(z-bulz)*(z-bulz)));
			if(buldist<4)
				life-=10;
			if(life<=0&&dead==0)
			{
				dead=1;
			}
		}
		if(life<=0)
		{
			respctr++;
			if(respctr>100)
			{
				cam.setAngles(0, 0);
				dirc=0;
				immune=1;
				immunectr=0;
				dead=0;
				zoom=0;
				crouch=0;
				styp=0;
				respctr=0;
				if(settings.difficulty==0)
				{
					wepen[1][0]=0;
					wepen[1][1]=0;
					wepen[2][0]=1;
					wepen[2][1]=100;
					gavail=7;
					styp=1;
				}
				if(settings.difficulty==1)
				{
					wepen[1][0]=0;
					wepen[1][1]=0;
					wepen[2][0]=1;
					wepen[2][1]=50;
					gavail=5;
					styp=1;
				}
				if(settings.difficulty==2)
				{
					wepen[1][0]=0;
					wepen[1][1]=0;
					wepen[2][0]=0;
					wepen[2][1]=0;
					gavail=3;
					styp=0;
				}
				life=100;
				cam.getPosition().set(34,5,75);
			}
		}
		else
		{
			if(immunectr<=51)
				immunectr++;
			if(immunectr>50)
				immune=0;
		}
	}
	
	public void chkbul(barrierClass[] bar,int bcount,objectClass[] ob,int ocount,EulerCamera cam,float deltaTime,GL10 gl)
	{
		if(wepen[styp][1]<=0&&styp!=0)
			wepen[styp][0]=0;
		if(shoot==1)
		{
			if(gren==1)
				gavail-=1;
			else
				wepen[styp][1]--;
			shoot=0;
			shooten=0;
			bulx=x;
			if(crouch==0)
				buly=y-0.5f;
			else
				buly=y-1;
			if(styp==1&&zoom==1)
			{
				buly+=0.5;
			}
			bulz=z;
			buldirc=180+cam.getYaw();
			
			if(styp!=1||(styp==1&&zoom==0))
			{
				rad=(float) ((buldirc-180)*Math.PI/180);
				bulx+=0.5*Math.cos(rad);
				bulz+=(-1)*0.5*Math.sin(rad);
				
				buldirc+=2;
			}
			if(gren==0)
			{
				if(styp==0)
					bulspeed=164.5161f;
				else
					bulspeed=264.6451f;
			}
			if(gren==1)
				bulspeed=16.129f;
			bctr=1;
			bulmov=1;
			bvel=0.4f;
		}
		if(bctr>0)
			bctr+=40*deltaTime;
		if((gren==0&&bctr>5)||(gren==1&&bctr>60)||bctr==0)
		{
			bulx=x;
			if(crouch==0)
				buly=y-0.5f;
			else
				buly=y-1;
			bulz=z;
			bctr=0;
			shoot=0;
			shooten=1;
			bulspeed=0;
			gren=0;
		}
		else
		{
			if(gren==1)
			{
				bvel-=0.05f;
				float[] ret=new float[4];
				ret=level(ob, ocount, bulx, buly, bulz, 1, 0,deltaTime);
				int objhit=(int) ret[0];
				bulx=ret[1];
				buly=ret[2];
				bulz=ret[3];
				if(objhit==0)
					buly+=bvel;
			}
			if(bctr>21&&gren==1)
				bulspeed=3.2258f;
			if(bctr>29&&gren==1)
				bulspeed=0;
			
			rad=(float) ((buldirc-90)*Math.PI/180);
			bulx+=bulspeed*Math.cos(rad)*bulmov*deltaTime;
			bulz+=(-1)*bulspeed*Math.sin(rad)*bulmov*deltaTime;
			
			float[] ret=new float[4];
			ret=collision(bar,bcount,bulx,buly,bulz,1,0);
			if(ret[0]==1)
			{
				if(gren==1)
					bulmov*=-1;
				if(gren==0)
					bulmov=0;
			}
			
			if(gren==0)
			{
				curgun=assets.bullet2;
			}
			if(gren==1)
			{
				if(bctr>50)
				{
					if(bctr<52)
					buly-=bvel;
					assets.exp.bind();
					curgun=assets.gexp;
				}
				else
				{
					assets.gre.bind();
					curgun=assets.grenade;
				}
			}
			if(bulmov!=0)
			{
		        curgun.bind();
				gl.glPushMatrix();
				gl.glTranslatef(bulx, buly, bulz);
				gl.glRotatef(buldirc, 0, 1, 0);
				curgun.draw(GL10.GL_TRIANGLES, 0, curgun.getNumVertices());
				gl.glPopMatrix();
				curgun.unbind();
			}
		}
	}
	
	public void chkjump(float deltaTime)
	{
		if(jump==1)
		{
			jctr+=40*deltaTime;
			jumpen=0;
			if(settings.gravity==1)
			{
				if(jctr<20)
					y+=(20-jctr)*1.4*deltaTime;
				else
				{
					if(jctr>50)
					{
						jump=0;
						jctr=0;
						jumpen=1;
					}
				}
			}
			else
			{
				if(jctr<50)
					y+=(50-jctr)*0.6*deltaTime;
				else
				{
					if(jctr>60)
					{
						jump=0;
						jctr=0;
						jumpen=1;
					}
				}
			}
		}
		else
			jumpen=1;
	}
	
	public float[] level(objectClass[] ob,int ocount,float x,float y,float z,int typ,float ht,float deltaTime)
	{
		int objhit=0;
		float maxht=0,wpos=90;
		swim=0;
		lswim=0;
		for(int i=0;i<ocount;i++)
		{
			float heightx=(float) ((ob[i].w-((ob[i].x+ob[i].w)-x))*Math.tan(ob[i].rotz*22.0/1260));
			float heightz=(float) (-1*(ob[i].l-((ob[i].z+ob[i].l)-z))*Math.tan(ob[i].rotx*22.0/1260));
			float xsp=(float) (ob[i].w*Math.cos(ob[i].rotz*22.0/1260));
			float zsp=(float) (ob[i].l*Math.cos(ob[i].rotx*22.0/1260));
			if(x>(ob[i].x-xsp)&&x<(ob[i].x+xsp)&&z>(ob[i].z-zsp)&&z<(ob[i].z+zsp))
			{
				if((ob[i].typ!=-1)&&y>=(ob[i].y+heightx+heightz+(ht-3))&&y<(ob[i].y+heightx+heightz+(ht-1)))
				{
					objhit=0;
					y=ob[i].y+heightx+heightz+(ht-3);
					jump=0;
					jctr=0;
					break;
				}
				if((ob[i].typ!=-1)&&y>=(ob[i].y+heightx+heightz+(ht-1))&&y<(ob[i].y+heightx+heightz+ht))
				{
					if(objhit==0)
					{
						maxht=(float) (ob[i].y+heightx+heightz+ht-0.1f);
					}
					else
					if((ob[i].y+heightx+heightz+ht-0.1f)>maxht)
						maxht=(float) (ob[i].y+heightx+heightz+ht-0.1f);
					objhit=1;
					if(ob[i].typ==-2&&typ==0)
					{
						lswim=1;
					}
				}
				if((ob[i].typ==-1)&&y>=(ob[i].y+heightx+heightz-8)&&y<(ob[i].y+heightx+heightz+(ht-0.5)))
				{
					if(y<(ob[i].y+heightx+heightz+0.2))
					{
						if(typ==0)
						swim=1;
						wpos=ob[i].y+heightx+heightz+0.1f;
					}
				}
			}
		}
		if(objhit!=0&&lswim==0)
			y=maxht;
		else
		{
			if(lswim==1)
			{
				if(y<maxht)
					y=maxht;
				if(swimup==1)
					y+=0.05*40*deltaTime;
			}
			else
			{
				if(swim==1)
				{
					if(swimup==0)
						y-=0.03*40*deltaTime;
					else
						y+=0.05*40*deltaTime;
					if(y>wpos)
						y=wpos;
				}
				else
					if(typ==0)
					y-=0.25*40*deltaTime;
			}
		}
		float[] ret=new float[]{objhit,x,y,z};
		return ret;
	}
	
	public float[] collision(barrierClass[] bar,int bcount,float x,float y,float z,int typ,float ht)
	{
		int objhit=0;
		for(int i=0;i<bcount;i++)
		{
			if(bar[i].typ==2||(bar[i].typ==3&&bar[i].dead!=0))
				continue;
			if(y-ht<(bar[i].y+bar[i].h)&&y-ht>=(bar[i].y-bar[i].h))
			{
				if(x>=(bar[i].x-bar[i].w)&&x<=(bar[i].x+bar[i].w))
				{
					if(z>=(bar[i].z-bar[i].l-1)&&z<=(bar[i].z))
					{
						objhit=1;
						z=(float) (bar[i].z-bar[i].l-1);
						if(typ==0&&bar[i].typ==1)
						{
							chkdis=1;
							y+=5;
						}
					}
					if(z<=(bar[i].z+bar[i].l+1)&&z>=(bar[i].z))
					{
						objhit=1;
						if(typ==0&&bar[i].typ==1)
						{
							chkdis=1;
							y+=5;
						}
						z=(float) (bar[i].z+bar[i].l+1);
					}
				}
				if(z>=(bar[i].z-bar[i].l)&&z<=(bar[i].z+bar[i].l))
				{
					if(x>=(bar[i].x-bar[i].w-1)&&x<=(bar[i].x))
					{
						objhit=1;
						if(typ==0&&bar[i].typ==1)
						{
							chkdis=1;
							y+=5;
						}
						x=(float) (bar[i].x-bar[i].w-1);
					}
					if(x<=(bar[i].x+bar[i].w+1)&&x>=(bar[i].x))
					{
						objhit=1;
						if(typ==0&&bar[i].typ==1)
						{
							chkdis=1;
							y+=5;
						}
						x=(float) (bar[i].x+bar[i].w+1);
					}
				}
			}
			if(chkdis==1)
			{
				chkctr++;
				if(chkctr>10)
				{
					chkctr=0;
					chkdis=0;
				}
			}
			if(x>=(bar[i].x-bar[i].w)&&x<=(bar[i].x+bar[i].w)&&z>=(bar[i].z-bar[i].l)&&z<=(bar[i].z+bar[i].l))
			{
				if(y-ht<=(bar[i].y+bar[i].h+1)&&y-ht>=(bar[i].y))
				{
					y=(float) (bar[i].y+bar[i].h+ht+1);
					if(typ==0)
					objhit=1;
				}
			}
		}
		float[] ret=new float[]{objhit,x,y,z};
		return ret;
	}
	
}