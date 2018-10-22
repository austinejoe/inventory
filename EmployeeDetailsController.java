package numerouno;

import NumeroInclude.NumeroConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Austine joe
 */
public class EmployeeDetailsController implements Initializable {

    @FXML
    private TextField txtfirstname;

    @FXML
    private TextField txtlastname;

    @FXML
    private TextField txtphonenumber;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtdepartment;

    @FXML
    private TextArea txtaddress;

    @FXML
    private ComboBox<String> cbgender;

    @FXML
    private JFXButton btnsave;

    @FXML
    private JFXButton btnreset;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    NumeroConnect nmc;

    String strfirstname;
    String strlastname;
    String strphonenumber;
    String stremail;
    String strdepartment;
    String straddress;
    String strgender;
    Date employeeapprovaldate;
    CompanyUser user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nmc = new NumeroConnect();
        hambugerControl();
        cbgender.getItems().addAll("Male", "Female");
        btnsave.setOnAction(ev -> {
            getEmployeeApprovalDate();
            Pattern pat = Pattern.compile("^[a-z]+[a-z.0-9-]+@[a-z.-]+(\\.[A-Za-z0-9]+)$");
            Matcher mat = pat.matcher(txtemail.getText().toLowerCase());
            if (!mat.matches() || txtemail.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email.");
                return;
            }
            Pattern name = Pattern.compile("[A-Za-z\\s]+");
            Matcher mName = name.matcher(txtfirstname.getText());
            if (!mName.matches() || txtfirstname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Only alphabets required.");
                return;
            }
            if (!name.matcher(txtlastname.getText()).matches() || txtfirstname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Only alphabets required.");
                return;
            }
            if (!name.matcher(txtdepartment.getText()).matches() || txtdepartment.getText().equals("")) {
                return;
            }
            Pattern num = Pattern.compile("[0-9]+");
            Matcher mnum = num.matcher(txtphonenumber.getText());
            if (!mnum.matches() || txtphonenumber.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid Phone Number.");
                return;
            }
            Pattern address = Pattern.compile("[A-Za-z0-9./\\s]+");
            Matcher maddress = address.matcher(txtaddress.getText());
            if (!maddress.matches() || txtaddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid address.");
                return;
            }
            regEmployee();
            clearInput();
        });

        btnreset.setOnAction(ev -> {
            clearInput();
        });
    }

    public void getEmployeeApprovalDate() {
        java.util.Date date = new java.util.Date();
        employeeapprovaldate = new Date(date.getTime());
    }

    public void regEmployee() {
        strfirstname = txtfirstname.getText();
        strlastname = txtlastname.getText();
        strphonenumber = txtphonenumber.getText();
        stremail = txtemail.getText();
        straddress = txtaddress.getText();
        strdepartment = txtdepartment.getText();
        strgender = cbgender.getSelectionModel().getSelectedItem();

        user = new CompanyUser(strfirstname, strlastname, strphonenumber, stremail, straddress,
                strdepartment, strgender, employeeapprovaldate);

        try {
            nmc.registerEmployee(user);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
    }//end of reEmployee()

    public void newScene(String url) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("image/numero.jpg")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }//end newScene();

    public void clearInput() {
        txtfirstname.setText("");
        txtlastname.setText("");
        txtphonenumber.setText("");
        cbgender.setValue(null);
        txtemail.setText("");
        txtaddress.setText("");
        txtdepartment.setText("");
    }

    public void hambugerControl() {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("HamburgerPane.fxml"));

            drawer.setSidePane(box);
            for (Node node : box.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        switch (node.getAccessibleText()) {
                            case "dashboard": {
                                node.getScene().getWindow().hide();
                                newScene("Dashboard2.fxml");
                            }
                            break;
                            case "logout": {
                                node.getScene().getWindow().hide();
                                newScene("Login.fxml");
                            }
                            break;
                            case "exit":
                                System.exit(0);
                        }
                    });
                }
            }
            HamburgerBackArrowBasicTransition hBack = new HamburgerBackArrowBasicTransition(hamburger);
            hBack.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                hBack.setRate(hBack.getRate() * -1);
                hBack.play();
                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
