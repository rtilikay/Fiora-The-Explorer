package ISP.classes;
import javax.swing.*;

/**
 * This class is used to time how long it takes the user to beat the game. 
 * 
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 * 
 * 
 */
public class Timer1 extends Thread
{
  /**
   * Current time.
   */
  private double time; 
  /**
   * Stores whether or not the timer should run.
   */
  private boolean end; 
  
  /**
   *  Stores the JFrame.
   */
  static JFrame f; 
  
  /**
   * Label that displays the time.
   */
  private JLabel timeDisplay;
  
  
  /**
   * Stores the layout.
   */
  static SpringLayout layout;
  
  /** 
   * Creates a Timer1 object with the given time and starts the timer. 
   * 
   *  <p> Creates a Timer1 object with the given time and starts the timer. 
   * 
   * @param time The time the timer should start at. 
   *  
   */
  public Timer1 (double time)
  {
    this.time = time; 
    timeDisplay = new JLabel (); 
    f = FioraGame.frame;
    layout = FioraGame.layout;
    f.add (timeDisplay);
  }
  
  /** 
   * Override of the run method in Thread. 
   * 
   * <p> This method is responsible for turning this class into a thread so it can run independently. 
   *  
   */
  public void run ()
  {
    timer ();  
  }
  
  
  /** 
   * The timer itself. 
   * 
   *  <p> This method increments time by .1 each 100 milliseconds.
   *  <p> It keeps running while end is false, when end is true it stops. 
   *  <p> It also updates the timer JLabel on the screen. 
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * e        InterruptedException   Stores the exception thrown.
   * <p>
   * e        NullPointerException   Stores the exception thrown.
   */
  private void timer ()
  {
    timeDisplay.setText ("Time: " + time);
    timeDisplay.setForeground (java.awt.Color.white);
        
    
        layout.putConstraint (SpringLayout.WEST, timeDisplay, 550, SpringLayout.WEST, f);
        layout.putConstraint (SpringLayout.NORTH, timeDisplay, 30, SpringLayout.NORTH, f);
    
    while (!end)
    {      
      time += 0.1;
      time = Math.round (time * 10.0) / 10.0;
      
      try 
      {
        timeDisplay.setText ("Time: " + time);
        f.validate (); 
        f.repaint ();
        
        sleep (100);  
      }
      catch (InterruptedException e)
      {
      }
      catch (NullPointerException e)
      {}
    }
  }
  
  /** 
   * Returns the current time.
   * 
   *  <p> Returns the current time.
   * 
   * @return Returns the current time.
   *  
   */
  public double getTime ()
  {
    return time; 
  }

  
  /** 
   * Stops the timer. 
   * 
   *  <p> Stops the timer by setting end to true and setting all reference variables to null.
   *  
   */
  public void stopTimer ()
  {
    end = true; 
    try
    {
    f.remove (timeDisplay);
    }
    catch (NullPointerException e)
    {
      
    }
    f = null; 
    layout = null; 
    timeDisplay = null;
  }
}
