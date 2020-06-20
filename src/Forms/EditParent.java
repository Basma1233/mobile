/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Services.ParentService;
import Entities.Parent;
import Entities.User;
import Services.UserService;
import Entities.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class EditParent {

    Form f;

    public EditParent(Form precedent) {
        f = new Form();
        List<Parent> myList = new ArrayList();
//        Parent pa = new Parent(2, "12/03/2025", "3a0", "blabag", "aa", "aa", "aa", "ahla", "sword");
//        Parent pa2 = new Parent(4, "12/03/2025", "3a15", "blabag", "aa", "aa", "aa", "ahla", "sword");

//        myList.add(pa);
//        myList.add(pa2);
        for (Parent p : myList) {

            Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Button Edit = new Button("Edit");
            Edit.addActionListener(e -> {
                EditForm(p, f);
            });

            Label ID = new Label();
            Label nomEtudiant = new Label();
            Label Classe = new Label();
            //nomEtudiant.setText(p.getClass());
            Classe.setText(p.getName());
            Classe.setText(p.getLogin());
            Classe.setText(p.getPassword());
            c1.add(nomEtudiant);
            ID.setText(String.valueOf(p.getId()));

            c2.add(Classe);
            c2.add(ID);

            c1.add(c2);
            c1.add(Edit);
            c3.add(c1);
            f.getToolbar().addCommandToLeftBar("< Retour", null, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    HomeForm hf = new HomeForm();
                    hf.ShowHomeForm("admin");
                }

            });
            f.add(c3);

        }
    }

    public void EditForm(Parent p, Form precedent) {

        f = new Form("Modifier mon profile", BoxLayout.y());
        Toolbar tb = f.getToolbar();
        Label lbl1 = new Label("Id d'utilisateur (Id) : ");
        TextField tfId = new TextField(p.getId());
        Label lbl2 = new Label("Name d'utilisateur (Name) : ");
        TextField tfName = new TextField(p.getName());
        Label lbl3 = new Label("login : ");
        TextField tfLogin = new TextField(p.getLogin());
        Label lbl4 = new Label("Password de téléphone : ");
        TextField tfPassword = new TextField(p.getPassword() + "");

        Button btModifier = new Button("Modifier");

        f.add(lbl1);
        f.add(tfId);
        f.add(lbl2);
        f.add(tfName);
        f.add(lbl3);
        f.add(tfLogin);
        f.add(lbl4);
        f.add(tfPassword);
        f.add(btModifier);

        btModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean yes = Dialog.show("Alerte", "Voulez-vous vraiment enregistrer les modifications ?", "oui", "non");
                if (yes) {
                    UserService u = new UserService();
                    //   u.(p.getId(), tfId.getText(), tfName.getText(), tfLogin.getText(), tfPassword.getText());
                    p.setId(0);
                    p.setName(tfName.getText());
                    p.setLogin(tfLogin.getText());
                    p.setPassword(toString());

                    // ProfileParent p = new ProfileParent(s, f);
                    //  p.getf().show();
                }
            }
        });

        tb.addCommandToLeftBar("< Retour", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            HomeForm hf = new HomeForm();
        hf.ShowHomeForm("admin"); 
            }

        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
