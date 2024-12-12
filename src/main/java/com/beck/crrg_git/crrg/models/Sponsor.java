package com.beck.beck_demos.crrg.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Sponsor {
  private String Sponsor_ID;
  private String Tier_ID;
  private String website;
  private String Description;
  private boolean is_active;

  public Sponsor(){}

  public Sponsor(String Sponsor_ID, String Tier_ID, String website, String Description, boolean is_active) {

    this.Sponsor_ID = Sponsor_ID;
    this.Tier_ID = Tier_ID;
    this.website = website;
    this.Description = Description;
    this.is_active = is_active;
  }

  public Sponsor(String Sponsor_ID) {

    this.Sponsor_ID = Sponsor_ID;
  }
  public String getSponsor_ID() {
    return Sponsor_ID;
  }
  public void setSponsor_ID(String Sponsor_ID) {
    Sponsor_ID = Sponsor_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Sponsor_ID.length()<4){
      throw new IllegalArgumentException("Sponsor_ID is too short.");
    }
    if(Sponsor_ID.length()>100){
      throw new IllegalArgumentException("Sponsor_ID is too long.");
    }
    this.Sponsor_ID = Sponsor_ID;
  }
  public String getTier_ID() {
    return Tier_ID;
  }
  public void setTier_ID(String Tier_ID) {
    Tier_ID = Tier_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Tier_ID.length()<4){
      throw new IllegalArgumentException("Tier_ID is too short.");
    }
    if(Tier_ID.length()>100){
      throw new IllegalArgumentException("Tier_ID is too long.");
    }
    this.Tier_ID = Tier_ID;
  }
  public String getwebsite() {
    return website;
  }
  public void setwebsite(String website) {
    website = website.replaceAll("^[A-Za-z0-9]+^/.*_","");
    if(website.length()<10){
      throw new IllegalArgumentException("website is too short.");
    }
    if(website.length()>100){
      throw new IllegalArgumentException("website is too long.");
    }
    this.website = website;
  }
  public String getDescription() {
    return Description;
  }
  public void setDescription(String Description) {
    Description = Description.replaceAll("^[A-Za-z0-9]+^/.*_ ","");
    if(Description.length()<4){
      throw new IllegalArgumentException("Description is too short.");
    }
    if(Description.length()>1027){
      throw new IllegalArgumentException("Description is too long.");
    }
    this.Description = Description;
  }
  public boolean getis_active() {
    return is_active;
  }
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

}

