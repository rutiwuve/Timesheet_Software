package service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.HourReport;
import entity.Reply;
import manager.HourReportManager;
import manager.ManagerHelper;

@Path("HourReport")
public class HourReportService {

	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectmanager");

	private static EntityManager entityManager = entityManagerFactory.createEntityManager();

	// function that get hourreport

	@GET
	@Path("get")
	public HourReport getHourReport(@QueryParam("id") int id) {
		return ManagerHelper.getHourReportManager().get(id);

	}
	
	// function that get hourreport by hour
	@GET
	@Path("getByhour")
	public List<HourReport> getByHour(@QueryParam("Hour") String name) {
		return ManagerHelper.getHourReportManager().getByhour(name);
	}
	
	// function that get all hourreport
	@GET
	@Path("getAllHourReports")
	public List<HourReport> getAllHourReports() {
		return ManagerHelper.getHourReportManager().getAllHourReports();

	}
	
	 // function that get  list of hourreport
	@GET
	@Path("getHourReports")
	public List<HourReport> getHourReports(@QueryParam("yearAndMonth") String yearAndMonth,
			@QueryParam("employee") int employee, @QueryParam("project") int project,
			@QueryParam("customer") int customer) {
		return ManagerHelper.getHourReportManager().getHourReport(yearAndMonth, employee, project, customer);

	}

	// function that create hourreport

	@GET
	@Path("createHourReport")
	public HourReport createHourReport(@QueryParam("employee") int employee, @QueryParam("project") int project,
			@QueryParam("startdate") String startdate, @QueryParam("enddate") String enddate,
			@QueryParam("description") String description, @QueryParam("date") String date) {
		return ManagerHelper.getHourReportManager().createHourReport(employee, project, date, startdate, enddate,
				description);

	}

	// function that update hourreport

	@GET
	@Path("UpdateHourReport")
	public Reply Update(@QueryParam("id") int id, @QueryParam("employee") int employee,
			@QueryParam("project") int project, @QueryParam("startdate") String startdate,
			@QueryParam("enddate") String enddate, @QueryParam("description") String description) {
		return ManagerHelper.getHourReportManager().update(id, employee, project, startdate, enddate, description);
	}

	// function that delete hourreport

	@GET
	@Path("DeleteHourReport")
	public Reply Delete(@QueryParam("id") int id) {
		return ManagerHelper.getHourReportManager().delete(id);
	}
	
	// function that get hourreport for 7 lest days

	@GET
	@Path("GetWeeklyReport")
	public List<HourReport> GetWeeklyReport(@QueryParam("id") int id) {
		return ManagerHelper.getHourReportManager().GetWeeklyReport(id);
	}
	
	// function that get employee hourreport

	@GET
	@Path("hourReportByEmployee")
	public List<HourReport> gethourReportByEmployee(@QueryParam("employee") int employee) {
		return ManagerHelper.getHourReportManager().gethourReportByEmployee(employee);
	}

	// function that get customer hourreport

	@GET
	@Path("hourReportByCustomer")
	public List<HourReport> gethoueReportByCustomer(@QueryParam("customer") int customer) {
		return ManagerHelper.getHourReportManager().gethourReportByCustomer(customer);
	}

	// function that get hourreport by year and month

	@GET
	@Path("getHourReportByYearAndMonth")
	public List<HourReport> getHourReportByYearAndMonth(@QueryParam("yaerAndMonth") String yaerAndMonth,
			@QueryParam("employeeId") int employeeId, @QueryParam("projectId") int projectId,
			@QueryParam("customerId") int customerId) {
		return ManagerHelper.getHourReportManager().getHourReportByYearAndMonth(yaerAndMonth, employeeId, projectId,
				customerId);
	}
}