/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import Entities.Parent;
import Entities.Student;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aissa
 */
public class HomeForm extends Form {

    Form current;

    public void ShowHomeForm(String value) {
        //super("Home", BoxLayout.y());
        Student p = new Student();

        if (value.toLowerCase().equals("admin")) {
            adminInterface();
        }
        if (value.toLowerCase().equals("student")) {
            returnStudents(35);
        }
        if (value.toLowerCase().equals("parent")) {
            returnParent(32);
        }

        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
        this.getToolbar().addCommandToLeftBar("> Logout", icon, evt -> new LoginWindow().getF().show());
    }

    public void returnStudents(int id) {
        Student p = new Student();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEVFINAL/web/app_dev.php/Student/find/" + id);
        con.addResponseListener(a -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> data = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println("data " + data.get("root"));
                List<Map<String, Object>> list = (List<Map<String, Object>>) data.get("root");
                for (Map<String, Object> obj : list) {

                    float idS = Float.parseFloat(obj.get("id").toString());
                    p.setId((int) id);
                    p.setName(obj.get("name").toString());
                    p.setMail(obj.get("mail").toString());
                    p.setDateOfBirth(obj.get("dateofbirth").toString());
                    p.setClass(obj.get("class").toString());
                    p.setSex(obj.get("sex").toString());
                    p.setLogin(obj.get("login").toString());
                    p.setPassword(obj.get("password").toString());
                    p.setAdress(obj.get("adress").toString());
                    break;
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        ProfileStudent ps = new ProfileStudent();
        ps.ShowProfileStudent(p);

    }

    public void returnParent(int id) {
        Parent p = new Parent();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEVFINAL/web/app_dev.php/Parent/find/" + id);
        con.addResponseListener(a -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> data = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println("data " + data.get("root"));
                List<Map<String, Object>> list = (List<Map<String, Object>>) data.get("root");
                for (Map<String, Object> obj : list) {

                    float idP = Float.parseFloat(obj.get("id").toString());
                    p.setId((int) idP);
                    p.setName(obj.get("name").toString());
                    p.setLogin(obj.get("login").toString());
                    p.setPassword(obj.get("password").toString());
                    break;
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        ProfileParent ps = new ProfileParent();
        ps.ShowProfileParent(p);

    }

    public void adminInterface() {
        Form f = new Form("DASHBOARD");
        f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Button btnAddStudent = new Button("Add Student");
        Button btnStudentList = new Button("Student List");

        Button btnAddParent = new Button("Add Parent");
        Button btnParentList = new Button("Parent List");

        Button btnAddProf = new Button("Enseignant");
        Button btnAddAbs = new Button("Absence");

        btnAddStudent.addActionListener((ActionListener) (ActionEvent evt) -> {
            new AddStudentForm(HomeForm.this).show();
        });
        /*
            new EditStudent(HomeForm.this).getF().show();

        });*/

        btnStudentList.addActionListener((ActionListener) (ActionEvent evt) -> {

//            StudentsListForm st = new StudentsListForm(HomeForm.this);
//            st.getForm().show();
            new StudentsListForm(HomeForm.this).show();
        });
        btnAddParent.addActionListener((ActionListener) (ActionEvent evt) -> {
            new AddParentForm(HomeForm.this).show();
        });
        /* btnEditsParent.addActionListener((ActionListener) (ActionEvent evt) -> {

            new EditParent(HomeForm.this).getF().show();

        });*/
        btnParentList.addActionListener((ActionListener) (ActionEvent evt) -> {
            new ParentsListForm(HomeForm.this).show();
//            ParentsListForm str = new ParentsListForm(HomeForm.this);
//            str.getForm().show();
        });
        
        btnAddProf.addActionListener((ActionListener) (ActionEvent evt) -> {
             new HomeProf(current).show();
        });

        //btnAddProf.addActionListener(e -> new HomeProf(current).show());
        btnAddAbs.addActionListener(e -> new HomeAbs(current).show());
        f.addAll(new Label("Student :"), btnAddStudent, btnStudentList);
        f.addAll(new Label("Parent :"), btnAddParent, btnParentList);
      
        
        f.addAll(new Label("Prof"), btnAddProf, btnAddAbs);
        f.show();
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
        LoginWindow lw = new LoginWindow();
        f.getToolbar().addCommandToLeftBar("< logout", icon, e -> lw.LoginWindow());
    }

}
