package ISP.classes;
import java.awt.*; 
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import ISP.classes.database.*;


/**
 * This class is responsible for the levels and cutscenes.
 *
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 *
 *
 */
public class Game
{
  /*
   * Stores the JFrame. 
   */
  static JFrame f; 
  /*
   * Stores the instance of FioraGame.
   */
  static FioraGame fg;
  /*
   * Stores the layout.
   */
  static SpringLayout layout;
  /*
   * Stores the database.
   */
  static DataBase data;
  /*
   * Stores the text.
   */
  JLabel text;
    /**
   * Variable for animation.
   */
  int x;
  /**
   * Stores a list of words the game uses. 
   */
  private ArrayList < String > words = new ArrayList < String > ();
  /*
   * Stores a list of noun objects the game uses. 
   */ 
  private ArrayList < Noun > nouns = new ArrayList < Noun > ();
  /**
   * Stores JButtons used in the game. 
   */
  private ArrayList < JButton > set1 = new ArrayList < JButton > ();
  /**
   * Stores JButtons used in the game. 
   */
  private ArrayList < JButton > set2 = new ArrayList < JButton > ();
  /**
   * Stores JLabels used in the game.  
   */
  private ArrayList < JLabel > labels = new ArrayList < JLabel > ();
  
  /**
   * Stores JLabels used in the game.  
   */
  private ArrayList <JPanel> rocks = new ArrayList <JPanel> ();
  /**
   * Stores JTextFields for the last level. 
   */
  private ArrayList < JTextField > fields;
  /**
   * Stores whether or not a button is active in level two.  
   */
  boolean state = false;
  
  /*
   * Stores the MouseTrack for level two. 
   */
  MouseTrack m;
  /**
   * Stores the current difficulty.
   */
  int difficulty = 1;
  /*
   * Stores the current time taken.
   */
  double time;
  
  /*
   * Stores the Timer 
   */
  Timer1 t;
  
  /**
   *  Stores the mistakes display.
   */
  JLabel mistakesCounter;
  /**
   * Stores the amount of mistakes made so far.
   */
  int mistakes = 0;
  /**
   *  Stores if the program is paused or not.
   */
  boolean pause;
  
  /**
   *  Stores the background.
   */
  JLabel background;
  
  
  /**
   *  Stores the Sounds.
   */
  Sounds s = new Sounds ();
  
  
  /** Creates a new Game object, sets the important lists and calls level select.
   * <p> Creates a new Game object, sets the important lists and calls level select.
   * 
   */
  public Game () 
  {
    f = FioraGame.frame; 
    layout = FioraGame.layout; 
    fg = FioraGame.fg; 
    data = FioraGame.data;
    levelSelect ();
  }
  
  /** Returns if the game is during a cutscene or level. 
   *
   * <p> Returns if the game is during a cutscene or level. 
   * If the button and panel lists are in use then level, otherwise cutscene.
   * 
   * @return If it is a level or not.
   */
  public boolean isMidLevel ()
  {
    if (set1.size () != 0 || rocks.size () != 0)
      return true; 
    return false;
  }

