package gameShooter;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

import engine.GameEngine;
import engine.GameGraphics;
import engine.InputKeyboard;
import engine.SpriteFrame;
import engine.Sprite;

public class Player extends ShooterEntity {
  private Vector<Sprite> m_sprites;
  private int m_spriteIndex;
  private int m_spriteIndexGoto;
  private long m_spriteTimestamp;
  
  private Vector<Weapon> m_weapons;
  private int m_weaponIndex;  
  
  private int m_health;
  private int m_lives;
  
  public Player(GameEngine engine, Scene scene) {
    super(engine,scene,"player");
    this.setGame(engine);
    
    this.m_sprites = new Vector<Sprite>();
    
    BufferedImage image;
    Sprite sprite;
    
    image = GameGraphics.getImage("data/ship.png");
    for(int i=0;i<5;i++) {
      sprite = new Sprite();
      sprite.add(GameGraphics.getImage(image, i*16,  0, 16, 24));
      sprite.add(GameGraphics.getImage(image, i*16, 24, 16, 24));
      sprite.add(new SpriteFrame(0,100));
      sprite.add(new SpriteFrame(1,100));
      this.m_sprites.add(sprite);
    }
    
    this.m_spriteIndex = 2;
    this.m_spriteIndexGoto = -1;
    this.m_spriteTimestamp = System.currentTimeMillis();
    this.getPosition().x = (int)((this.getGame().getWidth()-(this.m_sprites.elementAt(this.m_spriteIndex).getImage().getWidth()))*0.5);
    this.getPosition().y = this.getGame().getHeight() - (this.m_sprites.elementAt(this.m_spriteIndex).getImage().getHeight()) - 10;

    this.m_weapons = new Vector<Weapon>();
    this.m_weapons.add(new WeaponPlayerA(this));
    this.m_weapons.add(new WeaponPlayerB(this));
    this.m_weapons.add(new WeaponPlayerC(this));
    this.m_weapons.add(new WeaponPlayerD(this));
    this.m_weaponIndex = 0;
    
    this.setHealth(65);
    this.setLives(3);
  }
  
  public void update() {
    Sprite sprite;
    InputKeyboard keyboard = this.getGame().getInput().getKeyboard();
    long dtm = System.currentTimeMillis();
    double x;
    
    // movement;
    x = this.getPosition().x;
    if (keyboard.getState(KeyEvent.VK_LEFT)) {
      x = (x - ((160 / 1000.0)*this.getGame().getTimer().getElapsed()));
      if (this.m_spriteIndexGoto<0) {
        this.m_spriteIndexGoto=0;
        this.m_spriteTimestamp=dtm;
      }
    } else if (keyboard.getState(KeyEvent.VK_RIGHT)) {
      x = (x + ((160 / 1000.0)*this.getGame().getTimer().getElapsed()));
      if (this.m_spriteIndexGoto<0) {
        this.m_spriteIndexGoto=4;
        this.m_spriteTimestamp=dtm;
      }
    } else {
      if (this.m_spriteIndexGoto<0) {
        this.m_spriteIndexGoto=2;
        this.m_spriteTimestamp=dtm;
      }
    }      
    
    // collision
    if (x<0) x=0;
    if (x>(this.getGame().getWidth()-16)) x=this.getGame().getWidth()-16;

    // set position
    this.getPosition().x = x;
    
    // action
    if (keyboard.getState(KeyEvent.VK_Z)) {
      keyboard.setState(KeyEvent.VK_Z, false);
      this.m_weaponIndex--;
      if (this.m_weaponIndex<0) this.m_weaponIndex=0;
    } else if (keyboard.getState(KeyEvent.VK_X)) {
      keyboard.setState(KeyEvent.VK_X, false);
      this.m_weaponIndex++;
      if (this.m_weaponIndex>=this.m_weapons.size()) this.m_weaponIndex=this.m_weapons.size()-1;
    }    
    
    if (keyboard.getState(KeyEvent.VK_SPACE)) {
      Weapon weapon = this.m_weapons.elementAt(this.m_weaponIndex);
      weapon.fire();      
    }   
    
    // animation transition
    if (this.m_spriteIndexGoto>=0) {
      if ((dtm-this.m_spriteTimestamp)>=50) {
        this.m_spriteTimestamp=dtm;
        if (this.m_spriteIndex>this.m_spriteIndexGoto) {
          this.m_spriteIndex--;
        } else if (this.m_spriteIndex<this.m_spriteIndexGoto) {
          this.m_spriteIndex++;
        } else {
          this.m_spriteIndexGoto=-1;          
        }
      }
    }    
    
    // animation update
    sprite = this.m_sprites.elementAt(this.m_spriteIndex);
    sprite.update();    
  }
  
  public void paint() {
    GameGraphics g = this.getGame().getGraphics();
    Sprite sprite;
    int x = (int)this.getPosition().x;
    int y = (int)this.getPosition().y;
    sprite = this.m_sprites.elementAt(this.m_spriteIndex);
    g.drawImage(sprite.getImage(),x,y);    
  }
  
  public void setHealth(int i) {this.m_health=i;}
  public int getHealth() {return this.m_health;}
  
  public void setLives(int i) {this.m_lives=i;}
  public int getLives() {return this.m_lives;}
}
