package ISP.classes;
import java.io.File; 
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * This class is responsible for playing sounds when the user gets something right or wrong.
 * 
 * @author Reyno Tilikaynen
 * @author Jonah Haber
 * @version 1.0 June 11th 2013
 * 
 * 
 */
public class Sounds
{
  /**
   * Stores the correct sound.
   */
  Clip correct;
  /**
   * Stores the incorrect sound.
   */
  Clip incorrect;
  
  /** Creates a Sounds object and stores the Clip variables. 
   * 
   * <p> Creates a Sounds object and tries to store the Clip variables.
   * 
   * <p> Variable Dictionary: 
   * <p>
   * Name        Type                        Description
   * <p>
   * e       NullPointerException            Stores the exception thrown.
   * <p>
   * e       LineUnavailableException        Stores the exception thrown.
   * <p>
   * e       IOException                     Stores the exception thrown.
   * <p>
   * e       UnsupportedAudioFileException   Stores the exception thrown.
   */
  public Sounds ()
  {
    try
    {
 incorrect = AudioSystem.getClip();
 incorrect.open(AudioSystem.getAudioInputStream(new File("ISP/sounds/incorrect.wav")));
 correct = AudioSystem.getClip();
 correct.open(AudioSystem.getAudioInputStream(new File("ISP/sounds/correct.wav")));
    }
    catch (NullPointerException e)
    {
    }
    catch (LineUnavailableException e)
{}
catch (IOException e)
{}
catch (UnsupportedAudioFileException e) {}
      
  }

  /** 
   * Plays the correct sound.
   * 
   *  <p> Plays the correct sound. 
   *  <p> If it is already running, it stops it. 
   *  <p> It rewinds the clip to the beginning and then plays it.
   *  
   */
  public void playCorrect ()
  {
    if (correct.isRunning ())
      correct.stop (); 
      correct.setFramePosition (0);
    correct.start ();
  }
  
  /** 
   * Plays the incorrect sound.
   * 
   *  <p> Plays the incorrect sound. 
   *  <p> If it is already running, it stops it. 
   *  <p> It rewinds the clip to the beginning and then plays it.
   *  
   */
  public void playIncorrect ()
  {
    if (incorrect.isRunning ())
      incorrect.stop (); 
      incorrect.setFramePosition (0);
    incorrect.start ();
  }
}
