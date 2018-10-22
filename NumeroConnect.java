package NumeroInclude;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import numerouno.CompanyUser;

/**
 *
 * @author Austine joe
 */
public class NumeroConnect extends NumeroConstant {

    //initializin connection
    Connection connect;
    PreparedStatement statement;
    ResultSet result;
    

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost/inventory";
        String user = "root";
        String pass = "austinejoe";
        try {
            connect = DriverManager.getConnection(url, user, pass);
//           JOptionPane.showMessageDialog(null, "Connection successfull.");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection error." + e);
        }
        return connect;
    }//end of connection

    public void registerEmployee(CompanyUser user) {

        String insert = "insert into " + NumeroConstant.EMPLOYEE_TABLE + "(" + NumeroConstant.EMPLOYEE_FIRSTNAME
                + "," + NumeroConstant.EMPLOYEE_LASTNAME + "," + NumeroConstant.EMPLOYEE_PHONENUMBER + ","
                + NumeroConstant.EMPLOYEE_EMAIL + "," + NumeroConstant.EMPLOYEE_ADDRESS + ","
                + NumeroConstant.EMPLOYEE_DEPARTMENT + "," + NumeroConstant.EMPLOYEE_GENDER + ","
                + NumeroConstant.EMPLOYEE_APPROVALDATE + ") values (?,?,?,?,?,?,?,?)";

        try {
            statement = getConnection().prepareStatement(insert);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getPhonenumber());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getDepartment());
            statement.setString(7, user.getGender());
            statement.setDate(8, user.getEmployeeapprovaldate());

            statement.execute();
            JOptionPane.showMessageDialog(null, "Employee registered successfully.");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "User not created." + e);
        } finally {
            try {

                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
            }
        }

    }

    public void dbInsertItem(CompanyUser user) {
        String insert = "insert into " + NumeroConstant.ITEMDETAILS_TABLE + "(" + NumeroConstant.ITEMDETAILS_NAME + ","
                + NumeroConstant.ITEMDETAILS_DESCRIPTION + "," + NumeroConstant.ITEMDETAILS_RATE
                + "," + NumeroConstant.ITEMDETAILS_QUANTITY + "," + NumeroConstant.ITEMDETAILS_PURCASEDATE
                + ") values (?,?,?,?,?)";
        try {
            statement = getConnection().prepareStatement(insert);

            statement.setString(1, user.getItemname());
            statement.setString(2, user.getItemdescription());
            statement.setDouble(3, user.getRate());
            statement.setInt(4, user.getQuantity());
            statement.setDate(5, user.getPurchasedate());
            statement.execute();
            JOptionPane.showMessageDialog(null, "Items iserted successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Items not inserted " + e);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void registerUser(CompanyUser user) {
        String insert = "insert into " + NumeroConstant.LOGINUSER_TABLE + "(" + NumeroConstant.LOGINUSER_ACCOUNTTYPE
                + "," + NumeroConstant.LOGINUSER_COMPANYNAME + "," + NumeroConstant.LOGINUSER_USERNAME + "," + NumeroConstant.LOGINUSER_PASSWORD + ","
                + NumeroConstant.LOGINUSER_RESPONSIBILITY + ") values (?,?,?,?,?)";

        try {
            statement = getConnection().prepareStatement(insert);

            statement.setString(1, user.getAccounttype());
            statement.setString(2, user.getCompanyname());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getResponsibility());

            statement.execute();
            JOptionPane.showMessageDialog(null, "Data succefully inseerted");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, Data not succefully inseerted" + e);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public ResultSet selectItemid() {
        String itemId = "select " + NumeroConstant.CLIENTITEM_ITEMID + " from " + NumeroConstant.ITEMDETAILS_TABLE;
        try {
            statement = getConnection().prepareStatement(itemId);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SELECT USER ERROR: " + ex);
        }
        return result;
    }//end of selectItemid()

    public ResultSet selectOtherItems(CompanyUser user) {        
        String select = "select " + NumeroConstant.ITEMDETAILS_NAME + "," + NumeroConstant.ITEMDETAILS_DESCRIPTION
                + "," + NumeroConstant.ITEMDETAILS_RATE + "," + NumeroConstant.ITEMDETAILS_QUANTITY + " from "
                + NumeroConstant.ITEMDETAILS_TABLE + " where " + NumeroConstant.CLIENTITEM_ITEMID + " = ?";
        try {
            statement = getConnection().prepareStatement(select);
            statement.setInt(1, user.getSalesid());
            result = statement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SELECT USER ERROR: " + e);
        }
        return result;
    }//end of selectOtherItems() 

    public void insertClientDetail(CompanyUser user) {
        String insert = "insert into " + NumeroConstant.CLIENTDETAIL_TABLE + "(" + NumeroConstant.CLIENTDETAIL_CLIENTNAME
                + "," + NumeroConstant.CLIENTDETAIL_CLIENTPONE + "," + NumeroConstant.CLIENTDETAIL_CLIENTEMAIL
                + "," + NumeroConstant.CLIENTDETAIL_CLIENTADDRESS + "," + NumeroConstant.CLIENTDETAIL_ORDERDATE + ") values(?,?,?,?,?)";

        try {
            statement = getConnection().prepareStatement(insert);
            statement.setString(1, user.getClientname());
            statement.setString(2, user.getClientphone());
            statement.setString(3, user.getClientemail());
            statement.setString(4, user.getClientaddress());
            statement.setDate(5, user.getOrderdate());

            statement.execute();
            JOptionPane.showMessageDialog(null, "Client Details successfully inserted");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Inserting data failed: " + e);
        }
    }//insertClientDetail()

    public ResultSet getSalesid() {
        String salesid = "select " + NumeroConstant.CLIENTITEM_SALESID + " from " + NumeroConstant.CLIENTDETAIL_TABLE 
                +" ORDER BY " + NumeroConstant.CLIENTITEM_SALESID + " DESC LIMIT 1";
        try {
            statement = getConnection().prepareStatement(salesid);
            result = statement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error selecting salesid" + e);
        }
        return result;
    }//end of getSalesid()
    
     public ResultSet getSalesid2(CompanyUser user) {
        String salesid = "select " + NumeroConstant.CLIENTITEM_SALESID + " from " + NumeroConstant.CLIENTDETAIL_TABLE 
                +" WHERE " + NumeroConstant.CLIENTITEM_SALESID + " =?";
        try {
            statement = getConnection().prepareStatement(salesid);
            statement.setInt(1, user.getSalesid());
            result = statement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error selecting salesid" + e);
        }
        return result;
    }//end of getSalesid()

    public void insertClientItem(CompanyUser user) {
        String insertitem = "insert into " + NumeroConstant.CLIENTITEM_TABLE + "(" + NumeroConstant.CLIENTITEM_SALESID
                + "," + NumeroConstant.CLIENTITEM_ITEMID + "," + NumeroConstant.CLIENTITEM_ITEMNAME + ","
                + NumeroConstant.CLIENTITEM_ITEMDESCRIPTION + "," + NumeroConstant.CLIENTITEM_QUANTITY + ","
                + NumeroConstant.CLIENTITEM_RATE + "," + NumeroConstant.CLIENTITEM_PRICE + " , " + NumeroConstant.CLIENTDETAIL_ORDERDATE
                + ") values (?,?,?,?,?,?,?,?)";

        try {
            statement = getConnection().prepareStatement(insertitem);

            statement.setInt(1, user.getSalesid());
            statement.setInt(2, user.getItemid());
            statement.setString(3, user.getItemname());
            statement.setString(4, user.getItemdescription());
            statement.setInt(5, user.getQuantity());
            statement.setDouble(6, user.getRate());
            statement.setDouble(7, user.getPrice());
            statement.setDate(8, user.getOrderdate());
            statement.execute();
            JOptionPane.showMessageDialog(null, "Data inserted succefully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error inserting client item " + e);
            String revoke = "delete from " + NumeroConstant.CLIENTDETAIL_TABLE + " where " + NumeroConstant.CLIENTDETAIL_CLIENTID
                    + " = ?";
            try {
                statement = getConnection().prepareStatement(revoke);
                statement.setInt(1, user.getSalesid());
                statement.execute();
                JOptionPane.showMessageDialog(null, "Data Deleted succefully");
            } catch (SQLException ex) {
            }
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
            }
        }
    }//insertClientItem()

    public ResultSet searchSalesId(CompanyUser user) {
        String search = "select " + "e." + NumeroConstant.CLIENTITEM_SALESID + "," + "e."
                + NumeroConstant.CLIENTDETAIL_CLIENTNAME + "," + "e." + NumeroConstant.CLIENTDETAIL_CLIENTPONE
                + "," + "e." + NumeroConstant.CLIENTDETAIL_CLIENTEMAIL + "," + "e."
                + NumeroConstant.CLIENTDETAIL_CLIENTADDRESS + "," + "e." + NumeroConstant.CLIENTDETAIL_ORDERDATE
                + "," + "ep." + NumeroConstant.CLIENTITEM_ITEMID + "," + "ep." + NumeroConstant.CLIENTITEM_ITEMNAME
                + "," + "ep." + NumeroConstant.CLIENTITEM_ITEMDESCRIPTION + "," + "ep."
                + NumeroConstant.CLIENTITEM_QUANTITY + "," + "ep." + NumeroConstant.CLIENTITEM_RATE + "," + "ep."
                + NumeroConstant.CLIENTITEM_PRICE + " from " + NumeroConstant.CLIENTDETAIL_TABLE + " e" + " join "
                + NumeroConstant.CLIENTITEM_TABLE + " ep on " + "e." + NumeroConstant.CLIENTITEM_SALESID
                + " = ep." + NumeroConstant.CLIENTITEM_SALESID + " where ep." + NumeroConstant.CLIENTITEM_SALESID
                + " = ?";

        try {
            statement = getConnection().prepareStatement(search);
            statement.setInt(1, user.getSalesid());
            result = statement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error selecting salesId " + e);
        }
        return result;
    }

    public ResultSet loginUser(CompanyUser user) {
        String userQuery = "SELECT " + NumeroConstant.LOGINUSER_USERNAME + "," + NumeroConstant.LOGINUSER_PASSWORD
                + " FROM " + NumeroConstant.LOGINUSER_TABLE + " WHERE " + NumeroConstant.LOGINUSER_USERNAME + " = ? AND "
                + NumeroConstant.LOGINUSER_PASSWORD + " = ? ";
        try {

            statement = getConnection().prepareStatement(userQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            result = statement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "LOGIN USER ERROR: " + e);
        }
        return result;
    }

    public ResultSet getClientDetails(CompanyUser user) {
        String clientQuery = "SELECT " + NumeroConstant.CLIENTDETAIL_CLIENTNAME + "," + NumeroConstant.CLIENTDETAIL_CLIENTPONE
                + "," + NumeroConstant.CLIENTDETAIL_CLIENTEMAIL + "," + NumeroConstant.CLIENTDETAIL_CLIENTADDRESS + " from "
                + NumeroConstant.CLIENTDETAIL_TABLE + " where " + NumeroConstant.CLIENTITEM_SALESID + " = ?";
        try {
            statement = getConnection().prepareStatement(clientQuery);
            statement.setInt(1, user.getSalesid());
            result = statement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error getting client details: " + e);
        }
        return result;
    }

    public ResultSet hierarchy() {
        String level = "SELECT " + NumeroConstant.LOGINUSER_COMPANYNAME + " from " + NumeroConstant.LOGINUSER_TABLE;
        try {
            statement = getConnection().prepareStatement(level);
            result = statement.executeQuery();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Company name Not Selected: " + e);
        }
        return result;
    }

    public void invoiceDetail(CompanyUser user) {
        String invoice = "INSERT INTO " + NumeroConstant.INVOICE_TABLE + " (" + NumeroConstant.INVOICE_NUMBER + ", " + NumeroConstant.INVOICE_ISSUEDBY
                + " , " + NumeroConstant.INVOICE_ISSUEDTO + " , " + NumeroConstant.INVOICE_ISSUEDATE + ") VALUES (?,?,?,?)";
        try {
            statement = getConnection().prepareStatement(invoice);
            statement.setString(1, user.getInvoiceNumber());
            statement.setString(2, user.getIssuedBy());
            statement.setString(3, user.getIssuedTo());
            statement.setDate(4, user.getIssueDate());
            statement.execute();
            JOptionPane.showMessageDialog(null, "Invoice Successfully Created: ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Invoice Creation not successfull: " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void updateItemQauntity(CompanyUser user) {
        String update = "UPDATE " + NumeroConstant.ITEMDETAILS_TABLE + " SET " + NumeroConstant.ITEMDETAILS_QUANTITY
                + " = ? WHERE " + NumeroConstant.ITEMDETAILS_ITEMID + " = ?";
        try {
            statement = getConnection().prepareStatement(update);
            statement.setInt(1, user.getQuantity());
            statement.setInt(2, user.getItemid());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Item Quantity Updated Successfully: ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Upadate Not Successful: " + e);
        }
    }//end of updateItemQauntity()

    public ResultSet invoiceNumber() {
        String invoice = "select " + NumeroConstant.INVOICE_NUMBER + " from " + NumeroConstant.INVOICE_TABLE
                + " order by " + NumeroConstant.INVOICE_NUMBER + " desc limit 1";
        try {
            statement = getConnection().prepareStatement(invoice);
            result = statement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Invoice number cannot be selected: " + e);
        }
        return result;
    }//end of invoiceNumber()

    public void DeleteCustomer(CompanyUser user) {
        String customer = "delete from " + NumeroConstant.CLIENTDETAIL_TABLE + " where " + NumeroConstant.CLIENTDETAIL_CLIENTID
                + " =?";
        try {
            statement = getConnection().prepareStatement(customer);
            statement.setInt(1, user.getSalesid());
            statement.execute();
            JOptionPane.showMessageDialog(null, "Customer Details Successfully Deleted ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Action Can not be completed: " + e);
        }
    }

    public void deleteStock(CompanyUser user) {
        String deleteStock = "delete from " + NumeroConstant.ITEMDETAILS_TABLE + " where " + NumeroConstant.ITEMDETAILS_ITEMID
                + " =?";
        try {
            statement = getConnection().prepareStatement(deleteStock);
            statement.setInt(1, user.getSalesid());
            statement.execute();

            JOptionPane.showMessageDialog(null, "Action Completed Successfully ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Action Can not be completed: " + e);
        }
    }

    public ResultSet purchasedItemDetails(CompanyUser user) {

        String select = "select " + NumeroConstant.ITEMDETAILS_NAME + ", " + NumeroConstant.ITEMDETAILS_DESCRIPTION
                + ", " + NumeroConstant.ITEMDETAILS_RATE + " from "
                + NumeroConstant.ITEMDETAILS_TABLE + " where " + NumeroConstant.ITEMDETAILS_ITEMID + " =?";
        try {
            statement = getConnection().prepareStatement(select);
            statement.setInt(1, user.getSalesid());
            result = statement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SELECT USER ERROR: " + e);
        } finally {

        }
        return result;
    }

    public void updateStockName(CompanyUser user) {
        String updatestockname = "UPDATE " + NumeroConstant.ITEMDETAILS_TABLE + " SET " + NumeroConstant.ITEMDETAILS_NAME
                + " =? WHERE " + NumeroConstant.ITEMDETAILS_ITEMID + " =?";
        try {
            statement = getConnection().prepareStatement(updatestockname);
            statement.setString(1, user.getItemname());
            statement.setInt(2, user.getItemid());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Stock Name Update successful: ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An Unknown error Occured; " + e);
        }
    }

    public ResultSet viewAllStock() {
        String querry = "select * from " + NumeroConstant.ITEMDETAILS_TABLE;
        try {
            statement = getConnection().prepareStatement(querry);
            result = statement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An Unknown error Occured; " + e);
        }
        return result;
    }

    public void insertPurchase(CompanyUser user) {
        String insert = "insert into " + NumeroConstant.PURCHASE_TABLE + "(" + NumeroConstant.PURCHASE_STOCKID + ", "
                + NumeroConstant.PURCHASE_STOCKNAME + ", " + NumeroConstant.PURCHASE_DESCRIPTION + ", "
                + NumeroConstant.PURCHASE_RATE + ", " + NumeroConstant.PURCHASE_QUANTITY + ", " + NumeroConstant.PURCHASE_DATE
                + ") values(?,?,?,?,?,?)";
        try {
            statement = getConnection().prepareStatement(insert);
            statement.setInt(1, user.getItemid());
            statement.setString(2, user.getItemname());
            statement.setString(3, user.getItemdescription());
            statement.setDouble(4, user.getRate());
            statement.setInt(5, user.getQuantity());
            statement.setDate(6, user.getPurchasedate());
            statement.execute();
            JOptionPane.showMessageDialog(null, "Operation successful; ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An Unknown error Occured; " + e);
        }
    }
    public ResultSet selectStockQuantity(CompanyUser user){
        String stockQuantity = "select "+ NumeroConstant.ITEMDETAILS_QUANTITY + " from " + NumeroConstant.ITEMDETAILS_TABLE
                + " WHERE " + NumeroConstant.ITEMDETAILS_ITEMID +" =?";
        try {
            statement = getConnection().prepareStatement(stockQuantity);
            statement.setInt(1, user.getSalesid());
            result = statement.executeQuery();
        } catch (SQLException e) {
        }
        return result;
}
    public void updateStockPrice(CompanyUser user){
        String update = "update " + NumeroConstant.ITEMDETAILS_TABLE + " set " + NumeroConstant.ITEMDETAILS_RATE
                + " =? WHERE "+ NumeroConstant.ITEMDETAILS_ITEMID + " =?";
        try {
            statement = getConnection().prepareStatement(update);
            statement.setDouble(1, user.getRate());
            statement.setInt(2, user.getItemid());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Operation successful; ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An Unknown error Occured; " + e);
        }
    }
    public ResultSet viewTodaySale(){
        String sale = "SELECT * FROM " + NumeroConstant.CLIENTITEM_TABLE + " WHERE " + NumeroConstant.CLIENTDETAIL_ORDERDATE
                + " = CURRENT_DATE()";
        try {
            statement = getConnection().prepareStatement(sale);
            result = statement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An Unknown error Occured; " + e);
        }
        return result;
    }
    public ResultSet viewPerMonthSale(CompanyUser user){
        String PerMonthSale = "SELECT * FROM " + NumeroConstant.CLIENTITEM_TABLE + " WHERE MONTH(" +NumeroConstant.CLIENTDETAIL_ORDERDATE
                + ") = ?";
        try {
            statement = getConnection().prepareStatement(PerMonthSale);
            statement.setInt(1, user.getSalesid());
            result = statement.executeQuery();
        } catch (SQLException e) {
        }
        return result;
    }
     public ResultSet viewPerYearSale(CompanyUser user){
        String PerYearSale = "SELECT * FROM " + NumeroConstant.CLIENTITEM_TABLE + " WHERE YEAR(" +NumeroConstant.CLIENTDETAIL_ORDERDATE
                + ") = ?";
        try {
            statement = getConnection().prepareStatement(PerYearSale);
            statement.setInt(1, user.getSalesid());
            result = statement.executeQuery();
        } catch (SQLException e) {
        }
        return result;
    }
    public ResultSet viewPurchaseTransactions(){
        String transaction = "SELECT * FROM " + NumeroConstant.PURCHASE_TABLE;
        try {
            statement = getConnection().prepareStatement(transaction);
            result = statement.executeQuery();
        } catch (SQLException e) {
        }
        return result;
   }
    public ResultSet viewSaleTransaction(){
        String transaction = "SELECT c.clientname, s.itemname, s.quantity, s.rate, s.price, s.orderdate FROM "
                + "clientdetails c JOIN sales s ON c.salesid = s.salesid"
                ;
        try {
            statement = getConnection().prepareStatement(transaction);
            result = statement.executeQuery();
        } catch (SQLException e) {
        }
        return result;
    }
}
