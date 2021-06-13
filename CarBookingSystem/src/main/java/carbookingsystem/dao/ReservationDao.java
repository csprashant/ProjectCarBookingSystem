package carbookingsystem.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import carbookingsystem.bean.Reservationn;


@Component
public class ReservationDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	// method for creating reservation
	@Transactional
	public void createReservation(Reservationn reservationn) throws SQLException {
		try {
			hibernateTemplate.save(reservationn);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	// method for loading all reservation details

	public List<Reservationn> ListAllReservation() {
		List<Reservationn> listReservationn = hibernateTemplate.loadAll(Reservationn.class);
		return listReservationn;
	}
}
