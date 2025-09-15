package com.beck.crrg_git.crrg.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.crrg_git.crrg.data_fakes.Picture_DAO_Fake;
import com.beck.crrg_git.crrg.models.Picture;
import com.beck.crrg_git.crrg.models.Picture_VM;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.json.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class Picture_APITest {
  private static final String PAGE="WEB-INF/crrg/All_Picture.jsp";
  Picture_API servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new Picture_API();
    servlet.init(new Picture_DAO_Fake());
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
  public void TestLoggedOutUserGets200OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets200onDoGet() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("WrongRole");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  /**
   <p> Test that a logged in user is able to retrieve all of the Picture objects. </p>
   */
  @Test
  public void testUserGetsAllPicturesOfAlbumOneWithNoAlbumSet() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    String jsonContent = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonContent);
    Assertions.assertEquals(5,jsonArray.length());
  }

  /**
   <p> Test that getting all Picture can filter. </p>
   */
  @Test
  public void testLoggedInUserCanFilterPicturesByAlbum_ID() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Integer Album_ID= 50;
    request.setParameter("album",Album_ID.toString());
    servlet.doGet(request,response);
    String jsonContent = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonContent);
    Assertions.assertEquals(6,jsonArray.length());
  }




  /**
   <p> Test that getting all Picture can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearchAndReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Integer Album_ID= 200;
    request.setParameter("album",Album_ID.toString());
    servlet.doGet(request,response);
    String jsonContent = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonContent);
    Assertions.assertEquals(0,jsonArray.length());
  }
  /**
   <p> Test that getting all Picture can filter. </p>
   */
  @Test
  public void testServletCanHandleDAOExceptionGracefully() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Integer Album_ID= -1;
    request.setParameter("album",Album_ID.toString());
    servlet.doGet(request,response);
    String responseAsString = response.getContentAsString();
    Assertions.assertEquals("Invalid Album ID\r\n",responseAsString);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Picture_API();
    servlet.init();
  }

}

