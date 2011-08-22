package save_the_princess;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundManager {
	
	public static final boolean playsound = true;
	
	private Music menu1;
	private Music main1;
	private Music main2;
	private Music boss;
	private Music wind;
	private Music credits;
	
	public Sound win1;
	public Sound die1;
	public Sound dogbark;
	public Sound menuchange;
	public Sound hey;
	public Sound gate;
	public Sound getkey;
	public Sound unlockdoor;
	public Sound fireball;
	public Sound standandfight;
	
	public Music current;
	
	public SoundManager() {
		try {
			menu1 = new Music("snd//menu1.ogg",true);
			main1 = new Music("snd//main1.ogg",true);
			main2 = new Music("snd//main2.ogg",true);
			boss = new Music("snd//boss.ogg",true);
			wind = new Music("snd//wind.ogg",true);
			
			credits = new Music("snd//credits.ogg",true);
			
			getkey = new Sound("snd//getkey.wav");
			unlockdoor = new Sound("snd//unlockdoor.wav");
			dogbark = new Sound("snd//dogbark.wav");
			win1 = new Sound("snd//win1.wav");
			die1 = new Sound("snd//end1.wav");
			menuchange = new Sound("snd//beep.wav");
			hey = new Sound("snd//hey.wav");
			gate = new Sound("snd//gateclose.wav");
			fireball = new Sound("snd//fireball.wav");
			standandfight = new Sound("snd//standandfight.wav");
		} catch (SlickException e) {
			System.out.println("error with loading sound");
		}
	}
	
	private String sto = "menu1";
	public void play(String id) {
		sto = id;
		play();
	}
	
	public void play() {
		if(sto.equals("main2")) current = main2;
		if(sto.equals("menu1")) current = menu1;
		if(sto.equals("main1")) current = main1;
		if(sto.equals("boss")) current = boss;
		if(sto.equals("wind")) current = wind;
		if(sto.equals("credits")) current = credits;
		if (playsound) current.loop();
		if(sto.equals("credits")) {
			current.pause();
			current.play();
		}
	}
	
	public void stop() {
		current.stop();
	}
	
	
}
