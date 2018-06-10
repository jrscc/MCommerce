package filters;

import java.util.Date;

public class PedidoFilter {

	private String firstName;

	private String lastName;

	private Date birthdayRangeBegin;

	private Date birthdayRangeEnd;
	
	private String login;

	public PedidoFilter() {

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

}
