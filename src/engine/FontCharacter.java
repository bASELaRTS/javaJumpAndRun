package engine;

import java.awt.image.BufferedImage;

public class FontCharacter {
  private String m_character;
  private BufferedImage m_image;
  private int m_width;
  private int m_height;
  
  public FontCharacter(String character, BufferedImage image) {
    this.setCharacter(character);
    this.setImage(image);
    this.setSize(image.getWidth(), image.getHeight());
  }
  
  private void setCharacter(String ch) {this.m_character=ch;}
  public String getCharacter() {return this.m_character;}
  private void setImage(BufferedImage image) {this.m_image=image;}
  public BufferedImage getImage() {return this.m_image;}
  private void setWidth(int i) {this.m_width=i;}
  public int getWidth() {return this.m_width;}
  private void setHeight(int i) {this.m_height=i;}
  public int getHeight() {return this.m_height;}
  private void setSize(int w, int h) {
    this.setWidth(w);
    this.setHeight(h);
  }
}