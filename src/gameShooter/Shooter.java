package gameShooter;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

import engine.GameEngine;
import engine.GameFrame;
import engine.GameGraphics;
import engine.InputKeyboard;

public class Shooter extends GameEngine {
  private Vector<Sprite> m_sprites;
  private int m_spriteIndex;
  private double m_xposition;
  private double m_yposition;
  
  public Shooter() {
    super("Shooter",160,120);
    
    this.m_sprites = new Vector<Sprite>();
    
    BufferedImage image;
    Sprite sprite;
    
    image = GameGraphics.getImage("data/ship.png");
    for(int i=0;i<5;i++) {
      sprite = new Sprite();
      sprite.add(this.ripSpriteSheet(image, i*16,  0, 16, 24));
      sprite.add(this.ripSpriteSheet(image, i*16, 24, 16, 24));
      sprite.add(new Frame(0,100));
      sprite.add(new Frame(1,100));
      this.m_sprites.add(sprite);
    }
    
    this.m_spriteIndex = 2;    
    this.m_xposition = (int)((this.getWidth()-(this.m_sprites.elementAt(this.m_spriteIndex).getImage().getWidth()))*0.5);
    this.m_yposition = this.getHeight() - (this.m_sprites.elementAt(this.m_spriteIndex).getImage().getHeight()) - 5;
  }
  
  private BufferedImage ripSpriteSheet(BufferedImage sheet, int x, int y, int w, int h) {
    BufferedImage image;
    Graphics graphics;
    
    image = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
    graphics = image.getGraphics();
    graphics.drawImage(sheet,0,0,w,h,x,y,x+w,y+h,null);    
    
    return image;
  }
  
  public void update() {
    Sprite sprite;
    InputKeyboard keyboard = this.getInput().getKeyboard();
    
    if (keyboard.getState(KeyEvent.VK_LEFT)) {
      this.m_xposition = (this.m_xposition - ((160 / 1000.0)*this.getTimer().getElapsed()));
    } else if (keyboard.getState(KeyEvent.VK_RIGHT)) {
      this.m_xposition = (this.m_xposition + ((160 / 1000.0)*this.getTimer().getElapsed()));
    }
    
    sprite = this.m_sprites.elementAt(this.m_spriteIndex);
    sprite.update();    
  }
  
  public void paint() {
    GameGraphics g = this.getGraphics();
    g.clear();
    
    Sprite sprite;
    sprite = this.m_sprites.elementAt(this.m_spriteIndex);
    g.drawImage(sprite.getImage(), (int)this.m_xposition, (int)this.m_yposition);
  }
  
  public class Sprite {
    private Vector<Frame> m_frames;
    private Vector<BufferedImage> m_images;    
    private int m_frameIndex;
    private long m_frameTimestamp;
    
    public Sprite() {      
      this.m_frames = new Vector<Frame>();
      this.m_images = new Vector<BufferedImage>();
      this.m_frameIndex = 0;
      this.m_frameTimestamp = System.currentTimeMillis();
    }
    
    public void add(BufferedImage image) {
      if (image!=null) {
        this.m_images.add(image);
      }
    }
    
    public void add(Frame frame) {
      if (frame!=null) {
        this.m_frames.add(frame);
      }
    }
    
    public void update() {
      long dtm = System.currentTimeMillis();      
      Frame frame;
      
      if (this.m_frames.size()>0) {
        frame = this.m_frames.elementAt(this.m_frameIndex);
        if ((dtm-this.m_frameTimestamp)>=frame.getFrameDelay()) {
          this.m_frameTimestamp = dtm;
          this.m_frameIndex = (this.m_frameIndex+1)%this.m_frames.size();
        }
      }
    }
    public BufferedImage getImage() {
      Frame frame;      
      
      if (this.m_images.size()>0) {
        if (this.m_frames.size()>0) {
          frame = this.m_frames.elementAt(this.m_frameIndex);
          return this.m_images.elementAt(frame.m_imageIndex);
        } else {
          return this.m_images.elementAt(0);
        }
      }
      
      return null;
    }
  }
  
  public class Frame {
    private int m_frameDelay;
    private int m_imageIndex;
    
    public Frame(int imageIndex, int frameDelay) {
      this.setImageIndex(imageIndex);
      this.setFrameDelay(frameDelay);
    }
    
    public void setImageIndex(int i) {this.m_imageIndex=i;}
    public int getImageIndex() {return this.m_imageIndex;}
    public void setFrameDelay(int i) {this.m_frameDelay=i;}
    public int getFrameDelay() {return this.m_frameDelay;}
  }

  public static void main(String[] args) {
    new GameFrame(new Shooter());
  }
}
