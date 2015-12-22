package ISP.classes;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.util.ArrayList;
import java.awt.print.PrinterException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import ISP.classes.database.*;


/**
 * This class is responsible for creating the dialogs.
 *
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 *
 *
 */
public class Dialogs extends Thread
{
   /*
    * Stores the JFrame.
    */
   static JFrame f;
   /*
    * Stores the title of the JDialog.
    */
   String title; 
   /*
    * Stores the instance of FioraGame.
    */
   static FioraGame fg;
   /*
    * Stores the instance of Game.
    */
   static Game g;
   /*
    * The JDialog window.
    */
   JDialog d;
   /*
    * Stores which window to open.
    */
   int whichWindow;
   /*
    * Stores the database. 
    */
   static DataBase data;
   
   /** Creates an instance of JDialog. 
     *
    * <p> Constructs an instance of JDialog and sets the variables. 
    * 
    * @param title The title of the JDialog. 
    * @param whichWindow Which window should be opened. 
    */
   public Dialogs (String title, int whichWindow)
   {
     this.title = title; 
     this.whichWindow = whichWindow;
     data = FioraGame.data;
     fg = FioraGame.fg; 
     f = FioraGame.frame;
     g = FioraGame.game;
   }
   
   /** Overrides run in Thread to run the appropriate method. 
    *
    * <p> Overrides run in Thread to run the appropriate method. 
    * <p> It first creates the dialog window and makes sure the game unpauses if it's paused and the user closes the window.
    * <p> Then if whichWindow is 1, it calls the pauseMenu, if 2 the aboutMenu, if 3 the printMenu, otherwise the areYouSure.
    * 
    */
   public void run ()
   {
     d = new JDialog (f, title);
     d.addWindowListener (new WindowAdapter ()
     {
          public void windowClosing (WindowEvent w)
  {
    if (g != null)
      g.resume ();
    
  } 
     });
     if (whichWindow == 1)
       pauseMenu (); 
     else if (whichWindow == 2)
       about (); 
     else if (whichWindow == 3)
       printHighScores ();
     else
       areYouSure ();
   }
  
   /**
   * Displays a pause menu when the user is in-game.
   *
   *  <p> Displays a pause menu when the user is in-game.
   *  <p> If the user decides to return to main menu, an areYouSure prompt appears. If yes, returns, else no. 
   * 
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type                    Description
   * <p> 
   * closeButton   JButton               The close button.
   * <p> 
   * returnButton  JButton               The return to main menu button.
   *
   */
  private void pauseMenu ()
  {
    d.setSize (150, 100);
    d.setResizable (false);
    d.setLayout (new FlowLayout ());
    JButton closeButton = new JButton ("Close");
    d.add (closeButton);
    JButton returnButton = new JButton ("Return to Menu");
    d.add (returnButton);
    
    
    closeButton.addActionListener (new ActionListener ()
                                     {
      /** This method closes the dialog box.
        */
      public void actionPerformed (ActionEvent e)
      {
        d.dispose ();
        g.resume ();
      }
    }
    );
    
    returnButton.addActionListener (new ActionListener ()
                                      {
      /** This method closes the dialog box.
        */
      public void actionPerformed (ActionEvent e)
      {
        int intValue = JOptionPane.showConfirmDialog (f, "Are you sure? Your progress won't be saved!", "Fiora The Explorer", JOptionPane.YES_NO_OPTION);
        if (intValue == JOptionPane.NO_OPTION)
          return; 

        d.dispose ();
        g.end ();
      }
    }
    );
    
    d.setLocationRelativeTo (f);
    d.setVisible (true);
    g.pause ();
  }
  
  
  /**Displays an about dialog box
   * 
   * <p> Displays an about dialog box that closes when the user presses the close button. 
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type                    Description
   * <p> 
   * helpButton   JButton                The close button.
   */
  private void about ()
  {
    JLabel cont;
    
   
    d.setSize (500, 100);
    d.setResizable (false);
    d.setLayout (new FlowLayout ());
    cont = new JLabel ("This program was made by Jonah Haber and Reyno Tilikaynen. © 2013 V3.0");
    cont.setFont (new Font ("Serif", Font.PLAIN, 16));
    d.add (cont);
    JButton helpButton = new JButton ("Close");
    d.add (helpButton);
    
    helpButton.addActionListener (new ActionListener ()
                                    {
      /** This method closes the dialog box.
        */
      public void actionPerformed (ActionEvent e)
      {
        d.dispose ();
        if (g != null) 
          g.resume ();
      }
    }
    );
    d.setLocationRelativeTo (f);
    d.setVisible (true);
    if (g != null)
      g.pause ();
  }
  
  
  /**
   * Prints the highscore.
   * 
   * <p> Prints the highscore. 
   * <p> First sets up the highscore String that will be printed with HTML using a for loop that's 0-9 increment 1.  
   * <p> Then runs the JTextPane print dialog, which takes over. 
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type                    Description
   * <p> 
   * dateFormat  DateFormat              Stores the date.
   * <p> 
   * scores      ArrayList <HighScore>   Stores the high scores.
   * <p> 
   * date        Date                    Stores the Date.
   * <p> 
   * JTextPane   textPane                The JTextPane used for the print dialog.
   * <p> 
   * f           PrinterException        Exception thrown by JTextPane.
   */
  private void printHighScores ()
  {
    if (g != null) 
      g.pause = true;
    ArrayList <HighScore> scores = data.getHighScores();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    
    String temp =  "Fiora Games File \nJonah Haber and Reyno Tilikaynen\nMs. Dyke\n"+dateFormat.format(date)+ "\n\n\n\n High Scores:\n\n\n";
    for (int x = 0;x <10;x++)
    {
      temp += (x+1)+". Level/Name: "+scores.get(x).getLevel() + "/" +scores.get (x).getName () + "\nScore: "+ scores.get(x).getScore() + "\n";
    }
    temp += "\n\n\nFiora Games File \nJonah Haber and Reyno Tilikaynen\nMs. Dyke\n"+dateFormat.format(date);
    
    JTextPane textPane = new JTextPane();
    
    textPane.setText(temp);
    try{
      textPane.print();
    }
    catch (PrinterException f){}
    if (g != null) 
      g.resume ();
  }
  
  /**
   *  Asks the user if they want to clear the highscores. 
   * 
   * <p> Asks the user if they want to clear the highscores. 
   * <p> If yes, the highscores are cleared, if no nothing happens.
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type                    Description
   * <p> 
   * cont        JLabel                  Stores the message.
   * <p> 
   * yes         JButton                 The yes button.
   * <p> 
   * no         JButton                 The no button.
   */
  public void areYouSure()
  {
    JLabel cont;
    d.setSize (500, 100);
    d.setResizable (false);
    d.setLayout (new FlowLayout ());
    cont = new JLabel ("Are you sure you want to clear the high scores?");
    cont.setFont (new Font ("Serif", Font.PLAIN, 16));
    d.add (cont);
    JButton yes = new JButton ("Yes");
    d.add (yes);
    JButton no = new JButton ("No");
    d.add (no);
    
    yes.addActionListener (new ActionListener ()
                             {
      /** This method closes the dialog box.
        */
      public void actionPerformed (ActionEvent e)
      {
        data.clearHighScores ();
        data.createHighScores ();
        d.dispose ();
        fg.mainMenu();
      }
    }
    );
    no.addActionListener (new ActionListener ()
                            {
      /** This method closes the dialog box.
        */
      public void actionPerformed (ActionEvent e)
      {
        d.dispose ();
      }
    }
    );
    d.setLocationRelativeTo (f);
    d.setVisible (true);
  }

}