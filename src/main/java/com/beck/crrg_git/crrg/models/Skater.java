package com.beck.crrg_git.crrg.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Skater implements Comparable<Skater> {
  private String Skater_ID;
  private String Number;
  private String Derby_Name;
  private String First_Name;
  private String Last_Name;
  private boolean is_active;
  private Date Start_Date;

  public Skater(){}

  public Skater(String Skater_ID, String Number, String Derby_Name, String First_Name, String Last_Name, boolean is_active, Date Start_Date) {

    this.Skater_ID = Skater_ID;
    this.Number = Number;
    this.Derby_Name = Derby_Name;
    this.First_Name = First_Name;
    this.Last_Name = Last_Name;
    this.is_active = is_active;
    this.Start_Date = Start_Date;
  }

  public Skater(String Skater_ID) {

    this.Skater_ID = Skater_ID;
  }

  /**
   * <p> Gets the Skater_ID of the associated Skater object </p>
   * @return the Skater_ID of this Skater object.
   */
  public String getSkater_ID() {
    return Skater_ID;
  }

  /**
   * <p> Sets the Skater_ID of the associated Skater object </p>
   * @param Skater_ID the skater_id of the skater,
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
   * <p> Gets the Number of the associated Skater object </p>
   * @return the Number of this Skater object.
   */
  public String getNumber() {
    return Number;
  }

  /**
   * <p> Sets the Number of the associated Skater object </p>
   * @param Number the number of the skater,
   * throws IllegalArgumentException if Number under 3 characters or longer than 5 characters
   */
  public void setNumber(String Number) {
    Number = Number.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Number.length()<1){
      throw new IllegalArgumentException("Number is too short.");
    }
    if(Number.length()>5){
      throw new IllegalArgumentException("Number is too long.");
    }
    this.Number = Number;
  }

  /**
   * <p> Gets the Derby_Name of the associated Skater object </p>
   * @return the Derby_Name of this Skater object.
   */
  public String getDerby_Name() {
    return Derby_Name;
  }

  /**
   * <p> Sets the Derby_Name of the associated Skater object </p>
   * @param Derby_Name the derby_name of the skater,
   * throws IllegalArgumentException if Derby_Name under 3 characters or longer than 255 characters
   */
  public void setDerby_Name(String Derby_Name) {
    Derby_Name = Derby_Name.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Derby_Name.length()<4){
      throw new IllegalArgumentException("Derby_Name is too short.");
    }
    if(Derby_Name.length()>255){
      throw new IllegalArgumentException("Derby_Name is too long.");
    }
    this.Derby_Name = Derby_Name;
  }

  /**
   * <p> Gets the First_Name of the associated Skater object </p>
   * @return the First_Name of this Skater object.
   */
  public String getFirst_Name() {
    return First_Name;
  }

  /**
   * <p> Sets the First_Name of the associated Skater object </p>
   * @param First_Name the first_name of the skater,
   * throws IllegalArgumentException if First_Name under 3 characters or longer than 255 characters
   */
  public void setFirst_Name(String First_Name) {
    First_Name = First_Name.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(First_Name.length()<4){
      throw new IllegalArgumentException("First_Name is too short.");
    }
    if(First_Name.length()>255){
      throw new IllegalArgumentException("First_Name is too long.");
    }
    this.First_Name = First_Name;
  }

  /**
   * <p> Gets the Last_Name of the associated Skater object </p>
   * @return the Last_Name of this Skater object.
   */
  public String getLast_Name() {
    return Last_Name;
  }

  /**
   * <p> Sets the Last_Name of the associated Skater object </p>
   * @param Last_Name the last_name of the skater,
   * throws IllegalArgumentException if Last_Name under 3 characters or longer than 255 characters
   */
  public void setLast_Name(String Last_Name) {
    Last_Name = Last_Name.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Last_Name.length()<4){
      throw new IllegalArgumentException("Last_Name is too short.");
    }
    if(Last_Name.length()>255){
      throw new IllegalArgumentException("Last_Name is too long.");
    }
    this.Last_Name = Last_Name;
  }

  /**
   * <p> Gets the is_active of the associated Skater object </p>
   * @return the is_active of this Skater object.
   */
  public boolean getis_active() {
    return is_active;
  }

  /**
   * <p> Sets the is_active of the associated Skater object </p>
   * @param is_active the is_active of the skater,
   * throws IllegalArgumentException if is_active is outside of a logical range
   */
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

  /**
   * <p> Gets the Start_Date of the associated Skater object </p>
   * @return the Start_Date of this Skater object.
   */
  public Date getStart_Date() {
    return Start_Date;
  }

  /**
   * <p> Sets the Start_Date of the associated Skater object </p>
   * @param Start_Date the start_date of the skater,
   * throws IllegalArgumentException if Start_Date is outside of a logical range
   */
  public void setStart_Date(Date Start_Date) {
    this.Start_Date = Start_Date;
  }
  @Override
  public int compareTo(@NotNull Skater o) {
    if (this.Skater_ID.compareTo(o.Skater_ID)<0){
      return -1;
    }
    else if(this.Skater_ID.compareTo(o.Skater_ID) > 0){
      return 1;
    }
    if (this.Number.compareTo(o.Number)<0){
      return -1;
    }
    else if(this.Number.compareTo(o.Number) > 0){
      return 1;
    }
    if (this.Derby_Name.compareTo(o.Derby_Name)<0){
      return -1;
    }
    else if(this.Derby_Name.compareTo(o.Derby_Name) > 0){
      return 1;
    }
    if (this.First_Name.compareTo(o.First_Name)<0){
      return -1;
    }
    else if(this.First_Name.compareTo(o.First_Name) > 0){
      return 1;
    }
    if (this.Last_Name.compareTo(o.Last_Name)<0){
      return -1;
    }
    else if(this.Last_Name.compareTo(o.Last_Name) > 0){
      return 1;
    }
    if (!this.is_active&&o.is_active){
      return -1;
    }
    if (this.is_active&&!o.is_active){
      return 1;
    }
    if (this.Start_Date.compareTo(o.Start_Date)<0){
      return -1;
    }
    else if(this.Start_Date.compareTo(o.Start_Date) > 0){
      return 1;
    }
    return 0;
  }

}


