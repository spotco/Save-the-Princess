package Animations;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import save_the_princess.AnimationManager;

public class DeathAnimation extends BasicAnimation {
	
	public Image killerimage;
	public DeathAnimation(AnimationManager manager, Image alt) {
		this.manager = manager; killerimage = alt;
		manager.display.sound.current.pause();
		manager.display.sound.die1.play();
		manager.display.inGame = false;
		manager.animationTimer = 100;
		manager.inAnimation = true;
	}
	
	public void render(Graphics g) {
		if (manager.animationTimer > 50) {
			manager.display.menu.introleft.draw(150, 250);
		} else {
			manager.display.menu.guydead.draw(130,340);
		}
		killerimage.draw(625-(100-manager.animationTimer)*10,290);
		if (manager.animationTimer < 50 && manager.animationTimer > 25) {
			manager.display.menu.caught.draw(95,205);
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
