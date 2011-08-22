package levels;

import java.util.ArrayList;


import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import enemy.Enemy;



public class Level1 extends Level {
	
	public static final String thislevelsong = "main1";
	public String name() {
		return "why are there so many dogs";
	}
	
	public void init() {
		mapsong = thislevelsong;
		storedmap = new TiledMap[1][1];
		try {
			storedmap[0][0] = new TiledMap("data//level1.tmx","art");
		} catch (SlickException e) {
			System.out.println("level1 could not load data");
		}
		locationx = 0; locationy = 0;
	}
	
	public ArrayList<Rectangle> createstatics() {
		return super.createstatics();
	}
	
	public ArrayList<Enemy> createenemys() {
		return super.createenemys();
	}
}
