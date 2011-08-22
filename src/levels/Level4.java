package levels;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import save_the_princess.ListContainer;

public class Level4 extends Level {
	public static final String thislevelsong = "main1";
	public String name() {
		return "maximum security";
	}
	
	public void init() {
		mapsong = thislevelsong;
		storedmap = new TiledMap[2][2];
		try {
			storedmap[0][0] = new TiledMap("data//level4(0-0).tmx","art");
			storedmap[1][0] = new TiledMap("data//level4(1-0).tmx","art");
			storedmap[0][1] = new TiledMap("data//level4(0-1).tmx","art");
			storedmap[1][1] = new TiledMap("data//level4(1-1).tmx","art");
		} catch (SlickException e) {
			System.out.println("level4 could not load data");
			e.printStackTrace();
		}
		locationx = 0; locationy = 0;
	}
	
	public void createmasterlist() {
		createmasterlist(2,2);
	}

}
