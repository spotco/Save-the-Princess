package save_the_princess;

import java.io.File;
import java.util.Scanner;

import levels.Level;
import levels.Level1;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;

public class Menu {
	
	public int intro;
	public Animation introleft,introright;
	public Image introheart,guydead,caught,notice,loadercursor,ouch,seeme,goal;
	public Animation spotcologo;
	public Animation guardleft,guardright;
	
	public Image menuimg,timesmenu;
	public boolean inmenuimg;
	private Image pressspacetostart;
	
	public Image loaderimg;
	public boolean inloaderimg;
	
	public Level loadedlevel;
	
	public STPView display;
	
	public Menu(STPView display) throws SlickException {
		this.display = display;
		initimg();
		if (display.save.getSaveCurrent().equals("Level1")) {
			loaderImgMenuStatus = 0;
		} else {
			loaderImgMenuStatus = 1;
		}
		timestatinfo = new TimerCounter(null);
	}
	
	private TimerCounter timestatinfo;
	
	public void startintro() {
		display.animations.startAnimation("titleScreenAnimation", null);
		inmenuimg = true;
	}
	
	private int counter;
	private boolean displaypress;
	public void render(GameContainer frame,Graphics g) {
		if (inmenuimg) {
			menuimg.draw(0, 0, 625, 625);
			counter++;
			if (counter > 45) {
				counter = 0;
				displaypress = !displaypress;
			}
			if (displaypress) {
				pressspacetostart.draw(240, 360);
			}
		} else if (inloaderimg) {
			loaderimg.draw(0,0,625,625);
			if(loaderImgMenuStatus == 0) {
				loadercursor.draw(280,147);
			} else if (loaderImgMenuStatus == 1) {
				loadercursor.draw(280,230);
			} else if (loaderImgMenuStatus == 2) {
				loadercursor.draw(280,309);
			} else if (loaderImgMenuStatus == 3) {
				timesmenu.draw(0,0);
				int recordcount = 0;
				recordcount += recordcheck("Level1",g);
				g.drawString(timestatinfo.gettime("Level1"), 300, 135);
				recordcount += recordcheck("Level2",g);
				g.drawString(timestatinfo.gettime("Level2"), 300, 165);
				recordcount += recordcheck("Level3",g);
				g.drawString(timestatinfo.gettime("Level3"), 300, 194);
				recordcount += recordcheck("Level4",g);
				g.drawString(timestatinfo.gettime("Level4"), 300, 220);
				recordcount += recordcheck("Level5",g);
				g.drawString(timestatinfo.gettime("Level5"), 300, 250);
				recordcount += recordcheck("Level6",g);
				g.drawString(timestatinfo.gettime("Level6"), 300, 279);
				if (recordcount >= 6) {
					g.setColor(Color.yellow);
					g.drawString("Grats on beating my times.\nSee you in the sequel!\n-spotco", 190, 305);
				}
			}
		}
	}
	
	private int recordcheck(String level,Graphics g) {
		if (timestatinfo.gettimeraw(level) >= timestatinfo.getRecTime(level)) {
			g.setColor(Color.yellow);
			return 0;
		} else {
			g.setColor(Color.green);
			return 1;
		}
	}
	
	private int loaderImgMenuStatus;
	
