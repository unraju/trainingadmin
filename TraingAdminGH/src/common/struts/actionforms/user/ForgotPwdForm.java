package common.struts.actionforms.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ForgotPwdForm extends ActionForm {

	private String loginName;

	private String pwd;

	private String reEnteredPwd;

	private String dob;

	private String emailId;

	public String getLoginName() {

		return loginName;
	}

	public void setLoginName(String loginName) {

		this.loginName = loginName;
	}

	public String getPwd() {

		return pwd;
	}

	public void setPwd(String pwd) {

		this.pwd = pwd;
	}

	public String getDob() {

		return dob;
	}

	public void setDob(String dob) {

		this.dob = dob;
	}

	public String getReEnteredPwd() {

		return reEnteredPwd;
	}

	public void setReEnteredPwd(String reEnteredPwd) {

		this.reEnteredPwd = reEnteredPwd;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		String[] runtimeValues = new String[3];
		String operation = (String) request.getParameter("operation");

		if (operation != null && operation.equalsIgnoreCase("Submit")) {
			/*
			 * if (loginName == null || loginName.trim().equals("")) {
			 * runtimeValues[0] = "Login Name"; errors.add("loginName", new
			 * ActionMessage("errors.required", runtimeValues[0])); }
			 * 
			 * if (dob == null || dob.trim().equals("")) { runtimeValues[1] =
			 * "DOB"; errors.add("dob", new ActionMessage("errors.required",
			 * runtimeValues[1])); this.dob = "";
			 * 
			 * } if (pwd == null || pwd.trim().equals("")) { runtimeValues[2] =
			 * "Password"; errors.add("pwd", new
			 * ActionMessage("errors.required", runtimeValues[2])); this.pwd =
			 * "";
			 * 
			 * } if (reEnteredPwd == null || reEnteredPwd.trim().equals("")) {
			 * runtimeValues[2] = "Re-Entered Pwd"; errors.add("reEnteredPwd",
			 * new ActionMessage("errors.required", runtimeValues[2])); this.pwd
			 * = "";
			 * 
			 * }
			 */
			if (emailId == null || emailId.trim().equals("")) {
				runtimeValues[0] = "Email Id";
				errors.add("emailId", new ActionMessage("errors.required", runtimeValues[0]));
			}
		}
		return errors;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
