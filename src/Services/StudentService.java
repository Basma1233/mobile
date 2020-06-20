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
public class StudentService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Student> student;

    public StudentService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addStudent(Student student) {
        String url = Statics.BASE_URL + "/Student/new?classe=" + student.getClasse()+ "&adress=" +student.getAdress()  + "&mail=" + student.getMail() + "&sex=" + student.getSex() + "&name=" + student.getName()  + "&login=" + student.getLogin() + "&password=" + student.getPassword();

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

    public ArrayList<Student> getAllStudent() {
        String url = Statics.BASE_URL + "/Student/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                student = parseStudent(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        
        return student;
    }

    public ArrayList<Student> FindStudent(Student s) {
        String url = Statics.BASE_URL + 
                "/Student/find/" + 
                s.getId() ;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                student = parseStudent(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return student;
    }
    
     public boolean updateParent(Student student) {
        String url = Statics.BASE_URL + "/Student/update/"+ student.getId()+"?name=" + student.getName()+ "&dateofbirth=" +student.getDateOfBirth()+ student.getClasse()+ "&adress=" +student.getAdress()  + "&mail=" + student.getMail() + "&sex=" + student.getSex() + "&name=" + student.getName()  + "&login=" + student.getLogin() + "&password=" + student.getPassword();

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
    
    public ArrayList<Student> parseStudent(String jsonText) {
        try {
            student = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String DateOfBirth = obj.get("dateofbirth").toString();
                String classe = obj.get("class").toString();
                String adress = obj.get("adress").toString();
                String mail = obj.get("mail").toString();
                String sex = obj.get("sex").toString();
                String name = obj.get("name").toString();
                String login = obj.get("login").toString();
                String password = obj.get("password").toString();
                student.add(new Student(id, DateOfBirth, classe, adress, mail , sex, name, login, password));
            }

        } catch (IOException ex) {
        }
  

        return student;
    }
     public boolean Remove(Student student) {
        String url = Statics.BASE_URL + "/Student/remove/" +student.getId();

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
