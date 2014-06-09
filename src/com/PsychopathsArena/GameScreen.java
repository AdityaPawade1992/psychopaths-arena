package com.PsychopathsArena;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.PsychopathsHelp.AmbientLight;
import com.PsychopathsHelp.Camera2D;
import com.PsychopathsHelp.EulerCamera;
import com.PsychopathsHelp.GLScreen;
import com.PsychopathsHelp.Game;
import com.PsychopathsHelp.PointLight;
import com.PsychopathsHelp.SpriteBatcher;
import com.PsychopathsHelp.Vector2;
import com.PsychopathsHelp.Vector3;
import com.PsychopathsHelp.Input.TouchEvent;

public class GameScreen extends GLScreen
{
	//FPSCounter fps;
	EulerCamera cam;
	Camera2D cam2d;
	SpriteBatcher batcher;
	Vector2 touchPos;
	int movbutx,movbuty;
	int starctr=0;
	float lastX=-1;
	float lastY=-1;
	float lastX1=-1;
	float lastY1=-1;
	float px,py,pz;
	int ocount=1,pEnable=1,pFlag=0;
	int bcount=1,gamest=0,pos=0;
	AmbientLight ambient;
	PointLight point1,point2,point3,point4,point5,point6,point7;
	playerClass player;
	botClass[] bot;
	int btcount;
	int objhit=0,deadt=0,pause=0,prel=0,gamerun=1;
	barrierClass[] bar;
	objectClass[] obj;
	float[][] spoints;
	int scount;
	float[][] epoints;
	int ecount;
	float[][] wpoints;
	int wcount,pausectr=0,scr=0;
	int[] lightState=new int[5];
	GL10 gl=glGraphics.getGL();
	int movindx=-1;
	float lastmovx=-1,lastmovy=-1;
	//fire myFire;
	
	public GameScreen(Game game) {
		super(game);

		game.getInput().getTouchEvents();
		settings.mainscr=0;
		point1=new PointLight();
		point2=new PointLight();
		point3=new PointLight();
		point4=new PointLight();
		point5=new PointLight();
		point6=new PointLight();
		point7=new PointLight();

		point6.setPosition(0, 1, 40);
		point6.setAmbient(0f, 0f, 0f, 0);
		point6.setDiffuse(1, 1, 1, 1);
		point6.setAttenuation(0, 0.05f, 0.02f);
		
		point7.setPosition(0, 1, 40);
		point7.setAmbient(1f, 0.5f, 0.5f, 0);
		point7.setDiffuse(1, 0.5f, 0.5f, 1);
		point7.setAttenuation(0, 0.05f, 0.02f);
		
		cam=new EulerCamera(67, glGraphics.getWidth()/(float)glGraphics.getHeight(), 0.1f, 200);
		cam2d=new Camera2D(glGraphics,480,320);
		batcher=new SpriteBatcher(glGraphics, 1);
		touchPos=new Vector2();
		ambient = new AmbientLight();

		player=new playerClass();

		bar=new barrierClass[100];
		for(int i=0;i<bar.length;i++)
		bar[i]=new barrierClass();
		
		obj=new objectClass[100];
		for(int i=0;i<obj.length;i++)
		obj[i]=new objectClass();

		bot=new botClass[8];
		for(int i=0;i<bot.length;i++)
		{
			bot[i]=new botClass();
		}
		int[] ret=new int[8];
		
		//myFire=new fire(3*5.854f+15, 2, 7*5.854f+30);
		
		if(settings.level==1)
			ret=LevelDesign.setLev1(bar, obj, bot, ocount, bcount, btcount, cam, point1, point2, point3, point4, point5,ambient);
		if(settings.level==2)
			ret=LevelDesign.setLev2(bar, obj, bot, ocount, bcount, btcount, cam, point1, point2, point3, point4, point5,ambient);
		bcount=ret[0];
		ocount=ret[1];
		btcount=ret[2];
		lightState[0]=ret[3];
		lightState[1]=ret[4];
		lightState[2]=ret[5];
		lightState[3]=ret[6];
		lightState[4]=ret[7];
		//int tar=0,init=0;
		//fps=new FPSCounter();
		if(settings.sound==1)
		{
			//assets.music.play();
			//assets.music.setLooping(true);
			//assets.music.setVolume(1);
		}
		gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
		//gl.glDisable(GL10.GL_DITHER);
		
		//fire.initpart(0, 0, 0, 0, 2);
	}

