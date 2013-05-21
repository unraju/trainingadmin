package cricket.struts.helpers.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.hibernate.bf.user.User;
import common.struts.helpers.ErrorLogHelper;
import common.util.HibernateUtil;

public class GenerateMailHelper1
{

	public void mailScoreReport()
	{
		List<User> activeUserTeams = getActiveUserTeams();
		for (User user : activeUserTeams)
		{
			generateScoreReportMail(user);
			// break;
		}
	}

	private List<User> getActiveUserTeams()
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("active", true));
		// Criteria criteria1 = criteria.createCriteria(");
		// criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		List<User> list = (ArrayList<User>) criteria.list();
		return list;

	}

	private void generateScoreReportMail(User userTeam)
	{

		// SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
		String to = // "raju.unnava@gmail.com";
		userTeam.getEmailId();
		// "info@supercricteam.com";
		// userTeam.getUser().getEmailId();
		to = to.trim();
		String from = "scores@supercricteam.com";
		// SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
		String host = "mail.supercricteam.com";// "213.175.198.160";

		// Create properties, get Session
		Properties props = new Properties();

		// If using static Transport.send(),
		// need to specify which host to send it to
		props.put("mail.smtp.host", host);
		// To see what is going on behind the scene
		props.put("mail.debug", "true");
		Authenticator authenticator = new SMTPAuthenticator("admin", "new12345");
		javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);

		try
		{
			// Instantiatee a message
			Message msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address =
			{new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("Super Cric Team -  IPL");
			msg.setSentDate(new Date());

			// Set message content
			/* msg.setText("Hi "+userTeam.getUser().getName()+",\n\n" + 
			     "Super Cric Team -  Standings are updated up to latest match. Your Team "  +userTeam.getName()+ " Score - " +userTeam.getScore()+
			     ". \n\n" +
			     "Login to check your standing - http://www.supercricteam.com \n\n"+
			     "Any information contact on -  info@supercricteam.com \n\n"+
			     "Don't reply to this mail. \n\n\n"+
			     "Super Cric Team");*/

			msg.setText("Hi "
					+ userTeam.getName()
					+ ",\n\n"
					+ " Thanks for participating WC Super Cric Team contest.  Hope your are enjoyed the World Cup with SCT. \n\n"
					+ " It is time to Super Cric team IPL contest. Now you can select your IPL dream team..... Login http://www.supercricteam.com with same World Cup credentials. \n\n"
					+ " Few rules are changed in IPL, read the same on SCT rules page on site. \n\n"
					+ " Hurry Up few more days left to start IPL ...Select your dream IPL team. \n\n"
					+ " The top two winners of this tournament (the one with the maximum number of points) will receive gift vochers worth of Rs500/- each. \n\n"
					+ " Any further information contact on us -  info@supercricteam.com \n\n" + "Super Cric Team \n"
					+ "Own Your Own Cricket Team \n" + "http://www.supercricteam.com  ");

			// Send the mto this maolessage
			Transport.send(msg);
		}
		catch (MessagingException mex)
		{
			int userId = userTeam.getId().intValue();
			if (userId < 20 && userId > 10)
			{
				new ErrorLogHelper().logError(mex);
			}
		}

	}

}
