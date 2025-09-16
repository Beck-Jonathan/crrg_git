package com.beck.crrg_git.crrg.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.crrg_git.crrg.data_fakes.User_DAO_Fake;
import com.beck.crrg_git.crrg.models.User;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class Edit_UserTest {
  private static final String PAGE="WEB-INF/crrg/Edit_User.jsp";
  Edit_User servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new Edit_User();
    servlet.init(new User_DAO_Fake());
    request =  new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
    rd = new MockRequestDispatcher(PAGE);
  }


  /**
   <p> tear down by setting all variablges to null. </p>
   */
  @AfterEach
  public void teardown(){
    servlet=null;
    request=null;
    response=null;
    session=null;
    rd=null;
  }

  /**
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestLoggedInUserGets302OnDoGetWithNoUserIDSet() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String desiredRedirect = "all-Users";
    assertEquals(desiredRedirect,response.getRedirectedUrl());
  }

  /**
   <p> Test That a logged in user gets a 302 status on doPost </p>
   */
  @Test
  public void TestLoggedInUserGets302nDoPostWithNoUserSet() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String desiredRedirect = "all-Users";
    assertEquals(desiredRedirect,response.getRedirectedUrl());
  }

  /**
   <p> Tests That the user will received a 302 status on doGet if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "crrgLogin";
    assertEquals(desired_redirect,redirect_link);
    session = request.getSession(false);
    assertNull(session);
  }

  /**
   <p> Tests That the user will received a 302 status on doPost if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "crrgLogin";
    assertEquals(desired_redirect,redirect_link);
    session = request.getSession(false);
    assertNull(session);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("WrongRole");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "crrgLogin";
    assertEquals(desired_redirect,redirect_link);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doPost </p>
   */
  @Test
  public void TestWrongRoleGets302onDoPost() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("WrongRole");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "crrgLogin";
    assertEquals(desired_redirect,redirect_link);
  }

  /**
   <p> Test that a logged in user is able to retrieve a specific one of the User objects. </p>
   */
  @Test
  public void testGetOneUserGetsOneUser_ID() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    String User_ID= "bnyoAUFg";
    request.setParameter("userid",User_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    User _user = (User) session.getAttribute("_user");
    assertNotNull(_user);
    assertEquals(User_ID,_user.getUser_ID());
  }

  /**
   <p> Test that getting one User can fail. </p>
   */
  @Test
  public void testGetOneUserCanFailAndUserIsRedirected() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    String User_ID= "kjdkfljsadkfljsa";
    request.setParameter("userid",User_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    User _user = (User) session.getAttribute("_user");
    Assertions.assertNull(_user);
    assertEquals(302,response.getStatus());
    String desiredRedirect = "all-Users";
    assertEquals(desiredRedirect,response.getRedirectedUrl());
  }

  /**
   <p> Test that we are able to update User objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old User
    User _user = new User();
    _user.setUser_ID("bnyoAUFg");
    _user.setRole_ID("testUser");
    _user.setFirst_Name("testUser");
    _user.setLast_Name("testUser");
    _user.setEmail("testUser@gmail.com");
    _user.setLast_Logged_In(new DateTime());

    session.setAttribute("_user",_user);
//create a new users parameters
    request.setParameter("inputuserUser_ID","bnyoAUFg");
    request.setParameter("inputuserRole_ID","TestValue");
    request.setParameter("inputuserFirst_Name","TestValue");
    request.setParameter("inputuserLast_Name","TestValue");
    request.setParameter("inputuserEmail","TestValue@gmail.com");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(User_Updated);
    assertEquals("User updated",User_Updated);
    assertNotEquals("",User_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingUser objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    User _user = new User();
    session.setAttribute("_user",_user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    //String User_IDError = results.get("userUser_IDerror");
    String Role_IDError = results.get("userRole_IDerror");
    String First_NameError = results.get("userFirst_Nameerror");
    String Last_NameError = results.get("userLast_Nameerror");
    String EmailError = results.get("userEmailerror");

    assertNotEquals("",Role_IDError);
    assertNotNull(Role_IDError);
    assertNotEquals("",First_NameError);
    assertNotNull(First_NameError);
    assertNotEquals("",Last_NameError);
    assertNotNull(Last_NameError);
    assertNotEquals("",EmailError);
    assertNotNull(EmailError);

    assertEquals(200,responseStatus);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Edit_User();
    servlet.init();
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update User objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old User
    User _user = new User();
    _user.setUser_ID("bnyoAUFg");
    _user.setRole_ID("DUPLICATE");
    _user.setFirst_Name("DUPLICATE");
    _user.setLast_Name("DUPLICATE");
    _user.setEmail("DUPLICATE@gmail.com");

    session.setAttribute("_user",_user);
//create a new albums parameters
    request.setParameter("inputuserUser_ID","bnyoAUFg");
    request.setParameter("inputuserRole_ID","DUPLICATE");
    request.setParameter("inputuserFirst_Name","DUPLICATE");
    request.setParameter("inputuserLast_Name","DUPLICATE");
    request.setParameter("inputuserEmail","DUPLICATE@gmail.com");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(User_Updated);
    assertEquals("User Not Updated",User_Updated);
    assertNotEquals("",User_Updated);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old User
    User _user = new User();
    _user.setUser_ID("bnyoAUFg");
    _user.setRole_ID("EXCEPTION");
    _user.setFirst_Name("EXCEPTION");
    _user.setLast_Name("EXCEPTION");
    _user.setEmail("EXCEPTION@gmail.com");

    session.setAttribute("_user",_user);
//create a new albums parameters
    request.setParameter("inputuserUser_ID","bnyoAUFg");
    request.setParameter("inputuserRole_ID","EXCEPTION");
    request.setParameter("inputuserFirst_Name","EXCEPTION");
    request.setParameter("inputuserLast_Name","EXCEPTION");
    request.setParameter("inputuserEmail","EXCEPTION@gmail.com");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(User_Updated);
    assertNotEquals("",User_Updated);
    assertEquals("User Not Updated",User_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

