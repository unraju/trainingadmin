package cricket.struts.actionforms.common;

import org.apache.struts.action.ActionForm;

import common.util.Utility;

public class SCTNewsForm extends ActionForm
{

  private Long id;

  private String news;

  public String author;

  public boolean live;
  
  public String liveString;

  public String date;

  public String priority;

  public Long getId()
  {
  return id;
  }

  public void setId(Long id)
  {
  this.id = id;
  }

  public String getNews()
  {
  return news;
  }

  public void setNews(String news)
  {
  this.news = news;
  }

  public String getAuthor()
  {
  return author;
  }

  public void setAuthor(String author)
  {
  this.author = author;
  }

  public boolean isLive()
  {
  return live;
  }

  public void setLive(boolean live)
  {
  this.live = live;
  this.liveString = Utility.convertBooleanToString(live);
  }

  public String getDate()
  {
  return date;
  }

  public void setDate(String date)
  {
  this.date = date;
  }

  public String getPriority()
  {
  return priority;
  }

  public void setPriority(String priority)
  {
  this.priority = priority;
  }

  public String getLiveString()
  {
  return liveString;
  }

}
