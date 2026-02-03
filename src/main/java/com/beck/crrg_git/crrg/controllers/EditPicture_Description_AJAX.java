package com.beck.crrg_git.crrg.controllers;

import com.beck.crrg_git.crrg.data.Picture_DAO;
import com.beck.crrg_git.crrg.models.Picture;
import com.beck.crrg_git.crrg.models.User;
import com.beck.crrg_git.crrg.data_interfaces.iPicture_DAO;
import com.beck.crrg_git.crrg.data.Picture_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iPicture_DAO;
import io.netty.channel.unix.Errors;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static com.beck.crrg_git.crrg.shared.ResultWriter.writeResult;
/******************
 Create the Servlet Viuw/Edit from the Picture table
 Created By Jonathan Beck 1/13/2026
 ***************/
/**
 * Servlet implementation class EditPicture_Description_AJAX
 *
 * <p>This servlet handles the editing of {@link Picture} entries in the application.
 * It is mapped to the URL pattern <code>/editPicture</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing a single Pictures. Allows editing of appropriate fields. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for editing a Picture. Validates input,
 * attempts update of the the database via {@link Picture_DAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful updates redirect to the list of all Picture.</p
 > * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 1/13/2026</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.crrg_git.crrg.models.Picture
 * @see com.beck.crrg_git.crrg.data.Picture_DAO
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/editPicture_Description_AJAX")
public class EditPicture_Description_AJAX extends HttpServlet {
  /* ---------- Success ---------- */
  public static final int SUCCESS = 1;
  public static final int NO_CHANGE = 0;

  /* ---------- Authorization ---------- */
  public static final int UNAUTHORIZED = -1;

  /* ---------- Input Errors ---------- */
  public static final int INVALID_PICTURE_ID = -2;
  public static final int OLD_DESCRIPTION_MISSING = -3;
  public static final int NEW_DESCRIPTION_INVALID = -4;
  public static final int INVALID_PICTURE_OBJECT = -5;
  public static final int INVALID_OLD_DESCRIPTION = -6;
  public static final int INVALID_NEW_DESCRIPTION = -7;

  /* ---------- Persistence ---------- */
  public static final int DATABASE_ERROR = -8;


    private iPicture_DAO pictureDAO;

    @Override
    public void init() {
      pictureDAO = new Picture_DAO();

    }
    public void init(iPicture_DAO pictureDAO){
      this.pictureDAO = pictureDAO;

    }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int result=NO_CHANGE;

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      result =UNAUTHORIZED;
      writeResult(resp,result);
      return;
    }

    int primaryKey = -1;
    String oldDescription =null;
    String newDescription =null;
    try{
      primaryKey = Integer.parseInt(req.getParameter("pictureid"));
    }catch (Exception e) {
      result=INVALID_PICTURE_ID;
      writeResult(resp,result);
      return;
    }
    try{
      oldDescription = req.getParameter("picture_oldDescription");
      if (oldDescription==null||oldDescription.isEmpty()){
        throw new ServletException("Old description is empty");
      }
    }catch (Exception e) {
      result=OLD_DESCRIPTION_MISSING;
      writeResult(resp,result);
      return;
    }
    try{
      newDescription = req.getParameter("picture_newDescription");
      if (newDescription==null||newDescription.isEmpty()){
        throw new ServletException("New description is empty");
      }
    }catch (Exception e) {
      result=NEW_DESCRIPTION_INVALID;
      writeResult(resp,result);
      return;
    }
    Picture oldPicture = new Picture();
    Picture newPicture = new Picture();
    try{
      oldPicture.setPicture_ID(primaryKey);
    }catch (Exception e) {
      result=INVALID_PICTURE_OBJECT;
      writeResult(resp,result);
      return;
    }
    try{
      oldPicture.setDescription(oldDescription);
    }catch (Exception e) {
      result=INVALID_OLD_DESCRIPTION;
      writeResult(resp,result);
      return;
    }
    try{
      newPicture.setDescription(newDescription);
    }catch (Exception e) {
      result=INVALID_NEW_DESCRIPTION;
      writeResult(resp,result);
      return;
    }
    try{
      result=pictureDAO.updatePicture_Description(oldPicture , newPicture);
    }catch (Exception e) {
      result=DATABASE_ERROR;
      writeResult(resp,result);
      return;
    }
    writeResult(resp,result);
    return;
  }

}
