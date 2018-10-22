package numerouno;

import NumeroInclude.NumeroConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AUSTINS
 */
public class NewCustomer implements Initializable {

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
    private Label lblmessage;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXTextField txtclientname;
    @FXML
    private JFXTextField txtclientphone;
    @FXML
    private JFXTextArea txtclientaddress;
    @FXML
    private JFXTextField txtclientemail;
    @FXML
    private JFXTextField txtquantity;
    @FXML
    private JFXComboBox<Integer> cbitemid;
    @FXML
    private JFXButton btnAdditem;
    @FXML
    private JFXButton btnSave;

    /**
     * Initializes the controller class.
     */
    NumeroConnect connect;
    ResultSet result;
    ArrayList<Integer> comboList;
    int stockId, itemid, dbQuantity;
    int quantity;
    String itemname;
    String itemdescription;
    double price;
    double rate;
    String clientname;
    String clientPhonenumber;
    String clientAddress;
    String clientEmail;
    Date orderdate;
    ObservableList<CompanyUser> oblist;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = new NumeroConnect();
        hambugerControl();
        populateCombo();
        btnAdditem.setOnAction(event -> {
            addItemTotable();
        });
        btnSave.setOnAction(event -> {
            getOrders();
        });
    }

    public void populateCombo() {
        comboList = new ArrayList<>();
        try {
            result = connect.selectItemid();
            while (result.next()) {
                stockId = result.getInt("stockid");
                comboList.add(stockId);
            }
        } catch (SQLException e) {
        }
        cbitemid.getItems().addAll(comboList);
    }//end of populateCombo() method

    public void addItemTotable() { //method to add selected item to table
        quantity = Integer.parseInt(txtquantity.getText());
        stockQuantity();
        if (dbQuantity - quantity < 1) {
            JOptionPane.showMessageDialog(null, "Insufficient Item Quntity.");

        } else {
            oblist = FXCollections.observableArrayList();

            CompanyUser user = new CompanyUser(itemid);
            try {
                result = connect.selectOtherItems(user);
                while (result.next()) {
                    itemname = result.getString("stockname");
                    itemdescription = result.getString("stockdescription");
                    rate = result.getDouble("rate") + (5.0 / 100) * result.getDouble("rate");
                    price = rate * quantity;
                    oblist.add(new CompanyUser(itemid, itemname, itemdescription, quantity, rate, price));
                }
            } catch (SQLException e) {
            }
            tbitemid.setCellValueFactory(new PropertyValueFactory<>("itemid"));
            tblitemname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
            tblitemdescription.setCellValueFactory(new PropertyValueFactory<>("itemdescription"));
            tblrate.setCellValueFactory(new PropertyValueFactory<>("rate"));
            tblquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            tblprice.setCellValueFactory(new PropertyValueFactory<>("price"));
            itemdetailtable.getItems().addAll(oblist);
            updateItemQuantity();
        }
    }//end of addItemTotable()

    public void generateOrderDate() {
        java.util.Date date = new java.util.Date();
        orderdate = new Date(date.getTime());
    }//end of generateOrderDate();

    //method to retrieve client details.
    public void getClientDetails() {
        generateOrderDate();
        clientname = txtclientname.getText();
        clientPhonenumber = txtclientphone.getText();
        clientAddress = txtclientaddress.getText();
        clientEmail = txtclientemail.getText();
        try {
            CompanyUser user = new CompanyUser(clientname, clientname, clientAddress, clientEmail, orderdate);
            connect.insertClientDetail(user);
        } catch (Exception e) {
        }
    }//end of getClientDetails();

    public void clientSalesId() {
        getClientDetails();

        int salesId = 0;
        try {
            result = connect.getSalesid();
            while (result.next()) {
                salesId = result.getInt("salesid");
            }
        } catch (SQLException e) {
        }
        JOptionPane.showMessageDialog(null, "Your Sales Id is: " + salesId);
    }//end o clientSalesId();

    public void stockQuantity() {
        itemid = cbitemid.getSelectionModel().getSelectedItem();

        CompanyUser user = new CompanyUser(itemid);
        try {
            result = connect.selectStockQuantity(user);
            while (result.next()) {
                dbQuantity = result.getInt("quantity");
            }
        } catch (SQLException e) {
        }
    }

    public void updateItemQuantity() {
        int number = quantity;
        int quantity = dbQuantity - number;
        try {
            CompanyUser user = new CompanyUser(itemid, quantity);
            connect.updateItemQauntity(user);
        } catch (Exception e) {
        }
    }//end of updateQuantity()

    public void getOrders() {
        clientSalesId();
        for (CompanyUser item : itemdetailtable.getItems()) {
            int id = tbitemid.getCellData(item);
            String name = tblitemname.getCellData(item);
            String description = tblitemdescription.getCellData(item);
            int Quantity = tblquantity.getCellData(item);
            double rate = tblrate.getCellData(item);
            double price = tblprice.getCellData(item);
            CompanyUser user = new CompanyUser(stockId, id, name, description, Quantity, rate, price, orderdate);
            try {
                connect.insertClientItem(user);
            } catch (Exception e) {
            }
        }
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
