package com.beck.crrg_git.crrg.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Tag_VM extends Tag {
  private Skater Skater;
  private Picture Picture;

  public Tag_VM(){}

  public Tag_VM(Tag tag){
    super(tag.getTag_ID(), tag.getSkater_ID(), tag.getPicture_ID(), tag.getXcord(), tag.getYcord());
  }

  public Tag_VM(Tag tag,Skater skater,Picture picture){
    super( tag.getTag_ID(),  tag.getSkater_ID(),  tag.getPicture_ID(),  tag.getXcord(),  tag.getYcord());
    this.Skater = skater;
    this.Picture = picture;

  }


  public Skater getSkater() {
    return Skater;
  }
  public void setSkater(Skater _skater) {
    this.Skater = _skater;
  }
  public Picture getPicture() {
    return Picture;
  }
  public void setPicture(Picture _picture) {
    this.Picture = _picture;
  }

}

