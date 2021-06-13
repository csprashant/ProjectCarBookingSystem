package carbookingsystem.bean;

 
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO )
	
	private Integer id;
	//@NotBlank(message="*Make sure you have not entered EmailID")
	//@Email(message="*Not a Valid email address")
	private String email;
	//@NotBlank(message="* Please enter password ")
	private String  password;
	//@NotBlank(message="* Please enter Name ")
	private String name;
	
	private int type;
	@Column(name = "created", columnDefinition = "timestamp default current_timestamp")
	//@NotBlank
	private Timestamp created;
	private Timestamp updated;
	//-------------------------
/*	@OneToMany(targetEntity=Reservationn.class,cascade=CascadeType.ALL,orphanRemoval=true)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name="userId", referencedColumnName="id")
	@Cascade(org.hibernate.annotations.CascadeType.REMOVE)
	private Set<Reservationn> reservationns; */
	
	

}
