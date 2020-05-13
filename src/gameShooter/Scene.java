package gameShooter;

import engine.EntityManager;
import engine.GameEngine;

public class Scene {
  private String m_name;
  private GameEngine m_engine;
  private EntityManager m_entities;
  
  public Scene(GameEngine engine, String name) {
    this.setGameEngine(engine);
    this.setName(name);    
    
    this.m_entities = new EntityManager();    
  }
  
  public void activate() {
    
  }
  
  public void update() {
    this.m_entities.update();
  }
  
  public void paint() {
    this.m_entities.paint();
  }
  
  public void setGameEngine(GameEngine engine) {this.m_engine=engine;}
  public GameEngine getGameEngine() {return this.m_engine;}
  public void setName(String name) {this.m_name=name;}
  public String getName() {return this.m_name;}
  public EntityManager getEntities() {return this.m_entities;}
}
