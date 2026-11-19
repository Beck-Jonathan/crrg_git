package com.beck.crrg_git.crrg.controllers;

import com.beck.crrg_git.crrg.data.Skater_DAO;
import com.beck.crrg_git.crrg.models.Skater;
import com.beck.crrg_git.crrg.models.User;
import com.beck.crrg_git.crrg.data_interfaces.iSkater_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
/******************
 Create the Servlet  For adding to The  Skater table
 Created By Jonathan Beck 10/16/2026
 ***************/
/**
 * Servlet implementation class AddSkaterServlet
 *
 * <p>This servlet handles the addition of new {@link Skater} entries in the application.
 * It is mapped to the URL pattern <code>/addSkater</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the form for adding a new Skater. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for adding a new Skater. Validates input,
 * attempts insertion into the database via {@link Skater_DAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful insertions redirect to the list of all Skater.</p
 > * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 10/16/2026</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.crrg_git.crrg.models.Skater
 * @see com.beck.crrg_git.crrg.data.Skater_DAO
 * @see jakarta.servlet.http.HttpServlet
 */

@WebServlet("/addSkater")
public class Add_Skater extends HttpServlet{
  private iSkater_DAO skaterDAO;
  @Override
  public void init() {
    skaterDAO = new Skater_DAO();
  }
  public void init(iSkater_DAO skaterDAO){
    this.skaterDAO = skaterDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      session.invalidate();
      resp.sendRedirect("crrgLogin");
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Skater");
    req.getRequestDispatcher("WEB-INF/crrg/AddSkater.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      session.invalidate();
      resp.sendRedirect("crrgLogin");
      return;
    }

    String _Skater_ID = req.getParameter("inputskaterSkater_ID");
    if (_Skater_ID!=null) {
      _Skater_ID=_Skater_ID.trim();
    }
    String _Number = req.getParameter("inputskaterNumber");
    if (_Number!=null) {
      _Number=_Number.trim();
    }
    String _Derby_Name = req.getParameter("inputskaterDerby_Name");
    if (_Derby_Name!=null) {
      _Derby_Name=_Derby_Name.trim();
    }
    String _First_Name = req.getParameter("inputskaterFirst_Name");
    if (_First_Name!=null) {
      _First_Name=_First_Name.trim();
    }
    String _Last_Name = req.getParameter("inputskaterLast_Name");
    if (_Last_Name!=null) {
      _Last_Name=_Last_Name.trim();
    }
    String _is_active = req.getParameter("inputskateris_active");
    if (_is_active!=null) {
      _is_active=_is_active.trim();
    }
    String _Start_Date = req.getParameter("inputskaterStart_Date");
    if (_Start_Date!=null) {
      _Start_Date=_Start_Date.trim();
    }
    Map<String, String> results = new HashMap<>();
    results.put("Skater_ID",_Skater_ID);
    results.put("Number",_Number);
    results.put("Derby_Name",_Derby_Name);
    results.put("First_Name",_First_Name);
    results.put("Last_Name",_Last_Name);
    results.put("is_active",_is_active);
    results.put("Start_Date",_Start_Date);
    Skater skater = new Skater();
    int errors =0;
    try {
      skater.setNumber(_Number);
    } catch(Exception e) {results.put("skaterNumbererror", e.getMessage());
      errors++;
    }
    try {
      skater.setDerby_Name(_Derby_Name);
    } catch(Exception e) {results.put("skaterDerby_Nameerror", e.getMessage());
      errors++;
    }
    try {
      skater.setFirst_Name(_First_Name);
    } catch(Exception e) {results.put("skaterFirst_Nameerror", e.getMessage());
      errors++;
    }
    try {
      skater.setLast_Name(_Last_Name);
    } catch(Exception e) {results.put("skaterLast_Nameerror", e.getMessage());
      errors++;
    }
    try {
      skater.setis_active(Boolean.parseBoolean(_is_active));
    } catch(Exception e) {results.put("skateris_activeerror", e.getMessage());
      errors++;
    }
    try {
      DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
      Date _minDate = df.parse(_Start_Date);


    } catch(Exception e) {results.put("skaterStart_Dateerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=skaterDAO.add(skater);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Skater Added");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Skaters");
        return;
      } else {
        results.put("dbStatus","Skater Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add Skater");
    req.getRequestDispatcher("WEB-INF/crrg/AddSkater.jsp").forward(req, resp);

  }
}

