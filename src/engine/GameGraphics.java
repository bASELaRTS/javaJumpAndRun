package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameGraphics {
  private int m_width;
  private int m_height;
  
  private BufferedImage m_buffer;
  private Graphics m_graphics;
  
  public GameGraphics(int width, int height) {
    this.setSize(width, height);
    
    this.m_buffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
    this.m_graphics = this.m_buffer.getGraphics();
  }
  
  public void clear() {
   this.clear(GameGraphics.getColor(255, 0, 0, 0)); 
  }
  
  public void clear(int c) {
    this.m_graphics.setColor(new Color(c,true));
    this.m_graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
  }
  
  public void setPixel(int x, int y, int c) {
    this.m_buffer.setRGB(x, y, c);
  }
  
  public void drawLine(int x1, int y1, int x2, int y2, int c) {
    this.m_graphics.setColor(new Color(c,true));
    this.m_graphics.drawLine(x1, y1, x2, y2);
  }
  
  public void fillRect(int x, int y, int w, int h, int c) {
    this.m_graphics.setColor(new Color(c,true));
    this.m_graphics.fillRect(x, y, w, h);
  }
  
  public void drawImage(BufferedImage image, int x, int y) {
    if (image!=null) {
      this.drawImage(image, x, y, image.getWidth(), image.getHeight());
    }
  }
  
  public void drawImage(BufferedImage image, int x, int y, int w, int h) {
    if (image!=null) {
      this.m_graphics.drawImage(image, x, y, w, h, null);
    }
  }
  
  public void drawString(String str, int x, int y, int c) {
    this.m_graphics.setColor(new Color(c,true));
    this.m_graphics.drawString(str, x, y);
  }
  
  public void drawFont(String str, int x, int y, Font font) {
    int i,px;
    String s;
    FontCharacter character;
    BufferedImage image;
    
    px = 0;
    for(i=0;i<str.length();i++) {
      s = "" + str.charAt(i);
      character = font.getCharacter(s);
      if (character!=null) {
        image = character.getImage();
        if (image!=null) {
          this.drawImage(image, x, y);
        }        
        px = character.getWidth();
      } else {
        px = font.getDefaultCharacterWidth();
      }
      px += font.getCharacterSpacing();
      x += px;
    }
  }  
  
  public BufferedImage getImage() {return this.m_buffer;}
  
  protected void setWidth(int i) {this.m_width=i;}
  protected void setHeight(int i) {this.m_height=i;}
  protected void setSize(int w, int h) {
    this.setWidth(w);
    this.setHeight(h);
  }
  public int getWidth() {return this.m_width;}
  public int getHeight() {return this.m_height;}
  
  public static int getColor(int a, int r, int g, int b) {
    int rgb = 0;
    rgb |= (a&0xff)<<24;
    rgb |= (r&0xff)<<16;
    rgb |= (g&0xff)<<8;
    rgb |= (b&0xff);
    return rgb;
  }
  
  public static BufferedImage getImage(String filename) {
    try {
      return ImageIO.read(new File(filename));
    } catch (IOException e) {
      e.printStackTrace();
    }    
    return null;
  }
  
  public static BufferedImage getImage(BufferedImage sheet, int x, int y, int w, int h) {
    BufferedImage image;
    Graphics graphics;
    
    image = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
    graphics = image.getGraphics();
    graphics.drawImage(sheet,0,0,w,h,x,y,x+w,y+h,null);    
    
    return image;
  }

}
