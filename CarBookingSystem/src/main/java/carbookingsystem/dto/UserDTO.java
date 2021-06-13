package carbookingsystem.dto;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class UserDTO  {
	
	private Integer id;
	private String email;
	private String  password;
	private String name;
	private int type;
	private Timestamp created;
	private Timestamp updated;
	private VehicleDTO vehicle;
	private ReservationnDTO reservation;
}
