package com.beck.crrg_git.crrg.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.crrg_git.crrg.data_fakes.Contributor_DAO_Fake;
import com.beck.crrg_git.crrg.models.Contributor;
import com.beck.crrg_git.crrg.models.Contributor_VM;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class Edit_ContributorTest {
  private static final String PAGE="WEB-INF/crrg/Edit_Contributor.jsp";
  Edit_Contributor servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new Edit_Contributor();
    servlet.init(new Contributor_DAO_Fake());
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
  public void TestLoggedInUserGets302OnDoGetWithNoObjectSet() throws ServletException, IOException{
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
  public void TestLoggedInUserGets302nDoPostWithNoContributorSet() throws ServletException, IOException{
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
   <p> Test that a logged in user is able to retrieve a specific one of the Contributor objects. </p>
   */
  @Test
  public void testGetOneContributorGetsOneContributor_ID() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Integer Contributor_ID= 37;
    request.setParameter("contributorid",Contributor_ID.toString());
    request.setSession(session);
    servlet.doGet(request,response);
    Contributor_VM contributor = (Contributor_VM) session.getAttribute("contributor");
    assertNotNull(contributor);
    assertEquals(Contributor_ID,contributor.getContributor_ID());
  }

  /**
   <p> Test that getting one Contributor can fail. </p>
   */
  @Test
  public void testGetOneContributorCanFailAndUserIsRedirected() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Integer Contributor_ID= 3700;
    request.setParameter("contributorid",Contributor_ID.toString());
    request.setSession(session);
    servlet.doGet(request,response);
    Contributor contributor = (Contributor) session.getAttribute("contributor");
    assertNull(contributor);
    assertEquals(302,response.getStatus());
  }

  /**
   <p> Test that we are able to update Contributor objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Contributor
    Contributor contributor = new Contributor();
    contributor.setContributor_ID(43);
    contributor.setFirst_Name("testContributor");
    contributor.setLast_Name("testContributor");
    contributor.setEmail("testContributor@gmail.com");
    session.setAttribute("contributor",contributor);
//create a new albums parameters
    request.setParameter("inputcontributorFirst_Name","TestValue");
    request.setParameter("inputcontributorLast_Name","TestValue");
    request.setParameter("inputcontributorEmail","TestValue@gmail.com");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Contributor_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Contributor_Updated);
    assertEquals("Contributor updated",Contributor_Updated);
    assertNotEquals("",Contributor_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingContributor objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Contributor contributor = new Contributor();
    contributor.setContributor_ID(47);
    session.setAttribute("contributor",contributor);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String First_NameError = results.get("contributorFirst_Nameerror");
    String Last_NameError = results.get("contributorLast_Nameerror");
    String EmailError = results.get("contributorEmailerror");
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
    servlet = new Edit_Contributor();
    servlet.init();
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update Contributor objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Contributor
    Contributor contributor = new Contributor();
    contributor.setContributor_ID(43);
    contributor.setFirst_Name("DUPLICATE");
    contributor.setLast_Name("DUPLICATE");
    contributor.setEmail("DUPLICATE@gmail.com");
    session.setAttribute("contributor",contributor);
//create a new albums parameters
    request.setParameter("inputcontributorFirst_Name","DUPLICATE");
    request.setParameter("inputcontributorLast_Name","DUPLICATE");
    request.setParameter("inputcontributorEmail","DUPLICATE@gmail.com");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Contributor_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Contributor_Updated);
    assertEquals("Contributor Not Updated",Contributor_Updated);
    assertNotEquals("",Contributor_Updated);
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
//to set the old Contributor
    Contributor contributor = new Contributor();
    contributor.setContributor_ID(43);
    contributor.setFirst_Name("EXCEPTION");
    contributor.setLast_Name("EXCEPTION");
    contributor.setEmail("EXCEPTION@gmail.com");
    session.setAttribute("contributor",contributor);
//create a new albums parameters
    request.setParameter("inputcontributorFirst_Name","EXCEPTION");
    request.setParameter("inputcontributorLast_Name","EXCEPTION");
    request.setParameter("inputcontributorEmail","EXCEPTION@gmail.com");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Contributor_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Contributor_Updated);
    assertNotEquals("",Contributor_Updated);
    assertEquals("Contributor Not Updated",Contributor_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

