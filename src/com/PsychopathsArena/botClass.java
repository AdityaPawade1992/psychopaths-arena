package com.PsychopathsArena;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.PsychopathsHelp.Vertices3;

public class botClass
{
	int testing=1;
	int cnt=0;
	float x,y,z,dirc,speed,rad,bulx,buly,bulz,buldirc,bulspeed,bulrad,bvel;
	int state=1,chdir=0,dirturn=1,cntr=0,shoot=0,shooten=1,bctr=0,lswim=0,swim=0,swimup=0,styp=0,bulmov=1,gren=0;
	int jump=0,jumpen=1,jctr=0,objhit=0,crouch=0,movframe=0,straifl=0,straifr=0,gavail=0,pavail=1,mavail=1,savail=1;
	int wctr=0,sprog=1,jumpst=0,shootst,life=50,retstate=-1,wp,chen=1,lastpos=-1,prange=10,mrange=20,srange=25,dead=0;
	int crange=0,tag=0,respctr=0,frange=30,tarch=0,kills=0,underw=0,pos=0,immune=1,immunectr=0,chkdis=0,chkctr=0;
	float[] target=new float[]{0.0001f,0,0};
	Vertices3 model,curgun;
	Random rand;
	float[][] spoints,epoints,wpoints;
	int scount,ecount,wcount,wcntr=0;
	int[][] wepen=new int[][]{{1,100},{0,20},{0,20}};
	float[] resp;
	float mindist=-1;
	int mintag=0;
	int enemy=0;
	float plydist=10,enemdist=-1;
	int c1=0,c2=0,c3=0,c4=0;
	//float mu,mb,cu,cb;
	float ux1,ux2,uz1,uz2;
	float[] ul=new float[2];
	float[] ur=new float[2];
	float[] bl=new float[2];
	float[] br=new float[2];
	int flag2=0;
	float[] ntarget=new float[3];
	float mt,ct;
	
	float ptxu;
	float ptzu;
	
	float ptxb;
	float ptzb;

	float ptxl;
	float ptzl;

	float ptxr;
	float ptzr;
	float gredist=0;
	float tardist;
	float dircobr;
	float dircobl;
	float dircobf;
	float dircobb;
	float larger,distbar;
	
	public botClass()
	{
	}
	public void chkdirc()
	{
		dirc=(float) (Math.atan(-(target[2]-z)/(target[0]-x))*180/Math.PI);
		dirc+=90;
		if(target[0]<x)
			dirc+=180;
		
	}
	
	public void settarch(int tarch)
	{
		this.tarch=tarch;
	}
	public void setoffs(float x,float y,float z,float dirc,float[][] spoints,int scount,float[][] epoints,int ecount,float[][] wpoints,int wcount,int tag)
	{
		this.resp=new float[]{x,y,z};
		this.tag=tag;
		this.x=x;
		this.y=y;
		this.z=z;
		this.dirc=dirc;
		rand=new Random();
		this.spoints=spoints;
		this.scount=scount;
		this.epoints=epoints;
		this.ecount=ecount;
		this.wpoints=wpoints;
		this.wcount=wcount;
	}
	void render(GL10 gl)
	{
		if(life<=0)
			this.model=assets.mandead;
		else
		{
			if(crouch==0)
			{
				if(speed!=0)
				movframe++;
				if(jump==1&&jctr<30)
					this.model=assets.manjump;
				else
				{
					if(movframe<10||speed==0)
						this.model=assets.man;
					else
					{
						if(movframe<20)
							this.model=assets.manmov1;
						else
						{
							if(movframe<30)
								this.model=assets.man;
							else
							{
								if(movframe<40)
									this.model=assets.manmov2;
								else
								{
									this.model=assets.man;
									movframe=0;
								}
							}
						}
					}
				}
			}
			else
			{
				movframe=0;
				this.model=assets.manc;
			}
		}
		if(immune==1)
			assets.manTexb[0].bind();
		else
		if(life<10)
		assets.manTexr[0].bind();
		else
			if(life<40)
				assets.manTexo[0].bind();
			else
				assets.manTexg[0].bind();
		model.bind();
		gl.glPushMatrix();
		gl.glTranslatef(x, y, z);
		gl.glRotatef(dirc, 0, 1, 0);
		model.draw(GL10.GL_TRIANGLES, 0, model.getNumVertices());
		gl.glPopMatrix();
		model.unbind();
		if(life>0)
		{
			if(crouch==1)
			{
				float grad=(float) ((dirc-90)*Math.PI/180);
				x+=0.07*Math.cos(grad);
				z+=(-1)*0.07*Math.sin(grad);
			}
			if(styp==0)
			{
				assets.pistol.bind();
				this.model=assets.bpist;
				crange=prange;
			}
			if(styp==1)
			{
				assets.m4.bind();
				this.model=assets.bgun;
				crange=srange;
			}
			if(styp==2)
			{
				assets.pistol.bind();
				this.model=assets.bm4;
				crange=mrange;
			}
	
			model.bind();
			gl.glPushMatrix();
			if(crouch==0)
				gl.glTranslatef(x, y, z);
			else
				gl.glTranslatef(x, y-0.6f, z);
			gl.glRotatef(dirc, 0, 1, 0);
			model.draw(GL10.GL_TRIANGLES, 0, model.getNumVertices());
			gl.glPopMatrix();
			model.unbind();
			if(crouch==1)
			{
				float grad=(float) ((dirc-90)*Math.PI/180);
				x-=0.07*Math.cos(grad);
				z-=(-1)*0.07*Math.sin(grad);
			}
		}
	}
	
