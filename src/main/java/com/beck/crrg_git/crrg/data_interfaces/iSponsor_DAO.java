package com.beck.crrg_git.crrg.data_interfaces;

import com.beck.crrg_git.crrg.models.Sponsor;

import java.sql.SQLException;
import java.util.List;

public interface iSponsor_DAO {
  /**
   * DAO Method to add Sponsor objects
   * @param _sponsor the Sponsor to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Sponsor _sponsor) throws SQLException;
  /**
   * DAO Method to retreive by Foreign Key Sponsor objects
   * @return List of Sponsor
   * @author Jonathan Beck
   */
  //Sponsor getSponsorByPrimaryKey(Sponsor _sponsor) throws SQLException;
  /**
   * DAO Method to retreive by Foreign Key Sponsor objects
   * @return List of Sponsor
   * @author Jonathan Beck
   */
  public List<Sponsor> getSponsorbyTier(String Tier_ID) throws SQLException;
  /**
   * DAO Method to update Sponsor objects
   * @param oldSponsor the Sponsor to be updated
   * @param newSponsor the updated version of the Sponsor
   * @return number of records updated
   * @author Jonathan Beck
   */
  //int update(Sponsor oldSponsor, Sponsor newSponsor) throws SQLException;
  /**
   * DAO Method to retreive all Sponsor objects
   * @return List of Sponsor
   * @author Jonathan Beck
   */
  List<Sponsor> getAllSponsor( String Tier) throws SQLException;
  /**
   * DAO Method to retreive all Sponsor objects
   * @return List of Sponsor
   * @author Jonathan Beck
   */
  //List<Sponsor> getActiveSponsor() throws SQLException;
  /**
   * DAO Method to select distinct Sponsor for dropdowns
   * @return list of string
   * @author Jonathan Beck
   */
  List<String> getDistinctSponsorForDropdown() throws SQLException;



  List<String> selectAllTiersForDropDown() throws SQLException;

  int setPrimarySponsorImage() throws SQLException;

  /**
   * DAO Method to retreive by Foreign Key Sponsor objects
   * @return List of Sponsor
   * @author Jonathan Beck
   */
  Sponsor getSponsorByPrimaryKey(Sponsor _sponsor) throws SQLException;

  /**
   * DAO Method to update Sponsor objects
   * @param oldSponsor the Sponsor to be updated
   * @param newSponsor the updated version of the Sponsor
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Sponsor oldSponsor, Sponsor newSponsor) throws SQLException;

  int changeActivatiion(String objectID, int mode) throws SQLException;
}
