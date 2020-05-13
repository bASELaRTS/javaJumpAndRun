package gameShooter;

import java.awt.image.BufferedImage;

import engine.GameEngine;
import engine.GameGraphics;
import engine.Sprite;
import engine.SpriteFrame;
import engine.Vector3;

public class Bullit01 extends ShooterEntity {
  private Sprite m_sprite;
  private Vector3 m_speed;
  
  public Bullit01(GameEngine engine, Scene scene, Vector3 speed) {
    super(engine,scene,"bullit01");
    
    this.m_speed = new Vector3(speed);
    
    BufferedImage image;
    image = GameGraphics.getImage("data/laser-bolts.png");
    
    this.m_sprite = new Sprite();
    this.m_sprite.add(GameGraphics.getImage(image, 6,18,5,13));
    this.m_sprite.add(GameGraphics.getImage(image,20,18,5,13));
    this.m_sprite.add(new SpriteFrame(0,100));
    this.m_sprite.add(new SpriteFrame(1,100));
  }
  
  public void update() {
    Vector3 v1 = new Vector3();
    Vector3 v2 = new Vector3();
    
    Vector3.scale(this.m_speed, this.getGame().getTimer().getElapsed(), v1);
    Vector3.add(this.getPosition(), v1, v2);
    this.getPosition().setVector(v2);
    
    if (this.getPosition().y<-13) {
      this.setRemove(true);
      this.setVisible(false);
    }
    
    this.m_sprite.update();
  }
  
  public void paint() {
    GameGraphics graphics = this.getGame().getGraphics();
    int x = (int)this.getPosition().x;
    int y = (int)this.getPosition().y;
    graphics.drawImage(this.m_sprite.getImage(), x, y);
  }
}
