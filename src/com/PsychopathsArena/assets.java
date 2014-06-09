package com.PsychopathsArena;

import com.PsychopathsHelp.GLGame;
import com.PsychopathsHelp.ObjLoader;
import com.PsychopathsHelp.Sound;
import com.PsychopathsHelp.Texture;
import com.PsychopathsHelp.TextureRegion;
import com.PsychopathsHelp.Vertices3;

public class assets
{
	public static int loaded=0;
	
	public static Texture water1;
	public static Texture water2;
	public static Texture manTexr[]=new Texture[1];
	public static Texture manTexg[]=new Texture[1];
	public static Texture manTexo[]=new Texture[1];
	public static Texture manTexb[]=new Texture[1];
	public static Texture grndTex;
	public static Texture m4;
	public static Texture pistol;
	public static Texture exp;
	public static Texture gre;
	public static Texture wallT;
	public static Texture spaceT;
	public static Texture crateT;
	public static Texture bulletT;

	public static TextureRegion options;
	public static TextureRegion control1;
	public static TextureRegion control2;
	public static TextureRegion credits;
	public static Texture optionsT;
	public static TextureRegion controls;
	public static Texture controlsT;
	public static TextureRegion diffscr;
	public static Texture diffscrT;
	public static TextureRegion controlp;
	public static Texture controlpT;
	//public static TextureRegion[] stars=new TextureRegion[3];
	//public static Texture[] starsT=new Texture[3];
	public static Texture control1T;
	public static Texture control2T;
	public static Texture creditsT;

	public static Texture move0T;
	public static TextureRegion move0;
	public static Texture zmove0T;
	public static TextureRegion zmove0;
	public static Texture move1T;
	public static TextureRegion move1;
	public static Texture movebutT;
	public static TextureRegion movebut;
	public static Texture zmovebutT;
	public static TextureRegion zmovebut;
	public static Texture zmove1T;
	public static TextureRegion zmove1;
	public static Texture zshootT;
	public static Texture shootT;
	public static TextureRegion shoot;
	public static Texture checkT;
	public static TextureRegion check;
	public static TextureRegion zshoot;
	public static Texture exchT;
	public static TextureRegion exch;
	public static Texture grenT;
	public static TextureRegion gren;
	public static Texture playT;
	public static TextureRegion play;
	public static Texture crouchT;
	public static TextureRegion crouch;
	public static Texture levelT;
	public static TextureRegion level;
	public static Texture maskT;
	public static TextureRegion mask;
	public static Texture lifeT;
	public static TextureRegion life;
	public static Texture life2T;
	public static TextureRegion life2;
	public static Texture aimT;
	public static TextureRegion aim;
	public static Texture resp1T;
	public static TextureRegion resp1;
	public static Texture resp2T;
	public static TextureRegion resp2;
	public static Texture resp3T;
	public static TextureRegion blood;
	public static Texture bloodT;
	public static TextureRegion pause;
	public static Texture pauseT;
	public static TextureRegion pauseS;
	public static Texture pauseST;
	public static TextureRegion over;
	public static Texture overT;
	//public static TextureRegion[] back=new TextureRegion[360];
	//public static Texture backT;
	public static TextureRegion[] back2=new TextureRegion[360];
	public static Texture backT2;
	public static TextureRegion atkl;
	public static Texture atklT;
	public static TextureRegion atkr;
	public static Texture atkrT;

	public static TextureRegion[] nos=new TextureRegion[25];
	public static Texture nosT;
	
	public static TextureRegion[] name=new TextureRegion[7];
	public static Texture[] nameT=new Texture[7];
	
	/*public static TextureRegion[][] enemy=new TextureRegion[3][3];
	public static Texture[] enemyT=new Texture[3];
	*/
	public static TextureRegion resp3;
	
	
	//public static Music music;
	
	
	//public static Sound sound1;
	//public static Sound sound2;
	
	
	//public static Sound soundg;
	

