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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class EditPicture_Description_AJAXTest {
  private static final String PAGE="WEB-INF/crrg/Edit_Picture.jsp";
  EditPicture_Description_AJAX servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  /* ---------- Success ---------- */
  public static final int SUCCESS = 1;
  public static final int NO_CHANGE = 0;
  /* ---------- Authorization ---------- */
  public static final int UNAUTHORIZED = -1;
  /* ---------- Input Errors ---------- */
  public static final int INVALID_PICTURE_ID = -2;
  public static final int OLD_DESCRIPTION_MISSING = -3;
  public static final int NEW_DESCRIPTION_MISSING = -4;
  public static final int INVALID_PICTURE_OBJECT = -5;
  public static final int INVALID_OLD_DESCRIPTION = -6;
  public static final int INVALID_NEW_DESCRIPTION = -7;
  /* ---------- Persistence ---------- */
  public static final int DATABASE_ERROR = -8;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditPicture_Description_AJAX();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "Edit_Picture");
    servlet.init(servletConfig);
    servlet.init(new Picture_DAO_Fake());
    request =  new MockHttpServletRequest(servletContext);
    response = new MockHttpServletResponse();
    session = new MockHttpSession(servletContext);
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
  @Test
  public void TestErrorCodeUNAUTHORIZEDCanBeReached() throws ServletException, IOException {
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = String.valueOf(UNAUTHORIZED);
    assertEquals(expected,result);
  }
  @Test
  public void TestErrorCodeINVALIDIDCanBeReached() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected =String.valueOf( INVALID_PICTURE_ID);
    assertEquals(expected,result);
  }
  @Test
  public void TestErrorCodeOLD_VALUE_MISSINGCanBeReached() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setParameter("pictureid","1");
    request.setSession(session);
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = String.valueOf(OLD_DESCRIPTION_MISSING);
    assertEquals(expected,result);
  }
  @Test
  public void TestErrorCodeNEW_VALUE_MISSINGCanBeReached() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setParameter("pictureid","1");
    request.setParameter("picture_oldDescription","FuvHuRdtZFGKDCakPSTxdZrjXJHhKxuptOnmqYnxGJlVPhfSRkQYgDaLjUltauLHLgoThxCtZCqMnYdXNKNaGDCDNNDBIqkNxt");
    request.setSession(session);
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = String.valueOf(NEW_DESCRIPTION_MISSING);
    assertEquals(expected,result);
  }
  @Test
  public void TestErrorCodeINVALIDOBJECTCanBeReached() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setParameter("pictureid","-1");
    request.setParameter("picture_oldDescription","TUbJotiDTGmnYRNyWmnZYnqXsyaZJspeWQISyKHmjObpdlqhXtIUxoErSTQbbGSZdSfDcpDViiwFTvCRfuSaZWxBUBJjKNuuaQ");
    request.setParameter("picture_newDescription","VflhIpUsLLGnfwQlLgeniTymNJYVykvAkMJjFlpoudvQVdoQckqDUcqbfBmJGnqeMSHBGJrxsYvCOvnnhHLIgrrqkDenoErECm");
    request.setSession(session);
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = String.valueOf(INVALID_PICTURE_OBJECT);
    assertEquals(expected,result);
  }
  @Test
  public void TestErrorCodeINVALID_OLD_VALUECanBeReached() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setParameter("pictureid","1");
    request.setParameter("picture_oldDescription","KZssJvgoSiNxcXgfbJcRGtOdHxWrlSmhSOusDLIEBWIYVatdbHlxuSpupfosxTNAiFXJcpMNyBSKXJvaPbcSMPRBYLJFHBLKwIIiEa");
    request.setParameter("picture_newDescription","QtQNfwXmxYuVrHqaWAkoZtnIMflcXGsluUmkKlRJGFofCGakhTkqAjHAyKnVqWcvmDpLZLDIjhCXAjpVKKBEUDcKUwvRaiLiPC");
    request.setSession(session);
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = String.valueOf(INVALID_OLD_DESCRIPTION);
    assertEquals(expected,result);
  }
  @Test
  public void TestErrorCodeINVALID_NEW_VALUECanBeReached() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setParameter("pictureid","1000");
    request.setParameter("picture_oldDescription","kOIghPCFcyZRTMbqYANBspGLYGTLQEpvBTVEAaatfWpMcqqCURaqvkXhyqZfXKOTyiSuZnbsrbZdYaRailsNElJruovKoQUVCn");
    request.setParameter("picture_newDescription","DCWGiOCsoOUXsTpbGtwQZAeqXDaNDHVmOSeJxCsTcWDpyornVHqlbVrqxRbLNCFERIqXIrDvqZOmaElVPkOvvvJRGDblXRUxlhgAVN");
    request.setSession(session);
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected =String.valueOf( INVALID_NEW_DESCRIPTION);
    assertEquals(expected,result);
  }
  @Test
  public void TestErrorCodeDATABASE_ERRORCanBeReached() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setParameter("pictureid","1000");
    request.setParameter("picture_oldDescription","OmokvMEPtMumIbULgGBoKYItnoWEUsyIgLAwNqJCnaoJSSPLZReSmSPqBynytJLjrKeYdvTTyeDdBdQqBrMdwVfbholoPJOcWB");
    request.setParameter("picture_newDescription","EXCEPTION");
    request.setSession(session);
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = String.valueOf(DATABASE_ERROR);
    assertEquals(expected,result);
  }
  @Test
  public void TestCodeSUCCESSCanBeReached() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setParameter("pictureid","1");
    request.setParameter("picture_oldDescription","nsAWZxQoppXhOIrQxFunyqvaJVsUYNPkwBmfgaKkrDGgWsQNnCEuuXGZaWJYLqnLZOMnFEXYDxyEvlBwuvarOGdPsgBFECMAyw");
    request.setParameter("picture_newDescription","AUdbWXXQuMTLhGKockBBYtytAwwuEVsveBsAPKdskptoIXxkSbChWrWbSyIxRlIubCpnBODAGIoFdaTcTZcrsrdkllYfMovPmk");
    request.setSession(session);
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = String.valueOf(SUCCESS);
    assertEquals(expected,result);
  }
  @Test
  public void TestErrorCodeNO_CHANGECanBeReached() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setParameter("pictureid","1");
    request.setParameter("picture_oldDescription","NgHrQZCTkYqoYIYcfxJQOgchJUnbVgUwkbZUIhYmTaqmffGmDVewdcwtmsxvqHELgBMdBbDltFfoBfigoRQiyHxUoZpFiipdtJ");
    request.setParameter("picture_newDescription","NgHrQZCTkYqoYIYcfxJQOgchJUnbVgUwkbZUIhYmTaqmffGmDVewdcwtmsxvqHELgBMdBbDltFfoBfigoRQiyHxUoZpFiipdtJ");
    request.setSession(session);
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected =String.valueOf(NO_CHANGE);
    assertEquals(expected,result);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditPicture_Description_AJAX();
    servlet.init();
  }

}

