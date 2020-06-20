
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import Services.ParentService;
import Entities.Parent;
import Entities.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class ParentsListForm extends Form {

    public ParentsListForm(Form previous) {

        super("Parent list", new BorderLayout());
        Container listRec = new Container(BoxLayout.y());
        listRec.setScrollableY(true);
        Button btn = new Button("Annuler");
        ArrayList<Parent> List = new ParentService().getAllParents();
        for (int i = 0; i < List.size(); i++) {
            Button btnR = new Button();
            Button btnM = new Button();
            FontImage.setMaterialIcon(btnM, FontImage.MATERIAL_EDIT);
            FontImage.setMaterialIcon(btnR, FontImage.MATERIAL_DELETE);
            MultiButton mBtn = new MultiButton("Parent n°" + i + ":");
            System.out.println(List.get(i));
            mBtn.setTextLine1(List.get(i).getName());
            mBtn.setTextLine2(List.get(i).getLogin());
            FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_COMMENT);
            Parent p = new Parent(List.get(i).getId());
            btnM.addActionListener(es -> {
                ArrayList<Parent> List5 = new ParentService().FindParent(p);
                AddParentForm h = new AddParentForm(ParentsListForm.this, List5.get(0).getId(), List5.get(0).getName(), List5.get(0).getLogin(), List5.get(0).getPassword());
                h.show();
//                if (new RdvService().annulerRdv(r)) {
//                        Dialog.show("SUCCESS", "Rdv annuler", "OK", null);
//                    } else {
//                        Dialog.show("ERROR", "Server error", "OK", null);
//                    }
//                
//               
            });
            btnR.addActionListener(es -> {
                if (new ParentService().RemoveParent(p)) {
                    Dialog.show("SUCCESS", "Parent removed", "OK", null);
                } else {
                    Dialog.show("ERROR", "Server error", "OK", null);
                }

                btnR.remove();
                mBtn.remove();
                btnM.remove();
                this.refreshTheme();
            }
            //                
            //                
            //               
            );
            listRec.addAll(mBtn, btnR, btnM);

        }
        this.add(CENTER, listRec);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            HomeForm hf = new HomeForm();
            hf.ShowHomeForm("admin");
        });
    }
}
