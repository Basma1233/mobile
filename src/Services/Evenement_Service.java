/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Utils.DataSource;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Rzouga
 */
public class Evenement_Service {
    
    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Evenement> Evenets;

    public Evenement_Service() {
            request = DataSource.getInstance().getRequest();

    }


    
        public ArrayList<Evenement> getAllEvents() {
        String url = Statics.BASE_URL + "/Events/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Evenets = parseEvenement(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Evenets;
    }
        
          public ArrayList<Evenement> parseEvenement(String jsonText) throws ParseException {
        try {
            Evenets = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("id").toString());
            String titre = obj.get("titre").toString();
            String Description = obj.get("discription").toString();
            String lieu = obj.get("lieu").toString();
           
            int nbrplace = (int)Float.parseFloat(obj.get("nbrplace").toString());
          
            int note = (int)Float.parseFloat(obj.get("rate").toString());
            String dateEvent = obj.get("dateEvent").toString();
         
       

            Date datefin = new Date();
        
    
        Evenement e=new Evenement(id, nbrplace, 0, lieu, titre, "", Description, new Date(), datefin , note);

           e.setDate_event(dateEvent);
                  
                Evenets.add(e);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Evenets;
    }

      public ArrayList<Evenement> parseStat(String jsonText) {
        
        try {
            Evenets = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
          
             
            String titre = obj.get("titre").toString();     
          
      

            int nombre = (int)Float.parseFloat(obj.get("nombre").toString());
            
    
            Evenement e = new Evenement();
            e.setTitre(titre);
            e.setNbreplaces(nombre);
          
            Evenets.add(e);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Evenets;
    }
         public ArrayList<Evenement> getStat() {
        String url = Statics.BASE_URL + "/Evenment/stat";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Evenets = parseStat(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Evenets;
    }
 
     
}