	@Override
	public void update(float deltaTime) 
	{
		/*if(settings.sound==1&&!assets.music.isStopped())
			assets.music.play();*/
		//if(settings.sound==0&&!assets.music.isStopped())
			//assets.music.pause();
		
		if(player.kills>=20)
			pause=1;
		for(int i=0;i<btcount;i++)
			if(bot[i].kills>=20)
				pause=1;
		
		if(pause==2)
		{
			List<TouchEvent> touchEvent=game.getInput().getTouchEvents();
			game.getInput().getKeyEvents();
			int len=touchEvent.size();
			for(int i=0;i<len;i++)
			{
				float x=game.getInput().getTouchX(0);
				float y=game.getInput().getTouchY(0);
				cam2d.touchToWorld(touchPos.set(x, y));
				TouchEvent event=touchEvent.get(i);
				if(event.type==TouchEvent.TOUCH_DOWN)
				{
					if(scr==0)
					{
						if(touchPos.y<70&&touchPos.x<=160)
						{
							gamerun=1;
							scr=1;
						}
						if(touchPos.y<70&&touchPos.x>160)
						{
							game.getInput().getTouchEvents();
							settings.exit=1;
							game.setScreen(new mainScreen(game));
						}
						if(touchPos.y>=70)
						{
							pause=0;
						}
						break;
					}
					if(scr==1)
					{
						if(touchPos.y<=85)
						{
							scr=6;
						}
						else
						if(touchPos.y>85&&touchPos.y<150)
						{
							scr=5;
						}
						/*else
						if(touchPos.y>110&&touchPos.y<160)
						{
							if(settings.sound==0)
							{
								//assets.music.play();
								//assets.music.setLooping(true);
								//assets.music.setVolume(1);
								settings.sound=1;
							}
							else
								settings.sound=0;
						}*/
						else
						if(touchPos.y>150&&touchPos.y<213)
						{
							if(settings.invert==-1)
								settings.invert=1;
							else
								settings.invert=-1;
						}
						break;
					}
					if(scr==3)
					{
						scr=4;
						break;
					}
					if(scr==4)
					{
						scr=5;
						break;
					}
					if(scr==7)
					{
						scr=5;
						break;
					}
					if(scr==5)
					{
						if(touchPos.y>40&&touchPos.y<100)
						{
							if(settings.controls==1)
								scr=7;
							else
								scr=3;
						}
						else
						if(touchPos.y>110&&touchPos.y<160)
						{
							if(settings.controls==0)
								settings.controls=1;
						}
						else
						if(touchPos.y>110&&touchPos.y<213)
						{
							if(settings.controls==1)
								settings.controls=0;
						}
						break;
					}
					if(scr==6)
					{
						if(touchPos.y>155&&touchPos.y<220)
						{
							settings.difficulty=2;
						}
						else
						if(touchPos.y>100&&touchPos.y<155)
						{
							settings.difficulty=1;
						}
						else
						if(touchPos.y>35&&touchPos.y<100)
						{
							settings.difficulty=0;
						}
						break;
					}
				}
			}
		}
		if(player.life>0&&pause==0)
		{
			gamerun=1;
			//game.getInput().getTouchEvents();
			cam.rotate(0);
			float accx=game.getInput().getAccelX();
			accx-=6;
			float accy=game.getInput().getAccelY();
			float accz=game.getInput().getAccelZ();
			
			/*if(Math.abs(accy)>Math.abs(accx))
				accx/=20;
			else
				accy/=20;*/
			if(accx<1&&accx>-1)
				accx=0;
			if(accy<1&&accy>-1)
				accy=0;
			if(settings.controls==0)
			{
				if(accz>0)
				{
					if(player.zoom==0)
						cam.rotate(settings.invert*accy,0);
					else
					{
						if(player.styp==1)
							cam.rotate(settings.invert*accy/6,0);
						else
							cam.rotate(settings.invert*accy,0);
					}
				}
				else
				{
					if(player.zoom==0)
						cam.rotate(settings.invert*(-accy),0);
					else
					{
						if(player.styp==1)
							cam.rotate(settings.invert*(-accy)/6,0);
						else
							cam.rotate(settings.invert*(-accy),0);
					}
				}
				if(((accz>=0&&accx>=1)||(accz<0&&accx<-1)))
				{
					Vector3 direction=cam.getDirection();
					if(player.swim==0)
					{
						if(player.crouch==0)
							cam.getPosition().sub(direction.mul(deltaTime*7.4516f));
						else
							cam.getPosition().sub(direction.mul(deltaTime*2.6129f));
					}
					else
						cam.getPosition().sub(direction.mul(deltaTime*5.8387f));
				}
				if(((accz>=0&&accx<=-1)||(accz<0&&accx>1)))
				{
					Vector3 direction=cam.getDirection();
					if(player.swim==0)
					{
						if(player.crouch==0)
							cam.getPosition().add(direction.mul(deltaTime*7.4516f));
						else
							cam.getPosition().add(direction.mul(deltaTime*2.6129f));
					}
					else
						cam.getPosition().add(direction.mul(deltaTime*5.8387f));
				}
			}
			pFlag=0;
			int swimflag=0;
			
			game.getInput().getTouchEvents();
			game.getInput().getKeyEvents();
			if(player.chkdis==1)
			{
				Vector3 direction2=cam.getDirection();
				cam.getPosition().add(direction2.mul(deltaTime*19.4516f));
			}

			int movflag=0;
			for(int i=0;i<2;i++)
			{
				float x=game.getInput().getTouchX(i);
				float y=game.getInput().getTouchY(i);
				cam2d.touchToWorld(touchPos.set(x, y));
				if(settings.controls==0)
				{
					if(game.getInput().isTouchDown(i))
					{
						if(touchPos.x<64&&touchPos.y<64)
						{
							if(player.crouch==1)
							{
								player.jblk=1;
								player.crouch=0;
							}
							else
							{
								if(player.swim==0&&player.lswim==0)
								{
									if(player.jumpen==1&&objhit==1)
									{
										player.jump=1;
									}
								}
								else
								{
									swimflag=1;
									player.swimup=1;
								}
							}
						}
						else
						{
							if(touchPos.x>416&&touchPos.y<64)
							{
								pFlag=1;
								if((player.styp==2||pEnable==1)&&player.shooten==1)
								{
									pEnable=0;
									player.shoot=1;
									/*if(player.styp==2)
										assets.playsound(assets.sound2);
									else
										assets.playsound(assets.sound1);*/
								}
							}
							else
							{
								if(touchPos.x>416&&touchPos.y>256)
								{
									if(player.exchen==1&&player.shooten==1)
									{
										player.exchen=0;
										player.styp++;
										if(player.styp>2)
											player.styp=0;
									}
								}
								else
								{
									if(touchPos.x<64&&touchPos.y>256)
									{
										if(player.shooten==1&&player.gavail>0)
										{
											player.gren=1;
											player.shoot=1;
										}
									}
									else
									{
											if(touchPos.x<100&&touchPos.y>100)
											{
												cam.rotate(90,0);
												Vector3 direction=cam.getDirection();
												if(player.swim==0)
												{
													if(player.crouch==0)
														cam.getPosition().add(direction.mul(deltaTime*5.8387f));
													else
														cam.getPosition().add(direction.mul(deltaTime*2.6129f));
												}
												else
													cam.getPosition().add(direction.mul(deltaTime*4.2258f));
												cam.rotate(-90,0);
											}
											if(touchPos.x>380&&touchPos.y>100)
											{
												cam.rotate(-90,0);
												Vector3 direction=cam.getDirection();
												if(player.swim==0)
												{
													if(player.crouch==0)
														cam.getPosition().add(direction.mul(deltaTime*5.8387f));
													else
														cam.getPosition().add(direction.mul(deltaTime*2.6129f));
												}
												else
													cam.getPosition().add(direction.mul(deltaTime*2.2258f));
												cam.rotate(90,0);
											}
											if((touchPos.y<100)&&(touchPos.x>208&&touchPos.x<272))
											{
												if(player.swim==0)
													player.crouch=1;
											}
											if((touchPos.y>100)&&(touchPos.x>100&&touchPos.x<380))
											{
												if(player.zoomen==1)
												{
													player.zoomen=0;
													if(player.zoom==0)
														player.zoom=1;
													else
														player.zoom=0;
												}
											}
										
									}
								}
							}
						}
					}
				}
				else
				{
					if(game.getInput().isTouchDown(i))
					{
						if(touchPos.x<128&&touchPos.y<128)
						{
							movflag=1;
							movindx=i;
							lastmovx=(int)touchPos.x;
							lastmovy=(int)touchPos.y;
							movbutx=(int)touchPos.x;
							movbuty=(int)touchPos.y;
							if(touchPos.y<85&&touchPos.y>43&&touchPos.x<85&&touchPos.x>43)
							{
							}
							else
							{
								if(touchPos.y<84)
								{
									if((objhit==1||player.jump==1||player.swim==1))
									{
										Vector3 direction=cam.getDirection();
										if(player.swim==0)
										{
											if(player.crouch==0)
												cam.getPosition().sub(direction.mul(deltaTime*9.4516f));
											else
												cam.getPosition().sub(direction.mul(deltaTime*2.6129f));
										}
										else
											cam.getPosition().sub(direction.mul(deltaTime*5.8387f));
									}
									
								}
								if(touchPos.y>44)
								{
									if((objhit==1||player.jump==1||player.swim==1))
									{
										Vector3 direction=cam.getDirection();
										if(player.swim==0)
										{
											if(player.crouch==0)
												cam.getPosition().add(direction.mul(deltaTime*9.4516f));
											else
												cam.getPosition().add(direction.mul(deltaTime*2.6129f));
										}
										else
											cam.getPosition().add(direction.mul(deltaTime*5.8387f));
									}
								}
								if(touchPos.x<43)
								{
										if(player.zoom==0)
											cam.rotate(settings.invert*((43-touchPos.x)/8.0f),0);
										else
										{
											if(player.styp==1)
												cam.rotate(settings.invert*((43-touchPos.x)/8.0f)/6,0);
											else
												cam.rotate(settings.invert*((43-touchPos.x)/8.0f),0);
										}
								}
								if(touchPos.x>85)
								{
									if(player.zoom==0)
										cam.rotate(settings.invert*((touchPos.x-85)/-8.0f),0);
									else
									{
										if(player.styp==1)
											cam.rotate(settings.invert*((touchPos.x-85)/-8.0f)/6,0);
										else
											cam.rotate(settings.invert*((touchPos.x-85)/-8.0f),0);
									}
								}
							}
						}
						else
						{
							if(movindx==i)
							{
								lastmovx=(int)touchPos.x;
								lastmovy=(int)touchPos.y;
								if(lastmovy<85&&lastmovy>43&&lastmovx<85&&lastmovx>43)
								{
								}
								else
								{
									if(lastmovy<84)
									{
										if((objhit==1||player.jump==1||player.swim==1))
										{
											Vector3 direction=cam.getDirection();
											if(player.swim==0)
											{
												if(player.crouch==0)
													cam.getPosition().sub(direction.mul(deltaTime*9.4516f));
												else
													cam.getPosition().sub(direction.mul(deltaTime*2.6129f));
											}
											else
												cam.getPosition().sub(direction.mul(deltaTime*5.8387f));
										}
										
									}
									if(lastmovy>44)
									{
										if((objhit==1||player.jump==1||player.swim==1))
										{
											Vector3 direction=cam.getDirection();
											if(player.swim==0)
											{
												if(player.crouch==0)
													cam.getPosition().add(direction.mul(deltaTime*9.4516f));
												else
													cam.getPosition().add(direction.mul(deltaTime*2.6129f));
											}
											else
												cam.getPosition().add(direction.mul(deltaTime*5.8387f));
										}
									}
									if(lastmovx<43)
									{
											if(player.zoom==0)
												cam.rotate(settings.invert*((23)/8.0f),0);
											else
											{
												if(player.styp==1)
													cam.rotate(settings.invert*((23)/8.0f)/6,0);
												else
													cam.rotate(settings.invert*((23)/8.0f),0);
											}
									}
									if(lastmovx>85)
									{
										if(player.zoom==0)
											cam.rotate(settings.invert*((25)/-8.0f),0);
										else
										{
											if(player.styp==1)
												cam.rotate(settings.invert*((25)/-8.0f)/6,0);
											else
												cam.rotate(settings.invert*((25)/-8.0f),0);
										}
									}
								}
							}
							else
							{
							if(movflag==0)
							{
								movbutx=64;
								movbuty=64;
							}
							if(touchPos.x>416&&touchPos.y<64)
							{
								pFlag=1;
								if((player.styp==2||pEnable==1)&&player.shooten==1)
								{
									pEnable=0;
									player.shoot=1;
									/*if(player.styp==2)
										assets.playsound(assets.sound2);
									else
										assets.playsound(assets.sound1);*/
								}
							}
							else
							{
								if(touchPos.x>416&&touchPos.y>256)
								{
									if(player.exchen==1&&player.shooten==1)
									{
										player.exchen=0;
										player.styp++;
										if(player.styp>2)
											player.styp=0;
									}
								}
								else
								{
									if(touchPos.x<64&&touchPos.y>256)
									{
										if(player.shooten==1&&player.gavail>0)
										{
											player.gren=1;
											player.shoot=1;
										}
									}
									else
									{
										if(touchPos.y>60&&(touchPos.x<100||touchPos.x>380))
										{
											if(touchPos.x<100)
											{
												cam.rotate(90,0);
												Vector3 direction=cam.getDirection();
												if(player.swim==0)
												{
													if(player.crouch==0)
														cam.getPosition().add(direction.mul(deltaTime*7.8387f));
													else
														cam.getPosition().add(direction.mul(deltaTime*2.6129f));
												}
												else
													cam.getPosition().add(direction.mul(deltaTime*4.2258f));
												cam.rotate(-90,0);
											}
											if(touchPos.x>380)
											{
												cam.rotate(-90,0);
												Vector3 direction=cam.getDirection();
												if(player.swim==0)
												{
													if(player.crouch==0)
														cam.getPosition().add(direction.mul(deltaTime*7.8387f));
													else
														cam.getPosition().add(direction.mul(deltaTime*2.6129f));
												}
												else
													cam.getPosition().add(direction.mul(deltaTime*2.2258f));
												cam.rotate(90,0);
											}
										}
										else
										{
											if((touchPos.y<100)&&(touchPos.x>168&&touchPos.x<232))
											{
												if(player.swim==0)
													player.crouch=1;
												else
												{
													swimflag=1;
													player.swimup=1;
												}
											}
											if((touchPos.y<100)&&(touchPos.x>278&&touchPos.x<342))
											{
												if(player.crouch==1)
												{
													player.jblk=1;
													player.crouch=0;
												}
												else
												{
													if(player.swim==0&&player.lswim==0)
													{
														if(player.jumpen==1&&objhit==1)
														{
															player.jump=1;
														}
													}
													else
													{
														swimflag=1;
														player.swimup=1;
													}
												}
											}
											if((touchPos.y>100)&&(touchPos.x>100&&touchPos.x<380))
											{
												if(player.zoomen==1)
												{
													player.zoomen=0;
													if(player.zoom==0)
														player.zoom=1;
													else
														player.zoom=0;
												}
											}
										}
									}
								}
							}
							}
						}
					}
					else
					{
						if(movindx==i)
						{
							movindx=-1;
						}
						if(movflag==0)
						{
							movbutx=64;
							movbuty=64;
						}
					}
				}
			}
			if(swimflag==0)
				player.swimup=0;
		
			if(player.shooten==1&&pFlag==0)
				pEnable=1;
		}
		else
		{

			game.getInput().getTouchEvents();
			if(player.life<=0)
			cam.rotate(-90);
		}

		if(settings.back==1)
		{
			settings.back=0;
			if(pause==0)
				pause=2;
			else
			{
				if(pause==2)
				{
					if(scr==0)
						pause=0;
					if(scr==1)
						scr=0;
					if(scr==5||scr==6)
						scr=1;
					if(scr==3||scr==4||scr==7)
						scr=5;
				}
				else
				{
					if(pause==1)
					{
						game.getInput().getTouchEvents();
						//assets.music.play();
						settings.exit=1;
						game.setScreen(new mainScreen(game));
					}
				}
			}
		}
	}

	
	
