/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Absence;
import Utils.DataSource;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS CORE I5
 */
public class ServiceAbs {
   
    
     private ConnectionRequest request;
     public static ServiceProf instance=null;
     
      private boolean responseResult;
    public ArrayList<Absence> abs;
    
    
    
     public static ServiceProf getInstance() {
        
        if (instance == null) {
            instance = new ServiceProf();
        }
        return instance;
    }
    public ServiceAbs() {
        
        request = DataSource.getInstance().getRequest();
    }
    
    
    
//     public boolean addAbs(Absence p) {
//        String url = Statics.BASE_URL + "/majouter?n=" + p.getMatiere()+ "&p=" + p.getClasse()+"&=" + p.getDate()+ "&t=" + p.getHdep()+ "&e=" + p.getHfin();
//
//        
//        request.setUrl(url);
//        request.setPost(false);
//        request.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                responseResult = request.getResponseCode() == 200;
//                request.removeResponseListener(this);
//                
//            }
//        });
//        
//      NetworkManager.getInstance().addToQueueAndWait(request);
//        return responseResult;  
//    }
     
     
     
     
     public boolean modifierAbs(Absence p) {
         
       String url = Statics.BASE_URL+"/mmodifier/"+p.getId()+"?n=" + p.getMatiere()+ "&p=" + p.getClasse()+"&=" + p.getDate()+ "&t=" + p.getHdep()+ "&e=" + p.getHfin();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }
     
//       public boolean deleteAbs(Absence p) {
//        String url = Statics.BASE_URL + "/msupp/"+p.getId();
//        request.setUrl(url);
//        request.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                responseResult = request.getResponseCode() == 200; 
//                request.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(request);
//        return responseResult;
//    }
  
        public ArrayList<Absence> parseTasks(String jsonText){
        try {
            abs=new ArrayList<>();
            JSONParser j = new JSONParser();
          
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){

                Absence p = new Absence();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                p.setMatiere(obj.get("matiere").toString());
                p.setClasse(obj.get("classe").toString());
               //p.setDate((Date) (obj.get("date")));
                p.setHdep(obj.get("hdep").toString());
                p.setHfin(obj.get("hfin").toString());
                

                abs.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
         
    
        return abs;
    }
    
    public ArrayList<Absence> getAllAbs(){
        String url = Statics.BASE_URL+"/mallAbs";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                abs = parseTasks(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return abs;
    }
       
    
    
    
    
}
