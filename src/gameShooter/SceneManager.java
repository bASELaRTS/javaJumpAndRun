package gameShooter;

import java.util.Vector;

public class SceneManager {
  private Vector<Scene> m_list;
  private int m_sceneIndex;
  
  public SceneManager() {
    this.m_list = new Vector<Scene>();
    this.m_sceneIndex = -1;
  }
  
  public void add(Scene scene) {
    if (scene!=null) {
      this.m_list.add(scene);
      
      // active first scene
      if (this.m_list.size()==1) {
        this.activate(0);
      }
    }
  }
  
  public void activate(int index) {
    Scene scene = this.getScene(index);
    if (scene!=null) {
      this.m_sceneIndex = index;
      scene.activate();
    }    
  }
  
  public void activate(Scene scene) {
    for(int i=0;i<this.m_list.size();i++) {
      Scene s = this.m_list.elementAt(i);
      if (s.equals(scene)) {
        this.activate(i);
        return;
      }
    }    
  }
    
  public void update() {
    Scene scene = this.getScene();
    if (scene!=null) {
      scene.update();
    }
  }
  
  public void paint() {
    Scene scene = this.getScene();
    if (scene!=null) {
      scene.paint();
    }
  }
  
  public Scene getScene(String name) {
    Scene scene;
    for(int i=0;i<this.m_list.size();i++) {
      scene = this.getScene(i);
      if (scene.getName().equals(name)) {
        return scene;
      }
    }
    return null;
  }
  
  public Scene getScene() {
    return this.getScene(this.m_sceneIndex);
  }
  
  public Scene getScene(int index) {
    if (index>=0) {
      return this.m_list.elementAt(index);
    }
    
    return null;
  }
}
