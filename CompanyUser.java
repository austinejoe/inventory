package numerouno;

import java.sql.Date;

/**
 *
 * @author Austine joe
 */
public class CompanyUser {

    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;
    private String address;
    private String department;
    private Date employeeapprovaldate;
    private String gender;

    private String itemname;
    private String itemdescription;
    private Double rate;
    private Double price;
    private int quantity;
    private Date purchasedate;
    private int itemid;
    private int salesid;

    private String fullname;
    private String username;
    private String password;
    private String logindate;
    private String companyname;
    private String accounttype;
    private String responsibility;

    private String clientname;
    private String clientphone;
    private String clientaddress;
    private String clientemail;
    private Date orderdate;

    private String invoiceNumber;
    private String issuedBy;
    private String issuedTo;
    private Date issueDate;
    private int month;

    public CompanyUser(String accounttype, String companyname, String username, String password, String responsibility) {
        this.username = username;
        this.password = password;
        this.companyname = companyname;
        this.accounttype = accounttype;
        this.responsibility = responsibility;
    }

    public CompanyUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CompanyUser(int itemid, int quantity) {
        this.quantity = quantity;
        this.itemid = itemid;
    }

    public CompanyUser(String firstname, String lastname, String phonenumber, String email, String address, String department, String gender, Date employeeapprovaldate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.department = department;
        this.gender = gender;
        this.employeeapprovaldate = employeeapprovaldate;
    }

    public CompanyUser(String clientname, String clientphone, String clientaddress, String clientemail, Date orderdate) {
        this.clientname = clientname;
        this.clientphone = clientphone;
        this.clientaddress = clientaddress;
        this.clientemail = clientemail;
        this.orderdate = orderdate;
    }

    public CompanyUser(String itemname, String itemdescription, double rate, int quantity, Date purchasedate) {
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.rate = rate;
        this.quantity = quantity;
        this.purchasedate = purchasedate;
    }

    public CompanyUser(int itemid, String itemname, String itemdescription, int quantity, double rate, double price) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.quantity = quantity;
        this.rate = rate;
        this.price = price;
    }

    public CompanyUser(int salesid, int itemid, String itemname, String itemdescription, int quantity, double rate, double price, Date orderdate) {
        this.salesid = salesid;
        this.itemid = itemid;
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.rate = rate;
        this.price = price;
        this.quantity = quantity;
        this.orderdate = orderdate;
    }

    public CompanyUser(String invoiceNumber, String issuedBy, String issuedTo, Date issueDate) {
        this.invoiceNumber = invoiceNumber;
        this.issuedBy = issuedBy;
        this.issuedTo = issuedTo;
        this.issueDate = issueDate;
    }

    public CompanyUser(int salesid) {
        this.salesid = salesid;
    }

    public CompanyUser(int itemid, String clientname, String clientphone, String clientaddress, String clientemail, String itemname, String itemdescription, int quantity, double rate, double price, Date orderdate) {
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.rate = rate;
        this.price = price;
        this.quantity = quantity;
        this.itemid = itemid;
        this.clientname = clientname;
        this.clientphone = clientphone;
        this.clientaddress = clientaddress;
        this.clientemail = clientemail;
        this.orderdate = orderdate;
    }

    public CompanyUser(int itemid, String itemname, String itemdescription, double rate, int quantity, Date purchasedate) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.rate = rate;
        this.quantity = quantity;
        this.purchasedate = purchasedate;

    }

    public CompanyUser(double rate, int itemid) {
        this.rate = rate;
        this.itemid = itemid;
    }

    public CompanyUser(String itemname, int itemid) {
        this.itemname = itemname;
        this.itemid = itemid;
    }

    public CompanyUser(int itemid, String clientname, String itemname, int quantity, Double rate, Double price, Date orderdate) {
        this.itemid = itemid;
        this.clientname = clientname;
        this.itemname = itemname;
        this.quantity = quantity;
        this.rate = rate;
        this.price = price;
        this.orderdate = orderdate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getEmployeeapprovaldate() {
        return employeeapprovaldate;
    }

    public void setEmployeeapprovaldate(Date employeeapprovaldate) {
        this.employeeapprovaldate = employeeapprovaldate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getSalesid() {
        return salesid;
    }

    public void setSalesid(int salesid) {
        this.salesid = salesid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogindate() {
        return logindate;
    }

    public void setLogindate(String logindate) {
        this.logindate = logindate;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getClientphone() {
        return clientphone;
    }

    public void setClientphone(String clientphone) {
        this.clientphone = clientphone;
    }

    public String getClientaddress() {
        return clientaddress;
    }

    public void setClientaddress(String clientaddress) {
        this.clientaddress = clientaddress;
    }

    public String getClientemail() {
        return clientemail;
    }

    public void setClientemail(String clientemail) {
        this.clientemail = clientemail;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

}