	@Override
	public void present(float deltaTime) 
	{
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(0, 0, 0f, 1);
		//cam2d.setViewportAndMatrices();
		starctr++;
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		
		/*int starpos=0;
		if(starctr<20)
			starpos=0;
		else
			if(starctr<40)
				starpos=1;
			else
				starpos=2;
		if(starctr>60)
			starctr=0;
		/*batcher.beginBatch(assets.starsT[starpos]);
		batcher.drawSprite(240, 160, 480, 320, assets.stars[starpos]);
		batcher.endBatch();*/
		int disp=(int)player.dirc;
		if(disp>360)
			disp-=360;
		if(disp<0)
			disp+=360;
		int seg=(int)player.dirc;
		if(seg<0||seg>=360)
			seg=0;
		if(settings.level==2)
		{
			//System.out.println(seg);
		batcher.beginBatch(assets.backT2);
		batcher.drawSprite(240, 160, 480, 320, assets.back2[seg]);
		batcher.endBatch();
		}
		
		if(player.crouch==1)
		{
			cam.rotate(0,-90);
			Vector3 direction=cam.getDirection();
			cam.getPosition().add(direction.mul(0.5f));
			cam.rotate(0,90);
		}
		
		if(player.zoom==0)
			cam.setMatrices(gl);
		else
		{
			cam.setMatrices(gl,player.zoomlev, glGraphics.getWidth()/(float)glGraphics.getHeight());
		}

		if(player.crouch==1)
		{
			cam.rotate(0,90);
			Vector3 direction=cam.getDirection();
			cam.getPosition().add(direction.mul(0.5f));
			cam.rotate(0,-90);
		}
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_BLEND);
		//gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL10.GL_LIGHTING);
		
