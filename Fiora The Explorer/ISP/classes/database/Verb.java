package ISP.classes.database;
import java.util.ArrayList;

/**
 * This class is responsible for storing a verb. 
 * 
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 * 
 */
public class Verb extends Word
{ 
  private ArrayList <String> conjugations;
  
  /** Creates a new Verb object. 
   *
   * <p> Creates a new Verb object. 
   * 
   * @param infinitif The infinitif of the verb. 
   */
  public Verb (String infinitif, ArrayList <String> conjugations, String translation) 
  { 
    super (infinitif, translation);
    this.conjugations = conjugations;
  }
  
  /** Returns the verb conjugated to that subject.  
   *
   * <p> Returns the verb conjugated to that subject.
   * <p> Depending on the subject, the correct conjugation is returned (if statements used). 
   * 
   * @param subject The subject the verb will be conjugated to. 
   * @return The conjugated verb. 
   */
  public String conjugate (String subject)
  {
    if (subject.equalsIgnoreCase ("Je"))
      return (conjugations.get (0));
    if (subject.equalsIgnoreCase ("Tu"))
      return (conjugations.get (1));
    if (subject.equalsIgnoreCase ("Nous"))
      return (conjugations.get (3));
    if (subject.equalsIgnoreCase ("Vous"))
      return (conjugations.get (4));
    if (subject.equalsIgnoreCase ("Ils") || subject.equalsIgnoreCase ("Elles"))
      return (conjugations.get (5));
      return (conjugations.get (2));
  }
  
  /** Returns a string representation of this Verb.
   * 
   * <p> Returns a string representation of this Verb, including the conjugations (for loop 0-5 increment 1 used).
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type              Description
   * <p>
   * temp        String            String representation.
   * <p> 
   * x           int               Loop variable.
   * 
   * @return A string representation of this Verb.
   */
  public String toString ()
  {
    String temp = getWord () + " ";
    for (int x = 0; x < 6; x++)
    {
      temp += conjugations.get (x) + " ";
      
    }
    temp += getTranslation ();
    return temp;
  }
}
