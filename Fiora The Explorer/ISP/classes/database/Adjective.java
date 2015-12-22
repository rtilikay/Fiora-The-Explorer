package ISP.classes.database;
/**
 * This class is responsible for storing an adjective. 
 * 
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 * 
 */
public class Adjective extends Word
{
  /*
   *  Stores the female version of the adjective. 
   */
  private String female;
  /*
   *  Stores the plural version of the adjective. 
   */
  private String plural;  
  
  /** Creates a new adjective object. 
   *
   * <p> Creates a new Adjective object. 
   * 
   * @param male The male form of the adjective. 
   * @param female The female form of the adjective. 
   * @param plural The plural form of the adjective. 
   */
  public Adjective (String male, String female, String plural, String translation) 
  { 
    super (male, translation); 
    this.female = female;
    this.plural = plural;
  }
  
  /**  Returns the adjective in the desired form. 
   * 
   * <p> Returns the adjective in the desired form. 
   * If "Male" is asked, it returns the male form. 
   * If "Female" is asked, it returns the female form. 
   * Otherwise it returns the plural form. 
   * 
   * @param type The form to be returned. 
   * @return The adjective in that form. 
   */
  public String getWord (String type)
  {
    if (type.equals ("Male")) 
    return super.getWord ();
    if (type.equals ("Female"))
    return female; 
    return plural;
  }
  
  /** Returns a string representation of this Adjective.
   * 
   * <p> Returns a string representation of this Adjective.
   * 
   * @return A string representation of this Adjective.
   */
  public String toString ()
  {
    return super.getWord () + " " + female + " " + plural + " " + getTranslation ();
  }
}
