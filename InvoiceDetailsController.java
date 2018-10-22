package numerouno;

import NumeroInclude.NumeroConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Austine joe
 */
public class InvoiceDetailsController implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtIssuedBy;

    @FXML
    private TextField txtIssueTo;

    String IssuedBy;
    String IssuedTo;
    Date IssuedDate;
    String invoiceNumber;

    NumeroConnect connect;
    CompanyUser user;
    ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = new NumeroConnect();
        btnSave.setOnAction(ev -> {
            invoiceIssueDate();
            generateInvoiceNumber();
            getInvoiceDetail();
        });
    }

    public void invoiceIssueDate() {
        java.util.Date date = new java.util.Date();
        IssuedDate = new Date(date.getTime());
    }

    public void generateInvoiceNumber() {
        String initial = "NMR/";
        String number;
        try {
            result = connect.invoiceNumber();
            if (result.next()) {
                String invoicenumber = result.getString("invoicenumber");
                number = invoicenumber.substring(4);
                int value = Integer.parseInt(number);
                int trueValue = value + 1;
                invoiceNumber = initial.concat(String.valueOf(trueValue));
            } else {
                invoiceNumber = "NMR/1";
            }
        } catch (SQLException e) {
        }
    }

    public void getInvoiceDetail() {
        IssuedBy = txtIssuedBy.getText();
        IssuedTo = txtIssueTo.getText();
        user = new CompanyUser(invoiceNumber, IssuedBy, IssuedTo, IssuedDate);
        try {
            connect.invoiceDetail(user);

        } catch (Exception e) {
        }
        btnSave.getScene().getWindow().hide();
        newScene("Invoice.fxml");
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
