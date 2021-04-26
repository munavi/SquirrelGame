package de.hsa.fatsquirrel.core;

import java.util.Timer;
import java.util.TimerTask;

import de.hsa.fatsquirrel.console.UI;

public abstract class Game {

    protected final int FPS = 5;
    private int frameCount = 0;
    protected UI ui;
    protected State state;

    public Game(State state, UI ui) {
        this.state = state;
        this.ui = ui;
    }
    
    public void timerTask1() {		//prueft eingabepuffer, updatet rendert
    	Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
            	processInput();
                update();
            	render();
            }
        }, 0);
    }
    
    public void timerTask2() {		//ueberschreibt eingabepuffer
    	Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
            	ui.inputLoop();
            }
        }, 0);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep((int)(1.0f / FPS *1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerTask1();
            timerTask2();
            frameCount++;
        }
    }
    
    public void runOld() {
    	while(true) {
    	render();
    	ui.waitForInput();
    	processInput();
    	update();
    	}
    }

    public abstract void render();

    protected abstract void processInput();

    public void update() {
        state.update();
    }


}
