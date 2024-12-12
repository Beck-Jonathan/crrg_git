package com.beck.beck_demos.crrg.models;

import java.util.List;

public class Sponsor_VM extends Sponsor {

  private List<Sponsor_Tag> Sponsor_Tags;

  public Sponsor_VM(Sponsor sponsor, List<Sponsor_Tag> sponsor_tags) {
    super(sponsor.getSponsor_ID(),sponsor.getTier_ID(),sponsor.getwebsite(),sponsor.getDescription(),sponsor.getis_active());
    this.Sponsor_Tags = sponsor_tags;
  }

  public List<Sponsor_Tag> getSponsor_Tags() {
    return Sponsor_Tags;
  }

  public void setSponsor_Tags(List<Sponsor_Tag> sponsor_Tags) {
    Sponsor_Tags = sponsor_Tags;
  }
}