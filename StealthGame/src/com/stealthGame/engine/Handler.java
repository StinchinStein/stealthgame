package com.stealthGame.engine;

import java.awt.image.DataBufferInt;

import com.stealthGame.engine.gfx.Image;
import com.stealthGame.game.World;

public class Handler {
	private int pW, pH;
	private int [] p;
	
	public Handler(Game game) {
		pW = game.getWidth();
		pH = game.getHeight();
		p = ((DataBufferInt)game.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	
	public void clear() {
		for (int i = 0; i < p.length; i++) {
			p[i] = 0;	
		}
	}
	
	public void setPixel(int x, int y, int color2) {
		int alpha = ((color2 >> 24) & 0xff);
		
		if((x < 0 || x >= pW || y < 0 || y >=pH) || (alpha) == 0) return;
		
		int color1 = p[x + y * pW];
		int r = ((color1 >> 16) & 0xff) - (int) ((((color1 >> 16) & 0xff) - ((color2 >> 16) & 0xff)) * (alpha / 255f));
		int g = ((color1 >> 8) & 0xff) - (int) ((((color1 >> 8) & 0xff) - ((color2 >> 8) & 0xff)) * (alpha / 255f));
		int b = ((color1) & 0xff) - (int) ((((color1) & 0xff) - ((color2) & 0xff)) * (alpha / 255f));
		p[x + y * pW] = color(r, g, b);
	}
	
	public void setAlpha(int x, int y, int a) {
		int alpha = ((a >> 24) & 0xff);
		
		if((x < 0 || x >= pW || y < 0 || y >=pH) || (alpha) == 0) return;
		
		int color1 = p[x + y * pW];
		int r = ((color1 >> 16) & 0xff) - (int) ((((color1 >> 16) & 0xff) - ((getPixel(x, y) >> 16) & 0xff)) * (alpha / 255f));
		int g = ((color1 >> 8) & 0xff) - (int) ((((color1 >> 8) & 0xff) - ((getPixel(x, y) >> 8) & 0xff)) * (alpha / 255f));
		int b = ((color1) & 0xff) - (int) ((((color1) & 0xff) - ((getPixel(x, y)) & 0xff)) * (alpha / 255f));
		p[x + y * pW] = color(r, g, b);
	}
	
	public void drawLine(int x1, int y1, int x2, int y2) {
		
	}

	public void drawSprite(Image image, int offX, int offY) {
		for(int y = 0; y < image.getH(); y++) {
			for(int x = 0; x < image.getW(); x++) {
				setPixel(x + (offX*World.GRID_SIZE), y + (offY*World.GRID_SIZE), image.getP()[x + y * image.getW()]);
			}
		}
	}
	
	
	public void drawRect(int xOff, int yOff, int lx, int ly, int value) {
		for(int x = 0; x < lx; x++) {
			for(int y = 0; y < ly; y++) {
				setPixel(x+xOff, y+yOff, value);
			}
		}
	}

	public void drawImage(Image image, int offX, int offY) {
		for(int y = 0; y < image.getH(); y++) {
			for(int x = 0; x < image.getW(); x++) {
				setPixel(x + offX, y + offY, image.getP()[x + y * image.getW()]);
				
			}
		}
	}
	
	
	
	public int getPixel(int x, int y) {
		return p[x + y * pW];
	}

	public int color(int r, int g, int b, int a) {
		return ((a & 0x0ff) << 24) | (r & 0x0ff) << 16 | ((g & 0x0ff) << 8) | (b & 0x0ff);
	}
	public int color(int r, int g, int b) {
		return ((255 & 0x0ff) << 24) | (r & 0x0ff) << 16 | ((g & 0x0ff) << 8) | (b & 0x0ff);
	}
	
	public void printRGBA(int argb) {
		int r = (argb)&0xFF;
		int g = (argb>>8)&0xFF;
		int b = (argb>>16)&0xFF;
		int a = (argb>>24)&0xFF;
		System.out.println(r + ", " + g + ", " + b + ", " + a);
	}
}
