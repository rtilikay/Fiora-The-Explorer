package ISP.classes;
import java.awt.*;
import javax.swing.*; 


/**
 * This class is used to get pictures (Most features were removed).
 * 
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 * 
 */
public class GameGraphics
{   
  
  /** 
   * Opens and returns an Image.
   * 
   *  <p> Opens and returns an Image with the given name or null if an error occurs. 
   * 
   *  @param name The filename of the picture to be returned. 
   *  @return The image with the given filename. 
   *  
   */
  public JLabel getImage (String name)
  {
    return new JLabel (new ImageIcon (this.getClass().getClassLoader().getResource("ISP/pictures/"+name)));
  }
}
