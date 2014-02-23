package bookstore.click;

import bookstore.click.bean.CrateUserBean;
import bookstore.logic.CustomerLogic;
import net.sf.click.Page;
import net.sf.click.control.Form;
import net.sf.click.control.PasswordField;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.PageSubmit;

public class CrateUserPage extends Page {
	private CustomerLogic customerLogic;
	Form form = new Form("crateForm");
	
	public CrateUserPage() {
		TextField name = new TextField("name", "氏名", true);
		name.setSize(20);
		form.add(name);
		TextField email = new TextField("email", "E-mail", true);
		email.setSize(20);
		form.add(email);
		TextField account = new TextField("account", "ログイン名",true);
		account.setSize(20);
		form.add(account);
		TextField passwd = new PasswordField("passwd","パスワード", true);
		passwd.setSize(20);
		form.add(passwd);
		TextField passwd2 = new PasswordField("passwd2", "パスワード(確認)", true);
		passwd2.setSize(20);
		form.add(passwd2);
		
		form.add(new Submit("crate", "アカウント作成", this, "crateAccount"));
		form.add(new PageSubmit("リセット", CrateUserPage.class));
		
		addControl(form);
	}
	
	public boolean crateAccount () {
		if(form.isValid()) {
			CrateUserBean user = new CrateUserBean();
			form.copyTo(user);
		
			if(user.getPasswd().equals(user.getPasswd2()) == false) {
			setForward(CrateUserPage.class);
			return false;
			}
			customerLogic.createCustomer(user.getAccount(), user.getPasswd(), user.getName(), user.getEmail());
			setForward(LoginPage.class);
			}
		return true;
		}

	public CustomerLogic getCustomerLogic() {
		return customerLogic;
	}

	public void setCustomerLogic(CustomerLogic customerLogic) {
		this.customerLogic = customerLogic;
	}
	
	
}
