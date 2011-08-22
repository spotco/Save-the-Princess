package levels;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level3 extends Level {
	
	public static final String thislevelsong = "main1";
	public String name() {
		return "your friendly introduction to crates";
	}
	
	public void init() {
		mapsong = thislevelsong;
		storedmap = new TiledMap[1][1];
		try {
			storedmap[0][0] = new TiledMap("data//level3.tmx","art");
		} catch (SlickException e) {
			System.out.println("level3 could not load data");
		}
		locationx = 0; locationy = 0;
	}

}
