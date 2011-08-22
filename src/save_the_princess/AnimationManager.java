package save_the_princess;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Animations.*;

public class AnimationManager {
	
	public boolean inAnimation;
	public STPView display;
	
	public int animationTimer;
	
	public AnimationManager(STPView display) {
		this.display = display;
	}
	
	public BasicAnimation currentAnimation;
	
	public void startAnimation(String type,Image alt) {
		inAnimation = true;
		init(type,alt);
	}
	
	public void init(String type,Object alt) {
		if(type.equals("deathAnimation")) {
			currentAnimation = new DeathAnimation(this,(Image)alt);
		} else if (type.equals("winAnimation")) {
			currentAnimation = new WinAnimation(this);
		} else if (type.equals("titleScreenAnimation")) {
			currentAnimation = new TitleScreenAnimation(this);
		} else if (type.equals("crushedAnimation")) {
			currentAnimation = new CrushedAnimation(this,(Image)alt);
		} else if (type.equals("knightBossInitAnimation")) {
			currentAnimation = new KnightBossInitAnimation(this);
		} else if (type.equals("finalTowerLedge")) {
			currentAnimation = new FinalTowerLedgeActiveAnimation(this,null);
		} else if (type.equals("creditscroll")) {
			currentAnimation = new CreditScrollAnimation(this);
		}
	}
	
	
	public void render(GameContainer frame,Graphics g) {
		currentAnimation.render(g);
	}
	
	public void update(GameContainer frame) {
		currentAnimation.update(frame);
	}

}
