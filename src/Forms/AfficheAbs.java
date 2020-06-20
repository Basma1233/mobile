/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Absence;
import Services.ServiceAbs;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import java.util.List;

/**
 *
 * @author ASUS CORE I5
 */
public class AfficheAbs extends Form{
    
    public AfficheAbs(Form previous){
        setTitle("Afficher les absences");
        
         setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                
         Label titre = new Label("Liste des absences","WelcomeBlue");
         setLayout(new FlowLayout(CENTER,TOP));
         
               C1.add(titre);
                
               add(C1);
               
               
               
               
               
               
               
               
               
               
               
               
               ServiceAbs taskService = new ServiceAbs();
        List<Absence> abss = taskService.getAllAbs();
        if (abss!= null && !abss.isEmpty()) {
            for (Absence abs : abss) {
               int IdE=abs.getId();
                Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
  Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 Container C5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              Container C6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               
                Font font = titre.getUnselectedStyle().getFont();
                Font newFont = Font.createSystemFont(font.getFace(), Font.STYLE_BOLD, font.getSize());
                titre.getUnselectedStyle().setFont(newFont);
                SpanLabel nomclub= new SpanLabel("Matiere:  " + abs.getMatiere());
                SpanLabel cat = new SpanLabel("Classe:  " + abs.getClasse());
                SpanLabel branche = new SpanLabel("Date:   " + abs.getDate());
                SpanLabel email = new SpanLabel("Heur de debut: " + abs.getHdep());
                SpanLabel adresse = new SpanLabel("Heur de fin: " + abs.getHfin());
               
              
                            //  SpanLabel branche = new SpanLabel("Branche" + stage.getBranche());
              
               // System.out.println("");
                
            ///  Button participer = new Button("Participer");
                 SpanLabel ligne = new SpanLabel("__________________________________________   ", "BlueSeparatorLine" );
               //  participer.addActionListener((evt) -> {
       //   new ParticiperClub(club).show();
           
           
               // Dialog.show("SUCCESS", "vous aver participer au club", "OK", null);
            
       // });*/
                  
                 Button supprimer = new Button("supprimer");
        supprimer.addActionListener(b->{
                 Dialog.show("Info", "Vous voullez supprimer l'absence ?","ok","Annuler"); 
                    if(ServiceAbs.getInstance().deleteAbs(abs)){
                         Dialog.show("Success", "Vous avez supprimer l'absence ","ok",null); 
                        }
                    else{
                           Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                        }
                      new AfficheAbs(previous).show();
            });
        
        
         Button modifier = new Button("modifier");
          modifier.addActionListener((ActionEvent a)->{
               Form me=new Form("Modifier les informations ",BoxLayout.y());
             TextField mnom = new TextField(abs.getMatiere(),"matiere");
           //     Picker    mdpDateE= new Picker();
                TextField mprenom=new TextField(abs.getClasse(),"classe");
                Picker date = new Picker();
         
               TextField memail=new TextField(abs.getHdep(),"heur de debutl");
                TextField madresse=new TextField(abs.getHfin(),"heur de fin");
                
               
               Label labelid = new Label (Integer.toString(abs.getId()));
      //  Label nbrating = new Label("Nombre de Rating : "+Integer.toString(club.getEffectif()));
              
               // mcnt.add(nbrating);
                
                
                
                Button btnModifier = new Button("Modifier Absence");
        
                btnModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                if ((mnom.getText().length()==0)||(mprenom.getText().length()==0) )
                     Dialog.show("Alert", "Please fill all the fields","ok",null);  
               
                else 
                {
                    branche.setText("Date de naissance :" + date.getDate().toString());
                   Absence c = new Absence (IdE,mnom.getText(), mprenom.getText(),memail.getText(), madresse.getText());
                    
                    if(ServiceAbs.getInstance().UpdateAb(c)){
                        
                        
                         Dialog.show("Success", "Vous avez modifié l'abs ","ok",null); 
                   
                        }
                    else{
                        Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                 
                        }
                  new AfficheAbs(previous).show();

                  }                                                 
                }   
                });
                me.addAll(mnom,mprenom,date,memail,madresse,btnModifier);
           me.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
             me.show();        
                
            });
                
                   //  C1.add(titre);
                C2.add(nomclub);
               
                C2.add(cat);
                 C2.add(branche);
                C2.add(email);
                C2.add(adresse);
             
            //    C1.add(branche);
                C6.add(supprimer);
                C6.add(modifier);
               // C6.add(stat);
                C6.add(ligne);
              
                
              //  C5.add(C1);
                C5.add(C2);
                C5.add(C3);
                C5.add(C4);
               C5.add(C6);
                
                add(C5);
           
                 

        
    }
}



        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
    }
}
