package bookstore.click;

import java.util.List;

import javax.servlet.http.HttpSession;

import bookstore.logic.CustomerLogic;
import bookstore.logic.OrderLogic;
import bookstore.vbean.VCustomer;

import net.sf.click.Page;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Table;

public class OrderPage extends Page {
	private OrderLogic orderLogic;
	private CustomerLogic customerLogic;

	public Table bookTable = new Table("bookTable");
	public int totalPrice;
	public VCustomer vCustomer;
	public ActionLink link;
	
	public OrderPage() {
		bookTable.setAttribute("border", "1");
		bookTable.addColumn(new Column("title", "タイトル"));
		bookTable.addColumn(new Column("author", "著者"));
		bookTable.addColumn(new Column("publisher", "出版社"));
		bookTable.addColumn(new Column("price", "価格"));
		addControl(bookTable);
		
		link = new ActionLink("goTopPage", "トップに戻る", this, "goTopPage");
		
		addControl(link);
	}
	
	public boolean onSecurityCheck() {
		HttpSession httpSession = getContext().getRequest().getSession();
		if(httpSession == null) {
			setRedirect(SessionErrorPage.class);
			return false;
		}
		return true;
	}
	
	public void onInit() {
		HttpSession httpSession = getContext().getRequest().getSession();
		bookTable.setRowList((List)httpSession.getAttribute("ProductListViewCheck"));
		
		totalPrice = (Integer)httpSession.getAttribute("totalPrice");
		
		orderLogic.orderBooks((String)httpSession.getAttribute("Login"), (List)httpSession.getAttribute("Cart"));
		vCustomer = customerLogic.createVCustomer((String)httpSession.getAttribute("Login"));
	}
	
	public boolean goTopPage() {
		HttpSession seesion = getContext().getRequest().getSession();
		seesion.invalidate();
		setForward(LoginPage.class);
		return true;
	}

	public CustomerLogic getCustomerLogic() {
		return customerLogic;
	}

	public void setCustomerLogic(CustomerLogic customerLogic) {
		this.customerLogic = customerLogic;
	}

	public OrderLogic getOrderLogic() {
		return orderLogic;
	}

	public void setOrderLogic(OrderLogic orderLogic) {
		this.orderLogic = orderLogic;
	}
	
	
}
