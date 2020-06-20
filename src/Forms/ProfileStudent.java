/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import Entities.Student;

/**
 *
 * @author Asus
 */
public class ProfileStudent {

    Button EditStudent = new Button("Modifier");
    Button btModifierMotDePasse = new Button();
    Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
    FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
    Form f = new Form("Mon profile");

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public void ShowProfileStudent(Student S) {

        Label lblDOB = new Label("DateOfBirth d'utilisateur (id) : ");
        Label tf = new Label("16/03/1995");
       

        Label lblClass = new Label("classe d'utilisateur (id) : ");
        Label t = new Label(S.getClasse());

        Label lblAdress = new Label("Adress d'utilisateur (id) : ");
        Label tfAdress = new Label(S.getAdress());

        Label lblEmail = new Label("Email d'utilisateur (id) : ");
        Label tfEmail = new Label(S.getMail());

        Label lblSex = new Label("Sex d'utilisateur (id) : ");
        Label tfSex = new Label(S.getSex());

        Label lblNom = new Label("Nom d'utilisateur (username) : ");
        Label tfName = new Label(S.getLogin());

        Label lblLogin = new Label("login d'utilisateur (login) : ");
        TextField tflogin = new TextField(S.getLogin());

        Label lblPassword = new Label("Password d'utilisateur (Password) : ");
        TextField tfPassword = new TextField(S.getPassword());
        if(!tflogin.getText().equals("") || !tfPassword.getText().equals("") )
        EditStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://127.0.0.1:8000/student/modifier?id=" + 35 + "&login=" + tflogin.getText() + "&password=" + tfPassword.getText());
                con.addResponseListener((e) -> {
                    String str = new String(con.getResponseData());
                });
                NetworkManager.getInstance().addToQueueAndWait(con);
                HomeForm hf = new HomeForm();
                hf.ShowHomeForm("student");
            }
        });
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.addAll(lblNom, tfName, lblLogin, tflogin, lblEmail, tfEmail, lblPassword, tfPassword, lblDOB, tf, lblClass, t, lblSex, tfSex, EditStudent);
        f.add(c);
        f.show();
        LoginWindow lw = new LoginWindow();
         f.getToolbar().addCommandToLeftBar("< Retour", icon, e -> lw.LoginWindow());
    }

}
