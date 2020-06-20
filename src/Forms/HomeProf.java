/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ASUS CORE I5
 */
public class HomeProf extends Form{
    Form current;
    public HomeProf(Form previous){
        
        setTitle("Ajouter les absences");
         HomeForm hf = new HomeForm();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> hf.adminInterface());
         
   
        current=this;
        setTitle("Enseignant");
        setLayout( BoxLayout.y());
        
        Button btnAddProf = new Button("Ajouter un enseignant");
         
        Button btnProfList = new Button("Lister les enseignants");
        ;
      
        btnAddProf.addActionListener(e->new AddProf(current).show());
       
        btnProfList.addActionListener(e->new AfficheProf(current).show());
        
        
        this.addAll(new Label(""), btnAddProf,  btnProfList); 

    }
}
