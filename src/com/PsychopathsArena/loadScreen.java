package com.PsychopathsArena;

import javax.microedition.khronos.opengles.GL10;

import com.PsychopathsHelp.Camera2D;
import com.PsychopathsHelp.GLScreen;
import com.PsychopathsHelp.Game;
import com.PsychopathsHelp.SpriteBatcher;
import com.PsychopathsHelp.Texture;
import com.PsychopathsHelp.TextureRegion;

public class loadScreen extends GLScreen
{
	Texture loading1T;
	Texture loading2T;
	Texture loading3T;
	Texture loading4T;
	Texture loading5T;
	Texture loading6T;
	SpriteBatcher batcher;
	Camera2D guiCamera;
	TextureRegion loading1;
	TextureRegion loading2;
	TextureRegion loading3;
	TextureRegion loading4;
	TextureRegion loading5;
	TextureRegion loading6;
	int cnt=0;
	
	public loadScreen(Game game) {
		super(game);

		loading1T=new Texture(glGame,"loading1p.png",false);
		loading1=new TextureRegion(loading1T, 0, 0, 512, 256);
		loading2T=new Texture(glGame,"loading2p.png",false);
		loading2=new TextureRegion(loading2T, 0, 0, 512, 256);
		loading3T=new Texture(glGame,"loading3p.png",false);
		loading3=new TextureRegion(loading3T, 0, 0, 512, 256);
		loading4T=new Texture(glGame,"loading4p.png",false);
		loading4=new TextureRegion(loading4T, 0, 0, 512, 256);
		loading5T=new Texture(glGame,"loading5p.png",false);
		loading5=new TextureRegion(loading5T, 0, 0, 512, 256);
		loading6T=new Texture(glGame,"loading6p.png",false);
		loading6=new TextureRegion(loading6T, 0, 0, 512, 256);
		batcher=new SpriteBatcher(glGraphics,1);
		guiCamera=new Camera2D(glGraphics, 480, 320);
		
	}
	
	@Override
	public void update(float deltaTime) 
	{	
		settings.mainscr=1;
	}

	@Override
	public void present(float deltaTime) 
	{
		cnt++;
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		guiCamera.setViewportAndMatrices();

		if(cnt<=3)
		{
			batcher.beginBatch(loading1T);
			batcher.drawSprite(240, 160, 480, 320, loading1);
			batcher.endBatch();
		}
		else
			if(cnt<=5)
			{
				batcher.beginBatch(loading2T);
				batcher.drawSprite(240, 160, 480, 320, loading2);
				batcher.endBatch();
			}
			else
				if(cnt<=7)
				{
					batcher.beginBatch(loading3T);
					batcher.drawSprite(240, 160, 480, 320, loading3);
					batcher.endBatch();
				}
				else
					if(cnt<=9)
					{
						batcher.beginBatch(loading4T);
						batcher.drawSprite(240, 160, 480, 320, loading4);
						batcher.endBatch();
					}
					else
						if(cnt<=11)
						{
							batcher.beginBatch(loading5T);
							batcher.drawSprite(240, 160, 480, 320, loading5);
							batcher.endBatch();
						}
						else
						{
							batcher.beginBatch(loading6T);
							batcher.drawSprite(240, 160, 480, 320, loading6);
							batcher.endBatch();
						}
		//ldthr.run();
		gl.glDisable(GL10.GL_TEXTURE_2D);
		//FileOp.load();
		if(cnt==2)
		{
			//String[] loades=new String[]{"bgun","bm4","botc","bpist","bullet1","bullet2","cavewall1","cavewall2","crate","gexp","grenade","grnd","grndlow","hlth","lamp","male","maledead","malejump","malemov1","malemov2","player1","player2","player3","player4","wall1","wall2","wall1new","wall2new"};
			//for(int i=0;i<28;i++)
			//FileOp.load(glGame,loades[i]);
			//System.out.println("done");
			assets.load1(glGame);
		}
		if(cnt==4)
		{
			assets.load2(glGame);
		}
		if(cnt==6)
		{
			assets.load3(glGame);
		}
		if(cnt==8)
		{
			assets.load4(glGame);
		}
		if(cnt==10)
		{
			assets.load5(glGame);
		}
		if(cnt==12)
		{
			assets.load6(glGame);
			//game.setScreen(new mainScreen(game));
			game.setScreen(new GameScreen(game));
		}
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}