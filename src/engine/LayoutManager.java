package engine;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class LayoutManager {
  private Vector<Block> m_blocks;
  private BufferedImage m_image;
  
  public LayoutManager() {
    this.m_blocks = new Vector<Block>();
  }
  
  public LayoutManager(String filename){
    this.m_blocks = new Vector<Block>();
    this.load(filename);
  }
  
  public void load(String filename) {
    this.load(new java.io.File(filename));
  }
  public void load(java.io.File file) {
    java.io.FileReader reader;
    java.io.BufferedReader stream;
    
    this.m_blocks.clear();
    
    try {
      reader = new FileReader(file);
      stream = new BufferedReader(reader);  
      
      int count = Integer.parseInt(stream.readLine());
      for(int i=0;i<count;i++) {
        String line = stream.readLine();
        String strs[] = line.split(";");

        Block block = new Block();
        if (strs.length==5) {
          block.setName(strs[0]);
          block.setX(Integer.parseInt(strs[1]));
          block.setY(Integer.parseInt(strs[2]));
          block.setWidth(Integer.parseInt(strs[3]));
          block.setHeight(Integer.parseInt(strs[4]));          
        } else if (strs.length==6) {
          block.setName(";");
          block.setX(Integer.parseInt(strs[2]));
          block.setY(Integer.parseInt(strs[3]));
          block.setWidth(Integer.parseInt(strs[4]));
          block.setHeight(Integer.parseInt(strs[5]));          
        }
        
        this.add(block);
      }
      
      stream.close();
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
  
  public void save(String filename) {
    this.save(new java.io.File(filename));
  }
  public void save(java.io.File file) {
    java.io.FileWriter writer;
    java.io.BufferedWriter stream;
    
    try {
      writer = new FileWriter(file);
      stream = new BufferedWriter(writer);    
      
      stream.write(""+this.m_blocks.size()+"\r\n");
      for(int i=0;i<this.m_blocks.size();i++) {
        Block block = this.m_blocks.elementAt(i);
        stream.write(
            block.getName()
            + ";" + block.getX()
            + ";" + block.getY()
            + ";" + block.getWidth()
            + ";" + block.getHeight() 
            + "\r\n" 
        );
      }
      
      stream.close();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
  
  public void add(String name, int x, int y, int w, int h) {
    Block block = new Block(name,x,y,w,h);
    this.add(block);
  }
  
  public void add(Block block) {
    if (block!=null) {
      this.m_blocks.add(block);
    }
  }
  
  public Block getBlock(int index) {
    if ((this.m_blocks.size()>0)&&(index<this.m_blocks.size())) {
      return this.m_blocks.elementAt(index);
    }
    return null;
  }
  
  public Block getBlock(String name) {
    int i;
    Block b;
    for(i=0;i<this.m_blocks.size();i++) {
      b = this.m_blocks.elementAt(i);
      if (b.getName().equals(name)) {
        return b;
      }
    }
    return null;
  }

  public Vector<Block> getBlocks(){return this.m_blocks;}
  public void setImage(BufferedImage image) {
    this.m_image=image;
    
    if (this.m_image!=null) {
      int i;
      int x,y,w,h;
      Block b;
      for(i=0;i<this.m_blocks.size();i++) {
        b = this.m_blocks.elementAt(i);
        x = b.getX();
        y = b.getY();
        w = b.getWidth();
        h = b.getHeight();
        
        BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics graphics = img.getGraphics();
        graphics.drawImage(image,0,0,w,h,x,y,x+w,y+h,null);
        b.setImage(img);
      }      
    }
  }
  public BufferedImage getImage() {return this.m_image;}
    
  public class Block {
    private String m_name;
    private int m_xposition;
    private int m_yposition;
    private int m_width;
    private int m_height;
    private BufferedImage m_image;
    
    public Block() {
      this.setName("");
      this.setSize(0, 0);
      this.setXY(0, 0);
    }
    public Block(String name, int x, int y, int w, int h) {
      this.setName(name);
      this.setSize(0, 0);
      this.setXY(0, 0);
    }
    
    public void setSize(int w, int h) {
      this.setWidth(w);
      this.setHeight(h);
    }
    public void setWidth(int i) {this.m_width=i;}
    public int getWidth() {return this.m_width;}
    public void setHeight(int i) {this.m_height=i;}
    public int getHeight() {return this.m_height;}
    
    public void setXY(int x, int y) {
      this.setX(x);
      this.setY(y);
    }
    public void setX(int i) {this.m_xposition=i;}
    public int getX() {return this.m_xposition;}
    public void setY(int i) {this.m_yposition=i;}
    public int getY() {return this.m_yposition;}
        
    public void setName(String s) {this.m_name=s;}
    public String getName() {return this.m_name;}
    public void setImage(BufferedImage image) {this.m_image=image;}
    public BufferedImage getImage() {return this.m_image;}
  }
}
