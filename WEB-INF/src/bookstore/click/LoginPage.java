package bookstore.click;

import java.util.List;

import javax.servlet.http.HttpSession;

import bookstore.click.bean.LoginBean;
import bookstore.logic.BookLogic;
import bookstore.logic.CustomerLogic;
import net.sf.click.Page;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Form;
import net.sf.click.control.PasswordField;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.PageSubmit;

public class LoginPage extends Page {
	private CustomerLogic customerLogic;
	private BookLogic bookLogic;
	
	public Form form = new Form("loginForm");
	public ActionLink link;
	public LoginPage() {
		//フォーム作成
		TextField account = new TextField("account","ログイン名", true);
		account.setSize(20);
		form.add(account);
		TextField passwd = new PasswordField("passwd", "パスワード", true);
		passwd.setSize(20);
		form.add(passwd);
		
		form.add(new Submit("login", "ログイン", this, "login"));
		form.add(new PageSubmit("リセット", LoginPage.class));
		
		addControl(form);
		
		//アカウント作成ページへのリンク
		link = new ActionLink("goCratePage", "こちら", this, "goCratePage");
	
		addControl(link);
	}
	
	//ログイン処理
	public boolean login(){
		if(form.isValid()) {
			LoginBean loginBean = new LoginBean();
			form.copyTo(loginBean);
		
			//パスワードの確認
			if(!customerLogic.isPasswordMatched(loginBean.getAccount(), loginBean.getPasswd())) {
				setForward(LoginPage.class);
				return false;
			}
		
			//session
			HttpSession httpSession = getContext().getRequest().getSession(false);
			if(httpSession != null) {
				httpSession.invalidate();
			}
			httpSession = getContext().getRequest().getSession();
			
			httpSession.setAttribute("Login", loginBean.getAccount());
		
			List productListAll = bookLogic.getAllBookISBNs();
			List vProductList = bookLogic.createVBookList(productListAll, null );
		
			httpSession.setAttribute( "ProductList", productListAll );
			httpSession.setAttribute( "ProductListView", vProductList );
			setForward(BookStorePage.class);
		}
		return true;
	}
	
	//アカウント作成ページ
	public boolean goCratePage() {
			setForward(CrateUserPage.class);
			return true;
	}
	
	public BookLogic getBookLogic() {
		return bookLogic;
	}

	public void setBookLogic(BookLogic bookLogic) {
		this.bookLogic = bookLogic;
	}

	public CustomerLogic getCustomerLogic() {
		return customerLogic;
	}

	public void setCustomerLogic(CustomerLogic customerLogic) {
		this.customerLogic = customerLogic;
	}
	
	
}
