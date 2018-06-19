package filters;

import java.util.Date;

public class ClienteFilter {

	private String firstName;

	private String lastName;

	private Date birthdayRangeBegin;

	private Date birthdayRangeEnd;
	
	private String login;

	public ClienteFilter() {

	}

	public boolean isEmpty() {
		if (this.firstName != null && !this.firstName.trim().isEmpty()) {
			return false;
		}
		if (this.lastName != null && !this.lastName.trim().isEmpty()) {
			return false;
		}
		if (this.birthdayRangeBegin != null) {
			return false;
		}
		if (this.birthdayRangeEnd != null) {
			return false;
		}
		if (this.login != null) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "UserFilter [firstName=" + firstName + ", lastName=" + lastName + ", birthdayRangeBegin="
				+ birthdayRangeBegin + ", birthdayRangeEnd=" + birthdayRangeEnd + ", login="
				+ login + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdayRangeBegin() {
		return birthdayRangeBegin;
	}

	public void setBirthdayRangeBegin(Date birthdayRangeBegin) {
		this.birthdayRangeBegin = birthdayRangeBegin;
	}

	public Date getBirthdayRangeEnd() {
		return birthdayRangeEnd;
	}

	public void setBirthdayRangeEnd(Date birthdayRangeEnd) {
		this.birthdayRangeEnd = birthdayRangeEnd;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	

}
