package gameShooter;

public class Weapon {
  private ShooterEntity m_entity;

  private long m_fireTimestamp;
  private int m_fireDelay;
  private int m_count;
  private boolean m_autofire;
  
  public Weapon(ShooterEntity entity) {
    this.m_entity = entity;
    
    this.setFireDelay(500);
    this.setFireTimestamp(System.currentTimeMillis());
    this.setCount(-1);
    this.setAutofire(false);
  }
  
  public boolean fire() {
    long dtm = System.currentTimeMillis();
    if ((dtm-this.getFireTimestamp())>=this.getFireDelay()) {
      this.setFireTimestamp(dtm);
      return true;
    }
    return false;
  }  
  
  public void setCount(int count) {this.m_count=count;}
  public int getCount() {return this.m_count;}
  public void setFireDelay(int i) {this.m_fireDelay=i;}
  public int getFireDelay() {return this.m_fireDelay;}
  public void setFireTimestamp(long l) {this.m_fireTimestamp=l;}
  public long getFireTimestamp() {return this.m_fireTimestamp;}
  public void setAutofire(boolean b) {this.m_autofire=b;}
  public boolean getAutofire() {return this.m_autofire;}
  public ShooterEntity getGameEntity() {return this.m_entity;}
}
