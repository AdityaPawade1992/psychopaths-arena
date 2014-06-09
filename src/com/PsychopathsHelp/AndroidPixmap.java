package com.PsychopathsHelp;

import android.graphics.Bitmap;

public class AndroidPixmap implements Pixmap
{
	Bitmap bitmap;
	PixmapFormat format;
	
	public AndroidPixmap(Bitmap bitmap,PixmapFormat format)
	{
		this.bitmap=bitmap;
		this.format=format;
	}
	
	
	public int getWidth() {
		return bitmap.getWidth();
	}

	
	public int getHeight() {
		return bitmap.getHeight();
	}

	
	public PixmapFormat getFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	
	public void dispose() {
		bitmap.recycle();
	}
	
}