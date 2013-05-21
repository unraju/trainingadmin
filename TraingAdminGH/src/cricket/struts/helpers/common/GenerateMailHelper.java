package cricket.struts.helpers.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.hibernate.bf.user.User;
import common.struts.helpers.ErrorLogHelper;
import common.util.HibernateUtil;
import common.util.UserUtil;

import cricket.hibernate.bf.team.UserTeam;

public class GenerateMailHelper {

	public void mailScoreReport() {
		List<UserTeam> activeUserTeams = getActiveUserTeams();
		for (UserTeam userTeam : activeUserTeams) {
			generateScoreReportMail(userTeam);
			// break;
		}
	}

	private List<UserTeam> getActiveUserTeams() {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		List<UserTeam> list = (ArrayList<UserTeam>) criteria.list();
		return list;

	}

	private void generateScoreReportMail(UserTeam userTeam) {

		// SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
		String to = // "raju.unnava@gmail.com";
		userTeam.getUser().getEmailId();
		// "info@supercricteam.com";
		// userTeam.getUser().getEmailId();
		to = to.trim();
		String from = "scores@supercricteam.com";
		// SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
		String host = "localhost";
		// "213.175.198.160";

		// Create properties, get Session
		Properties props = new Properties();

		// If using static Transport.send(),
		// need to specify which host to send it to
		props.put("mail.smtp.host", host);
		// To see what is going on behind the scene
		props.put("mail.debug", "false");
		Authenticator authenticator = new SMTPAuthenticator("scores", "unraju");
		javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);

		try {
			// Instantiatee a message
			Message msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("Super Cric Team - IPL Score");
			msg.setSentDate(new Date());

			// Set message content
			msg.setText("Hi " + userTeam.getUser().getName() + ",\n\n"
					+ "Super Cric Team -  Standings are updated up to latest match. " + "Your Team " + userTeam.getName()
					+ " Score - " + userTeam.getScore() + ". \n\n" + "Your Team available substitutions - "
					+ (userTeam.getSubstitutions() - userTeam.getUsedSubstitutions()) + "\n\n"
					+ "Login to check your standing - http://www.supercricteam.com \n\n"
					+ "Any information contact on -  info@supercricteam.com \n\n" + "Don't reply to this mail. \n\n\n"
					+ "Super Cric Team");

			// Send the mto this maolessage
			Transport.send(msg);
		} catch (MessagingException mex) {
			int userId = userTeam.getUser().getId().intValue();
			if (userId < 20 && userId > 10) {
				new ErrorLogHelper().logError(mex);
			}
		}

	}

	public static void sendMailForgotPassword(User user) {

		// SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
		String to = // "raju.unnava@gmail.com";
		user.getEmailId();
		// "info@supercricteam.com";
		// userTeam.getUser().getEmailId();
		to = to.trim();
		String from = "scores@supercricteam.com";
		// SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
		String host = "localhost";
		// "213.175.198.160";

		// Create properties, get Session
		Properties props = new Properties();

		// If using static Transport.send(),
		// need to specify which host to send it to
		props.put("mail.smtp.host", host);
		// To see what is going on behind the scene
		props.put("mail.debug", "false");
		Authenticator authenticator = new SMTPAuthenticator(" User Credentials -  supercricteam.com", "unraju");
		javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);

		try {
			// Instantiatee a message
			Message msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("Super Cric Team - Login credentials");
			msg.setSentDate(new Date());

			// Set message content
			msg.setText("Hi " + user.getName() + ",\n\n" + " Your Login details for Super Cric Team " + "\n   " +
					"   Login Id -"	+ user.getLoginName() + "\n      Password -" + user.getPassword() + "\n\n"
					+ "Login - http://www.supercricteam.com \n\n"
					+ "Any information contact on -  info@supercricteam.com \n\n" +
					 "Don't reply to this mail. \n\n\n"
					+ "Super Cric Team");

			// Send the mto this maolessage
			Transport.send(msg);
		} catch (MessagingException mex) {
			int userId = user.getId().intValue();
			if (userId < 20 && userId > 10) {
				new ErrorLogHelper().logError(mex);
			}
		}

	}
	
	public static void sendMailRegisterUser(User user) throws AddressException, MessagingException {

		// SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
		String to = // "raju.unnava@gmail.com";
		user.getEmailId();
		// "info@supercricteam.com";
		// userTeam.getUser().getEmailId();
		to = to.trim();
		String from = "scores@supercricteam.com";
		// SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
		String host = "localhost";
		// "213.175.198.160";

		// Create properties, get Session
		Properties props = new Properties();

		// If using static Transport.send(),
		// need to specify which host to send it to
		props.put("mail.smtp.host", host);
		// To see what is going on behind the scene
		props.put("mail.debug", "false");
		Authenticator authenticator = new SMTPAuthenticator(" Regisration Confirmation! -  supercricteam.com", "unraju");
		javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);

		
			// Instantiatee a message
			Message msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("Super Cric Team - Regisration Confirmation!");
			msg.setSentDate(new Date());

			// Set message content
			msg.setText("Hi " + user.getName() + ",\n\n" + "Thanks for Registration!...\n\nYour Login details for Super Cric Team " + "\n   " +
					"   Login Id -"	+ user.getLoginName() + "\n      Password -" + user.getPassword() + "\n\n"
					+ "Login - http://www.supercricteam.com \n\n"
					+ "Any information contact on -  info@supercricteam.com \n\n" +
					 "Don't reply to this mail. \n\n\n"
					+ "Super Cric Team");

			// Send the mto this maolessage
			Transport.send(msg);
		

	}

	public void sendSeriesLaunchMail()
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("active", true));
		//criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		List<User> list = (ArrayList<User>) criteria.list();
		for(User user: list)
		{
			try
			{
				sendSeriesLauchMail(user);
			}
			catch (Exception e)
			{
				int userId = user.getId().intValue();
				if (userId < 01 && userId > 10) {
					new ErrorLogHelper().logError(e);
				}
			
			}
		}
	}
	
	private void sendSeriesLauchMail(User user) throws AddressException, MessagingException {

		// SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
		String to = // "raju.unnava@gmail.com";
		user.getEmailId();
		// "info@supercricteam.com";
		// userTeam.getUser().getEmailId();
		to = to.trim();
		String from = "scores@supercricteam.com";
		// SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
		String host = "localhost";
		// "213.175.198.160";

		// Create properties, get Session
		Properties props = new Properties();

		// If using static Transport.send(),
		// need to specify which host to send it to
		props.put("mail.smtp.host", host);
		// To see what is going on behind the scene
		props.put("mail.debug", "false");
		Authenticator authenticator = new SMTPAuthenticator("Super Cric Team - IPL 12 Contest launched.....Hurry Up to select your team!-  supercricteam.com", "unraju");
		javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);

		
			// Instantiatee a message
			Message msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("Super Cric Team -  IPL 12 Contest launched.....Hurry Up to select your team!");
			msg.setSentDate(new Date());

			// Set message content
			msg.setText("Hi " + user.getName() + ",\n\n" + "Super Cric Team IPL 5 contest launched, select your own team & enjoy the series....\n\nYour Login details for Super Cric Team " + "\n   " +
					"   Login Id -"	+ user.getLoginName() + "\n      Password -" + user.getPassword() + "\n\n"
					+ "Login - http://www.supercricteam.com \n\n"
					+ "For any queries contact on -  info@supercricteam.com \n\n" +
					 "Don't reply to this mail. \n\n\n"
					+ "Super Cric Team");

			// Send the mto this maolessage
			Transport.send(msg);
	}

}
