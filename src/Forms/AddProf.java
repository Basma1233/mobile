/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Prof;
import Services.ServiceProf;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.florianingerl.util.regex.*;

import java.util.Arrays;

/**
 *
 * @author ASUS CORE I5
 */
public class AddProf extends Form{
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

	// static Pattern object, since pattern is fixed
	private static Pattern pattern;

	// non-static Matcher object because it's created from the input String
	private Matcher matcher;
public boolean isValid(String email) {
    pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
 matcher = pattern.matcher(email);
		  System.out.println("l'email est valide?"+matcher.matches());
        return false;
                  
   }

    public AddProf(Form previous) {
        
        setTitle("Ajouter les enseignants");
        setLayout(BoxLayout.y());

        TextField nomp = new TextField(null, "Nom");
        TextField prenomp = new TextField(null, "Prenom");
         TextField telp = new TextField(null, "Telephone");
        TextField emailp = new TextField(null, "Email");
        TextField adressep = new TextField(null, "Adresse");
         TextField specialitep = new TextField(null, "Specialite");
        
         Button btn = new Button("Ajouter");
         btn.addActionListener((ActionEvent evt) -> {
              if ((nomp.getText().length() == 0) || (prenomp.getText().length() == 0) || (telp.getText().length() == 0) || (emailp.getText().length() == 0)|| (adressep.getText().length() == 0) || (specialitep.getText().length() == 0)
                    
                    ) {
                Dialog.show("Alert", "Remplire les champs", "OK", null);
            }
           
             else if ((telp.getText().length()>8) ||(telp.getText().length()<8)) {
                         Dialog.show("ERROR", "Le numero de telephone doit etre de 8 chiffres", "OK", null);
                    }
                        
               else {
                try {
                    Prof p = new Prof(nomp.getText(),prenomp.getText(),telp.getText(),emailp.getText(),adressep.getText(),specialitep.getText());
                        
                  
             isValid(emailp.getText());
//                          
                    if (ServiceProf.getInstance().addProf(p)) {
                        Dialog.show("SUCCESS", "Enseignant est ajouté", "OK", null);
                   
                      nomp.setText(null);
                        prenomp.setText(null);
                         telp.setText(null);
                         emailp.setText(null);
                         adressep.setText(null);
                          specialitep.setText(null);
                        
                    } else {
                        Dialog.show("ERROR", "error", "OK", null);
                        nomp.setText(null);
                        prenomp.setText(null);
                         telp.setText(null);
                         emailp.setText(null);
                         adressep.setText(null);
                          specialitep.setText(null);
                    }  
                    
//                  } else if (isValid(emailp.getText())== false) {
//                            Dialog.show("ERROR", "l'email n'est pas valide", "OK", null);
//                        }
                } catch (Exception e) {
                    Dialog.show("ERROR", "Erreur1", "OK", null);
                }

            
             }
            
           
         });
        
        
         this.addAll(nomp, prenomp, telp,emailp,adressep,specialitep, btn);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
  }
    
    
}
