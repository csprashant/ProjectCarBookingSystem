package carbookingsystem.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import carbookingsystem.bean.Vehicle;

@Component
public class VehicleDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	// Adding vehicle details
	@Transactional
	public void createVehicle(Vehicle vehicle) {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		vehicle.setUpdated(ts);
		this.hibernateTemplate.saveOrUpdate(vehicle);
	}

	// Listing all vehicle details
	@Transactional
	public List<Vehicle> ListAllVehicle() {
		List<Vehicle> listVehicle = hibernateTemplate.loadAll(Vehicle.class);
		return listVehicle;
	}

	// deleting vehicle details
	@Transactional
	public void deleteVehicle(int vehicleId) {
		Vehicle v = hibernateTemplate.load(Vehicle.class, vehicleId);
		if (v != null)
			hibernateTemplate.delete(v);
		else
			throw new RuntimeException("element not found");
	}

	// getting single vehicle details
	@Transactional
	public Vehicle getVehicle(int vehicleId) {
		Vehicle vehicle = hibernateTemplate.get(Vehicle.class, vehicleId);
		return vehicle;
	}

	// method for updating vehicle details
	@Transactional
	public void updateVehicle(Vehicle vehicle) {
		hibernateTemplate.update(vehicle);
	}
}