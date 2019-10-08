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
 * @author 641580
 */
public class Ball {
    private int height, width, x, y, vx, vy;
    private Rectangle bounds;
    private Color color;
    
public Ball(int cWidth, int cHeight) {
        this.x = 200;
        this.y = cHeight / 2;
        this.vx = ((int)Math.random()+1*5);
        this.vy = ((int)Math.random()+1*5);
        this.width = 10;
        this.height = 10;
        this.color = Color.WHITE;
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
  }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }


 public void draw(Graphics g) {
        g.setColor(this.color);
        Graphics2D g2d = (Graphics2D) g;
        g.fillOval(x, y, width, height);
    }
 public void update() {
     x += vx;
     y += vy;
     this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
 }
 
}