		if(player.bctr>=1&&player.bctr<=3&&player.gren==0)
		{
			point6.setPosition(player.bulx, player.buly, player.bulz);
		}
		else
			point6.setPosition(player.bulx-100, player.buly-100, player.bulz-100);
		int botgflag=0;
		if(player.bctr>50&&player.gren==1)
		{
			//correct
			//if(settings.sound==1)
				//assets.playsound(assets.soundg);
			point7.setPosition(player.bulx, player.buly, player.bulz);
			botgflag=1;
		}
		else
			for(int i=0;i<btcount;i++)
			{
				if(bot[i].bctr>50&&bot[i].gren==1)
				{

					//correct
					//if(settings.sound==1)
						//assets.playsound(assets.soundg);
					point7.setPosition(bot[i].bulx, bot[i].buly, bot[i].bulz);
					botgflag=1;
				}
			}
		if(botgflag==0)
			point7.setPosition(player.bulx-100, player.buly-100, player.bulz-100);


		ambient.enable(gl);

		if(lightState[0]==1)
			point1.enable(gl, GL10.GL_LIGHT0);
		if(lightState[1]==1)
			point2.enable(gl, GL10.GL_LIGHT1);
		if(lightState[2]==1)
			point3.enable(gl, GL10.GL_LIGHT2);
		if(lightState[3]==1)
			point4.enable(gl, GL10.GL_LIGHT3);
		if(lightState[4]==1)
			point5.enable(gl, GL10.GL_LIGHT4);
		point6.enable(gl, GL10.GL_LIGHT5);
		point7.enable(gl, GL10.GL_LIGHT6);
		
