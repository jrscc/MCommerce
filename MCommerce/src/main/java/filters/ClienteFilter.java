package filters;


public class ClienteFilter {

	
	private String login;

	public ClienteFilter() {

	}

	public boolean isEmpty() {
		
		if (this.login != null) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "ClienteFilter [login=" + login + "]";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	

}