	public static Vertices3 grnd;
	public static Vertices3 grndlow;
	public static Vertices3 man;
	public static Vertices3 manjump;
	public static Vertices3 manmov1;
	public static Vertices3 manmov2;
	public static Vertices3 mandead;
	public static Vertices3 manc;
	public static Vertices3 player;
	public static Vertices3 player1;
	public static Vertices3 player2;
	public static Vertices3 wallu1;
	public static Vertices3 wallu2;
	public static Vertices3 wall1;
	public static Vertices3 wall2;
	public static Vertices3 cwall1;
	public static Vertices3 cwall2;
	public static Vertices3 bullet;
	public static Vertices3 bullet2;
	public static Vertices3 grenade;
	public static Vertices3 gexp;
	public static Vertices3 bgun;
	public static Vertices3 bpist;
	public static Vertices3 bm4;
	public static Vertices3 lamp;
	public static Vertices3 crate;
	public static Vertices3 hlth;
	//public static Vertices3 stair;
	
	public static void load1(GLGame glGame)
	{
		//music=glGame.getAudio().newMusic("back.wav");
		
		
		/*sound1=glGame.getAudio().newSound("pist.wav");
		sound2=glGame.getAudio().newSound("m4.wav");
		soundg=glGame.getAudio().newSound("gren.wav");*/
		
		
		//soundg=glGame.getAudio().newSound("gren.wav");

		manTexr[0]=new Texture(glGame, "hum0r.png");
		manTexg[0]=new Texture(glGame, "hum0g.png");
		manTexo[0]=new Texture(glGame, "hum0o.png");
		manTexb[0]=new Texture(glGame, "hum0b.png");
		/*manTexr[1]=new Texture(glGame, "hum1r.png");
		manTexg[1]=new Texture(glGame, "hum1g.png");
		manTexo[1]=new Texture(glGame, "hum1o.png");
		manTexb[1]=new Texture(glGame, "hum1b.png");
		manTexr[2]=new Texture(glGame, "hum2r.png");
		manTexg[2]=new Texture(glGame, "hum2g.png");
		manTexo[2]=new Texture(glGame, "hum2o.png");
		manTexb[2]=new Texture(glGame, "hum2b.png");
		manTexr[3]=new Texture(glGame, "hum3r.png");
		manTexg[3]=new Texture(glGame, "hum3g.png");
		manTexo[3]=new Texture(glGame, "hum3o.png");
		manTexb[3]=new Texture(glGame, "hum3b.png");
		manTexr[4]=new Texture(glGame, "hum4r.png");
		manTexg[4]=new Texture(glGame, "hum4g.png");
		manTexo[4]=new Texture(glGame, "hum4o.png");
		manTexb[4]=new Texture(glGame, "hum4b.png");
		manTexr[5]=new Texture(glGame, "hum5r.png");
		manTexg[5]=new Texture(glGame, "hum5g.png");
		manTexo[5]=new Texture(glGame, "hum5o.png");
		manTexb[5]=new Texture(glGame, "hum5b.png");*/
		
		grndTex=new Texture(glGame, "bricks.png",true);
		water1=new Texture(glGame, "water1.png");
		water2=new Texture(glGame, "water2.png");
		m4=new Texture(glGame, "m4.png");
		pistol=new Texture(glGame, "pistol.png");
		exp=new Texture(glGame, "exp.png");
		gre=new Texture(glGame, "gre.png");
		wallT=new Texture(glGame, "wallt1.png");
		spaceT=new Texture(glGame, "wall.png");
		crateT=new Texture(glGame, "crate.png",true);
		bulletT=new Texture(glGame, "bullet.png");

		optionsT=new Texture(glGame, "options.png");
		options=new TextureRegion(optionsT, 0, 0, 512, 256);
		controlsT=new Texture(glGame, "controls.png");
		controls=new TextureRegion(controlsT, 0, 0, 512, 256);
		diffscrT=new Texture(glGame, "difficulty.png");
		diffscr=new TextureRegion(diffscrT, 0, 0, 512, 256);
		/*starsT[0]=new Texture(glGame, "stars.png");
		stars[0]=new TextureRegion(starsT[0], 0, 0, 512, 256);
		starsT[1]=new Texture(glGame, "stars1.png");
		stars[1]=new TextureRegion(starsT[1], 0, 0, 512, 256);
		starsT[2]=new Texture(glGame, "stars2.png");
		stars[2]=new TextureRegion(starsT[2], 0, 0, 512, 256);*/
		control1T=new Texture(glGame, "control1.png");
		control1=new TextureRegion(control1T, 0, 0, 512, 256);
		control2T=new Texture(glGame, "control2.png");
		control2=new TextureRegion(control2T, 0, 0, 512, 256);
		controlpT=new Texture(glGame, "control1p.png");
		controlp=new TextureRegion(controlpT, 0, 0, 512, 256);
		creditsT=new Texture(glGame, "credits.png");
		credits=new TextureRegion(creditsT, 0, 0, 512, 256);

		checkT=new Texture(glGame, "check.png");
		check=new TextureRegion(checkT, 0, 0, 16, 16);
		atklT=new Texture(glGame, "atkl.png");
		atkl=new TextureRegion(atklT, 0, 0, 32, 128);
		atkrT=new Texture(glGame, "atkr.png");
		atkr=new TextureRegion(atkrT, 0, 0, 32, 128);
		lifeT=new Texture(glGame, "life.png");
		life=new TextureRegion(lifeT, 0, 0, 8, 16);
		life2T=new Texture(glGame, "life2.png");
		life2=new TextureRegion(life2T, 0, 0, 8, 16);
		move0T=new Texture(glGame, "move.png");
		move0=new TextureRegion(move0T, 0, 0, 64, 64);
		move1T=new Texture(glGame, "move2_2.png");
		move1=new TextureRegion(move1T, 0, 0, 128, 128);
		movebutT=new Texture(glGame, "movbut.png");
		movebut=new TextureRegion(movebutT, 0, 0, 64, 64);
		zmovebutT=new Texture(glGame, "zmovbut.png");
		zmovebut=new TextureRegion(zmovebutT, 0, 0, 64, 64);
		crouchT=new Texture(glGame, "crouch.png");
		crouch=new TextureRegion(crouchT, 0, 0, 64, 64);
		shootT=new Texture(glGame, "shoot.png");
		shoot=new TextureRegion(shootT, 0, 0, 64, 64);
		zmove0T=new Texture(glGame,"zmove.png");
		zmove0=new TextureRegion(zmove0T, 0, 0, 64, 64);
		zmove1T=new Texture(glGame,"zmove2.png");
		zmove1=new TextureRegion(zmove1T, 0, 0, 128, 128);
		zshootT=new Texture(glGame,"zshoot.png");
		zshoot=new TextureRegion(zshootT, 0, 0, 64, 64);
		exchT=new Texture(glGame, "exch.png");
		exch=new TextureRegion(exchT, 0, 0, 64, 64);
		grenT=new Texture(glGame, "grei.png");
		gren=new TextureRegion(grenT, 0, 0, 64, 64);
		pauseT=new Texture(glGame, "pause.png");
		pause=new TextureRegion(pauseT, 0, 0, 64, 64);
		aimT=new Texture(glGame, "aim.png");
		aim=new TextureRegion(aimT, 0, 0, 16, 16);
		playT=new Texture(glGame, "play.png");
		play=new TextureRegion(playT, 0, 0, 512, 256);
		levelT=new Texture(glGame, "levels.png");
		level=new TextureRegion(levelT, 0, 0, 512, 256);
		maskT=new Texture(glGame,"mask.png");
		mask=new TextureRegion(maskT, 0, 0, 512, 256);
		resp1T=new Texture(glGame,"resp1.png");
		resp1=new TextureRegion(resp1T, 0, 0, 512, 256);
		resp2T=new Texture(glGame,"resp2.png");
		resp2=new TextureRegion(resp2T, 0, 0, 512, 256);
		resp3T=new Texture(glGame,"resp3.png");
		resp3=new TextureRegion(resp3T, 0, 0, 512, 256);
		bloodT=new Texture(glGame,"blood.png");
		blood=new TextureRegion(bloodT, 0, 0, 512, 256);
		pauseST=new Texture(glGame,"pauseS2.png");
		pauseS=new TextureRegion(pauseST, 0, 0, 512, 256);
		overT=new Texture(glGame,"over2.png");
		over=new TextureRegion(overT, 0, 0, 512, 256);
		
		float st=2048;
		backT2=new Texture(glGame,"back2.png");
		for(int i=0;i<360;i++)
		{
			back2[i]=new TextureRegion(backT2, st, 0, 512, 256);
			st-=5.68;
		}
		/*st=1920;
		backT=new Texture(glGame,"back.png");
		for(int i=0;i<360;i++)
		{
			back[i]=new TextureRegion(backT, st, 0, 512, 256);
			st-=5.333;
		}*/
		nameT[6]=new Texture(glGame,"name1.png");
		nameT[0]=new Texture(glGame,"name2.png");
		nameT[1]=new Texture(glGame,"name3.png");
		nameT[2]=new Texture(glGame,"name4.png");
		nameT[3]=new Texture(glGame,"name5.png");
		nameT[4]=new Texture(glGame,"name6.png");
		nameT[5]=new Texture(glGame,"name7.png");

		name[0]=new TextureRegion(nameT[0], 0, 0, 128, 32);
		name[1]=new TextureRegion(nameT[1], 0, 0, 128, 32);
		name[2]=new TextureRegion(nameT[2], 0, 0, 128, 32);
		name[3]=new TextureRegion(nameT[3], 0, 0, 128, 32);
		name[4]=new TextureRegion(nameT[4], 0, 0, 128, 32);
		name[5]=new TextureRegion(nameT[5], 0, 0, 128, 32);
		name[6]=new TextureRegion(nameT[6], 0, 0, 128, 32);
		
		/*enemyT[3]=new Texture(glGame,"enemy1.png");
		enemyT[0]=new Texture(glGame,"enemy2.png");
		enemyT[1]=new Texture(glGame,"enemy3.png");
		enemy[0][0]=new TextureRegion(enemyT[0], 0, 0, 133, 30);
		enemy[0][1]=new TextureRegion(enemyT[0], 158, 0, 293, 30);
		enemy[0][2]=new TextureRegion(enemyT[0], 315, 0, 450, 30);
		enemy[1][0]=new TextureRegion(enemyT[1], 0, 0, 93, 30);
		enemy[1][1]=new TextureRegion(enemyT[1], 119, 0, 214, 30);
		enemy[1][2]=new TextureRegion(enemyT[1], 238, 0, 331, 30);
		enemy[2][0]=new TextureRegion(enemyT[2], 0, 0, 150, 30);
		enemy[2][1]=new TextureRegion(enemyT[2], 150, 0, 300, 30);
		enemy[2][2]=new TextureRegion(enemyT[2], 300, 0, 450, 30);
		*/
		nosT=new Texture(glGame,"nos.png");
		for(int i=0;i<25;i++)
			nos[i]=new TextureRegion(nosT, i*41, 0, 41, 32);

		System.out.println("texs loaded !!");

		wallu1=ObjLoader.load(glGame, "wall1new.adi");
		wallu2=ObjLoader.load(glGame, "wall2new.adi");
		wall1=ObjLoader.load(glGame, "wall1.adi");
		wall2=ObjLoader.load(glGame, "wall2.adi");
		cwall1=ObjLoader.load(glGame, "cavewall1.adi");
		cwall2=ObjLoader.load(glGame, "cavewall2.adi");
		
		lamp=ObjLoader.load(glGame, "lamp.adi");
		crate=ObjLoader.load(glGame, "crate.adi");
		hlth=ObjLoader.load(glGame, "hlth.adi");

		System.out.println("build loaded !!");
	}

