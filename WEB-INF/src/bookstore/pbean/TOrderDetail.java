package bookstore.pbean;
// Generated 2006/07/22 0:31:30 by Hibernate Tools 3.1.0.beta4



/**
 * TOrderDetail generated by hbm2java
 */

public class TOrderDetail  implements java.io.Serializable {


    // Fields    

     private int id;
     private TOrder TOrder;
     private TBook TBook;


    // Constructors

    /** default constructor */
    public TOrderDetail() {
    }

    
    /** full constructor */
    public TOrderDetail(int id, TOrder TOrder, TBook TBook) {
        this.id = id;
        this.TOrder = TOrder;
        this.TBook = TBook;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public TOrder getTOrder() {
        return this.TOrder;
    }
    
    public void setTOrder(TOrder TOrder) {
        this.TOrder = TOrder;
    }

    public TBook getTBook() {
        return this.TBook;
    }
    
    public void setTBook(TBook TBook) {
        this.TBook = TBook;
    }
   








}
