package gameShooter;

import java.awt.image.BufferedImage;

import engine.GameEngine;
import engine.GameEntity;
import engine.GameGraphics;

public class Background extends ShooterEntity {
  private BufferedImage m_image;
  private double m_yoffset;
  
  private long m_cloudTimestamp;
  
  public Background(GameEngine engine, Scene scene) {
    super(engine,scene,"background");
    
    this.m_image = GameGraphics.getImage("data/desert-backgorund.png");
    this.setSize(this.m_image.getWidth(), this.m_image.getHeight());
    this.m_yoffset = 0;
    this.m_cloudTimestamp = System.currentTimeMillis();
  }
  
  public void update() {
    long dtm = System.currentTimeMillis();
    this.m_yoffset = this.m_yoffset + ((160 / 5000.0)*this.getGame().getTimer().getElapsed());
    if ((dtm-this.m_cloudTimestamp)>=5000) {
      this.m_cloudTimestamp = dtm;
      if ((Math.random()*10)>=5) {
        GameEntity entity = new Cloud(this.getGame());
        entity.getPosition().y = -entity.getHeight();
        this.getScene().getEntities().add(entity);
      }
    }
  }
  
  public void paint() {
    GameGraphics graphics = this.getGame().getGraphics();
    int x,y;

    x = 0;
    y = (((int)this.m_yoffset)%this.getHeight()) - this.getHeight();
    while(y<this.getGame().getHeight()) {
      x = 0;
      while(x<this.getGame().getHeight()) {
        graphics.drawImage(this.m_image, x, y);
        x+=this.getWidth();
      }
      y+=this.getHeight();
    }
  }
}
