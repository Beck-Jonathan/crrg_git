package com.beck.crrg_git.crrg.data;

import com.beck.crrg_git.crrg.data_interfaces.iSkater_DAO;
import com.beck.crrg_git.crrg.models.Skater;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.beck.crrg_git.crrg.data.Database.getConnection;

public class Skater_DAO implements iSkater_DAO {
  @Override
  public int add(Skater _skater) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Skater( ?, ?, ?, ?, ?, ?)}")){
          statement.setString(1,_skater.getNumber());
          statement.setString(2,_skater.getDerby_Name());
          statement.setString(3,_skater.getFirst_Name());
          statement.setString(4,_skater.getLast_Name());
          statement.setBoolean(5,_skater.getis_active());
          statement.setDate(6,new java.sql.Date(_skater.getStart_Date().getTime()));
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Skater. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Skater. Try again later");
    }
    return numRowsAffected;
  }

  @Override
  public Skater getSkaterByPrimaryKey(Skater _skater) throws SQLException {
    Skater result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_pk_Skater(?)}")) {
        statement.setString(1, _skater.getSkater_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            String Skater_ID = resultSet.getString("Skater_Skater_ID");
            String Number = resultSet.getString("Skater_Number");
            String Derby_Name = resultSet.getString("Skater_Derby_Name");
            String First_Name = resultSet.getString("Skater_First_Name");
            String Last_Name = resultSet.getString("Skater_Last_Name");
            boolean is_active = resultSet.getBoolean("Skater_is_active");
            Date Start_Date = resultSet.getDate("Skater_Start_Date");
            result = new Skater( Skater_ID, Number, Derby_Name, First_Name, Last_Name, is_active, Start_Date);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public int update(Skater oldSkater, Skater newSkater) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Skater(? ,?,?,?,?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldSkater.getSkater_ID());
          statement.setString(2,oldSkater.getNumber());
          statement.setString(3,newSkater.getNumber());
          statement.setString(4,oldSkater.getDerby_Name());
          statement.setString(5,newSkater.getDerby_Name());
          statement.setString(6,oldSkater.getFirst_Name());
          statement.setString(7,newSkater.getFirst_Name());
          statement.setString(8,oldSkater.getLast_Name());
          statement.setString(9,newSkater.getLast_Name());
          statement.setBoolean(10,oldSkater.getis_active());
          statement.setBoolean(11,newSkater.getis_active());
          statement.setDate(12,new java.sql.Date(newSkater.getStart_Date().getTime()));
          statement.setDate(13,new java.sql.Date(newSkater.getStart_Date().getTime()));
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Skater . Try again later");
        }
      }
    }
    return result;
  }



  @Override
  public List<Skater> getAllSkater(int offset, int limit, String search) throws SQLException {
    List<Skater> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_Skater(?,?,?)}")) {
          statement.setInt(1,limit)
          ;statement.setInt(2,offset);
          statement.setString(3,search);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Skater_ID = resultSet.getString("Skater_Skater_ID");
              String Number = resultSet.getString("Skater_Number");
              String Derby_Name = resultSet.getString("Skater_Derby_Name");
              String First_Name = resultSet.getString("Skater_First_Name");
              String Last_Name = resultSet.getString("Skater_Last_Name");
              boolean is_active = resultSet.getBoolean("Skater_is_active");
              Date Start_Date = resultSet.getDate("Skater_Start_Date");
              Skater _skater = new Skater( Skater_ID, Number, Derby_Name, First_Name, Last_Name, is_active, Start_Date);
              result.add(_skater);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Skaters. Try again later");
    }
    return result;
  }

  @Override
  public List<Skater> getDistinctSkaterForDropdown() throws SQLException {
    List<Skater> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_select_distinct_and_active_Skater_for_dropdown()}")) {
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Skater_ID = resultSet.getString("Skater_Skater_ID");
              Skater _skater = new Skater( Skater_ID);
              result.add(_skater);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Skaters. Try again later");
    }
    return result;
  }



  @Override
  public int updateSkater_is_active(Skater _oldSkater ) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Skater_is_active(? ,?)}"))
        {
          statement.setString(1,_oldSkater.getSkater_ID());
          statement.setBoolean(2,_oldSkater.getis_active());

          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Skater . Try again later");
        }
      }
    }
    return result;

  }

  @Override
  public int getSkaterCount(String searchTerm) throws SQLException {
    int result =0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_count_by_all_Skater(?)}")) {
          statement.setString(1, searchTerm);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              result = resultSet.getInt(1);

            }
          }

        } catch (SQLException e) {
          throw new RuntimeException("Could not retrieve Skaters. Try again later");
        }
      }
    }
      return result;
  }
}
