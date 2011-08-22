package levels;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level2 extends Level1 {
	
	public static final String thislevelsong = "main1";
	public String name() {
		return "buttons and doors";
	}
	
	public void init() {
		mapsong = thislevelsong;
		storedmap = new TiledMap[1][1];
		try {
			storedmap[0][0] = new TiledMap("data//level2.tmx","art");
		} catch (SlickException e) {
			System.out.println("level2 could not load data");
		}
		locationx = 0; locationy = 0;
	}

}
