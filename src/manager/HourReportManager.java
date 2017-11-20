package manager;

import java.util.List;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.HourReport;
import entity.Project;
import entity.Reply;



public class  HourReportManager {

	private final EntityManager entityManager;

	public  HourReportManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 

	}

	// function that update hour report

	public void update( HourReport hourReport) {
		entityManager.getTransaction().begin();
		entityManager.merge(hourReport);
		entityManager.getTransaction().commit();
	}
	
	public Reply update( int id,
			int employee, int project, String startdate, String enddate, String description) {
		Employee employee1 = ManagerHelper.getEmployeeManager().get(employee);
		Project project1 = ManagerHelper.getProjectManager().get(project);	
		try {
		HourReport hourReport = new HourReport(id, employee1, project1, startdate, enddate, description);
		entityManager.getTransaction().begin();
		entityManager.merge(hourReport);
		entityManager.getTransaction().commit();	
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	// function that create hour report

	public void create( HourReport hourReport) {
		entityManager.getTransaction().begin();
		entityManager.persist(hourReport);
		entityManager.getTransaction().commit();
	}
	
	public HourReport createHourReport(int employee, int project, String date, String startdate,  String enddate, String description) {			
		
		String  datestartdate = date+" "+  startdate;
		String dateenddate = date+" "+  enddate;
		
		try {
			Employee employee1 = ManagerHelper.getEmployeeManager().get(employee);
			Project project1 = ManagerHelper.getProjectManager().get(project);
			HourReport hourReport = new HourReport( employee1, project1, datestartdate, dateenddate, description);
			
			create(hourReport);
			return hourReport;
		} catch (Exception e) {
			HourReport hr = new HourReport();
			return hr;
		}
			
		
	}
	// function that delete hour report

	
	public void delete( HourReport  hourReport) {
		entityManager.getTransaction().begin();
		entityManager.remove(hourReport);
		entityManager.getTransaction().commit();
	}
	public Reply delete(int id) {
		try {
			HourReport hourReport = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(hourReport);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}

	}

	// function that get hour report

	public  HourReport get(Integer id) {
		return entityManager.find( HourReport.class, id);
	}
	
	// function that get hour report by hour

	public List<HourReport> getByhour(String name) {
		String sql = "select * from HourReport where description like '";
		return (List)entityManager.
				createNativeQuery(sql+name+"'", HourReport.class).getResultList();
	}
	
	// function that get all hour report

	 public List<HourReport>getAllHourReports(){
			String all = "SELECT h.id,h.employee,h.project,h.description, "
					+ " substring(h.startdate,1,10) as 'date',"
					+ " DATE_FORMAT(h.startdate,'%H:%i')startdate, DATE_FORMAT(h.enddate,'%H:%i')"
					+ " enddate from projectmanager.hourreport h"
					+ " inner join employee on  employee.id = h.employee "
					+ " inner join user on  user.id = employee.Id order by date Desc";
 
		return (List)entityManager.
				createNativeQuery(all, HourReport.class).getResultList();
		
	}
	 
	 // function that get  list of hour report

	public List<HourReport>getHourReport(String yearAndMonth, int employeeId, 
			int projectId, int customerId){
		String sql ="SELECT e.id, e.firstname, e.lastname, p.projectname, c.companyname, h.startdate, h.enddate from hourreport h"
				+ " inner join employee e on e.id = h.employee"
				+ " inner join project p on p.id = h.project "
				+ "inner join customer c on c.id = p.customer"
				+ "  where e.id = "+ employeeId +" and p.id = "+ projectId +" and c.id = "+ customerId +" and h.startdate > '"+ yearAndMonth +"' and h.startdate < LAST_DAY ('"+ yearAndMonth +"-10')";
	return (List)entityManager.
			createNativeQuery(sql, HourReport.class).getResultList();
		
	}
	
	// function that get hour report for 7 lest days

	public List<HourReport> GetWeeklyReport( int id){
		String sql= "select * from projectmanager.employee e"
				+ "where e.user ="+id;
		Employee e =ManagerHelper.getEmployeeManager().get(id);
		
		String weekly= "select h.id,h.employee,h.project,h.description, "
				+ " substring(h.startdate,1,10) as 'date', "
				+ " DATE_FORMAT(h.startdate,'%H:%i')startdate, "
				+ " DATE_FORMAT(h.enddate,'%H:%i')enddate from "
				+ " projectmanager.hourreport h inner join employee e "
				+ " on e.id=h.employee inner join user u "
				+ " on u.id=e.user WHERE h.enddate > DATE_SUB(CURDATE() , INTERVAL 1 WEEK) "
				+ "  and u.id = "+e.getUser()+" order by h.enddate desc ";
		return (List)entityManager.createNativeQuery(weekly, HourReport.class).getResultList();
				
	}
	
	// function that get employee hour report

	public List<HourReport> gethourReportByEmployee(int employee) {
		
		String sql= "select * from projectmanager.employee e"
				+ "where e.user ="+employee;
		Employee e =ManagerHelper.getEmployeeManager().get(employee);
		
		
		
		String employeehour = "SELECT h.id,h.employee,h.project,h.description, "
				+ " substring(h.startdate,1,10) as 'date', "
				+ " DATE_FORMAT(h.startdate,'%H:%i')startdate, "
				+ " DATE_FORMAT(h.enddate,'%H:%i')enddate from projectmanager.hourreport h "
				+ " inner join employee on  employee.id = h.employee "
				+ " inner join user on  user.id = employee.Id and user.id ="+e.getUser()+ " "
				+ " order by h.enddate desc ";

		return (List) entityManager.createNativeQuery(employeehour , HourReport.class).getResultList();
	}

	// function that get customer hour report

	public List<HourReport> gethourReportByCustomer(int customer) {
		
		String sql= "select * from projectmanager.customer c"
				+ "where c.user ="+customer;
		Customer c =ManagerHelper.getCustomerManager().get(customer);
			
		String customerhour = "select h.id,h.employee,h.project, substring(h.startdate,1,10) as "
				+ " 'date', DATE_FORMAT(h.startdate,'%H:%i')startdate, "
				+ " DATE_FORMAT(h.enddate,'%H:%i')enddate, h.description from "
				+ " projectmanager.hourreport h "
				+ " inner join project p on p.id = h.project "
				+ " inner join customer c on c.id= p.customer "
				+ " where c.user="+c.getUser()+" order by h.date desc ";
		
		return (List<HourReport>) entityManager.createNativeQuery(customerhour, HourReport.class).getResultList();
	}

	// function that get hour report by year and month

	public List<HourReport> getHourReportByYearAndMonth(String yaerAndMonth, int employeeId, int customerId, int projectId ) {
		
		String yearAndMonth= "SELECT h.id, e.firstname,e.lastname, p.projectname, c.companyname, "
				+ "  h.description ,substring(h.startdate,1,10) as "
				+ " 'date',DATE_FORMAT(h.startdate,'%H:%i')startdate, "
				+ " DATE_FORMAT(h.enddate,'%H:%i')enddate from hourreport h "
				+ " inner join employee e on e.id = h.employee "
				+ " inner join project p on p.id = h.project"
				+ " inner join customer c on c.id = p.customer where "
				+ " month (h.startdate) = month ('2017-10-10') and "
				+ " year(h.startdate) = year('2017-10-10') "; 
				
				if(employeeId !=0){
					yearAndMonth +=" and h.employee = "+ employeeId;
				}
				if(projectId !=0){
					yearAndMonth +=" and h.project = "+ projectId;
				}
				if(customerId !=0){
					yearAndMonth +=" and p.customer = "+ customerId;
				}
		 return (List)entityManager.createNativeQuery(yearAndMonth, HourReport.class).getResultList();
		
	}
		
}
