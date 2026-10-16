package com.beck.crrg_git.crrg.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TagTest {
  private Tag _tag;
  @BeforeEach
  public void setup(){
    _tag = new Tag();
  }
  @AfterEach
  public void teardown(){
    _tag = null;
  }

  /**
   <p> Tests That the default constructor for the Tag object works </p>
   */
  @Test
  public void testTagDefaultConstructorSetsNoVariables(){
    Tag _tag= new Tag();
    Assertions.assertNull(_tag.getTag_ID());
    Assertions.assertNull(_tag.getSkater_ID());
    Assertions.assertNull(_tag.getPicture_ID());
    Assertions.assertNull(_tag.getXcord());
    Assertions.assertNull(_tag.getYcord());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Tag object works </p>
   */
  @Test
  public void testTagParameterizedConstructorSetsAllVariables(){
    Tag _tag= new Tag(
        7514,
        "wOhytURguZSUXKMNKrPpoojdgtwdmemkEN",
        1817,
        9621,
        7018
    );
    Assertions.assertEquals(7514,_tag.getTag_ID());
    Assertions.assertEquals("wOhytURguZSUXKMNKrPpoojdgtwdmemkEN",_tag.getSkater_ID());
    Assertions.assertEquals(1817,_tag.getPicture_ID());
    Assertions.assertEquals(9621,_tag.getXcord());
    Assertions.assertEquals(7018,_tag.getYcord());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Tag object works </p>
   */
  @Test
  public void testTagKeyedParameterizedConstructorSetsKeyedVariables(){
    Tag _tag= new Tag(
        2136
    );
    Assertions.assertEquals(2136,_tag.getTag_ID());
    Assertions.assertNull(_tag.getSkater_ID());
    Assertions.assertNull(_tag.getPicture_ID());
    Assertions.assertNull(_tag.getXcord());
    Assertions.assertNull(_tag.getYcord());
  }

  /**
   <p> Tests That the Setters for the Tag.Tag_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testTagThrowsIllegalArgumentExceptionIfTag_IDTooSmall(){
    int Tag_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_tag.setTag_ID(Tag_ID);});
  }

  /**
   <p> Tests That the Setters for the Tag.Tag_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testTagThrowsIllegalArgumentExceptionIfTag_IDTooBig(){
    int Tag_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_tag.setTag_ID(Tag_ID);});
  }

  /**
   <p> Tests That the Setters for the Tag.Tag_ID field work </p>
   */
  @Test
  public void testTagSetsTag_ID(){
    int Tag_ID = 1566;
    _tag.setTag_ID(Tag_ID);
    Assertions.assertEquals(Tag_ID, _tag.getTag_ID());
  }

  /**
   <p> Tests That the Setters for the Tag.Skater_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testTagThrowsIllegalArgumentExceptionIfSkater_IDTooShort(){
    String Skater_ID = "se";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_tag.setSkater_ID(Skater_ID);});
  }

  /**
   <p> Tests That the Setters for the Tag.Skater_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testTagThrowsIllegalArgumentExceptionIfSkater_IDTooLong(){
    String Skater_ID = "PjSASvdZsOGYsPQhXjXojeZIAyoeGwKyITtdaA";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_tag.setSkater_ID(Skater_ID);});
  }

  /**
   <p> Tests That the Setters for the Tag.Skater_ID field work </p>
   */
  @Test
  public void testSetSkater_IDSetsSkater_ID(){
    String Skater_ID = "TkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFSD";
    _tag.setSkater_ID(Skater_ID);
    Assertions.assertEquals(Skater_ID,_tag.getSkater_ID());
  }

  /**
   <p> Tests That the Setters for the Tag.Picture_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testTagThrowsIllegalArgumentExceptionIfPicture_IDTooSmall(){
    int Picture_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_tag.setPicture_ID(Picture_ID);});
  }

  /**
   <p> Tests That the Setters for the Tag.Picture_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testTagThrowsIllegalArgumentExceptionIfPicture_IDTooBig(){
    int Picture_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_tag.setPicture_ID(Picture_ID);});
  }

  /**
   <p> Tests That the Setters for the Tag.Picture_ID field work </p>
   */
  @Test
  public void testTagSetsPicture_ID(){
    int Picture_ID = 8337;
    _tag.setPicture_ID(Picture_ID);
    Assertions.assertEquals(Picture_ID, _tag.getPicture_ID());
  }

  /**
   <p> Tests That the Setters for the Tag.Xcord field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testTagThrowsIllegalArgumentExceptionIfXcordTooSmall(){
    int Xcord = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_tag.setXcord(Xcord);});
  }

  /**
   <p> Tests That the Setters for the Tag.Xcord field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testTagThrowsIllegalArgumentExceptionIfXcordTooBig(){
    int Xcord = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_tag.setXcord(Xcord);});
  }

  /**
   <p> Tests That the Setters for the Tag.Xcord field work </p>
   */
  @Test
  public void testTagSetsXcord(){
    int Xcord = 4958;
    _tag.setXcord(Xcord);
    Assertions.assertEquals(Xcord, _tag.getXcord());
  }

  /**
   <p> Tests That the Setters for the Tag.Ycord field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testTagThrowsIllegalArgumentExceptionIfYcordTooSmall(){
    int Ycord = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_tag.setYcord(Ycord);});
  }

  /**
   <p> Tests That the Setters for the Tag.Ycord field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testTagThrowsIllegalArgumentExceptionIfYcordTooBig(){
    int Ycord = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_tag.setYcord(Ycord);});
  }

  /**
   <p> Tests That the Setters for the Tag.Ycord field work </p>
   */
  @Test
  public void testTagSetsYcord(){
    int Ycord = 6500;
    _tag.setYcord(Ycord);
    Assertions.assertEquals(Ycord, _tag.getYcord());
  }


  /**
   <p> Tests That the CompareTo Method for the Tag object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    Tag smaller = new Tag();
    Tag bigger = new Tag();
//to compare a smaller and larger Tag_ID
    smaller.setTag_ID(10);
    bigger.setTag_ID(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Tag_ID as equal.
    smaller.setTag_ID(20);
//to compare a smaller and larger Skater_ID
    smaller.setSkater_ID("TkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
    bigger.setSkater_ID("UkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Skater_ID as equal.
    smaller.setSkater_ID("UkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
//to compare a smaller and larger Picture_ID
    smaller.setPicture_ID(10);
    bigger.setPicture_ID(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Picture_ID as equal.
    smaller.setPicture_ID(20);
//to compare a smaller and larger Xcord
    smaller.setXcord(10);
    bigger.setXcord(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Xcord as equal.
    smaller.setXcord(20);
//to compare a smaller and larger Ycord
    smaller.setYcord(10);
    bigger.setYcord(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Ycord as equal.
    smaller.setYcord(20);
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

