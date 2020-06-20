/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Absence;
import Entities.Prof;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS CORE I5
 */
public class ServiceProf {

    private ConnectionRequest request;
    public static ServiceProf instance = null;

    private boolean responseResult;
    public ArrayList<Prof> profs;

    public static ServiceProf getInstance() {

        if (instance == null) {
            instance = new ServiceProf();
        }
        return instance;
    }

    public ServiceProf() {

        request = DataSource.getInstance().getRequest();
    }

    public boolean addProf(Prof p) {
        String url = Statics.BASE_URL + "/majout?n=" + p.getNom() + "&p=" + p.getPrenom() + "&t=" + p.getTel() + "&e=" + p.getEmail() + "&a=" + p.getAdresse() + "&s=" + p.getSpecialite();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }

    public boolean UpdateAb(Absence p) {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL + "/updateAbs?id=" + p.getId() + "&matiere=" + p.getMatiere() + "&classe=" + p.getClasse() + "&hdep=" + p.getHdep() + "&hfin=" + p.getHfin();
        System.err.println(url);
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            responseResult = request.getResponseCode() == 200; //Code HTTP 20
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //appel asynchrone
        return responseResult;
    }



    public boolean modifier(Prof p) {

        String url = Statics.BASE_URL + "/mupdate/" + p.getId() + "?n=" + p.getNom() + "&p=" + p.getPrenom() + "&t=" + p.getTel() + "&e=" + p.getEmail() + "&a=" + p.getAdresse() + "&s=" + p.getSpecialite();
        //    String url = Statics.BASE_URL +"/posts/update/"+e.getId()+"/"+e.getTitle()+"/"+e.getDescription()+"/"+e.getRating();
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

    public boolean delete(Prof p) {
        String url = Statics.BASE_URL + "/mdelet/" + p.getId();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }

    public ArrayList<Prof> parseTasks(String jsonText) {
        try {
            profs = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {

                Prof p = new Prof();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setNom(obj.get("nom").toString());
                p.setPrenom(obj.get("prenom").toString());
                p.setTel(obj.get("tel").toString());
                p.setEmail(obj.get("email").toString());
                p.setAdresse(obj.get("adresse").toString());
                p.setSpecialite(obj.get("specialite").toString());

                profs.add(p);
            }

        } catch (IOException ex) {

        }

        return profs;
    }

    public ArrayList<Prof> getAllTasks() {
        String url = Statics.BASE_URL + "/mall";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                profs = parseTasks(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return profs;
    }

    public boolean deleteAbs(Absence abs) {

        String url = Statics.BASE_URL + "/msupp?id=" + abs.getId();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;

    }

    public boolean addAbs(Absence p) {
        String url = Statics.BASE_URL + "/majouter?matiere=" + p.getMatiere() + "&classe=" + p.getClasse() + "&hdep=" + p.getHdep() + "&hfin=" + p.getHfin();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }

}
