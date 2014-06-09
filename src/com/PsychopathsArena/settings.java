package com.PsychopathsArena;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class settings
{
	public static int sound=1;
	public static int invert=1;
	public static int back=0;
	public static int mainscr=0;
	public static int exit=0;
	public static int level=1;
	public static int controls=1;
	public static int gravity=0;
	public static int difficulty=0;

	public static final String KEY_SOUND="sound";
	public static final String KEY_ID="r_id";
	public static final String KEY_INVERT="invert";
	public static final String KEY_CONTROL="control";
	public static final String KEY_DIFFICULTY="difficulty";

	public static final String DATABASE_NAME="game_database";
	public static final String DATABASE_TABLE="settings";
	public static final int DATABASE_VERSION=1;
	
	public Context c;
	public dbhelper sethelp;
	public SQLiteDatabase setdb;
	
	public settings(Context c)
	{
		this.c=c;
	}
	
	public class dbhelper extends SQLiteOpenHelper
	{

		public dbhelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " ("
					+ KEY_ID + " INTEGER PRIMARY KEY, "
					+ KEY_CONTROL + " INTEGER NOT NULL, "
					+ KEY_INVERT + " INTEGER NOT NULL, "
					+ KEY_DIFFICULTY + " INTEGER NOT NULL, "
					+ KEY_SOUND + " INTEGER NOT NULL);"
					);

			
			ContentValues cv=new ContentValues();
			cv.put(KEY_CONTROL, 1);
			cv.put(KEY_ID, 1);
			cv.put(KEY_INVERT, 1);
			cv.put(KEY_SOUND, 1);
			cv.put(KEY_DIFFICULTY, 1);
			db.insert(DATABASE_TABLE, null, cv);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP IF TABLE EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	public settings open()
	{
		sethelp=new dbhelper(c);
		setdb=sethelp.getWritableDatabase();
		return this;
		
	}
	public void close()
	{
		sethelp.close();
	}
	
	public int[] getset()
	{
		int[] set=new int[4];
		String[] columns=new String[]{KEY_ID,KEY_CONTROL,KEY_SOUND,KEY_INVERT,KEY_DIFFICULTY};
		Cursor c=setdb.query(DATABASE_TABLE, columns, KEY_ID+"="+1, null, null, null, null);
		c.moveToFirst();
		int icon=c.getColumnIndex(KEY_CONTROL);
		int iinv=c.getColumnIndex(KEY_INVERT);
		int ison=c.getColumnIndex(KEY_SOUND);
		int idif=c.getColumnIndex(KEY_DIFFICULTY);
		set[0]=c.getInt(icon);
		set[1]=c.getInt(ison);
		set[2]=c.getInt(iinv);
		set[3]=c.getInt(idif);
		c.close();
		return set;
	}
	
	public void setconfig(int[] sets)
	{
		ContentValues cv=new ContentValues();
		cv.put(KEY_CONTROL, sets[0]);
		cv.put(KEY_INVERT, sets[2]);
		cv.put(KEY_SOUND, sets[1]);
		cv.put(KEY_DIFFICULTY, sets[3]);
		setdb.update(DATABASE_TABLE, cv, KEY_ID+"="+1, null);
		
	}
}