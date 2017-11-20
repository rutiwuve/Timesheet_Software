package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Employeeproject {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "employee")
	private Employee employee;	
	@ManyToOne
	@JoinColumn(name = "project")
	private Project project;
	
	public Employeeproject() {

	}
	public Employeeproject( int id, Employee employee, Project project ){
		this.id = id;
		this.employee = employee;
		this.project = project;
	}
	public Employeeproject(Employee employee, Project project) {
		this.employee = employee;
		this.project = project;
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
			
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
}
