package Animations;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import save_the_princess.AnimationManager;

public class CrushedAnimation extends BasicAnimation {
	
	public Image killerimage;
	public CrushedAnimation(AnimationManager manager, Image alt) {
		this.manager = manager; killerimage = alt;
		manager.display.sound.current.pause();
		manager.display.sound.die1.play();
		manager.display.inGame = false;
		manager.animationTimer = 100;
		manager.inAnimation = true;
	}
	
	private int sto;
	
	public void render(Graphics g) {
		if (manager.animationTimer > 75) {
			manager.display.menu.introleft.draw(150, 250);
		} else {
			manager.display.menu.guydead.draw(130,340);
		}
		
		if (manager.animationTimer > 75) {
			sto = 120 + 6 * (100 - manager.animationTimer);
			killerimage.draw(130,sto);
			
		} else {
			killerimage.draw(130,sto);
		}
		
		if (manager.animationTimer > 35 && manager.animationTimer < 87) {
			manager.display.menu.ouch.draw(95,195);
		}

	}
	
	public void update(GameContainer frame) {
		manager.animationTimer--;
		if(manager.display.sound.die1.playing() && manager.animationTimer == 0) {
			manager.animationTimer = 1;
		}
		//System.out.println("RESPAWN:"+deathtimer);
		if (frame.getInput().isKeyDown(Input.KEY_SPACE)) {
			manager.display.sound.die1.stop();
			manager.animationTimer = 5;
		}
		if (manager.animationTimer == 0) {
			manager.inAnimation = false;
			manager.display.inGame = true;
			manager.display.game.loadlevel();
			manager.display.sound.play();
		}
	}

}
