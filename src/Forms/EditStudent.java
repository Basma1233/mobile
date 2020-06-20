/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

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
import Services.UserService;
import Entities.Student;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Asus
 */
public class EditStudent {

    Form f;

    public EditStudent(Form precedent) {
        f = new Form();
         List<Student> myList = new ArrayList();
         Student s = new Student(2, "12/03/2025", "3a0", "blabag", "aa", "aa", "aa", "ahla", "sword");
         Student s2 = new Student(4, "12/03/2025", "3a15", "blabag", "aa", "aa", "aa", "ahla", "sword");
       
        myList.add(s);
        myList.add(s2);
        for (Student p : myList) {

            Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Button Edit = new Button("Edit");
            Edit.addActionListener(e -> {
                EditForm(p, f);
                    });

            Label ID = new Label();
            Label nomEtudiant = new Label();
            Label Classe = new Label();
            nomEtudiant.setText(p.getName());
            Classe.setText(p.getClasse());
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
    
    public void EditForm(Student s, Form precedent) {
            f = new Form("Modifier mon profile", BoxLayout.y());
        Toolbar tb = f.getToolbar();
        Label lbl1 = new Label("id d'utilisateur (id) : ");
        TextField tfId = new TextField(s.getId());
        tfId.setText(String.valueOf(s.getId()));
        Label lbl2 = new Label("DateOfBirth : ");
        TextField tfAdress = new TextField(s.getAdress());
        tfAdress.setText(s.getAdress());
        Label lbl3 = new Label("Classe : ");
        TextField tfClasse = new TextField(s.getClasse());
        Label lbl4 = new Label("Adress : ");
        TextField tfDateOfBirth = new TextField(s.getDateOfBirth());
        Label lbl5 = new Label("Email: ");
        TextField tfEmail = new TextField(s.getMail());
        Label lbl6 = new Label("Sex : ");
        TextField tfSex = new TextField(s.getSex());
        Label lbl7 = new Label("Name : ");
        TextField tfName = new TextField(s.getName());
        Label lbl8 = new Label("Login : ");
        TextField tfLogin = new TextField(s.getLogin());
        Label lbl9 = new Label("Name : ");
        TextField tfPassword = new TextField(s.getPassword());

        Button btModifier = new Button("Modifier");

        f.add(lbl1);
        f.add(tfId);
        f.add(lbl2);
        f.add(tfDateOfBirth);
        f.add(lbl3);
        f.add(tfClasse);
        f.add(lbl4);
        f.add(tfAdress);
        f.add(lbl5);
        f.add(tfEmail);
        f.add(lbl6);
        f.add(tfSex);
        f.add(lbl7);
        f.add(tfName);
        f.add(lbl8);
        f.add(tfLogin);
        f.add(lbl9);
        f.add(tfPassword);

        f.add(btModifier);

        btModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean yes = Dialog.show("Alerte", "Voulez-vous vraiment enregistrer les modifications ?", "oui", "non");
                if (yes) {
                    UserService su = new UserService();
                    s.setId(0);
                    s.setDateOfBirth(tfDateOfBirth.getText());
                    s.setClass(tfClasse.getText());
                    s.setAdress(tfAdress.getText());
                    s.setMail(tfEmail.getText());
                    s.setSex(tfSex.getText());
                    s.setName(tfName.getText());
                    s.setLogin(tfLogin.getText());
                    s.setPassword(tfPassword.getText());
                   // ProfileStudent p = new ProfileStudent(s, f);
                    Button btn = new Button("Edit the Student");
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
        
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
 
    }

}
