package cricket.hibernate.bf.common;

import java.io.Serializable;

import cricket.struts.actionforms.common.SeriesTypeForm;

public class SeriesType implements Serializable
{

  private Long id;

  private String name;
  

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

  public SeriesTypeForm getActionForm()
  {

  // TODO Auto-generated method stub
  SeriesTypeForm form = new SeriesTypeForm();
  form.setId(id);
  form.setName(name);
  return form;

  }

}
