package cricket.hibernate.bf.common;

import java.io.Serializable;
import java.util.Date;

import cricket.struts.actionforms.common.SunSignForm;

public class SunSigns implements Serializable
{

  private Long id;

  private String name;

  private Date startDate;

  private Date endDate;

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

  public Date getStartDate()
  {
  return startDate;
  }

  public void setStartDate(Date startDate)
  {
  this.startDate = startDate;
  }

  public Date getEndDate()
  {
  return endDate;
  }

  public void setEndDate(Date endDate)
  {
  this.endDate = endDate;
  }

  public SunSignForm getActionForm()
  {
  SunSignForm form = new SunSignForm();
  form.setId(id);
  form.setName(name);
  return form;
  }

}