	public void update(GameContainer frame){
		if (inmenuimg) {
			if (frame.getInput().isKeyPressed(Input.KEY_SPACE)) {
				inmenuimg = false;
				inloaderimg = true;
				display.sound.menuchange.play();
			}
		} else if (inloaderimg) {
			Input sto = frame.getInput();
			if (sto.isKeyPressed(Input.KEY_DOWN)) {
				display.sound.menuchange.play();
				if (loaderImgMenuStatus == 0) {
					loaderImgMenuStatus = 1;
				} else if (loaderImgMenuStatus == 1) {
					loaderImgMenuStatus = 2;
				}
			} else if (sto.isKeyPressed(Input.KEY_UP)) {
				display.sound.menuchange.play();
				if (loaderImgMenuStatus == 1) {
					loaderImgMenuStatus = 0;
				} else if (loaderImgMenuStatus == 2) {
					loaderImgMenuStatus = 1;
				}
			} else if (sto.isKeyPressed(Input.KEY_ESCAPE)) {
				display.sound.menuchange.play();
				if (loaderImgMenuStatus == 0 || loaderImgMenuStatus == 1 || loaderImgMenuStatus == 2) {
					inloaderimg = false;
					inmenuimg = true;
				} else if (loaderImgMenuStatus == 3) {
					loaderImgMenuStatus = 2;
				}
			} else if (sto.isKeyPressed(Input.KEY_SPACE)) {
				display.sound.menuchange.play();
				if (loaderImgMenuStatus == 2) {
					loaderImgMenuStatus = 3;
				} else if (loaderImgMenuStatus == 0 || loaderImgMenuStatus == 1) {
					if (loaderImgMenuStatus == 0) display.save.newGame();
					if (loaderImgMenuStatus == 1) display.save.loadGame();
					loadfiles(display.save.getCurrentLevel());
				}
			}
		}
	}
	
	public void loadfiles(String loadname) {
		//System.out.println(loadname);
		if(loadname.equals("End")) {
			//TODO--PUT ENDING + CREDITS HERE
			System.out.println("the end");
			reset();
		} else {
			display.sound.stop();
			Level loadedlevel = null;
			try {
				loadedlevel = (Level) STPView.class.getClassLoader().loadClass("levels."+loadname).newInstance();
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			} catch (ClassNotFoundException e) {
				System.out.println("classnotfound");
			}
			this.loadedlevel = loadedlevel;
			display.inMenu = false;
			display.inGame = true;
			display.game.loadedlevel = this.loadedlevel;
			display.game.loadlevel();
		}
	}

	
	public void initimg() throws SlickException {
		Image[] sto = {new Image("art//menu//guy1.png"),new Image("art//menu//guy2.png")};
		int[] duration = {500,500};
		introleft = new Animation(sto,duration,true);
		Image[] sto2 = {new Image("art//menu//princess1.png"),new Image("art//menu//princess2.png")};
		introright = new Animation(sto2,duration,true);
		menuimg = new Image("art//menu//menunew.png");
		pressspacetostart = new Image("art//menu//space2start.png");
		loaderimg = new Image("art//menu//loader.png");
		introheart = new Image("art//menu//heart.png");
		Image[] stoGL = {new Image("art//menu//guardleft1.png"),new Image("art//menu//guardleft2.png")};
		Image[] stoGR = {new Image("art//menu//guardright1.png"),new Image("art//menu//guardright2.png")};
		guardleft = new Animation(stoGL,duration,true);
		guardright = new Animation(stoGR,duration,true);
		guydead = new Image("art//menu//guydead.png");
		timesmenu = new Image("art//menu//timesmenu.png");
		caught = new Image("art//menu//CAUGHT.png");
		ouch = new Image("art//menu//OUCH.png");
		notice = new Image("art//menu//notice.png");
		loadercursor = new Image("art//menu//loadercursor.png");
		Image[] foo = {new Image("art//menu//spotcologo//1.png"),new Image("art//menu//spotcologo//2.png"),new Image("art//menu//spotcologo//3.png"),
					   new Image("art//menu//spotcologo//4.png"),new Image("art//menu//spotcologo//5.png"),new Image("art//menu//spotcologo//6.png"),
					   new Image("art//menu//spotcologo//7.png")};
		int[] bar = {100,100,100,100,100,100,100};
		spotcologo = new Animation(foo,bar,true);
		seeme = new Image("art//misc//seeme.png");
		goal = new Image("art//misc//goal.png");
	}
	
	public void reset() {
		inmenuimg = true;
		inloaderimg = false;
		display.inGame = false;
		loadedlevel = null;
		display.inMenu = true;
		display.sound.stop();
		display.sound.play("menu1");
	}
	
}
