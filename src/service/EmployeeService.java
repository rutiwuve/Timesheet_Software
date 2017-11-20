package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;
import entity.Employee;
import entity.Reply;
import manager.EmployeeManager;
import manager.ManagerHelper;


@Path("/employee")
public class EmployeeService {
	
	// function that get employee
	
	@GET
	@Path("get")
	public Employee getEmployee(@QueryParam("id") int id){
		return ManagerHelper.getEmployeeManager().get(id);
		
	}
	// function that get employee by name

	
	@GET
	@Path("getByName")
	public List<Employee>getByName(@QueryParam("firstname")String name){
		return ManagerHelper.getEmployeeManager().getByName(name);
	}
	
	// function that create employee
	
	@GET
	@Path("CreateEmployee")
	public Employee CreateEmployee(@QueryParam("firstname")String firstname,
			@QueryParam("lastname") String lastname,@QueryParam("email") String email, 
			@QueryParam("phone") String phone,@QueryParam("username")String username ,@QueryParam("password") String password) {
		return ManagerHelper.getEmployeeManager().CreateEmployee(firstname, lastname, email, phone, username, password);
	}
	
	// function that update employee

	@GET
	@Path("UpdateEmployee")
	public Reply UpdateEmployee(@QueryParam("id")int id, @QueryParam("firstname")String firstname,
			@QueryParam("lastname") String lastname,@QueryParam("email") String email, 
			@QueryParam("phone") String phone,@QueryParam("user") int user) {
		return ManagerHelper.getEmployeeManager().UpdateEmployee(id, firstname, lastname, email, phone, user);
	}
	
	// function that delete employee
	
	@GET
	@Path("DeleteEmployee")
	public Reply DeleteEmployee(@QueryParam("id")int id) {
		return ManagerHelper.getEmployeeManager().DeleteEmployee(id);
	}
	
	// function that get all employees

	
	@GET
	@Path("getAllEmployee")
	public List<Employee> DeleteEmployee() {
		return ManagerHelper.getEmployeeManager().getAllEmployee();
	}
	
	// function that get employee by userId
	
	@GET
	@Path("getEmployeeByUser")
	public Employee getEmployeeByUser(@QueryParam("user")int user) {
		return ManagerHelper.getEmployeeManager().getEmployeeByUser(user);
	}
}