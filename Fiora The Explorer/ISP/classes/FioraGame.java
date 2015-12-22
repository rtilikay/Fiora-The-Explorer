package ISP.classes;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.print.PrinterException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This class is responsible for creating the main menu, drop down menu, and listeners. It handles general flow.
 *
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 *
 *
 */
public class FioraGame extends JFrame implements ActionListener
{
  /**
   * The JPanel that is used to display the screens.
   */
  static JFrame frame;
  
  /*
   * Stores the instance of this class.
   */
  static FioraGame fg;
  /**
   * The GameGraphics object is used for adding images to the screens.
   */
  GameGraphics gg;
  
  /*
   * Points to the Game class.
   */
  static Game game; 
  /*
   * Points to the MainMenuOptions class.
   */
  MainMenuOptions m;
  /**
   * The SpringLayout used for this panel to organize things on the screen.
   */
  static SpringLayout layout;
  /**
   * The Database that stores the French words and highscores.
   */
  static DataBase data;

  /*
   * The pause JMenuItem.
   */
  JMenuItem pause;
  
  
  /**
   * Creates a FioraGame object, setting up a JFrame and displaying a splash screen.
   * <p> This constructor first creates an instance of JFrame and the Splash Screen.
   * <p> It then creates the JMenuItems and JMenus, adds them together, and adds ActionListeners.
   * <p> Then the window settings are set.
   *
   * <p> Variable Dictionary:
   * <p>
   * Name        Type                 Description
   * <p>
   * t           SplashWindow         The class that creates the splash screen.
   * <p>
   * pause       JMenuItem            The "pause" JMenuItem.
   * <p>
   * about       JMenuItem            The "about" JMenuItem.
   * <p>
   * quit        JMenuItem            The "quit" JMenuItem.
   * <p>
   * print       JMenuItem            The "Print" JMenuItem.
   * <p>
   * file        JMenu                The "File" JMenu.
   * <p>
   * help        JMenu                The "Help" JMenu.
   * <p>
   * menuBar     JMenuBar             The JMenuBar that holds the JMenus.
   *
   */
  public FioraGame ()
  {
    super ("Fiora The Explorer");
    frame = this;
    fg = this;
    SplashWindow1 t = new SplashWindow1 ("ISP/pictures/splash.gif",this);
    data = new DataBase ();
    pause = new JMenuItem ("Pause (F1)");
    JMenuItem about = new JMenuItem ("About (F3)");
    JMenuItem helpFile = new JMenuItem ("Help (F10)");
    JMenuItem quit = new JMenuItem ("Quit (F4)");
    JMenuItem print = new JMenuItem ("Print (F2)");
    JMenu file = new JMenu ("File");
    JMenu help = new JMenu ("Help");
    JMenuBar menuBar = new JMenuBar ();
    
    file.add (pause);
    file.add (print);
    file.add (quit);
    help.add (about);
    help.add (helpFile);
    menuBar.add (file);
    menuBar.add (help);
    setJMenuBar (menuBar);
    
    pause.addActionListener (this);
    about.addActionListener (this);
    print.addActionListener (this);
    quit.addActionListener (this);
    helpFile.addActionListener (this);
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    manager.addKeyEventDispatcher(new MyDispatcher());
    
    gg = new GameGraphics ();
    
    setVisible (true);
    setSize (640, 530);
    setResizable (false);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  }
  
  
  /**
   *  This method creates the mainMenu and lets the user choose what to do.
   *
   *  <p> The main menu creates a menu in the panel and lets the user choose what they want to do.
   *  <p> It first creates the menu buttons.
   *  <p> It then adds visuals and buttons to the panel.
   *  <p> It then sets the constraints of the buttons and visuals (SpringLayout).
   *  <p> It then adds the ActionListeners to the buttons which all the corresponding method when pressed.
   *
   * <p> Variable Dictionary:
   * <p>
   * Name        Type                 Description
   * <p>
   * layout      SpringLayout         The SpringLayout used for this panel.
   * <p>
   * newGame     JButton              The "New Game" menu button.
   *<p>
   * practice    JButton              The "Practice" menu button.
   *<p>
   *instructions JButton              The "Instructions" menu button.
   * <p>
   * highScores  JButton              The "High Scores" menu button.
   *<p>
   * back        JLabel               The background.
   * <p>
   * fiora       JLabel               Picture of Fiora.
   * <p>
   * text        JLabel               Picture of the title.
   *
   */
  public void mainMenu ()
  {
    pause.setEnabled (false);
    getContentPane().removeAll();
    layout = new SpringLayout ();
    setLayout (layout);
    m = new MainMenuOptions ();
    game = null;
    
    JLabel back = gg.getImage ("background1.png");
    JLabel fiora = gg.getImage ("menuFiora.gif");
    JLabel text = gg.getImage ("fioraText.gif");
    JButton newGame = new JButton ("New Game");
    JButton practice = new JButton ("Practice");
    JButton instructions = new JButton ("Instructions");
    JButton highScores = new JButton ("High Scores");
    JButton quit = new JButton ("Quit");
    add (newGame);
    add (practice);
    add (instructions);
    add (highScores);
    add (quit);
    add (text);
    add (fiora);
    add (back);
    
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, newGame, 280, SpringLayout.HORIZONTAL_CENTER, this);
    layout.putConstraint (SpringLayout.NORTH, newGame, 150, SpringLayout.NORTH, this);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, practice, 0, SpringLayout.HORIZONTAL_CENTER, newGame);
    layout.putConstraint (SpringLayout.NORTH, practice, 50, SpringLayout.NORTH, newGame);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, instructions, 0, SpringLayout.HORIZONTAL_CENTER, practice);
    layout.putConstraint (SpringLayout.NORTH, instructions, 50, SpringLayout.NORTH, practice);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, highScores, 0, SpringLayout.HORIZONTAL_CENTER, instructions);
    layout.putConstraint (SpringLayout.NORTH, highScores, 50, SpringLayout.NORTH, instructions);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, quit, 0, SpringLayout.HORIZONTAL_CENTER, highScores);
    layout.putConstraint (SpringLayout.NORTH, quit, 50, SpringLayout.NORTH, highScores);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, text, 280, SpringLayout.HORIZONTAL_CENTER, this);
    layout.putConstraint (SpringLayout.NORTH, text, 0, SpringLayout.NORTH, this);
    layout.putConstraint (SpringLayout.NORTH, fiora, 100, SpringLayout.NORTH, this);
    validate ();
    repaint ();
    newGame.addActionListener (new ActionListener ()
                                 {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        game = new Game ();
        
      }
      
    }
    );
    practice.addActionListener (new ActionListener ()
                                  {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        m.practice ();
        
      }
      
    }
    );
    instructions.addActionListener (new ActionListener ()
                                      {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        m.instructions ();
      }
      
    }
    );
    highScores.addActionListener (new ActionListener ()
                                    {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        m.highscores ();
        
      }
      
    }
    );
    quit.addActionListener (new ActionListener ()
                              {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        m.quit ();
      }
      
    }
    );
  }
  
  /**
   * This method is part of ActionListener and decides what to do when something is pressed.
   *
   * <p> This method is part of ActionListener and decides what to do when something is pressed.
   * <p> If "Quit (F4)" is pressed, the game exits.
   * <p> If "About (F3)" is pressed, a message is displayed to the user.
   * <p> If "Print (F2)" is pressed, the high scores are printed.
   * <p> If the "Help (F10)" button is pressed, the help file is opened. 
   * <p> Otherwise it pauses the game if it's running.
   * 
   * @param ae The ActionEvent that occured.
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ().equals ("Quit (F4)"))
    {
      if (game != null)
        game.pause = true;
      int intValue = JOptionPane.showConfirmDialog (frame, "Are you sure you want to quit?", "Fiora The Explorer", JOptionPane.YES_NO_OPTION);
      if (game != null)
        game.resume ();
      if (intValue == JOptionPane.NO_OPTION)
        return; 
      m.quit ();
    }
    else if (ae.getActionCommand ().equals ("About (F3)"))
    {
      Dialogs d = new Dialogs ("About", 2);
      d.start ();
    }
    else if (ae.getActionCommand ().equals ("Print (F2)"))
    {
      Dialogs d = new Dialogs ("", 3);
      d.start ();
    }
    else if (ae.getActionCommand ().equals ("Help (F10)"))
    {
      try
        {
        Runtime.getRuntime().exec("hh.exe ISP/helpfile.chm");
        }
        catch (Exception ex)
        {
        } 
    }
    
    else
    {
      if (ae.getActionCommand ().equals ("Pause (F1)") && game != null)
      {
        Dialogs d = new Dialogs ("Pause", 1);
        d.start ();
      }
    }
  }
  
  
  /**
   * This is the main method that starts the program by creating an instance of this class.
   *
   * <p> This is the main method that starts the program by creating an instance of this class.
   *
   * <p> Variable Dictionary:
   * <p>
   * Name        Type         Description
   * <p>
   * f           FioraGame    Reference variable for the FioraGame object.
   *
   * @param args Stores the arguments passed into this method.
   *
   */
  public static void main (String[] args)
  {
    FioraGame f = new FioraGame ();
    f.mainMenu ();
  }
  
  /* This class does the exact same thing as the actionPerformed class but with keys. 
   * If f1 is pressed, same thing happens as pause. 
   * If f2 is pressed, same thing happens as print. 
   * If f3 is pressed, same thing happens as about. 
   * If f4 is pressed, same thing happens as quit. 
   * If f10 is pressed, same thing happens as helpFile.
   * 
   * 
   * Code from: http://stackoverflow.com/questions/286727/java-keylistener-for-jframe-is-being-unresponsive
   * 
   * 
   */
  private class MyDispatcher implements KeyEventDispatcher {
    public boolean dispatchKeyEvent(KeyEvent e) {
      if (e.getID() == KeyEvent.KEY_PRESSED)
      {
        if (e.getKeyCode() == 112) 
        {
          if (game != null && game.isMidLevel ())
          {
            Dialogs d = new Dialogs ("Pause", 1);
            d.start ();
          }
        }
        else if (e.getKeyCode() == 113)
        {
          Dialogs d = new Dialogs ("", 3);
          d.start (); 
        }
        
        else if (e.getKeyCode () == 114)
        {
          Dialogs d = new Dialogs ("About", 2);
          d.start ();
        }
        else if (e.getKeyCode () == 121)
        {
        try
        {
        Runtime.getRuntime().exec("hh.exe ISP/helpfile.chm");
        }
        catch (Exception ex)
        {
        } 
        }
        else 
        {
          if (e.getKeyCode () == 115)
          {
            if (game != null)
              game.pause = true;
            int intValue = JOptionPane.showConfirmDialog (frame, "Are you sure you want to quit?", "Fiora The Explorer", JOptionPane.YES_NO_OPTION);
            if (game != null)
              game.resume ();
            if (intValue == JOptionPane.NO_OPTION)
              return false; 
            m.quit ();
          }
        }
        
      }
      return false;
    }
  }
}
