package com.beck.crrg_git.crrg.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.crrg_git.crrg.data_fakes.Album_DAO_Fake;
import com.beck.crrg_git.crrg.models.Album;
import com.beck.crrg_git.crrg.models.Album_VM;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class Edit_AlbumTest {
  private static final String PAGE="WEB-INF/crrg/Edit_Album.jsp";
  Edit_Album servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new Edit_Album();
    servlet.init(new Album_DAO_Fake());
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
  public void TestLoggedInUserGets302OnDoGetWithNoAlbumSet() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  /**
   <p> Test That a logged in user gets a 200 status on doPost </p>
   */
  @Test
  public void TestLoggedInUserGets302nDoPostWithNoAlbumSet() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
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
   <p> Test that a logged in user is able to retrieve a specific one of the Album objects. </p>
   */
  @Test
  public void testGetOneAlbumGetsOneAlbum_ID() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Integer Album_ID= 1;
    request.setParameter("albumid",Album_ID.toString());
    request.setSession(session);
    servlet.doGet(request,response);
    Album album = (Album) session.getAttribute("album");
    assertNotNull(album);
    assertEquals(Album_ID,album.getAlbum_ID());
  }

  /**
   <p> Test that getting one Album can fail. </p>
   */
  @Test
  public void testGetOneAlbumCanFailAndUserIsRedirected() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Integer Album_ID= 4;
    request.setParameter("albumid",Album_ID.toString());

    request.setSession(session);
    servlet.doGet(request,response);
    Album album = (Album) session.getAttribute("album");
    assertNull(album);
    assertEquals(302,response.getStatus());

  }

  /**
   <p> Test that we are able to update Album objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Album
    Album album = new Album();
    album.setAlbum_ID(1);
    album.setAlbum_Name("testAlbum");
    album.setIs_Active(true);
    session.setAttribute("album",album);
//create a new albums parameters
    request.setParameter("inputalbumAlbum_Name","TestValue");
    request.setParameter("inputalbumIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Album_Updated);
    assertEquals("Album updated",Album_Updated);
    assertNotEquals("",Album_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingAlbum objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Album album = new Album();
    session.setAttribute("album",album);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_NameError = results.get("albumAlbum_Nameerror");
    assertNotEquals("",Album_NameError);
    assertNotNull(Album_NameError);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Edit_Album();
    servlet.init();
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update Album objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Album
    Album album = new Album();
    album.setAlbum_ID(43);
    album.setAlbum_Name("DUPLICATE");
    album.setIs_Active(true);
    session.setAttribute("album",album);
//create a new albums parameters
    request.setParameter("inputalbumAlbum_Name","DUPLICATE");
    request.setParameter("inputalbumIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Album_Updated);
    assertEquals("Album Not Updated",Album_Updated);
    assertNotEquals("",Album_Updated);
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
//to set the old Album
    Album album = new Album();
    album.setAlbum_ID(43);
    album.setAlbum_Name("EXCEPTION");
    album.setIs_Active(true);
    session.setAttribute("album",album);
//create a new albums parameters
    request.setParameter("inputalbumAlbum_Name","EXCEPTION");
    request.setParameter("inputalbumIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Album_Updated);
    assertNotEquals("",Album_Updated);
    assertEquals("Album Not Updated",Album_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

