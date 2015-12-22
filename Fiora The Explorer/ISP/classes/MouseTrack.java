package ISP.classes;
import javax.swing.*; 
import java.awt.*; 

/**
 * This class is used to time how long it takes the user to beat the game. 
 * 
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 * 
 * 
 */
public class MouseTrack extends Thread
{
  /**
   * Stores the button that will be following the mouse.
   */
  private JButton button; 
  /**
   * Stores the JFrame.
   */
  static JFrame frame;  
  
  /**
   *  Stores the location of the mouse.
   */
  private Point mouseLocation; 
  
  /*
   * Stores the background.
   */
  private JLabel background;
  
  /*
   * Stores the layout.
   */ 
  static SpringLayout layout; 
  
  /*
   * Stores whether this Thread should be running or not.
   */
  private boolean run = true; 
  
  /** 
   * Creates a MouseTrack object. 
   * 
   *  <p> Creates a MouseTrack object that tracks the given button.
   * 
   * @param button The button that will track your mouse.
   * @param background The background of the screen.
   *  
   */
  public MouseTrack (JButton button, JLabel background)
  {
    this.button = button;
    frame = FioraGame.frame; 
    layout = FioraGame.layout; 
    this.background = background;
  }
  
  /* This method overrides the run in Thread to move the button with the mouse.
   * 
   * <p> This method overrides the run in Thread to move the button with the mouse.
   * <p> It tries to remove the button and the background and re-add it at the mouse position.
   * <p> It does this in a loop while run is true.
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * e       NullPointerException  Stores the exception thrown.
   * <p> 
   * windowLoc   Point             Stores the location of the window.
   * 
   */
  public void run ()
  {
    while (run)
    {
      try
      {
      button.setDoubleBuffered (true);
      background.setDoubleBuffered (true);
      layout.removeLayoutComponent (button);
      frame.remove (button); 
      frame.remove (background);
      frame.add (button);
      frame.add (background);
      mouseLocation = MouseInfo.getPointerInfo ().getLocation ();
      Point windowLoc = frame.getLocation ();
      mouseLocation = new Point ((int) (mouseLocation.getX () - windowLoc.getX ()), (int) (mouseLocation.getY () - windowLoc.getY ()));
      layout.putConstraint (SpringLayout.WEST, (JButton) button, (int) mouseLocation.getX () - 25, SpringLayout.WEST, frame);
      layout.putConstraint (SpringLayout.NORTH, (JButton) button, (int) mouseLocation.getY () - 60, SpringLayout.NORTH, frame);
      frame.validate ();
      frame.repaint ();
      Thread.sleep (20);
      }
      catch (NullPointerException e)
      {
        
      }
      catch (InterruptedException e)
      {
        
      }
    }
  }
  
  /** 
   * Stops the mouse track. 
   * 
   *  <p> Stops the mouse track by sending run to false.
   *  
   */
  public void stopTrack ()
  {
    run = false; 
  }
}
