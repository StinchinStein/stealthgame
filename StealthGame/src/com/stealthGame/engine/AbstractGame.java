package com.stealthGame.engine;

public abstract class AbstractGame {
	public abstract void update(Game game, float dt);
	public abstract void render(Game game, Handler r);
}
