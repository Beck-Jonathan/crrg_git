package com.beck.crrg_git.crrg.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.crrg_git.crrg.data_fakes.User_DAO_Fake;
import com.beck.crrg_git.crrg.models.User;

import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class Edit_RoleTest {
  private static final String PAGE="WEB-INF/crrg/Delete_User.jsp";
  Edit_Role servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new Edit_Role();
    servlet.init(new User_DAO_Fake());
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
   <p> Test that the Change Role Servlet can change the role of a user</p>

   */
  @Test
  public void TesChangeRoleCanChangerole() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("objectID","bnyoAUFg");
    request.setParameter("mode","BOD");
    servlet.doPost(request,response);
    String status = response.getContentAsString();
    assertEquals("1",status);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
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
   <p> Test that the deactivate servlet will fail if the User is already insactive. </p>
   */
  @Test
  public void TestDeactivateFailIfAlreadyFalse() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("objectID","bnyoAUFg");
    request.setParameter("mode","plHOlKWC");
    servlet.doPost(request,response);
    String status = response.getContentAsString();
    assertEquals("User already in Role.",status);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestDeActivateCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("objectID","xxxxxxxxxxxxx");
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    String status = response.getContentAsString();
    assertEquals("User not found",status);
  }





  /**
   <p> Test that the activation servlet can fail if the User is already active </p>
   */
  @Test
  public void TestActivateFailIfAlreadyTrue() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("objectID","bnyoAUFg");
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    String status = response.getContentAsString();
    assertEquals("Invalid Role",status);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Edit_Role();
    servlet.init();
  }

}