	void update(playerClass s,objectClass[] ob,int ocount,barrierClass[] bar,int bcount,botClass[] bot,int btcount,float deltaTime,GL10 gl)
	{	
		if(y<-7)
		{
			life=-10;
			dead=1;
			respctr=110;
		}
		/*if(tag==0)
		{
			System.out.println(x);
			System.out.println(y);
			System.out.println(z);
			System.out.println(state);
		}*/
		if(life>0)
		{
			while(wepen[styp][0]!=1)
			{
				styp++;
				if(styp>2)
					styp=0;
			}
			float[] ret=level(ob,ocount,x,y,z,0,0.6f,deltaTime);
			objhit=(int) ret[0];
			x=ret[1];
			y=ret[2];
			z=ret[3];
			ret=collision(bar,bcount,x,y,z,0,0);
			if(ret[0]==1&&state==6)
			{
				dirc=rand.nextInt(360);
			}
			x=ret[1];
			y=ret[2];
			z=ret[3];
			chkjump(deltaTime);
			chkvector(s, bar, bcount,bot,btcount,deltaTime);
			render(gl);
			chkbul(bar,bcount,ob,ocount,deltaTime, gl);
			if(swim==1&&swimup==0)
			{
				underw++;
				if(underw>100)
					life-=0.3;
				if(life<=0&&dead==0)
					dead=1;
			}
			else
				underw=0;
			chkhit(s,bot,btcount);

			if(immunectr<=51)
				immunectr++;
			if(immunectr>50)
				immune=0;
		}
		else
		{
			if(respctr<20)
			{
				swimup=0;
				float[] ret=level(ob,ocount,x,y,z,0,0.6f,deltaTime);
				objhit=(int) ret[0];
				x=ret[1];
				y=ret[2];
				z=ret[3];
			}
			chkbul(bar,bcount,ob,ocount,deltaTime, gl);
			render(gl);
			respctr++;
			if(respctr>100)
			{
				immune=1;
				immunectr=0;
				life=50;
				respctr=0;
				dead=0;
				wepen[0][0]=1;
				wepen[1][0]=0;
				wepen[2][0]=0;
				wepen[0][1]=1;
				wepen[1][1]=20;
				wepen[2][1]=20;
				gavail=1;
				x=resp[0];
				y=resp[1];
				z=resp[2];
			}
		}
	}
	
