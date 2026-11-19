package com.beck.crrg_git.crrg.data;

import com.beck.crrg_git.crrg.data_interfaces.iTag_DAO;
import com.beck.crrg_git.crrg.models.Picture;
import com.beck.crrg_git.crrg.models.Skater;
import com.beck.crrg_git.crrg.models.Tag;
import com.beck.crrg_git.crrg.models.Tag_VM;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.beck.crrg_git.crrg.data.Database.getConnection;

public class Tag_DAO implements iTag_DAO {
  @Override
  public int add(Tag _tag) throws SQLException {

    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Tag( ?, ?, ?, ?)}")){
          statement.setString(1,_tag.getSkater_ID());
          statement.setInt(2,_tag.getPicture_ID());
          statement.setInt(3,_tag.getXcord());
          statement.setInt(4,_tag.getYcord());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Tag. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Tag. Try again later");
    }
    return numRowsAffected;
  }

  @Override
  public int deleteTag(Tag _tag) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Tag( ?)}")){
          statement.setInt(1,_tag.getTag_ID());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Tag. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Tag. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public List<Tag_VM> getAllTag(String search, String Skater_ID, Integer Picture_ID) throws SQLException {
    List<Tag_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_Tag(?,?,?)}")) {

          statement.setString(1,search);
          statement.setString(2,Skater_ID);
          statement.setInt(3,Picture_ID);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              Integer Tag_ID = resultSet.getInt("Tag_Tag_ID");
              String _Skater_ID = resultSet.getString("Tag_Skater_ID");
              Integer _Picture_ID = resultSet.getInt("Tag_Picture_ID");
              Integer Xcord = resultSet.getInt("Tag_Xcord");
              Integer Ycord = resultSet.getInt("Tag_Ycord");
              String Skater_Skater_ID = resultSet.getString("Skater_Skater_ID");
              String Skater_Number = resultSet.getString("Skater_Number");
              String Skater_Derby_Name = resultSet.getString("Skater_Derby_Name");
              String Skater_First_Name = resultSet.getString("Skater_First_Name");
              String Skater_Last_Name = resultSet.getString("Skater_Last_Name");
              boolean Skater_is_active = resultSet.getBoolean("Skater_is_active");
              Date Skater_Start_Date = resultSet.getDate("Skater_Start_Date");
              Integer Picture_Picture_ID = resultSet.getInt("Picture_Picture_ID");
              Integer Picture_Album_ID = resultSet.getInt("Picture_Album_ID");
              Integer Picture_Contributor_ID = resultSet.getInt("Picture_Contributor_ID");
              String Picture_Web_Address = resultSet.getString("Picture_Web_Address");
              String Picture_Description = resultSet.getString("Picture_Description");
              boolean Picture_Is_Active = resultSet.getBoolean("Picture_Is_Active");
              boolean Picture_is_Approved = resultSet.getBoolean("Picture_is_Approved");
              Tag _tag = new Tag( Tag_ID, _Skater_ID, _Picture_ID, Xcord, Ycord);
              Skater skater = new Skater(Skater_Skater_ID,Skater_Number,Skater_Derby_Name,Skater_First_Name,Skater_Last_Name,Skater_is_active,Skater_Start_Date);
              Picture picture = new Picture(Picture_Picture_ID,Picture_Album_ID,Picture_Contributor_ID,Picture_Web_Address,Picture_Description,Picture_Is_Active,Picture_is_Approved);
              Tag_VM vm = new Tag_VM(_tag);
              vm.setSkater(skater);
              vm.setPicture(picture);
              result.add(vm);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Tags. Try again later");
    }
    return result;
  }

  @Override
  public Tag_VM getTagByPrimaryKey(Tag _tag) throws SQLException {
    Tag_VM result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_pk_Tag(?)}")) {
        statement.setString(1, _tag.getTag_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            Integer Tag_ID = resultSet.getInt("Tag_Tag_ID");
            String Skater_ID = resultSet.getString("Tag_Skater_ID");
            Integer Picture_ID = resultSet.getInt("Tag_Picture_ID");
            Integer Xcord = resultSet.getInt("Tag_Xcord");
            Integer Ycord = resultSet.getInt("Tag_Ycord");
            String Skater_Skater_ID = resultSet.getString("Skater_Skater_ID");
            String Skater_Number = resultSet.getString("Skater_Number");
            String Skater_Derby_Name = resultSet.getString("Skater_Derby_Name");
            String Skater_First_Name = resultSet.getString("Skater_First_Name");
            String Skater_Last_Name = resultSet.getString("Skater_Last_Name");
            boolean Skater_is_active = resultSet.getBoolean("Skater_is_active");
            Date Skater_Start_Date = resultSet.getDate("Skater_Start_Date");
            Integer Picture_Picture_ID = resultSet.getInt("Picture_Picture_ID");
            Integer Picture_Album_ID = resultSet.getInt("Picture_Album_ID");
            Integer Picture_Contributor_ID = resultSet.getInt("Picture_Contributor_ID");
            String Picture_Web_Address = resultSet.getString("Picture_Web_Address");
            String Picture_Description = resultSet.getString("Picture_Description");
            boolean Picture_Is_Active = resultSet.getBoolean("Picture_Is_Active");
            boolean Picture_is_Approved = resultSet.getBoolean("Picture_is_Approved");
            Tag tag = new Tag(Tag_ID, Skater_ID, Picture_ID, Xcord, Ycord);
            result = new Tag_VM(tag);
            Skater skater = new Skater(Skater_Skater_ID,Skater_Number,Skater_Derby_Name,Skater_First_Name,Skater_Last_Name,Skater_is_active,Skater_Start_Date);
            Picture picture = new Picture(Picture_Picture_ID,Picture_Album_ID,Picture_Contributor_ID,Picture_Web_Address,Picture_Description,Picture_Is_Active,Picture_is_Approved);

            result.setSkater(skater);
            result.setPicture(picture);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public int addBatchOfTags(List<Tag> tags) throws SQLException{
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        for (Tag _tag : tags) {
          try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Tag( ?, ?, ?, ?)}")){
            statement.setString(1,_tag.getSkater_ID());
            statement.setInt(2,_tag.getPicture_ID());
            statement.setInt(3,_tag.getXcord());
            statement.setInt(4,_tag.getYcord());
            numRowsAffected += statement.executeUpdate();
          } catch (SQLException e) {
            continue;
          }
        }
      }
    } catch (Exception e) {
      throw new SQLException(e);
    }
    return numRowsAffected;
  }
}
