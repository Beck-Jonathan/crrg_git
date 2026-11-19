package com.beck.crrg_git.crrg.data_interfaces;

import com.beck.crrg_git.crrg.models.Skater;

import java.sql.SQLException;
import java.util.List;

public interface iSkater_DAO {


  /**
   * DAO Method to add Skater objects
   * @param _skater the Skater to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Skater _skater) throws SQLException;
  /**
   * DAO Method to retrieve by Foreign Key Skater objects
   * @return List of Skater
   * @author Jonathan Beck
   */
  Skater getSkaterByPrimaryKey(Skater _skater) throws SQLException;
  /**
   * DAO Method to update Skater objects
   * @param oldSkater the Skater to be updated
   * @param newSkater the updated version of the Skater
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Skater oldSkater, Skater newSkater) throws SQLException;

  /**
   * DAO Method to retrieve all Skater objects
   * @return List of Skater
   * @author Jonathan Beck
   */
  List<Skater> getAllSkater(int offset, int limit, String search) throws SQLException;

  /**
   * DAO Method to select distinct Skater for dropdowns
   * @return list of string
   * @author Jonathan Beck
   */
  List<Skater> getDistinctSkaterForDropdown() throws SQLException;

//  /**
//   * DAO Method to undelete Skater objects
//   * @param Skater_ID the Skater to be undeleted
//   * @return number of records undeleted
//   * @author Jonathan Beck
//   */
  int updateSkater_is_active(Skater skater ) throws SQLException;

  int getSkaterCount(String searchTerm) throws SQLException;
}
