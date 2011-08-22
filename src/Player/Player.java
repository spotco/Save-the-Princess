package Player;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import other.Other;
import save_the_princess.STPGame;

public class Player {
	private final int XCOMP = 0;
	private final int YCOMP = 10;
	
	public Animation standdown,standright,standleft,standup;
	public Animation walkdown,walkright,walkleft,walkup;
	
	public int lastdirection; //1DOWN, 2RIGHT,3LEFT,4UP
	
	public int x,y;
	
	public Animation img;
	public Rectangle hitbox;
	
	public Image haskeydisplay;
	public boolean haskey;

	public Player(int x, int y) {
		this.x = x+6;
		this.y = y-2;
		imageinit();
		img = standdown;
		inithitbox();
		lastdirection = 1;
	}
	
	public void inithitbox() {
		hitbox = new Rectangle(x+XCOMP,y+YCOMP,15,15);
	}
	
	public void update(STPGame game, GameContainer frame, ArrayList<Rectangle> staticslist, ArrayList<Other> enemylist) {
		//System.out.println(hitbox.getWidth()+","+hitbox.getHeight());
		if (staticcollision(staticslist)) { //caught on doors
			try {
				game.display.animations.startAnimation("crushedAnimation",new Image("art//menu//gatekiller.png"));
			} catch (SlickException e) {
			}
		}
		boolean iswalk = false;
		if (frame.getInput().isKeyDown(Input.KEY_RIGHT)) {
			x++; hitbox.setX(x+XCOMP);
			img = walkright; iswalk = true; lastdirection = 2;
			if (staticcollision(staticslist)) {
				x--; hitbox.setX(x+XCOMP);
			}
		} 
		if (frame.getInput().isKeyDown(Input.KEY_LEFT)) {
			x--; hitbox.setX(x+XCOMP);
			img = walkleft; iswalk = true; lastdirection = 3;
			if (staticcollision(staticslist)) {
				x++; hitbox.setX(x+XCOMP);
			}
		}
		if (frame.getInput().isKeyDown(Input.KEY_UP)) {
			y--; hitbox.setY(y+YCOMP);
			img = walkup; iswalk = true; lastdirection = 4;
			if (staticcollision(staticslist)) {
				y++; hitbox.setY(y+YCOMP);
			}
		}
		if (frame.getInput().isKeyDown(Input.KEY_DOWN)) {
			y++; hitbox.setY(y+YCOMP);
			img = walkdown; iswalk = true; lastdirection = 1;
			if (staticcollision(staticslist)) {
				y--; hitbox.setY(y+YCOMP);
			}
		}
		if (iswalk) {
			game.seeme = false;
		}
		if (!iswalk) {
			if (lastdirection == 1) img = standdown;
			if (lastdirection == 2) img = standright;
			if (lastdirection == 3) img = standleft;
			if (lastdirection == 4) img = standup;
		}
	}
	
	private boolean staticcollision(ArrayList<Rectangle> staticslist) {
		for(Rectangle check:staticslist) {
			if (check.intersects(hitbox)) return true;
		}
		return false;
	}
	
	public void render() {
		if (haskey) {
			haskeydisplay.draw(x+2,y-21);
		}
		img.draw(x,y);
	}
	
	public void imageinit() {
		try {
			int[] nullduration = {10000};
			Image[] standDownSto = {new Image("art//guy//standdown.png")};
			standdown = new Animation(standDownSto,nullduration,true);
			Image[] standRightSto = {new Image("art//guy//standright.png")};
			standright = new Animation(standRightSto,nullduration,true);
			Image[] standLeftSto = {new Image("art//guy//standleft.png")};
			standleft = new Animation(standLeftSto,nullduration,true);
			Image[] standUpSto = {new Image("art//guy//standup.png")};
			standup = new Animation(standUpSto,nullduration,true);
			
			haskeydisplay = new Image("art//misc//havekey.png");
			
			Image[] stoWLeft = {new Image("art//guy//walkleft.png"),new Image("art//guy//standleft.png"),new Image("art//guy//walkleft2.png"),new Image("art//guy//standleft.png")};
			int[] duration = {250,250,250,250};
			walkleft = new Animation(stoWLeft,duration,true);
			Image[] stoWRight = {new Image("art//guy//walkright.png"),new Image("art//guy//standright.png"),new Image("art//guy//walkright2.png"),new Image("art//guy//standright.png")};
			walkright = new Animation(stoWRight,duration,true);
			Image[] stoWUp = {new Image("art//guy//walkup.png"),new Image("art//guy//standup.png"),new Image("art//guy//walkup2.png"),new Image("art//guy//standup.png")};
			walkup = new Animation(stoWUp,duration,true);
			Image[] stoWDown = {new Image("art//guy//walkdown.png"),new Image("art//guy//standdown.png"),new Image("art//guy//walkdown2.png"),new Image("art//guy//standdown.png")};
			walkdown = new Animation(stoWDown,duration,true);
		} catch (SlickException e) {
			System.out.println("player image(s) missing!");
		}
	}
	
}
