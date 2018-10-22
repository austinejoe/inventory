package numerouno;

import NumeroInclude.NumeroConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Austine joe
 */
public class LoginController implements Initializable {

    @FXML
    private Label lbllogin;

    @FXML
    private ComboBox<String> cbLoginAs;

    @FXML
    private TextField txtusername;
    @FXML
    private Button btncancel;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnregister;

    String strusername;
    String strpassword;
    ResultSet result;
    NumeroConnect connect;
     CompanyUser user ;
    ArrayList<String> alist = new ArrayList<>();
    String company;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = new NumeroConnect();       
        //Take me to Register.fxml
        btnregister.setOnAction(ev -> {
            validatehierarchy();
        });
        btnLogin.setOnAction(ev -> {
            selectUser();
        });
        btncancel.setOnAction(ev -> {
            System.exit(0);
        });
    }

    public void selectUser() {
        strusername = txtusername.getText();
        strpassword = txtpassword.getText();
      user = new CompanyUser(strusername, strpassword);
      try {
            result = connect.loginUser(user);

            if (result.next()) {
                btnLogin.getScene().getWindow().hide();
                newscene("Dashboard2.fxml");
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
            }
        } catch (SQLException e) {
            
        }
        
    }

    public void validatehierarchy() {
        try {
            result = connect.hierarchy();
            while (result.next()) {
                company = result.getString("companyname");
            }
            alist.add(company);
            if (alist.contains("Numero")) {
                JOptionPane.showMessageDialog(null, "User can only be created by the CEO");
            } else {
                btnregister.getScene().getWindow().hide();
                newscene("Register.fxml");
            }
        } catch (SQLException e) {
        }
    }
   

    public void newscene(String url) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("image/numero.jpg")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error opening scene" + ex);
        }
    }//end of newscene method

}
