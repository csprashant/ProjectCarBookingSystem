package carbookingsystem.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;


@Data
public class ReservationnDTO {
	private int id;
	private  Integer userId;
	private Date fromDate;
	private String userName;
	private Date toDate;
	private Integer vehicleId;
	private boolean status;
	private Timestamp created;
	private Timestamp updated;
	
	}