    /**
   * This method displays the intro cutscene when a new game starts.
   *
   *  <p> This method displays the intro cutscene when a new game starts.
   * variables:<p>
   * name type purpose<p>
   * animationPanel AnimationPanel to animate fiora<p>
   * image JLabel to have an image of fiora<p>
   * bubble JLabel to have a speech bubble <p>
   * keys JLabel the 3 keys in the image<p>
   *
   */
  private void introScene ()
  {
   f.getContentPane ().removeAll ();
    f.validate ();
    f.repaint ();
    AnimationPanel animationPanel= new AnimationPanel("menuFiora.gif","backgroundIntro.jpg",0,50,100,false);
    f.add (animationPanel);
    animationPanel.showAnimation();
    f.remove (animationPanel);
    JLabel image = fg.gg.getImage ("menuFiora.gif");
    JLabel bubble = fg.gg.getImage ("speechBubble1.png");
    final JLabel keys = fg.gg.getImage ("triKey.png");
    text = new JLabel ("Hey guys, look I have 3 keys!");
    f.add (text);
    f.add (bubble);
    f.add (image);
    f.add (keys);
    layout.putConstraint (SpringLayout.WEST, image, 100, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, image, 50, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, bubble, 230, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, bubble, 0, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, text, 250, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, text, 50, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.WEST, keys, 350, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, keys, 350, SpringLayout.NORTH, f);
    f.add (fg.gg.getImage ("backgroundIntro.jpg"));
    f.repaint ();
    f.validate();
    sceneOneDialog ();
          /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
   new Timer(4000, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        f.remove (keys);
        f.repaint ();
        f.validate ();
        ((Timer) evt.getSource ()).stop ();
      }
    }).start();
          /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
    new Timer(10000, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        levelOne ();
        ((Timer) evt.getSource ()).stop ();
      }
    }).start();
  }
  /**
   * displays the dialog in scene 1<p>
   * variables:<p>
   * name type purpose <p>
   * taskPerformer ActionListener runs when the Timer runs out<p>
   */
  public void sceneOneDialog ()
  { 
    ActionListener taskPerformer = new ActionListener() {
      /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed(ActionEvent evt) {
        text.setText ("<html>WAIT. WHERE DID THEY GO?!?! Richard the <br>Sneaky Gecko must have taken them!<br> help me get them back!</html>");
      }
    };
    new Timer(5000, taskPerformer).start();
  }
  
  /** Ends the Game. 
   *  <p> Ends the game by killing all variables and calling mainMenu in FioraGame. 
   * 
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * f      NullPointerException   Stores the exception thrown.
   */
  public void end ()
  {
    try {
      t.stopTimer (); }
    catch (NullPointerException f){}
    clearLists ();
    mistakes = -1; 
    time = 0.0;
    data = null; 
    f = null;
    layout = null;
    fg.mainMenu ();
  }
  
  /** Pauses the Game. 
   *  <p> Pauses the game. 
   *  <p> If an instance of pause is already running, don't run again. 
   *  <p> It first tries to stop the timer. 
   *  <p> It then runs a while loop while pause is true. 
   *  <p> It then re-adds the timer if it was removed in the first hand. 
   * 
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * ended       boolean           Stores whether the timer was stopped or not.
   * <p>
   * e       NullPointerException  Stores the exception thrown.
   */
  public void pause ()
  {
    if (pause)
      return;
    
    boolean ended = false;
    try
    {
      time = t.getTime ();
    t.stopTimer ();
    }
    catch (NullPointerException e)
    {
      ended = true;
    }
    t = null;
    pause = true;
    while (pause == true)
    {
      try
      {
        Thread.sleep (100); 
      }
      catch (InterruptedException e)
      {
        
      }
    }
    if (!ended)
    {
    t = new Timer1 (time);
    t.start ();
    f.remove (background);   
    f.validate (); 
    f.repaint ();
    f.add (background);
    }

  }
  
  /** Resumes the game.
   *  <p> Resumes the game. 
   */
  public void resume ()
  {
    pause = false;
  }
  
  /**
   * Clears all the lists the game uses. 
   *
   *  <p> Clears all the lists the game uses. 
   *
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * e      NullPointerException   Stores the exception thrown.
   */
  private void clearLists ()
  {
    try
    {
      set1.clear ();
      words.clear ();
      nouns.clear ();
      labels.clear ();
      rocks.clear ();
      set2.clear ();
      fields.clear ();
    }
    catch (NullPointerException e)
    {}
  }
  
  /** This method allows the user to select the level of difficulty.
   *  <p> This method allows the user to select the level of difficulty.
   *  <p> It first creates the buttons and background and adds them to the screen. 
   *  <p> It then creates the actionListeners to recieve user input. 
   *  <p> If back is pressed, the program returns to the main menu, otherwise the difficulty is set and the game starts.
   *  <p> If the game is paused all buttons will become unresponsive.
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * level1      JButton           Easy Option.
   * <p>
   * level2      JButton           Medium Option.
   * <p>
   * level3      JButton           Hard Option.
   * <p>
   * back        JButton           Back button.
   */
  public void levelSelect ()
  {
    f.getContentPane ().removeAll ();
    JButton level1 = new JButton ("Easy");
    JButton level2 = new JButton ("Medium");
    JButton level3 = new JButton ("Hard");
    JButton back = new JButton ("Back");
    f.add (level1);
    f.add (level2);
    f.add (level3);
    f.add (back);
    background = fg.gg.getImage ("levelSelectBackground.png");
    f.add (background);
    
    layout.putConstraint (SpringLayout.EAST, level1, 340, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, level1, 50, SpringLayout.NORTH, f);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, level2, 0, SpringLayout.HORIZONTAL_CENTER, level1);
    layout.putConstraint (SpringLayout.NORTH, level2, 50, SpringLayout.NORTH, level1);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, level3, 0, SpringLayout.HORIZONTAL_CENTER, level2);
    layout.putConstraint (SpringLayout.NORTH, level3, 50, SpringLayout.NORTH, level2);
    layout.putConstraint (SpringLayout.WEST, back, 25, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, back, 350, SpringLayout.NORTH, f);
    f.repaint ();
    f.validate ();
    back.addActionListener (new ActionListener ()
                              {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        if (pause)
          return;
        end ();
        
      }
      
    }
    );
    level1.addActionListener (new ActionListener ()
                                {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        if (pause)
          return;
        difficulty = 1;
        introScene ();
      }
      
    }
    );
    level2.addActionListener (new ActionListener ()
                                {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        if (pause)
          return;
        difficulty = 2;
        introScene ();
      }
      
    }
    );
    level3.addActionListener (new ActionListener ()
                                {
      /**
       * This method checks for buttons being pressed
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed (ActionEvent e)
      {
        if (pause)
          return;
        difficulty = 3;
        introScene ();
        
      }
      
    }
    );
  }
  
  /** This method creates a JPanel with a rock background and text in the front.
   *  <p> This method creates a JPanel with a rock background and text in the front.
   *  <p> It does this by layering the two JLabels and using SpringLayout.
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * temp        JPanel            JPanel to be returned.
   * <p>
   * tempLayout  SpringLayout      Layout for JPanel.
   * 
   * @param label The JLabel with text. 
   * @param background the JLabel that will be the background. 
   * @return A JPanel with the two parameters. 
   */
  private JPanel createRockWord (JLabel label, JLabel background)
  {
    JPanel temp = new JPanel (); 
    SpringLayout tempLayout = new SpringLayout ();
    temp.setLayout (tempLayout); 
    temp.setPreferredSize (new Dimension (90, 30));
    temp.setOpaque (false);
    temp.add (label); 
    tempLayout.putConstraint (SpringLayout.WEST, label, 15, SpringLayout.WEST, temp);
    tempLayout.putConstraint (SpringLayout.NORTH, label, 5, SpringLayout.NORTH, temp);
    temp.add (background); 
    return temp;
  }
  
  
  /**
   * Displays and runs level one.
   *
   *  <p> Displays and runs level one.
   *  <p> It first clears the screen and starts the timer. 
   *  <p> It then runs a for loop from 0 to less than 2 + difficulty (value of 1-3) with increment 1. 
   *  <p> In the for loop, it gets a noun from the database, makes sure there's no duplicates with a do-while
   *  (nouns.contains (tempN) loop, creates a label out of it and gives it two buttons (le and la). 
   *  It then adds the constraints and listeners to the button. 
   *  If the user chooses the correct option (le or la), that set dissapears. If they choose wrong, mistakes is increased.
   *  Once all buttons dissapear,the time updates and stops and the game goes to the next level. 
   *
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * x           int               Loop variable.
   * <p>
   * temp        final int         Stores the value of x so it can be used in the ActionListener.
   * <p>
   * tempN       Noun              Stores the temporary noun to check for duplicates.
   *
   */
  private void levelOne ()
  {
    fg.pause.setEnabled (true);
    f.getContentPane ().removeAll ();
    t = new Timer1 (time); 
    t.start ();
    
    mistakesCounter = new JLabel ();
    mistakes = 0;
    incrementMistakes (false);
    
    for (int x = 0 ; x < 2 + difficulty ; x++)
    {
      Noun tempN;
      do
      {
        tempN = data.getNoun (-1);
      }
      while (nouns.contains (tempN));
      nouns.add (tempN);
      
      rocks.add (createRockWord (new JLabel (nouns.get (x).getWord ()), fg.gg.getImage ("smallRock.png")));
      f.add (rocks.get (x));
      set1.add (new JButton ("Le"));
      f.add (set1.get (x));
      set2.add (new JButton ("La"));
      f.add (set2.get (x));
      layout.putConstraint (SpringLayout.WEST, rocks.get (x), (250 - 70 * (difficulty - 1)) * x + 20, SpringLayout.WEST, f);
      layout.putConstraint (SpringLayout.NORTH, rocks.get (x), 400, SpringLayout.NORTH, f);
      
      layout.putConstraint (SpringLayout.WEST, set1.get (x), 0, SpringLayout.WEST, rocks.get (x));
      layout.putConstraint (SpringLayout.SOUTH, set1.get (x), 0, SpringLayout.NORTH, rocks.get (x));
      
      layout.putConstraint (SpringLayout.WEST, set2.get (x), 0, SpringLayout.WEST, rocks.get (x));
      layout.putConstraint (SpringLayout.NORTH, set2.get (x), 0, SpringLayout.SOUTH, rocks.get (x));
      f.validate ();
      f.repaint ();
      
      final int temp = x;
      set1.get (x).addActionListener (new ActionListener ()
                                        {
        public void actionPerformed (ActionEvent ae)
        {
          if (pause)
            return;
          if (set1.indexOf (ae.getSource ()) != 0)
            return;
          if (ae.getActionCommand ().equals (nouns.get (temp).getGender ()))
          {
            s.playCorrect ();
            f.remove (set1.get (0));
            f.remove (set2.get (0));
            set1.remove (0);
            set2.remove (0);
            f.validate ();
            f.repaint ();
          }
          else
          {
            incrementMistakes (true);
          }
          if (set1.size () == 0)
          {
            clearLists ();
            time = t.getTime (); 
            t.stopTimer (); 
            oneTwoScene (); 
          }
        }
        
      }
      );
      set2.get (x).addActionListener (new ActionListener ()
                                        {
        public void actionPerformed (ActionEvent ae)
        {
          if (pause)
            return;
          if (set2.indexOf (ae.getSource ()) != 0)
            return;
          if (ae.getActionCommand ().equals (nouns.get (temp).getGender ()))
          {
            s.playCorrect ();
            f.remove (set1.get (0));
            f.remove (set2.get (0));
            set1.remove (0);
            set2.remove (0);
            f.validate ();
            f.repaint ();
          }
          else
          {
            incrementMistakes (true);
          }
          if (set1.size () == 0)
          {
            clearLists ();
            time = t.getTime (); 
            t.stopTimer (); 
            oneTwoScene (); 
          }
        }
      }
      );
    }
    background = fg.gg.getImage ("LevelOneBackground.png");
    f.add (background);
    f.validate ();
    f.repaint (); 
  }
  
  /** Increments mistakes and updates the JLabel.
   *
   * <p>  Increments mistakes and updates the JLabel.
   * <p> If it was a mistake, mistake is incremented and the incorrect sound plays. 
   * <p> If the JLabel doesn't exist or isn't in the JFrame, it is created and/or added. 
   * <p> Otherwise just the text in the JLabel is updated. 
   * 
   * @param mistake Stores if a mistake happened or not. 
   */
  private void incrementMistakes (boolean mistake)
  {
    if (mistake)
    {
    mistakes++;
    s.playIncorrect ();
    }
    
      if (mistakesCounter == null)
        mistakesCounter = new JLabel ("Mistakes: " + mistakes);
      else 
        mistakesCounter.setText ("Mistakes: " + mistakes);
      mistakesCounter.setForeground (Color.white);
      
    if (mistakesCounter.getParent () == null)
    {
      f.add (mistakesCounter); 
    
    layout.putConstraint (SpringLayout.WEST, mistakesCounter, 550, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, mistakesCounter, 0, SpringLayout.NORTH, f); 
    }
    f.validate (); 
    f.repaint ();
    
  }
  
  
   /**
   * Displays the cutscene between level one and two.
   *
   *  <p> Displays the cutscene between level one and two.<p>
   * variable: name: text1 type: JLabel purpose: store the text in the bubble<p>
   *
   */
  private void oneTwoScene ()
  {
    fg.pause.setEnabled (false);
    f.getContentPane().removeAll ();
    final JLabel text1 = new JLabel ("Yes, you got 1 key back! only 2 more!");
    
    f.add (text1);
    layout.putConstraint (SpringLayout.WEST, text1, 130, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, text1, 50, SpringLayout.NORTH, f);
    f.add (fg.gg.getImage ("backgroundScene2.png"));
    new Timer(4000, new ActionListener() {
            /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed(ActionEvent evt) {
        text1.setText ("<html>To get the next key, put the <br>correct words in the correct baskets!<br>We can catch Richard!</html>");
        f.repaint ();
        f.validate ();
        ((Timer) evt.getSource ()).stop ();
      }
    }).start();
    new Timer(8000, new ActionListener() {
            /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed(ActionEvent evt) {
        levelTwo();
        ((Timer) evt.getSource ()).stop ();
      }
    }).start();
    f.repaint ();
    f.validate ();
  }
  
  
  /**
   * Displays and runs level two.
   *
   *  <p> Displays and runs level two.
   *  It starts by restarting the timer and removing everything from the screen. 
   *  It then runs a for loop from 0 to difficulty * 4, incrementing 4 each time. 
   *  It adds a noun, verb, adjective, and subject to the words list. 
   *  It then creates a JButton for each word and positions it on the screen in random order (using a do-while loop). 
   *  <p> The ActionListeners make so if a button is pressed, it's location follows the mouse. 
   *  If the button is pressed again, then if the mouse is over the right basket, the button dissapears. 
   *  If it's over the wrong basket, mistakes is incremented. 
   *  If it's not over a basket, it stays where it is and stops following the mouse. 
   *  <p> When all buttons dissapear, the timer is stopped and the next level is called. 
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * x           int               Loop variable.
   * <p>
   * y           int               Loop variable.
   * <p> 
   * mouse       Point             Stores the location of the mouse in the window.
   * <p> 
   * windowLoc   Point             Stores the location of the window.
   * <p> 
   * grid        boolean [] []     Stores the used spots in the grid. 
   * <p>
   * tempX       int               Stores the x location of the current word.
   * <p>
   * tempY       int               Stores the y location of the current word.
   *
   */
  private void levelTwo ()
  {
    fg.pause.setEnabled (true);
    f.getContentPane ().removeAll ();
    t = new Timer1 (time);
    t.start ();
    incrementMistakes (false);
    background = fg.gg.getImage ("levelTwoBackground.png");
     
    
    boolean [] [] grid = new boolean [difficulty] [4]; 
    for (int x = 0; x < difficulty; x++)
    {
      java.util.Arrays.fill (grid [x], false);
    }
    
    for (int x = 0 ; x < difficulty * 4 ; x += 4)
    {
      words.add (data.getNoun (-1).getWord ());
      words.add (data.getVerb (-1).getWord ());
      words.add (data.getAdjective (-1).getWord ("Male"));
      words.add (data.getSubject (-1).getWord ()); 
      for (int y = x ; y < x + 4 ; y++)
      {
        set1.add (new JButton (words.get (y)));
        f.add (set1.get (y));
        int tempX, tempY;
        do
        {
          tempX = (int) (Math.random () * 4);
          tempY = (int) (Math.random () * difficulty);
        }
        while (grid [tempY] [tempX]);
        grid [tempY] [tempX] = true; 
        
        layout.putConstraint (SpringLayout.WEST, set1.get (y), tempX * 100 + 225, SpringLayout.WEST, f);
        layout.putConstraint (SpringLayout.NORTH, set1.get (y), tempY * 50 + 100, SpringLayout.NORTH, f);

        set1.get (y).addActionListener (new ActionListener ()
                                          {
          
          public void actionPerformed (ActionEvent ae)
          {
            if (pause)
            return;
            if (state == false)
            {
              state = true;
              m = new MouseTrack ((JButton) ae.getSource (), background);
              m.start (); 
            }
            else
            {
              state = false;
              m.stopTrack ();
              Point mouse = MouseInfo.getPointerInfo ().getLocation ();
              Point windowLoc = f.getLocation ();
              mouse = new Point ((int) (mouse.getX () - windowLoc.getX ()), (int) (mouse.getY () - windowLoc.getY ()));
              if (mouse.getY () < 350)
                return;
              else
              {
                if (mouse.getX () < 160 && data.getType (ae.getActionCommand ()).equals ("A") || mouse.getX () < 320 && mouse.getX () >= 160 && data.getType (ae.getActionCommand ()).equals ("N") || mouse.getX () < 480 && mouse.getX () >= 320 && data.getType (ae.getActionCommand ()).equals ("V") || mouse.getX () < 640 && mouse.getX () >= 480 && data.getType (ae.getActionCommand ()).equals ("S"))
                {
                  s.playCorrect ();
                  f.remove ((JButton) ae.getSource ());
                  set1.remove (set1.indexOf (ae.getSource ()));
                  f.repaint (); 
                  f.validate ();
                }
                else
                {
                  layout.putConstraint (SpringLayout.WEST, set1.get (set1.indexOf (ae.getSource ())), (set1.indexOf (ae.getSource ()) % 4) * 100 + 170, SpringLayout.WEST, f);
                  layout.putConstraint (SpringLayout.NORTH, set1.get (set1.indexOf (ae.getSource ())), (set1.indexOf (ae.getSource ()) / 4) * 30 + 300, SpringLayout.NORTH, f);
                  incrementMistakes (true);
                }
              }
            }
            if (set1.size () == 0)
            {
              clearLists (); 
              time = t.getTime (); 
              t.stopTimer (); 
              t = null;
              twoThreeScene (); 
            }
          }
        }
        );
      }
      
      
    }
    f.add (background);
    f.validate ();
    f.repaint (); 
  }
  
  
  /**
   * Displays the cutscene between level two and three.
   *
   *  <p> Displays the cutscene between level two and three.<p>
   * variable name: text1, type: JLabel, purpose: to store the text within the text bubble<p>
   *
   */
  private void twoThreeScene ()
  {
    fg.pause.setEnabled (false);
    f.getContentPane().removeAll ();
    final JLabel text1 = new JLabel ("Wow, we have 2 keys! We only need 1 more!");
    
    f.add (text1);
    layout.putConstraint (SpringLayout.WEST, text1, 120, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, text1, 135, SpringLayout.NORTH, f);
    new Timer(4000, new ActionListener() {
            /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed(ActionEvent evt) {
        text1.setText ("<html>HEY! THERE'S RICHARD. <br>JUST COMPLETE THE FRENCH SENTENCE <br>IN THE NEXT LEVEL AND WE CAN CATCH HIM!!</html>");
        f.repaint ();
        f.validate ();
        ((Timer) evt.getSource ()).stop ();
      }
    }).start();
    new Timer(10000, new ActionListener() {
            /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed(ActionEvent evt) {
        levelThree();
        ((Timer) evt.getSource ()).stop ();
      }
    }).start();
    f.add (fg.gg.getImage ("backgroundScene3.jpg"));
    f.repaint ();
    f.validate ();
  }
  
  
  
  /**
   * Displays and runs level three.
   *
   *  <p> Displays and runs level three.
   *  It starts by restarting the timer and removing everything from the screen. 
   *  <p> It then gets a sentence and creates an array of JTextFields. 
   *  <p> A for loop is run from 0 to the to length of the sentence with increment 1. 
   *  It adds the fields to the screen and adds JLabels with each word onto the screen randomly using a do-while 
   *  (grid [tempX]) loop. 
   *  <p> A button check is created to check if the sentence the user enters is right. 
   *  It runs a for loop from 0 to the length of the sentence with increment 1. 
   *  If a field is blank, nothing happens. 
   *  If a field is wrong, then mistakes is incremented and the field is cleared. 
   *  If a field is right, then temp is incremented. 
   *  At the end of the check, if temp is equal to the amount of words in the sentence (all fields right), 
   *  it ends the level. 
   *
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * x           int               Loop variable.
   * <p>
   * check       JButton           The JButton that checks the fields when pressed.
   * <p> 
   * temp        int               Stores the amount of fields that are correct.
   * <p> 
   * grid        boolean []        Stores the used spots in the grid. 
   * <p>
   * tempX       int               Stores the x location of the current word.
   * 
   */
  private void levelThree ()
  {
    fg.pause.setEnabled (true);
    f.getContentPane ().removeAll ();
    t = new Timer1 (time); 
    t.start (); 
    incrementMistakes (false);
    
    words = data.getSentence (difficulty);
    boolean [] grid = new boolean [words.size ()];
    java.util.Arrays.fill (grid, false);
    
    fields = new ArrayList < JTextField > ();
    for (int x = 0 ; x < words.size () ; x++)
    {
      rocks.add (createRockWord (new JLabel (words.get (x)), fg.gg.getImage ("smallRock.png")));
      f.add (rocks.get (x));
      
      int tempX; 
      do
      {
        tempX = (int) (Math.random () * grid.length);
      }
      while (grid [tempX]);
      grid [tempX] = true;
      
      layout.putConstraint (SpringLayout.WEST, rocks.get (x), tempX * 100 + 100, SpringLayout.WEST, f);
      layout.putConstraint (SpringLayout.NORTH, rocks.get (x), (x % 2) * 50 + 250, SpringLayout.NORTH, f);
      fields.add (new JTextField (5));
      f.add (fields.get (x));
      layout.putConstraint (SpringLayout.WEST, fields.get (x), x * 75 + 100, SpringLayout.WEST, f);
      layout.putConstraint (SpringLayout.NORTH, fields.get (x), 350, SpringLayout.NORTH, f);
    }
    JButton check = new JButton ("Check");
    f.add (check);
    background = fg.gg.getImage ("levelThreeBackground.png");
    f.add (background);
    layout.putConstraint (SpringLayout.WEST, check, 500, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, check, 350, SpringLayout.NORTH, f);
    check.addActionListener (new ActionListener ()
                               {
      public void actionPerformed (ActionEvent ae)
      {
        if (pause)
            return;
        int temp = 0;
        for (int x = 0 ; x < words.size () ; x++)
        {
          if (fields.get (x).getText ().equals (""))
            continue;
          else if (fields.get (x).getText ().equals (words.get (x)))
          {
            temp++;
          }
          else
          {
            incrementMistakes (true);
            fields.get (x).setText ("");
          }
        }
        if (temp == fields.size ())
        {
          s.playCorrect ();
          clearLists (); 
          time = t.getTime (); 
          t.stopTimer (); 
          t = null;
          endScene (); 
        }
      }
    }
    );
    f.repaint (); 
    f.validate ();
  }
  
  
  /**
   * Displays the cutscene when you beat the game.
   *
   *  <p> Displays the cutscene when you beat the game.
   *  It then sends the user to the getHighScore screen or the mainMenu. <p>
   * variable:<p>
   * name type purpose<p>
   * image JLabel to store the speechbubble for Richard<p>
   * back JLabel to store the background image <p>
   * text1 JLabel to store the text for fiora<p>
   * text2 JLabel to store the text for Richard <p>
   * <p>
   * conditionals:<p>
   * to check if there is a highscore<p>
   *
   */
  private void endScene ()
  {
    fg.pause.setEnabled (false);
    final JLabel image = fg.gg.getImage ("speechBubble2.png");
    final JLabel back = fg.gg.getImage ("backgroundEndScene.jpg");
    f.getContentPane().removeAll ();
    final JLabel text1 = new JLabel ("<html>We found you, Richard! You're a <br>sneaky Gecko but we found you!</html>");
    final JLabel text2 = new JLabel ("Rats, you kids are so good at french.");
    f.add (text1);
    layout.putConstraint (SpringLayout.WEST, text1, 190, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, text1, 45, SpringLayout.NORTH, f);
    new Timer(5000, new ActionListener() {
            /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed(ActionEvent evt) {
        text1.setText ("<html>Thank you, kids for helping me and <br>Gloves find all of the keys!</html>");
        ((Timer) evt.getSource ()).stop ();
      }
    }).start();
    new Timer(3000, new ActionListener() {
            /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed(ActionEvent evt) {
        f.remove (back);
        f.add (text2);
        f.add (image);
        f.add (back);
        layout.putConstraint (SpringLayout.WEST, image, 220, SpringLayout.WEST, f);
        layout.putConstraint (SpringLayout.NORTH, image, 180, SpringLayout.NORTH, f);
        layout.putConstraint (SpringLayout.WEST, text2, 240, SpringLayout.WEST, f);
        layout.putConstraint (SpringLayout.NORTH, text2, 265, SpringLayout.NORTH, f);
        f.repaint ();
        f.validate ();
        ((Timer) evt.getSource ()).stop ();
      }
    }).start();
    new Timer(6000, new ActionListener() {
            /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed(ActionEvent evt) {
        text2.setText ("Wow, good job, kids!");
        ((Timer) evt.getSource ()).stop ();
      }
    }).start();
    new Timer(10000, new ActionListener() {
            /**
       * This method checks for a Timer running out
       * <p>
       * @param e Stores the ActionEvent object
       */
      public void actionPerformed(ActionEvent evt) {
        if (((900 - time) * 10 - (mistakes * 25) + (difficulty - 1) * 150) > data.getHighScores ().get (9).getScore ())
        {
          getHighscore ();
        }
        else
          end ();
        ((Timer) evt.getSource ()).stop ();
      }
    }).start();
    f.add (back);
    f.repaint ();
    f.validate ();
  }
  /**
   * Displays a window that asks the user to enter a highscore name if they got a highscore.
   *
   *  <p> Displays a window that asks the user to enter a highscore name if they got a highscore.
   *  <p> It first sets up the labels, fields, button, and background. 
   *  <p> It then uses an actionListener on the button for user input. 
   *  When the button is pressed, if the field is empty then it goes to mainMenu, otherwise it stores the high score
   *  name. 
   * 
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * save        JButton           The JButton that saves and/or goes to main menu.
   *
   */
  private void getHighscore ()
  {
    
    f.getContentPane().removeAll ();
    
    fields = new ArrayList <JTextField> (); 
    fields.add (new JTextField (10)); 
    f.add (fields.get (0)); 
    labels = new ArrayList <JLabel> (); 
    labels.add (new JLabel ("New Highscore!"));
    labels.add (new JLabel ("Please enter your name: "));
    labels.get (0).setForeground (Color.white);
    labels.get (1).setForeground (Color.white);
    f.add (labels.get (0)); 
    f.add (labels.get (1)); 
    JButton save = new JButton ("Save"); 
    f.add (save); 
    background = fg.gg.getImage ("highScoreBackground.png");
    f.add (background);
    
    layout.putConstraint (SpringLayout.WEST, labels.get (0), 100, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, labels.get (0), 100, SpringLayout.NORTH, f);
    
    layout.putConstraint (SpringLayout.WEST, labels.get (1), 100, SpringLayout.WEST, f);
    layout.putConstraint (SpringLayout.NORTH, labels.get (1), 250, SpringLayout.NORTH, f);
    
    layout.putConstraint (SpringLayout.WEST, fields.get (0), 20, SpringLayout.EAST, labels.get (1));
    layout.putConstraint (SpringLayout.NORTH, fields.get (0), 250, SpringLayout.NORTH, f);
    
    layout.putConstraint (SpringLayout.WEST, save, 20, SpringLayout.EAST, fields.get (0));
    layout.putConstraint (SpringLayout.NORTH, save, 250, SpringLayout.NORTH, f);
    
    save.addActionListener (new ActionListener ()
                              {
      public void actionPerformed (ActionEvent ae)
      {
        
        if (!fields.get (0).getText ().equals (""))
        {
          double score = Math.round (((900 - time) * 10 - (mistakes * 25) + (difficulty - 1) * 150) * 10)/10;
          data.addHighScore (new HighScore (fields.get (0).getText (), score,difficulty));
        }
        clearLists ();
        end ();
      }
    });
    f.validate (); 
    f.repaint ();
  }
}