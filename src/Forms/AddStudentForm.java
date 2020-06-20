/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import Services.StudentService;
import Entities.Student;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author aissa
 */
public class AddStudentForm extends Form {

    public AddStudentForm(Form previous,String classe,String adress,String email,String Sex,String name,String login,String password) {
        super("Edit the Student", BoxLayout.y());
        TextField tfId = new TextField(null, "Student Id");
        TextField tfDateOfBirth = new TextField(null, "Student DateOfBirth");
        TextField tfClasse = new TextField(null, "Student Classe");
        TextField tfAdress = new TextField(null, "Student Adress");
        TextField tfEmail = new TextField(null, "Student Email");
        TextField tfSex = new TextField(null, "Student Sex");
        TextField tfName = new TextField(null, "Student Name");
        TextField tflogin = new TextField(null, "Student login");
        TextField tfPassword = new TextField(null, "Student Password");
        tfPassword.setConstraint(TextField.PASSWORD);
        
        
        
//        tfId.setText(tfid);
//        tfDateOfBirth.setText(date);
        tfClasse.setText(classe);
        tfAdress.setText(adress);
        tfEmail.setText(email);
        tfSex.setText(Sex);
        tfName.setText(name);
        tflogin.setText(login);
        tfPassword.setText(password);
        tfPassword.setConstraint(TextField.PASSWORD);
        
        Button btn = new Button("Edit the Student");
       btn.addActionListener((evt) -> {
           
//            if ((tfidRdv.getText().length() == 0) || (tfStatus.getText().length() == 0)) {
//                Dialog.show("Alert", "Please fill all the fields", "OK", null);
//            } else {
                try {
                    Student r = new Student(tfClasse.getText(), tfAdress.getText(),tfEmail.getText(),tfSex.getText(),tfName.getText(),tflogin.getText(),tfPassword.getText());
                    if (new StudentService().addStudent(r)) {
                        Dialog.show("SUCCESS", "Student updated", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }

            
        });

//        this.addAll(tfidRdv, tfStatus, btn);
//
//        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
//            previous.showBack();
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//
//        });
       
//        this.addAll(tfId);
//        this.addAll(tfDateOfBirth);
        this.addAll(tfClasse);
        this.addAll(tfAdress);
        this.addAll(tfEmail);
        this.addAll(tfSex);
        this.addAll(tfName);
        this.addAll(tflogin);
        this.addAll(tfPassword, btn);
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {         
        HomeForm hf = new HomeForm();
        hf.ShowHomeForm("admin");
        });
    }
    public AddStudentForm(Form previous) {
        super("Add a new Student", BoxLayout.y());
        TextField tfId = new TextField(null, "Student Id");
        TextField tfDateOfBirth = new TextField(null, "Student DateOfBirth");
        TextField tfClasse = new TextField(null, "Student Classe");
        TextField tfAdress = new TextField(null, "Student Adress");
        TextField tfEmail = new TextField(null, "Student Email");
        tfEmail.setConstraint(TextField.EMAILADDR);
        TextField tfSex = new TextField(null,"Student Sex");
        TextField tfName = new TextField(null, "Student Name");
        TextField tflogin = new TextField(null, "Student login");
        TextField tfPassword = new TextField(null, "Student Password");
        tfPassword.setConstraint(TextField.PASSWORD);
        
       
        Button btn = new Button("Add the Student");
       btn.addActionListener((evt) -> {
           
//            if ((tfidRdv.getText().length() == 0) || (tfStatus.getText().length() == 0)) {
//                Dialog.show("Alert", "Please fill all the fields", "OK", null);
//            } else {
                try {
                    Student r = new Student(tfClasse.getText(), tfAdress.getText(),tfEmail.getText(),tfSex.getText(),tfName.getText(),tflogin.getText(),tfPassword.getText());
                    if (new StudentService().addStudent(r)) {
                        Dialog.show("SUCCESS", "Student Added", "OK", null);
                        HomeForm hf = new HomeForm();
                        hf.adminInterface();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }

            
        });

//        this.addAll(tfidRdv, tfStatus, btn);
//
//        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
//            previous.showBack();
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//
//        });
       
//        this.addAll(tfId);
//        this.addAll(tfDateOfBirth);
        this.addAll(tfClasse);
        this.addAll(tfAdress);
        this.addAll(tfEmail);
        this.addAll(tfSex);
        this.addAll(tfName);
        this.addAll(tflogin);
        this.addAll(tfPassword, btn);
        
        HomeForm hf = new HomeForm();
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            hf.ShowHomeForm("admin");
            
        });
    }
}