		objhit=player.update(obj,ocount, bar, bcount,bot,btcount, cam, deltaTime, gl,pause);

		if(pause==0)
		{
			for(int i=0;i<btcount;i++)
				bot[i].update(player,obj, ocount, bar, bcount ,bot,btcount, deltaTime, gl);
		}
		
		//fire here !!
		assets.manTexr[0].bind();
		//myFire.updatefire(gl);
		
		for(int i=0;i<bcount;i++)
		{
			bar[i].render(gl,player);
		}
		for(int i=0;i<ocount;i++)
		{
			if(obj[i].typ==-1)
			{
				gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE);
				obj[i].render(gl);
				gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ZERO);
			}
			else
				obj[i].render(gl);
		}
		
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);


		cam2d.setViewportAndMatrices();
		gl.glDisable(GL10.GL_LIGHTING);
		if(pause==0)
		{
			if(player.styp==1&&player.zoom==1&&player.life>0)
			{
				if(settings.controls==0)
				{
					batcher.beginBatch(assets.zmove0T);
					batcher.drawSprite(32, 32, 64, 64, assets.zmove0);
					batcher.endBatch();
				}
				else
				{
					batcher.beginBatch(assets.zmovebutT);
					batcher.drawSprite(movbutx, movbuty, 44, 45, assets.zmovebut);
					batcher.endBatch();
				}
				batcher.beginBatch(assets.zshootT);
				batcher.drawSprite(448, 32, 64, 64, assets.zshoot);
				batcher.endBatch();
				batcher.beginBatch(assets.maskT);
				batcher.drawSprite(240, 160, 480, 320, assets.mask);
				batcher.endBatch();
			}
			else
			{
				if(player.life<=0)
				{
					if(player.respctr>=40&&player.respctr<60)
					{
						batcher.beginBatch(assets.resp3T);
						batcher.drawSprite(240, 160, 480, 320, assets.resp3);
						batcher.endBatch();
					}
					if(player.respctr>=60&&player.respctr<80)
					{
						batcher.beginBatch(assets.resp2T);
						batcher.drawSprite(240, 160, 480, 320, assets.resp2);
						batcher.endBatch();		
					}
					if(player.respctr>=80)
					{
						batcher.beginBatch(assets.resp1T);
						batcher.drawSprite(240, 160, 480, 320, assets.resp1);
						batcher.endBatch();
					}
					batcher.beginBatch(assets.bloodT);
					if(player.respctr<=40)
						batcher.drawSprite(240,(480-player.respctr*8) , 480, 320, assets.blood);
					else
						batcher.drawSprite(240,160 , 480, 320, assets.blood);
					batcher.endBatch();
				}
				else
				{
					if(player.atkl==1)
					{
						batcher.beginBatch(assets.atklT);
						batcher.drawSprite(120, 160, 50, 179, assets.atkl);
						batcher.endBatch();
					}
					if(player.atkr==1)
					{
						batcher.beginBatch(assets.atkrT);
						batcher.drawSprite(360, 160, 50, 179, assets.atkr);
						batcher.endBatch();
					}
					
					batcher.beginBatch(assets.exchT);
					batcher.drawSprite(448, 288, 64, 64, assets.exch);
					batcher.endBatch();
					batcher.beginBatch(assets.grenT);
					batcher.drawSprite(32, 288, 64, 64, assets.gren);
					batcher.endBatch();
					if(settings.controls==1)
					{
						batcher.beginBatch(assets.move0T);
						batcher.drawSprite(310, 32, 64, 64, assets.move0);
						batcher.endBatch();
					
						batcher.beginBatch(assets.crouchT);
						batcher.drawSprite(200, 32, 64, 64, assets.crouch);
						batcher.endBatch();
					}
					else
					{

						batcher.beginBatch(assets.crouchT);
						batcher.drawSprite(240, 32, 64, 64, assets.crouch);
						batcher.endBatch();
					}
					if(settings.controls==0)
					{
						batcher.beginBatch(assets.move0T);
						batcher.drawSprite(32, 32, 64, 64, assets.move0);
						batcher.endBatch();
					}
					else
					{

						batcher.beginBatch(assets.movebutT);
						batcher.drawSprite(movbutx, movbuty, 44, 45, assets.movebut);
						batcher.endBatch();
						batcher.beginBatch(assets.move1T);
						batcher.drawSprite(64, 64, 128, 128, assets.move1);
						batcher.endBatch();
					}
					batcher.beginBatch(assets.aimT);
					batcher.drawSprite(240, 150, 15, 15, assets.aim);
					batcher.endBatch();
					batcher.beginBatch(assets.shootT);
					batcher.drawSprite(448, 32, 64, 64, assets.shoot);
					batcher.endBatch();
					int st=250-player.life;
					for(int i=0;i<player.life/10;i++)
					{
						if(player.life>30)
						{
							batcher.beginBatch(assets.lifeT);
							batcher.drawSprite(st+i*20, 300, 10, 15, assets.life);
							batcher.endBatch();
						}
						else
						{
							batcher.beginBatch(assets.life2T);
							batcher.drawSprite(st+i*20, 300, 10, 15, assets.life2);
							batcher.endBatch();
						}
					}
				}
			}
		}
		if(pause==2)
		{
			if(scr==0)
			{
				//int[] kill=new int[]{player.kills,bot[0].kills,bot[1].kills,bot[2].kills};
				int[] kill=new int[btcount+1];
				kill[0]=player.kills;
				for(int i=1;i<=btcount;i++)
				{
					kill[i]=bot[i-1].kills;
				}
				//int[] tag=new int[]{player.tag,bot[0].tag,bot[1].tag,bot[2].tag};
				int[] tag=new int[btcount+1];
				tag[0]=player.tag;
				for(int i=1;i<=btcount;i++)
				{
					tag[i]=bot[i-1].tag;
				}
				for(int i=0;i<=btcount;i++)
				{
					for(int j=0;j<btcount;j++)
					{
						if(kill[j]>kill[j+1])
						{
							int tempk=kill[j];
							kill[j]=kill[j+1];
							kill[j+1]=tempk;
							
							int tempt=tag[j];
							tag[j]=tag[j+1];
							tag[j+1]=tempt;
						}
					}
				}
				for(int i=0;i<=btcount;i++)
				{
					for(int j=0;j<btcount;j++)
					{
						if(bot[j].tag==tag[i])
						{
							bot[j].pos=i;
						}
					}
					if(player.tag==tag[i])
					{
						player.pos=i;
					}
				}
				int st=60,stp=30;
				for(int i=0;i<btcount;i++)
				{
					batcher.beginBatch(assets.nosT);
					batcher.drawSprite(350, st+bot[i].pos*stp, 32, 30, assets.nos[bot[i].kills]);
					batcher.endBatch();
				}
				batcher.beginBatch(assets.nosT);
				batcher.drawSprite(350, st+player.pos*stp, 32, 30, assets.nos[player.kills]);
				batcher.endBatch();

				for(int i=0;i<btcount;i++)
				{
					batcher.beginBatch(assets.nameT[bot[i].tag]);
					batcher.drawSprite(200, st+bot[i].pos*stp, 155, 30, assets.name[bot[i].tag]);
					batcher.endBatch();
				}
				batcher.beginBatch(assets.nameT[btcount]);
				batcher.drawSprite(200, st+player.pos*stp, 155, 30, assets.name[btcount]);
				batcher.endBatch();
				
				batcher.beginBatch(assets.pauseST);
				batcher.drawSprite(240, 160, 480, 320, assets.pauseS);
				batcher.endBatch();
			}
			if(scr==1)
			{
				/*if(settings.sound==1)
				{
					batcher.beginBatch(assets.checkT);
					batcher.drawSprite(397, 138, 15, 15, assets.check);
					batcher.endBatch();
				}*/
				if(settings.invert==-1)
				{
					batcher.beginBatch(assets.checkT);
					batcher.drawSprite(397, 185, 15, 15, assets.check);
					batcher.endBatch();
				}
				batcher.beginBatch(assets.optionsT);
				batcher.drawSprite(240, 160, 480, 320, assets.options);
				batcher.endBatch();
			}
			if(scr==3)
			{
				batcher.beginBatch(assets.control1T);
				batcher.drawSprite(240, 160, 480, 320, assets.control1);
				batcher.endBatch();
			}
			if(scr==4)
			{
				batcher.beginBatch(assets.control2T);
				batcher.drawSprite(240, 160, 480, 320, assets.control2);
				batcher.endBatch();
			}
			if(scr==7)
			{
				batcher.beginBatch(assets.controlpT);
				batcher.drawSprite(240, 160, 480, 320, assets.controlp);
				batcher.endBatch();
			}
			if(scr==5)
			{
				if(settings.controls==1)
				{
					batcher.beginBatch(assets.checkT);
					batcher.drawSprite(397, 138, 15, 15, assets.check);
					batcher.endBatch();
				}
				if(settings.controls==0)
				{
					batcher.beginBatch(assets.checkT);
					batcher.drawSprite(397, 185, 15, 15, assets.check);
					batcher.endBatch();
				}
				batcher.beginBatch(assets.controlsT);
				batcher.drawSprite(240, 160, 480, 320, assets.controls);
				batcher.endBatch();
			}
			if(scr==6)
			{

				if(settings.difficulty==0)
				{
					batcher.beginBatch(assets.checkT);
					batcher.drawSprite(326, 72, 15, 15, assets.check);
					batcher.endBatch();
				}
				if(settings.difficulty==1)
				{
					batcher.beginBatch(assets.checkT);
					batcher.drawSprite(326, 126, 15, 15, assets.check);
					batcher.endBatch();
				}
				if(settings.difficulty==2)
				{
					batcher.beginBatch(assets.checkT);
					batcher.drawSprite(326, 182, 15, 15, assets.check);
					batcher.endBatch();
				}
				batcher.beginBatch(assets.diffscrT);
				batcher.drawSprite(240, 160, 480, 320, assets.diffscr);
				batcher.endBatch();
			}
		}
		if(pause==1)
		{
			int[] kill=new int[btcount+1];
			kill[0]=player.kills;
			for(int i=1;i<=btcount;i++)
			{
				kill[i]=bot[i-1].kills;
			}
			//int[] tag=new int[]{player.tag,bot[0].tag,bot[1].tag,bot[2].tag};
			int[] tag=new int[btcount+1];
			tag[0]=player.tag;
			for(int i=1;i<=btcount;i++)
			{
				tag[i]=bot[i-1].tag;
			}
			for(int i=0;i<=btcount;i++)
			{
				for(int j=0;j<btcount;j++)
				{
					if(kill[j]>kill[j+1])
					{
						int tempk=kill[j];
						kill[j]=kill[j+1];
						kill[j+1]=tempk;
						
						int tempt=tag[j];
						tag[j]=tag[j+1];
						tag[j+1]=tempt;
					}
				}
			}
			for(int i=0;i<=btcount;i++)
			{
				for(int j=0;j<btcount;j++)
				{
					if(bot[j].tag==tag[i])
					{
						bot[j].pos=i;
					}
				}
				if(player.tag==tag[i])
				{
					player.pos=i;
				}
			}
			int st=30,stp=34;
			for(int i=0;i<btcount;i++)
			{
				batcher.beginBatch(assets.nosT);
				batcher.drawSprite(350, st+bot[i].pos*stp, 32, 30, assets.nos[bot[i].kills]);
				batcher.endBatch();
			}
			batcher.beginBatch(assets.nosT);
			batcher.drawSprite(350, st+player.pos*stp, 32, 30, assets.nos[player.kills]);
			batcher.endBatch();

			for(int i=0;i<btcount;i++)
			{
				batcher.beginBatch(assets.nameT[bot[i].tag]);
				batcher.drawSprite(200, st+bot[i].pos*stp, 155, 30, assets.name[bot[i].tag]);
				batcher.endBatch();
			}
			batcher.beginBatch(assets.nameT[btcount]);
			batcher.drawSprite(200, st+player.pos*stp, 155, 30, assets.name[btcount]);
			batcher.endBatch();
			
			batcher.beginBatch(assets.overT);
			batcher.drawSprite(240, 160, 480, 320, assets.over);
			batcher.endBatch();
		}
		gl.glDisable(GL10.GL_DEPTH_TEST);
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		//fps.logFrame();
	}

	@Override
	public void pause() {
	
		
	}

	@Override
	public void resume() {
		assets.reload();
	}

	@Override
	public void dispose() {
		//assets.music.pause();
		
	}
	
}