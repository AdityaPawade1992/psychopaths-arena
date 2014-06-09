package com.PsychopathsArena;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.PsychopathsHelp.Camera2D;
import com.PsychopathsHelp.GLScreen;
import com.PsychopathsHelp.Game;
import com.PsychopathsHelp.SpriteBatcher;
import com.PsychopathsHelp.Vector2;
import com.PsychopathsHelp.Input.TouchEvent;

public class mainScreen extends GLScreen
{
	SpriteBatcher batcher;
	Camera2D cam2d;
	int scr=0;
	Vector2 touchPos=new Vector2();
	public mainScreen(Game game) {
		super(game);
		game.getInput().getTouchEvents();
		batcher=new SpriteBatcher(glGraphics,1);
		cam2d=new Camera2D(glGraphics, 480, 320);
		

		//correct
		/*if(settings.sound==1)
		{
			assets.music.play();
			assets.music.setLooping(true);
			assets.music.setVolume(1);
		}*/
	}
	
	@Override
	public void update(float deltaTime) 
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
			if(event.type==TouchEvent.TOUCH_UP)
			{
				if(scr==0)
				{
					if(touchPos.y>170&&touchPos.y<230)
					{
						scr=5;
					}
					if(touchPos.y>100&&touchPos.y<170)
					{
						scr=1;
					}
					if(touchPos.y<100)
					{
						scr=2;
					}
					break;
				}
				if(scr==1)
				{
					if(touchPos.y<=85)
					{
						scr=8;
					}
					else
					if(touchPos.y>85&&touchPos.y<150)
					{
						scr=6;
					}
					else
					//if(touchPos.y>110&&touchPos.y<160)
					//{

						//correct
						/*if(settings.sound==0)
						{
							settings.sound=1;

							assets.music.play();
							assets.music.setLooping(true);
							assets.music.setVolume(1);
						}
						else
						{
							settings.sound=0;
							assets.music.pause();
						}*/
					//}
					//else
					if(touchPos.y>150&&touchPos.y<213)
					{
						if(settings.invert==-1)
							settings.invert=1;
						else
							settings.invert=-1;
					}
					break;
				}
				if(scr==2)
				{
					scr=0;
					break;
				}
				if(scr==3)
				{
					scr=4;
					break;
				}
				if(scr==4)
				{
					scr=6;
					break;
				}
				if(scr==7)
				{
					scr=6;
					break;
				}
				if(scr==5)
				{
					if(touchPos.y>170&&touchPos.y<235)
					{
						settings.level=1;
						game.setScreen(new GameScreen(game));
						return;
					}
					if(touchPos.y>115&&touchPos.y<170)
					{

						settings.level=2;
						game.setScreen(new GameScreen(game));
						return;
					}
					break;
				}
				if(scr==6)
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
				if(scr==8)
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

		if(settings.back==1)
		{
			settings.back=0;
			if(scr==1||scr==2||scr==5)
				scr=0;
			if(scr==6||scr==8)
				scr=1;
			if(scr==3||scr==4||scr==7)
				scr=6;
		}
	}

	@Override
	public void present(float deltaTime) 
	{
		GL10 gl = glGraphics.getGL();	
		gl.glEnable(GL10.GL_TEXTURE_2D);
		cam2d.setViewportAndMatrices();
		
		if(scr==0)
		{
			batcher.beginBatch(assets.playT);
			batcher.drawSprite(240, 160, 480, 320, assets.play);
			batcher.endBatch();
			settings.mainscr=1;
		}
		else
			settings.mainscr=0;
		if(scr==1)
		{
			batcher.beginBatch(assets.optionsT);
			batcher.drawSprite(240, 160, 480, 320, assets.options);
			batcher.endBatch();
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
		}
		if(scr==2)
		{
			batcher.beginBatch(assets.creditsT);
			batcher.drawSprite(240, 160, 480, 320, assets.credits);
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
			batcher.beginBatch(assets.levelT);
			batcher.drawSprite(240, 160, 480, 320, assets.level);
			batcher.endBatch();
		}
		if(scr==6)
		{
			batcher.beginBatch(assets.controlsT);
			batcher.drawSprite(240, 160, 480, 320, assets.controls);
			batcher.endBatch();
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
		}
		if(scr==8)
		{
			batcher.beginBatch(assets.diffscrT);
			batcher.drawSprite(240, 160, 480, 320, assets.diffscr);
			batcher.endBatch();
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
		}
		
		gl.glDisable(GL10.GL_TEXTURE_2D);
		
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		if(settings.exit==1)
		assets.reload();
		settings.exit=0;
	}

	@Override
	public void dispose() {

		//correct
		/*if(settings.sound==1)
		{
			assets.music.pause();
		}*/
	}

}