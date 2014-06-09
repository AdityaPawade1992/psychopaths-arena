package com.PsychopathsArena;

import com.PsychopathsHelp.AmbientLight;
import com.PsychopathsHelp.EulerCamera;
import com.PsychopathsHelp.PointLight;

public class LevelDesign
{
	public static int[] setLev1(barrierClass[] bar,objectClass[] obj,botClass[] bot,int ocount,int bcount,int btcount,EulerCamera cam,PointLight point1,PointLight point2,PointLight point3,PointLight point4,PointLight point5,AmbientLight ambient)
	{

		settings.gravity=1;
		//ambient.setColor(0.4f, 0.4f, 1f, 0);
		ambient.setColor(1f, 1f, 1f, 0);
		
		float att=0.05f,attq=0.005f;
		point1.setPosition(0, 1, -3);
		point1.setAmbient(0f, 0f, 0f, 0);
		point1.setDiffuse(1, 0.47f, 0, 1);
		point1.setAttenuation(0, att, attq);

		point2.setPosition(46, 1, 40);
		point2.setAmbient(0f, 0f, 0f, 0);
		point2.setDiffuse(1, 0.47f, 0, 1);
		point2.setAttenuation(0, att, attq);

		point3.setPosition(-46, 1, 40);
		point3.setAmbient(0f, 0f, 0f, 0);
		point3.setDiffuse(1, 0.47f, 0, 1);
		point3.setAttenuation(0, att, attq);

		point4.setPosition(0, 1, 87);
		point4.setAmbient(0f, 0f, 0f, 0);
		point4.setDiffuse(1, 0.47f, 0, 1);
		point4.setAttenuation(0, att, attq);

		point5.setPosition(0, 1, 40);
		point5.setAmbient(0f, 0f, 0f, 0);
		point5.setDiffuse(0, 0, 1, 1);
		point5.setAttenuation(0, att, attq);
		
		int farh=-10;
		
		bar[0].setoffs(assets.wall1,assets.wallT,0,-0.4f,farh,180,0);
		bar[1].setoffs(assets.wall1,assets.wallT,bar[0].w,-0.4f,farh,180,0);
		bar[2].setoffs(assets.wall2,assets.wallT,2*bar[0].w,-0.4f,farh+bar[0].w,0,0);
		bar[3].setoffs(assets.wall2,assets.wallT,2*bar[0].w,-0.4f,farh+3*bar[0].w,0,0);
		bar[4].setoffs(assets.wall2,assets.wallT,-2*bar[0].w,-0.4f,farh+bar[0].w,180,0);
		bar[5].setoffs(assets.wall2,assets.wallT,-2*bar[0].w,-0.4f,farh+3*bar[0].w,180,0);
		bar[6].setoffs(assets.wall1,assets.wallT,-bar[0].w,-0.4f,farh+4*bar[0].w,0,0);
		bar[7].setoffs(assets.wall1,assets.wallT,bar[0].w,-0.4f,farh+4*bar[0].w,0,0);

		float wid=bar[0].w/2;
		System.out.println("wid fu : "+wid);
		bar[8].setoffs(assets.cwall1,assets.wallT,0,0,farh+2*wid-1.4f,0,0);
		bar[9].setoffs(assets.cwall1,assets.wallT,0,0,farh+6*wid+1.4f,180,0);
		bar[10].setoffs(assets.cwall2,assets.wallT,-2*wid-1.7f,0,farh+4*wid,180,0);
		bar[11].setoffs(assets.cwall2,assets.wallT,2*wid+1.7f,0,farh+4*wid,0,0);

		bar[12].setoffs(assets.wallu1,assets.wallT,0,-2*bar[0].h-0.1f,farh+2*wid-0.5f,0,1);
		bar[13].setoffs(assets.wallu1,assets.wallT,0,-2*bar[0].h-0.1f,farh+6*wid+0.5f,180,1);
		bar[14].setoffs(assets.wallu2,assets.wallT,-2*wid-0.5f,-2*bar[0].h-0.1f,farh+4*wid-0.4f,180,1);
		bar[15].setoffs(assets.wallu2,assets.wallT,2*wid+0.5f,-2*bar[0].h-0.1f,farh+4*wid+0.4f,0,1);

		bar[16].setoffs(assets.lamp,assets.wallT,4*wid-1.5f,0,farh+4*wid+0.4f,270,2);
		bar[17].setoffs(assets.lamp,assets.wallT,-4*wid+1.5f,0,farh+4*wid+0.4f,90,2);
		bar[18].setoffs(assets.lamp,assets.wallT,0,0,farh+8*wid-1.5f,180,2);
		bar[19].setoffs(assets.lamp,assets.wallT,0,0,farh+1.5f,0,2);

		bar[20].setoffs(assets.crate,assets.crateT,4*wid-6.5f,0,farh+4*wid+0.4f,0,3);
		bar[21].setoffs(assets.crate,assets.crateT,-4*wid+6.5f,0,farh+4*wid+0.4f,0,3);
		bar[22].setoffs(assets.crate,assets.crateT,0,0,farh+8*wid-6.5f,0,3);
		bar[23].setoffs(assets.crate,assets.crateT,0,0,farh+6.5f,0,3);
		bar[24].setoffs(assets.crate,assets.crateT,0,-3f,40,0,3);
		
		//bar[0].y=-0.4f;
		bar[0].x=-bar[0].w;
		float ht=2*bar[11].h;
		float wid2=bar[11].w;

		bcount=25;
		
		obj[0].setoffs( assets.grnd, assets.grndTex,-3*wid, 0, farh+wid, 0, 0, 0,0,0);
		obj[1].setoffs( assets.grnd, assets.grndTex,-3*wid, 0, farh+3*wid, 0, 0, 0,0,0);
		obj[3].setoffs( assets.grnd, assets.grndTex,-wid, 0, farh+wid, 0, 0, 0,0,0);

		obj[5].setoffs( assets.grnd, assets.grndTex,3*wid, 0, farh+wid, 0, 0, 0,0,0);
		obj[6].setoffs( assets.grnd, assets.grndTex,3*wid, 0, farh+3*wid, 0, 0, 0,0,0);
		obj[8].setoffs( assets.grnd, assets.grndTex,wid, 0, farh+wid, 0, 0, 0,0,0);

		obj[10].setoffs( assets.grnd, assets.grndTex,-3*wid, 0, farh+5*wid, 0, 0, 0,0,0);
		obj[12].setoffs( assets.grnd, assets.grndTex,-3*wid, 0, farh+7*wid, 0, 0, 0,0,0);
		obj[13].setoffs( assets.grnd, assets.grndTex,-wid, 0, farh+7*wid, 0, 0, 0,0,0);
		
		obj[15].setoffs( assets.grnd, assets.grndTex,3*wid, 0, farh+5*wid, 0, 0, 0,0,0);
		obj[17].setoffs( assets.grnd, assets.grndTex,3*wid, 0, farh+7*wid, 0, 0, 0,0,0);
		obj[18].setoffs( assets.grnd, assets.grndTex,wid, 0, farh+7*wid, 0, 0, 0,0,0);

		obj[2].setoffs( assets.grndlow, assets.wallT,-3*wid-wid2-1.3f, ht, farh+3*wid+6, 180, 0, 0,0,0);
		obj[11].setoffs( assets.grndlow, assets.wallT,-3*wid-wid2-1.3f, ht, farh+5*wid-6, 180, 0, 0,0,0);
		obj[7].setoffs( assets.grndlow, assets.wallT,3*wid+wid2+1.3f, ht, farh+3*wid+6, 180, 0, 0,0,0);
		obj[16].setoffs( assets.grndlow, assets.wallT,3*wid+wid2+1.3f, ht, farh+5*wid-6, 180, 0, 0,0,0);
		obj[4].setoffs( assets.grndlow, assets.wallT,-wid+6, ht, farh+wid-wid2-1.6f, 180, 0, 0,0,0);
		obj[9].setoffs( assets.grndlow, assets.wallT,wid-6, ht, farh+wid-wid2-1.6f, 180, 0, 0,0,0);
		obj[14].setoffs( assets.grndlow, assets.wallT,-wid+6, ht, farh+7*wid+wid2+1.6f, 180, 0, 0,0,0);
		obj[19].setoffs( assets.grndlow, assets.wallT,wid-6, ht, farh+7*wid+wid2+1.6f, 180, 0, 0,0,0);

		obj[20].setoffs( assets.grnd, assets.water1,wid, -8, farh+5*wid, 0, 0, 0,-2,0);
		obj[21].setoffs( assets.grnd, assets.water1,-wid, -8, farh+5*wid, 0, 0, 0,-2,0);
		obj[22].setoffs( assets.grnd, assets.water1,wid, -8, farh+3*wid, 0, 0, 0,-2,0);
		obj[23].setoffs( assets.grnd, assets.water1,-wid, -8, farh+3*wid, 0, 0, 0,-2,0);
		/*obj[24].setoffs( assets.grndlow, assets.wallT,-2*wid+10, -10.5f, farh+2*wid+8, 30, 0, -30,-2,0);
		obj[25].setoffs( assets.grndlow, assets.wallT,-2*wid+10, -10.5f, farh+6*wid-8, -30, 0, -30,-2,0);
		obj[26].setoffs( assets.grndlow, assets.wallT,2*wid-10f, -10.5f, farh+6*wid-8f, -30, 0, 30,-2,0);
		obj[27].setoffs( assets.grndlow, assets.wallT,2*wid-10, -10.5f, farh+2*wid+8, 30, 0, 30,-2,0);

		obj[24].setclimb();
		obj[25].setclimb();
		obj[26].setclimb();
		obj[27].setclimb();*/
		
		obj[24].setoffs( assets.grnd, assets.water1,-wid, 0, farh+3*wid, 0, 0, 0,-1,0);
		obj[25].setoffs( assets.grnd, assets.water1,wid, 0, farh+3*wid, 0, 0, 0,-1,0);
		obj[26].setoffs( assets.grnd, assets.water1,-wid, 0, farh+5*wid, 0, 0, 0,-1,0);
		obj[27].setoffs( assets.grnd, assets.water1,wid, 0, farh+5*wid, 0, 0, 0,-1,0);
		
		ocount=28;
		cam.getPosition().set(3*wid, 2, farh+7*wid);

		float[][] spoints;
		int scount;
		float[][] epoints;
		int ecount;
		float[][] wpoints;
		int wcount;
		spoints=new float[][]{{34,75},{36,40},{34,3},{0,3},{-34,3},{-36,40},{-34,75},{0,75},{0,40}};
		scount=9;
		epoints=new float[][]{{36,40},{0,3},{-36,40},{0,75}};
		ecount=4;
		wpoints=new float[][]{{34,75},{34,3},{-34,3},{-34,75}};
		wcount=4;

		btcount=6;

		bot[0].setoffs(34, 2, 3, 0,spoints,scount,epoints,ecount,wpoints,wcount,0);
		bot[1].setoffs(-34, 2, 3, 0,spoints,scount,epoints,ecount,wpoints,wcount,1);
		bot[2].setoffs(-34, 2, 75, 0,spoints,scount,epoints,ecount,wpoints,wcount,2);
		bot[3].setoffs(34, 2, 36, 0,spoints,scount,epoints,ecount,wpoints,wcount,3);
		bot[4].setoffs(-34, 2, 36, 0,spoints,scount,epoints,ecount,wpoints,wcount,4);
		bot[5].setoffs(0, 2, 75f, 0,spoints,scount,epoints,ecount,wpoints,wcount,5);
		int tar;
		if(btcount==0)
			tar=0;
		else
			tar=100/btcount;
		int init=0;
		for(int i=0;i<btcount;i++)
		{
			bot[i].settarch(init);
			init+=tar;
		}
		
		int[] ret=new int[8];
		ret[0]=bcount;
		ret[1]=ocount;
		ret[2]=btcount;
		
		ret[3]=1;
		ret[4]=1;
		ret[5]=1;
		ret[6]=1;
		ret[7]=1;
	
		return ret;
	}
	public static int[] setLev2(barrierClass[] bar,objectClass[] obj,botClass[] bot,int ocount,int bcount,int btcount,EulerCamera cam,PointLight point1,PointLight point2,PointLight point3,PointLight point4,PointLight point5,AmbientLight ambient)
	{
		settings.gravity=0;

		ambient.setColor(1f, 1f, 1f, 0);
		
		float att=0.005f,attq=0.0005f;
		point1.setPosition(0, 1, -3);
		point1.setAmbient(0f, 0f, 0f, 0);
		point1.setDiffuse(1, 1f, 1, 1);
		point1.setAttenuation(0, att, attq);

		point2.setPosition(46, 1, 40);
		point2.setAmbient(0f, 0f, 0f, 0);
		point1.setDiffuse(1, 1f, 1, 1);
		point2.setAttenuation(0, att, attq);

		point3.setPosition(-46, 1, 40);
		point3.setAmbient(0f, 0f, 0f, 0);
		point1.setDiffuse(1, 1f, 1, 1);
		point3.setAttenuation(0, att, attq);

		point4.setPosition(0, 1, 87);
		point4.setAmbient(0f, 0f, 0f, 0);
		point1.setDiffuse(1, 1f, 1, 1);
		point4.setAttenuation(0, att, attq);

		point5.setPosition(0, 12, 40);
		point5.setAmbient(0f, 0f, 0f, 0);
		point1.setDiffuse(1, 1f, 1, 1);
		point5.setAttenuation(0, att, attq);

		
		int farh=-10;
		

		float ht=6.544f;
		float wid=11.69345f;
		
		bar[0].setoffs(assets.crate,assets.crateT,7.0418f,13.3f,57,0,3);
		bar[1].setoffs(assets.crate,assets.crateT,-17.7f,13.3f,54.69f,0,3);
		bar[2].setoffs(assets.crate,assets.crateT,-19.38f,13.3f,21,0,3);
		bar[3].setoffs(assets.crate,assets.crateT,18.9f,13.3f,52.15f,0,3);
		bar[4].setoffs(assets.crate,assets.crateT,0, 13.3f,38.198f,0,3);

		bcount=5;

		obj[0].setoffs( assets.grnd, assets.spaceT,-3*wid, 0, farh+wid, 0, 0, 0,0,0);
		obj[1].setoffs( assets.grnd, assets.spaceT,-3*wid, 0, farh+3*wid, 0, 0, 0,0,0);
		obj[2].setoffs( assets.grnd, assets.spaceT,-wid, 0, farh+wid, 0, 0, 0,0,0);

		obj[3].setoffs( assets.grnd, assets.spaceT,3*wid, 0, farh+wid, 0, 0, 0,0,0);
		obj[4].setoffs( assets.grnd, assets.spaceT,3*wid, 0, farh+3*wid, 0, 0, 0,0,0);
		obj[5].setoffs( assets.grnd, assets.spaceT,wid, 0, farh+wid, 0, 0, 0,0,0);

		obj[6].setoffs( assets.grnd, assets.spaceT,-3*wid, 0, farh+5*wid, 0, 0, 0,0,0);
		obj[7].setoffs( assets.grnd, assets.spaceT,-3*wid, 0, farh+7*wid, 0, 0, 0,0,0);
		obj[8].setoffs( assets.grnd, assets.spaceT,-wid, 0, farh+7*wid, 0, 0, 0,0,0);
		
		obj[9].setoffs( assets.grnd, assets.spaceT,3*wid, 0, farh+5*wid, 0, 0, 0,0,0);
		obj[10].setoffs( assets.grnd, assets.spaceT,3*wid, 0, farh+7*wid, 0, 0, 0,0,0);
		obj[11].setoffs( assets.grnd, assets.spaceT,wid, 0, farh+7*wid, 0, 0, 0,0,0);

		obj[19].setoffs( assets.grnd, assets.spaceT,-wid, 0, farh+3*wid, 0, 0, 0,0,0);
		obj[20].setoffs( assets.grnd, assets.spaceT,wid, 0, farh+3*wid, 0, 0, 0,0,0);
		obj[21].setoffs( assets.grnd, assets.spaceT,-wid, 0, farh+5*wid, 0, 0, 0,0,0);
		obj[22].setoffs( assets.grnd, assets.spaceT,wid, 0, farh+5*wid, 0, 0, 0,0,0);

		obj[23].setoffs( assets.grnd, assets.spaceT,-3*wid, ht, farh+wid, 0, 0, 0,0,0);
		obj[24].setoffs( assets.grnd, assets.spaceT,-3*wid, ht, farh+3*wid, 0, 0, 0,0,0);
		obj[25].setoffs( assets.grnd, assets.spaceT,-wid, ht, farh+wid, 0, 0, 0,0,0);

		obj[26].setoffs( assets.grnd, assets.spaceT,3*wid, ht, farh+wid, 0, 0, 0,0,0);
		obj[27].setoffs( assets.grnd, assets.spaceT,3*wid, ht, farh+3*wid, 0, 0, 0,0,0);
		obj[28].setoffs( assets.grnd, assets.spaceT,wid, ht, farh+wid, 0, 0, 0,0,0);

		obj[29].setoffs( assets.grnd, assets.spaceT,-3*wid, ht, farh+5*wid, 0, 0, 0,0,0);
		obj[30].setoffs( assets.grnd, assets.spaceT,-3*wid, ht, farh+7*wid, 0, 0, 0,0,0);
		obj[31].setoffs( assets.grnd, assets.spaceT,-wid, ht, farh+7*wid, 0, 0, 0,0,0);
		
		obj[18].setoffs( assets.grnd, assets.spaceT,3*wid, ht, farh+5*wid, 0, 0, 0,0,0);
		obj[17].setoffs( assets.grnd, assets.spaceT,3*wid, ht, farh+7*wid, 0, 0, 0,0,0);
		obj[16].setoffs( assets.grnd, assets.spaceT,wid, ht, farh+7*wid, 0, 0, 0,0,0);

		//obj[16].setoffs( assets.stair, assets.wallT,0, 4, farh+5.7f*wid, -45, 0, 0,0,0);

		obj[15].setoffs( assets.grnd, assets.spaceT,-wid, 2*ht, farh+3*wid, 0, 0, 0,0,0);
		obj[14].setoffs( assets.grnd, assets.spaceT,wid, 2*ht, farh+3*wid, 0, 0, 0,0,0);
		obj[13].setoffs( assets.grnd, assets.spaceT,-wid, 2*ht, farh+5*wid, 0, 0, 0,0,0);
		obj[12].setoffs( assets.grnd, assets.spaceT,wid, 2*ht, farh+5*wid, 0, 0, 0,0,0);
		
		ocount=32;
		cam.getPosition().set(0, 4, 0);

		float[][] spoints;
		int scount;
		float[][] epoints;
		int ecount;
		float[][] wpoints;
		int wcount;
		spoints=new float[][]{{34,75},{36,40},{34,3},{0,3},{-34,3},{-36,40},{-34,75},{0,75},{0,40}};
		scount=9;
		epoints=new float[][]{{36,40},{0,3},{-36,40},{0,75}};
		ecount=4;
		wpoints=new float[][]{{34,75},{34,3},{-34,3},{-34,75}};
		wcount=4;

		btcount=6;
		//34,2,3
		bot[0].setoffs(34, 2, 3, 0,spoints,scount,epoints,ecount,wpoints,wcount,0);
		bot[1].setoffs(-34, 2, 3, 0,spoints,scount,epoints,ecount,wpoints,wcount,1);
		bot[2].setoffs(-34, 2, 75, 0,spoints,scount,epoints,ecount,wpoints,wcount,2);
		bot[3].setoffs(34, 12, 4, 0,spoints,scount,epoints,ecount,wpoints,wcount,3);
		bot[4].setoffs(-34, 12, 6, 0,spoints,scount,epoints,ecount,wpoints,wcount,4);
		bot[5].setoffs(0, 14, 38.198f, 0,spoints,scount,epoints,ecount,wpoints,wcount,5);
		int tar;
		if(btcount==0)
			tar=0;
		else
			tar=100/btcount;
		int init=0;
		for(int i=0;i<btcount;i++)
		{
			bot[i].settarch(init);
			init+=tar;
		}
		

		int[] ret=new int[8];
		ret[0]=bcount;
		ret[1]=ocount;
		ret[2]=btcount;
		
		ret[3]=1;
		ret[4]=1;
		ret[5]=1;
		ret[6]=1;
		ret[7]=0;
		return ret;
	}
}