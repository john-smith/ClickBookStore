package bookstore.click;

import java.util.List;

import javax.servlet.http.HttpSession;

import bookstore.logic.BookLogic;

import net.sf.click.Page;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;
import net.sf.click.util.ErrorPage;

public class CheckPage extends Page {
	private BookLogic bookLogic;
	
	public Table bookTable = new Table("bookTable");
	public Form form = new Form("form");
	public int totalPrice;
	
	public CheckPage() {
		//テーブル作成
		bookTable.setAttribute("border", "1");
		bookTable.addColumn(new Column("title", "タイトル"));
		bookTable.addColumn(new Column("author", "著者"));
		bookTable.addColumn(new Column("publisher", "出版社"));
		bookTable.addColumn(new Column("price", "価格"));
		addControl(bookTable);
		
		//ボタン作成
		form.add(new Submit("chekcOut", "注文する", this, "chekcOut"));
		addControl(form);
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
		if(((List)httpSession.getAttribute("Cart")).size() == 0) {
			setForward(BookStorePage.class);
		}
		totalPrice = bookLogic.createVCheckout(((List)httpSession.getAttribute("Cart"))).getTotal();
	}
	
	public boolean chekcOut() {
		HttpSession session = getContext().getRequest().getSession();
		session.setAttribute("totalPrice", totalPrice);
		setForward(OrderPage.class);
		return true;
	}

	public BookLogic getBookLogic() {
		return bookLogic;
	}

	public void setBookLogic(BookLogic bookLogic) {
		this.bookLogic = bookLogic;
	}
	
	
}
