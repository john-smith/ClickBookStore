package bookstore.logic;

import java.util.Iterator;
import java.util.List;

import bookstore.dao.BookDAO;
import bookstore.dao.CustomerDAO;
import bookstore.dao.OrderDAO;
import bookstore.dao.OrderDetailDAO;
import bookstore.pbean.TBook;
import bookstore.pbean.TCustomer;
import bookstore.pbean.TOrder;

public class OrderLogicImpl implements OrderLogic {

	BookDAO bookdao;
	CustomerDAO customerdao;
	OrderDAO orderdao;
	OrderDetailDAO odetaildao;
	
	public void orderBooks( String inUid, List inISBNs ){
	
		TCustomer customer = customerdao.findCustomerByUid( inUid );
		TOrder order = orderdao.createOrder( customer );

		Iterator iter = bookdao.retrieveBooksByISBNs( inISBNs )
														.iterator();
		while( iter.hasNext() ){
			TBook book = (TBook)iter.next();
			odetaildao.createOrderDetail( order, book );
		}
	}

	
	public void setBookdao( BookDAO bookdao ){
		this.bookdao = bookdao;
	}


	public void setCustomerdao( CustomerDAO customerdao ){
		this.customerdao = customerdao;
	}


	public void setOrderdao( OrderDAO orderdao ){
		this.orderdao = orderdao;
	}


	public void setOrderdetaildao( OrderDetailDAO odetaildao ){
		this.odetaildao = odetaildao;
	}
}