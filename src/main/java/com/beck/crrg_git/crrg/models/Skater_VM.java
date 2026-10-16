package com.beck.crrg_git.crrg.models;

import java.util.List;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Skater_VM extends Skater {
  private List<Tag> Tags;
  private List<Skater_Bio_Line> Skater_Bio_Lines;

  public Skater_VM(){}

  public Skater_VM(Skater skater){
    super(skater.getSkater_ID(), skater.getNumber(), skater.getDerby_Name(), skater.getFirst_Name(), skater.getLast_Name(), skater.getis_active(), skater.getStart_Date());
  }



  public Skater_VM(Skater skater,List<Tag> tags,List<Skater_Bio_Line> skater_bio_lines){
    super( skater.getSkater_ID(),  skater.getNumber(),  skater.getDerby_Name(),  skater.getFirst_Name(),  skater.getLast_Name(),  skater.getis_active(),  skater.getStart_Date());
    this.Tags = tags;
    this.Skater_Bio_Lines = skater_bio_lines;

  }
  public List<Tag> getTags() {
    return Tags;
  }
  public void setTags(List<Tag> _tags) {
    this.Tags = _tags;
  }
  public List<Skater_Bio_Line> getSkater_Bio_Lines() {
    return Skater_Bio_Lines;
  }
  public void setSkater_Bio_Lines(List<Skater_Bio_Line> _skater_bio_lines) {
    this.Skater_Bio_Lines = _skater_bio_lines;
  }

}

