package com.beck.crrg_git.crrg.controllers;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.beck.crrg_git.crrg.data.aws_DAO;
import com.beck.crrg_git.crrg.data_fakes.Sponsor_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.Sponsor_Tag_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.aws_DAO_Fake;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import static com.beck.crrg_git.crrg.shared.MultipartContentBuilder.buildMultipartContent;
import static org.junit.jupiter.api.Assertions.*;

public class Add_Sponsor_TagTest {
  private static final String PAGE="WEB-INF/crrg/Add_Sponsor_Tag.jsp";
  Add_Sponsor_Tag servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new Add_Sponsor_Tag();
    servlet.init(new Sponsor_Tag_DAO_Fake(),new Sponsor_DAO_Fake(), new aws_DAO_Fake());
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
   <p> Test that error messages are sent for each field for addingSponsor_Tag objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    MockHttpSession session = new MockHttpSession();
    session.setAttribute("User", user);

    // Define boundary
    String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

    // Define form fields
    Map<String, String> formFields = new HashMap<>();
    formFields.put("variant", "php");
    formFields.put("os", "mac");
    formFields.put("version", "3.4");
    formFields.put("inputsponsor_tagSponsor_ID", "-1");
    formFields.put("inputsponsor_tagPicture_ID", "-1");
    formFields.put("inputsponsor_tagDescription", "x");
    formFields.put("inputsponsor_tagIs_Primary", "x");


    // Define file fields
    Map<String, byte[]> fileFields = new LinkedHashMap<>();
    fileFields.put("content", "testFile".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content2", "testFile2".getBytes(StandardCharsets.UTF_8));

    // Build multipart content
    byte[] multipartContent = buildMultipartContent(boundary, formFields, fileFields);

    // Setup request
    //MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("POST");
    request.setContentType("multipart/form-data; boundary=" + boundary);
    request.setContent(multipartContent);
    request.setSession(session);

    // Setup response
    //MockHttpServletResponse response = new MockHttpServletResponse();

    // Call servlet
    servlet.doPost(request, response);
    //request.
    // servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_IDError = results.get("sponsor_tagSponsor_IDerror");
    String Picture_IDError = results.get("sponsor_tagPicture_IDerror");
    String DescriptionError = results.get("sponsor_tagDescriptionerror");
    assertNotEquals("",Sponsor_IDError);
    assertNotNull(Sponsor_IDError);
    //assertNotEquals("",Picture_IDError);
    //assertNotNull(Picture_IDError);
    assertNotEquals("",DescriptionError);
    assertNotNull(DescriptionError);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Tests That We can add to the database if all input fields are validated  </p>
   */
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    // Setup session with valid user
    User user = new User();
    user.setRole_ID("Jonathan");
    MockHttpSession session = new MockHttpSession();
    session.setAttribute("User", user);

    // Define boundary
    String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

    // Define form fields
    Map<String, String> formFields = new HashMap<>();
    formFields.put("variant", "php");
    formFields.put("os", "mac");
    formFields.put("version", "3.4");
    formFields.put("inputsponsor_tagSponsor_ID","TestValue");
    formFields.put("inputsponsor_tagPicture_ID","406");
    formFields.put("inputsponsor_tagDescription","TestValue");
    formFields.put("inputsponsor_tagIs_Primary","true");


    // Define file fields
    Map<String, byte[]> fileFields = new LinkedHashMap<>();
    fileFields.put("content", "testFile".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content2", "testFile2".getBytes(StandardCharsets.UTF_8));

    // Build multipart content
    byte[] multipartContent = buildMultipartContent(boundary, formFields, fileFields);

    // Setup request
    //MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("POST");
    request.setContentType("multipart/form-data; boundary=" + boundary);
    request.setContent(multipartContent);
    request.setSession(session);

    // Setup response
    //MockHttpServletResponse response = new MockHttpServletResponse();

    // Call servlet
    servlet.doPost(request, response);

    // Validate
    int status = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");

    assertEquals(302, status);
    assertNotNull(results);
    assertEquals("2 Pictures Added. 0 Pictures Not Added.", results.get("dbStatus"));
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    MockHttpSession session = new MockHttpSession();
    session.setAttribute("User", user);

    // Define boundary
    String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

    // Define form fields
    Map<String, String> formFields = new HashMap<>();
    formFields.put("variant", "php");
    formFields.put("os", "mac");
    formFields.put("version", "3.4");
    formFields.put("inputsponsor_tagSponsor_ID","EXCEPTION");
    formFields.put("inputsponsor_tagPicture_ID","406");
    formFields.put("inputsponsor_tagDescription","EXCEPTION");
    formFields.put("inputsponsor_tagIs_Primary","true");


    // Define file fields
    Map<String, byte[]> fileFields = new LinkedHashMap<>();
    fileFields.put("content", "testFile".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content2", "testFile2".getBytes(StandardCharsets.UTF_8));

    // Build multipart content
    byte[] multipartContent = buildMultipartContent(boundary, formFields, fileFields);

    // Setup request
    //MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("POST");
    request.setContentType("multipart/form-data; boundary=" + boundary);
    request.setContent(multipartContent);
    request.setSession(session);

    // Setup response
    //MockHttpServletResponse response = new MockHttpServletResponse();

    // Call servlet
    servlet.doPost(request, response);
    //request.
    // servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String dbError = results.get("dbError");

    String Picture_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Picture_Added);
    assertNotEquals("",Picture_Added);
    assertEquals("0 Pictures Added. 2 Pictures Not Added.",Picture_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  /**
   <p> Test that Sponsor_Tag objects with duplicate primary keys don't get added, and proper error handling exists. </p>
   */
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    MockHttpSession session = new MockHttpSession();
    session.setAttribute("User", user);

    // Define boundary
    String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

    // Define form fields
    Map<String, String> formFields = new HashMap<>();
    formFields.put("variant", "php");
    formFields.put("os", "mac");
    formFields.put("version", "3.4");
    formFields.put("inputsponsor_tagSponsor_ID","DUPLICATE");
    formFields.put("inputsponsor_tagPicture_ID","406");
    formFields.put("inputsponsor_tagDescription","DUPLICATE");
    formFields.put("inputsponsor_tagIs_Primary","true");

    // Define file fields
    Map<String, byte[]> fileFields = new LinkedHashMap<>();
    fileFields.put("content", "testFile".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content2", "testFile2".getBytes(StandardCharsets.UTF_8));

    // Build multipart content
    byte[] multipartContent = buildMultipartContent(boundary, formFields, fileFields);

    // Setup request
    //MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("POST");
    request.setContentType("multipart/form-data; boundary=" + boundary);
    request.setContent(multipartContent);
    request.setSession(session);

    // Setup response
    //MockHttpServletResponse response = new MockHttpServletResponse();

    // Call servlet
    servlet.doPost(request, response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Picture_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Picture_Added);
    assertEquals("0 Pictures Added. 2 Pictures Not Added.",Picture_Added);
    assertNotEquals("",Picture_Added);
    assertNull(dbError);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Add_Sponsor_Tag();
    servlet.init();
  }

}

