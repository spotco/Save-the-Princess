package Animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.AnimationManager;

public class FinalTowerLedgeActiveAnimation extends BasicAnimation {
	
	private Image background;
	private Image foreground;
	private Animation princess,walkleft,walkright,standleft,standright,currentanim;
	public Rectangle princessbox,playerbox;
	public int playerx,playery;
	
	public FinalTowerLedgeActiveAnimation(AnimationManager manager, Image alt) {
		manager.display.game.timer.cancel();
		//manager.display.game.timercounter.gettime(null);
		manager.display.game.timercounter.writetime(manager.display.save.getCurrentLevel(),manager.display.game.timercounter.abs);
		this.manager = manager;
		manager.display.sound.current.pause();
		manager.display.sound.play("wind");
		manager.inAnimation = true;
		imageinit();
		makehitbox();
	}
	
	public void render(Graphics g) {
		background.draw();
		princess.draw(348,318);
		currentanim.draw(playerx, playery);
		foreground.draw();
		String sec = manager.display.game.timercounter.sec + "";
		String tenms = manager.display.game.timercounter.tenms + "";
		if (manager.display.game.timercounter.sec < 10) {
			sec = "0"+sec;
		}
		if (manager.display.game.timercounter.tenms < 10) {
			tenms = "0"+tenms;
		}
		g.drawString("YOU: "+manager.display.game.timercounter.min+":"+sec+":"+tenms,250,77);
		g.drawString("BEST:"+manager.display.game.timercounter.gettime(manager.display.save.getCurrentLevel()),250,90);
		//g.draw(playerbox);
		//g.draw(princessbox);
	}
	private boolean lastdir; //false left, true right
	public void update(GameContainer frame) {
		if (frame.getInput().isKeyDown(Input.KEY_RIGHT)) {
			playerx++; currentanim = walkright; lastdir = true;
		} else if (frame.getInput().isKeyDown(Input.KEY_LEFT)) {
			playerx--; currentanim = walkleft; lastdir = false;
		} else {
			if (lastdir) {
				currentanim = standright;
			} else {
				currentanim = standleft;
			}
		}
		makehitbox();
		if (playerx > 543) {
			playerx = 543;
		}
		if (playerbox.intersects(princessbox)) {
			manager.startAnimation("creditscroll",null);
		}
		//System.out.println(playerx+","+playery);
	}
	
	private void makehitbox() {
		playerbox = new Rectangle(playerx,playery,currentanim.getWidth(),currentanim.getHeight());
	}
	
	private void imageinit() {
		try {
			background = new Image("art//final//firstview.png");
			foreground = new Image("art//final//firstviewrail.png");
			Image[] bar = {new Image("art//princess//princess1.png"),new Image("art//princess//princess2.png")};
			int[] duration2 = {800,800};
			princess = new Animation(bar,duration2,true);
			princessbox = new Rectangle(348,318,bar[0].getWidth(),bar[0].getHeight());
			
			Image[] stoWLeft = {new Image("art//guy//walkleft.png"),new Image("art//guy//standleft.png"),new Image("art//guy//walkleft2.png"),new Image("art//guy//standleft.png")};
			int[] duration = {250,250,250,250};
			walkleft = new Animation(stoWLeft,duration,true);
			Image[] stoWRight = {new Image("art//guy//walkright.png"),new Image("art//guy//standright.png"),new Image("art//guy//walkright2.png"),new Image("art//guy//standright.png")};
			walkright = new Animation(stoWRight,duration,true);
			
			int[] nullduration = {10000};
			Image[] standRightSto = {new Image("art//guy//standright.png")};
			standright = new Animation(standRightSto,nullduration,true);
			Image[] standLeftSto = {new Image("art//guy//standleft.png")};
			standleft = new Animation(standLeftSto,nullduration,true);
			
			
			playerx = 543;
			playery = 327;
			currentanim = standleft;
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
