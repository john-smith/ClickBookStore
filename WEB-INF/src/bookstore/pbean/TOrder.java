package bookstore.pbean;
// Generated 2006/07/22 0:31:30 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * TOrder generated by hbm2java
 */

public class TOrder  implements java.io.Serializable {


    // Fields    

     private int id;
     private TCustomer TCustomer;
     private Date orderday;
     private Set TOrderDetails = new HashSet(0);


    // Constructors

    /** default constructor */
    public TOrder() {
    }

	/** minimal constructor */
    public TOrder(int id, TCustomer TCustomer, Date orderday) {
        this.id = id;
        this.TCustomer = TCustomer;
        this.orderday = orderday;
    }
    
    /** full constructor */
    public TOrder(int id, TCustomer TCustomer, Date orderday, Set TOrderDetails) {
        this.id = id;
        this.TCustomer = TCustomer;
        this.orderday = orderday;
        this.TOrderDetails = TOrderDetails;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public TCustomer getTCustomer() {
        return this.TCustomer;
    }
    
    public void setTCustomer(TCustomer TCustomer) {
        this.TCustomer = TCustomer;
    }

    public Date getOrderday() {
        return this.orderday;
    }
    
    public void setOrderday(Date orderday) {
        this.orderday = orderday;
    }

    public Set getTOrderDetails() {
        return this.TOrderDetails;
    }
    
    public void setTOrderDetails(Set TOrderDetails) {
        this.TOrderDetails = TOrderDetails;
    }
   








}