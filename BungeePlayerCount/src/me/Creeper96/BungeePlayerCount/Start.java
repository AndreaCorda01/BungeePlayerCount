package me.Creeper96.BungeePlayerCount;

 
import java.util.Map;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;


//try the minequery query of the servers using the method main in eclipse, you set the ip and the port and click run
//this is useful to check the right working of the server
 
public class Start extends Plugin implements Listener {
	
	public static ProxyServer ps;
	
	public static Minequery[] servers = new Minequery[2];

	
	 int slots = 0;
	 int online = 0;
	
	
	
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerListener(this, this);
        ps =  ProxyServer.getInstance();
       
                                    //minequery ip, //minequery port

       
       
    }
   
    public void onDisable() {
        // Nothing
    }
   
    
    @EventHandler
    public void pingEvent(ProxyPingEvent e) {
        
    	online = 0;
    	slots = 0;

        servers[0] = new Minequery("91.121.7.118", 25571);
    	servers[1] = new Minequery("91.121.7.118", 25572);
    	
    	for(Minequery query: servers)
    	{
    	    String result = query.connetti();
    		String[] array_query = result.split("/");
        	try{
        		int s_online = Integer.valueOf(array_query[0]).intValue();
        		int s_slots = Integer.valueOf(array_query[1]).intValue();
        		online += s_online;
        		slots += s_slots;
        	}catch(Exception ex){}
    	}
    	
    	e.setResponse(new ServerPing( ps.getProtocolVersion() , ps.getGameVersion(), e.getConnection().getListener().getMotd(),online , slots));
        
    
    }

public static void main(String[] args)
		{

	
    servers[0] = new Minequery("94.23.67.215", 25567);
	servers[1] = new Minequery("94.23.67.215", 25556);
	
	for(Minequery query: servers)
	{
	    String result = query.connetti();
	    System.out.println(result);
		String[] array_query = result.split("/");
    	try{
    		int s_online = Integer.valueOf(array_query[0]).intValue();
    		int s_slots = Integer.valueOf(array_query[1]).intValue();

    	}catch(Exception ex){}
	}
		}
}