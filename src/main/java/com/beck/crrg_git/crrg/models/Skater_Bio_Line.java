package com.beck.crrg_git.crrg.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */
import org.jetbrains.annotations.NotNull;

public class Skater_Bio_Line implements Comparable<Skater_Bio_Line> {
  private String Skater_Bio_Line_ID;
  private String Skater_ID;
  private String Prompt;
  private String Answer;

  public Skater_Bio_Line(){}

  public Skater_Bio_Line(String Skater_Bio_Line_ID, String Skater_ID, String Prompt, String Answer) {

    this.Skater_Bio_Line_ID = Skater_Bio_Line_ID;
    this.Skater_ID = Skater_ID;
    this.Prompt = Prompt;
    this.Answer = Answer;
  }

  public Skater_Bio_Line(String Skater_Bio_Line_ID) {

    this.Skater_Bio_Line_ID = Skater_Bio_Line_ID;
  }

  /**
   * <p> Gets the Skater_Bio_Line_ID of the associated Skater_Bio_Line object </p>
   * @return the Skater_Bio_Line_ID of this Skater_Bio_Line object.
   */
  public String getSkater_Bio_Line_ID() {
    return Skater_Bio_Line_ID;
  }

  /**
   * <p> Sets the Skater_Bio_Line_ID of the associated Skater_Bio_Line object </p>
   * @param Skater_Bio_Line_ID the skater_bio_line_id of the skater_bio_line,
   * throws IllegalArgumentException if Skater_Bio_Line_ID under 3 characters or longer than 36 characters
   */
  public void setSkater_Bio_Line_ID(String Skater_Bio_Line_ID) {
    if(Skater_Bio_Line_ID.length()<36){
      throw new IllegalArgumentException("Skater_Bio_Line_ID is too short.");
    }
    if(Skater_Bio_Line_ID.length()>36){
      throw new IllegalArgumentException("Skater_Bio_Line_ID is too long.");
    }
    this.Skater_Bio_Line_ID = Skater_Bio_Line_ID;
  }

  /**
   * <p> Gets the Skater_ID of the associated Skater_Bio_Line object </p>
   * @return the Skater_ID of this Skater_Bio_Line object.
   */
  public String getSkater_ID() {
    return Skater_ID;
  }

  /**
   * <p> Sets the Skater_ID of the associated Skater_Bio_Line object </p>
   * @param Skater_ID the skater_id of the skater_bio_line,
   * throws IllegalArgumentException if Skater_ID under 3 characters or longer than 36 characters
   */
  public void setSkater_ID(String Skater_ID) {
    if(Skater_ID.length()<36){
      throw new IllegalArgumentException("Skater_ID is too short.");
    }
    if(Skater_ID.length()>36){
      throw new IllegalArgumentException("Skater_ID is too long.");
    }
    this.Skater_ID = Skater_ID;
  }

  /**
   * <p> Gets the Prompt of the associated Skater_Bio_Line object </p>
   * @return the Prompt of this Skater_Bio_Line object.
   */
  public String getPrompt() {
    return Prompt;
  }

  /**
   * <p> Sets the Prompt of the associated Skater_Bio_Line object </p>
   * @param Prompt the prompt of the skater_bio_line,
   * throws IllegalArgumentException if Prompt under 3 characters or longer than 255 characters
   */
  public void setPrompt(String Prompt) {
    Prompt = Prompt.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Prompt.length()<4){
      throw new IllegalArgumentException("Prompt is too short.");
    }
    if(Prompt.length()>255){
      throw new IllegalArgumentException("Prompt is too long.");
    }
    this.Prompt = Prompt;
  }

  /**
   * <p> Gets the Answer of the associated Skater_Bio_Line object </p>
   * @return the Answer of this Skater_Bio_Line object.
   */
  public String getAnswer() {
    return Answer;
  }

  /**
   * <p> Sets the Answer of the associated Skater_Bio_Line object </p>
   * @param Answer the answer of the skater_bio_line,
   * throws IllegalArgumentException if Answer under 3 characters or longer than 1000 characters
   */
  public void setAnswer(String Answer) {
    Answer = Answer.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Answer.length()<4){
      throw new IllegalArgumentException("Answer is too short.");
    }
    if(Answer.length()>1000){
      throw new IllegalArgumentException("Answer is too long.");
    }
    this.Answer = Answer;
  }
  @Override
  public int compareTo(@NotNull Skater_Bio_Line o) {
    if (this.Skater_Bio_Line_ID.compareTo(o.Skater_Bio_Line_ID)<0){
      return -1;
    }
    else if(this.Skater_Bio_Line_ID.compareTo(o.Skater_Bio_Line_ID) > 0){
      return 1;
    }
    if (this.Skater_ID.compareTo(o.Skater_ID)<0){
      return -1;
    }
    else if(this.Skater_ID.compareTo(o.Skater_ID) > 0){
      return 1;
    }
    if (this.Prompt.compareTo(o.Prompt)<0){
      return -1;
    }
    else if(this.Prompt.compareTo(o.Prompt) > 0){
      return 1;
    }
    if (this.Answer.compareTo(o.Answer)<0){
      return -1;
    }
    else if(this.Answer.compareTo(o.Answer) > 0){
      return 1;
    }
    return 0;
  }

}

