package ISP.classes;
import java.awt.*; 
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import ISP.classes.database.*;


/**
 * This class is responsible for the main menu options besides New Game. 
 *
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 *
 *
 */
public class MainMenuOptions
{
  /*
   * Stores the JFrame.
   */
  static JFrame f = FioraGame.frame;
  /*
   * Stores the layout.
   */
  SpringLayout layout = FioraGame.layout;
  /*
   * Stores the FioraGame instance.
   */
  FioraGame fg = FioraGame.fg;
  /*
   * Stores the database.
   */
  DataBase data = FioraGame.data;
  /*
   * Panel used in practice.
   */
  JPanel wordPanel;
  /*
   * The background used in practice.
   */
  JLabel practiceBackground;
  

  /**
   * Displays and runs the practice method.
   *
   *  <p> Displays and runs the practice method.
   *  <p> It first creates the tabbed menus. 
   *  <p> It then runs a for loop from 0 to 3 (increment 1).
   *  In this loop it adds a JComboBox to the panels list, storing each type of word in a seperate JComboBox. 
   *  <p> It then creates the itemListener for the JComboBox which displays the information of the word placed, depending
   *  on if it's a noun (translation + gender), verb (translation + conjugation), adjective (translation + tenses), or
   *  subject (just translation). 
   *  <p> It then puts it all together and displays it on the screen. 
   * 
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * panels      ArrayList <JPanel>   Stores the JComboBox's. 
   * <p>
   * tabbedPane  JTabbedPane          Stores the JTabbedPane.
   * <p>
   * options     ArrayList <String>   Stores the options for the JComboBox. 
   * <p>
   * cb          JComboBox            Stores the JComboBox itself.
   * <p> 
   * word        String               Stores the word selected in the JComboBox.
   * <p> 
   * wordObject  Word                 Stores the Word object of word.
   * <p> 
   * header      JLabel               Stores the word as a JLabel.
   * <p> 
   * translation JLabel               Stores the translation.
   * <p> 
   * female      JLabel               Stores the female version.
   * <p> 
   * plural      JLabel               Stores the plural version.
   * <p> 
   * gender      JLabel               Stores the gender.
   * <p>
   * temp        ArrayList <JLabel>   Stores the conjugations.
   * <p> 
   * back        JButton              The back button.
   *
   */
  public void practice ()
  {
    practiceBackground = fg.gg.getImage ("backgroundPractice.png");
    f.getContentPane().removeAll();
    f.repaint();
    JTabbedPane tabbedPane = new JTabbedPane (); 
    ArrayList <JPanel> panels = new ArrayList <JPanel> ();
    
    for (int x = 0; x < 4; x++)
    {
      panels.add (new JPanel ());
      panels.get (x).setPreferredSize (new Dimension (400, 50));
      ArrayList <String> options = new ArrayList <String> ();
      
      for (int y = 0; y < data.getSize (x + 1); y++)
      {
        if (x == 0)
          options.add (data.getSubject (y).getWord ());
        else if (x == 1)
          options.add (data.getVerb (y).getWord ());
        else if (x == 2)
          options.add (data.getAdjective (y).getWord ());
        else
        options.add (data.getNoun (y).getWord ());
      }
      
      JComboBox cb = new JComboBox(options.toArray ());
      cb.addItemListener(new ItemListener () {
        public void itemStateChanged (ItemEvent evt)
        {
          try
          {
          f.remove (wordPanel);
          }
          catch (NullPointerException e)
          {
          }
         wordPanel = new JPanel ();
          wordPanel.setPreferredSize (new Dimension (200, 200));
        f.add (wordPanel);
        f.remove (practiceBackground);
        f.add (practiceBackground);
          layout.putConstraint (SpringLayout.WEST, wordPanel, 400, SpringLayout.WEST, f);
          layout.putConstraint (SpringLayout.NORTH, wordPanel, 150, SpringLayout.NORTH, f);
          f.validate ();
          
          
          SpringLayout layout = new SpringLayout (); 
          wordPanel.setLayout (layout);
          
          String word = (String) evt.getItem ();
          Word wordObject = data.getWordObject (word);
          JLabel header = new JLabel (word);
          wordPanel.add (header); 
          layout.putConstraint (SpringLayout.WEST, header, 20, SpringLayout.WEST, wordPanel);
          layout.putConstraint (SpringLayout.NORTH, header, 10, SpringLayout.NORTH, wordPanel);
          
          
          JLabel translation = new JLabel ("Means: " + wordObject.getTranslation ()); 
          wordPanel.add (translation); 
          layout.putConstraint (SpringLayout.WEST, translation, 0, SpringLayout.WEST, header);
          layout.putConstraint (SpringLayout.NORTH, translation, 30, SpringLayout.NORTH, header);
          
          if (data.getType (word).equals ("N"))
          {
            JLabel gender = new JLabel ("Gender: " + ((((Noun) wordObject).getGender ().equals ("La"))?"Female":"Male"));
            wordPanel.add (gender); 
            layout.putConstraint (SpringLayout.WEST, gender, 0, SpringLayout.WEST, translation);
            layout.putConstraint (SpringLayout.NORTH, gender, 10, SpringLayout.SOUTH, translation);
          }
          else if (data.getType (word).equals ("A"))
          {
            JLabel female = new JLabel ("Female: " + ((Adjective) wordObject).getWord ("Female"));
            wordPanel.add (female);
            JLabel plural = new JLabel ("Plural: " + ((Adjective) wordObject).getWord ("Plural"));
            wordPanel.add (plural);
            layout.putConstraint (SpringLayout.WEST, female, 0, SpringLayout.WEST, translation);
            layout.putConstraint (SpringLayout.NORTH, female, 10, SpringLayout.SOUTH, translation);
            
            layout.putConstraint (SpringLayout.WEST, plural, 0, SpringLayout.WEST, female);
            layout.putConstraint (SpringLayout.NORTH, plural, 10, SpringLayout.SOUTH, female);
          }
          else
          {
            if (data.getType (word).equals ("V"))
            {
              ArrayList <JLabel> temp = new ArrayList <JLabel> ();
              for (int x = 0; x < 9; x++)
              {
                temp.add (new JLabel (data.getSubject (x).getWord () + ": " + ((Verb) wordObject).conjugate (data.getSubject (x).getWord ()))); 
                wordPanel.add (temp.get (temp.size () - 1));
                
                layout.putConstraint (SpringLayout.WEST, temp.get (temp.size () - 1), 0, SpringLayout.WEST, translation);
                layout.putConstraint (SpringLayout.NORTH, temp.get (temp.size () - 1), 5, SpringLayout.SOUTH, ((temp.size () == 1) ? translation : temp.get (temp.size () - 2)));
                
                if (x == 2)
                  x+= 3; 
              }
            }
          }
          f.validate (); 
          f.repaint ();
        } 
      });
      panels.get (x).add(cb);
    }
    tabbedPane.addTab("Subjects",panels.get (0));
    tabbedPane.addTab("Verbs", panels.get (1));
    tabbedPane.addTab("Adjectives", panels.get (2));
    tabbedPane.addTab("Nouns", panels.get (3));
    f.add(tabbedPane, BorderLayout.CENTER);
    JButton back = new JButton ("Back");
    f.add (back);
    f.add (practiceBackground);
    layout.putConstraint (SpringLayout.WEST, back, 25, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, back, 350, SpringLayout.NORTH, f);
    back.addActionListener (new ActionListener ()
                              {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        fg.mainMenu ();
        
      }
      
    }
    );
    f.validate();
    f.repaint ();
    
  }
  
  
  /**
   * Displays a window with all the highscores.
   *
   *  <p> Displays a window with all the highscores.
   *  <p> It first adds all the headers. 
   *  <p> It then runs a for loop from 0 to 9 with increment 1 and stores the name, level, and score of each high score. 
   *  <p> It then runs a for loop from 0 to 9 with increment 1 and outputs the highscore table.
   *  <p> If the user asks for the highScores to be cleared, an areYouSure dialog pops up and the highscores are cleared
   *  if the user says yes.
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type                    Description
   * <p>
   * score       ArrayList <HighScore>   Stores the JComboBox's. 
   * <p>
   * scores      JLabel [] []            Stores the high scores.
   * <p>
   * clearHighScores  JButton            Clears the highScores.  
   * <p> 
   * back        JButton                 The back button.
   * <p> 
   * title       JLabel                  The title header.
   * <p> 
   * gLevel      JLabel                  The level header.
   * <p> 
   * gName       JLabel                  The name header.
   * <p> 
   * gScore      JLabel                  The score header.
   * <p> 
   * d           Dialogs                 Used for displaying an areYouSure dialog.
   *
   */
  public void highscores ()
  {
    f.getContentPane().removeAll();
    data.createHighScores();
    data.updateHighScores ();
    
    ArrayList <HighScore> score = data.getHighScores();
    JButton back = new JButton ("Back");
    JButton clearHighScores = new JButton ("Clear High Scores");
  f.add (back);
  f.add (clearHighScores);
    JLabel title = new JLabel ("High Scores");
    JLabel gLevel = new JLabel ("Level");
    JLabel gName = new JLabel ("Name");
    JLabel gScore = new JLabel ("Score");
  f.add (gLevel);
  f.add (gName);
  f.add (gScore);
  f.add (title);
    JLabel [][] scores = new JLabel[3][10];
    for (int x =0;x<10;x++){
      scores [0][x] = new JLabel (score.get(x).getName ());
    f.add (scores [0][x]);
      scores [1][x] = new JLabel (""+score.get(x).getScore ());
    f.add (scores [1][x]);
      scores [2][x] = new JLabel (""+score.get(x).getLevel ());
    f.add (scores [2][x]);
    
    
    }
    f.add (fg.gg.getImage ("backgroundHighscores.jpg"));
    layout.putConstraint (SpringLayout.WEST, title, 280, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, title, 20, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, gLevel, 20, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, gLevel, 40, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, gName, 180, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, gName, 40, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, gScore, 580, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, gScore, 40, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, scores [0][0], 180, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, scores [0][0], 60, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, scores [1][0], 580, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, scores [1][0] , 60, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, scores [2][0], 20, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, scores [2][0] , 60, SpringLayout.NORTH, f);
    for (int x =1;x<10;x++){
      layout.putConstraint (SpringLayout.WEST, scores [0][x], 180, SpringLayout.WEST, f);
      layout.putConstraint (SpringLayout.NORTH, scores [0][x], 60 + (x*20), SpringLayout.NORTH, f);
      layout.putConstraint (SpringLayout.WEST, scores [1][x], 580, SpringLayout.WEST, f);
      layout.putConstraint (SpringLayout.NORTH, scores [1][x] , 60 + (x*20), SpringLayout.NORTH, f);
      layout.putConstraint (SpringLayout.WEST, scores [2][x], 20, SpringLayout.WEST, f);
      layout.putConstraint (SpringLayout.NORTH, scores [2][x] , 60 + (x*20), SpringLayout.NORTH, f);
    }
    layout.putConstraint (SpringLayout.WEST, back, 25, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, back, 350, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, clearHighScores, 100, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, clearHighScores, 350, SpringLayout.NORTH, f);
    f.validate ();
    f.repaint ();
    back.addActionListener (new ActionListener ()
                              {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        fg.mainMenu ();
        
      }
      
    }
    );
    clearHighScores.addActionListener (new ActionListener ()
                                         {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        Dialogs d = new Dialogs ("Are you sure?", 4);
         d.start ();
        f.repaint ();
        f.validate();
      }
      
    }
    );
    
    
  }
  
  
  /**
   * Displays a window with all the instructions.
   *
   *  <p> Displays a window with all the instructions.
   *  <p> It creates a back button and a JLabel storing the instructions and adds them to the JFrame. 
   * 
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type                    Description
   * <p> 
   * back        JButton                 The back button.
   * <p> 
   * purpose     JLabel                  The instructions.
   *
   */
  public void instructions ()
  {
    f.getContentPane().removeAll ();
    JButton back = new JButton ("Back");
    JLabel purpose = new JLabel 
      ("<html>Purpose: Get the 3 keys in the shortest amount of time to beat the game and get a high score!<br><br> How to play: Every level is different and there are 3 difficulties available foe each level.<br><br>" +
       "The goal of level one is to figure out if nouns are masculine or feminine. The player simply clicks <br> on the correct button"+
       " and if the button disappears, they got it right. Be careful, making <br>a mistake will affect your score! <br><br>"+
       "The goal of level two is to organize words that are scattered on the screen into <br>categories: Subject, Verb, Noun, and Adjective."+
       " Be careful, time counts! <br><br> "+
       "The goal of level three is to organize the scattered words on the screen into proper sentences and translate <br> the sentences."+
       " Try not to make any mistakes! <br><br>"+
       "The highest score goes to whoever can get all the keys with the fewest number of mistakes in the fastest <br> amount of time. <br><br>"+
       "Good Luck!</html>");
  f.add (purpose);
  f.add (back);
  f.add (fg.gg.getImage ("backgroundInstructions.jpg"));
    layout.putConstraint (SpringLayout.NORTH, purpose, 40, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, back, 25, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, back, 350, SpringLayout.NORTH, f);
    
    f.validate ();
    f.repaint ();
    back.addActionListener (new ActionListener ()
                              {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        fg.mainMenu ();
        
      }
      
    }
    );
  }
  
  /**
   * This method displays the goodbye screen when the user chooses to quit in the main menu.
   *
   *  <p>  This method displays the goodbye screen when the user chooses to quit in the main menu.
   *  <p>  It first displays information. 
   *  <p>  Then it quits after 5 seconds. 
   * 
   *  
   *<p> Variable Dictionary: 
   * <p>
   * Name        Type                    Description
   * <p> 
   * end         JLabel                  The thank you message.
   * <p> 
   * author      JLabel                  The author message.
   * <p> 
   * message     JLabel                  The 5 seconds message.
   * <p> 
   * taskPerformer  JLabel               Works with the timer to close after 5 seconds.
   */
  public void quit ()
  {
    f.getContentPane ().removeAll ();
    JLabel end = new JLabel ("Thank you for playing Fiora the Explorer!");
    JLabel author = new JLabel ("Created by: Jonah Haber and Reyno Tilikaynen");
    JLabel message = new JLabel ("This program will close in 5 seconds.");
  f.add (end);
  f.add (author);
  f.add (message);
  f.add (fg.gg.getImage ("endBackground.jpg"));
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, end, 320, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, end, 50, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, author, 320, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, author, 200, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, message, 320, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, message, 350, SpringLayout.NORTH, f);
    f.validate ();
    f.repaint ();
    ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        System.exit (0);
      }
    };
    new Timer(5000, taskPerformer).start();
  }

}