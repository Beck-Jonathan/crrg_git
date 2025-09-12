package com.beck.crrg_git.crrg.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.crrg_git.crrg.data_fakes.Album_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.Contributor_DAO_Fake;
import com.beck.crrg_git.crrg.data_fakes.Picture_DAO_Fake;
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

public class Approval_ServletTest {
  private static final String PAGE="WEB-INF/crrg/Delete_Album.jsp";
  Approval_Servlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new Approval_Servlet();
    servlet.init(new Album_DAO_Fake(),new Picture_DAO_Fake());
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
  public void TestDeapprovePictureCanDeactivate() throws ServletException, IOException {
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
  public void TestDeapprovePictureFailIfAlreadyFalse() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","picture");
    Integer objectID=37;
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
  public void TestDeApprovePictureCanFailIfKeyDoesNotExist() throws ServletException, IOException {
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
  public void TestApprovePictureCanFailIfKeyDoesNotExist() throws ServletException, IOException {
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
  public void TestApprovePictureCanActivate() throws ServletException, IOException {
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
  public void TestApprovePictureFailIfAlreadyTrue() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("object","picture");
    Integer objectID=46;
    request.setParameter("objectID",objectID.toString());
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
    servlet = new Approval_Servlet();
    servlet.init();
  }

}

