package com.beck.crrg_git.crrg.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class SkaterTest {
  private Skater _skater;
  @BeforeEach
  public void setup(){
    _skater = new Skater();
  }
  @AfterEach
  public void teardown(){
    _skater = null;
  }

  /**
   <p> Tests That the default constructor for the Skater object works </p>
   */
  @Test
  public void testSkaterDefaultConstructorSetsNoVariables(){
    Skater _skater= new Skater();
    Assertions.assertNull(_skater.getSkater_ID());
    Assertions.assertNull(_skater.getNumber());
    Assertions.assertNull(_skater.getDerby_Name());
    Assertions.assertNull(_skater.getFirst_Name());
    Assertions.assertNull(_skater.getLast_Name());
    Assertions.assertFalse(_skater.getis_active());
    Assertions.assertNull(_skater.getStart_Date());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Skater object works </p>
   */
  @Test
  public void testSkaterParameterizedConstructorSetsAllVariables(){
    Date testDate = new Date();
    Skater _skater= new Skater(
        "kwOhytURguZSUXKMNKrPpoojdgtwdmemkENK",
        "wiM",
        "IsePjSASvdZsOGYsPQhXjXojeZIAyoeGwKyITtdaATkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFpfEqopgVoyRCRdtjWXAefRScyAkBUgqOnWgqPprnMuiVEeZRITsVRygemfuFunaUinpWEABfQBXverGmMQPWmIZOaVnbIEcUioRQZnWcUjPNnAFPEYWCVvpZJNElsSTQJRQJuqkcWERHybXrAJwfYqPRjdiCVyEivUMhnfpqQHBNUBRKSr",
        "khDaYrmpCIRCrmJAchKjNDNPhjLfbewNQfOusyoIoykdriIOCTSVeHRvfrtNMHLOHcsBtOScWKMEVujwSsPVrMJridQWtVeIZDCBGwFZHWyOoRxkkVojkZtlspydijlNOqOPtXlBGVjGCbLudtmgiSbHTDPZglMPkjpxChdDJfqNniEpyanvOGuqISZdpQBjKIDodBhwgMBfocuNiwcMdyojKKZgoMEvlKvlNjJOeHfhphOHTYabEytqEQrfo",
        "bQfJDlKURThDTkXgMnawThKBujfrVxREXJLVJDBFPjQqVJSrKZBBSRrjPdobuVVyDJMLRDjqDxsKaSALQCEqDSetYLUUuqRyGgrWQOyiCbjqEvyRIsfJRTTidxTNbMTSIAaccTFKVivdpuSgwmMNxIHsALPOWiEUVhbaxueWEGIYuuOGtBYEgTtiuYhNnlwxVOfOCjZmqmBhQnCvtduLgPALiqRWrxwsRfxukdsGIVXyvAicDUCyxCRBctbbI",
        true,
        testDate
    );
    Assertions.assertEquals("kwOhytURguZSUXKMNKrPpoojdgtwdmemkENK",_skater.getSkater_ID());
    Assertions.assertEquals("wiM",_skater.getNumber());
    Assertions.assertEquals("IsePjSASvdZsOGYsPQhXjXojeZIAyoeGwKyITtdaATkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFpfEqopgVoyRCRdtjWXAefRScyAkBUgqOnWgqPprnMuiVEeZRITsVRygemfuFunaUinpWEABfQBXverGmMQPWmIZOaVnbIEcUioRQZnWcUjPNnAFPEYWCVvpZJNElsSTQJRQJuqkcWERHybXrAJwfYqPRjdiCVyEivUMhnfpqQHBNUBRKSr",_skater.getDerby_Name());
    Assertions.assertEquals("khDaYrmpCIRCrmJAchKjNDNPhjLfbewNQfOusyoIoykdriIOCTSVeHRvfrtNMHLOHcsBtOScWKMEVujwSsPVrMJridQWtVeIZDCBGwFZHWyOoRxkkVojkZtlspydijlNOqOPtXlBGVjGCbLudtmgiSbHTDPZglMPkjpxChdDJfqNniEpyanvOGuqISZdpQBjKIDodBhwgMBfocuNiwcMdyojKKZgoMEvlKvlNjJOeHfhphOHTYabEytqEQrfo",_skater.getFirst_Name());
    Assertions.assertEquals("bQfJDlKURThDTkXgMnawThKBujfrVxREXJLVJDBFPjQqVJSrKZBBSRrjPdobuVVyDJMLRDjqDxsKaSALQCEqDSetYLUUuqRyGgrWQOyiCbjqEvyRIsfJRTTidxTNbMTSIAaccTFKVivdpuSgwmMNxIHsALPOWiEUVhbaxueWEGIYuuOGtBYEgTtiuYhNnlwxVOfOCjZmqmBhQnCvtduLgPALiqRWrxwsRfxukdsGIVXyvAicDUCyxCRBctbbI",_skater.getLast_Name());
    Assertions.assertTrue(_skater.getis_active());
    Assertions.assertEquals(new Date(),_skater.getStart_Date());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Skater object works </p>
   */
  @Test
  public void testSkaterKeyedParameterizedConstructorSetsKeyedVariables(){
    Skater _skater= new Skater(
        "DBIyBiHenitQgPucYOhvciBXBLmPcGsfbtUY"
    );
    Assertions.assertEquals("DBIyBiHenitQgPucYOhvciBXBLmPcGsfbtUY",_skater.getSkater_ID());
    Assertions.assertNull(_skater.getNumber());
    Assertions.assertNull(_skater.getDerby_Name());
    Assertions.assertNull(_skater.getFirst_Name());
    Assertions.assertNull(_skater.getLast_Name());
    Assertions.assertFalse(_skater.getis_active());
    Assertions.assertNull(_skater.getStart_Date());
  }

  /**
   <p> Tests That the Setters for the Skater.Skater_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkaterThrowsIllegalArgumentExceptionIfSkater_IDTooShort(){
    String Skater_ID = "SY";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater.setSkater_ID(Skater_ID);});
  }

  /**
   <p> Tests That the Setters for the Skater.Skater_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkaterThrowsIllegalArgumentExceptionIfSkater_IDTooLong(){
    String Skater_ID = "pWShLmegyjHTZGXZwchTbqGmXAgANyvCqFVTMN";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater.setSkater_ID(Skater_ID);});
  }

  /**
   <p> Tests That the Setters for the Skater.Skater_ID field work </p>
   */
  @Test
  public void testSetSkater_IDSetsSkater_ID(){
    String Skater_ID = "armlZKZWpdEOxMtmlxfljLQbaRDdWmiEyeBx";
    _skater.setSkater_ID(Skater_ID);
    Assertions.assertEquals(Skater_ID,_skater.getSkater_ID());
  }

  /**
   <p> Tests That the Setters for the Skater.Number field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkaterThrowsIllegalArgumentExceptionIfNumberTooShort(){
    String Number = "";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater.setNumber(Number);});
  }

  /**
   <p> Tests That the Setters for the Skater.Number field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkaterThrowsIllegalArgumentExceptionIfNumberTooLong(){
    String Number = "JRxbyHJ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater.setNumber(Number);});
  }

  /**
   <p> Tests That the Setters for the Skater.Number field work </p>
   */
  @Test
  public void testSetNumberSetsNumber(){
    String Number = "YYc";
    _skater.setNumber(Number);
    Assertions.assertEquals(Number,_skater.getNumber());
  }

  /**
   <p> Tests That the Setters for the Skater.Derby_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkaterThrowsIllegalArgumentExceptionIfDerby_NameTooShort(){
    String Derby_Name = "ab";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater.setDerby_Name(Derby_Name);});
  }

  /**
   <p> Tests That the Setters for the Skater.Derby_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkaterThrowsIllegalArgumentExceptionIfDerby_NameTooLong(){
    String Derby_Name = "oPqwsWmYJQyasoQFuasSAbKsLwyKCKVGcJDjHgCHccJGGFNVIqVhbKFDYnKTimrvrqlexECQfUoDWwvAWPHugOwaxETXLtVOKMWdsRfMvGUBgrKvYfvOXcnDyyirZZZoLjWtWpPZHcRcMPjRuonpYsSvTguMkRnBCpgorKhVNfJyiQulaSGChSpqJYNYsdFwSetLvihShLjJpNaSUhoALqDMuprLwZuRacXjmcgBdKgtaPWHRIiTXeWHUqxrFSAMl";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater.setDerby_Name(Derby_Name);});
  }

  /**
   <p> Tests That the Setters for the Skater.Derby_Name field work </p>
   */
  @Test
  public void testSetDerby_NameSetsDerby_Name(){
    String Derby_Name = "kQFNycKYCNeOcnpHlqwIwwSeHkhIrfgoGBEJFJtMYIFOKCwJPcmSmbqqUtJbwZEgoCckmNkJIBGaSXLjnOROhSOafJMtwXUCncZpTwOSqENUrQEhYQbdVTAsfVEvqhAeCtkQyWyXnegTfuXfcBpZAvVfAdWfruocTXZsFexVLRiJLFdMCsnuTuVBIlkpqRDOJduKJTlqFLaVRQmiFwcPwWdrqvvrhBXNkyJePoaWZDclDgSuEvirsydOedPMQ";
    _skater.setDerby_Name(Derby_Name);
    Assertions.assertEquals(Derby_Name,_skater.getDerby_Name());
  }

  /**
   <p> Tests That the Setters for the Skater.First_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkaterThrowsIllegalArgumentExceptionIfFirst_NameTooShort(){
    String First_Name = "Qj";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater.setFirst_Name(First_Name);});
  }

  /**
   <p> Tests That the Setters for the Skater.First_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkaterThrowsIllegalArgumentExceptionIfFirst_NameTooLong(){
    String First_Name = "GnwyHoMAvnOuJrLVFBTRCHPefavspPqROBvrUvWOihvEkiElRNJKwyhyRSasSKfwwgIvSBIdFbTKKtvKbbGfUOTwUCdpUWQHnBvpxMAqWTnSXWEijbQbXnxxxnjLotDGuUdUFFqyJBdMlffvkpLdHqAOlHLsORUGMYKFoovURCDsJNtQXdGdTVfPlSkMRiReGDSCDsJjDgdiBXKHqGnqitTJCpkyKqbnNEMdSypkuaECbnWJxsqctRgZrZcwCVwsS";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater.setFirst_Name(First_Name);});
  }

  /**
   <p> Tests That the Setters for the Skater.First_Name field work </p>
   */
  @Test
  public void testSetFirst_NameSetsFirst_Name(){
    String First_Name = "NWuxYGykQwbTnWDGwfUrjARWhQRdBEFiKayNjcIAiqeMsvXQVcGQHoUiMrsJNXpeZLeOaAcJfFkEfnKiDceTNSuNYnqxUXnDRxFsLvVAbGjdeEhZKnKRLriAHLkaoNQhmghQGNdlHpKdnOmPtNArAmNJKnKOBQOYvqrVWvDaaECWTwxQuAPpmtHSRCVkpbkhvVWrwJZRhKenNPjVOJFEwuPdlKnOlhWabXxpoSMPqoFASbbrXbTXvGfZjrFEf";
    _skater.setFirst_Name(First_Name);
    Assertions.assertEquals(First_Name,_skater.getFirst_Name());
  }

  /**
   <p> Tests That the Setters for the Skater.Last_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkaterThrowsIllegalArgumentExceptionIfLast_NameTooShort(){
    String Last_Name = "hR";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater.setLast_Name(Last_Name);});
  }

  /**
   <p> Tests That the Setters for the Skater.Last_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkaterThrowsIllegalArgumentExceptionIfLast_NameTooLong(){
    String Last_Name = "XYTakkenFuiEmmHKGJFpdXsXfmcYmIXhDtOBGgpnNMBrfchybmAZIFxFLfHgFjMvFWOBVtfVmHwtYMfoMQtnrBhkcbUgUmuVltIySZWFmlRqIQMusqrZSHMVnYnyByLaIEflsnAFiqcRUZmuDdgmLoBbnkSeaHhqNUFHHfvtGZFLEirQgkQOMUWLCKfcHouGPutIZbMNvDbomhTOPumPjrvmfWOmwDSKLkqIndHIPxbpCmYgPakpDxUvvoyIFUawF";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater.setLast_Name(Last_Name);});
  }

  /**
   <p> Tests That the Setters for the Skater.Last_Name field work </p>
   */
  @Test
  public void testSetLast_NameSetsLast_Name(){
    String Last_Name = "ReHsTvrwnGhaUfgsnqgSJHrDTgLxanPGhDJeXEqKBXCuAuPdRddSxTBPbmfosMfbNWUcxFZAYgQLTOKemjjIUoiXVUoZTSlVuOBqANaOJFVPQibgvrUsaeKFcdvLySxZxTudviaFfdCFsKoNlXWTcplWdeDKmXpvmxkMVmmuBIyiZGwMuCyRmKSsvCpePWNBPBKEciYbhlhKFwqsmBvLatqjZKjSLEhLjYYlajEXehXvykUQaDZQblxyWJCUx";
    _skater.setLast_Name(Last_Name);
    Assertions.assertEquals(Last_Name,_skater.getLast_Name());
  }

  /**
   <p> Tests That the Setters for the Skater.is_active field work </p>
   */
  @Test
  public void testSkaterSetsis_activeasFalse(){
    boolean status = false;
    _skater.setis_active(status);
    Assertions.assertEquals(status, _skater.getis_active());
  }

  /**
   <p> Tests That the Setters for the Skater.is_active field work </p>
   */
  @Test
  public void testSkaterSetsis_activeasTrue(){
    boolean status = true;
    _skater.setis_active(status);
    Assertions.assertEquals(status, _skater.getis_active());
  }


  /**
   <p> Tests That the CompareTo Method for the Skater object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() throws ParseException {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Skater smaller = new Skater();
    Skater bigger = new Skater();
//to compare a smaller and larger Skater_ID
    smaller.setSkater_ID("UkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
    bigger.setSkater_ID("VkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Skater_ID as equal.
    smaller.setSkater_ID("VkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
//to compare a smaller and larger Number
    smaller.setNumber("aaaa");
    bigger.setNumber("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Number as equal.
    smaller.setNumber("bbbb");
//to compare a smaller and larger Derby_Name
    smaller.setDerby_Name("aaaa");
    bigger.setDerby_Name("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Derby_Name as equal.
    smaller.setDerby_Name("bbbb");
//to compare a smaller and larger First_Name
    smaller.setFirst_Name("aaaa");
    bigger.setFirst_Name("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the First_Name as equal.
    smaller.setFirst_Name("bbbb");
//to compare a smaller and larger Last_Name
    smaller.setLast_Name("aaaa");
    bigger.setLast_Name("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Last_Name as equal.
    smaller.setLast_Name("bbbb");
//to compare a smaller and larger is_active
    smaller.setis_active(false);
    bigger.setis_active(true);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the is_active as equal.
    smaller.setis_active(true);
//to compare a smaller and larger Start_Date
    smaller.setStart_Date(df.parse("01/01/2023"));
    bigger.setStart_Date(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Start_Date as equal.
    smaller.setStart_Date(df.parse("01/01/2024"));
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

