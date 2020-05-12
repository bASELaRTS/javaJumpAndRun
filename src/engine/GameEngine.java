package engine;

public class GameEngine {
  private int m_width;
  private int m_height;
  private String m_key;
  private boolean m_close;
  
  private GameGraphics m_graphics;
  private Timer m_timer;
  private GameInput m_input;

  public GameEngine(String key, int width, int height) {
    this.m_graphics = new GameGraphics(width,height);
    this.m_timer = new Timer();
    this.m_input = new GameInput();
    this.m_close = false;
    
    this.setKey(key);
    this.setSize(width, height);
  }
  
  public void loop() {
    this.getTimer().update();
    this.update();
    this.paint();
  }
  
  public void update() {}
  public void paint() {
    int w = this.getWidth();
    int h = this.getHeight();
    int c = GameGraphics.getColor(255, 255, 0, 0);
    this.getGraphics().clear();
    this.getGraphics().drawLine(0, 0, w, h, c);
    this.getGraphics().drawLine(w, 0, 0, h, c);
  }
  
  public void close() {this.m_close=true;}
  public boolean isClosed() {return this.m_close;}
  
  public void setKey(String key) {this.m_key=key;}
  public String getKey() {return this.m_key;}
  
  protected void setWidth(int i) {this.m_width=i;}
  protected void setHeight(int i) {this.m_height=i;}
  protected void setSize(int w, int h) {
    this.setWidth(w);
    this.setHeight(h);
  }
  public int getWidth() {return this.m_width;}
  public int getHeight() {return this.m_height;}
  
  public GameGraphics getGraphics() {return this.m_graphics;}
  public Timer getTimer() {return this.m_timer;}
  public GameInput getInput() {return this.m_input;}
}
