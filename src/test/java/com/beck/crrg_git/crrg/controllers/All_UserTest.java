package com.beck.crrg_git.crrg.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.crrg_git.crrg.data_fakes.User_DAO_Fake;
import com.beck.crrg_git.crrg.models.User;

import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class All_UserTest {
  private static final String PAGE="WEB-INF/crrg/All_User.jsp";
  All_User servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new All_User();
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
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
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
   <p> Test that a logged in user is able to retrieve all of the User objects. </p>
   */
  @Test
  public void testLoggedInUserGetsAllUsers() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    List<User> users = (List<User>) request.getAttribute("Users");
    assertNotNull(users);
    assertEquals(12,users.size());
  }

  /**
   <p> Test that getting all User can filter. </p>
   */
  @Test
  public void testLoggedInUserCanFilterUsersByRole_ID() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    String Role_ID= "plHOlKWC";
    request.setParameter("role_id",Role_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    List<User> users = (List<User>) request.getAttribute("Users");
    assertNotNull(users);
    assertEquals(4,users.size());
  }





  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new All_User();
    servlet.init();
  }

}

