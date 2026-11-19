package com.beck.crrg_git.crrg.data_fakes;

import com.beck.crrg_git.crrg.data_interfaces.iSkater_DAO;
import com.beck.crrg_git.crrg.models.Skater;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Skater_DAO_Fake implements iSkater_DAO {
  private List<Skater> skaters;
  public Skater_DAO_Fake() {
    skaters = new ArrayList<>();
    Skater skater0 = new Skater("OJRKIPHitPRQVkcfQbNAqeRTjGHGxlcmwRXm", "VrAmKUoq", "HnkrXApX", "IWoMEglY", "MROnEexG", true, new Date());
    Skater skater1 = new Skater("aelRHdootyQaETVfZTBgDIbTiPNYXjPseFrw", "23", "ESgvradn", "PFfGaaQL", "pkBaxEDK", false, new Date());
    Skater skater2 = new Skater("teyKJGkmkAvAjKmYwmeZXtTCkhnWgReeFTyp", "nKtjRAni", "Hit", "GVDSPLTX", "ugEmofrR", false, new Date());
    Skater skater3 = new Skater("djjtkoiyVPIBUdmPxVWtFOCadcVdoEMWeJaZ", "wHlBebGJ", "CKbSdNoe", "Hit", "vQxxctpp", true, new Date());
    Skater skater4 = new Skater("mTNWyZZLCyayrLXIEMNTiRHKYPkqnnCRWQPa", "vYiKQXyF", "BdDhUFth", "hVOBuQef", "LhHavpLe", false, new Date());
    skaters.add(skater0);
    skaters.add(skater1);
    skaters.add(skater2);
    skaters.add(skater3);
    skaters.add(skater4);
    Collections.sort(skaters);
  }

  @Override
  public int add(Skater _skater) throws SQLException {
    if (duplicateKey(_skater)){
      return 0;
    }
    if (exceptionKey(_skater)){
      throw new SQLException("error");
    }
    int size = skaters.size();
    skaters.add(_skater);
    int newsize = skaters.size();
    return newsize-size;
  }

  @Override
  public Skater getSkaterByPrimaryKey(Skater _skater) throws SQLException {
    Skater result = null;
    for (Skater skater : skaters) {
      if (skater.getSkater_ID().equals(_skater.getSkater_ID())){
        result = skater;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Skater not found");
    }
    return result;
  }

  @Override
  public int update(Skater oldSkater, Skater newSkater) throws SQLException {
    int location =-1;
    if (duplicateKey(oldSkater)){
      return 0;
    }
    if (exceptionKey(oldSkater)){
      throw new SQLException("error");
    }
    for (int i=0;i<skaters.size();i++){
      if (skaters.get(i).getSkater_ID().equals(oldSkater.getSkater_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    skaters.set(location,newSkater);
    return 1;
  }


  @Override
  public List<Skater> getAllSkater(int offset, int limit, String search) throws SQLException {
    List<Skater> results = new ArrayList<>();
    for (Skater skater : skaters){
      if (!search.isEmpty()
          && !skater.getSkater_ID().contains(search)
          && !skater.getNumber().contains(search)
          && !skater.getDerby_Name().contains(search)
          && !skater.getFirst_Name().contains(search)
          && !skater.getLast_Name().contains(search)){
        continue;
      }

    results.add(skater);
  }
return results;
  }

  @Override
  public List<Skater> getDistinctSkaterForDropdown() throws SQLException {
    return skaters;
  }

  @Override
  public int updateSkater_is_active(Skater skater) throws SQLException {
    int location =-1;
    if (duplicateKey(skater)){
      return 0;
    }
    if (exceptionKey(skater)){
      throw new SQLException("error");
    }
    for (int i=0;i<skaters.size();i++){
      if (skaters.get(i).getSkater_ID().equals(skater.getSkater_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    if (skaters.get(location).getis_active()==(skater.getis_active())){
      return 0;
    }
    skaters.get(location).setis_active(skater.getis_active());
    return 1;
  }

  @Override
  public int getSkaterCount(String searchTerm) throws SQLException {
    return getAllSkater(0,10000,searchTerm).size();
  }

  private boolean duplicateKey(Skater _skater){
    return _skater.getDerby_Name().contains("DUPLICATE");
  }
  private boolean exceptionKey(Skater _skater){
    return _skater.getDerby_Name().contains("EXCEPTION");
  }
}
