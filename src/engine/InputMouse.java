package engine;

public class InputMouse {
  public static int LEFT = 0;
  public static int RIGHT = 1;
  public static int MIDDLE = 2;
  
  private boolean m_buttons[];
  private int m_xposition;
  private int m_yposition;
  
  public InputMouse() {
    this.m_buttons = new boolean[3];
    this.setXY(0, 0);
  }
  
  public void setX(int i) {this.m_xposition=i;}
  public int getX() {return this.m_xposition;}
  public void setY(int i) {this.m_yposition=i;}
  public int getY() {return this.m_yposition;}
  public void setXY(int x, int y) {
    this.setX(x);
    this.setY(y);
  }
  
  public void setButton(int button, boolean state) {
    if ((button>=0)&&(button<3)) {
      this.m_buttons[button]=state;
    }
  }
  public boolean getButton(int button){return this.m_buttons[button];}
}
