package bookstore.dao.hibernate;

import java.util.Calendar;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bookstore.dao.OrderDAO;
import bookstore.pbean.TCustomer;
import bookstore.pbean.TOrder;

public class OrderDAOImpl extends HibernateDaoSupport
										implements OrderDAO{
	public TOrder createOrder(TCustomer inCustomer){

		TOrder saveOrder = new TOrder();
		saveOrder.setTCustomer( inCustomer );
		saveOrder.setOrderday( Calendar.getInstance().getTime() );
		
		getHibernateTemplate().save( saveOrder );
		
		return( saveOrder );
	}
}