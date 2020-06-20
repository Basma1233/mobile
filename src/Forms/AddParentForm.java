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
import Entities.Parent;
import Services.ParentService;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author aissa
 */
public class AddParentForm extends Form {

    public AddParentForm(Form previous) {
        super("Add a new Parent", BoxLayout.y());
        TextField tfId = new TextField(null, "Parent Id");
        TextField tfName = new TextField(null, "Parent name");
        TextField tflogin = new TextField(null, "login");
        TextField tfPassword = new TextField(null, "Password");
        tfPassword.setConstraint(TextField.PASSWORD);
        // Button btn = new Button("Add the Parent");

        Button btn = new Button("Add the Parent");
        btn.addActionListener((evt) -> {

//            if ((tfidRdv.getText().length() == 0) || (tfStatus.getText().length() == 0)) {
//                Dialog.show("Alert", "Please fill all the fields", "OK", null);
//            } else {
            try {
                Parent r = new Parent(tfName.getText(), tflogin.getText(), tfPassword.getText());
                if (new ParentService().addParent(r)) {
                    Dialog.show("SUCCESS", "Parent Added", "OK", null);
                } else {
                    Dialog.show("ERROR", "Server error", "OK", null);
                }
            } catch (NumberFormatException e) {
                Dialog.show("ERROR", "Status must be a number", "OK", null);
            }

            String myURL = "https://rest.nexmo.com/sms/json?api_key=ba0770fd&api_secret=usuJZlpKnD9SEBF7&to=21699883161" + "&from=SKool&text=Parent ajouter";
                ConnectionRequest cntRqst = new ConnectionRequest() {
                    protected void readResponse(InputStream in) throws IOException {
                    }

                    @Override
                    protected void postResponse() {
                        Dialog.show("SMS", "sms successfully sent", "OK", null);

                    }
                };
                cntRqst.setUrl(myURL);
                NetworkManager.getInstance().addToQueue(cntRqst);
        });

//       this.addAll(tfId);
        this.addAll(tfName);
        this.addAll(tflogin);
        this.addAll(tfPassword, btn);

        HomeForm hf = new HomeForm();
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            hf.ShowHomeForm("admin");
        });
    }

    public AddParentForm(Form previous, int id, String name, String login, String password) {
        super("Edit the Parent", BoxLayout.y());
        TextField tfId = new TextField(null, "Parent Id");
        TextField tfName = new TextField(null, "Parent name");
        TextField tflogin = new TextField(null, "login");
        TextField tfPassword = new TextField(null, "Password");
        // Button btn = new Button("Add the Parent");
        tfName.setText(name);
        tflogin.setText(login);
        tfPassword.setText(password);
        Button btn = new Button("Edit the Parent");
        btn.addActionListener((evt) -> {

//            if ((tfidRdv.getText().length() == 0) || (tfStatus.getText().length() == 0)) {
//                Dialog.show("Alert", "Please fill all the fields", "OK", null);
//            } else {
            try {
                Parent r = new Parent(id, tfName.getText(), tflogin.getText(), tfPassword.getText());
                if (new ParentService().updateParent(r)) {
                    Dialog.show("SUCCESS", "Parent updated", "OK", null);
                } else {
                    Dialog.show("ERROR", "Server error", "OK", null);
                }
            } catch (NumberFormatException e) {
                Dialog.show("ERROR", "Status must be a number", "OK", null);
            }

            String myURL = "https://rest.nexmo.com/sms/json?api_key=ba0770fd&api_secret=usuJZlpKnD9SEBF7&to=21699883161" + "&from=SKool&text=Parent ajouter";
                ConnectionRequest cntRqst = new ConnectionRequest() {
                    protected void readResponse(InputStream in) throws IOException {
                    }

                    @Override
                    protected void postResponse() {
                        Dialog.show("SMS", "sms successfully sent", "OK", null);

                    }
                };
                cntRqst.setUrl(myURL);
                NetworkManager.getInstance().addToQueue(cntRqst);
        });

//       this.addAll(tfId);
        this.addAll(tfName);
        this.addAll(tflogin);
        this.addAll(tfPassword, btn);

        HomeForm hf = new HomeForm();
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            hf.ShowHomeForm("admin");
            
        });
    }

}
