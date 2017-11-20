package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerHelper {
	public static EntityManagerFactory entityManagerFactory =
			Persistence.createEntityManagerFactory("projectmanager");
	
	

	public static EmployeeManager getEmployeeManager() {
		return new EmployeeManager(entityManagerFactory.createEntityManager());
	}
	public static CustomerManager getCustomerManager() {
		return new CustomerManager(entityManagerFactory.createEntityManager());
	}
	public static ProjectManager getProjectManager() {
		return new ProjectManager(entityManagerFactory.createEntityManager());
	}
	
	public static HourReportManager getHourReportManager() {
		return new HourReportManager(entityManagerFactory.createEntityManager());
	}
	public static UserManager getUserManager() {
		return new UserManager(entityManagerFactory.createEntityManager());
	}
	public static EmployeeprojectManager EmployeeprojectManager() {
		return new EmployeeprojectManager(entityManagerFactory.createEntityManager());
	}
}
