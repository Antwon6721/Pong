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
    private boolean paused;
    private Timer timer;
    private Player player;
    private Opponent opponent; 
    private Ball ball;
    

    public World() {
        super();
        paused = false;
        player = new Player(800, 600);
        opponent = new Opponent(800, 600);
        ball = new Ball(800,600);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/30);
        
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 1440, 840);
        opponent.draw(g);
        g.drawString("" + opponent.getScore(), 1420, 14);
        g.drawString("power points:" + opponent.getPowerPoints(), 1320, 800);
        player.draw(g);
        g.drawString("" + player.getScore(), 10, 14);
        g.drawString("power points:" + player.getPowerPoints(), 30, 800);
        ball.draw(g);
         if (ball.getVx() == 0 & ball.getVy() == 0){
            g.drawString("press space to play",670,520);
        } 
        if (paused == true){
            g.drawString("Paused press p to play",670,520);
        }
        if (opponent.getPowerPoints() >= 6){
            g.drawString("press left arrow to use speed ball", 1220, 770);
        }
         if (opponent.getPowerPoints() >= 16){
            g.drawString("press Enter to use combined powers", 1220, 730);
        }
         if (player.getPowerPoints() >= 16){
            g.drawString("press X to use combined", 30, 730);
        }
        if (player.getPowerPoints() >= 6){
            g.drawString("press D to use speed ball", 30, 770);
        }
        if (opponent.getPowerPoints() >= 12){
            g.drawString("press right arrow to use Small ball", 1220, 750);
        }
        if (player.getPowerPoints() >= 12){
            g.drawString("press E to use Small ball", 30, 750);
        }
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
           if (paused == false) { 
               opponent.update();
               player.update();
                ball.update();
            }
            repaint();
            checkCollisions();
            powerUps();
        }
    }
    public int i = 0;
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
         if (player.getBounds().intersects(ball.getBounds())){
             ball.setVx(-5);
         }
        if (opponent.getBounds().intersects(ball.getBounds())){
             ball.setVx(5);
         }
        if((player.getBounds().intersects(ball.getBounds())) || (opponent.getBounds().intersects(ball.getBounds()))){
           
            if(ball.getVx() > 5 ){   
                ball.setVx(5);
            } 
            else if (ball.getVx() < -5) {
                ball.setVx(-5);
            }
            ball.setVx(-ball.getVx());
            int[] speed = new int[9];
            speed[0] = -6;
            speed[1] = -4;
            speed[2] = -4;
            speed[3] = -3;
            speed[4] = 0;
            speed[5] = 3;
            speed[6] = 4;
            speed[7] = 4;
            speed[8] = 6;
            ball.setVy(speed[(int)(Math.random() * 9)]);  
            
            if (ball.getVy() < -6) {
                ball.setVy(speed[(int)(Math.random() * 9)]);
            }
            if(ball.getVy() > 6 ){   
                ball.setVy(speed[(int)(Math.random() * 9)]);
            }
            if(ball.getWidth()<10){
                ball.setWidth(10);
            }
            if(ball.getHeight()<10){
                ball.setHeight(10);
            }
        }
        
        if (ball.getX() + ball.getWidth() > 1440) {
            if (i > 0) {
            player.setScore(player.getScore() + 1); 
            player.setPowerPoints(player.getPowerPoints() + 2);
            }
            i++;
           
            ball.setVx(0);
            ball.setVy(0);
            ball.setX(720);
            ball.setY(420);
        }
        if (ball.getX() < 0) {
            opponent.setScore(opponent.getScore() + 1);
            opponent.setPowerPoints(opponent.getPowerPoints() + 2);
            ball.setVx(0);
            ball.setVy(0);
            ball.setX(720);
            ball.setY(420);
        }
    }
    
    public void powerUps() {
       
    }
    
    
    public void togglePaused() {
        if (paused) {
            paused = false;
        }
        else {
            paused = true;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (player.getPowerPoints() >=16 && e.getKeyCode() == KeyEvent.VK_X){
            ball.setVx(ball.getVx()*2);
            ball.setVy(ball.getVy()*2);
            ball.setWidth(ball.getWidth()/2);
            ball.setHeight(ball.getHeight()/2);
            player.setPowerPoints(player.getPowerPoints() - 16);
        }
        if (opponent.getPowerPoints() >=16 && e.getKeyCode() == KeyEvent.VK_ENTER){
            ball.setVx(ball.getVx()*2);
            ball.setVy(ball.getVy()*2);
             ball.setWidth(ball.getWidth()/2);
            ball.setHeight(ball.getHeight()/2);
            player.setPowerPoints(opponent.getPowerPoints() - 16);
        }
        if (player.getPowerPoints() >=6 && e.getKeyCode() == KeyEvent.VK_D){
            ball.setVx(ball.getVx()*2);
            ball.setVy(ball.getVy()*2);
            player.setPowerPoints(player.getPowerPoints() - 6);
        }
         if (player.getPowerPoints() >=12 && e.getKeyCode() == KeyEvent.VK_E){
            ball.setWidth(ball.getWidth()/2);
            ball.setHeight(ball.getHeight()/2);
            player.setPowerPoints(player.getPowerPoints() - 12);
        }
           if (opponent.getPowerPoints() >=12 && e.getKeyCode() == KeyEvent.VK_RIGHT){
            ball.setWidth(ball.getWidth()/2);
            ball.setHeight(ball.getHeight()/2);
            opponent.setPowerPoints(opponent.getPowerPoints() - 12);
        }
        if (opponent.getPowerPoints() >=6 && e.getKeyCode() == KeyEvent.VK_LEFT){
            ball.setVx(ball.getVx()*2);
            ball.setVy(ball.getVy()*2);
            opponent.setPowerPoints(opponent.getPowerPoints() - 6);
        }
        if(ball.getVx() == 0){  
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                ball.setVx(5);
                ball.setVy((int) ((Math.random() * 9) - 4));
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_P){
            togglePaused();
        }
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