package com.stealthGame.game;

import com.stealthGame.engine.AbstractGame;
import com.stealthGame.engine.Game;
import com.stealthGame.engine.Handler;
import com.stealthGame.engine.Input;
import com.stealthGame.engine.gfx.Image;

public class GameManager extends AbstractGame {
	
	
	public int mouseA, mouseB;
	public int scrollX, scrollY;
	
	public World world;
	public Entity player;
	public Image GRASS, REFINERY;
	public GameManager() {
		world = new World();
		GRASS = new Image("grass.png");
		REFINERY = new Image("refinery.png"); //gffdshgfdgsfd
	}

	public void update(Game game, float dt) {
		Input inp = game.getInput();
		mouseA = inp.getMouseX() / world.GRID_SIZE;
		mouseB = inp.getMouseY() / world.GRID_SIZE;
		scrollX += inp.getMouseX() - inp.getPMouseX();
		scrollY += inp.getMouseY() - inp.getPMouseY();
	}

	public void render(Game game, Handler g) {
		for(int i = 0; i < world.blocks.length; i++) {
			int x = i / world.h;
			int y = i % world.h;

			//if(world.blocks[i] == 1) {
				g.drawSprite(GRASS, x, y);
			//}
			/*
			if(world.blocks[i] == 2) {
				g.setPixel(x, y, g.color(255, 255, 0));
			}*/
		}
		g.drawSprite(REFINERY, mouseA, mouseB);
		
		//JS parser test
		/*try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			FileInputStream fileInputStream = new FileInputStream("main.js");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
			engine.eval(reader);
			Invocable inv = (Invocable) engine;
			inv.invokeFunction("init", "param");	
		} catch(Exception e) {}*/
	}
	
	public static void main(String args[]) {
		Game game = new Game(new GameManager());
		game.start();
	}

}
