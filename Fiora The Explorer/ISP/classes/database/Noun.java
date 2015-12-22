package ISP.classes.database;
/**
 * This class is responsible for storing a noun. 
 * 
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 * 
 */
public class Noun extends Word
{ 
  /*
   * Stores the gender of the word (male or female).
   */
  private String gender; 
  
  /** Creates a new Noun object.  
   *
   * <p> Creates a new Verb object. 
   * 
   * @param word The noun. 
   * @param gender The gender of the noun. 
   */
  public Noun(String word, String gender, String translation) 
  { 
    super (word, translation); 
    this.gender = gender; 
  } 
  
  /** Returns the gender of the noun.
   *
   * <p> Returns the gender of the noun.
   * 
   * @return The gender of the noun. 
   */
  public String getGender ()
  {
    return gender;
  }
  
  /** Returns a string representation of this noun. 
   *
   * <p> Returns a string representation of this noun. 
   * 
   * @return A string representation of this noun.
   */
  public String toString ()
  {
    return getWord () + " " + getGender () + getTranslation ();
  }
}
