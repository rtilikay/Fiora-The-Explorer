package ISP.classes;
import javax.swing.*;
import java.awt.*;


/**
 * This class displays the splashscreen at the beginning of the program.
 * @author Jonah Haber
 * @author Reyno Tilikaynen
 * @author Tony Colston
 * @version 1.0 June 11th 2013
 * Thanks to Tony Colston for his code which I used as a baseline.
 */
class SplashWindow1 extends JWindow
{
  
    /** 
   * Displays the splash screen and after 5 seconds goes to the program itself. 
   * 
   *  <p> Creates the splash screen by creating a JWindow, loading the image and displaying it in the middle of the screen. 
   *  <p> The splash screen lasts 5 seconds and then dissapears. 
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type                 Description
   * <p>
   * l           JLabel               Stores the image with the given filename. 
   * screenSize  Dimensions           Stores the dimensions of the screen. 
   * labelSize   Dimensions           Stores the dimensions of the image. 
   * sd          InterruptedException Stores the exception thrown by the Thread.sleep () method. 
   * 
   * @param filename The name of image to be displayed. 
   * @param f The JFrame this splash screen is associated to. 
   *  
   */
    public SplashWindow1(String filename, JFrame f)
    {
        super(f);
        JLabel l = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource(filename)));
        getContentPane().add(l, BorderLayout.CENTER);
        pack();
        Dimension screenSize =
          Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = l.getPreferredSize();
        setLocation(screenSize.width/2 - (labelSize.width/2),
                    screenSize.height/2 - (labelSize.height/2));
        setVisible(true);
        screenSize = null;
        labelSize = null;
        try{
          Thread.sleep (5000);}
        catch (InterruptedException sd){}
        setVisible (false);
    }
}