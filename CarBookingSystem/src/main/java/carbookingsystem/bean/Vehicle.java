package carbookingsystem.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Vehicle {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//@NotBlank(message="*Make sure you have not entered Vehicle Name")
	private String vName;
	//@NotBlank(message="*Make sure you have not entered Vehicle color")
	private String vColor;
	//@NotBlank(message="*Make sure you have not entered vehicle number")
	private String vNumber;
	@Column(name = "created", columnDefinition = "timestamp default current_timestamp")
	private java.sql.Timestamp created;
	private java.sql.Timestamp updated;
//------------------------------------
	/*@OneToMany(targetEntity=Reservationn.class,cascade=CascadeType.ALL,orphanRemoval=true)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name="userId", referencedColumnName="id")
	@Cascade(org.hibernate.annotations.CascadeType.REMOVE)
	private Set<Reservationn> reservationns; */
	

	
	

}
