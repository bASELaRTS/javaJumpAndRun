package engine;

public class Timer {
  private int m_elapsed;
  private long m_elapsedTimestamp;
    
  private int m_fps;
  private int m_fpsCounter;
  private long m_fpsTimestamp;
  
  private int m_fpsIndex;
  private int m_fpsArray[];
  
  public Timer() {
    long dtm = System.currentTimeMillis();
    this.m_elapsed = 0;
    this.m_elapsedTimestamp = dtm;
    
    this.m_fps = 0;
    this.m_fpsCounter = 0;
    this.m_fpsTimestamp = dtm;
    
    this.m_fpsIndex = 0;
    this.m_fpsArray = new int[10];
  }
  
  public void update() {
    long dtm = System.currentTimeMillis();
    
    this.m_elapsed = (int)(dtm-this.m_elapsedTimestamp);
    this.m_elapsedTimestamp = dtm;
    
    if ((dtm-this.m_fpsTimestamp)>=1000) {
      this.m_fpsTimestamp = dtm;
      this.m_fps = this.m_fpsCounter;
      
      this.m_fpsIndex=(this.m_fpsIndex+1)%this.m_fpsArray.length;
      this.m_fpsArray[this.m_fpsIndex]=this.m_fpsCounter;
      
      this.m_fpsCounter = 0;
    }
    this.m_fpsCounter++;
  }
  
  public int getElapsed() {return this.m_elapsed;}
  public int getFPS() {return this.m_fps;}
  public int getFPSAverage() {
    int i,t;
    t = 0;
    for(i=0;i<this.m_fpsArray.length;i++) {
      t+=this.m_fpsArray[i];
    }
    return (t/this.m_fpsArray.length);
  }
}
