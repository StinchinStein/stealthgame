package com.stealthGame.game;

public class World {

	public int w = 64;
	public int h = 64;
	public static final int GRID_SIZE = 8;
	
	public int[] blocks = new int[w*h];
	public World() {
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				if(x % 2 == 0) {
					blocks[x + y * w] = 1;
				} else {
					blocks[x + y * w] = 2;
				}
			}
		}
	}
}
