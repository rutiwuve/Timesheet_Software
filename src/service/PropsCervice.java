package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;



@Path("/props")
public class PropsCervice {
	
	
	@GET
	@Path("/getHours")
	public String getHours(){
		String hours = PropsHelper.get("hours");
		return hours;
	}
	
	@GET
	@Path("/setHours")
	public String setHours(@QueryParam("starttime")String starttime,@QueryParam("endtime")String endtime){
		String 	val = starttime+","+endtime;
		PropsHelper.set("hours",val);	
		return val;
		
	}
	@GET
	@Path("/getdays")
	public String getdays(){
		String days = PropsHelper.get("days");
		return days;
	}
	
	@GET
	@Path("/setdays")
	public String setdays(@QueryParam("days")String days){
		
		PropsHelper.set("days",days);	
		return days;
		
	}


}
