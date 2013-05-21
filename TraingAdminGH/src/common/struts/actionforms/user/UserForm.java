package common.struts.actionforms.user;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class UserForm extends ActionForm
{

  private Long id;

  private String name;

  private String dob;

  private String loginName;

  private String pwd;

  private String reEnterPwd;

  private String address;

  private String contactNo;

  private char active;

  private List<UserRoleForm> userRoles;

  private List<UserRoleForm> allUserRoles;

  private String createdDate;

  private String gender;

  private String emailId;
  
  private boolean activeUser;
  
  private boolean  mailFailed;

  public boolean isActiveUser()
  {
  return activeUser;
  }

  public void setActiveUser(boolean activeUser)
  {
  this.activeUser = activeUser;
  }

  public String getAddress()
  {

    return address;
  }

  public String getContactNo()
  {

    return contactNo;
  }

  public String getDob()
  {

    return dob;
  }

  public String getGender()
  {

    return gender;
  }

  public String getLoginName()
  {

    return loginName;
  }

  public String getName()
  {

    return name;
  }

  public String getPwd()
  {

    return pwd;
  }

  public void setAddress(String address)
  {

    this.address = address;
  }

  public void setContactNo(String contactNo)
  {

    this.contactNo = contactNo;
  }

  public void setDob(String dob)
  {

    this.dob = dob;
  }

  public void setGender(String gender)
  {

    this.gender = gender;
  }

  public void setLoginName(String loginName)
  {

    this.loginName = loginName;
  }

  public void setName(String name)
  {

    this.name = name;
  }

  public void setPwd(String pwd)
  {

    this.pwd = pwd;
  }

  public String getReEnterPwd()
  {

    return reEnterPwd;
  }

  public void setReEnterPwd(String reEnterPwd)
  {

    this.reEnterPwd = reEnterPwd;
  }

  public String getEmailId()
  {

    return emailId;
  }

  public void setEmailId(String emailId)
  {

    this.emailId = emailId;
  }

  public List<UserRoleForm> getUserRoles()
  {

    return userRoles;
  }

  public void setUserRoles(List<UserRoleForm> userRoles)
  {

    this.userRoles = userRoles;
  }

  public char getActive()
  {

    return active;
  }

  public void setActive(char active)
  {

    this.active = active;
  }

  public String getCreatedDate()
  {

    return createdDate;
  }

  public void setCreatedDate(String createdDate)
  {

    this.createdDate = createdDate;
  }

  public Long getId()
  {

    return id;
  }

  public void setId(Long id)
  {

    this.id = id;
  }

  public List<UserRoleForm> getAllUserRoles()
  {
    return allUserRoles;
  }

  public void setAllUserRoles(List<UserRoleForm> allUserRoles)
  {
    this.allUserRoles = allUserRoles;
  }

  public boolean isMailFailed()
  {
    return mailFailed;
  }

  public void setMailFailed(boolean mailFailed)
  {
    this.mailFailed = mailFailed;
  }

  @Override
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {

    ActionErrors errors = new ActionErrors();
    String[] runtimeValues = new String[6];
    String operation = (String) request.getParameter("operation");

    if (operation != null && operation.equalsIgnoreCase("Submit"))
    {

      if (name == null || name.trim().equals(""))
      {
        runtimeValues[0] = "Name";
        errors.add("name", new ActionMessage("errors.required", runtimeValues[0]));
        this.name = "";
      }

    /*  if (loginName == null || loginName.trim().equals(""))
      {
        runtimeValues[0] = "Login Name";
        errors.add("loginName", new ActionMessage("errors.required", runtimeValues[0]));
        this.loginName = "";
      }*/

      if (pwd == null || pwd.trim().equals(""))
      {
        runtimeValues[1] = "Password";
        errors.add("pwd", new ActionMessage("errors.required", runtimeValues[1]));
        this.pwd = "";

      }
      if (dob == null || dob.trim().equals(""))
      {
        runtimeValues[2] = "Date of Birth";
        errors.add("dob", new ActionMessage("errors.required", runtimeValues[2]));
        this.dob = "";

      }

      if (reEnterPwd == null || reEnterPwd.trim().equals(""))
      {
        runtimeValues[3] = "Re-Entere Pwd";
        errors.add("reEnterPwd", new ActionMessage("errors.required", runtimeValues[3]));
        this.reEnterPwd = "";

      }
      if (emailId == null || emailId.trim().equals(""))
      {
        runtimeValues[3] = "Email";
        errors.add("emailId", new ActionMessage("errors.required", runtimeValues[3]));
        this.emailId = "";
      } else 
      {
        runtimeValues[3] = "Email";
        Pattern p=Pattern.compile("[a-z0-9A-Z-_.]*@[a-zA-Z]*.[a-zA-Z]*.[a-zA-Z]*.[a-zA-Z]*");  
        Matcher m=p.matcher(emailId);  
        boolean valid=m.matches();
        if(!valid)
        {
          errors.add("emailId", new ActionMessage("errors.invalid",runtimeValues[3]));
        }

      }

      if (address == null || address.trim().equals(""))
      {
        runtimeValues[3] = "Address";
        errors.add("address", new ActionMessage("errors.required", runtimeValues[3]));
        this.address = "";

      }

      if (contactNo == null || contactNo.trim().equals(""))
      {
        runtimeValues[4] = "Contact Number";
        errors.add("contactNo", new ActionMessage("errors.required", runtimeValues[4]));
        this.contactNo = "";
      } else 
      {
        runtimeValues[4] = "Contact Number";
        Pattern p=Pattern.compile("[0-9]{10,12}?");  
        Matcher m=p.matcher(contactNo);  
        boolean valid=m.matches();
        if(!valid)
        {
          errors.add("contactNo", new ActionMessage("errors.invalid",runtimeValues[4]));
        }
      }
    } else if(operation != null && operation.equalsIgnoreCase("Confirm"))
  {

    if (name == null || name.trim().equals(""))
    {
      runtimeValues[0] = "Name";
      errors.add("name", new ActionMessage("errors.required", runtimeValues[0]));
      this.name = "";
    }

    if (dob == null || dob.trim().equals(""))
    {
      runtimeValues[2] = "Date of Birth";
      errors.add("dob", new ActionMessage("errors.required", runtimeValues[2]));
      this.dob = "";

    }

    if (emailId == null || emailId.trim().equals(""))
    {
      runtimeValues[3] = "Email";
      errors.add("emailId", new ActionMessage("errors.required", runtimeValues[3]));
      this.emailId = "";
    }
    else
    {
      runtimeValues[3] = "Email";
      Pattern p = Pattern.compile("[a-z0-9A-Z-_.]*@[a-zA-Z]*.[a-zA-Z]*.[a-zA-Z]*.[a-zA-Z]*");
      Matcher m = p.matcher(emailId);
      boolean valid = m.matches();
      if (!valid)
      {
        errors.add("emailId", new ActionMessage("errors.invalid", runtimeValues[3]));
      }

    }

    if (address == null || address.trim().equals(""))
    {
      runtimeValues[3] = "Address";
      errors.add("address", new ActionMessage("errors.required", runtimeValues[3]));
      this.address = "";

    }

    if (contactNo == null || contactNo.trim().equals(""))
    {
      runtimeValues[4] = "Contact Number";
      errors.add("contactNo", new ActionMessage("errors.required", runtimeValues[4]));
      this.contactNo = "";
    }
    else
    {
      runtimeValues[4] = "Contact Number";
      Pattern p = Pattern.compile("[0-9]{10,12}?");
      Matcher m = p.matcher(contactNo);
      boolean valid = m.matches();
      if (!valid)
      {
        errors.add("contactNo", new ActionMessage("errors.invalid", runtimeValues[4]));
      }
    }

  }
    return errors;

  }

  public String getUserRolesAsString()
  {

    StringBuffer buffer = new StringBuffer();
    for (UserRoleForm role : userRoles)
    {
      buffer.append(role.getRole());
      buffer.append(", ");
    }
    return buffer.toString();
  }

}
