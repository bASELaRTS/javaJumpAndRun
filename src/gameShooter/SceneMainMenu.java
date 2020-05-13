package gameShooter;

import java.awt.event.KeyEvent;

import engine.Font;
import engine.GameEngine;
import engine.GameGraphics;
import engine.InputKeyboard;
import engine.InputKeyboardKey;
import engine.LayoutManager;

public class SceneMainMenu extends Scene {
  private Font m_fontTitle;
  private Font m_fontSmall;
  
  private int m_option;
  
  public SceneMainMenu(GameEngine engine) {
    super(engine,"scenemainmenu");
    
    this.m_fontTitle = new Font();
    this.m_fontTitle.load(GameGraphics.getImage("data/font14_1bpl_08x16.png"), new LayoutManager("data/font14_1bpl_08x16.layout"));
    
    this.m_fontSmall = new Font();
    this.m_fontSmall.load(GameGraphics.getImage("data/charmap-oldschool_transparent.png"), new LayoutManager("data/charmap-oldschool_white.layout"));

    this.getEntities().add(new Background(this.getGameEngine(),this));
    
    this.getGameEngine().getInput().getKeyboard().add(new InputKeyboardKey(KeyEvent.VK_ENTER));
  }
  
  public void activate() {
    this.m_option = 0;
  }
  
  public void update() {
    super.update();
    Scene scene;
    Shooter engine = (Shooter)this.getGameEngine();
    InputKeyboard keyboard = this.getGameEngine().getInput().getKeyboard();
    if (keyboard.getState(KeyEvent.VK_ENTER)) {
      keyboard.setKeys(false);
      if (this.m_option==0) {
        scene = engine.getScenes().getScene("scenegame");
        engine.getScenes().activate(scene);
      } else if (this.m_option==1) {
        scene = engine.getScenes().getScene("sceneoutro");
        engine.getScenes().activate(scene);
      }
    } else if (keyboard.getState(KeyEvent.VK_UP)) {
      this.m_option--;
      if (this.m_option<0) this.m_option=0;
    } else if (keyboard.getState(KeyEvent.VK_DOWN)) {
      this.m_option++;
      if (this.m_option>1) this.m_option=1;
    }    
  }
  
  public void paint() {
    super.paint();
    
    GameGraphics graphics = this.getGameEngine().getGraphics();
    
    String s;
    int i,c,x,y,w,h;
    i = this.m_fontTitle.measureString("#");
    s = "INFINITY";
    w = this.m_fontTitle.measureString(s);
    x = (int)((this.getGameEngine().getWidth() - w)*0.5);
    graphics.drawFont(s, x, 16, this.m_fontTitle);
    s = "SHOOTER";
    w = this.m_fontTitle.measureString(s);
    x = (int)((this.getGameEngine().getWidth() - w)*0.5);
    graphics.drawFont(s, x, 34, this.m_fontTitle);
    graphics.drawFont("#",x-i-2,24,this.m_fontTitle);
    graphics.drawFont("#",x+w+2,24,this.m_fontTitle);
    
    w = this.getGameEngine().getWidth();
    h = 9;
    x = 0;
    y = (int)((this.getGameEngine().getHeight()*0.5)-1);
    c = GameGraphics.getColor(128, 255, 0, 0); 
    graphics.fillRect(x, y + (this.m_option*9), w, h, c);
    
    s = "START";
    w = this.m_fontSmall.measureString(s);
    x = (int)((this.getGameEngine().getWidth()-w)*0.5);
    y = (int)((this.getGameEngine().getHeight()*0.5));
    graphics.drawFont(s, x, y, this.m_fontSmall);    
    s = "EXIT";
    w = this.m_fontSmall.measureString(s);
    x = (int)((this.getGameEngine().getWidth()-w)*0.5);
    y = (int)((this.getGameEngine().getHeight()*0.5)+9);
    graphics.drawFont(s, x, y, this.m_fontSmall);        
  }
}
