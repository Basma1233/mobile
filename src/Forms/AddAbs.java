/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Absence;
import Services.ServiceAbs;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author ASUS CORE I5
 */
public class AddAbs extends Form{
    
    public AddAbs(Form previous){
        
        setTitle("Ajouter les absences");
        
        
        
         setTitle("Ajouter les absences");
        setLayout(BoxLayout.y());

        TextField nomp = new TextField(null, "Matieres");
        TextField prenomp = new TextField(null, "Classe");
         //TextField telp = new TextField(null, "Date");
            Picker date = new Picker();
        TextField emailp = new TextField(null, "Heure de debut de ");
        TextField adressep = new TextField(null, "Heure de fin");
         
        
         Button btn = new Button("Ajouter");
         btn.addActionListener((ActionEvent evt) -> {
              if ((nomp.getText().length() == 0) || (prenomp.getText().length() == 0) || (emailp.getText().length() == 0)|| (adressep.getText().length() == 0)
                    ) {
                Dialog.show("Alert", "Remplire les champs", "OK", null);
            }
           
            
                        
               else {
                try {
                    Absence p = new Absence(nomp.getText(),prenomp.getText(),null,emailp.getText(),adressep.getText());
                        
                  
//                  if (isValid(emailp.getText())== true) {
//                            Dialog.show("Ok", "l'email est valide <3", "OK", null);
                    if (ServiceAbs.getInstance().addAbs(p)) {
                        Dialog.show("SUCCESS", "Absence est ajouté", "OK", null);
                   new AfficheAbs(previous).show();
                      nomp.setText(null);
                        prenomp.setText(null);
                        
                         emailp.setText(null);
                         adressep.setText(null);
                          
                        
                    } else {
                        Dialog.show("ERROR", "error", "OK", null);
                        nomp.setText(null);
                        prenomp.setText(null);
                        
                         emailp.setText(null);
                         adressep.setText(null);
                     
                    }  
                    
//                  } else if (isValid(emailp.getText())== false) {
//                            Dialog.show("ERROR", "l'email n'est pas valide", "OK", null);
//                        }
                } catch (Exception e) {
                    Dialog.show("ERROR", "Erreur1", "OK", null);
                }

            
             }
            
           
         });
        
        
         this.addAll(nomp, prenomp,date,emailp,adressep, btn);
         
        

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
}
