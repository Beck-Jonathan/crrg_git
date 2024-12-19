package com.beck.crrg_git.crrg.models;

public class Sponsor_Tag_VM extends Sponsor_Tag{
  private Picture Picture;
  public Sponsor_Tag_VM(Sponsor_Tag sponsor_tag, Picture picture) {
    super(sponsor_tag.getSponsor_ID(),sponsor_tag.getPicture_ID(),sponsor_tag.getDescription(),sponsor_tag.getis_Primary());
    this.Picture = picture;
  }

  public Sponsor_Tag_VM() {

  }

  public com.beck.crrg_git.crrg.models.Picture getPicture() {
    return Picture;
  }

  public void setPicture(com.beck.crrg_git.crrg.models.Picture picture) {
    Picture = picture;
  }
}
