package common.hibernate.bf.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webservices.restful.user.UserWSTO;

import common.struts.actionforms.user.UserForm;
import common.struts.actionforms.user.UserRoleForm;
import common.util.Constants;
import common.util.DateUtil;
import common.util.Utility;

public class User implements Serializable
{

	private Long id;

	private String name;

	private Date dob;

	private String loginName;

	private String password;

	private String address;

	private String contactNo;

	private boolean active;

	private Date createdDate;

	private String emailId;

	private boolean acceptRules;

	private List<UserRole> userRoles;

	private String gender = Constants.GENDER_MALE;

	private boolean mailFailed;

	private Long count;

	// this is used to identify the mobile users.
	private String userRef;

	// has current series Team
	private boolean hadCSTeam;

	public Long getId()
	{

		return id;
	}

	public void setId(Long id)
	{

		this.id = id;
	}

	public String getName()
	{

		return name;
	}

	public void setName(String name)
	{

		this.name = name;
	}

	public Date getDob()
	{

		return dob;
	}

	public void setDob(Date dob)
	{

		this.dob = dob;
	}

	public String getLoginName()
	{

		return loginName;
	}

	public void setLoginName(String loginName)
	{

		this.loginName = loginName;
	}

	public String getPassword()
	{

		return password;
	}

	public void setPassword(String password)
	{

		this.password = password;
	}

	public String getAddress()
	{

		return address;
	}

	public void setAddress(String address)
	{

		this.address = address;
	}

	public String getContactNo()
	{

		return contactNo;
	}

	public void setContactNo(String contactNo)
	{

		this.contactNo = contactNo;
	}

	public boolean isActive()
	{

		return active;
	}

	public void setActive(boolean active)
	{

		this.active = active;
	}

	public Date getCreatedDate()
	{

		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{

		this.createdDate = createdDate;
	}

	public String getEmailId()
	{

		return emailId;
	}

	public void setEmailId(String emailId)
	{

		this.emailId = emailId;
	}

	public List<UserRole> getUserRoles()
	{

		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles)
	{

		this.userRoles = userRoles;
	}

	public String getUserRolesAsString()
	{

		StringBuffer buffer = new StringBuffer();
		for (UserRole role : userRoles)
		{
			buffer.append(role.getRole());
			buffer.append(", ");
		}
		return buffer.toString();
	}

	public boolean isAcceptRules()
	{
		return acceptRules;
	}

	public void setAcceptRules(boolean acceptRules)
	{
		this.acceptRules = acceptRules;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public boolean isMailFailed()
	{
		return mailFailed;
	}

	public void setMailFailed(boolean mailFailed)
	{
		this.mailFailed = mailFailed;
	}

	public boolean isHadCSTeam()
	{
		return hadCSTeam;
	}

	public void setHadCSTeam(boolean hadCSTeam)
	{
		this.hadCSTeam = hadCSTeam;
	}

	@Override
	public String toString()
	{

		StringBuffer sb = new StringBuffer();
		sb.append("  Name ").append(name).append("  userId ").append(loginName).append(" date ").append(dob);
		return sb.toString();
	}

	public Long getCount()
	{
		return count;
	}

	public void setCount(Long count)
	{
		this.count = count;
	}

	public String getUserRef()
	{
		return userRef;
	}

	public void setUserRef(String userRef)
	{
		this.userRef = userRef;
	}

	public UserForm getUserForm()
	{

		UserForm userForm = new UserForm();
		userForm.setId(this.id);
		userForm.setName(this.name);
		userForm.setLoginName(loginName);
		userForm.setContactNo(contactNo);
		userForm.setAddress(address);
		userForm.setEmailId(emailId);
		userForm.setActive(Utility.convertBooleanToChar(this.active));
		userForm.setDob(DateUtil.getStringFromDate(this.dob, Constants.DATE_FORMAT_DD_MMM_YYYY));
		userForm.setCreatedDate(DateUtil.getStringFromDate(this.createdDate, "dd/MM/yyyy"));
		if (gender != null) userForm.setGender(gender);
		List<UserRoleForm> roleList = new ArrayList<UserRoleForm>();
		if (userRoles != null && userRoles.size() > 0)
		{
			for (UserRole role : userRoles)
			{
				roleList.add(role.getUserRoleForm());
			}
		}
		userForm.setUserRoles(roleList);
		userForm.setActiveUser(active);
		userForm.setPwd(password);
		userForm.setMailFailed(mailFailed);
		return userForm;
	}
	
	// this TO used for Webservice - Android
	public UserWSTO getUserWSTO()
	{

		UserWSTO user = new UserWSTO();
		user.setUserName(this.name);
		user.setContactNo(contactNo);
		user.setEmilId(emailId);
		//user.setActive(Utility.convertBooleanToChar(this.active));
		user.setDob(DateUtil.getStringFromDate(this.dob, Constants.DATE_FORMAT_DD_MMM_YYYY));
		//user.setCreatedDate(DateUtil.getStringFromDate(this.createdDate, "dd/MM/yyyy"));
		//if (gender != null) user.setGender(gender);
		user.setUserRef(userRef);
		return user;
	
	}

}
