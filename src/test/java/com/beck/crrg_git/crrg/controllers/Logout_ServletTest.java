package com.beck.crrg_git.crrg.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class Logout_ServletTest {
  Logout_Servlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{
    servlet = new Logout_Servlet();
    servlet.init();
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
  public void TestLoggedInUserGets302OnDoGetAndSessionIsNull() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    session = request.getSession(false);
    assertNull(session);

  }
  @Test
  public void TestLoggeOutUserGets302OnDoGetAndSessionIsNull() throws ServletException, IOException{
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    session = request.getSession(false);
    assertNull(session);
  }

}