	public void chkhit(playerClass s,botClass[] bot,int btcount)
	{
		if(immune==0)
		{
			float buldist=(float) Math.sqrt(((x-s.bulx)*(x-s.bulx)+(z-s.bulz)*(z-s.bulz)));
			float dirc=(float) (Math.atan(-(z-s.z)/(x-s.x))*180/Math.PI);
			dirc+=90;
			if(x<s.x)
				dirc+=180;
			float dirc2=s.dirc-180;
			if(dirc2<0)
				dirc2+=360;
			if(dirc2>360)
				dirc2-=360;
			if(s.gren==0&&plydist<30&&(Math.abs(dirc-dirc2)<=10)&&s.bctr>2&&s.bulmov!=0&&Math.abs(y-s.buly)<3)
			{
				if((s.jump==0&&jump==0)||(s.jump==1&&jump==1))
				{
					if(s.crouch==1)
						life-=8;
					else
						life-=7;
					if(s.styp==1)
						life-=15;
					s.bulmov=0;
					if(life<=0&&dead==0)
					{
						dead=1;
						s.kills++;
					}
				}
			}
			if(s.gren==1&&buldist<4.5&&s.bctr>50&&(y-s.buly)<3&&(y-s.buly)>0)
			{
				life-=10;
				if(life<=0&&dead==0)
				{
					dead=1;
					s.kills++;
				}
			}
			for(int i=0;i<btcount;i++)
			{
				if(i==tag)
					continue;
				buldist=(float) Math.sqrt(((x-bot[i].bulx)*(x-bot[i].bulx)+(z-bot[i].bulz)*(z-bot[i].bulz)));
				if(bot[i].gren==0&&buldist<10&&bot[i].bctr>=1&&bot[i].bulmov!=0&&Math.abs(y-bot[i].buly)<3)
				{
					if(bot[i].crouch==1)
						life-=8;
					else
						life-=7;
					bot[i].bulmov=0;
					if(life<=0&&dead==0)
					{
						dead=1;
						bot[i].kills++;
					}
				}
				if(bot[i].gren==1&&buldist<4.5&&bot[i].bctr>50&&(y-bot[i].buly)<3&&(y-bot[i].buly)>0)
				{
					life-=15;
					if(life<=0&&dead==0)
					{
						dead=1;
						bot[i].kills++;
					}
				}
			}
		}
		if(gren==1&&bctr>50)
		{
			float buldist=(float) Math.sqrt(((x-bulx)*(x-bulx)+(z-bulz)*(z-bulz)));
			if(buldist<1)
				life-=10;
			if(life<=0&&dead==0)
			{
				dead=1;
			}
		}
	}

