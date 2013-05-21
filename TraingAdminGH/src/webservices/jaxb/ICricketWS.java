package webservices.jaxb;

import webservices.restful.cricket.BestPlayers;



public interface ICricketWS
{
  BestPlayers getBestPlayers(String seriesId);
  
  String getHelloMsg(String name);
}
