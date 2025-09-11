package com.beck.crrg_git.crrg.controllers;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import com.beck.crrg_git.crrg.data_fakes.Sponsor_DAO_Fake;
import com.beck.crrg_git.crrg.models.Sponsor;
import com.beck.crrg_git.crrg.models.Sponsor_VM;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class Edit_SponsorTest {
  private static final String PAGE="WEB-INF/crrg/Edit_Sponsor.jsp";
  Edit_Sponsor servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException, SQLException {

    servlet = new Edit_Sponsor();
    servlet.init(new Sponsor_DAO_Fake());
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
  public void TestLoggedInUserGets302OnDoGetWithNoObject() throws ServletException, IOException{
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
  public void TestLoggedInUserGets302nDoPostWithNoSponsorSet() throws ServletException, IOException{
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
   <p> Test that a logged in user is able to retrieve a specific one of the Sponsor objects. </p>
   */
  @Test
  public void testGetOneSponsorGetsOneSponsor_ID() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    String Sponsor_ID= "nrCUTMuU";
    request.setParameter("sponsorid",Sponsor_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Sponsor_VM sponsor = (Sponsor_VM) session.getAttribute("sponsor");
    assertNotNull(sponsor);
    assertEquals(Sponsor_ID,sponsor.getSponsor_ID());
  }

  /**
   <p> Test that getting one Sponsor can fail. </p>
   */
  @Test
  public void testGetOneSponsorCanFailAndUserIsRedirected() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    String Sponsor_ID= "nrCUTMuUddd";
    request.setParameter("sponsorid",Sponsor_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Sponsor_VM sponsor = (Sponsor_VM) session.getAttribute("sponsor");
    assertNull(sponsor);
    assertEquals(302,response.getStatus());
  }

  /**
   <p> Test that we are able to update Sponsor objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Sponsor
    Sponsor sponsor = new Sponsor();
    sponsor.setSponsor_ID("nrCUTMuU");
    sponsor.setTier_ID("testSponsor");
    sponsor.setWebsite("testSponsor");
    sponsor.setDescription("testSponsor");
    sponsor.setIs_Active(true);
    session.setAttribute("sponsor",sponsor);
//create a new albums parameters
    request.setParameter("inputsponsorSponsor_ID","TestValue");
    request.setParameter("inputsponsorTier_ID","TestValue");
    request.setParameter("inputsponsorWebsite","www.TestValue.com");
    request.setParameter("inputsponsorDescription","TestValue");
    request.setParameter("inputsponsorIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Sponsor_Updated);
    assertEquals("Sponsor updated",Sponsor_Updated);
    assertNotEquals("",Sponsor_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingSponsor objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Sponsor sponsor = new Sponsor();
    sponsor.setSponsor_ID("nrCUTMuU");
    session.setAttribute("sponsor",sponsor);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_IDError = results.get("sponsorSponsor_IDerror");
    String Tier_IDError = results.get("sponsorTier_IDerror");
    String WebsiteError = results.get("sponsorWebsiteerror");
    String DescriptionError = results.get("sponsorDescriptionerror");
    assertNotEquals("",Sponsor_IDError);
    assertNotNull(Sponsor_IDError);
    assertNotEquals("",Tier_IDError);
    assertNotNull(Tier_IDError);
    assertNotEquals("",WebsiteError);
    assertNotNull(WebsiteError);
    assertNotEquals("",DescriptionError);
    assertNotNull(DescriptionError);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Edit_Sponsor();
    servlet.init();
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update Sponsor objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Sponsor
    Sponsor sponsor = new Sponsor();
    sponsor.setSponsor_ID("nrCUTMuU");
    sponsor.setTier_ID("DUPLICATE");
    sponsor.setWebsite("www.DUPLICATE.com");
    sponsor.setDescription("DUPLICATE");
    sponsor.setIs_Active(true);
    session.setAttribute("sponsor",sponsor);
//create a new albums parameters
    request.setParameter("inputsponsorSponsor_ID","nrCUTMuU");
    request.setParameter("inputsponsorTier_ID","DUPLICATE");
    request.setParameter("inputsponsorWebsite","www.DUPLICATE.com");
    request.setParameter("inputsponsorDescription","DUPLICATE");
    request.setParameter("inputsponsorIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Sponsor_Updated);
    assertEquals("Sponsor Not Updated",Sponsor_Updated);
    assertNotEquals("",Sponsor_Updated);
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
//to set the old Sponsor
    Sponsor sponsor = new Sponsor();
    sponsor.setSponsor_ID("nrCUTMuU");
    sponsor.setTier_ID("EXCEPTION");
    sponsor.setWebsite("www.EXCEPTION.com");
    sponsor.setDescription("EXCEPTION");
    sponsor.setIs_Active(true);
    session.setAttribute("sponsor",sponsor);
//create a new albums parameters
    request.setParameter("inputsponsorSponsor_ID","EXCEPTION");
    request.setParameter("inputsponsorTier_ID","EXCEPTION");
    request.setParameter("inputsponsorWebsite","www.EXCEPTION.com");
    request.setParameter("inputsponsorDescription","EXCEPTION");
    request.setParameter("inputsponsorIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Sponsor_Updated);
    assertNotEquals("",Sponsor_Updated);
    assertEquals("Sponsor Not Updated",Sponsor_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

