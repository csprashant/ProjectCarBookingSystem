package carbookingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import carbookingsystem.bean.User;

@Component
public class UserDao {
	private static final String All_Details_Query="SELECT reservationn.id,NAME,fromdate,todate,vname,vnumber,STATUS FROM `user`,`vehicle`,`reservationn` WHERE user.id=reservationn.userid AND vehicle.id=reservationn.vehicleid AND user.id=?";
	@Autowired
	private HibernateTemplate hibernateTemplate;
	// method to register normal user
	@Transactional
	public void createUser(User user) throws Exception {
		user.setUpdated(new Timestamp(new Date().getTime()));
		user.setType(2);
		try{
		this.hibernateTemplate.save(user);
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
			throw ee;
		}
		
		
	}
	// method to perform login operation
	public User checkLogin(String email, String password) throws Exception {
		System.out.println("Inside method checkLogoin");
		List list = null;
		Object[] obj;
		User user = null;
		obj = new Object[2];
		obj[0] = email;
		obj[1] = password;
		list = hibernateTemplate.find("from User u where u.email= ? and u.password=?", obj);
		if (!list.isEmpty()) {
			user = (User) list.get(0);
			return user;
		} else
			return user;
	}
	// method for loading all users
	public List<User> ListAllUser() throws HibernateException{
		List<User> listUser = hibernateTemplate.loadAll(User.class);
		return listUser;
	}

	// method for loading single user
	@Transactional
	public User getUser(int userID) throws HibernateException{
		User user = hibernateTemplate.get(User.class, userID);
		return user;
	}
	// method for udpate user information
	@Transactional
	public void updateUser(User user) throws HibernateException{
		hibernateTemplate.update(user);
	}
	// deleting vehicle details
	@Transactional
	public void deleteUser(int userId) throws HibernateException{
		User u = hibernateTemplate.load(User.class, userId);
		if (u != null)
			hibernateTemplate.delete(u);
		else
			throw new RuntimeException("element not found");
	}
	public List<String []> getAllDetailsByUserID(int userId) throws Exception
	{		List<String []> list=new ArrayList<String []>();
	
	Connection conn = Utility.getConnection();
	PreparedStatement ps=conn.prepareStatement(All_Details_Query);
	ps.setInt(1,userId);
	ResultSet rs=ps.executeQuery();
	System.out.println(rs.next());
	String[] obj; 
	while(rs.next())
	{	
		obj= new String[7];
		obj[0]=rs.getString(1);
		obj[1]=rs.getString(2);
		obj[2]=rs.getString(3);
		obj[3]=rs.getString(4);
		obj[4]=rs.getString(5);
		obj[5]=rs.getString(6);
		obj[6]=rs.getString(7);
		list.add(obj);
	}
	
	return list;
	}
	
}