	public void chkbul(barrierClass[] bar,int bcount,objectClass[] ob,int ocount,float deltaTime,GL10 gl)
	{
		wcntr++;
		if(wcntr>500)
		{
			wepen[1][0]=1;
			wepen[2][0]=1;
			wepen[1][1]=20;
			wepen[2][1]=20;
			gavail=1;
		}

		if(swim==1)
			styp=0;
		else
		if(wepen[2][0]==1)
			styp=2;
		else
			if(wepen[1][0]==1)
				styp=1;
			else
				styp=0;
		if(wepen[styp][1]<=0&&styp!=0)
			wepen[styp][0]=0;
		if(shoot==1)
		{
			wepen[styp][1]--;
			shoot=0;
			shooten=0;
			bulx=x;
			if(crouch==0)
				buly=y+1.3f;
			else
				buly=y+1.3f-0.6f;
			bulz=z;
			buldirc=dirc;
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
			bvel=0.4f;
			bulmov=1;
		}
		if(bctr>0)
			bctr+=40*deltaTime;
		if((gren==0&&bctr>5)||(gren==1&&bctr>60)||bctr==0)
		{
			bulx=x;
			if(crouch==0)
				buly=y+1.3f;
			else
				buly=y+0.7f;
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
			ret=collision(bar,bcount,bulx,buly,bulz,0,1);
			if(ret[0]==1)
			{
				if(gren==1)
					bulmov*=-1;
				if(gren==0)
					bulmov=0;
			}
			
			if(gren==0)
			{
				curgun=assets.bullet;
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
	
	void chkvector(playerClass p,barrierClass[] bars,int bcount,botClass[] bot,int btcount,float deltaTime)
	{
		tarch++;
		if(tarch>100)
		{
			tarch=0;
			mindist=-1;
			mintag=0;
			for(int j=0;j<btcount;j++)
			{
				if(j==tag)
					continue;
				
				float botdist=(float) Math.sqrt(((x-bot[j].x)*(x-bot[j].x)+(z-bot[j].z)*(z-bot[j].z)));
				if(botdist<frange&&(mindist==-1||mindist<botdist)&&Math.abs(y-bot[j].y)<1)
				{
					flag2=0;
					ntarget[0]=bot[j].x;
					ntarget[2]=bot[j].z;
					for(int i=0;i<bcount;i++)
					{
						if(bars[i].typ==1)
							continue;
						c1=0;c2=0;c3=0;c4=0;
						
						ux1=bars[i].x-bars[i].w;
						ux2=bars[i].x+bars[i].w;
						uz1=bars[i].z-bars[i].l;
						uz2=bars[i].z+bars[i].l;
						ul[0]=ux1;
						ul[1]=uz2;
						ur[0]=ux2;
						ur[1]=uz2;
						bl[0]=ux1;
						bl[1]=uz1;
						br[0]=ux2;
						br[1]=uz1;
						//mu=(ul[1]-ur[1])/(ul[0]-ur[0]);
						//mb=(bl[1]-br[1])/(bl[0]-br[0]);
						//cu=ul[1]-(mu*ul[0]);
						//cb=bl[1]-(mb*bl[0]);
	
						mt=(z-ntarget[2])/(x-ntarget[0]);
						//ct=ntarget[2]-(mt*ntarget[0]);

						ptzu=ul[1];
						ptxu=((ptzu-z)/mt)+x;
						//ptxu=(-cu+ct)/(mu-mt);
						//ptzu=(ct*mu-cu*mt)/(mu-mt);
						if((ptxu>=ul[0]&&ptxu<=ur[0])&&((ptzu>=ntarget[2]&&ptzu<=z)||(ptzu<=ntarget[2]&&ptzu>=z)))
							c1=1;

						ptzb=bl[1];
						ptxb=((ptzb-z)/mt)+x;
						//ptxb=(-cb+ct)/(mb-mt);
						//ptzb=(ct*mb-cb*mt)/(mb-mt);
						if((ptxb>=bl[0]&&ptxb<=br[0])&&((ptzb>=ntarget[2]&&ptzb<=z)||(ptzb<=ntarget[2]&&ptzb>=z)))
							c2=1;
	
						ptxl=ul[0];
						ptzl=mt*(ptxl-x)+z;
						//ptzl=mt*ptxl+ct;
						if((ptzl<=ul[1]&&ptzl>=bl[1])&&((ptxl>=ntarget[0]&&ptxl<=x)||(ptxl<=ntarget[0]&&ptxl>=x)))
							c3=1;
	
						ptxr=ur[0];
						ptzr=mt*(ptxr-x)+z;
						//ptzr=mt*ptxr+ct;
						if((ptzr<=ur[1]&&ptzr>=br[1])&&((ptxr>=ntarget[0]&&ptxr<=x)||(ptxr<=ntarget[0]&&ptxr>=x)))
							c4=1;
	
						if(c1+c2+c3+c4>=2)
						{
							flag2=1;
							break;
						}
					}
					if(flag2==1)
						continue;
					mintag=j;
					mindist=botdist;
				}
			}
			enemy=0;
			plydist=(float) Math.sqrt(((x-p.x)*(x-p.x)+(z-p.z)*(z-p.z)));
			enemdist=-1;
			if((plydist<mindist+2||mindist==-1)&&plydist<frange&&Math.abs(y-p.y)<3)
			{
				flag2=0;
				ntarget[0]=p.x;
				ntarget[2]=p.z;
				for(int i=0;i<bcount;i++)
				{
					if(bars[i].typ==1)
						continue;
					c1=0;c2=0;c3=0;c4=0;
					ux1=bars[i].x-bars[i].w;
					ux2=bars[i].x+bars[i].w;
					uz1=bars[i].z-bars[i].l;
					uz2=bars[i].z+bars[i].l;
					ul[0]=ux1;
					ul[1]=uz2;
					ur[0]=ux2;
					ur[1]=uz2;
					bl[0]=ux1;
					bl[1]=uz1;
					br[0]=ux2;
					br[1]=uz1;
					/*mu=(ul[1]-ur[1])/(ul[0]-ur[0]);
					mb=(bl[1]-br[1])/(bl[0]-br[0]);
					cu=ul[1]-(mu*ul[0]);
					cb=bl[1]-(mb*bl[0]);
	*/
					mt=(z-ntarget[2])/(x-ntarget[0]);
					//ct=ntarget[2]-(mt*ntarget[0]);
					
					ptzu=ul[1];
					ptxu=((ptzu-z)/mt)+x;
					//ptxu=(-cu+ct)/(mu-mt);
					//ptzu=(ct*mu-cu*mt)/(mu-mt);
					if((ptxu>=ul[0]&&ptxu<=ur[0])&&((ptzu>=ntarget[2]&&ptzu<=z)||(ptzu<=ntarget[2]&&ptzu>=z)))
						c1=1;

					ptzb=bl[1];
					ptxb=((ptzb-z)/mt)+x;
					//ptxb=(-cb+ct)/(mb-mt);
					//ptzb=(ct*mb-cb*mt)/(mb-mt);
					if((ptxb>=bl[0]&&ptxb<=br[0])&&((ptzb>=ntarget[2]&&ptzb<=z)||(ptzb<=ntarget[2]&&ptzb>=z)))
						c2=1;

					ptxl=ul[0];
					ptzl=mt*(ptxl-x)+z;
					//ptzl=mt*ptxl+ct;
					if((ptzl<=ul[1]&&ptzl>=bl[1])&&((ptxl>=ntarget[0]&&ptxl<=x)||(ptxl<=ntarget[0]&&ptxl>=x)))
						c3=1;

					ptxr=ur[0];
					ptzr=mt*(ptxr-x)+z;
					//ptzr=mt*ptxr+ct;
					if((ptzr<=ur[1]&&ptzr>=br[1])&&((ptxr>=ntarget[0]&&ptxr<=x)||(ptxr<=ntarget[0]&&ptxr>=x)))
						c4=1;
	
					if(c1+c2+c3+c4>=2)
					{
						flag2=1;
						break;
					}
				}
				if(flag2!=1)
				{
					enemdist=plydist;
					enemy=1;
				}
			}
			if(enemdist==-1&&mindist!=-1)
			{
				enemdist=mindist;
			}
			
			if(enemdist==-1)
			{
				swimup=1;
				straifl=0;
				straifr=0;
				jumpst=0;
				crouch=0;
				if(state!=5&&state!=1&&state!=6)
				{
					retstate=1;
					state=1;
				}
			}
			else
			{
				if(enemdist<crange)
					state=2;
				else
				{
					if(enemy==1)
					{
						target[0]=p.x;
						target[1]=p.y;
						target[2]=p.z;
					}
					else
					{
						target[0]=bot[mintag].x;
						target[1]=bot[mintag].y;
						target[2]=bot[mintag].z;
					}
				}
			}
		}
		if(life>0)
		{
			swimup=1;
			if(enemy==1)
			{
				if(p.swim==1&&p.swimup==0)
				{
					swimup=0;
				}
			}
			if(enemy==0&&mindist!=-1)
			{
				if(bot[mintag].swim==1&&bot[mintag].swimup==0)
				{
					swimup=0;
				}
			}
		}
		else
			swimup=0;
		
		if(testing==1)
			state=100;
		
		
		if(state==1)
		{
			retstate=1;
			wp=rand.nextInt(scount);
			if(wp==lastpos)
			{
				wp=rand.nextInt(scount);
			}
			target[0]=spoints[wp][0];
			target[2]=spoints[wp][1];
			state=5;
			lastpos=wp;
		}
		if(state==2)
		{
			
			if(sprog==1)
			{
				wctr++;
				if(wctr>20)
				{
					wctr=0;
					sprog=0;
				}
				
				if((enemy==1&&p.gren==1)||(enemy==0&&bot[mintag].gren==1))
				{
					gredist=0;
					if(enemy==1)
						gredist=(float) Math.sqrt(((x-p.bulx)*(x-p.bulx)+(z-p.bulz)*(z-p.bulz)));
					else
						gredist=(float) Math.sqrt(((x-bot[mintag].bulx)*(x-bot[mintag].bulx)+(z-bot[mintag].bulz)*(z-bot[mintag].bulz)));

					if(gredist<2&&shooten==1&&((enemy==1&&p.bctr<50)||(enemy==0&&bot[mintag].bctr<50)))
					{
						if(settings.difficulty==0)
						wp=rand.nextInt(40);
						else
							if(settings.difficulty==1)
								wp=rand.nextInt(35);
							else
								wp=rand.nextInt(33);
						if(wp==1)
						{
							if(enemy==1)
								p.bctr=100;
							else
								bot[mintag].bctr=100;
							gren=1;
							shoot=1;
						}
					}
					else
					if(gredist<4&&straifl==0&&straifr==0)
					{
						//change

						if(settings.difficulty==0)
						wp=rand.nextInt(49);
						else
							if(settings.difficulty==1)
								wp=rand.nextInt(45);
							else
								wp=rand.nextInt(44);
						if(wp==0)
						{
							straifl=1;
							straifr=0;
							jumpst=1;
						}
						else
						if(wp==1)
						{
							straifl=0;
							straifr=1;
							jumpst=1;
						}
						else
						{
							jumpst=0;
						}
						crouch=0;
						speed=deltaTime*9.2258f;	
					}
				}
				if(enemy==1)
				{
					target[0]=p.x;
					target[1]=p.y;
					target[2]=p.z;
				}
				else
				{
					target[0]=bot[mintag].x;
					target[1]=bot[mintag].y;
					target[2]=bot[mintag].z;
				}
				chkdirc();
				if(shooten==1)
					shoot=1;
				if(jumpen==1&&jumpst==1&&objhit==1)
				{
					jump=1;
				}
			}
			else
			{
				wp=1;
				if(gavail==1)
				{
					wp=rand.nextInt(2);
				}
				if(wp==0)
				{
					if(shooten==1)
					{
						gren=1;
						shoot=1;
						
					}
				}
				else
				{
					wp=rand.nextInt(100);
					
					if(wp<5)
					{
						straifl=1;
						straifr=0;
						jumpst=0;
						crouch=0;
						speed=deltaTime*9.2258f;
					}
					if(wp>=5&&wp<10)
					{
						straifl=0;
						straifr=1;
						jumpst=0;
						crouch=0;
						speed=deltaTime*9.2258f;
					}
					if(wp>=10&&wp<15)
					{
						straifl=0;
						straifr=1;
						jumpst=1;
						crouch=0;
						speed=deltaTime*9.2258f;
					}
					if(wp>=15&&wp<20)
					{
						straifl=1;
						straifr=0;
						jumpst=1;
						crouch=0;
						speed=deltaTime*9.2258f;
					}
					if(swim==1||(wp>=20&&wp<50))
					{
						straifl=0;
						straifr=0;
						jumpst=0;
						crouch=0;
						speed=0;
					}
					if(swim==0&&(styp==1||((styp==0)&&(wp>=50))||((styp==2)&&(wp>=50))))
					{
						straifl=0;
						straifr=0;
						jumpst=0;
						crouch=1;
						speed=0;
					}
					
					sprog=1;
				}
			}
		}
		if(state==5)
		{
			int cnt2=0;
			chkdirc();
			straifl=0;
			straifr=0;
			crouch=0;
			jump=0;
			if(swim!=0)
				speed=deltaTime*5.8387f;
			else
				speed=deltaTime*4.2258f;
			
			tardist=(float) Math.sqrt(((x-target[0])*(x-target[0])+(z-target[2])*(z-target[2])));
			
			if(tardist>2)
			{
				if(swim==0)
					chen=1;
				if(swim==1&&chen==1)
				{
					wp=rand.nextInt(wcount);
					target[0]=wpoints[wp][0];
					target[2]=wpoints[wp][1];
					chen=0;
				}
				if(chdir==1)
				{
					if(target[0]>x)
						dirturn=-10;
					else
						dirturn=10;
				}
				chdir=0;
				flag2=0;
				cnt=0;
				ntarget[0]=target[0];
				ntarget[2]=target[2];
				for(int i=0;i<bcount;i++)
				{
					if(bars[i].typ==1)
						continue;
					c1=0;c2=0;c3=0;c4=0;
					ux1=bars[i].x-bars[i].w;
					ux2=bars[i].x+bars[i].w;
					uz1=bars[i].z-bars[i].l;
					uz2=bars[i].z+bars[i].l;
					ul[0]=ux1;
					ul[1]=uz2;
					ur[0]=ux2;
					ur[1]=uz2;
					bl[0]=ux1;
					bl[1]=uz1;
					br[0]=ux2;
					br[1]=uz1;
					/*mu=(ul[1]-ur[1])/(ul[0]-ur[0]);
					mb=(bl[1]-br[1])/(bl[0]-br[0]);
					cu=ul[1]-(mu*ul[0]);
					cb=bl[1]-(mb*bl[0]);*/
					
					mt=(z-ntarget[2])/(x-ntarget[0]);
					//ct=ntarget[2]-(mt*ntarget[0]);
					
					ptzu=ul[1];
					ptxu=((ptzu-z)/mt)+x;
					//ptxu=(-cu+ct)/(mu-mt);
					//ptzu=(ct*mu-cu*mt)/(mu-mt);
					if((ptxu>=ul[0]&&ptxu<=ur[0])&&((ptzu>=ntarget[2]&&ptzu<=z)||(ptzu<=ntarget[2]&&ptzu>=z)))
						c1=1;

					ptzb=bl[1];
					ptxb=((ptzb-z)/mt)+x;
					//ptxb=(-cb+ct)/(mb-mt);
					//ptzb=(ct*mb-cb*mt)/(mb-mt);
					if((ptxb>=bl[0]&&ptxb<=br[0])&&((ptzb>=ntarget[2]&&ptzb<=z)||(ptzb<=ntarget[2]&&ptzb>=z)))
						c2=1;

					ptxl=ul[0];
					ptzl=mt*(ptxl-x)+z;
					//ptzl=mt*ptxl+ct;
					if((ptzl<=ul[1]&&ptzl>=bl[1])&&((ptxl>=ntarget[0]&&ptxl<=x)||(ptxl<=ntarget[0]&&ptxl>=x)))
						c3=1;

					ptxr=ur[0];
					ptzr=mt*(ptxr-x)+z;
					//ptzr=mt*ptxr+ct;
					if((ptzr<=ur[1]&&ptzr>=br[1])&&((ptxr>=ntarget[0]&&ptxr<=x)||(ptxr<=ntarget[0]&&ptxr>=x)))
						c4=1;

					if(c1+c2+c3+c4>=2)
					{
						flag2=1;
						break;
					}
				}
				if(flag2==1)
				for(int i=0;i<bcount;i++)
				{
					if(bars[i].typ==1)
						continue;
					cnt2++;
					dircobr=(float) ((float) Math.atan(-(bars[i].z-z)/(bars[i].x+bars[i].w+.1-x))*180/Math.PI);
					dircobr+=90;
					if(bars[i].x+bars[i].w+.1<x)
						dircobr+=180;

					dircobl=(float) ((float) Math.atan(-(bars[i].z-z)/(bars[i].x-bars[i].w-.1-x))*180/Math.PI);
					dircobl+=90;
					if(bars[i].x-bars[i].w-.1<x)
						dircobl+=180;
					
					dircobf=(float) ((float) Math.atan(-(bars[i].z-bars[i].l-.1-z)/(bars[i].x-x))*180/Math.PI);
					dircobf+=90;
					if(bars[i].x<x)
						dircobf+=180;
					dircobb=(float) ((float) Math.atan(-(bars[i].z+bars[i].l+.1-z)/(bars[i].x-x))*180/Math.PI);
					dircobb+=90;
					if(bars[i].x<x)
						dircobb+=180;
					
					int c1=0,c2=0,c3=0,c4=0;
					if((dircobf>dircobb&&(x<bars[i].x-bars[i].w)&&(dirc<dircobf&&dirc>dircobb))||(dircobf<dircobb&&(x<bars[i].x-bars[i].w)&&(dirc<dircobf||dirc>dircobb)))
						c1=1;
					if((dircobf<dircobb&&(x>=bars[i].x+bars[i].w)&&(dirc>dircobf&&dirc<dircobb))||(dircobf>dircobb&&(x>=bars[i].x+bars[i].w)&&(dirc>dircobf||dirc<dircobb)))
						c2=1;
					if((dircobl<dircobr&&(z<bars[i].z-bars[i].l)&&(dirc>dircobl&&dirc<dircobr))||(dircobl>=dircobr&&(z<bars[i].z-bars[i].l)&&(dirc>dircobl||dirc<dircobr)))
						c3=1;
					if((dircobl>dircobr&&(z>=bars[i].z+bars[i].l)&&(dirc>dircobr&&dirc<dircobl))||(dircobl<dircobr&&(z>=bars[i].z+bars[i].l)&&(dirc>dircobr||dirc<dircobl)))
						c4=1;
					if(c1==1||c2==1||c3==1||c4==1)
					{
						distbar=(float) Math.sqrt(((x-bars[i].x)*(x-bars[i].x)+(z-bars[i].z)*(z-bars[i].z)));
						if(bars[i].w>bars[i].l)
							larger=bars[i].w;
						else
							larger=bars[i].l;
						if(tardist+larger>distbar)
						{
							dirc+=dirturn;
							if(dirc>360)
								dirc-=360;
							if(dirc<0)
								dirc+=360;
							i=-1;
							cnt++;
							if(cnt>100)
							{
								dirc=rand.nextInt(360);
								state=6;
								break;
							}
						}
						else
							cnt=0;
					}
					if(cnt2>36)
					{
						dirc=rand.nextInt(360);
						state=6;
						break;
					}
				}
			}
			else
			{
				state=retstate;
				chdir=1;
				speed=0;
			}
		}
		if(state==6)
		{
			cntr++;
			if(cntr>200)
			{
				cntr=0;
				state=5;
			}
		}
		if(straifl==1)
		{
			speed=deltaTime*9.8387f;
			dirc+=90;
		}
		if(straifr==1)
		{
			speed=deltaTime*9.8387f;
			dirc-=90;
		}
		if(chkdis==1)
			speed=deltaTime*9.8387f;
		rad=(float) ((dirc-90)*Math.PI/180);
        x+=speed*Math.cos(rad);
        z+=(-1)*speed*Math.sin(rad);
        if(straifl==1)
		{
			dirc-=90;
		}
        if(straifr==1)
		{
			dirc+=90;
		}
	}
	public void chkjump(float deltaTime)
	{
		if(jump==1)
		{
			crouch=0;
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
					if(y<(ob[i].y+heightx+heightz-1))
					{
						if(typ==0)
						swim=1;
						wpos=ob[i].y+heightx+heightz-1.1f;
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
				if(y<maxht+1)
					y=maxht+0.9f;
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
	
	public float[] collision(barrierClass[] bar,int bcount,float x,float y,float z,float ht,int typ)
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
						if(typ==0&&bar[i].typ==1)
						{
							chkdis=1;
							y+=5;
						}
						z=(float) (bar[i].z-bar[i].l-1);
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
				if(y-ht<=(bar[i].y+bar[i].h+.08)&&y-ht>=(bar[i].y))
					y=(float) (bar[i].y+bar[i].h+ht);
			}
		}
		float[] ret=new float[]{objhit,x,y,z};
		return ret;
	}

	
}
