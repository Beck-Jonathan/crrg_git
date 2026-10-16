package com.beck.crrg_git.crrg.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */
import org.jetbrains.annotations.NotNull;

public class Tag implements Comparable<Tag> {
  private Integer Tag_ID;
  private String Skater_ID;
  private Integer Picture_ID;
  private Integer Xcord;
  private Integer Ycord;

  public Tag(){}

  public Tag(Integer Tag_ID, String Skater_ID, Integer Picture_ID, Integer Xcord, Integer Ycord) {

    this.Tag_ID = Tag_ID;
    this.Skater_ID = Skater_ID;
    this.Picture_ID = Picture_ID;
    this.Xcord = Xcord;
    this.Ycord = Ycord;
  }

  public Tag(Integer Tag_ID) {

    this.Tag_ID = Tag_ID;
  }

  /**
   * <p> Gets the Tag_ID of the associated Tag object </p>
   * @return the Tag_ID of this Tag object.
   */
  public Integer getTag_ID() {
    return Tag_ID;
  }

  /**
   * <p> Sets the Tag_ID of the associated Tag object </p>
   * @param Tag_ID the tag_id of the tag,
   * throws IllegalArgumentException if Tag_ID is negative or greater than 10,000
   */
  public void setTag_ID(Integer Tag_ID) {
    if (Tag_ID<0||Tag_ID>10000){
      throw new IllegalArgumentException("Tag_ID Can Not Be Negative");
    }
    this.Tag_ID = Tag_ID;
  }

  /**
   * <p> Gets the Skater_ID of the associated Tag object </p>
   * @return the Skater_ID of this Tag object.
   */
  public String getSkater_ID() {
    return Skater_ID;
  }

  /**
   * <p> Sets the Skater_ID of the associated Tag object </p>
   * @param Skater_ID the skater_id of the tag,
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
   * <p> Gets the Picture_ID of the associated Tag object </p>
   * @return the Picture_ID of this Tag object.
   */
  public Integer getPicture_ID() {
    return Picture_ID;
  }

  /**
   * <p> Sets the Picture_ID of the associated Tag object </p>
   * @param Picture_ID the picture_id of the tag,
   * throws IllegalArgumentException if Picture_ID is negative or greater than 10,000
   */
  public void setPicture_ID(Integer Picture_ID) {
    if (Picture_ID<0||Picture_ID>10000){
      throw new IllegalArgumentException("Picture_ID Can Not Be Negative");
    }
    this.Picture_ID = Picture_ID;
  }

  /**
   * <p> Gets the Xcord of the associated Tag object </p>
   * @return the Xcord of this Tag object.
   */
  public Integer getXcord() {
    return Xcord;
  }

  /**
   * <p> Sets the Xcord of the associated Tag object </p>
   * @param Xcord the xcord of the tag,
   * throws IllegalArgumentException if Xcord is negative or greater than 10,000
   */
  public void setXcord(Integer Xcord) {
    if (Xcord<0||Xcord>10000){
      throw new IllegalArgumentException("Xcord Can Not Be Negative");
    }
    this.Xcord = Xcord;
  }

  /**
   * <p> Gets the Ycord of the associated Tag object </p>
   * @return the Ycord of this Tag object.
   */
  public Integer getYcord() {
    return Ycord;
  }

  /**
   * <p> Sets the Ycord of the associated Tag object </p>
   * @param Ycord the ycord of the tag,
   * throws IllegalArgumentException if Ycord is negative or greater than 10,000
   */
  public void setYcord(Integer Ycord) {
    if (Ycord<0||Ycord>10000){
      throw new IllegalArgumentException("Ycord Can Not Be Negative");
    }
    this.Ycord = Ycord;
  }
  @Override
  public int compareTo(@NotNull Tag o) {
    if (this.Tag_ID.compareTo(o.Tag_ID)<0){
      return -1;
    }
    else if(this.Tag_ID.compareTo(o.Tag_ID) > 0){
      return 1;
    }
    if (this.Skater_ID.compareTo(o.Skater_ID)<0){
      return -1;
    }
    else if(this.Skater_ID.compareTo(o.Skater_ID) > 0){
      return 1;
    }
    if (this.Picture_ID.compareTo(o.Picture_ID)<0){
      return -1;
    }
    else if(this.Picture_ID.compareTo(o.Picture_ID) > 0){
      return 1;
    }
    if (this.Xcord.compareTo(o.Xcord)<0){
      return -1;
    }
    else if(this.Xcord.compareTo(o.Xcord) > 0){
      return 1;
    }
    if (this.Ycord.compareTo(o.Ycord)<0){
      return -1;
    }
    else if(this.Ycord.compareTo(o.Ycord) > 0){
      return 1;
    }
    return 0;
  }

}

