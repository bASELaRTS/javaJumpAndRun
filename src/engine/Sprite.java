package engine;

import java.awt.image.BufferedImage;
import java.util.Vector;

import engine.SpriteFrame;

public class Sprite {
    private Vector<SpriteFrame> m_frames;
    private Vector<BufferedImage> m_images;    
    private int m_frameIndex;
    private long m_frameTimestamp;
    
    public Sprite() {      
      this.m_frames = new Vector<SpriteFrame>();
      this.m_images = new Vector<BufferedImage>();
      this.m_frameIndex = 0;
      this.m_frameTimestamp = System.currentTimeMillis();
    }
    
    public void add(BufferedImage image) {
      if (image!=null) {
        this.m_images.add(image);
      }
    }
    
    public void add(SpriteFrame frame) {
      if (frame!=null) {
        this.m_frames.add(frame);
      }
    }
    
    public void update() {
      long dtm = System.currentTimeMillis();      
      SpriteFrame frame;
      
      if (this.m_frames.size()>0) {
        frame = this.m_frames.elementAt(this.m_frameIndex);
        if ((dtm-this.m_frameTimestamp)>=frame.getFrameDelay()) {
          this.m_frameTimestamp = dtm;
          this.m_frameIndex = (this.m_frameIndex+1)%this.m_frames.size();
        }
      }
    }
    
    public BufferedImage getImage() {
      SpriteFrame frame;      
      
      if (this.m_images.size()>0) {
        if (this.m_frames.size()>0) {
          frame = this.m_frames.elementAt(this.m_frameIndex);
          return this.m_images.elementAt(frame.getImageIndex());
        } else {
          return this.m_images.elementAt(0);
        }
      }
      
      return null;
    }
  }
