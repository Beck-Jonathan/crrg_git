package com.beck.crrg_git.crrg.controllers;


import java.io.IOException;
import java.util.*;
import com.beck.crrg_git.crrg.data_fakes.Skater_DAO_Fake;
import com.beck.crrg_git.crrg.models.Skater;
import com.beck.crrg_git.crrg.models.Skater_VM;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class All_SkaterTest {
  private static final String PAGE="WEB-INF/crrg/All_Skater.jsp";
  All_Skater servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new All_Skater();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "All_Skater");
    servlet.init(servletConfig);
    servlet.init(new Skater_DAO_Fake());
    request =  new MockHttpServletRequest(servletContext);
    response = new MockHttpServletResponse();
    session = new MockHttpSession(servletContext);
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
   <p> Test that a logged in user is able to retrieve all of the Skater objects. </p>
   */
  @Test
  public void testLoggedInUserGetsAllSkaters() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Skater_VM> skaters = (List<Skater_VM>) request.getAttribute("Skaters");
    int count = (int)  request.getAttribute("count");
    assertNotNull(skaters);
    assertEquals(5,skaters.size());
    assertEquals(5,count);
  }

  /**
   <p> Test that getting all Skater can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearch() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    String searchTerm = "Hit";
    request.setParameter("search",searchTerm);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Skater_VM> skaters = (List<Skater_VM>) request.getAttribute("Skaters");
    assertNotNull(skaters);
    assertEquals(3,skaters.size());
    int count = (int) request.getAttribute("count");
    assertEquals(3,count);
  }

  /**
   <p> Test that getting all Skater can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearchAndReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    String searchTerm = "banana banana123";
    request.setParameter("search",searchTerm);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Skater_VM> skaters = (List<Skater_VM>) request.getAttribute("Skaters");
    assertNotNull(skaters);
    assertEquals(0,skaters.size());
    int count = (int) request.getAttribute("count");
    assertEquals(0,count);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new All_Skater();
    servlet.init();
  }

}

