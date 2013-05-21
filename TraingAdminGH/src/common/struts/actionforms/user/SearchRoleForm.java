package common.struts.actionforms.user;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class SearchRoleForm extends ActionForm
{

  private String role;

  private boolean active = true;

  private String selectedActivity;

  private List<ActivityForm> allActivities;

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  public String getSelectedActivity()
  {
    return selectedActivity;
  }

  public void setSelectedActivity(String selectedActivity)
  {
    this.selectedActivity = selectedActivity;
  }

  public List<ActivityForm> getAllActivities()
  {
    return allActivities;
  }

  public void setAllActivities(List<ActivityForm> allActivities)
  {
    this.allActivities = allActivities;
  }
}
