package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;
import entity.Employeeproject;
import entity.HourReport;
import entity.Reply;
import manager.ManagerHelper;

@Path("EmployreeProject")
public class EmployreeProjectService {

	

	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectmanager");

	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	

	// function that create employeeproject
	@GET
	@Path("createEmployreeProject")
	public Employeeproject createEmployeeProject(@QueryParam("employee")int employee, @QueryParam("project")int project) {
		return  ManagerHelper.EmployeeprojectManager().createEmployeeProject( employee, project);
		
	}
	
	// function that update employeeproject

	@GET
	@Path("UpdateEmployreeProject")
	public Reply UpdateEmployeeProject(@QueryParam("id")int id, @QueryParam("employee")int employee, @QueryParam("project")int project) {
		return ManagerHelper.EmployeeprojectManager().updateEmployeeProject(id, employee, project);
	}
	// function that delete employeeproject

	
	@GET
	@Path("DeleteEmployreeProject")
	public Reply DeleteEmployeeProject(@QueryParam("id")int id) {
		return ManagerHelper.EmployeeprojectManager().deleteEmployeeProject(id);
	}
	
	// function that get employeeproject

	@GET
	@Path("get")
	public Employeeproject get(@QueryParam("id")int id) {
		return ManagerHelper.EmployeeprojectManager().get(id);

	}
	
	// function that all employeeproject

	@GET
	@Path("getAllEP")
	public List<Employeeproject> getAllEP() {
		return ManagerHelper.EmployeeprojectManager().getAllEP();
	}
	
	// function that get employeeproject by id

	@GET
	@Path("getProjectByemployeeId")
	public List<Employeeproject> getProjectByemployeeId(@QueryParam("employee") int employee) {
		return(List<Employeeproject>) ManagerHelper.EmployeeprojectManager().getProjectByemployeeId(employee);
	}
	
	
	
}
