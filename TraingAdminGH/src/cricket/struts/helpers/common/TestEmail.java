package cricket.struts.helpers.common;

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
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;

import common.hibernate.bf.user.User;

// Send a simple, single part, text/plain e-mail
public class TestEmail
{

  private static Configuration configuration;

  private static SessionFactory sessionFactory;
  
  private  static Transaction transaction;

  static ApplicationContext ctx = null;
  // Create the initial SessionFactory from the default configuration files
  static
  {

    // Getting the connection direct from Configuration
    configuration = new Configuration();
    sessionFactory = configuration.configure("test_hibernate.cfg.xml").buildSessionFactory();
  }

  public static void main(String[] args)
  {
  List<User> users = getAllUser();
  int size = 0;
  for (User user : users)
  {
    generateScoreReportMail(user);
    size++;
    if(size ==1)
    {
     break;
    }
    // break;
   // transaction.commit();
  }
  /*
   * // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!! String to =
   * "info@supercricteam.com"; String from = "scores@supercricteam.com"; //
   * SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!! String host =
   * "mail.supercricteam.com";
   * 
   * // Create properties, get Session Properties props = new Properties();
   * 
   * // If using static Transport.send(), // need to specify which host to send
   * it to props.put("mail.smtp.host", host); // To see what is going on behind
   * the scene props.put("mail.debug", "true"); Session session =
   * Session.getInstance(props);
   * 
   * try { // Instantiatee a message Message msg = new MimeMessage(session);
   * 
   * // Set message attributes msg.setFrom(new InternetAddress(from));
   * InternetAddress[] address = { new InternetAddress(to) };
   * msg.setRecipients(Message.RecipientType.TO, address);
   * msg.setSubject("Super Cric Team -  Updates"); msg.setSentDate(new Date());
   * 
   * StringBuffer sb = new StringBuffer(); sb.append(
   * "<div class=Section1><p class=MsoNormal><o:p>&nbsp;</o:p></p><p class=MsoNormal><o:p>&nbsp;</o:p></p><p class=MsoNormal><b><span style='font-size:12.0pt;color:#00B0F0'>Thanks &amp;Regards,</span></b><b><span style='font-size:12.0pt;color:#0070C0'><o:p></o:p></span></b></p>"
   * +
   * "<p class=MsoNormal><b><span style='font-size:18.0pt;color:#17365D'>Super CricTeam<o:p></o:p></span></b></p><p class=MsoNormal><i><span style='font-size:10.0pt;color:#E36C0A'>Own Your WonCric Team<o:p></o:p></span></i></p>"
   * +
   * "<p class=MsoNormal><span style='font-size:14.0pt;color:black'>contect us : </span><b><spanstyle='font-size:14.0pt;color:#365F91'>info@supercricteam.com</span></b><spanstyle='font-size:14.0pt;color:black'><o:p></o:p></span></p><p class=MsoNormal><o:p>&nbsp;</o:p></p>"
   * + "</div>"); // Set message content msg.setText("Hi \n" +
   * "Super Cric Team Standings are updated util match 19. Your Team Score  - 1000. \n"
   * + "plain Login to check your team scores. supercriteam.com \n\n" +
   * sb.toString());
   * 
   * 
   * Transport transport = session.getTransport("smtp.213.175.198.160");
   * transport.connect(host, "admin@supercricteam.com", "new12345");
   * transport.sendMessage(msg, msg.getAllRecipients()); transport.close();
   * 
   * 
   * // Send the message Transport.send(msg); } catch (MessagingException mex) {
   * // Prints all nested (chained) exceptions as well mex.printStackTrace(); }
   */
  }

  private static void generateScoreReportMail(User user)
  {

  // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
  String to = //user.getEmailId();
      "info@supercricteam.com";
  // userTeam.getUser().getEmailId();
  to = to.trim();
  String from = "admin@supercricteam.com";
  // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
  String host = "mail.supercricteam.com";// "213.175.198.160";

  // Create properties, get Session
  Properties props = new Properties();

  // If using static Transport.send(),
  // need to specify which host to send it to
  props.put("mail.smtp.host", host);
  // To see what is going on behind the scene
  props.put("mail.debug", "false");
  Authenticator   authenticator = new SMTPAuthenticator("admin", "new12345"); 
  javax.mail.Session session = javax.mail.Session.getInstance(props,authenticator);

  try
  {
    // Instantiatee a message
    Message msg = new MimeMessage(session);

    // Set message attributes
    msg.setFrom(new InternetAddress(from));
    InternetAddress[] address = { new InternetAddress(to) };
    msg.setRecipients(Message.RecipientType.TO, address);
    msg.setSubject("Super Cric Team - IPL Contest ");
    msg.setSentDate(new Date());

    // Set message content
    msg.setText("Hi " + user.getName() + ",\n\n" + 
        " Thanks for participating WC Super Cric Team contest.  Hope your are enjoyed the World Cup with SCT. \n\n" 
         +" It is time to Super Cric team IPL contest. Now you can select your IPL dream team..... Login http://www.supercricteam.com with same World Cup credentials. \n\n" + 
         " Few rules are changed in IPL, read the same on SCT rules page on site. \n\n" + 
         " Hurry Up few more days left to start IPL ...Select your dream IPL team. \n\n"+
         " The top two winners of this tournament (the one with the maximum number of points) will receive gift vochers worth of Rs500/- each. \n\n"+
         " Any further information contact on us -  info@supercricteam.com \n\n" 
        +"Super Cric Team \n"
        +"Own Your Own Cricket Team \n"+
        "http://www.supercricteam.com  ");

    // Send the mto this maolessage
    Transport.send(msg);
  }
  catch (MessagingException mex)
  {
    // int userId = userTeam.getUser().getId().intValue();
    mex.printStackTrace();
  }
  //user.setMailFailed(true);

  }

  private static List<User> getAllUser()
  {
  org.hibernate.Session session = sessionFactory.openSession();
  //transaction=session.beginTransaction();
  Criteria criteria = session.createCriteria(User.class);
  List<User> list = criteria.list();
  return list;
  }
}// End of class