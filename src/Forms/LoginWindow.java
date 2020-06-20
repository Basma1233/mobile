/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author Sami
 */
public class LoginWindow {

    Form f;
    Label username;
    Label password;
    TextField tfLogin;
    TextField tfPassword;
    Button login;
    Button reset;

    private EncodedImage ei;
    public static int iduser;

    public void LoginWindow() {
        try {
            ei = EncodedImage.create("/bg_3.jpg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        f = new Form("S'autehntifier", new BoxLayout(BoxLayout.Y_AXIS));
        username = new Label("Username");
        tfLogin = new TextField(null, "Username", 10, 0);
        password = new Label("Password");
        tfPassword = new TextField(null, "Password", 10, 0);

        tfPassword.setConstraint(TextField.PASSWORD);

        login = new Button("Login");
        reset = new Button("Annuler");

        Container L1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container L11 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        L11.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        Container L2 = new Container(new BoxLayout(BoxLayout.X_AXIS)); 
        Container L12 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        L12.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        Container L3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        Container L13 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        L13.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        Container L5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container L4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        L1.add(username);
        L1.add(tfLogin);
        L2.add(password);
        L2.add(tfPassword);
        L3.add(login);
        L3.add(reset);

        login.addActionListener((e) -> {
            if ("student".equals(tfLogin.getText().toLowerCase()) && "student".equals(tfPassword.getText().toLowerCase()) 
                 || "parent".equals(tfLogin.getText().toLowerCase()) && "parent".equals(tfPassword.getText().toLowerCase()) 
                 || "admin".equals(tfLogin.getText().toLowerCase()) && "admin".equals(tfPassword.getText().toLowerCase())) {
                HomeForm hf = new HomeForm();
                hf.ShowHomeForm(tfLogin.getText());
            } 
            else if ( "client".equals(tfLogin.getText().toLowerCase()) && "client".equals(tfPassword.getText().toLowerCase()))
            {
                 new  UserInterfaceForm(f).show();
            }
            
            else {
               
                Dialog.show("Login", "Username/password inccorect", "OK", "Cancel");
            }

        });

        reset.addActionListener((e) -> {
            tfLogin.setText("");
            tfPassword.setText("");

        });
        Image bgImage = URLImage.createToStorage(ei, "bg_3.jpg",
                "http://localhost/PIDEVFINAL/web/images/bg_3.jpg", URLImage.RESIZE_SCALE_TO_FILL);
        L4.setPreferredSize(new Dimension(L4.getWidth(), 800));
        // bgImage.scale(200, 200);
        ImageViewer iv = new ImageViewer(bgImage);
        L4.add(iv);

      //  f.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
      L11.addComponent(BorderLayout.CENTER,L1);
      L12.addComponent(BorderLayout.CENTER,L2);
      L13.addComponent(BorderLayout.CENTER,L3);
        L5.addAll(L4, L11, L12, L13);
        
        f.add(L5);
       // f.addComponent(BorderLayout.CENTER, L5);
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Label getUsername() {
        return username;
    }

    public void setUsername(Label username) {
        this.username = username;
    }

    public Label getPassword() {
        return password;
    }

    public void setPassword(Label password) {
        this.password = password;
    }

    public TextField getTfLogin() {
        return tfLogin;
    }

    public void setTfLogin(TextField tfLogin) {
        this.tfLogin = tfLogin;
    }

    public TextField getTfPassword() {
        return tfPassword;
    }

    public void setTfPassword(TextField tfPassword) {
        this.tfPassword = tfPassword;
    }

    public Button getLogin() {
        return login;
    }

    public void setLogin(Button login) {
        this.login = login;
    }

    public static int getIduser() {
        return iduser;
    }

    public static void setIduser(int iduser) {
        LoginWindow.iduser = iduser;
    }
    

}
