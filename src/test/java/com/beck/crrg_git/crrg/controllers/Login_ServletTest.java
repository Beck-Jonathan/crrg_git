package com.beck.crrg_git.crrg.controllers;

import com.beck.crrg_git.crrg.data_fakes.User_DAO_Fake;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Login_ServletTest {
  Login_Servlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException {
    servlet = new Login_Servlet();
    servlet.init(new User_DAO_Fake());
    request =  new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
  }
  @AfterEach
  public void teardown(){
    servlet=null;
    request=null;
    response=null;
    session=null;
    rd=null;
  }
  @Test
  public void TestLoggedInUserGets302OnDoGetAndSessionIsvalid() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();

    boolean isValid = request.isRequestedSessionIdValid();
    session=request.getSession(false);
    assertTrue(isValid);
    assertNotNull(session);
    assertEquals(302,status);

  }
  @Test
  public void TestLoggeOutUserGets200OnDoGetAndSessionIsInvalid() throws ServletException, IOException{
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
    session = request.getSession(false);
    assertNull(session);
  }

  /**
   <p> Test that error messages are sent for each field for logging in errors. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestLoginHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_IDError = results.get("userUser_IDerror");
    String PasswordError = results.get("userPassworderror");
    assertNotEquals("",User_IDError);
    assertNotNull(User_IDError);
    assertNotEquals("",PasswordError);
    assertNotNull(PasswordError);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Test that error messages are sent for each field for logging in errors. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestLoginFailsWithBadDataAndKeepsOnSamePage() throws ServletException, IOException{
    request.setParameter("inputuserUser_ID","TestValue");
    request.setParameter("inputuserPassword","TestValue#2233");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String dbStatus = results.get("dbStatus");
    assertNotEquals("",dbStatus);
    assertNotNull(dbStatus);
    assertEquals("User Not Logged In",dbStatus);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Tests That We can add to the database if all input fields are validated  </p>
   */
  @Test
  public void TestGoodLoginInfoAddsUserToSessionAndRedirects() throws ServletException, IOException{
    request.setParameter("inputuserUser_ID","bnyoAUFg");
    request.setParameter("inputuserPassword","FkGJoBij!!");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(User_Added);
    assertEquals("Logged In!",User_Added);
    assertNotEquals("",User_Added);
  }

}