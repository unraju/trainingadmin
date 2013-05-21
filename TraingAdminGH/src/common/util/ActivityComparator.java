package common.util;

import java.util.Comparator;

public class ActivityComparator<Activity> implements Comparator<Activity>
{

  public int compare(Object arg0, Object arg1)
  {
    // parameter are of type Object, so we have to downcast it to Employee
    // objects

    int emp1Age = 0;//((Activity) arg0).getPriority().intValue();

    int emp2Age = 0;//((Activity) arg1).getPriority().intValue();

    if (emp1Age > emp2Age)

      return 1;

    else if (emp1Age < emp2Age)

      return -1;

    else

      return 0;
  }

}