	public static void load2(GLGame glGame)
	{

		grnd=ObjLoader.load(glGame, "grndlow.adi");
		grndlow=ObjLoader.load(glGame, "grndlow.adi");
		bullet=ObjLoader.load(glGame, "bullet1.adi");
		bullet2=ObjLoader.load(glGame, "bullet2.adi");

		System.out.println("weapons loaded !!");
	}

	public static void load3(GLGame glGame)
	{
		player=ObjLoader.load(glGame, "player2.adi");
		player2=ObjLoader.load(glGame, "player3.adi");
		player1=ObjLoader.load(glGame, "player4.adi");

		System.out.println("players loaded !!");
	}

	public static void load4(GLGame glGame)
	{

		grenade=ObjLoader.load(glGame, "grenade.adi");
		gexp=ObjLoader.load(glGame, "gexp.adi");
		bgun=ObjLoader.load(glGame, "bgun.adi");
		bpist=ObjLoader.load(glGame, "bpist.adi");
		bm4=ObjLoader.load(glGame, "bm4.adi");
		man=ObjLoader.load(glGame, "male.adi");
	}

	public static void load5(GLGame glGame)
	{
		manmov1=ObjLoader.load(glGame, "malemov1.adi");
		manmov2=ObjLoader.load(glGame, "malemov2.adi");
		manjump=ObjLoader.load(glGame, "malejump.adi");
	}

