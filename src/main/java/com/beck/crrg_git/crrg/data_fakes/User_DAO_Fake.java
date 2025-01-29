package com.beck.crrg_git.crrg.data_fakes;

import com.beck.crrg_git.crrg.data.User_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iUser_DAO;
import com.beck.crrg_git.crrg.models.User;
import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User_DAO_Fake implements iUser_DAO {
  private  List<User> users;
  public User_DAO_Fake(){
    users = new ArrayList<>();
    User user0 = new User("bnyoAUFg", "plHOlKWC", "IsJUDQrB", "ORAIQQXu", "LaCnDqgX", new DateTime(), "FkGJoBij!!".toCharArray());
    User user1 = new User("DxKlPMsV", "plHOlKWC", "sQaDsonD", "rEVSgeLR", "PHHLHbaT", new DateTime(), "jYFmadmH".toCharArray());
    User user2 = new User("bxhMUBwp", "plHOlKWC", "ehfHOwwV", "kKlivMIV", "hPQkrtdT", new DateTime(), "bumJJkvf".toCharArray());
    User user3 = new User("qMRqpdJs", "plHOlKWC", "PBXelHDx", "TgFwndcd", "cUxAbwya", new DateTime(), "hvEpZgVq".toCharArray());
    User user4 = new User("pMphDchM", "brGxaZro", "MPiuOYeK", "ZHsNrjFL", "keCtbIKF", new DateTime(), "kIrdSVcN".toCharArray());
    User user5 = new User("WfvSYeSW", "brGxaZro", "yjuTOTGb", "rrLbKUGl", "ESiRpnsy", new DateTime(), "DIrRqRet".toCharArray());
    User user6 = new User("rNpqWCPl", "brGxaZro", "JBDCKOFu", "pEJvXZOs", "CZyUPZON", new DateTime(), "SZBETTrv".toCharArray());
    User user7 = new User("YxdLuakv", "brGxaZro", "pWbyplto", "kbRgAdcW", "CFbDbSZU", new DateTime(), "TYoxiSgq".toCharArray());
    User user8 = new User("pLQdbREf", "snZLRYGX", "TlnPDZUW", "aVvsxyIj", "nvqmJtdS", new DateTime(), "KkkHkZDx".toCharArray());
    User user9 = new User("uDdjAptq", "snZLRYGX", "IKrwMRrH", "fsKMTVZG", "KfoOLFpA", new DateTime(), "EFGEvBrR".toCharArray());
    User user10 = new User("WQBgGrPs", "snZLRYGX", "dcmJnhjN", "QseOCsxl", "GMNVlYKe", new DateTime(), "AspCTmAS".toCharArray());
    User user11 = new User("WvebSfLV", "snZLRYGX", "VrmDVdOR", "PsdsHKXH", "JurgtrSq", new DateTime(), "GOWYAqAs".toCharArray());

    users.add(user0);
    users.add(user1);
    users.add(user2);
    users.add(user3);
    users.add(user4);
    users.add(user5);
    users.add(user6);
    users.add(user7);
    users.add(user8);
    users.add(user9);
    users.add(user10);
    users.add(user11);

    Collections.sort(users);
  }
  @Override
  public int add(User _user) throws SQLException {
    if (duplicateKey(_user)){
      return 0;
    }
    if (exceptionKey(_user)){
      throw new SQLException("error");
    }
    int size = users.size();

    users.add(_user);
    int newsize = users.size();
    return newsize-size;

  }

  @Override
  public List<String> getDistinctRoleForDropdown() {
    return List.of();
  }

  @Override
  public List<User> getAllUser(int limit, int offset, String Role_ID) throws SQLException {
    List<User> results = new ArrayList<>();
    for (User user : users){
      if ((user.getRole_ID()!=null||user.getRole_ID().equals(Role_ID)))
      {
        results.add(user);
      }
    }
    return results;
  }



  @Override
  public User getUserByUserID(User _user) throws SQLException {
    User result = null;
    for (User user : users) {
      if (user.getUser_ID().equals(_user.getUser_ID())){
        result = user;
        break;
      }
    }
    if (result == null){
      throw new SQLException("User not found");
    }
    return result;
  }

  @Override
  public String getPassword(String username) throws SQLException {
    String result = null;
    for (User user : users) {
      if (user.getUser_ID().equals(username)){
        String hashPassword = BCrypt.hashpw(String.valueOf(user.getPassword()), BCrypt.gensalt(12));

        result =hashPassword;
        break;
      }
    }
    if (result == null){
      throw new SQLException("User not found");
    }
    return result;
  }

  @Override
  public boolean usernameFree(String username) throws SQLException {
    boolean result = true;
    for (User user : users) {
      if (user.getUser_ID().equals(username)){
        result = false;
        break;
      }
    }
    return result;
  }

  @Override
  public boolean emailFree(String email) throws SQLException {
    boolean result = true;
    for (User user : users) {
      if (user.getEmail().equals(email)){
        result = false;
        break;
      }
    }
    return result;
  }

  @Override
  public boolean updatePassword(User _user) throws SQLException {
    boolean result = false;
    for (User user : users) {
      if (user.getUser_ID().equals(_user.getUser_ID())){
        user.setPassword(_user.getPassword());
        result = true;
        break;
      }
    }
    return result;
  }

  @Override
  public int changeRole(String userID, String Role) throws SQLException {
    return 0;
  }

  @Override
  public int updateLastLoggedIn(User _user) throws SQLException {
    int result = 0;
    for (User user : users) {
      if (user.getUser_ID().equals(_user.getUser_ID())){
        user.setLast_Logged_In(DateTime.now());
        result = 1;
        break;
      }
    }
    return result;
  }

  @Override
  public int update(User oldUser, User newUser) throws SQLException {
    int location =-1;
    if (duplicateKey(oldUser)){
      return 0;
    }
    if (exceptionKey(oldUser)){
      throw new SQLException("error");
    }
    for (int i=0;i<users.size();i++){
      if (users.get(i).getUser_ID().equals(oldUser.getUser_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }

    users.set(location,newUser);
    return 1;

  }
  private boolean duplicateKey(User _user){
    return _user.getUser_ID().equals("DUPLICATE");
  }
  private boolean exceptionKey(User _user){
    return _user.getUser_ID().equals("EXCEPTION");
  }

}
