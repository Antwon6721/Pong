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
    private Ball ball;
    

    public World() {
        super();
        player = new Player(800, 600);
        opponent = new Opponent(800, 600);
        ball = new Ball(800,600);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/60);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 1440, 840);
        opponent.draw(g);
        //opponent.drawScore(g);
        player.draw(g);
        //player.drawScore(g);
        ball.draw(g);
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
            
            opponent.update();
            player.update();
            ball.update();
            repaint();
            checkCollisions();
        }
    }
    public void checkCollisions(){
        if (ball.getX() < 0 || ball.getX() + ball.getWidth() > 1440) {
            ball.setVx(-ball.getVx());
        }
        if (ball.getY() < 0 || ball.getY() + ball.getHeight() > 840) {
            ball.setVy(-ball.getVy());
        }
        if (player.getY() < 0 || player.getY() + player.getHeight() > 840) {
            player.setVy(player.getVy() - player.getVy());
        }
        if((player.getBounds().intersects(ball.getBounds())) || (opponent.getBounds().intersects(ball.getBounds()))){
           ball.setVx(-ball.getVx());
           ball.setVy((int)Math.random() * 5 - 2 );
           if (ball.getVx() > 0) {
               ball.setVx(ball.getVx() + 1);
           }
           else {
               ball.setVx(ball.getVx() - 1);
           }
          
        }
        if (ball.getX() + ball.getWidth() > 1440) {
            player.setScore(player.getScore() + 1);
            System.out.println("player score: "+ player.getScore());
        }
        if (ball.getX() < 0) {
            opponent.setScore(opponent.getScore() + 1);
            System.out.println("opponent score: "+ opponent.getScore());
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.move("up");
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.move("down");
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            opponent.move("up");
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            opponent.move("down");
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.stop();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player.stop();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            opponent.stop();
        }    
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            opponent.stop();
        }
    }
}