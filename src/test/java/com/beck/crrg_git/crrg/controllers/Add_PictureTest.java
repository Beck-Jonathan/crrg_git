package com.beck.crrg_git.crrg.controllers;
import java.io.File;
import java.io.IOException;
import java.util.*;

import com.beck.crrg_git.crrg.data_fakes.Album_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.Contributor_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.Picture_DAO_Fake;
import com.beck.crrg_git.crrg.models.Picture;
import com.beck.crrg_git.crrg.models.Picture_VM;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

public class Add_PictureTest {
  private static final String PAGE="WEB-INF/crrg/Add_Picture.jsp";
  Add_Picture servlet;
  MockMultipartHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  private static final String BOUNDARY = "OurCustomBoundaryValue";

  public byte[] createFileContent(byte[] data, String boundary, String contentType, String fileName){
    String start = "--" + boundary + "\r\n Content-Disposition: form-data; name=\"file\"; filename=\""+fileName+"\"\r\n"
        + "Content-type: "+contentType+"\r\n\r\n";;

    String end = "\r\n--" + boundary + "--"; // correction suggested @butfly
    return ArrayUtils.addAll(start.getBytes(),ArrayUtils.addAll(data,end.getBytes()));
  }

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new Add_Picture();
    servlet.init(new Picture_DAO_Fake(),new Album_DAO_Fake(),new Contributor_DAO_Fake());
    request =  new MockMultipartHttpServletRequest();
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
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestLoggedInUserGets200OnDoPostWithNoFieldsSet() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doPost(request,response);
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
   <p> Test that error messages are sent for each field for addingPicture objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    //request.setContentType("");
    String resourceName = "";
    String partName = "";
    byte[] content = "testFile".getBytes();
    byte[] content2 = "testFile2".getBytes();
    MultipartFile file = new MockMultipartFile("content","a","application/zip",content);
    MultipartFile file2 = new MockMultipartFile("content2","b","application/zip",content2);
    request.addFile(file);
    request.addFile(file2);



    String boundary = "q1w2e3r4t5y6u7i8o9";
    request.setContentType("multipart/form-data; boundary="+boundary);
    request.setContent(createFileContent(content2,boundary,"application/zip","test.zip"));
    request.addFile(file);
    request.setMethod("POST");
    request.setParameter("variant", "php");
    request.setParameter("os", "mac");
    request.setParameter("version", "3.4");
    MockHttpServletResponse response = new MockHttpServletResponse();
    servlet.doPost(request, response);

    //request.


   // servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_IDError = results.get("pictureAlbum_IDerror");
    String Contributor_IDError = results.get("pictureContributor_IDerror");
    String Web_AddressError = results.get("pictureWeb_Addresserror");
    String DescriptionError = results.get("pictureDescriptionerror");
    assertNotEquals("",Album_IDError);
    assertNotNull(Album_IDError);
    assertNotEquals("",Contributor_IDError);
    assertNotNull(Contributor_IDError);
    assertNotEquals("",Web_AddressError);
    assertNotNull(Web_AddressError);
    assertNotEquals("",DescriptionError);
    assertNotNull(DescriptionError);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Tests That We can add to the database if all input fields are validated  </p>
   */
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("inputpictureAlbum_ID","406");
    request.setParameter("inputpictureContributor_ID","406");
    request.setParameter("inputpictureWeb_Address","TestValue");
    request.setParameter("inputpictureDescription","TestValue");
    request.setParameter("inputpictureIs_Active","true");
    request.setParameter("inputpictureis_Approved","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Picture_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Picture_Added);
    assertEquals("Picture Added",Picture_Added);
    assertNotEquals("",Picture_Added);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("inputpictureAlbum_ID","406");
    request.setParameter("inputpictureContributor_ID","406");
    request.setParameter("inputpictureWeb_Address","EXCEPTION");
    request.setParameter("inputpictureDescription","EXCEPTION");
    request.setParameter("inputpictureIs_Active","true");
    request.setParameter("inputpictureis_Approved","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Picture_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Picture_Added);
    assertEquals("Picture Not Added",Picture_Added);
    assertNotEquals("",Picture_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  /**
   <p> Test that Picture objects with duplicate primary keys don't get added, and proper error handling exists. </p>
   */
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("inputpictureAlbum_ID","406");
    request.setParameter("inputpictureContributor_ID","406");
    request.setParameter("inputpictureWeb_Address","DUPLICATE");
    request.setParameter("inputpictureDescription","DUPLICATE");
    request.setParameter("inputpictureIs_Active","true");
    request.setParameter("inputpictureis_Approved","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Picture_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Picture_Added);
    assertEquals("Picture Not Added",Picture_Added);
    assertNotEquals("",Picture_Added);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Add_Picture();
    servlet.init();
  }

}

