package carbookingsystem.vo;

import javax.persistence.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor

public class ReservationnVo {
	
	private String id;
	private String userId;
	private String userName;
	private String fromDate;
	private String toDate;
	private String vehicleId;
	private String status;
	private String created;
	private String updated;
	
	}

