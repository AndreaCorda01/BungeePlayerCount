package me.Creeper96.BungeePlayerCount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Minequery {
 String ip = ""; //your ip
 int port;  //your minequery port
//java socket client example


public Minequery(String ip, int port)
{
	this.ip = ip;
	this.port = port;
}

public  String connetti()  {

	String query = "";
	
try{	  
Socket s = new Socket();
  PrintWriter s_out = null;
  BufferedReader s_in = null;
  
     
      try
      {
      s.connect(new InetSocketAddress(this.ip, this.port));
  //    System.out.println("Connected");
           
      //writer for socket
          s_out = new PrintWriter( s.getOutputStream(), true);
          //reader for socket
          s_in = new BufferedReader(new InputStreamReader(s.getInputStream()));
      }
       
      //Host not found
      catch (UnknownHostException e)
      {
          System.err.println("Don't know about host : " + ip);
          return "0/0";
      }
      catch (ConnectException e)
      {
          return "0/0";
      }
       
      //Send message to server
  String message = "QUERY";
  s_out.println( message );
           
       
  
  try{
  //Get response from server
  String response;
  while ((response = s_in.readLine()) != null)
  {
	 query = query + response + "\n";
     
  }
  
	}catch(IOException e){e.printStackTrace();}
query = taglia(query);

}catch(Exception e){query= "0/0";}

return query;
}
public  String taglia(String query)
{
	//System.out.println(query);
	String[] riga = query.split("\n");
	
	//System.out.println(riga.length);
	
	/*for(String stampa:riga)
	{
		System.out.println(stampa);
	}*/
	
	String online = riga[1].replace("PLAYERCOUNT","").trim();
	
	String slots = riga[2].replace("MAXPLAYERS","").trim();
	
	return online + "/" + slots;
	//String online = riga[1].replace("PLAYERCOUNT","");
	//online = online.trim();
	//System.out.println(online);
	

}


}