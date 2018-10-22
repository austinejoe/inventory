package numerouno;

import NumeroInclude.NumeroConnect;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author austinejoe
 */
public class DeleteStockController implements Initializable {

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private TextField txtStockid;

    @FXML
    private Button btnDelete;

    NumeroConnect connect;
    CompanyUser user;
    int stockId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = new NumeroConnect();
        hambugerControl();
        btnDelete.setOnAction(event -> {
            Pattern inputPat = Pattern.compile("[0-9]");
            Matcher inputMatch = inputPat.matcher(txtStockid.getText());
            if (!inputMatch.matches() || txtStockid.getText().equals("")) {
                txtStockid.setText("");
                txtStockid.requestFocus();
                return;
            }
            stockId = Integer.parseInt(txtStockid.getText());
            CompleteDelete();
        });
    }

    public void CompleteDelete() {
        user = new CompanyUser(stockId);
        try {
            JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the Item with ID: " + stockId);
            connect.deleteStock(user);
        } catch (HeadlessException e) {
        }

    }
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
}
