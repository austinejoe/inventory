package numerouno;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AUSTINS
 */
public class Dashboard2Controller implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane displaypane;

    @FXML
    private Label lblwelcome;

    @FXML
    private JFXDrawer drawer;

    GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HamburgerBackArrowBasicTransition hBack = new HamburgerBackArrowBasicTransition(hamburger);

        lblwelcome.setText("Welcome to Numero Home Page, Click Menu to Proceed");
        try {
            VBox box = FXMLLoader.load(getClass().getResource("DashboardHamburger.fxml"));

            drawer.setSidePane(box);
            for (Node node : box.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        switch (node.getAccessibleText()) {
                            case "director": {
                                try {
                                    drawer.close();
                                    gridPane = FXMLLoader.load(getClass().getResource("DirectorDashboard.fxml"));
                                    displaypane.getChildren().add(gridPane);
                                    navigate();
                                } catch (IOException ex) {
                                    ex.getMessage();
                                }
                            }
                            break;
                            case "finance": {

                            }
                            break;
                            case "warehouse": {
                                try {
                                    drawer.close();
                                    gridPane = FXMLLoader.load(getClass().getResource("WarehouseDashboard.fxml"));
                                    displaypane.getChildren().add(gridPane);
                                    navigate();
                                } catch (IOException a) {
                                    a.getMessage();
                                }
                            }
                            break;
                            case "sales": {
                                try {
                                    drawer.close();
                                    gridPane = FXMLLoader.load(getClass().getResource("SalesDashboard.fxml"));
                                    displaypane.getChildren().add(gridPane);
                                    navigate();
                                } catch (IOException b) {
                                    b.getMessage();
                                }
                            }
                            break;
                            case "humanResource": {
                                try {
                                    drawer.close();
                                    gridPane = FXMLLoader.load(getClass().getResource("HumanResourceDashboard.fxml"));
                                    displaypane.getChildren().add(gridPane);
                                    navigate();
                                } catch (IOException z) {
                                }
                            }
                            break;
                            case "logout": {
                                node.getScene().getWindow().hide();
                                newScene("Login.fxml");
                            }
                            break;
                            case "exit": {
                                System.exit(0);
                            }
                            break;
                        }
                    });
                }
            }

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

    public void navigate() {
        for (Node display : gridPane.getChildren()) {
            if (display.getAccessibleText() != null) {
                display.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (display.getAccessibleText()) {
                        case "registerUser": {
                            display.getScene().getWindow().hide();
                            newScene("Register.fxml");
                        }
                        break;
                        case "registerEmployee": {
                            display.getScene().getWindow().hide();
                            newScene("EmployeeDetails.fxml");
                        }
                        break;
                        case "logout": {
                            display.getScene().getWindow().hide();
                            newScene("Login.fxml");
                        }
                        break;
                        case "salesOrder": {
                            display.getScene().getWindow().hide();
                            newScene("NewCustomer.fxml");
                        }
                        break;
                        case "searchOrder": {
                            display.getScene().getWindow().hide();
                            newScene("SearchSalesOrder.fxml");
                        }
                        break;
                        case "generateInvoice": {
                            display.getScene().getWindow().hide();
                            newScene("InvoiceDetails.fxml");
                        }
                        break;
                        case "viewSales": {
                            try {
                                GridPane pane = FXMLLoader.load(getClass().getResource("ViewSales.fxml"));
                                displaypane.getChildren().add(pane);
                                for (Node n : pane.getChildren()) {
                                    if (n.getAccessibleText() != null) {
                                        n.addEventHandler(MouseEvent.MOUSE_CLICKED, (ex) -> {
                                            switch (n.getAccessibleText()) {
                                                case "viewSalesByMonth": {
                                                    n.getScene().getWindow().hide();
                                                    newScene("ViewSalesByMonth.fxml");
                                                }
                                                break;
                                                case "viewTodaySales": {
                                                    n.getScene().getWindow().hide();
                                                    newScene("ViewTodaySales.fxml");
                                                }
                                                break;
                                                case "viewSalesByYear": {
                                                    n.getScene().getWindow().hide();
                                                    newScene("ViewSalesByYear.fxml");
                                                }
                                                break;
                                            }
                                        });
                                    }
                                }
                            } catch (IOException ep) {
                            }
                        }
                        break;
                        case "transaction": {
                            try {
                                GridPane pane = FXMLLoader.load(getClass().getResource("Transactions.fxml"));
                                displaypane.getChildren().add(pane);
                                for (Node n : pane.getChildren()) {
                                    if (n.getAccessibleText() != null) {
                                        n.addEventHandler(MouseEvent.MOUSE_CLICKED, (ex) -> {
                                            switch (n.getAccessibleText()) {
                                                case "purchase": {
                                                    n.getScene().getWindow().hide();
                                                    newScene("PurchaseTransaction.fxml");
                                                }
                                                break;
                                                case "sales": {
                                                    n.getScene().getWindow().hide();
                                                    newScene("SalesTransacion.fxml");
                                                }
                                                break;
                                            }
                                        });
                                    }
                                }
                            } catch (IOException ep) {
                            }
                        }
                        break;
                        case "viewStock": {
                            display.getScene().getWindow().hide();
                            newScene("ViewStock.fxml");
                        }
                        break;
                        case "newStock": {
                            display.getScene().getWindow().hide();
                            newScene("ItemDetails.fxml");
                        }
                        break;
                        case "existingStock": {
                            display.getScene().getWindow().hide();
                            newScene("Purchases.fxml");
                        }
                        break;
                        case "deleteStock": {
                            display.getScene().getWindow().hide();
                            newScene("DeleteStock.fxml");
                        }
                        break;
                        case "updateStock": {
                            try {
                                GridPane pane = FXMLLoader.load(getClass().getResource("UpdateStock.fxml"));
                                displaypane.getChildren().add(pane);
                                for (Node n : pane.getChildren()) {
                                    if (n.getAccessibleText() != null) {
                                        n.addEventHandler(MouseEvent.MOUSE_CLICKED, (ex) -> {
                                            switch (n.getAccessibleText()) {
                                                case "updateStockName": {
                                                    n.getScene().getWindow().hide();
                                                    newScene("UpdateStockName.fxml");
                                                }
                                                break;
                                                case "updateStockPrice": {
                                                    n.getScene().getWindow().hide();
                                                    newScene("UpdateStockPrice.fxml");
                                                }
                                                break;
                                            }
                                        });
                                    }
                                }
                            } catch (IOException ep) {
                            }
                        }
                        break;
                        case "deleteCustomer": {
                            display.getScene().getWindow().hide();
                            newScene("DeleteCustomer.fxml");
                        }
                        break;
                    }
                });
            }
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
