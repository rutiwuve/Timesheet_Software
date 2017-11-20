package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.Employeeproject;
import entity.Project;
import entity.Reply;

public class EmployeeprojectManager {

	
	private final EntityManager entityManager;

	public  EmployeeprojectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 

	}

// function that update employeeproject
	public void update( Employeeproject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.merge(employeeProject);
		entityManager.getTransaction().commit();
	}
	
	public Reply updateEmployeeProject(int id ,int employee, int project) {
		Project project1 = ManagerHelper.getProjectManager().get(project);
		Employee employee1 = ManagerHelper.getEmployeeManager().get(employee);
		Employeeproject employeeProject = new Employeeproject(id, employee1, project1);
		try {
		entityManager.getTransaction().begin();
		entityManager.merge(employeeProject);
		entityManager.getTransaction().commit();	
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	// function that create employeeproject

	public void create( Employeeproject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.persist(employeeProject);
		entityManager.getTransaction().commit();
	}
	
	public  Employeeproject createEmployeeProject( int employee, int project) {	
	Project project1 = ManagerHelper.getProjectManager().get(project);
	Employee employee1 = ManagerHelper.getEmployeeManager().get(employee);
		
		Employeeproject employeeProject = new Employeeproject(employee1, project1);
		try {
		entityManager.getTransaction().begin();
		entityManager.persist(employeeProject);
		entityManager.getTransaction().commit();
		
		return employeeProject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	// function that delete employeeproject

	public void delete(Employeeproject  employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.remove(employeeProject);
		entityManager.getTransaction().commit();
	}
	public Reply deleteEmployeeProject(int id) {
		try {
			Employeeproject employeeProject = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(employeeProject);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}

	}

	// function that get employeeproject

	public Employeeproject get(Integer id) {
		return entityManager.find( Employeeproject.class, id);
	}
	
	// function that all employeeproject

	public List<Employeeproject> getAllEP() {
		String all = "SELECT * FROM projectmanager.employeeproject"; 
		return (List<Employeeproject>) entityManager.createNativeQuery(all, Employeeproject.class).getResultList();

	}
	
	// function that get employeeproject by id

	public List<Employeeproject> getProjectByemployeeId(int id){
		String ProjectByemployee = "SELECT * FROM employeeproject where employee =" +id;
		return (List)entityManager.createNativeQuery(ProjectByemployee, Employeeproject.class).getResultList();
		
	}
	
}
