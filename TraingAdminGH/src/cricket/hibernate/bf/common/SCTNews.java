package cricket.hibernate.bf.common;

import java.io.Serializable;
import java.util.Date;

import common.util.DateUtil;

import cricket.struts.actionforms.common.SCTNewsForm;

public class SCTNews implements Serializable
{

  private Long id;

  private String news;

  public String author;

  public boolean live;

  public Date date;
  
  public Long  priority;
  
  public Long  seriesId;

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
  }

  public Date getDate()
  {
  return date;
  }

  public void setDate(Date date)
  {
  this.date = date;
  }

  public long getPriority()
  {
  return priority;
  }

  public void setPriority(long priority)
  {
  this.priority = priority;
  }

  public Long getSeriesId()
  {
  return seriesId;
  }

  public void setSeriesId(Long seriesId)
  {
  this.seriesId = seriesId;
  }

  public void setPriority(Long priority)
  {
  this.priority = priority;
  }

  public SCTNewsForm getActionForm()
  {
  SCTNewsForm sctNewsForm = new SCTNewsForm();
  sctNewsForm.setId(id);
  sctNewsForm.setNews(news);
  sctNewsForm.setLive(live);
  if(date != null)
  sctNewsForm.setDate(DateUtil.getStringFromDate(date, DateUtil.DD_MMM_YYYY_FORMAT));
  if(priority != null)
  sctNewsForm.setPriority(priority.toString());
  return sctNewsForm;
  }
}
