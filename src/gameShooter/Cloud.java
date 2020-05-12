package gameShooter;

import java.awt.image.BufferedImage;

import engine.GameEngine;
import engine.GameEntity;
import engine.GameGraphics;

public class Cloud extends GameEntity {
  private BufferedImage m_image;
  
  public Cloud(GameEngine engine) {
    super(engine,"cloud");
    this.m_image = GameGraphics.getImage("data/clouds-transparent.png");
    this.setSize(this.m_image.getWidth(), this.m_image.getHeight());
  }
  
  public void update() {
    this.getPosition().y += ((160 / 2000.0) * this.getGame().getTimer().getElapsed());
    if (this.getPosition().y>this.getGame().getHeight()) {
      this.setVisible(false);
      this.setRemove(true);
    }
  }
  
  public void paint() {
    GameGraphics graphics = this.getGame().getGraphics();
    int x = (int) this.getPosition().x;
    int y = (int) this.getPosition().y;
    graphics.drawImage(this.m_image, x, y);
  }
}
