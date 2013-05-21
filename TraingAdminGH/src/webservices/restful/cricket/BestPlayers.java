package webservices.restful.cricket;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import webservices.jaxb.WSPlayerTO;
@XmlRootElement
public class BestPlayers implements Serializable
{
	private List<WSPlayerTO> players;
    
    private String errorMessage;
    
    @XmlElement
    public String getErrorMessage()
    {
    return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
    this.errorMessage = errorMessage;
    }
    
    @XmlElement(name="BestPlayer")
    public List<WSPlayerTO> getBestPlayers()
    {
    return players;
    }

    public void setBestPlayers(List<WSPlayerTO> bestPlayers)
    {
    this.players = bestPlayers;
    }

    public BestPlayers()
    {
    super();
    // TODO Auto-generated constructor stub
    }
    
    
    public BestPlayers(String errorMsg)
    {
    	super();
    	this.errorMessage = errorMsg;
    	
    }

	@Override
	public String toString() {
		return "ResponseTO [bestPlayers=" + players + ", errorMessage=" + errorMessage + "]";
	}
    
    
}
