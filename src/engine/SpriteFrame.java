package engine;

public class SpriteFrame {
  private int m_frameDelay;
  private int m_imageIndex;
  
  public SpriteFrame(int imageIndex, int frameDelay) {
    this.setImageIndex(imageIndex);
    this.setFrameDelay(frameDelay);
  }
  
  public void setImageIndex(int i) {this.m_imageIndex=i;}
  public int getImageIndex() {return this.m_imageIndex;}
  public void setFrameDelay(int i) {this.m_frameDelay=i;}
  public int getFrameDelay() {return this.m_frameDelay;}
}
