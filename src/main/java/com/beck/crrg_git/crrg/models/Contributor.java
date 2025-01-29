package com.beck.crrg_git.crrg.models;

import com.beck.crrg_git.crrg.shared.MyValidators;

import java.util.regex.Matcher;
import org.jetbrains.annotations.NotNull;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Contributor implements Comparable<Contributor>{
  private Integer Contributor_ID;
  private String First_Name;
  private String Last_Name;
  private String Email;

  public Contributor(){}

  public Contributor(Integer Contributor_ID, String email) {

    this.Contributor_ID = Contributor_ID;
    this.Email = email;
  }

  public Contributor(Integer Contributor_ID, String First_Name, String Last_Name, String email) {

    this.Contributor_ID = Contributor_ID;
    this.First_Name = First_Name;
    this.Last_Name = Last_Name;
    this.Email = email;
  }
  public Integer getContributor_ID() {
    return Contributor_ID;
  }
  public void setContributor_ID(Integer Contributor_ID) {
    if (Contributor_ID<0||Contributor_ID>10000){
      throw new IllegalArgumentException("Contributor_ID Can Not Be Negative");
    }
    this.Contributor_ID = Contributor_ID;
  }
  public String getFirst_Name() {
    return First_Name;
  }
  public void setFirst_Name(String First_Name) {
    First_Name = First_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(First_Name.length()<4){
      throw new IllegalArgumentException("First_Name is too short.");
    }
    if(First_Name.length()>100){
      throw new IllegalArgumentException("First_Name is too long.");
    }
    this.First_Name = First_Name;
  }
  public String getLast_Name() {
    return Last_Name;
  }
  public void setLast_Name(String Last_Name) {
    Last_Name = Last_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(Last_Name.length()<4){
      throw new IllegalArgumentException("Last_Name is too short.");
    }
    if(Last_Name.length()>100){
      throw new IllegalArgumentException("Last_Name is too long.");
    }
    this.Last_Name = Last_Name;
  }
  public String getEmail() {
    return Email;
  }
  public void setEmail(String email) {
    Matcher matcher = MyValidators.emailPattern.matcher(email);
    if (!matcher.matches()){
      throw new IllegalArgumentException("Invalid email");
    }
    this.Email =email;
  }
  @Override
  public int compareTo(@NotNull Contributor o) {
    if (this.Contributor_ID.compareTo(o.Contributor_ID)<0){
      return -1;
    }
    else if(this.Contributor_ID.compareTo(o.Contributor_ID) > 0){
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
    if (this.Email.compareTo(o.Email)<0){
      return -1;
    }
    else if(this.Email.compareTo(o.Email) > 0){
      return 1;
    }
    return 0;
  }

}

