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
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/60);
        
        
    }
public int y = 0;
public int q = 0;
public int x = 0;
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
        if (x > 0){
            g.drawString("press space to play",670,520);
        } 
         
        if (q <= 0){
            g.drawString("what would you like your Score limit to be press 1 for 5, press 2 for 10, or press 3 for unlimitied", 465, 500);
        }
        if (paused == true){
            g.drawString("Paused press p to play",670,520);
        }
         if (paused == true){
            g.drawString("Press R to Restart",670,500);
        }
        if (opponent.getPowerPoints() >= 6){
            g.drawString("press left arrow to use speed ball (cost:6)", 1180, 770);
        }
         if (opponent.getPowerPoints() >= 16){
            g.drawString("press Enter to use combined powers (cost:16)", 1180, 730);
        }
         if (player.getPowerPoints() >= 16){
            g.drawString("press X to use combined (cost:16)", 30, 730);
        }
        if (player.getPowerPoints() >= 6){
            g.drawString("press D to use speed ball (cost:6)", 30, 770);
        }
        if (opponent.getPowerPoints() >= 12){
            g.drawString("press right arrow to use Small ball (cost:12)", 1180, 750);
        }
        if (player.getPowerPoints() >= 12){
            g.drawString("press E to use Small ball (cost:12)", 30, 750);
        }
        if(y >= 1){
        if (opponent.getScore() == maxScore){
            g.drawString("player 2 wins", 690, 200);
            g.drawString("press Q to play again", 670, 250);
        }
         if (player.getScore() == maxScore){
            g.drawString("player 1 wins", 690, 200);
            g.drawString("press Q to play again", 670, 250);
        }
        }
         
//         if (y >= 2){
//            g.drawString("press Q to play agian", 670, 520);
//        }
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
        }
    }
    public int t = 0;
    public int i = 0;
    public int maxScore;
    public void checkCollisions(){
        if(player.getScore() == maxScore){
                ball.setVx(0);
                ball.setVy(0);
                ball.setX(720);
                ball.setY(420);
                player.setPowerPoints(0);
                opponent.setPowerPoints(0);
                
            }
            if(opponent.getScore() == maxScore){
                ball.setVx(0);
                ball.setVy(0);
                ball.setX(720);
                ball.setY(420);
                player.setPowerPoints(0);
                opponent.setPowerPoints(0);
                
            }
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
             if(t >= 6){
                 ball.setVx(ball.getVx()-1);
                 t++;
             }
         }
        if (opponent.getBounds().intersects(ball.getBounds())){
             ball.setVx(3);
              if(t >= 6){
                 ball.setVx(ball.getVx()+1);
                 t++;
         }
              if(t>=7){
                  t = 0;
              }
        }
        if((player.getBounds().intersects(ball.getBounds())) || (opponent.getBounds().intersects(ball.getBounds()))){
           
            if(ball.getVx() > 3 ){   
                ball.setVx(3);
            } 
            else if (ball.getVx() < -3) {
                ball.setVx(-3);
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
            player.setScore(player.getScore() + 1); 
            player.setPowerPoints(player.getPowerPoints() + 2);
            ball.setVx(0);
            ball.setVy(0);
            ball.setX(720);
            ball.setY(420);
            x++;
        }
        if (ball.getX() < 0) {
            opponent.setScore(opponent.getScore() + 1);
            opponent.setPowerPoints(opponent.getPowerPoints() + 2);
            ball.setVx(0);
            ball.setVy(0);
            ball.setX(720);
            ball.setY(420);
            x++;
        }
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
        if ( player.getScore() == maxScore || player.getScore() == maxScore && e.getKeyCode() == KeyEvent.VK_Q){
                player.setScore(0);
                opponent.setScore(0);
                q = 0;
                x = 0;
        }
      
        if ( e.getKeyCode() == KeyEvent.VK_1){
            maxScore = 5;
            y+=1;
            q++;
            x++;
        }
         if (e.getKeyCode() == KeyEvent.VK_2){
            maxScore = 10;
            y+=1;
            q++;
            x++;
        }
        if (e.getKeyCode() == KeyEvent.VK_3){
            maxScore = -1;
            y+=1;
            q++;
            x++;
        }
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
        if (paused == true && e.getKeyCode() == KeyEvent.VK_R){
            player.setScore(0);
            opponent.setScore(0);
            ball.setX(720);
            ball.setY(420);
            ball.setVx(0);
            ball.setVy(0);
            player.setPowerPoints(0);
            opponent.setPowerPoints(0);
            paused = false;
        }
        if(ball.getVx() == 0){  
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                ball.setVx((int)(Math.random()+1*3));
                ball.setVy((int) ((Math.random() * 9) - 4));
                x = 0;
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