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
public class HomeAbs extends Form{
    Form current;
    public HomeAbs(Form previous){
                setTitle("Ajouter les absences");
        HomeForm hf = new HomeForm();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> hf.adminInterface());
        
        
        
        
        current=this;
        setTitle("Absence");
        setLayout( BoxLayout.y());
        
       
         Button btnAddAbs = new Button("Ajouter une absence");
        
        Button btnAbsList = new Button("Lister les absences");
      
        btnAddAbs.addActionListener(e->new AddAbs(current).show());
       
        btnAbsList.addActionListener(e->new AfficheAbs(current).show());
        
        this.addAll(new Label(""),  btnAddAbs, btnAbsList);

    }
}
