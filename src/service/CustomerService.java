package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.sun.org.apache.regexp.internal.recompile;

import entity.Customer;
import entity.Reply;
import entity.User;
import manager.CustomerManager;
import manager.ManagerHelper;

@Path("/customer")
public class CustomerService {

	// function that get customer

	@GET
	@Path("get")
	public Customer getCustomer(@QueryParam("id") int id) {
		return ManagerHelper.getCustomerManager().get(id);

	}
	
	// function that get customer by name

	@GET
	@Path("getByName")
	public List<Customer> getByName(@QueryParam("companyname") String companyname) {
		return ManagerHelper.getCustomerManager().getByName(companyname);
	}
	
	// function that active customers

	@GET
	@Path("getcustomerbystatus")

	public List<Customer> getAllCustomerByStatus(@QueryParam("active") boolean active) {
		if (active) {
			return ManagerHelper.getCustomerManager().getActiveCustomer();
		} else {

			return ManagerHelper.getCustomerManager().getAllCustomerByStatus();
		}

	}

	// function that create customer

	@GET
	@Path("CreateCustomer")
	public Customer createCustomer(@QueryParam("companyname") String companyname,
			@QueryParam("companynumber") String companynumber, @QueryParam("contactname") String contactname,
			@QueryParam("email") String email, @QueryParam("phone") String phone, @QueryParam("username")String username ,@QueryParam("password") String password) {
		return ManagerHelper.getCustomerManager().createCustomer(companyname, companynumber, contactname, email, phone, username,password );
	}

	// function that update customer

	@GET
	@Path("UpdateCustomer")
	public Reply UpdateCustomer(@QueryParam("id") int id, @QueryParam("companyname") String companyname,
			@QueryParam("companynumber") String companynumber, @QueryParam("contactname") String contactname,
			@QueryParam("email") String email, @QueryParam("phone") String phone, @QueryParam("user") int user) {
		return ManagerHelper.getCustomerManager().UpdateCustomer(id, companyname, companynumber, contactname, email,
				phone, user);
	}
	
	// function that delete customer

	@GET
	@Path("DeleteCustomer")
	public Reply deleteCustomer(@QueryParam("id") int id) {
		return ManagerHelper.getCustomerManager().deleteCustomer(id);
	}

	// function that get big customers

	@GET
	@Path("getBigCustomer")
	public List<Customer> getBigCustomer() {
		return ManagerHelper.getCustomerManager().getBigCustomer();
	}
	
	// function that get all the customers

	@GET
	@Path("getAllCustomer")
	public List<Customer> getAllCustomer() {
		return ManagerHelper.getCustomerManager().getAllCustomer();
	}
	
	// function that get customers by userId

	
	@GET
	@Path("getCustomerByUser")
	public Customer getCustomerByUser(@QueryParam("user")int user) {
		return ManagerHelper.getCustomerManager().getCustomerByUser(user);
	}
	

}