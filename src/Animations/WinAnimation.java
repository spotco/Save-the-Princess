package Animations;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import save_the_princess.AnimationManager;

public class WinAnimation extends BasicAnimation {
	
	public WinAnimation(AnimationManager manager) {
		manager.display.game.timer.cancel();
		//manager.display.game.timercounter.gettime(null);
		manager.display.game.timercounter.writetime(manager.display.save.getCurrentLevel(),manager.display.game.timercounter.abs);
		
		manager.display.sound.current.pause();
		this.manager = manager;
		manager.display.inGame = false;
		manager.animationTimer = 250;
		manager.inAnimation = true;
		isuck = 0;
		isuck2 = 0;
		manager.display.sound.win1.play();
	}
	
	private int isuck;
	private int isuck2;
	public void render(Graphics g) {
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
		
		if (manager.animationTimer > 200) {
			isuck = (250-manager.animationTimer)*3;
			manager.display.menu.introleft.draw(isuck,240);
		} else if (manager.animationTimer > 100) {
			manager.display.menu.introleft.draw(isuck,240);
		} else {
			manager.display.menu.introleft.draw(isuck+(100-manager.animationTimer)*6,240);
		}
		
		if (manager.animationTimer < 200 && manager.animationTimer > 150) {
			manager.display.menu.guardleft.draw(625-(200-manager.animationTimer)*2,30);
			manager.display.menu.guardleft.draw(625-(200-manager.animationTimer)*2,400);
			isuck2 = 625-(200-manager.animationTimer)*2;
		} else {
			manager.display.menu.guardright.draw(isuck2+(150-manager.animationTimer)*2,30);
			manager.display.menu.guardright.draw(isuck2+(150-manager.animationTimer)*2,400);
			
		}
		if (manager.animationTimer > 150) {
			manager.display.menu.introright.draw(450,200);
		} else {
			manager.display.menu.introright.draw(450+(150-manager.animationTimer)*2,200);
		}
		if (manager.animationTimer < 195 && manager.animationTimer > 150) {
			manager.display.menu.notice.draw(430,125);
		}
	}
	
	public void update(GameContainer frame) {
		//System.out.println(winanimationtimer);
		manager.animationTimer--;
		if (manager.display.sound.win1.playing() && manager.animationTimer == 0) {
			manager.animationTimer = 1;
		}

		if (frame.getInput().isKeyDown(Input.KEY_SPACE)) {
			manager.animationTimer = 0;

		}
		
		if(manager.animationTimer < 0) {
			manager.display.sound.win1.stop();
			manager.inAnimation = false;
			manager.display.save.nextLevel();
			if(!manager.display.save.getCurrentLevel().equals("End")) {
				manager.display.save.writeSaveCurrent(manager.display.save.getCurrentLevel());
			}
			manager.display.menu.loadfiles(manager.display.save.getCurrentLevel());
		}
	}
}
