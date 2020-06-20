/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Prof;
import Services.ServiceProf;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.util.List;
import com.florianingerl.util.regex.*;

/**
 *
 * @author ASUS CORE I5
 */
public class AfficheProf extends Form{
    
    
    public AfficheProf(Form previous){
        setTitle("Liste des enseignants");
       setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                
         Label titre = new Label("Liste des enseignants","WelcomeBlue");
         setLayout(new FlowLayout(CENTER,TOP));
         
               C1.add(titre);
                
               add(C1);
               
               
               
               
               
               
               
               
               
               
               
               
               ServiceProf taskService = new ServiceProf();
        List<Prof> profs = taskService.getAllTasks();
        if (profs!= null && !profs.isEmpty()) {
            for (Prof prof : profs) {
               int IdE=prof.getId();
                Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
  Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 Container C5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              Container C6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               
                Font font = titre.getUnselectedStyle().getFont();
                Font newFont = Font.createSystemFont(font.getFace(), Font.STYLE_BOLD, font.getSize());
                titre.getUnselectedStyle().setFont(newFont);
                SpanLabel nomclub= new SpanLabel("Nom:  " + prof.getNom());
                SpanLabel cat = new SpanLabel("Prenom:  " + prof.getPrenom());
                SpanLabel branche = new SpanLabel("Telephone:   " + prof.getTel());
                SpanLabel email = new SpanLabel("Email: " + prof.getEmail());
                SpanLabel adresse = new SpanLabel("Adresse: " + prof.getAdresse());
                SpanLabel sp = new SpanLabel("Specialité:   " + prof.getSpecialite());
              
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
                 Dialog.show("Info", "Vous voullez supprimer l'enseignant ?"+prof.getNom()+" "+prof.getPrenom(),"ok","Annuler"); 
                    if( ServiceProf.getInstance().delete(prof)){
                         Dialog.show("Success", "Vous avez supprimer l'enseignant "+prof.getNom()+" "+prof.getPrenom(),"ok",null); 
                        }
                    else{
                           Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                        }
                      new AfficheProf(previous).show();
            });
        
        
         Button modifier = new Button("modifier");
          modifier.addActionListener(a->{
               Form me=new Form("Modifier les informations ",BoxLayout.y());
             TextField mnom = new TextField(prof.getNom(),"nom");
           //     Picker    mdpDateE= new Picker();
                TextField mprenom=new TextField(prof.getPrenom(),"prenom");
                TextField mtel=new TextField(prof.getTel(),"telephone");
               TextField memail=new TextField(prof.getEmail(),"email");
                TextField madresse=new TextField(prof.getAdresse(),"adresse");
                TextField msp=new TextField(prof.getSpecialite(),"speciallité");
               
               Label labelid = new Label (Integer.toString(prof.getId()));
      //  Label nbrating = new Label("Nombre de Rating : "+Integer.toString(club.getEffectif()));
              
               // mcnt.add(nbrating);
                
                
                
                Button btnModifier = new Button("Modifier enseignant");
        
                btnModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                if ((mnom.getText().length()==0)||(mprenom.getText().length()==0) )
                     Dialog.show("Alert", "Please fill all the fields","ok",null);  
               
                else 
                {
        
                   Prof c = new Prof (IdE,mnom.getText(), mprenom.getText(),mtel.getText(),memail.getText(), madresse.getText(),msp.getText());
                    
                    if( ServiceProf.getInstance().modifier(c)){
                        
                        
                         Dialog.show("Success", "Vous avez modifié l'enseignant "+c.getNom()+" "+c.getPrenom(),"ok",null); 
                   
                        }
                    else{
                        Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                 
                        }
                  new AfficheProf(previous).show();

                  }                                                 
                }   
                });
                me.addAll(mnom,mprenom,mtel,memail,madresse,msp,btnModifier);
           me.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
             me.show();        
                
            });
                
                   //  C1.add(titre);
                C2.add(nomclub);
               
                C2.add(cat);
                 C2.add(branche);
                C2.add(email);
                C2.add(adresse);
                C2.add(sp);
                
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