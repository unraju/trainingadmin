package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import cricket.struts.actionforms.common.SeriesForm;
import cricket.struts.actionforms.common.SeriesTypeForm;

public class SeriesElement implements Serializable
{
  public static final String SERIES = "SERIES";

  public List<SeriesForm> seriesForms;

  public List<SeriesTypeForm> seriesTypeForms;

  public SeriesElement(List<SeriesForm> seriesForms, List<SeriesTypeForm> seriesTypeForms)
  {
    super();
    this.seriesForms = seriesForms;
    this.seriesTypeForms = seriesTypeForms;
  }

  public List<SeriesForm> getSeriesForms()
  {
    return seriesForms;
  }

  public List<SeriesTypeForm> getSeriesTypeForms()
  {
    return seriesTypeForms;
  }

}
