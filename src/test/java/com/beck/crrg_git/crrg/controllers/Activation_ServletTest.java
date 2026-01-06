package com.beck.crrg_git.crrg.controllers;
import java.io.IOException;

import com.beck.crrg_git.crrg.data_fakes.Album_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.Contributor_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.Picture_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.Sponsor_DAO_Fake;

import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class Activation_ServletTest {
  private static final String PAGE="WEB-INF/crrg/Delete_Album.jsp";
  Activation_Servlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new Activation_Servlet();
    servlet.init(new Picture_DAO_Fake(),new Album_DAO_Fake(),  new Contributor_DAO_Fake(), new Sponsor_DAO_Fake());
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
  public void TestLoggedInUserGets200OnDoPost() throws ServletException, IOException{
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
  public void TestLoggedOutUserGetsForbiddenDoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(403,status);

    session = request.getSession(false);
    assertNull(session);
  }



  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGetsForbiddenonDoPost() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("WrongRole");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(403,status);
    session = request.getSession(false);
    assertNull(session);
  }
  /**
   <p> Test that the deactivation servlet can deactivate a Album </p>
   */
  @Test
  public void TestDeactivateAlbumCanDeactivate() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");

    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","album");
    Integer objectID=3;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    String writer = response.getContentAsString();


    assertEquals(1,status);
  }

  /**
   <p> Test that the deactivate servlet will fail if the Album is already insactive. </p>
   */
  @Test
  public void TestDeactivateAlbumFailIfAlreadyFalse() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","album");
    Integer objectID=2;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(0,status);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestDeActivateAlbumCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","album");
    Integer objectID=30;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(0,status);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestActivateAlbumCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","album");
    Integer objectID=300;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");

    assertEquals(0,status);
  }

  /**
   <p> Test that the activation servlet can active a Album </p>
   */
  @Test
  public void TestactivateAlbumCanActivate() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","album");
    Integer objectID=2;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(1,status);
  }

  /**
   <p> Test that the activation servlet can fail if the Album is already active </p>
   */
  @Test
  public void TestActivateAlbumFailIfAlreadyTrue() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","album");
    Integer objectID=1;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(0,status);
  }

  /**
   <p> Test that the deactivation servlet can deactivate a Album </p>
   */
  @Test
  public void TestDeactivatePictureCanDeactivate() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","picture");
    Integer objectID=2;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(1,status);
  }

  /**
   <p> Test that the deactivate servlet will fail if the Album is already insactive. </p>
   */
  @Test
  public void TestDeactivatePictureFailIfAlreadyFalse() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","picture");
    Integer objectID=1;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(0,status);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestDeActivatePictureCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","picture");
    Integer objectID=300;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");

    assertEquals(0,status);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestActivatePictureCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","picture");
    Integer objectID=300;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");

    assertEquals(0,status);
  }

  /**
   <p> Test that the activation servlet can active a Album </p>
   */
  @Test
  public void TestactivatePictureCanActivate() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","picture");
    Integer objectID=1;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(1,status);
  }

  /**
   <p> Test that the activation servlet can fail if the Album is already active </p>
   */
  @Test
  public void TestActivatePictureFailIfAlreadyTrue() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","picture");
    Integer objectID=2;
    request.setParameter("objectID",objectID.toString());
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(0,status);
  }

  /**
   <p> Test that the deactivation servlet can deactivate a Album </p>
   */
  @Test
  public void TestDeactivateSponsorCanDeactivate() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");

    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","sponsor");
    String objectID="nlSjPWct";
    request.setParameter("objectID",objectID);
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    String writer = response.getContentAsString();


    assertEquals(1,status);
  }

  /**
   <p> Test that the deactivate servlet will fail if the Album is already insactive. </p>
   */
  @Test
  public void TestDeactivateSponsorFailIfAlreadyFalse() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","sponsor");
    String objectID="wIUiNXNE";
    request.setParameter("objectID",objectID);
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(0,status);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestDeActivateSponsorCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","sponsor");
    String objectID="wIUiNXNEsssss";
    request.setParameter("objectID",objectID);
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(0,status);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestActivateSponsorCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","sponsor");
    String objectID="wIUiNXNEssss";
    request.setParameter("objectID",objectID);
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");

    assertEquals(0,status);
  }

  /**
   <p> Test that the activation servlet can active a Album </p>
   */
  @Test
  public void TestactivateSponsorCanActivate() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","sponsor");
    String objectID="wIUiNXNE";
    request.setParameter("objectID",objectID);
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(1,status);
  }

  /**
   <p> Test that the activation servlet can fail if the Album is already active </p>
   */
  @Test
  public void TestActivateSponsorFailIfAlreadyTrue() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","sponsor");
    String objectID="nlSjPWct";
    request.setParameter("objectID",objectID);
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(0,status);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Activation_Servlet();
    servlet.init();
  }

}

