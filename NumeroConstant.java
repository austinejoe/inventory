package NumeroInclude;

/**
 *
 * @author Austine joe
 */
public class NumeroConstant {

    protected String server = "localhost";
    protected String user = "root";
    protected String pass = "";
    protected String db = "inventory";
    //initialize constants
    //Employee table begin
    public static final String EMPLOYEE_TABLE = "employee";
    public static final String EMPLOYEE_FIRSTNAME = "firstname";
    public static final String EMPLOYEE_LASTNAME = "lastname";
    public static final String EMPLOYEE_PHONENUMBER = "phonenumber";
    public static final String EMPLOYEE_EMAIL = "email";
    public static final String EMPLOYEE_ADDRESS = "address";
    public static final String EMPLOYEE_DEPARTMENT = "department";
    public static final String EMPLOYEE_GENDER = "gender";
    public static final String EMPLOYEE_APPROVALDATE = "employeeapprovaldate";

    //Itemdetails table begin
    public static final String ITEMDETAILS_TABLE = "stock";
    public static final String ITEMDETAILS_ITEMID = "stockid";
    public static final String ITEMDETAILS_NAME = "stockname";
    public static final String ITEMDETAILS_DESCRIPTION = "stockdescription";
    public static final String ITEMDETAILS_RATE = "rate";
    public static final String ITEMDETAILS_QUANTITY = "quantity";   
    public static final String ITEMDETAILS_PURCASEDATE = "purchasedate";
    //login_user table begin
    public static final String LOGINUSER_TABLE = "loginuser";
    public static final String LOGINUSER_USERNAME = "username";
    public static final String LOGINUSER_PASSWORD = "password";
    public static final String LOGINUSER_COMPANYNAME = "companyname";
    public static final String LOGINUSER_ACCOUNTTYPE = "accounttype";
    public static final String LOGINUSER_RESPONSIBILITY = "responsibility";
    //Salesorderdetail table begin
    public static final String CLIENTDETAIL_TABLE = "clientdetails";
     public static final String CLIENTDETAIL_CLIENTID = "salesid";
    public static final String CLIENTDETAIL_CLIENTNAME = "clientname";
    public static final String CLIENTDETAIL_CLIENTPONE = "clientphone";
    public static final String CLIENTDETAIL_CLIENTADDRESS = "clientaddress";
    public static final String CLIENTDETAIL_CLIENTEMAIL = "clientemail";
    public static final String CLIENTDETAIL_ORDERDATE = "orderdate";
    

    public static final String CLIENTITEM_TABLE = "sales";
    public static final String CLIENTITEM_ITEMNAME = "itemname";
    public static final String CLIENTITEM_ITEMID = "stockid";
    public static final String CLIENTITEM_SALESID = "salesid";
    public static final String CLIENTITEM_ITEMDESCRIPTION = "itemdescription";
    public static final String CLIENTITEM_QUANTITY = "quantity";
    public static final String CLIENTITEM_RATE = "rate";
    public static final String CLIENTITEM_PRICE = "price";

    public static final String INVOICE_TABLE = "invoice";
    public static final String INVOICE_NUMBER = "invoicenumber";
    public static final String INVOICE_ISSUEDBY = "issuedby";
    public static final String INVOICE_ISSUEDTO = "issuedto";
    public static final String INVOICE_ISSUEDATE = "issuedate";
    
    public static final String PURCHASE_TABLE = "purchase";
    public static final String PURCHASE_STOCKID = "stockid";
    public static final String PURCHASE_STOCKNAME = "stockname";
    public static final String PURCHASE_DESCRIPTION = "stockdescription";
    public static final String PURCHASE_RATE = "rate";
    public static final String PURCHASE_QUANTITY = "quantity";
    public static final String PURCHASE_DATE = "purchasedate";
}
