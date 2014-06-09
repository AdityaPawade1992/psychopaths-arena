package com.PsychopathsArena;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.PsychopathsHelp.GLGame;
import com.PsychopathsHelp.Screen;


public class PsychopathsArenaActivity extends GLGame 
{
	boolean firstTime=true;
	int a;
	public Screen getStartScreen() 
	{
		return new loadScreen(this);
	}

	@Override
	public void onBackPressed() {
		settings.back=1;
		if(settings.mainscr==1)
		{

			settings set=new settings(this);
			set.open();
			int[] sets=new int[]{settings.controls,settings.sound,settings.invert,settings.difficulty};
			set.setconfig(sets);
			set.close();
			super.onBackPressed();
		}
	}
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{
		super.onSurfaceCreated(gl, config);
		
		settings set=new settings(this);
		set.open();
		int[] sets=new int[4];
		sets=set.getset();
		set.close();

		settings.controls=sets[0];
		settings.sound=sets[1];
		settings.invert=sets[2];
		settings.difficulty=sets[3];
		
		if(!firstTime) 
		{
			assets.reload();
		}
	}
	
}