package manager;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Reply;
import entity.User;

public class UserManager {

	private final EntityManager entityManager;

	public UserManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);

	}
	// function that update user

	public void update(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	// function that create user

	public void create(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}

	// function that delete user
	
	public void delete(User user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	// function that get user
	
	public User get(Integer id) {
		return entityManager.find(User.class, id);
	}

	// function that get user login by password and  user name
	
	public User login(String username, String password) {
		try {
			String userandpass = "select * from user where username like '" + username + "' and password like '"
					+ password + "'";
			return (User) entityManager.createNativeQuery(userandpass, User.class).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	// function that create user
	
	public User CreateUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return user;
	}

	// function that delete user
	
	public String DeleteUser(int id) {
		try {
			User user = get(id);	
			delete(user);
			return Reply.OK_STR;
		} catch (Exception e) {
			return Reply.FAIL_STR;
		}
		
	}

	// function that update user
	
	public String UpdateUser(String username, String password, String type) {
		User user = new User(username, password, type);
		update(user);
		return null;
	}
	
	// function that get user by email
	public User getByEmail(String email,String type) {
		try{
			String sql = "SELECT u.id,u.username,u.password,u.type FROM projectmanager."+type+" c inner join user u on u.id= c.user where email like '";
			return (User) entityManager.createNativeQuery(sql + email + "'", User.class).getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
}
