package com.beck.crrg_git.crrg.data_fakes;

import com.beck.crrg_git.crrg.data_interfaces.iSkater_DAO;
import com.beck.crrg_git.crrg.models.Skater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Skater_DAO_Fake implements iSkater_DAO {
  private List<Skater> skaters;
  public Skater_DAO_Fake() {
    skaters = new ArrayList<>();
    Skater skater0 = new Skater("OJRKIPvKpPRQVkcfQbNAqeRTjGHGxlcmwRXm", "VrAmKUoq", "HnkrXApX", "IWoMEglY", "MROnEexG", true, new Date());
    Skater skater1 = new Skater("aelRHdootyQaETVfZTBgDIbTiPNYXjPseFrw", "dsMBhijF", "ESgvradn", "PFfGaaQL", "pkBaxEDK", false, new Date());
    Skater skater2 = new Skater("teyKJGkmkAvAjKmYwmeZXtTCkhnWgReeFTyp", "nKtjRAni", "fFWRCHEp", "GVDSPLTX", "ugEmofrR", false, new Date());
    Skater skater3 = new Skater("djjtkoiyVPIBUdmPxVWtFOCadcVdoEMWeJaZ", "wHlBebGJ", "CKbSdNoe", "ehOEWsth", "vQxxctpp", true, new Date());
    Skater skater4 = new Skater("mTNWyZZLCyayrLXIEMNTiRHKYPkqnnCRWQPa", "vYiKQXyF", "BdDhUFth", "hVOBuQef", "LhHavpLe", false, new Date());
    skaters.add(skater0);
    skaters.add(skater1);
    skaters.add(skater2);
    skaters.add(skater3);
    skaters.add(skater4);
    Collections.sort(skaters);
  }
}
