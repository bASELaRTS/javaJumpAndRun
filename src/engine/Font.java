package engine;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class Font {
  private Vector<FontCharacter> m_characters;
  private int m_defaultCharacterWidth;
  private int m_characterSpacing;
  
  public Font() {
    this.m_characters = new Vector<FontCharacter>();
    this.setDefaultCharacterWidth(8);
    this.setCharacterSpacing(1);
  }
  
  public void load(BufferedImage image, LayoutManager layout) {
    FontCharacter character;
    for(int i=0;i<layout.getBlocks().size();i++) {
      LayoutManager.Block b = layout.getBlock(i);
      character = new FontCharacter(
          "" + ((char)Integer.parseInt(b.getName()))
          ,GameGraphics.getImage(image,b.getX(),b.getY(),b.getWidth(),b.getHeight())
      );
      this.getCharacters().add(character);      
    }
  }
  
  public int measureString(String str) {
    int i,x,w;
    String s;
    FontCharacter character;
    w = 0;
    for(i=0;i<str.length();i++) {
      s = "" + str.charAt(i);
      character = this.getCharacter(s);
      x = this.getDefaultCharacterWidth();
      if (character!=null) {
        x = character.getWidth();
      }
      x += this.getCharacterSpacing();
      w += x;
    }
    return w;
  }
  
  public FontCharacter getCharacter(String s) {
    FontCharacter ch;
    for(int i=0;i<this.m_characters.size();i++) {
      ch=this.m_characters.elementAt(i);
      if (ch.getCharacter().equals(s)) {
        return ch;
      }
    }
    return null;
  }
  
  public void setDefaultCharacterWidth(int i) {this.m_defaultCharacterWidth=i;}
  public int getDefaultCharacterWidth() {return this.m_defaultCharacterWidth;}
  public void setCharacterSpacing(int i) {this.m_characterSpacing=i;}
  public int getCharacterSpacing() {return this.m_characterSpacing;}
  public Vector<FontCharacter> getCharacters(){return this.m_characters;}
}
