package com.beck.crrg_git.crrg.controllers;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.beck.crrg_git.crrg.data_fakes.Album_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.Contributor_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.Picture_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.aws_DAO_Fake;
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

import static com.beck.crrg_git.crrg.shared.MultipartContentBuilder.buildMultipartContent;
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
    servlet.init(new Picture_DAO_Fake(),new Album_DAO_Fake(),new Contributor_DAO_Fake(), new aws_DAO_Fake());
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
    MockHttpSession session = new MockHttpSession();
    session.setAttribute("User", user);

    // Define boundary
    String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

    // Define form fields
    Map<String, String> formFields = new HashMap<>();
    formFields.put("variant", "php");
    formFields.put("os", "mac");
    formFields.put("version", "3.4");
    formFields.put("inputpictureAlbum_ID", "-1");
    formFields.put("inputpictureContributor_ID", "-1");
    formFields.put("inputpictureWeb_Address", "x");
    formFields.put("inputpicturedescription", "x");
    //formFields.put("inputpictureis_Active", "");
    //formFields.put("inputpictureis_Approved", "");

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
    String Album_IDError = results.get("pictureAlbum_IDerror");
    String Contributor_IDError = results.get("pictureContributor_IDerror");
    String DescriptionError = results.get("picturedescriptionerror");
    assertNotEquals("",Album_IDError);
    assertNotNull(Album_IDError);
    assertNotEquals("",Contributor_IDError);
    assertNotNull(Contributor_IDError);
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
    formFields.put("inputpictureAlbum_ID", "406");
    formFields.put("inputpictureContributor_ID", "406");
    formFields.put("inputpictureWeb_Address", "TestValue");
    formFields.put("inputpicturedescription", "TestValue");
    formFields.put("inputpictureis_Active", "true");
    formFields.put("inputpictureis_Approved", "true");

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
    formFields.put("inputpictureAlbum_ID", "1");
    formFields.put("inputpictureContributor_ID", "1");
    formFields.put("inputpictureWeb_Address", "www.EXCEPTION.com");
    formFields.put("inputpicturedescription", "EXCEPTION");
    formFields.put("inputpictureis_Active", "true");
    formFields.put("inputpictureis_Approved", "true");

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
   <p> Test that Picture objects with duplicate primary keys don't get added, and proper error handling exists. </p>
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
    formFields.put("inputpictureAlbum_ID", "1");
    formFields.put("inputpictureContributor_ID", "1");
    formFields.put("inputpictureWeb_Address", "www.DUPLICATE.com");
    formFields.put("inputpicturedescription", "DUPLICATE");
    formFields.put("inputpictureis_Active", "true");
    formFields.put("inputpictureis_Approved", "true");

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
    servlet = new Add_Picture();
    servlet.init();
  }


  @Test
  public void testFalseOverRideCanTrigger() throws IOException, ServletException {

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
    formFields.put("inputpictureAlbum_ID", "406");
    formFields.put("inputpictureContributor_ID", "406");
    formFields.put("inputpictureWeb_Address", "TestValue");
    formFields.put("inputpicturedescription", "TestValue");
    formFields.put("inputpictureis_Active", "x");
    formFields.put("inputpictureis_Approved", "x");

    // Define file fields
    Map<String, byte[]> fileFields = new LinkedHashMap<>();
    fileFields.put("content1", "testFile1".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content2", "testFile2".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content3", "testFile3".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content4", "testFile4".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content5", "testFile5".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content6", "testFile6".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content7", "testFile7".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content8", "testFile8".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content9", "testFile9".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content10", "testFile10".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content11", "testFile11".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content12", "testFile12".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content13", "testFile13".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content14", "testFile14".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content15", "testFile15".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content16", "testFile16".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content17", "testFile17".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content18", "testFile18".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content19", "testFile19".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content20", "testFile20".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content21", "testFile21".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content22", "testFile22".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content23", "testFile23".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content24", "testFile24".getBytes(StandardCharsets.UTF_8));

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
    assertEquals("False Override activated.",results.get("falseOverride"));
    assertEquals("24 Pictures Added. 0 Pictures Not Added.", results.get("dbStatus"));
  }



}

