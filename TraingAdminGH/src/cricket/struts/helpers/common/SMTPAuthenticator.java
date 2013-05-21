package cricket.struts.helpers.common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator
{
	private String userName = null;

	private String password = null;

	public SMTPAuthenticator(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(userName, password);
	}
}
