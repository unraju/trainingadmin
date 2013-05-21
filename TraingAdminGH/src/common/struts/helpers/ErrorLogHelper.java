package common.struts.helpers;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.Transaction;

import common.hibernate.bf.user.ErrorLog;
import common.util.DateUtil;
import common.util.HibernateUtil;
import common.util.UserUtil;

public class ErrorLogHelper {

	public void logError(Throwable ex) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		ErrorLog errorLog = new ErrorLog();
		errorLog.setTime(DateUtil.getCurrentDateAsTimestampinGMT0530());
		String test1 = " ";
		if (ex != null) {
			test1 = ex.getMessage();
		} else {
			test1 = "This error due to JSP related like compilation....There is no Throwable";
		}

		if (test1 != null && test1.length() > 0) {
			int size = test1.length();
			if (size > 200) {
				size = 198;
			}
			errorLog.setErrorDescription(test1.substring(0, size));
		}
		if (UserUtil.getCurrentUser() != null) {
			errorLog.setUserName(UserUtil.getCurrentUser().getName());
		}

		if (UserUtil.isLogNeeded()) {
			// pipe throwable to byte buffer to obtain stack trace
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(baos);
			ex.printStackTrace(pw);
			pw.flush();
			String test = baos.toString();
			if (test != null && test.length() > 0) {
				int size = test.length();
				if (size > 900) {
					size = 900;
				}
				errorLog.setStackTrace(test.substring(0, size));
			}
		}
		session.save(errorLog);
		transaction.commit();
		// session.close();

	}

}
