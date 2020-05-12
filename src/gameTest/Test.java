package gameTest;

import java.awt.image.BufferedImage;

import com.sun.glass.events.KeyEvent;

import engine.GameEngine;
import engine.GameFrame;
import engine.GameGraphics;

public class Test extends GameEngine {
  private BufferedImage m_image;
  private double m_offset;  
  private double m_sinpos[];
  
  public Test() {
    super("Test",320,240);
    
    this.m_image = GameGraphics.getImage("data/test.png");

    this.m_sinpos = new double[this.getWidth()];
    for(int i=0;i<this.getWidth();i++) {
      this.m_sinpos[i]=(int)(this.getHeight()*0.5);
    }
  }
  
  public void update() {
    if (this.getInput().getKeyboard().getState(KeyEvent.VK_ESCAPE)) {
      this.close();
    }
    
    this.m_offset+=((32 / 500.0)*this.getTimer().getElapsed());
    
    for(int i=0;i<this.m_sinpos.length;i++) {
      this.m_sinpos[i] = (Math.sin( ((Math.PI*((((int)(this.m_offset*3))+i)%360))/180.0) )*15) + (this.getHeight()*0.5);
    }
  }
  
  public void paint() {
    int i;
    int k,l;
    int x,y;
    int w,h;
    int c;
    GameGraphics graphics = this.getGraphics();
    
    // clear screen
    graphics.clear();

    // sinwave
    for(i=0;i<this.m_sinpos.length;i++) {
      k = (int)this.m_sinpos[i];
      c = GameGraphics.getColor(255, 0, 148, 255);
      graphics.drawLine(i, 0, i, k-1, c);
      c = GameGraphics.getColor(255, 58, 192, 0);
      graphics.drawLine(i, k, i, this.getHeight(), c);
    }
    
    // draw checkerboard
    l = 32;
    w = (int)Math.ceil(this.getWidth()/l);
    h = (int)Math.ceil(this.getHeight()/l);
    k = 0;
    i = 0;
    y = -(((int)this.m_offset)%(l*2));    
    while(y<this.getHeight()) {
      //x = -(((int)this.m_offset)%(l*2));
      x = 0;
      while(x<this.getWidth()) {
        if ((k%2)==0) {
          c = GameGraphics.getColor(255, 255, 216, 0);
          graphics.fillRect(x, y, l, l, c);
        }        
        
        k++;
        x+=l;
      }
      k = (i+1)%2;
      i++;
      y+=l;
    }
        
    // draw image
    w = this.getWidth();
    h = this.getHeight();
    x = (int)((w-this.m_image.getWidth())*0.5);
    y = (int)((h-this.m_image.getHeight())*0.5);
    graphics.drawImage(this.m_image, x, y);
    
    // show info
    c = GameGraphics.getColor(255, 255, 255, 255);
    graphics.drawString("FPS : "+this.getTimer().getFPS(), 2, 12, c);
    graphics.drawString("FPS Average : "+this.getTimer().getFPSAverage(), 2, 22, c);
    graphics.drawString("Elapsed : "+this.getTimer().getElapsed(), 2, 32, c);
  }
  
  public static void main(String[] args) {
    new GameFrame(new Test());
  }
}
