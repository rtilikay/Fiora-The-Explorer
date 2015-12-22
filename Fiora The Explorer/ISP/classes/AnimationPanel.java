package ISP.classes;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;

 /**
   * This Panel displays the flash intro to the grame.
   * 
   * @author Hsin-Yen Liu
   * @author Edwin Ng
   * @author Jonah Haber
   * 
   * @version  4.0 May 30, 2013
   */

public class AnimationPanel extends JPanel
{
  /**
   * Stores the image that will move across the screen
   */
  private BufferedImage image;
  /**
   * Stores the background image
   */
  private BufferedImage background; 
    /**
   * Stores the offset amount 
   */
  private int offset;
    /**
   * Stores the distance travelled 
   */
  private int distance;
   /**
   * Stores whether the image goes left or right
   */
  private boolean left;
    /**
   * Stores the x location
   */
  private int locationx;
    /**
   * Stores the y location
   */
  private int locationy;
    /**
   * Stores an instance of the Gamegraphics class
   */
  GameGraphics g;
  /**
   * This constructor creates a JPanel, ns well as an image based
   * on the imagePath. It also gives all of the location, offset, and distance variables a value
   * 
   * 
   * @param backgroundPath the path of the background 
   * @param locationx stores the x location
   * @param locationy stores the y location
   * @param distance stores the distance being travelled 
   * @param left1 stores whether the object is moving left 
   * @param imagePath the path of the image
   */
  public AnimationPanel (String imagePath,String backgroundPath,int locationx,int locationy,int distance,boolean left1)
  {
    g= new GameGraphics();
    setSize (640, 480);
    this.distance=distance;
    left=left1;
    this.locationx=locationx;
    this.locationy=locationy;
    try 
    {
      image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("ISP/pictures/"+imagePath));
      background = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("ISP/pictures/"+backgroundPath));
    } catch (IOException e) 
    {System.out.print (1);}
    
  }
  /**
   * This method animates one image and makes it move across the screen.
   * A for loop is used to change the image's position.
   * 
   * @exception e an InteruptedException is thrown when the thread is interrupted
   */
  public void showAnimation ()
  {
    if (left){
    for (offset=distance; offset>=0; offset-=5)
    {
      try
      {
        paintComponents (getGraphics());
        Thread.sleep (50);
      }
      catch (InterruptedException e)
      {
        
      }}}
    else{
      for (offset=0; offset<distance; offset+=5)
    {
      try
      {
  paintComponents (getGraphics());
        Thread.sleep (50);
      
      }
      catch (InterruptedException e)
      {
        
      }}
      
    } 
  }
  /**
   * This method repaints the JPanel, allowing the animation to show
   * 
   * @param g the Graphics of the JPanel.
   */
  public void paintComponents (Graphics g)
  {
      ((Graphics2D)g).drawImage(background, 0, 0, null);
      ((Graphics2D)g).drawImage(image, locationx+offset, locationy, null); 


  }
  
  
  
  
  
}