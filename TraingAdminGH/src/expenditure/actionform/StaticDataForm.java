package expenditure.actionform;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class StaticDataForm extends ActionForm
{

  private String staticType;

  private String code;

  private String description;

  private boolean financeItemType;
  
  private int cashFlowTypeId;

  public String getStaticType()
  {
    return staticType;
  }

  public void setStaticType(String staticType)
  {
    this.staticType = staticType;
  }

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public boolean isFinanceItemType()
  {
    return financeItemType;
  }

  public void setFinanceItemType(boolean financeItemType)
  {
    this.financeItemType = financeItemType;
  }

  @Override
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {

    ActionErrors errors = new ActionErrors();
    String[] runtimeValues = new String[6];

    if (staticType == null || staticType.trim().equals(""))
    {
      runtimeValues[0] = "Static Type";
      errors.add("StaticType", new ActionMessage("errors.required", runtimeValues[0]));
      this.staticType = "";
    }

    if (code == null || code.trim().equals(""))
    {
      runtimeValues[1] = "code";
      errors.add("code", new ActionMessage("errors.required", runtimeValues[1]));
      this.code = "";

    }

    if (description == null || description.trim().equals(""))
    {
      runtimeValues[2] = "description";
      errors.add("description", new ActionMessage("errors.required", runtimeValues[2]));
      this.description = "";

    }
    return errors;
  }

}
