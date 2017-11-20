package service;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


import entity.User;
import manager.ManagerHelper;

@Path("/User")
public class UserService {

	// function that get user

	@GET
	@Path("get")
	public User get(@QueryParam("id") int id) {
		return ManagerHelper.getUserManager().get(id);

	}
	
	// function that get user login by password and  user name

	@GET
	@Path("login")
	public User login(@QueryParam("username") String username, @QueryParam("password") String password) {
	if( username.equals(username)&& password.equals(password)){
		return ManagerHelper.getUserManager().login(username, password);
		}else{
			return null;
		}

	}
	// function that update user

	@GET
	@Path("UpdateUser")
	public String UpdateUser(@QueryParam("username") String username,
			@QueryParam("password") String password,@QueryParam("type") String type) {
		return ManagerHelper.getUserManager().UpdateUser(username, password, type);
	}
	
	// function that delete user
	@GET
	@Path("DeleteUser")
	public String DeleteUser(@QueryParam("id") int id) {
		return ManagerHelper.getUserManager().DeleteUser(id);
	}
	
	// function that get user by email

	@GET
	@Path("getByEmail")
	public User getByEmail(@QueryParam("email") String email,@QueryParam("type") String type) {
		return ManagerHelper.getUserManager().getByEmail(email, type);

	}
	

}
