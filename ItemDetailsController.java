package numerouno;

import NumeroInclude.NumeroConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Austine joe
 */
public class ItemDetailsController implements Initializable {
//
//    Connection connect = null;
//    PreparedStatement statement = null;

    NumeroConnect nmc;

    String stritemname;
    String stritemdescription;
    double stritemrate;
    int stritemquantity;
    Date itempurchasedate;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private GridPane gridepane;

    @FXML
    private JFXTextField txtitemname;

    @FXML
    private JFXTextArea txtitemdescription;

    @FXML
    private JFXTextField txtitemrate;

    @FXML
    private JFXTextField txtitemqauntity;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton btnsave;

    @FXML
    private JFXButton btnreset;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nmc = new NumeroConnect();
        hambugerControl();
        btnsave.setOnAction(ev -> {
            Pattern name = Pattern.compile("[A-Za-z\\s]+");
            Matcher mName = name.matcher(txtitemname.getText());
            if (!mName.matches() || txtitemname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Only alphabets required.");
                txtitemname.setText("");
                txtitemname.requestFocus();
                return;
            }
            Pattern num = Pattern.compile("[0-9]+");
            Matcher mnum = num.matcher(txtitemqauntity.getText());
            if (!mnum.matches() || txtitemqauntity.getText().equals("")) {
                txtitemqauntity.setText("");
                txtitemqauntity.requestFocus();
                return;
            }
            if (!num.matcher(txtitemrate.getText()).matches() || txtitemrate.getText().equals("")) {
                txtitemrate.setText("");
                txtitemrate.requestFocus();
                return;
            }
            Pattern address = Pattern.compile("[A-Za-z0-9/.\\s]+");
            Matcher maddress = address.matcher(txtitemdescription.getText());
            if (!maddress.matches() || txtitemdescription.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Item description field contains invalid text.");
                txtitemdescription.setText("");
                txtitemdescription.requestFocus();
                return;
            } else if (txtitemdescription.getText().length() > 150) {
                JOptionPane.showMessageDialog(null, "Text too long.");
                txtitemdescription.setText("");
                txtitemdescription.requestFocus();
                return;

            }
            getpurchaseDate();
            insertItem();
            clearField();
        });

        btnreset.setOnAction(ev -> {
            clearField();
        });
    }

    public void getpurchaseDate() {
        java.util.Date date = new java.util.Date();
        itempurchasedate = new Date(date.getTime());
    }

    public void insertItem() {
        stritemname = txtitemname.getText();
        stritemdescription = txtitemdescription.getText();
        stritemrate = Double.parseDouble(txtitemrate.getText());
        stritemquantity = Integer.parseInt(txtitemqauntity.getText());
        CompanyUser user = new CompanyUser(stritemname, stritemdescription, stritemrate, stritemquantity, itempurchasedate);
        try {
            nmc.dbInsertItem(user);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error" + e);
        }
    }

    public void clearField() {
        txtitemname.setText("");
        txtitemdescription.setText("");
        txtitemqauntity.setText("");
        txtitemrate.setText("");
    }

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

     public void hambugerControl(){
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
