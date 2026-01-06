package com.beck.crrg_git.crrg.data_fakes;

import com.beck.crrg_git.crrg.data_interfaces.ISponsor_Tag_DAO;
import com.beck.crrg_git.crrg.models.*;

import java.sql.SQLException;
import java.util.*;

import com.beck.crrg_git.crrg.data.Sponsor_Tag_DAO;
import com.beck.crrg_git.crrg.models.Sponsor_Tag;
import com.beck.crrg_git.crrg.data_interfaces.ISponsor_Tag_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Sponsor_Tag_DAO_Fake implements ISponsor_Tag_DAO {
  private  List<Sponsor_Tag_VM> sponsor_tagVMs;
  public Sponsor_Tag_DAO_Fake(){
    sponsor_tagVMs = new ArrayList<>();
    Picture _picture = new Picture();
    Sponsor_Tag sponsor_tag0 = new Sponsor_Tag("OCVWslWE", 40, "IjXxtTDF", false);
    Sponsor_Tag sponsor_tag1 = new Sponsor_Tag("OCVWslWE", 30, "MLusAehT", false);
    Sponsor_Tag sponsor_tag2 = new Sponsor_Tag("OCVWslWE", 63, "rZwDBlsm", false);
    Sponsor_Tag sponsor_tag3 = new Sponsor_Tag("OCVWslWE", 24, "ejmLYalR", false);
    Sponsor_Tag sponsor_tag4 = new Sponsor_Tag("HbqDGWOE", 31, "DQdPPYqo", true);
    Sponsor_Tag sponsor_tag5 = new Sponsor_Tag("lLvyLGnL", 31, "GbvSUgyY", false);
    Sponsor_Tag sponsor_tag6 = new Sponsor_Tag("UeinwBjy", 31, "ivPKIKGr", false);
    Sponsor_Tag sponsor_tag7 = new Sponsor_Tag("uWbCwogl", 31, "vGkrWWaN", false);
    Sponsor_Tag sponsor_tag8 = new Sponsor_Tag("CkcEMXeL", 55, "UINoIlck", true);
    Sponsor_Tag sponsor_tag9 = new Sponsor_Tag("CkcEMXeL", 17, "UhdOqmnb", false);
    Sponsor_Tag sponsor_tag10 = new Sponsor_Tag("CkcEMXeL", 36, "pxKmpqdu", true);
    Sponsor_Tag sponsor_tag11 = new Sponsor_Tag("CkcEMXeL", 57, "rvUsUdwU", false);
    Sponsor_Tag sponsor_tag12 = new Sponsor_Tag("CCZwaEYw", 20, "mMmxSUNm", true);
    Sponsor_Tag sponsor_tag13 = new Sponsor_Tag("YPlkRgXJ", 20, "dZFunHkE", false);
    Sponsor_Tag sponsor_tag14 = new Sponsor_Tag("xlKwebFw", 20, "qyWKQrWk", false);
    Sponsor_Tag sponsor_tag15 = new Sponsor_Tag("QifFXAnI", 20, "mhCjLdJu", true);
    Sponsor_Tag sponsor_tag16 = new Sponsor_Tag("iSeKfTdG", 35, "mZIMDUUM", false);
    Sponsor_Tag sponsor_tag17 = new Sponsor_Tag("iSeKfTdG", 30, "KInWuGUU", true);
    Sponsor_Tag sponsor_tag18 = new Sponsor_Tag("iSeKfTdG", 26, "DIEOGFCR", true);
    Sponsor_Tag sponsor_tag19 = new Sponsor_Tag("iSeKfTdG", 28, "aAidgpJn", false);
    Sponsor_Tag sponsor_tag20 = new Sponsor_Tag("VHSJVOYl", 30, "DJFDZnNU", true);
    Sponsor_Tag sponsor_tag21 = new Sponsor_Tag("uYCcIlKu", 30, "WZLYaimv", false);
    Sponsor_Tag sponsor_tag22 = new Sponsor_Tag("wjnrvXEs", 30, "comuSKWK", true);
    Sponsor_Tag sponsor_tag23 = new Sponsor_Tag("CEOrHcWQ", 30, "qQeCFgMf", false);
    Sponsor_Tag_VM sponsor_tag_VM0= new Sponsor_Tag_VM(sponsor_tag0,_picture);
    Sponsor_Tag_VM sponsor_tag_VM1= new Sponsor_Tag_VM(sponsor_tag1,_picture);
    Sponsor_Tag_VM sponsor_tag_VM2= new Sponsor_Tag_VM(sponsor_tag2,_picture);
    Sponsor_Tag_VM sponsor_tag_VM3= new Sponsor_Tag_VM(sponsor_tag3,_picture);
    Sponsor_Tag_VM sponsor_tag_VM4= new Sponsor_Tag_VM(sponsor_tag4,_picture);
    Sponsor_Tag_VM sponsor_tag_VM5= new Sponsor_Tag_VM(sponsor_tag5,_picture);
    Sponsor_Tag_VM sponsor_tag_VM6= new Sponsor_Tag_VM(sponsor_tag6,_picture);
    Sponsor_Tag_VM sponsor_tag_VM7= new Sponsor_Tag_VM(sponsor_tag7,_picture);
    Sponsor_Tag_VM sponsor_tag_VM8= new Sponsor_Tag_VM(sponsor_tag8,_picture);
    Sponsor_Tag_VM sponsor_tag_VM9= new Sponsor_Tag_VM(sponsor_tag9,_picture);
    Sponsor_Tag_VM sponsor_tag_VM10= new Sponsor_Tag_VM(sponsor_tag10,_picture);
    Sponsor_Tag_VM sponsor_tag_VM11= new Sponsor_Tag_VM(sponsor_tag11,_picture);
    Sponsor_Tag_VM sponsor_tag_VM12= new Sponsor_Tag_VM(sponsor_tag12,_picture);
    Sponsor_Tag_VM sponsor_tag_VM13= new Sponsor_Tag_VM(sponsor_tag13,_picture);
    Sponsor_Tag_VM sponsor_tag_VM14= new Sponsor_Tag_VM(sponsor_tag14,_picture);
    Sponsor_Tag_VM sponsor_tag_VM15= new Sponsor_Tag_VM(sponsor_tag15,_picture);
    Sponsor_Tag_VM sponsor_tag_VM16= new Sponsor_Tag_VM(sponsor_tag16,_picture);
    Sponsor_Tag_VM sponsor_tag_VM17= new Sponsor_Tag_VM(sponsor_tag17,_picture);
    Sponsor_Tag_VM sponsor_tag_VM18= new Sponsor_Tag_VM(sponsor_tag18,_picture);
    Sponsor_Tag_VM sponsor_tag_VM19= new Sponsor_Tag_VM(sponsor_tag19,_picture);
    Sponsor_Tag_VM sponsor_tag_VM20= new Sponsor_Tag_VM(sponsor_tag20,_picture);
    Sponsor_Tag_VM sponsor_tag_VM21= new Sponsor_Tag_VM(sponsor_tag21,_picture);
    Sponsor_Tag_VM sponsor_tag_VM22= new Sponsor_Tag_VM(sponsor_tag22,_picture);
    Sponsor_Tag_VM sponsor_tag_VM23= new Sponsor_Tag_VM(sponsor_tag23,_picture);
    sponsor_tagVMs.add(sponsor_tag_VM0);
    sponsor_tagVMs.add(sponsor_tag_VM1);
    sponsor_tagVMs.add(sponsor_tag_VM2);
    sponsor_tagVMs.add(sponsor_tag_VM3);
    sponsor_tagVMs.add(sponsor_tag_VM4);
    sponsor_tagVMs.add(sponsor_tag_VM5);
    sponsor_tagVMs.add(sponsor_tag_VM6);
    sponsor_tagVMs.add(sponsor_tag_VM7);
    sponsor_tagVMs.add(sponsor_tag_VM8);
    sponsor_tagVMs.add(sponsor_tag_VM9);
    sponsor_tagVMs.add(sponsor_tag_VM10);
    sponsor_tagVMs.add(sponsor_tag_VM11);
    sponsor_tagVMs.add(sponsor_tag_VM12);
    sponsor_tagVMs.add(sponsor_tag_VM13);
    sponsor_tagVMs.add(sponsor_tag_VM14);
    sponsor_tagVMs.add(sponsor_tag_VM15);
    sponsor_tagVMs.add(sponsor_tag_VM16);
    sponsor_tagVMs.add(sponsor_tag_VM17);
    sponsor_tagVMs.add(sponsor_tag_VM18);
    sponsor_tagVMs.add(sponsor_tag_VM19);
    sponsor_tagVMs.add(sponsor_tag_VM20);
    sponsor_tagVMs.add(sponsor_tag_VM21);
    sponsor_tagVMs.add(sponsor_tag_VM22);
    sponsor_tagVMs.add(sponsor_tag_VM23);
    Collections.sort(sponsor_tagVMs);
  }
  @Override
  public int addSponsor_Tag(Sponsor_Tag_VM tag) throws SQLException {
    if (duplicateKey(tag)){
      return 0;
    }
    if (exceptionKey(tag)){
      throw new SQLException("error");
    }
    int size = sponsor_tagVMs.size();
    Sponsor_Tag_VM sponsor_tag_VM = new Sponsor_Tag_VM(tag, new Picture());
    sponsor_tagVMs.add(sponsor_tag_VM);
    int newsize = sponsor_tagVMs.size();
    return newsize-size;
  }

  @Override
  public int removeSponsor_Tag(Sponsor_Tag tag) throws SQLException {
    int size = sponsor_tagVMs.size();
    int location =-1;
    for (int i=0;i<sponsor_tagVMs.size();i++){
      if (sponsor_tagVMs.get(i).getSponsor_ID().equals(tag.getSponsor_ID())&&sponsor_tagVMs.get(i).getPicture_ID().equals(tag.getPicture_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException("Unable To Find Sponsor_Tag.");
    }
    sponsor_tagVMs.remove(location);
    int newsize = sponsor_tagVMs.size();
    return size-newsize;
  }

  @Override
  public List<Sponsor_Tag_VM> getAllSponsor_Tag(String Sponsor_ID) {
    List<Sponsor_Tag_VM> results = new ArrayList<>();
    for (Sponsor_Tag_VM sponsor_tag : sponsor_tagVMs){
      if (Sponsor_ID.isEmpty()|| sponsor_tag.getSponsor_ID().equals(Sponsor_ID)){
        results.add(sponsor_tag);
      }
    }
    return results;
  }

  @Override
  public int deleteSponsor_Tag(String sponsor, int PhotoID) throws SQLException {
    int location =-1;

    for (int i=0;i<sponsor_tagVMs.size();i++) {
      if (sponsor_tagVMs.get(i).getSponsor_ID().equals(sponsor)&& sponsor_tagVMs.get(i).getPicture_ID().equals(PhotoID)) {
          location = i;
          break;
      }
    }
    if (location==-1){
      throw new SQLException("Invalid ID");
    }
    sponsor_tagVMs.remove(location);
    return 1;
  }
  private boolean duplicateKey(Sponsor_Tag _sponsor_tag){
    return _sponsor_tag.getSponsor_ID().equals("DUPLICATE");
  }
  private boolean exceptionKey(Sponsor_Tag _sponsor_tag){
    return _sponsor_tag.getSponsor_ID().equals("EXCEPTION");
  }
}
