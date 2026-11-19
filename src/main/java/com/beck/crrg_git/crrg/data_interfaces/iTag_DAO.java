package com.beck.crrg_git.crrg.data_interfaces;
import com.beck.crrg_git.crrg.models.*;

import java.sql.SQLException;
import java.util.List;

public interface iTag_DAO {
  /**
   * DAO Method to add Tag objects
   * @param _tag the Tag to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Tag _tag) throws SQLException;

  /**
   * DAO Method to delete Tag objects
   * @param _tag the Tag to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int deleteTag( Tag _tag) throws SQLException;

  /**
   * DAO Method to retrieve all Tag objects
   * @return List of Tag
   * @author Jonathan Beck
   */
  List<Tag_VM> getAllTag(String search, String Skater_ID, Integer Picture_ID) throws SQLException;

  /**
   * DAO Method to retrieve by Foreign Key Tag objects
   * @return List of Tag
   * @author Jonathan Beck
   */
  Tag_VM getTagByPrimaryKey(Tag _tag) throws SQLException;
}
