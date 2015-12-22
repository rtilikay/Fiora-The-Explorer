package ISP.classes;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import ISP.classes.database.*;

/**
 * This class stores all the French words and HighScores this game will use. 
 * 
 * @author Jonah Haber
 * @version 4.0 May 17th 2013
 * 
 * 
 */
public class DataBase
{
  
  /**
   * Stores the French verbs that can be used. 
   */
  private ArrayList <Verb> verbs = new ArrayList <Verb>(); 
  
  /**
   * Stores the French nouns that can be used. 
   */
  private ArrayList <Noun> nouns = new ArrayList <Noun>(); 
  /**
   * Stores the French adjectives that can be used. 
   */
  private ArrayList <Adjective> adjectives = new ArrayList <Adjective>(); 
  /**
   * Stores the French subjects that can be used. 
   */
  private ArrayList <Word> subjects = new ArrayList <Word>();
  
  /**
   * Stores the French sentences that can be used. 
   */
  private ArrayList <String> sentences = new ArrayList <String> (); 
  /**
   * Stores the HighScores.
   */
  private ArrayList <HighScore> highScores = new ArrayList <HighScore>(); 
  /**
   * Stores the file names from which the French words are read in. 
   */
  private String [] fileNames = {"ISP/database/GameVerbs.fio", "ISP/database/GameNouns.fio","ISP/database/GameAdjectives.fio","ISP/database/GameSubjects.fio", "ISP/database/GameSentences.fio"};
  
  /** 
   * Creates a new Database object.  
   * 
   *  <p> Creates a new Database object, loads the lists and the highscores.  
   *  
   */
  public DataBase ()
  {
    updateLists (); 
    createHighScores ();
    
  }
  
  /** Gets the size of the given type.
   * 
   * <p> Gets the size of the given type.
   * if type == 1, gets the size of subjects, if 2 then verbs, if 3 adjectives and else nouns. 
   * 
   * @param type Which type of word to get the size of. 
   * @return The size of the list of words of the given type.
   */
  public int getSize (int type)
  {
    if (type==1)
      return subjects.size();
    if (type==2)
      return verbs.size ();
    if (type ==3)
      return adjectives.size ();
    return nouns.size();
  }
  /** 
   * Creates the HighScores list. 
   * 
   *  <p> Creates the HighScores list by reading in from the HighScore file and writing a new one if it doesn't exist
   *  or is corrupt.
   *  <p> First it opens the file and checks if the header is there. If it isn't an exception is thrown. 
   *  <p> Then it runs a for loop from 0 to 9 (increment 1), each time reading in 2 lines and adding a HighScore object
   *  from those two lines to the highScores list. 
   *  <p> If any of this errors, a new HighScore file is created, 10 default high scores are outputted and stored in
   *  the list using a for loop from 0 to 9 increasing by 1. 
   *  
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * in          BufferedReader    Reference variable for the BufferedReader.
   * <p>
   * out         PrintWriter       Reference variable for the PrintWriter.
   * <p>
   * x           int               Loop variable.
   * <p>
   * line1       String            Temporarily stores the name of a HighScore.
   * <p>
   * line2       String            Temporarily stores the score of a HighScore.
   * <p>
   * f           Exception         Stores the Exception thrown by the try statement.
   * <p> 
   * d           Exception         Stores the Exception thrown by the try statement. 
   *  
   */
  public void createHighScores()
  {
    highScores.clear();
    try{
      BufferedReader in = new BufferedReader (new FileReader ("ISP/database/HighScore.fio"));
      if (!in.readLine().equals ("Jeyno Games File"))
 throw new IOException ();
      String line1,line2,line3;
      for (int x = 0; x<10;x++)
      {
 line1 = in.readLine();
 line2 = in.readLine();
 line3 = in.readLine();
 highScores.add(new HighScore (line1,Double.parseDouble (line2),Integer.parseInt (line3)));
      }
      
    }
    catch (Exception f)
    { 
      
 clearHighScores ();
      updateHighScores();
    }
    
  }
  
