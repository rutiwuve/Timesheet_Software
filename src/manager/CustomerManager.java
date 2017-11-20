package manager;

import java.util.List;
import javax.persistence.EntityManager;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.openjpa.persistence.EntityManagerImpl;
import entity.Customer;
import entity.HourReport;
import entity.Reply;
import entity.User;
import sun.security.util.Password;

public class CustomerManager {

	private final EntityManager entityManager;

	
	public CustomerManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 

	}
// function that update customer

	public void update(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
	}

	
	public Reply UpdateCustomer(int id, String companyname, String companynumber, String contactname, String email,
			String phone, int user) {
		Customer customer = new Customer(id, companyname, companynumber, contactname, email, phone, user);
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(customer);
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

	// function that create customer
	
public void create(Customer customer) {
		
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();

	}	

	public Customer createCustomer(String companyname, String companynumber, String contactname, String email,
			String phone, String username , String password) {
		
		User user = new User(username,password,"customer");	
		user = ManagerHelper.getUserManager().CreateUser(user);		
		Customer customer = new Customer();
		customer.setCompanyname(companyname);
		customer.setCompanynumber(companynumber);
		customer.setContactname(contactname);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setUser(user.getId());
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(customer);
			entityManager.getTransaction().commit();	
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	// function that delete customer
	

	public void delete(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.remove(customer);
		entityManager.getTransaction().commit();
	}

	public Reply deleteCustomer(int id) {
		try {
			Customer customer = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(customer.getUser());
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}

	}

	// function that get customer

	public Customer get(Integer id) {
		return entityManager.find(Customer.class, id);
	}

	// function that get customer by name

	public List<Customer> getByName(String name) {
		String sql = "select * from customer where companyname like '";
		return (List<Customer>) entityManager.createNativeQuery(sql + name + "'", Customer.class).getResultList();
	}
	// function that get all the customers

	public List<Customer> getAllCustomer() {
		String all = "SELECT * FROM projectmanager.customer"; 
		return (List<Customer>) entityManager.createNativeQuery(all, Customer.class).getResultList();

	}

	// function that active customers 

	public List<Customer> getAllCustomerByStatus() {
		String all = "select c.id, c.companyname,(select count(p.id) from projectmanager.project p"
				+ " where p.customer = c.id and(current_date() between p.startdate and p.enddate))"
				+ " >0  as 'isactive' from projectmanager.customer c";
		return (List<Customer>) entityManager.createNativeQuery(all, Customer.class).getResultList();

	}

	// function that active customers
	
	public List<Customer> getActiveCustomer() {
		String active = " select c.id, c.companyname, true as 'isactive' from projectmanager.customer c"
				+ " where( select count(p.id) from projectmanager.project p" + " where p.customer = c.id "
				+ " and (current_date() between p.startdate and p.enddate)>0)";
		return (List<Customer>) entityManager.createNativeQuery(active, Customer.class).getResultList();

	}
	
	// function that get big customers

	public List<Customer> getBigCustomer() {
		String bigCustomer = "SELECT c.id,c.companyname,c.companynumber,c.contactname,c.email,c.phone, count(p.customer)  as 'projectnum'  from "
				+ " projectmanager.customer c inner join projectmanager.project p on c.id = p.customer"
				+ " where (select count( p.customer)  from projectmanager.project)" + " group  by c.id "
				+ " HAVING count(p.customer) >= 2 ";
		System.out.println(bigCustomer);
		return (List<Customer>) entityManager.createNativeQuery(bigCustomer, Customer.class).getResultList();
	}
	
	// function that get customers by userId

	public Customer getCustomerByUser(int user){
		String sql = "SELECT * FROM customer where user =" +user;
		return (Customer)entityManager.
				createNativeQuery(sql, Customer.class).getSingleResult();
		
	}
	
}
