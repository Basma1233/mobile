package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Parent;
import Entities.Student;
import Utils.DataSource;
import Utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aissa
 */
public class ParentService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Parent> parent;
    public ArrayList<Student> F;

    public ParentService() {
        request = DataSource.getInstance().getRequest();
    }
//    btnsupp.addActionListener(e->{
//Parent p = new Parent();
//ParentService.Remove(p);        
//    });

   
    public boolean addParent(Parent parent) {
        String url = Statics.BASE_URL + "/Parent/new?name=" + parent.getName()+ "&login=" +parent.getLogin()  + "&password=" + parent.getPassword();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    public boolean updateParent(Parent parent) {
        String url = Statics.BASE_URL + "/Parent/update/"+ parent.getId()+"?name=" + parent.getName()+ "&login=" +parent.getLogin()  + "&password=" + parent.getPassword();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public ArrayList<Parent> getAllParents() {
        String url = Statics.BASE_URL + "/Parent/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               parent = parseParent(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        System.out.println("Parent"+ parent);
        return parent;
    }
 public ArrayList<Parent> FindParent(Parent p) {
        String url = Statics.BASE_URL + 
                "/Parent/find/" + 
                p.getId() ;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                parent = parseParent(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return parent;
    }
    
   
    public ArrayList<Parent> parseParent(String jsonText) {
        try {
            parent = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String name = obj.get("name").toString();
                String login = obj.get("login").toString();
                String password = obj.get("password").toString();
                parent.add(new Parent(id, name, login, password));

            }

        } catch (IOException ex) {
        }

        return parent;
    }
     public boolean RemoveParent(Parent parent) {
        String url = Statics.BASE_URL + "/Parent/remove/" +parent.getId();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
}
