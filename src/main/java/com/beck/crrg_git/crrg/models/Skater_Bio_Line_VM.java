package com.beck.crrg_git.crrg.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Skater_Bio_Line_VM extends Skater_Bio_Line {
  private Skater skater;

  public Skater_Bio_Line_VM(){}

  public Skater_Bio_Line_VM(Skater_Bio_Line skater_bio_line){
    super(skater_bio_line.getSkater_Bio_Line_ID(), skater_bio_line.getSkater_ID(), skater_bio_line.getPrompt(), skater_bio_line.getAnswer());
  }

  public Skater_Bio_Line_VM(Skater_Bio_Line skater_bio_line,Skater skater){
    super( skater_bio_line.getSkater_Bio_Line_ID(),  skater_bio_line.getSkater_ID(),  skater_bio_line.getPrompt(),  skater_bio_line.getAnswer());
    this.skater = skater;

  }


  public Skater getskater() {
    return skater;
  }
  public void setskater(Skater _skater) {
    this.skater = _skater;
  }

}

