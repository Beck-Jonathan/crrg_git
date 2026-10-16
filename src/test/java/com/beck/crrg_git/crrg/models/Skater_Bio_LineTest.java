package com.beck.crrg_git.crrg.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class Skater_Bio_LineTest {
  private Skater_Bio_Line _skater_bio_line;
  @BeforeEach
  public void setup(){
    _skater_bio_line = new Skater_Bio_Line();
  }
  @AfterEach
  public void teardown(){
    _skater_bio_line = null;
  }

  /**
   <p> Tests That the default constructor for the Skater_Bio_Line object works </p>
   */
  @Test
  public void testSkater_Bio_LineDefaultConstructorSetsNoVariables(){
    Skater_Bio_Line _skater_bio_line= new Skater_Bio_Line();
    Assertions.assertNull(_skater_bio_line.getSkater_Bio_Line_ID());
    Assertions.assertNull(_skater_bio_line.getSkater_ID());
    Assertions.assertNull(_skater_bio_line.getPrompt());
    Assertions.assertNull(_skater_bio_line.getAnswer());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Skater_Bio_Line object works </p>
   */
  @Test
  public void testSkater_Bio_LineParameterizedConstructorSetsAllVariables(){
    Skater_Bio_Line _skater_bio_line= new Skater_Bio_Line(
        "kwOhytURguZSUXKMNKrPpoojdgtwdmemkENK",
        "wiMIsePjSASvdZsOGYsPQhXjXojeZIAyoe",
        "GwKyITtdaATkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFpfEqopgVoyRCRdtjWXAefRScyAkBUgqOnWgqPprnMuiVEeZRITsVRygemfuFunaUinpWEABfQBXverGmMQPWmIZOaVnbIEcUioRQZnWcUjPNnAFPEYWCVvpZJNElsSTQJRQJuqkcWERHybXrAJwfYqPRjdiCVyEivUMhnfpqQHBNUBRKSrkhDaYrmpCIRCrmJAchKjNDNPhjLfbew",
        "NQfOusyoIoykdriIOCTSVeHRvfrtNMHLOHcsBtOScWKMEVujwSsPVrMJridQWtVeIZDCBGwFZHWyOoRxkkVojkZtlspydijlNOqOPtXlBGVjGCbLudtmgiSbHTDPZglMPkjpxChdDJfqNniEpyanvOGuqISZdpQBjKIDodBhwgMBfocuNiwcMdyojKKZgoMEvlKvlNjJOeHfhphOHTYabEytqEQrfobQfJDlKURThDTkXgMnawThKBujfrVxREXJLVJDBFPjQqVJSrKZBBSRrjPdobuVVyDJMLRDjqDxsKaSALQCEqDSetYLUUuqRyGgrWQOyiCbjqEvyRIsfJRTTidxTNbMTSIAaccTFKVivdpuSgwmMNxIHsALPOWiEUVhbaxueWEGIYuuOGtBYEgTtiuYhNnlwxVOfOCjZmqmBhQnCvtduLgPALiqRWrxwsRfxukdsGIVXyvAicDUCyxCRBctbbIWpHDBIyBiHenitQgPucYOhvciBXBLmPcGsfbtUYSYpWShLmegyjHTZGXZwchTbqGmXAgANyvCqFVTMNarmlZKZWpdEOxMtmlxfljLQbaRDdWmiEyeBxapJRxbyHJYYcLBoPqwsWmYJQyasoQFuasSAbKsLwyKCKVGcJDjHgCHccJGGFNVIqVhbKFDYnKTimrvrqlexECQfUoDWwvAWPHugOwaxETXLtVOKMWdsRfMvGUBgrKvYfvOXcnDyyirZZZoLjWtWpPZHcRcMPjRuonpYsSvTguMkRnBCpgorKhVNfJyiQulaSGChSpqJYNYsdFwSetLvihShLjJpNaSUhoALqDMuprLwZuRacXjmcgBdKgtaPWHRIiTXeWHUqxrFSAMlkQFNycKYCNeOcnpHlqwIwwSeHkhIrfgoGBEJFJtMYIFOKCwJPcmSmbqqUtJbwZEgoCckmNkJIBGaSXLjnOROhSOafJMtwXUCncZpTwOSqENUrQEhYQbdVTAsfVEvqhAeCtkQyWyXn"
    );
    Assertions.assertEquals("kwOhytURguZSUXKMNKrPpoojdgtwdmemkENK",_skater_bio_line.getSkater_Bio_Line_ID());
    Assertions.assertEquals("wiMIsePjSASvdZsOGYsPQhXjXojeZIAyoe",_skater_bio_line.getSkater_ID());
    Assertions.assertEquals("GwKyITtdaATkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFpfEqopgVoyRCRdtjWXAefRScyAkBUgqOnWgqPprnMuiVEeZRITsVRygemfuFunaUinpWEABfQBXverGmMQPWmIZOaVnbIEcUioRQZnWcUjPNnAFPEYWCVvpZJNElsSTQJRQJuqkcWERHybXrAJwfYqPRjdiCVyEivUMhnfpqQHBNUBRKSrkhDaYrmpCIRCrmJAchKjNDNPhjLfbew",_skater_bio_line.getPrompt());
    Assertions.assertEquals("NQfOusyoIoykdriIOCTSVeHRvfrtNMHLOHcsBtOScWKMEVujwSsPVrMJridQWtVeIZDCBGwFZHWyOoRxkkVojkZtlspydijlNOqOPtXlBGVjGCbLudtmgiSbHTDPZglMPkjpxChdDJfqNniEpyanvOGuqISZdpQBjKIDodBhwgMBfocuNiwcMdyojKKZgoMEvlKvlNjJOeHfhphOHTYabEytqEQrfobQfJDlKURThDTkXgMnawThKBujfrVxREXJLVJDBFPjQqVJSrKZBBSRrjPdobuVVyDJMLRDjqDxsKaSALQCEqDSetYLUUuqRyGgrWQOyiCbjqEvyRIsfJRTTidxTNbMTSIAaccTFKVivdpuSgwmMNxIHsALPOWiEUVhbaxueWEGIYuuOGtBYEgTtiuYhNnlwxVOfOCjZmqmBhQnCvtduLgPALiqRWrxwsRfxukdsGIVXyvAicDUCyxCRBctbbIWpHDBIyBiHenitQgPucYOhvciBXBLmPcGsfbtUYSYpWShLmegyjHTZGXZwchTbqGmXAgANyvCqFVTMNarmlZKZWpdEOxMtmlxfljLQbaRDdWmiEyeBxapJRxbyHJYYcLBoPqwsWmYJQyasoQFuasSAbKsLwyKCKVGcJDjHgCHccJGGFNVIqVhbKFDYnKTimrvrqlexECQfUoDWwvAWPHugOwaxETXLtVOKMWdsRfMvGUBgrKvYfvOXcnDyyirZZZoLjWtWpPZHcRcMPjRuonpYsSvTguMkRnBCpgorKhVNfJyiQulaSGChSpqJYNYsdFwSetLvihShLjJpNaSUhoALqDMuprLwZuRacXjmcgBdKgtaPWHRIiTXeWHUqxrFSAMlkQFNycKYCNeOcnpHlqwIwwSeHkhIrfgoGBEJFJtMYIFOKCwJPcmSmbqqUtJbwZEgoCckmNkJIBGaSXLjnOROhSOafJMtwXUCncZpTwOSqENUrQEhYQbdVTAsfVEvqhAeCtkQyWyXn",_skater_bio_line.getAnswer());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Skater_Bio_Line object works </p>
   */
  @Test
  public void testSkater_Bio_LineKeyedParameterizedConstructorSetsKeyedVariables(){
    Skater_Bio_Line _skater_bio_line= new Skater_Bio_Line(
        "egTfuXfcBpZAvVfAdWfruocTXZsFexVLRiJL"
    );
    Assertions.assertEquals("egTfuXfcBpZAvVfAdWfruocTXZsFexVLRiJL",_skater_bio_line.getSkater_Bio_Line_ID());
    Assertions.assertNull(_skater_bio_line.getSkater_ID());
    Assertions.assertNull(_skater_bio_line.getPrompt());
    Assertions.assertNull(_skater_bio_line.getAnswer());
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Skater_Bio_Line_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkater_Bio_LineThrowsIllegalArgumentExceptionIfSkater_Bio_Line_IDTooShort(){
    String Skater_Bio_Line_ID = "Fd";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater_bio_line.setSkater_Bio_Line_ID(Skater_Bio_Line_ID);});
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Skater_Bio_Line_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkater_Bio_LineThrowsIllegalArgumentExceptionIfSkater_Bio_Line_IDTooLong(){
    String Skater_Bio_Line_ID = "MCsnuTuVBIlkpqRDOJduKJTlqFLaVRQmiFwcPw";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater_bio_line.setSkater_Bio_Line_ID(Skater_Bio_Line_ID);});
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Skater_Bio_Line_ID field work </p>
   */
  @Test
  public void testSetSkater_Bio_Line_IDSetsSkater_Bio_Line_ID(){
    String Skater_Bio_Line_ID = "WdrqvvrhBXNkyJePoaWZDclDgSuEvirsydOe";
    _skater_bio_line.setSkater_Bio_Line_ID(Skater_Bio_Line_ID);
    Assertions.assertEquals(Skater_Bio_Line_ID,_skater_bio_line.getSkater_Bio_Line_ID());
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Skater_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkater_Bio_LineThrowsIllegalArgumentExceptionIfSkater_IDTooShort(){
    String Skater_ID = "dP";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater_bio_line.setSkater_ID(Skater_ID);});
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Skater_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkater_Bio_LineThrowsIllegalArgumentExceptionIfSkater_IDTooLong(){
    String Skater_ID = "MQQjGnwyHoMAvnOuJrLVFBTRCHPefavspPqROB";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater_bio_line.setSkater_ID(Skater_ID);});
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Skater_ID field work </p>
   */
  @Test
  public void testSetSkater_IDSetsSkater_ID(){
    String Skater_ID = "VkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX";
    _skater_bio_line.setSkater_ID(Skater_ID);
    Assertions.assertEquals(Skater_ID,_skater_bio_line.getSkater_ID());
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Prompt field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkater_Bio_LineThrowsIllegalArgumentExceptionIfPromptTooShort(){
    String Prompt = "SB";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater_bio_line.setPrompt(Prompt);});
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Prompt field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkater_Bio_LineThrowsIllegalArgumentExceptionIfPromptTooLong(){
    String Prompt = "IdFbTKKtvKbbGfUOTwUCdpUWQHnBvpxMAqWTnSXWEijbQbXnxxxnjLotDGuUdUFFqyJBdMlffvkpLdHqAOlHLsORUGMYKFoovURCDsJNtQXdGdTVfPlSkMRiReGDSCDsJjDgdiBXKHqGnqitTJCpkyKqbnNEMdSypkuaECbnWJxsqctRgZrZcwCVwsSNWuxYGykQwbTnWDGwfUrjARWhQRdBEFiKayNjcIAiqeMsvXQVcGQHoUiMrsJNXpeZLeOaA";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater_bio_line.setPrompt(Prompt);});
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Prompt field work </p>
   */
  @Test
  public void testSetPromptSetsPrompt(){
    String Prompt = "cJfFkEfnKiDceTNSuNYnqxUXnDRxFsLvVAbGjdeEhZKnKRLriAHLkaoNQhmghQGNdlHpKdnOmPtNArAmNJKnKOBQOYvqrVWvDaaECWTwxQuAPpmtHSRCVkpbkhvVWrwJZRhKenNPjVOJFEwuPdlKnOlhWabXxpoSMPqoFASbbrXbTXvGfZjrFEfhRXYTakkenFuiEmmHKGJFpdXsXfmcYmIXhDtOBGgpnNMBrfchybmAZIFxFLfHgFjMvFWOB";
    _skater_bio_line.setPrompt(Prompt);
    Assertions.assertEquals(Prompt,_skater_bio_line.getPrompt());
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Answer field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkater_Bio_LineThrowsIllegalArgumentExceptionIfAnswerTooShort(){
    String Answer = "Vt";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater_bio_line.setAnswer(Answer);});
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Answer field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSkater_Bio_LineThrowsIllegalArgumentExceptionIfAnswerTooLong(){
    String Answer = "fVmHwtYMfoMQtnrBhkcbUgUmuVltIySZWFmlRqIQMusqrZSHMVnYnyByLaIEflsnAFiqcRUZmuDdgmLoBbnkSeaHhqNUFHHfvtGZFLEirQgkQOMUWLCKfcHouGPutIZbMNvDbomhTOPumPjrvmfWOmwDSKLkqIndHIPxbpCmYgPakpDxUvvoyIFUawFReHsTvrwnGhaUfgsnqgSJHrDTgLxanPGhDJeXEqKBXCuAuPdRddSxTBPbmfosMfbNWUcxFZAYgQLTOKemjjIUoiXVUoZTSlVuOBqANaOJFVPQibgvrUsaeKFcdvLySxZxTudviaFfdCFsKoNlXWTcplWdeDKmXpvmxkMVmmuBIyiZGwMuCyRmKSsvCpePWNBPBKEciYbhlhKFwqsmBvLatqjZKjSLEhLjYYlajEXehXvykUQaDZQblxyWJCUxZXlHUOVjGIPnZnvLmNwtdkZpgmaTuVPjxkrPHFtpwRAnSKCKWnWEvmBgehnWLmyDOLTGqygfZYBUbJarfBljKeYdGZlAjqvqpshEOGbNcSsQMgLHXZJrgNsMtuXQffSdsSameHvCFkoMFSsSXYVgBASKBMgoRoNdtCSnqfrTMpaDAobxGMPecsAVkYGjQLSCyEkaCKYpHBXEQSiATPYZnuYrFFQxrhlcZUeNKXZKOiAWpXarDcQApHKUFHqPTepoUUGEAydMLYPhjydIDJCpSRSyVVuybMHlxbgmdiiEWXVPiXfRorDNhgQNpDQFTNfjumTlvtwifsSQWPijtrVnfJmQtBCDvtJatEjlmSJJBwoGEufdGKGHJxeAvgcwcJiKxNIFCBdIpnvHPPCWmwaGlWqaMXnFcsgDIeAIIMyJgSGRjNXjBSeKDOjBWKDwZlkxOQRVxXgFFhmseHwLYwYVQGlBvMBtbHDZvrUDmNxieScRXoPRqvOOLZEeajYiPbYYLYffmIjXeCakbycGqOkDtEGlsriuqGUrWPcDnxCAyxqifbKZUb";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_skater_bio_line.setAnswer(Answer);});
  }

  /**
   <p> Tests That the Setters for the Skater_Bio_Line.Answer field work </p>
   */
  @Test
  public void testSetAnswerSetsAnswer(){
    String Answer = "BPqorcmuLsLjyemZmBTDeluAyrYPvupgEZnvIKwMnFfygqnjgTMsOTppyJyQVUCuLydqWpExpUyLbLNJdIKKZFSKmiaiQwkeoMCohnQpmbCmCMjskBXRfcHGDCvfxLfnVsvdWLfnZaDAdPokpLQpWXmSeWnquYUHcmoOXiAdySVJZoctEDLiEOycweeegBvbnEaomtnrSBeXjSPDEbFKqMjXfrUooiHNnoOkQuMNNUKMRHUYbZpXvrqXHCZCRthloTQKxcxUMZNZWGhgprwfqdOJkurQjmYcdOJeZSsKhMcGKnMffkGxiBinrDvilnqlrBWDJfvlOejDDEHgeHvepyOxjXlTSQLPHhwRmPZONSYrdeGUHSWFlDMJXwBqnQXmroAyTlqVNaWUEQWMAsFDntpOcZvtfyoeHpncVphcBbvWEWslRwkYucsuRctWZJaYZTGNmLrNRCDqlNgcEUevjZEheCWofwPKijahRqrZFHJClEoXmfhiNKofNLaNxRxsmverbEyAipSbHHBBCSLdOtKkngwQrltEdiwlKFoucYFmvrZIgxpeQOtIVRPUbZWRFRWVieFoiwrooYdMGmORuKgRJksTWtHlROKUpaRCTtpkVTdCmEVLiJTuDpdROEuQqtqcsudHSeoSyyvcplacFqYljgkEpatAISJlDVcESdVlFPQlkhKvWOoOmFCuTQfANaSphXDUSUBdJkOhdxogHiPpyQHXJYmDKYdAlsCvZmkrlfffhKpFrDAiHustLCqGpEdLudLOEXUolkTvxRUdQewJBOjOclLoaCOLtcavKiDIhRIuHOZSaxJAlotfJAKOEjDhmhFvWFsaYGhdwTHiuQkjcoGQCWDwMCDKEhlNeaVQZSiLCMfDkTnyTFxaosABuTVCXfUQiVYJWEAKXsiPKjZjLXkaTUGwghnAiODEdtvJjrodBrglSowvJEIBEDjWVWKMeKapNBgrkiSNQuUgqEKvbprvZTUZMqeBal";
    _skater_bio_line.setAnswer(Answer);
    Assertions.assertEquals(Answer,_skater_bio_line.getAnswer());
  }


  /**
   <p> Tests That the CompareTo Method for the Skater_Bio_Line object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    Skater_Bio_Line smaller = new Skater_Bio_Line();
    Skater_Bio_Line bigger = new Skater_Bio_Line();
//to compare a smaller and larger Skater_Bio_Line_ID
    smaller.setSkater_Bio_Line_ID("VkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
    bigger.setSkater_Bio_Line_ID("WkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Skater_Bio_Line_ID as equal.
    smaller.setSkater_Bio_Line_ID("WkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
//to compare a smaller and larger Skater_ID
    smaller.setSkater_ID("AkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
    bigger.setSkater_ID("BkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Skater_ID as equal.
    smaller.setSkater_ID("BkdfUtgAoYojyqrqSvFdNIJNTPHTFqoMxFXX");
//to compare a smaller and larger Prompt
    smaller.setPrompt("aaaa");
    bigger.setPrompt("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Prompt as equal.
    smaller.setPrompt("bbbb");
//to compare a smaller and larger Answer
    smaller.setAnswer("aaaa");
    bigger.setAnswer("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Answer as equal.
    smaller.setAnswer("bbbb");
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

