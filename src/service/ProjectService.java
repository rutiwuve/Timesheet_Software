package service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.http.util.EntityUtils;

import com.sun.org.apache.regexp.internal.recompile;

import entity.Project;
import entity.Reply;
import manager.ManagerHelper;
import manager.ProjectManager;

@Path("/Project")
public class ProjectService {
	
	// function that get project
	@GET
	@Path("get")
	public Project getProject(@QueryParam("id") int id) {
		return ManagerHelper.getProjectManager().get(id);

	}
	// function that get project by name

	@GET
	@Path("getByName")
	public List<Project> getByName(@QueryParam("projectname") String projectname) {
		return ManagerHelper.getProjectManager().getByName(projectname);
	}
	
	// function that get all project

	@GET
	@Path("getAllProject")
	public List<Project> getAllProject() {
		return ManagerHelper.getProjectManager().getAllProject();
	}
	
	// function that create project

	@GET
	@Path("CreateProject")
	public Project CreateProject(@QueryParam("projectname") String projectname, @QueryParam("customer") int customer,
			@QueryParam("customerprojectmanager") String customerprojectmanager,
			@QueryParam("projectmanageremail") String projectmanageremail,
			@QueryParam("projectmanagerephone") String projectmanagerephone, @QueryParam("startdate") String startdate,
			@QueryParam("enddate") String enddate) {
		return ManagerHelper.getProjectManager().CreateProject(projectname, customer, customerprojectmanager,
				projectmanageremail, projectmanagerephone, startdate, enddate);

	}

	// function that update project

	@GET
	@Path("UpdateProject")
	public Reply UpdateProject(@QueryParam("id") int id, @QueryParam("projectname") String projectname,
			@QueryParam("customer") int customer, @QueryParam("customerprojectmanager") String customerprojectmanager,
			@QueryParam("projectmanageremail") String projectmanageremail,
			@QueryParam("projectmanagerephone") String projectmanagerephone, @QueryParam("startdate") String startdate,
			@QueryParam("enddate") String enddate) {
		return ManagerHelper.getProjectManager().UpdateProject(id, projectname, customer, customerprojectmanager,
				projectmanageremail, projectmanagerephone, startdate, enddate);
	}

	// function that delete project

	@GET
	@Path("DeleteProject")
	public Reply DeleteProject(@QueryParam("id") int id) {
		return ManagerHelper.getProjectManager().DeleteProject(id);
	}

	// function that get projects befor thay end

	@GET
	@Path("GetProjectBeforEnd")
	public List<Project> GetProjectBeforEnd() {
		return ManagerHelper.getProjectManager().GetProjectBeforEnd();
	}
	
	// function that get active projects

	@GET
	@Path("GetActiveProject")
	public List<Project> GetActiveProject() {
		return ManagerHelper.getProjectManager().GetActiveProject();
	}
	
	// function that get customers active projects

	@GET
	@Path("GetCustomerActiveProject")
	public List<Project> GetCustomerActiveProject(@QueryParam("customerId") int customerId) {
		return ManagerHelper.getProjectManager().GetCustomerActiveProject(customerId);
	}
	
	// function that get customer by project

	@GET
	@Path("getCustomerByProject")
	public List<Project> getCustomerByProject(@QueryParam("customerId") int id) {
		return ManagerHelper.getProjectManager().getCustomerByProject(id);
	}

}