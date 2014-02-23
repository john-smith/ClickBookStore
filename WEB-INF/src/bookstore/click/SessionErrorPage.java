package bookstore.click;

import bookstore.click.LoginPage;
import net.sf.click.Page;
import net.sf.click.control.ActionLink;

public class SessionErrorPage extends Page {
	public ActionLink top;
	
	public SessionErrorPage() {
		top = new ActionLink("top", "トップ", this, "goTop");
	}
	
	public boolean goTop() {
		setForward(LoginPage.class);
		return true;
	}
}
