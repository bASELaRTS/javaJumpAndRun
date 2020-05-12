package gameShooter;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

import engine.GameEngine;
import engine.GameEntity;
import engine.GameGraphics;
import engine.InputKeyboard;
import engine.InputKeyboardKey;
import engine.SpriteFrame;
import engine.Vector3;
import engine.Sprite;

public class Player extends GameEntity {
  private Vector<Sprite> m_sprites;
  private int m_spriteIndex;
  private int m_spriteIndexGoto;
  private long m_spriteTimestamp;

  private int m_fireDelay;
  private long m_fireTimestamp;
  
  public Player(GameEngine engine) {
    super(engine,"player");
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

    this.m_fireDelay = 350;
    this.m_fireTimestamp = 0;
    
    this.getGame().getInput().getKeyboard().add(new InputKeyboardKey(KeyEvent.VK_Z));
    this.getGame().getInput().getKeyboard().add(new InputKeyboardKey(KeyEvent.VK_X));
    this.getGame().getInput().getKeyboard().add(new InputKeyboardKey(KeyEvent.VK_C));
  }
  
  public void update() {
    Sprite sprite;
    InputKeyboard keyboard = this.getGame().getInput().getKeyboard();
    long dtm = System.currentTimeMillis();
    double x;
    Vector3 v3 = new Vector3();
    
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
    if (keyboard.getState(KeyEvent.VK_SPACE)) {
      if ((dtm-this.m_fireTimestamp)>=this.m_fireDelay) {
        this.m_fireTimestamp = dtm;
        
        // add bullet;
        GameEntity bullit;
        v3.setCoordinates(0, -(120/1000.0), 0);
        bullit = new Bullit01(this.getGame(),v3);
        bullit.getPosition().x = this.getPosition().x+5.5;
        bullit.getPosition().y = this.getPosition().y-13;
        ((Shooter)this.getGame()).getEntities().add(bullit);
      }
    }
    
    if (keyboard.getState(KeyEvent.VK_Z)) {
      if ((dtm-this.m_fireTimestamp)>=this.m_fireDelay) {
        this.m_fireTimestamp = dtm;
        
        // add bullet;
        GameEntity bullit;
        v3.setCoordinates(0, -(120/1000.0), 0);
        bullit = new Bullit01(this.getGame(),v3);
        bullit.getPosition().x = this.getPosition().x-3;
        bullit.getPosition().y = this.getPosition().y-13;
        ((Shooter)this.getGame()).getEntities().add(bullit);

        bullit = new Bullit01(this.getGame(),v3);
        bullit.getPosition().x = this.getPosition().x + 16 - 3;
        bullit.getPosition().y = this.getPosition().y-13;
        ((Shooter)this.getGame()).getEntities().add(bullit);
      }
    }
    if (keyboard.getState(KeyEvent.VK_X)) {
      if ((dtm-this.m_fireTimestamp)>=this.m_fireDelay) {
        this.m_fireTimestamp = dtm;
        
        // add bullet;
        GameEntity bullit;
        v3.setCoordinates(0, -(120/1000.0), 0);
        bullit = new Bullit01(this.getGame(),v3);
        bullit.getPosition().x = this.getPosition().x-3;
        bullit.getPosition().y = this.getPosition().y-13;
        ((Shooter)this.getGame()).getEntities().add(bullit);

        bullit = new Bullit01(this.getGame(),v3);
        bullit.getPosition().x = this.getPosition().x + 16 - 3;
        bullit.getPosition().y = this.getPosition().y-13;
        ((Shooter)this.getGame()).getEntities().add(bullit);

        bullit = new Bullit01(this.getGame(),v3);
        bullit.getPosition().x = this.getPosition().x + 5.5;
        bullit.getPosition().y = this.getPosition().y-13-7;
        ((Shooter)this.getGame()).getEntities().add(bullit);
      }
    }    
    if (keyboard.getState(KeyEvent.VK_C)) {
      if ((dtm-this.m_fireTimestamp)>=this.m_fireDelay) {
        this.m_fireTimestamp = dtm;
        
        // add bullet;
        GameEntity bullit;
        v3.setCoordinates(-(160/5000.0), -(120/1000.0), 0);
        bullit = new Bullit01(this.getGame(),v3);
        bullit.getPosition().x = this.getPosition().x-3;
        bullit.getPosition().y = this.getPosition().y-13;
        ((Shooter)this.getGame()).getEntities().add(bullit);

        v3.setCoordinates((160/5000.0), -(120/1000.0), 0);
        bullit = new Bullit01(this.getGame(),v3);
        bullit.getPosition().x = this.getPosition().x + 16 - 3;
        bullit.getPosition().y = this.getPosition().y-13;
        ((Shooter)this.getGame()).getEntities().add(bullit);

        v3.setCoordinates(0, -(120/1000.0), 0);
        bullit = new Bullit01(this.getGame(),v3);
        bullit.getPosition().x = this.getPosition().x + 5.5;
        bullit.getPosition().y = this.getPosition().y-13-7;
        ((Shooter)this.getGame()).getEntities().add(bullit);
      }
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
}
