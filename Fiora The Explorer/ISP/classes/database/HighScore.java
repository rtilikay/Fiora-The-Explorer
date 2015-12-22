package ISP.classes.database;
/**
 * This class is responsible for storing a highscore.
 * 
 * @author Jonah Haber
 * @version 4.0 May 17th 2013
 * 
 * 
 */
public class HighScore
{
  /**
   * Highscore name.
   */
  private String scoreName;
  /**
   * Highscore score. 
   */
  private double finalScore;
  /**
   * Highscore level. 
   */
  private int level;
  
  /** 
   * Creates a new highscore object with the given name and score.
   * 
   *  <p> Creates a new highscore object with the given name and score.
   * 
   * @param name The name of the highscore. 
   * @param score The highscore. 
   *  
   */
  public HighScore (String name, double score,int level)
  {
    scoreName = name;
    finalScore = score;
    this.level=level;
  }
  
  /** 
   * Returns the score of this HighScore object.
   * 
   *  <p> Returns the score of this HighScore object.
   * 
   * @return The score of this HighScore object.
   *  
   */
  public double getScore ()
  {
    return finalScore;
  }
  
  /** 
   * Returns the name of this HighScore object.
   * 
   *  <p> Returns the name of this HighScore object.
   * 
   * @return Returns the name of this HighScore object.
   *  
   */
  public String getName ()
  {
    return scoreName;
  }
   /** 
   * Returns the level of this HighScore object.
   * 
   *  <p> Returns the level of this HighScore object.
   * 
   * @return Returns the level of this HighScore object.
   *  
   */
  public int getLevel ()
  {
    return level;
  }
  
  /** 
   * Returns a string representation of this HighScore object.
   * 
   *  <p> Returns a string representation of this HighScore object.
   * 
   * @return Returns a string representation of this HighScore object.
   *  
   */
  public String toString ()
  {
    String temp =  scoreName + "\n" + level +"\n"+ finalScore;
    return temp;
  }
}
