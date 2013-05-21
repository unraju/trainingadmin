package cricket.hibernate.bf.team;

import java.util.HashMap;
import java.util.Map;

public enum Skill
{
  BM(1L), BL(2L), WK(4L);

  private static Map<Long, Skill> idToEnum = new HashMap<Long, Skill>();

  private Long id;

  private String type;

  private String discription;

  Skill(Long id)
  {
    this.id = id;
  }

  /**
   * 
   * @return Long
   */
  public Long getId()
  {
    return id;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getDiscription()
  {
    return discription;
  }

  public void setDiscription(String discription)
  {
    this.discription = discription;
  }

}