  public void clearHighScores ()
  {
     try
      {
    PrintWriter out = new PrintWriter (new FileWriter ("ISP/database/HighScore.fio")); 
 out.println ("Jeyno Games File");
 for (int x = 0; x<10; x++)
 {
   HighScore temp = new HighScore ("AAA",0,0);
   out.println (temp.getName());
   out.println (temp.getScore());
   out.println (temp.getLevel());
   highScores.add (temp); 
 }
 out.close ();
     }
      catch (IOException d)
      {}
  }
  
  /** 
   * Saves all the lists. 
   * 
   *  <p> Saves all the lists. 
   *  
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * out         PrintWriter       Reference variable for the PrintWriter.
   * <p>
   * x           int               Loop variable.
   * <p>
   * d           Exception         Stores the Exception thrown by the try statement. 
   * <p>
   * list        ArrayList         Stores the list to be saved. 
   * <p>
   * loc         int               Loop variable. 
   * 
   */
  public void saveLists ()
  {
    ArrayList list; 
    for (int loc = 0; loc < 5; loc++)
    {
      if (loc == 0)
 list = verbs; 
      else if (loc == 1)
 list = nouns; 
      else if (loc == 2)
  list = adjectives;
      else if (loc == 3)        
 list = subjects;
      else 
 list = sentences;
      
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter (fileNames [loc])); 
      out.println ("Jeyno Games File");
      out.println (list.size());
      for (int x = 0; x<list.size(); x++)
      {
 out.println (list.get(x).toString ());
      }
      out.close ();
    }
    catch (IOException d)
    {}
    }
  }
  
  /** 
   * Saves a HighScorelist to a file. 
   * 
   *  <p> Saves the HighScore list to the highscore file.
   *  <p> It first creates a HighScore.fio file and prints the header. 
   *  <p> Then it runs a for loop from 0-9 increment 1 and prints out all the high scores from the highScores list. 
   *  
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * out         PrintWriter       Reference variable for the PrintWriter.
   * <p>
   * x           int               Loop variable.
   * <p>
   * d           Exception         Stores the Exception thrown by the try statement. 
   */
  public void updateHighScores()
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter ("ISP/database/HighScore.fio")); 
      out.println ("Jeyno Games File");
      for (int x = 0; x<10; x++)
      {
 HighScore temp = highScores.get (x);
 out.println (temp.getName());
 out.println (temp.getScore());
 out.println (temp.getLevel());
      }
      out.close ();
    }
    catch (IOException d)
    {}
    
  }
  
  /** 
   * Reads in the French word lists from a file. 
   * 
   *  <p> Reads in the French word lists from a file.
   *  <p> It runs a for loop from 0 to the amount of files to read in from (4) - 1 with increment 1. 
   *  <p> It first opens the file with the filename in the array to read in from. 
   *  <p> If the header is incorrect, an exception is thrown. 
   *  <p> Then a for loop from 0 to the amount read in - 1 and adds the read-in word to the correct list. 
   *  <p> If an error occurs the program catches it and does nothing. 
   *  
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type                 Description
   * <p>
   * in          BufferedReader       Reference variable for the BufferedReader.
   * <p>
   * x           int                  Loop variable.
   * <p>
   * y           int                  Loop variable.
   * <p>
   * max         int                  Stores how many lines have to be read in.
   * <p>
   * line        String               Temporarily stores the word read in from the file.
   * <p>
   * f           NullPointerException Stores the NullPointerException thrown by the try statement.
   * <p>
   * f           IOException          Stores the IOException thrown by the try statement.\
   * <p> 
   * gender      String               Stores the gender of a noun. 
   * <p> 
   * male        String               Stores the male version of the adjective. 
   * <p> 
   * female      String               Stores the female version of the adjective. 
   * <p> 
   * plural      String               Stores the plural version of the adjective. 
   */
  public void updateLists ()
  {
    for (int y = 0; y<fileNames.length;y++)
    {
      try
      {
 BufferedReader in = new BufferedReader (new FileReader (fileNames[y]));
 String line = in.readLine ();
 if (!line.equals ("Jeyno Games File"))
   throw new IOException();
 else
   line = in.readLine();
 int max = Integer.parseInt (line);
 for (int x = 0; x< max ;x++){
   line = in.readLine ();
   if (y==0)
   {
     ArrayList <String> conjugations = new ArrayList <String> ();
     String word = line.substring (0, line.indexOf (" "));
     while (line.indexOf (" ") != line.lastIndexOf (" "))
     {
       line = line.substring (line.indexOf (" ") + 1);
       conjugations.add (line.substring (0, line.indexOf (" ")));
     }
     line = line.substring (line.indexOf (" ") + 1);
     verbs.add (new Verb (word, conjugations, line));
   }
   else if (y==1)
   {
     String gender = line.substring (line.indexOf (" ") + 1, line.lastIndexOf (" "));
     String word = line.substring (0, line.indexOf (" "));
     line = line.substring (line.lastIndexOf (" ") + 1);
     nouns.add (new Noun (word, gender, line));
   }
   else if (y==2)
   {
     String male = line.substring (0, line.indexOf (" ")); 
     line = line.substring (line.indexOf (" ") + 1); 
     String female = line.substring (0, line.indexOf (" ")); 
     String plural = line.substring (line.indexOf (" ") + 1, line.lastIndexOf (" "));
     String translation = line.substring (line.lastIndexOf (" ") + 1);
     adjectives.add (new Adjective (male, female, plural, translation));
   }
   else if (y==3)
   {
     String word = line.substring (0, line.indexOf (" "));
     line = line.substring (line.indexOf (" ") + 1);
     subjects.add (new Word (word, line));
   }
   else
     sentences.add (line); 
 }     
      }
      catch (NullPointerException f)
      {}
      catch (IOException f)
      {}
    }
  }
  
  /** 
   * Sorts all the French words in alphabetical order. 
   * 
   *  <p> Sorts all the French words in alphabetical order. 
   *  <p> It first runs a for loop from 0 to the length of verbs. 
   *  <p> It then does a selection sort through the list using a for loop from the current position to the end to sort. 
   *  <p> The same thing is done for the other 3 lists to sort them. 
   *  
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type                 Description
   * <p>
   * numSort     int                  Loop variable.
   * <p>
   * pos         int                  Loop variable.
   * <p>
   * minPos      int                  Stores the index of the smallest value in the section of the array being searched.
   * <p>
   * temp        String               Temporarily stores the subject being swapped when a swap occurs.
   * <p>
   * tempV       Verb                 Temporarily stores the verb being swapped when a swap occurs. 
   * <p>
   * tempN       Noun                 Temporarily stores the noun being swapped when a swap occurs. 
   * <p>
   * tempA       Adjective            Temporarily stores the adjective being swapped when a swap occurs. 
   */
  public void sort ()
  {
    //for verbs
    for (int numSort = 0 ; numSort < verbs.size() ; numSort++)
    {
      Verb tempV;
      int minPos = numSort;
      for (int pos = numSort ; pos < verbs.size() ; pos++)
      {
 if (verbs.get(minPos).getWord ().compareTo(verbs.get(pos).getWord ()) >0)
   minPos = pos;
      }
      
      tempV = verbs.set (minPos,verbs.get(numSort));
      verbs.set(numSort,tempV);
    }
    
    //for nouns
    for (int numSort = 0 ; numSort < nouns.size() ; numSort++)
    {
      Noun tempN; 
      int minPos = numSort;
      for (int pos = numSort ; pos < nouns.size() ; pos++)
      {
 if (nouns.get(minPos).getWord ().compareTo(nouns.get(pos).getWord ()) >0)
   minPos = pos;
      }
      
      tempN = nouns.set (minPos,nouns.get(numSort));
      nouns.set(numSort,tempN);
    }
    
    //for adjectives
    for (int numSort = 0 ; numSort < adjectives.size() ; numSort++)
    {
      Adjective tempA; 
      int minPos = numSort;
      for (int pos = numSort ; pos < adjectives.size() ; pos++)
      {
 if (adjectives.get(minPos).getWord ("Male").compareTo(adjectives.get(pos).getWord ("Male")) >0)
   minPos = pos;
      }
      
      tempA = adjectives.set (minPos,adjectives.get(numSort));
      adjectives.set(numSort,tempA);
    }
    
    //for subjects
    for (int numSort = 0 ; numSort < subjects.size() ; numSort++)
    {
      Word temp;
      int minPos = numSort;
      for (int pos = numSort ; pos < subjects.size() ; pos++)
      {
 if (subjects.get(minPos).getWord ().compareTo(subjects.get(pos).getWord ()) >0)
   minPos = pos;
      }
      
      temp = subjects.get(minPos);
      subjects.set (minPos,subjects.get(numSort));
      subjects.set(numSort,temp);
    }
    saveLists ();
  }
  
  
  /** 
   * Adds the given HighScore in the correct location.
   * 
   *  <p> Adds the given HighScore in the correct location.
   *  <p> A for loop from 0 to 9 is run increment 1. 
   *  <p> If the high score at the current location in the list is smaller than the high score to be added, it adds the
   *  highscore to that position and removes the last highscore (10th position). 
   *  
   *  <p> Variable Dictionary: 
   * <p>
   * Name        Type                 Description
   * <p>
   * x           int                  Loop variable.
   * 
   * @param score The HighScore to be added. 
   */
  public void addHighScore(HighScore score)
  {
    for ( int x = 0; x<10;x++)
    {
      if (highScores.get(x).getScore()<=score.getScore())
      {
 highScores.add(x,score);
 highScores.remove (10);
 break;
      }
    }
    updateHighScores();
  }
  
  /** 
   * Gets the verb at the given index or a random word if the index is out of bounds.
   * 
   *  <p> Gets the verb at the given index or a random word if the index is out of bounds.
   * 
   * @param index The index of the verb you want.
   * @return A French verb at that index or at a random index. 
   */
  public Verb getVerb(int index)
  {
    if (index>=0 && index<verbs.size())
      return verbs.get(index);
    return verbs.get((int)(Math.random ()*verbs.size()));
  }
  
  /** 
   * Gets the noun at the given index or a random word if the index is out of bounds.
   * 
   *  <p> Gets the noun at the given index or a random word if the index is out of bounds.
   * 
   * @param index The index of the noun you want.
   * @return A French noun at that index or at a random index. 
   */
  public Noun getNoun(int index)
  {
    if (index>=0 && index<nouns.size())
      return nouns.get(index);
    return nouns.get((int)(Math.random ()*nouns.size()));
  }
  
  /** 
   * Gets the adjective at the given index or a random word if the index is out of bounds.
   * 
   *  <p> Gets the adjective at the given index or a random word if the index is out of bounds.
   * 
   * @param index The index of the adjective you want.
   * @return A French adjective at that index or at a random index. 
   */
  public Adjective getAdjective(int index)
  {
    if (index>=0 && index<adjectives.size())
      return adjectives.get(index);
    return adjectives.get((int)(Math.random ()*adjectives.size()));
  }
  
  /** 
   * Gets the subject at the given index or a random word if the index is out of bounds.
   * 
   *  <p> Gets the subject at the given index or a random word if the index is out of bounds.
   * 
   * @param index The index of the subject you want.
   * @return A French subject at that index or at a random index. 
   */
  public Word getSubject(int index)
  {
    if (index>=0 && index<subjects.size())
      return subjects.get(index);
    return subjects.get((int)(Math.random ()*subjects.size()));
  }
  
  /** 
   * Returns the Word of the word given.
   * 
   *  <p> Returns the Word of the word given.
   *  <p> It does this by using a for each loop to go through each list and returning the word when it has a match. 
   *  Else it returns null. 
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type                 Description
   * <p>
   * n           Noun                 Loop variable.
   * <p>
   * a           Adjective            Loop variable.
   * <p>
   * v           Verb                 Loop variable.
   * <p> 
   * s           Word                 Loop variable.
   * 
   * @param word The word you want the Word of. 
   * @return A Word object that matches word.
   */
  public Word getWordObject (String word)
  {
    for (Word s : subjects)
    {
      if (s.getWord ().equals (word))
 return s; 
    }
    for (Noun n : nouns)
    {
      if (n.getWord ().equals (word))
 return n; 
    }
    for (Adjective a : adjectives)
    {
      if (a.getWord ("Male").equals (word))
 return a; 
    }
    for (Verb v : verbs)
    {
      if (v.getWord ().equals (word))
 return v; 
    }
    return null;
  }
  
  /** 
   * Returns the type of the word given. 
   * 
   *  <p> Returns the type of the word given. 
   *  If it is in the list of subjects, it returns "S". 
   *  If it is in the list of nouns, it returns "N".
   *  If it is in the list of adjectives, it returns "A". 
   *  If it is in the list of verbs, it returns "V". 
   *  Otherwise it returns "". 
   * 
   *  A for-each loop is used for the nouns, adjectives, and verbs to traverse the respective lists. 
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type                 Description
   * <p>
   * n           Noun                 Loop variable.
   * <p>
   * a           Adjective            Loop variable.
   * <p>
   * v           Verb                 Loop variable.
   * <p> 
   * s           Word                 Loop variable.
   * 
   * @param word The word you want the type of. 
   * @return A letter that defines the word's type.  
   */
  public String getType (String word)
  {
    for (Word s : subjects)
    {
      if (s.getWord ().equals (word))
 return "S"; 
    }
    for (Noun n : nouns)
    {
      if (n.getWord ().equals (word))
      {
 return "N"; 
      }}
    for (Adjective a : adjectives)
    {
      if (a.getWord ("Male").equals (word))
 return "A"; 
    }
    for (Verb v : verbs)
    {
      if (v.getWord ().equals (word))
 return "V"; 
    }
    return "";
  }
  
  /** 
   * Returns a sentence.  
   * 
   *  <p> Returns the sentence. 
   *  It first gets a random sentence from the list. 
   *  Then it runs a while loop splitting the sentence into tokens (splitter is " "). 
   *  It breaks when there are no spaces left. 
   *  It then returns the list of tokens. 
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type                 Description
   * <p>
   * temp        ArrayList            Stores the sentence in list form (each word is an element). 
   * <p>
   * sentence    String               Stores the sentence. 
   * 
   * @param difficulty The difficulty of the sentence you want. 
   * @return A sentence in ArrayList form.  
   */
  public ArrayList <String> getSentence (int difficulty)
  {
    ArrayList <String> temp = new ArrayList <String> (); 
    int tempSize = sentences.indexOf ("Medium"); 
    String sentence = sentences.get((int)(Math.random ()*tempSize));;
    
    if (difficulty == 2)
    {
      tempSize = sentences.indexOf ("Hard") - sentences.indexOf ("Medium") - 1;
      sentence = sentences.get((int)(Math.random ()*tempSize) + sentences.indexOf ("Medium") + 1);
    }
    else
    {
      if (difficulty == 3)
    {
      tempSize = sentences.size () - sentences.indexOf ("Hard") - 1;
      sentence = sentences.get((int)(Math.random ()*tempSize) + sentences.indexOf ("Hard") + 1);
    }
    }
    
    
    while (true)
    {
       if (sentence.indexOf (" ") == -1)
 break;
      temp.add (sentence.substring (0, sentence.indexOf (" "))); 
      sentence = sentence.substring (sentence.indexOf (" ") + 1);
    }
    temp.add (sentence); 
    return temp; 
  }
  
  /** 
   * Gets the list of HighScores. 
   * 
   *  <p> Gets the list of HighScores. 
   * 
   * @return The list of HighScores.
   */
  public ArrayList <HighScore> getHighScores()
  {
    return highScores;
  }
  
  
}
