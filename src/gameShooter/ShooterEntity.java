package gameShooter;

import engine.GameEngine;
import engine.GameEntity;

public class ShooterEntity extends GameEntity {
  private Scene m_scene;
  
  public ShooterEntity(GameEngine engine, Scene scene, String key) {
    super(engine,key);
    this.setScene(scene);
  }
  
  public void setScene(Scene scene) {this.m_scene=scene;}
  public Scene getScene() {return this.m_scene;}
}
