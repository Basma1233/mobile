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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import Entities.Parent;

/**
 *
 * @author Asus
 */
public class ProfileParent {

    Form f;

    public void ShowProfileParent(Parent p) {

        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
        Button EditParent = new Button("Modifier");
        f = new Form("Mon profile", BoxLayout.y());

        Label lblNom = new Label("Nom d'utilisateur (id) : ");
        Label tfName = new Label(p.getName());

        Label lblUsername = new Label("Nom d'utilisateur (username) : ");
        Label tfUsername = new Label(p.getName());

        Label lblLogin = new Label("login d'utilisateur (login) : ");
        TextField tflogin = new TextField(p.getLogin());

        Label lblPassword = new Label("Password d'utilisateur (Password) : ");
        TextField tfPassword = new TextField(p.getPassword());

        if (!tflogin.getText().equals("") || !tfPassword.getText().equals("")) {
            EditParent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://127.0.0.1:8000/Parent/update/" + 32 +"?name="+tfName.getText()+ "&login=" + tflogin.getText() + "&password=" + tfPassword.getText());
                    con.addResponseListener((e) -> {
                        String str = new String(con.getResponseData());
                    });
                    NetworkManager.getInstance().addToQueueAndWait(con);
                    HomeForm hf = new HomeForm();
                    hf.ShowHomeForm("parent");
                }
            });
        }
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.addAll(lblNom, tfName, lblUsername, tfUsername, lblLogin, tflogin, lblPassword, tfPassword, EditParent);
        f.add(c);
        f.show();
        LoginWindow lw = new LoginWindow();
        f.getToolbar().addCommandToLeftBar("< logout", icon, e -> lw.LoginWindow());
    }

}

