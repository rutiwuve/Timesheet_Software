package manager;

import java.util.List;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.Reply;
import entity.User;

public class EmployeeManager {

	private final EntityManager entityManager;

	public EmployeeManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 

	}

	// function that update employee

	public void update(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.merge(employee);
		entityManager.getTransaction().commit();
	}

	public Reply UpdateEmployee(int id, String firstname, String lastname, String email, String phone, int user) {
		Employee employee = new Employee(id, firstname, lastname, email, phone, user);
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(employee);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	// function that create employee

	public void create(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}

	public Employee CreateEmployee(String firstname, String lastname, String email, String phone,
			String username, String password) {
		
		User user = new User(username,password,"employee");	
		user = ManagerHelper.getUserManager().CreateUser(user);		
		Employee employee = new Employee();
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setUser(user.getId());
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(employee);
			entityManager.getTransaction().commit();
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	// function that delete employee


	public void delete(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.remove(employee);
		entityManager.getTransaction().commit();
	}

	public Reply DeleteEmployee(int id) {
		try {
			Employee employee = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(employee.getUser());
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}

	}
	// function that get employee

	public Employee get(Integer id) {
		return entityManager.find(Employee.class, id);
	}
	
	// function that get employee by name

	
	public List<Employee> getByName(String name) {
		String sql = "select * from employee where firstname like '";
		return (List) entityManager.createNativeQuery(sql + name + "'", Employee.class).getResultList();
	}
	
	// function that get all employees

	 public List<Employee>getAllEmployee(){
			String all ="SELECT employee.id, employee.firstname, employee.lastname, "
					+ "employee.email, employee.phone, employee.user FROM projectmanager.employee";
		return (List)entityManager.
				createNativeQuery(all, Employee.class).getResultList();
		
	}
		// function that get employee by userId

		public Employee getEmployeeByUser(int user){
			String sql = "SELECT * FROM employee where user =" +user;
			return (Employee)entityManager.
					createNativeQuery(sql, Employee.class).getSingleResult();
			
		}

}
