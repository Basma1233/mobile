/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import Services.ParentService;
import Services.StudentService;
import Entities.Parent;
import Entities.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aissa
 */
public class StudentsListForm extends Form {

    public StudentsListForm(Form previous) {

        super("Student list", new BorderLayout());
        Container listRec = new Container(BoxLayout.y());
        listRec.setScrollableY(true);
        Button btn = new Button("Annuler");
        ArrayList<Student> List = new StudentService().getAllStudent();
        for (int i = 0; i < List.size(); i++) {
//            System.out.println(List.get(i));
            Button btnM = new Button();
            Button btnR = new Button();
            FontImage.setMaterialIcon(btnM, FontImage.MATERIAL_EDIT);
            FontImage.setMaterialIcon(btnR, FontImage.MATERIAL_DELETE);
//            this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
//        this.setBgImage(UIManager.getImage("Affiche_final.png"));


            MultiButton mBtn = new MultiButton("Student n°" + i + ":");
            mBtn.setTextLine1(List.get(i).getName());
            mBtn.setTextLine2(List.get(i).getClasse());
            FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_COMMENT);
            System.out.println(List.get(i).getId());
            Student s = new Student(List.get(i).getId());
            btnM.addActionListener(es -> {
                ArrayList<Student> List5 = new StudentService().FindStudent(s);
                AddStudentForm h = new AddStudentForm(StudentsListForm.this, List5.get(0).getClasse(), List5.get(0).getAdress(), List5.get(0).getMail(), List5.get(0).getSex(), List5.get(0).getName(), List5.get(0).getLogin(), List5.get(0).getPassword());
                h.show();
//                if (new RdvService().annulerRdv(r)) {
//                        Dialog.show("SUCCESS", "Rdv annuler", "OK", null);
//                    } else {
//                        Dialog.show("ERROR", "Server error", "OK", null);
//                    }
            });
            btnR.addActionListener(es -> {
                if (new StudentService().Remove(s)) {
                    Dialog.show("SUCCESS", "Student removed", "OK", null);
                } else {
                    Dialog.show("ERROR", "Server error", "OK", null);
                }

                btnR.remove();
                mBtn.remove();
                btnM.remove();
                this.refreshTheme();
            }
            );
            listRec.addAll(mBtn, btnR, btnM);

        }
        this.add(CENTER, listRec);

//        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
//            previous.showBack();
             HomeForm hf = new HomeForm();
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            hf.ShowHomeForm("admin");
        });
    }
}
