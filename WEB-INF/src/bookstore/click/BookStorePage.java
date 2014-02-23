package bookstore.click;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import bookstore.logic.BookLogic;
import bookstore.vbean.VBook;
import net.sf.click.Page;
import net.sf.click.control.Checkbox;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;
import net.sf.click.extras.control.FieldColumn;
import net.sf.click.extras.control.FormTable;
import net.sf.click.util.ErrorPage;

public class BookStorePage extends Page {
	public FormTable bookTable = new FormTable("bookTable");
		
	public BookStorePage() {
		bookTable.setAttribute("border", "1");
		bookTable.getForm().setButtonAlign(Form.ALIGN_LEFT);
		FieldColumn column = new FieldColumn("selected", new Checkbox("selected"));
		column.setHeaderTitle("");
		bookTable.addColumn(column);
		
		bookTable.addColumn(new Column("title", "タイトル"));
		bookTable.addColumn(new Column("author", "著者"));
		bookTable.addColumn(new Column("publisher", "出版社"));
		bookTable.addColumn(new Column("price", "価格"));
		
		bookTable.getForm().add(new Submit("cart", "カートに追加", this, "cart"));
		bookTable.getForm().add(new Submit("buy", "商品購入", this, "buy"));
		addControl(bookTable);
	}
	
	public boolean onSecurityCheck() {
		HttpSession httpSession = getContext().getRequest().getSession(false);
		if(httpSession == null) {
			setRedirect(SessionErrorPage.class);
			return false;
		}
		return true;
	}
	
	public void onInit() {
		HttpSession httpSession = getContext().getRequest().getSession();
		List list = (List)httpSession.getAttribute("ProductListView");
		bookTable.setRowList(list);	
		bookTable.setRowCount(list.size());
	}

	//カートに追加
	public boolean cart() {
		List<VBook> vProductListAll = new ArrayList<VBook>();
		Iterator ite = bookTable.getRowList().iterator();
		while(ite.hasNext()) {
			VBook vbook = (VBook)ite.next();
			if(vbook.isSelected()) {
				vProductListAll.add(vbook);
			}
		}
		getContext().getRequest().getSession().setAttribute("bookList", vProductListAll);
		setForward(BookStorePage.class);
		return true;
	}
	
	//購入画面
	public boolean buy() {
		HttpSession session = getContext().getRequest().getSession();
		List books = (List)session.getAttribute("bookList");
		List<String> isbnList = new ArrayList<String>();
		Iterator<VBook> ite = books.iterator();
		
		while(ite.hasNext()) {
			isbnList.add(ite.next().getIsbn());
		}

		session.setAttribute("Cart", isbnList);
		session.setAttribute("ProductListViewCheck", books);
		setForward(CheckPage.class);
		return true;
	}

}