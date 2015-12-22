package ISP.classes.database;
/**
 * This class is responsible for storing a verb. 
 * 
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 * 
 */
public class Word 
{
  /* 
   * Stores the word.
   */
  private String word; 
  /* 
   * Stores the translation.
   */
  private String translation; 
  
  /* This class constructor creates a Word object that stores a word. 
   * 
   * <p> This class constructor creates a Word object that stores a word. 
   * 
   */
  public Word (String word, String translation) 
  { 
    this.word = word;  
    this.translation = translation; 
  }  
  
  /* This method returns the word. 
   * 
   * <p> This method returns the word. 
   * 
   * @returns The word. 
   */
  public String getWord ()
  {
    return word; 
  }
  /* This method returns the translation. 
   * 
   * <p> This method returns the translation. 
   * 
   * @returns The translation. 
   */
  public String getTranslation ()
  {
    return translation;
  }
  
  /* This method returns the string representation of this word. 
   * 
   * <p> This method returns the string representation of this word. 
   * 
   * @returns The string representation of this word. 
   */
  public String toString ()
  {
    return word + " " + translation;
  }
}
