package com.stealthGame.engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Game implements Runnable {
	
	private Thread thread;
	private Window window;
	private Handler handler;
	private Input input;
	private AbstractGame ag;
	
	private int width = 120, height = 80;
	private float scale = 6f;
	private String title = "Speercs v0.1";
	
	private boolean running = false; 
	private final double UPDATE_CAP = 1.0/60.0;
	
	
	public Game(AbstractGame ag) {
		this.ag = ag;
		
	}
	
	public void start() {
		window = new Window(this);
		handler = new Handler(this);
		input = new Input(this);
		
		thread = new Thread(this);
		thread.run();
	}
	
	public void stop() {
		
	}
	
	public void run() {
		running = true; 
		boolean render = false;
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		
		double frameTime = 0;
		int frames = 0;
		int fps = 0;
		
		while(running) {
			render = false;
			
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while (unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				render = true;
				
				ag.update(this, (float)UPDATE_CAP);
/*
				System.out.println("X: " + input.getMouseX() + "\nY: " + input.getMouseY());
				
				if (input.getScroll() != 0) {
					System.out.println(input.getScroll());
				}
				
				if(input.isButton(MouseEvent.BUTTON1)) {
					System.out.println("Mouse is pressed");
				}
				
				if(input.isKey(KeyEvent.VK_A)) {
					System.out.println("A is pressed");
				}
*/				
				input.update();
				
				if (frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
					System.out.println("FPS: " + fps);
				}
			}

			if (render) {
				handler.clear();
				ag.render(this, handler);
				window.update();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		dispose();
	}
	
	private void dispose() {
		
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public Window getWindow() {
		return window;
	}

	public Input getInput() {
		return input;
	}
}
