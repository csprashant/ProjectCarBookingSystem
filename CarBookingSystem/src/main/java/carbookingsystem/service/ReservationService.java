package carbookingsystem.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carbookingsystem.bean.Reservationn;
import carbookingsystem.dao.ReservationDao;
import carbookingsystem.vo.ReservationnVo;

@Service
public class ReservationService {
	@Autowired
	private ReservationDao reservationDao;
	
	public boolean processReservation(ReservationnVo rvo) throws Exception {
		Reservationn reservationn = new Reservationn();
		BeanUtils.copyProperties(rvo, reservationn);
		reservationn.setId(0);
		reservationn.setUserName(rvo.getUserName());
		reservationn.setFromDate(new SimpleDateFormat("yyyy-mm-dd").parse(rvo.getFromDate()));
		reservationn.setToDate(new SimpleDateFormat("yyyy-mm-dd").parse(rvo.getToDate()));
		reservationn.setStatus(true);
		reservationn.setUserId(Integer.valueOf(rvo.getUserId()));
		reservationn.setVehicleId(Integer.valueOf(rvo.getVehicleId()));
		reservationn.setUpdated(new Timestamp(new Date().getTime()));
		System.out.println(reservationn);
		boolean res = false;
		try {reservationDao.createReservation(reservationn);
			res = true;
		}
		catch (Exception ee) {
			ee.printStackTrace();
			res = false;
		}
		return res;
	}
	public List<ReservationnVo> fetchAllReservationDetails() {
		List<Reservationn> listReservation = reservationDao.ListAllReservation();
		List<ReservationnVo> listVo = new ArrayList<ReservationnVo>();
		for (int i = 0; i < listReservation.size(); i++) {
			ReservationnVo reservationnvo = new ReservationnVo();
			reservationnvo.setId(listReservation.get(i).getId() + "");
			reservationnvo.setUserId(listReservation.get(i).getUserId() + "");
			reservationnvo.setUserName(listReservation.get(i).getUserName());
			reservationnvo.setVehicleId(listReservation.get(i).getVehicleId() + "");
			reservationnvo.setFromDate((listReservation.get(i).getFromDate() + "").substring(0, 10));
			System.out.println(reservationnvo.getFromDate().substring(0, 10));
			reservationnvo.setToDate((listReservation.get(i).getToDate() + "").substring(0, 10));
			reservationnvo.setStatus(listReservation.get(i).isStatus() + "");
			reservationnvo.setUpdated(listReservation.get(i).getUpdated() + "");
			reservationnvo.setCreated(listReservation.get(i).getCreated() + "");
			listVo.add(reservationnvo);
		}
		return listVo;
	}

}