	public static void load6(GLGame glGame)
	{
		mandead=ObjLoader.load(glGame, "maledead.adi");
		manc=ObjLoader.load(glGame, "botc.adi");

		System.out.println("bots loaded !!  START  woo hoo !!");
	}
	
	static public void reload()
	{
		 water1.reload();
		 water2.reload();
		 manTexr[0].reload();
		 manTexg[0].reload();
		 manTexo[0].reload();
		 manTexb[0].reload();
		 /*manTexr[1].reload();
		 manTexg[1].reload();
		 manTexo[1].reload();
		 manTexb[1].reload();
		 manTexr[2].reload();
		 manTexg[2].reload();
		 manTexo[2].reload();
		 manTexb[2].reload();
		 manTexr[3].reload();
		 manTexg[3].reload();
		 manTexo[3].reload();
		 manTexb[3].reload();
		 manTexr[4].reload();
		 manTexg[4].reload();
		 manTexo[4].reload();
		 manTexb[4].reload();
		 manTexr[5].reload();
		 manTexg[5].reload();
		 manTexo[5].reload();
		 manTexb[5].reload();*/
		 grndTex.reload();
		 move0T.reload();
		 move1T.reload();
		 m4.reload();
		 pistol.reload();
		 exp.reload();
		 gre.reload();
		 wallT.reload();
		 spaceT.reload();
		 crateT.reload();
		 bulletT.reload();

		 optionsT.reload();
		 control1T.reload();
		 control2T.reload();
		 controlsT.reload();
		 controlpT.reload();
		 creditsT.reload();
		
		 shootT.reload();
		 checkT.reload();
		 zmove0T.reload();
		 zmove1T.reload();
		 movebutT.reload();
		 zmovebutT.reload();
		 zshootT.reload();
		 exchT.reload();
		 grenT.reload();
		 playT.reload();
		 maskT.reload();
		 lifeT.reload();
		 life2T.reload();
		 aimT.reload();
		 resp1T.reload();
		 resp2T.reload();
		 resp3T.reload();
		 bloodT.reload();
		 pauseT.reload();
		 pauseST.reload();
		 overT.reload();
		 atklT.reload();
		 atkrT.reload();
		 crouchT.reload();
		 backT2.reload();
		 levelT.reload();
		 diffscrT.reload();
		 nosT.reload();
		 for(int i=0;i<7;i++)
			nameT[i].reload();
	}
	
	public static void playsound(Sound sound)
	{
		if(settings.sound==1)
		{
			sound.play(1);
		}
	}
}