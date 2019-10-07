/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Anthony Hall
 */
public class World extends JPanel {
    
    private Timer timer;
    private Player player;
    private Opponent opponent; 
    

    public World() {
        super();
        player = new Player(800, 600);
        opponent = new Opponent(800, 600);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/12);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        opponent.draw(g);
        player.draw(g);
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
            
            opponent.update();
            player.update();
            repaint();
        }
    }
    

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.move("up");
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.move("down");
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.stop();
        }
            
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.stop();
        }
            
    }
}