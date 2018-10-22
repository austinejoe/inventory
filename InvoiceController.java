package numerouno;

import NumeroInclude.NumeroConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Austine joe
 */
public class InvoiceController implements Initializable {

    @FXML
    private AnchorPane salesidpane;

    @FXML
    private TextField txtsalesid;

    @FXML
    private Button btnsearch;

    @FXML
    private Label lblsalesid;

    @FXML
    private Label lblclientname;

    @FXML
    private Label lblclientcontact;

    @FXML
    private Label lblclientemail;

    @FXML
    private Label lblorderdate;

    @FXML
    private TextArea txtclientaddress;

    @FXML
    private TableView<CompanyUser> itemdetailtable;

    @FXML
    private TableColumn<CompanyUser, Integer> tbitemid;

    @FXML
    private TableColumn<CompanyUser, String> tblitemname;

    @FXML
    private TableColumn<CompanyUser, String> tblitemdescription;

    @FXML
    private TableColumn<CompanyUser, Integer> tblquantity;

    @FXML
    private TableColumn<CompanyUser, Double> tblrate;

    @FXML
    private TableColumn<CompanyUser, Double> tblprice;

    @FXML
    private Button btnback;

    @FXML
    private Button btnLOGOUT;

    @FXML
    private Label lbltotalprice;

    @FXML
    private Label title;

    @FXML
    private TextArea txtnumeroaddress;

    @FXML
    private Label lblinvoice;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXButton btnprintinvoice;

    public static int requestedSalesId;
    NumeroConnect connect;
    ResultSet result;
//    Connection conn;
    ArrayList<Integer> salesidList = new ArrayList<>();
    int dbsalesid;

    double totalprice = 0;
    String clientName;
    String clientPhone;
    String clientEmail;
    String clientAddress;
    String itemOrderdate;
    ObservableList<CompanyUser> oblist;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = new NumeroConnect();
        hambugerControl();
        btnsearch.setOnAction(ev -> {
            Pattern pat = Pattern.compile("[0-9\\s]+");
            Matcher mat = pat.matcher(txtsalesid.getText());
            if (!mat.matches() || txtsalesid.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter sales id ");
                return;
            }
            getSearchedSalesId();
        });
        btnprintinvoice.setOnAction(ev -> {
            salesidpane.setVisible(false);
            sum();
            printInvoice();
        });
        btnLOGOUT.setOnAction(ev -> {
            btnLOGOUT.getScene().getWindow().hide();
            newScene("Login.fxml");
        });
        btnback.setOnAction(ev -> {
            btnback.getScene().getWindow().hide();
            newScene("Dashbboard.fxml");
        });
    }

    public int getrequestedSalesId() {
        requestedSalesId = Integer.parseInt(txtsalesid.getText());
        return requestedSalesId;
    }

    public void getSearchedSalesId() {
        oblist = FXCollections.observableArrayList();
        getrequestedSalesId();
        try {
            CompanyUser user = new CompanyUser(requestedSalesId);
            result = connect.searchSalesId(user);
            boolean check = false;

            while (result.next()) {
                check = true;
                clientName = result.getString("clientname");
                clientPhone = result.getString("clientphone");
                clientEmail = result.getString("clientemail");
                clientAddress = result.getString("clientaddress");
                itemOrderdate = result.getDate("orderdate").toString();

                int itemid = result.getInt("itemid");
                String itemname = result.getString("itemname");
                String itemdescription = result.getString("itemdescription");
                int quantity = result.getInt("quantity");
                double rate = result.getDouble("rate");
                double price = result.getDouble("price");
                oblist.add(new CompanyUser(itemid, itemname, itemdescription, quantity, rate, price));
            }
            if (check == false) {

                JOptionPane.showMessageDialog(null, "Incorrect sales ID");

            }
        } catch (SQLException e) {
        }
        lblsalesid.setText(String.valueOf(requestedSalesId));
        lblclientname.setText(clientName);
        lblclientcontact.setText(clientPhone);
        lblclientemail.setText(clientAddress);
        txtclientaddress.setText(clientEmail);
        lblorderdate.setText(itemOrderdate);

        tbitemid.setCellValueFactory(new PropertyValueFactory<>("itemid"));
        tblitemname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        tblitemdescription.setCellValueFactory(new PropertyValueFactory<>("itemdescription"));
        tblquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblrate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        tblprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemdetailtable.setItems(oblist);
    }

    public void sum() {
        for (int i = 0; i < itemdetailtable.getItems().size(); i++) {
            double at = tblprice.getCellData(i);

            totalprice += at;
        }

        lbltotalprice.setText(String.valueOf(totalprice));
    }

    public void printInvoice() {
//        try {
//            String sourceFile = "C:\\Users\\user pc\\Documents\\NetBeansProjects\\NumeroUno\\Numero_Report.jrxml";
//            JasperReport jasper = JasperCompileManager.compileReport(sourceFile);
//            JasperPrint print = JasperFillManager.fillReport(jasper, null, connect.getConnection());
//            JasperViewer.viewReport(print);
//           
//        } catch (JRException ex) {
//            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
