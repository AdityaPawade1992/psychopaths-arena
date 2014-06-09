package com.PsychopathsHelp;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

public class AndroidAudio implements Audio
{
	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio(Activity activity)
	{
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets=activity.getAssets();
		this.soundPool=new SoundPool(20,AudioManager.STREAM_MUSIC,0);
	}
	
	
	public Music newMusic(String filename) 
	{
		try
		{
			AssetFileDescriptor assetDescriptor=assets.openFd(filename);
			return new AndroidMusic(assetDescriptor);
		} 
		catch (IOException e) 
		{
			throw new RuntimeException("Couldn't load music '" + filename + "'"+e);
		}
	}

	
	public Sound newSound(String filename) {
		try
		{
			AssetFileDescriptor assetDescriptor=assets.openFd(filename);
			int soundID=soundPool.load(assetDescriptor, 0);
			return new AndroidSound(soundPool,soundID);
		}
		catch (IOException e)
		{
			throw new RuntimeException("Couldn't load sound '" + filename + "'");
		}
	}
}