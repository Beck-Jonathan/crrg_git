package com.beck.crrg_git.crrg.data_fakes;

import com.beck.crrg_git.crrg.data_interfaces.iSkater_Bio_Line_DAO;

import java.util.*;

import com.beck.crrg_git.crrg.models.Skater_Bio_Line;
import com.beck.crrg_git.crrg.models.Skater_Bio_Line_VM;

public class Skater_Bio_Line_DAO_Fake implements iSkater_Bio_Line_DAO {
  private  List<Skater_Bio_Line_VM> skater_bio_lineVMs;
  public Skater_Bio_Line_DAO_Fake(){
    skater_bio_lineVMs = new ArrayList<>();
    Skater_Bio_Line skater_bio_line0 = new Skater_Bio_Line("xnaZQQNPBmjaguouVCeKxCBfMcsPbcSPZMOd", "ryVRrwnYOPCRiksjRFAehlhhiybGpspsUlPZ", "TywrGGTq", "GkmoYeEe");
    Skater_Bio_Line skater_bio_line1 = new Skater_Bio_Line("VUImAqyDmGxldZpokAuIdNlPxsCHFIhTkvpB", "ryVRrwnYOPCRiksjRFAehlhhiybGpspsUlPZ", "VqwIpckf", "HNFVZAIl");
    Skater_Bio_Line skater_bio_line2 = new Skater_Bio_Line("duFFsgieDKXJjdZknXfjhhKxqSnQwsKXjGkZ", "ryVRrwnYOPCRiksjRFAehlhhiybGpspsUlPZ", "BUYkjVog", "UumceIPk");
    Skater_Bio_Line skater_bio_line3 = new Skater_Bio_Line("TGQQAZcsAiYyuARrcuLfnddfbLtREqVOWUJj", "ryVRrwnYOPCRiksjRFAehlhhiybGpspsUlPZ", "eYpapHdU", "XGnihSgg");
    Skater_Bio_Line skater_bio_line4 = new Skater_Bio_Line("dDTGOYVBVmCoaIGVKQIhvqiAekUJcigmaBQP", "ryVRrwnYOPCRiksjRFAehlhhiybGpspsUlPZ", "hYNfwtdV", "pMUZvduu");
    Skater_Bio_Line skater_bio_line5 = new Skater_Bio_Line("ixDcQRLWabvEcDnNnHpAqEDPoTRpgSBaOUEH", "DVCwAHpFqtCywFOtInJmDqaZsBsnGnKpOXoE", "kbvPBObP", "ohMfSWlh");
    Skater_Bio_Line skater_bio_line6 = new Skater_Bio_Line("NVoOxfMasjopahcdBAeFgZNwndvtSAuLaiJF", "DVCwAHpFqtCywFOtInJmDqaZsBsnGnKpOXoE", "ocBrnSEO", "WWlgNuQR");
    Skater_Bio_Line skater_bio_line7 = new Skater_Bio_Line("ExsDrtonetsMIelAuIxKGdhrfLUygbKYhoTF", "DVCwAHpFqtCywFOtInJmDqaZsBsnGnKpOXoE", "npmseqjW", "gqCeauyi");
    Skater_Bio_Line skater_bio_line8 = new Skater_Bio_Line("MRVnCpYtHSKQbxpIOvCAOYnkaHIwaTNSTvTM", "DVCwAHpFqtCywFOtInJmDqaZsBsnGnKpOXoE", "BdjZbnYU", "OVnajuiJ");
    Skater_Bio_Line skater_bio_line9 = new Skater_Bio_Line("KlpCOeTMuSSRagdUWRlTVpXIDVbEkSxPpwVF", "DVCwAHpFqtCywFOtInJmDqaZsBsnGnKpOXoE", "dBbfXZWq", "jZjsSwXn");
    Skater_Bio_Line skater_bio_line10 = new Skater_Bio_Line("nOCleevMmpuHHQehntqPlnuRnQIytWXFLnyK", "jATRpgyQgkljYxHRSuqLKnmuryCCwobiVXdD", "KwlIjbhv", "mkeFqJeK");
    Skater_Bio_Line skater_bio_line11 = new Skater_Bio_Line("rZBeFpicitcDSdrWrsDMlurKOMpTuqHLtHfo", "jATRpgyQgkljYxHRSuqLKnmuryCCwobiVXdD", "WNkgAvAG", "FpEaToMj");
    Skater_Bio_Line skater_bio_line12 = new Skater_Bio_Line("JhMKXxWkDolANqNHGAVlaBunTkYvqaSSjoiH", "jATRpgyQgkljYxHRSuqLKnmuryCCwobiVXdD", "YTqyirug", "uRPrCWHK");
    Skater_Bio_Line skater_bio_line13 = new Skater_Bio_Line("hjeYyaCOjCZlvXEebhPGKmxVTsQBQAHWJPsP", "jATRpgyQgkljYxHRSuqLKnmuryCCwobiVXdD", "RyHXIpJM", "euGuVXcT");
    Skater_Bio_Line skater_bio_line14 = new Skater_Bio_Line("AJloBuNlRWPymVhckkXmUenbCJIMOGQlxIgX", "jATRpgyQgkljYxHRSuqLKnmuryCCwobiVXdD", "XcArJEEI", "dadXCPZR");
    Skater_Bio_Line_VM skater_bio_line_VM0= new Skater_Bio_Line_VM(skater_bio_line0);
    Skater_Bio_Line_VM skater_bio_line_VM1= new Skater_Bio_Line_VM(skater_bio_line1);
    Skater_Bio_Line_VM skater_bio_line_VM2= new Skater_Bio_Line_VM(skater_bio_line2);
    Skater_Bio_Line_VM skater_bio_line_VM3= new Skater_Bio_Line_VM(skater_bio_line3);
    Skater_Bio_Line_VM skater_bio_line_VM4= new Skater_Bio_Line_VM(skater_bio_line4);
    Skater_Bio_Line_VM skater_bio_line_VM5= new Skater_Bio_Line_VM(skater_bio_line5);
    Skater_Bio_Line_VM skater_bio_line_VM6= new Skater_Bio_Line_VM(skater_bio_line6);
    Skater_Bio_Line_VM skater_bio_line_VM7= new Skater_Bio_Line_VM(skater_bio_line7);
    Skater_Bio_Line_VM skater_bio_line_VM8= new Skater_Bio_Line_VM(skater_bio_line8);
    Skater_Bio_Line_VM skater_bio_line_VM9= new Skater_Bio_Line_VM(skater_bio_line9);
    Skater_Bio_Line_VM skater_bio_line_VM10= new Skater_Bio_Line_VM(skater_bio_line10);
    Skater_Bio_Line_VM skater_bio_line_VM11= new Skater_Bio_Line_VM(skater_bio_line11);
    Skater_Bio_Line_VM skater_bio_line_VM12= new Skater_Bio_Line_VM(skater_bio_line12);
    Skater_Bio_Line_VM skater_bio_line_VM13= new Skater_Bio_Line_VM(skater_bio_line13);
    Skater_Bio_Line_VM skater_bio_line_VM14= new Skater_Bio_Line_VM(skater_bio_line14);
    skater_bio_lineVMs.add(skater_bio_line_VM0);
    skater_bio_lineVMs.add(skater_bio_line_VM1);
    skater_bio_lineVMs.add(skater_bio_line_VM2);
    skater_bio_lineVMs.add(skater_bio_line_VM3);
    skater_bio_lineVMs.add(skater_bio_line_VM4);
    skater_bio_lineVMs.add(skater_bio_line_VM5);
    skater_bio_lineVMs.add(skater_bio_line_VM6);
    skater_bio_lineVMs.add(skater_bio_line_VM7);
    skater_bio_lineVMs.add(skater_bio_line_VM8);
    skater_bio_lineVMs.add(skater_bio_line_VM9);
    skater_bio_lineVMs.add(skater_bio_line_VM10);
    skater_bio_lineVMs.add(skater_bio_line_VM11);
    skater_bio_lineVMs.add(skater_bio_line_VM12);
    skater_bio_lineVMs.add(skater_bio_line_VM13);
    skater_bio_lineVMs.add(skater_bio_line_VM14);
    Collections.sort(skater_bio_lineVMs);
  }
}
