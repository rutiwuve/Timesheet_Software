package manager;

import java.util.List;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Project;
import entity.Reply;

public class ProjectManager {

	private final EntityManager entityManager;

	public ProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 

	}
	
	// function that update project

	public void update(Project project) {
		entityManager.getTransaction().begin();
		entityManager.merge(project);
		entityManager.getTransaction().commit();
	}

	public Reply UpdateProject(int id, String projectname, int customer, String customerprojectmanager,
			String projectmanageremail, String projectmanagerephone, String startdate, String enddate) {
		Customer customer1 = ManagerHelper.getCustomerManager().get(customer);
		Project project = new Project(id,projectname, customer1, customerprojectmanager, projectmanageremail,
				projectmanagerephone, startdate, enddate);
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(project);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	// function that create project

	public void create(Project project) {
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
	}

	public Project CreateProject(String projectname, int customer, String customerprojectmanager,
			String projectmanageremail, String projectmanagerephone, String startdate, String enddate) {
		Customer customer1 = ManagerHelper.getCustomerManager().get(customer);
		Project project = new Project(projectname, customer1, customerprojectmanager, projectmanageremail,
				projectmanagerephone, startdate, enddate);
		create(project);
		return project;

	}
	// function that delete project

	public void delete(Project project) {
		entityManager.getTransaction().begin();
		entityManager.remove(project);
		entityManager.getTransaction().commit();
	}

	public Reply DeleteProject(int id) {
		try {
			Project project = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(project);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}

	}
	// function that get project

	public Project get(Integer id) {
		return entityManager.find(Project.class, id);
	}
	// function that get project by name

	public List<Project> getByName(String name) {
		String sql = "select * from project where projectname like '";
		return (List) entityManager.createNativeQuery(sql + name + "'", Project.class).getResultList();
	}
	// function that get all project

	public List<Project> getAllProject() {
		String all = "SELECT  p.id, p.projectname, c.companyname, "
				+ "date_format(startdate, '%y-%m-%d')startdate,"
				+ " date_format(enddate, '%y-%m-%d')enddate  from project p"
				+ " inner join customer c on  c.id =  p.customer";
		return (List) entityManager.createNativeQuery(all, Project.class).getResultList();
	}
	
	// function that get projects befor thay end

	public List<Project> GetProjectBeforEnd() {
		String weekly = "select p.id,p.projectname,p.customer,p.customerprojectmanager, "
				+ " p.projectmanageremail,p.projectmanagerephone, "
				+ " DATE_FORMAT(startdate,'%Y-%m-%d')startdate, "
				+ " DATE_FORMAT(enddate,'%Y-%m-%d')enddate FROM projectmanager.project p "
				+ " where enddate between now() and date_add(now(), interval 200 day) "
				+ " order by p.enddate desc ";

		return (List<Project>) entityManager.createNativeQuery(weekly, Project.class).getResultList();

	}
	
	// function that get active projects

	public List<Project> GetActiveProject() {
		
	String activprogect= "select p.id,p.projectname,p.customer, "
			+ " p.customerprojectmanager,p.projectmanageremail, "
			+ " p.projectmanagerephone, DATE_FORMAT(startdate,'%Y-%m-%d')startdate, "
			+ " DATE_FORMAT(enddate,'%Y-%m-%d')enddate FROM projectmanager.project p "
			+ " inner join customer c on c.id=p.customer "
			+ " inner join user u on u.id=c.user where(current_date() "
			+ " between p.startdate and p.enddate) order by p.enddate ";
		return (List<Project>) entityManager.createNativeQuery(activprogect, Project.class).getResultList();

	}
	
	// function that get customers active projects

	public List<Project> GetCustomerActiveProject(int customerId) {
		
		String sql= "select * from projectmanager.customer c"
				+ "where c.user ="+customerId;
		Customer c =ManagerHelper.getCustomerManager().get(customerId);
		
		
		String activprogect= "select p.id,p.projectname,p.customer,p.customerprojectmanager, "
				+ " p.projectmanageremail,p.projectmanagerephone, "
				+ "  DATE_FORMAT(startdate,'%Y-%m-%d')startdate, "
				+ " DATE_FORMAT(enddate,'%Y-%m-%d')enddate FROM projectmanager.project p "
				+ " inner join customer c on c.id=p.customer "
				+ " inner join user u on u.id=c.user "
				+ " where(current_date() between p.startdate and p.enddate) and u.id = "+c.getUser()+" " ;
			return (List<Project>) entityManager.createNativeQuery(activprogect, Project.class).getResultList();

		}
	
	// function that get customer by project

	public List<Project> getCustomerByProject(int id) {
		String activprogect= "SELECT * FROM projectmanager.project where customer = "+id;
			return (List<Project>) entityManager.createNativeQuery(activprogect, Project.class).getResultList();

		}
	

}
