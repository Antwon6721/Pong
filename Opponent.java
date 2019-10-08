/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Anthony Hall
 */
public class Opponent {
     private int height, width, x, y, vx, vy, hp, xp, score;
    private Rectangle bounds;
    private Color color;
    private final int SPEED = 15;
    private boolean alive;
    
    public Opponent(int cWidth, int cHeight) {
    this.x = 1400;
        this.y = cHeight / 2;
        this.vx = 0;
        this.vy = 0;
        this.width = 10;
        this.height = 40;
        this.xp = 0;
        this.score = 0;
        this.color = Color.WHITE;
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    //Methods
    
    public void move(String direction) {        
        if (direction.equals("up"))
            vy = -SPEED;
        else if (direction.equals("down"))
            vy = SPEED;
    }
    
    public void draw(Graphics g) {
        g.setColor(this.color);
        Graphics2D g2d = (Graphics2D) g;
        g.fillRect(x, y, width, height);
    }
    
    public void update() {
        this.x += vx;
        this.y += vy;
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public void stop() {
        this.vx = 0;
        this.vy = 0;
    }
}

