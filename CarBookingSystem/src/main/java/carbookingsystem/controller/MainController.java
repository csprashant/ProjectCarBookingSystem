package carbookingsystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import carbookingsystem.bean.User;
import carbookingsystem.bean.Vehicle;
import carbookingsystem.dao.UserDao;
import carbookingsystem.dao.VehicleDao;
import carbookingsystem.service.ReservationService;
import carbookingsystem.vo.ReservationnVo;

@Controller
public class MainController {
	int type;
	User user1 = null;
	HttpSession session = null;
	@Autowired
	private UserDao userDao;
	@Autowired
	private VehicleDao vehicleDao;
	@Autowired
	private ReservationService service;

	@RequestMapping("/")
	public String home(@ModelAttribute("User") User user) {
		return "login";
	}

	@RequestMapping("/welcome")
	public String mapperWelcomePage() {
		return "welcome";
	}

	@RequestMapping("/welcomeuser")
	public String mapperWelcomeUserPage() {
		return "welcomeuser";
	}

	@PostMapping("/handle-login")
	public String handleLogin(@Valid @ModelAttribute("User") User user, BindingResult result,
			HttpServletRequest request) throws Exception {

		if (user.getEmail().length() == 0 || user.getPassword().length() == 0) {
			return "login";
		} else {
			user1 = userDao.checkLogin(user.getEmail(), user.getPassword());
			if (user1 != null) {
				session = request.getSession();
				session.setAttribute("user", user1);
				if (user1.getType() == 1) {
					return "welcome";
				} else {
					return "welcomeuser";
				}
			} else {
				request.setAttribute("msg", "you have enter wrong credentials ");
				return "landing";
			}
		}

	}

	@RequestMapping("/logout")
	public String logout(@ModelAttribute("User") User user, HttpServletRequest request) throws Exception {
		session = request.getSession(false);

		session.invalidate();
		return "login";

	}

	// mapper for adding user
	@RequestMapping(value = "/add-user")
	public String mapperAddUser(@ModelAttribute User user, Model m, HttpServletRequest request) throws Exception {
		session = request.getSession(false);
		user1 = (User) session.getAttribute("user");
		if (user1.getType() == 1)
			return "add_user";
		else {
			m.addAttribute("msg", "you dont have  privilege for this page");
			return "landing";
		}
	}

	// handler for adding user
	@RequestMapping(value = "/handle-add-user")
	public String handlerAddUser(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response,
			Model m) throws Exception {
		session = request.getSession(false);
		user1 = (User) session.getAttribute("user");
		if (user1.getType() == 1) {
			if (user.getName().length() == 0 || user.getEmail().length() == 0 || user.getPassword().length() == 0) {
				m.addAttribute("name", "*Name can not be empty");
				m.addAttribute("password", "*email can not be empty");
				m.addAttribute("email", "*email can not be empty");
				return "add_user";
			}
			userDao.createUser(user);
			m.addAttribute("res", "data inserted succesfuly");
			return "add_user";
		} else {
			m.addAttribute("msg", "you dont have  privilege for this page");
			return "landing";
		}
	}

	// mapper for adding vehicle
	@RequestMapping("/add-vehicle")
	public String mapperAddVehicle(Model m, HttpServletRequest request) throws Exception {
		session = request.getSession(false);
		user1 = (User) session.getAttribute("user");
		if (user1.getType() == 1) {
			m.addAttribute("title", "Add Vehicle");
			return "add_vehicle";
		} else {
			m.addAttribute("msg", "you dont have  privilege for this page");
			return "landing";
		}
	}

	// for handler for adding vehicle
	@RequestMapping(value = "/handle-add-vehicle", method = RequestMethod.POST)
	public String handlerAddVehicle(@ModelAttribute Vehicle vehicle, HttpServletRequest request,
			HttpServletResponse response, Model m) throws IOException, ServletException {
		session = request.getSession(false);
		user1 = (User) session.getAttribute("user");
		if (user1.getType() == 1) {
			if (vehicle.getVName().length() == 0 || vehicle.getVColor().length() == 0
					|| vehicle.getVNumber().toString().length() == 0) {
				m.addAttribute("vName", "*Name can not be empty");
				m.addAttribute("vColor", "*color can not be empty");
				m.addAttribute("vNumber", "*number can not be empty");
				return "add_vehicle";
			}
			vehicleDao.createVehicle(vehicle);
			request.setAttribute("res", "data inserted succesfuly");
			// m.addAttribute("res", "data inserted succesfuly");
			return "add_vehicle";
		} else {
			m.addAttribute("msg", "you dont have  privilege for this page");
			return "landing";
		}
	}

	// handler for Listing all vehicles
	@GetMapping("/list-all-vehicle")
	public String handlerAllVehicle(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("listVehicle", vehicleDao.ListAllVehicle());
		return "list_all_vehicle";
	}

	// handler for listing all users
	@GetMapping(value = "/list-all-users")
	public String handlerAllUser(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("listUser", userDao.ListAllUser());
		return "list_all_users";
	}

	// mapper for return jsp page of reservation history
	@RequestMapping("/list-reservation-history")
	public String mapperListReservatinHistory(HttpServletRequest request, User user, Model model) {
		session = request.getSession(false);
		user1 = (User) session.getAttribute("user");
		if (user1.getType() == 1) {
			request.setAttribute("listreservation", service.fetchAllReservationDetails());
			return "reservation_history";
		} else {
			model.addAttribute("msg", "you dont have  privilege for this page");
			return "landing";
		}
	}

	// mapper for updating vehicles
	@RequestMapping("/updateuser/{userId}")
	public String mapperUpdateUser(@PathVariable("userId") int userId, HttpServletRequest request, Model model) {
		session = request.getSession(false);
		user1 = (User) session.getAttribute("user");
		if (user1.getType() == 1) {
			User user = userDao.getUser(userId);
			model.addAttribute("user", user);
			return "update_user";
		} else {
			model.addAttribute("msg", "you dont have  privilege for this page");
			return "landing";
		}
	}

	// for handler for updating user
	@RequestMapping(value = "/handle-update-user", method = RequestMethod.POST)
	public void handlerUpdateUser(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response,
			Model model) throws IOException, ServletException {

		RequestDispatcher rd;
		session = request.getSession(false);
		user1 = (User) session.getAttribute("user");
		if (user1.getType() == 1) {
			String res = "";
			userDao.updateUser(user);
			;
			res = "details updated successfully";
			request.setAttribute("res", res);
			rd = request.getRequestDispatcher("WEB-INF/views/update_user.jsp");
			rd.forward(request, response);
		} else {
			throw new RuntimeException("you dont have  privilege for this page");
		}
	}

	// handler for deleting user
	@RequestMapping("/deleteuser/{userId}")
	public RedirectView handlerDeleteUser(@PathVariable("userId") int userId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		type = 1;
		RedirectView redirectView = new RedirectView();
		if (type == 1) {
			userDao.deleteUser(userId);
			redirectView.setUrl(request.getContextPath() + "/list-all-users");
			return redirectView;
		} else {
			throw new RuntimeException("you dont have  privilege for this page");
		}
	}

	// handler for deleting vehicle
	@RequestMapping("/delete/{vehicleId}")
	public RedirectView handlerDeleteVehicle(@PathVariable("vehicleId") int vehicleId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		type = 1;
		RedirectView redirectView = new RedirectView();
		if (type == 1) {
			vehicleDao.deleteVehicle(vehicleId);
			redirectView.setUrl(request.getContextPath() + "/list-all-vehicle");
			return redirectView;
		} else {
			throw new RuntimeException("you dont have  privilege for this page");
		}
	}

	// mapper for updating vehicles
	@RequestMapping("/update/{vehicleId}")
	public String mapperUpdateVehicle(@PathVariable("vehicleId") int vehicleId, HttpServletRequest request,
			Model model) {
		session = request.getSession(false);
		user1 = (User) session.getAttribute("user");
		if (user1.getType() == 1) {
			Vehicle vehicle = vehicleDao.getVehicle(vehicleId);
			model.addAttribute("vehicle", vehicle);
			return "update_vehicle";
		} else {
			model.addAttribute("msg", "you dont have  privilege for this page");
			return "landing";
		}
	}

	// for handler for updating vehicle
	@RequestMapping(value = "/handle-update-vehicle", method = RequestMethod.POST)
	public void handlerUpdateVehicle(@ModelAttribute Vehicle vehicle, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException, ServletException {

		RequestDispatcher rd;
		session = request.getSession(false);
		user1 = (User) session.getAttribute("user");
		if (user1.getType() == 1) {
			String res = "";
			vehicleDao.updateVehicle(vehicle);
			res = "details updated successfully";
			request.setAttribute("res", res);
			rd = request.getRequestDispatcher("WEB-INF/views/update_vehicle.jsp");
			rd.forward(request, response);
		} else {
			throw new RuntimeException("you dont have  privilege for this page");
		}
	}

	// handler for display all vehicle information to user only
	@RequestMapping("/list-all-vehicle-user")
	public String handlerListAllVehicleUser(HttpServletRequest request, User user) {
		System.out.println(2);
		session = request.getSession(false);
		user = (User) session.getAttribute("user");
		request.setAttribute("listVehicle", vehicleDao.ListAllVehicle());
		System.out.println(user.getId());
		return "list_all_vehicle_user";
	}

	// mapper for creating reservation by user
	@RequestMapping("/reservation/{vehicleId}")
	public String mappercreateReservation(@PathVariable("vehicleId") int vehicleId, HttpServletRequest request,
			User user, Model model) throws ServletException, IOException {
		session = request.getSession(false);
		user = (User) session.getAttribute("user");
		model.addAttribute("vehivleID", vehicleId);
		model.addAttribute("userID", user.getId());
		model.addAttribute("userName", user.getName());
		return "create_reservation";
	}

	// handler for creating reservation by user
	@RequestMapping(value = "/handle-create-reservation", method = RequestMethod.POST)
	public String handlerCreateReservation(@ModelAttribute ReservationnVo reservationnVo, Model model)
			throws Exception {

		boolean res = false;
		try {
			res = service.processReservation(reservationnVo);
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		if (res = true) {
			model.addAttribute("msg", "Reservation created successfully");
			return "create_reservation";
		} else {
			model.addAttribute("msg", "Reservation failed");
			return "landing";
		}

	}
	//handler for displaying reservation
	@RequestMapping("reservation-status")
	public String handlerReservationStauts(@ModelAttribute User user, Model model) throws Exception {
		user = (User) session.getAttribute("user");
		System.out.println(user);
		model.addAttribute("data", userDao.getAllDetailsByUserID(user.getId()));
		return "reservation_status";
	}

	// mapper for creating reservation by user
		@RequestMapping("/reservation-admin/{vehicleId}")
		public String mappercreateReservationByAdmin(@PathVariable("vehicleId") int vehicleId, HttpServletRequest request,
				User user, Model model) throws ServletException, IOException {
			session = request.getSession(false);
			user = (User) session.getAttribute("user");
			model.addAttribute("vehivleID", vehicleId);
			model.addAttribute("userID", user.getId());
			model.addAttribute("userName", user.getName());
			
			return "create_reservation_admin";
		}

		// handler for creating reservation by user
		@RequestMapping(value = "/handle-create-reservation-admin", method = RequestMethod.POST)
		public String handlerCreateReservationByadmin(@ModelAttribute ReservationnVo reservationnVo, Model model)
				throws Exception {

			boolean res = false;
			try {
				res = service.processReservation(reservationnVo);
				res = true;
			} catch (Exception e) {
				e.printStackTrace();
				res = false;
			}
			if (res = true) {
				model.addAttribute("msg", "Reservation created successfully");
				return "create_reservation_admin";
			} else {
				model.addAttribute("msg", "Reservation failed");
				return "landing";
			}

		}
	
	
	/*@RequestMapping("create-reservation-admin")
	public String mapperCreateReservationAdmin(HttpServletRequest request,Model model) {
		session = request.getSession(false);
		
		User user = (User) session.getAttribute("user");
		if (user.getType() == 1) {
			model.addAttribute("userall",userDao.ListAllUser());
			model.addAttribute("vehicleall",vehicleDao.ListAllVehicle());
			
			return "create_reservation_admin";
		} else {
			model.addAttribute("msg", "you dont have  privilege for this page");
			return "landing";
		}
	}*/

	/*@RequestMapping(value = "confrom-Reservation-admin", method = RequestMethod.POST)
	public String handlerCreateReservationAdmin(@ModelAttribute ReservationnVo reservationnVo, Model model,HttpServletRequest request)
			throws Exception {
		System.out.println(request.getParameter("userid"));
		System.out.println(request.getParameter("vehicleid"));
		

		boolean res = false;
		try {
			reservationnVo.setUserId(request.getParameter("userid"));
			reservationnVo.setUserId(request.getParameter("vehicleid"));
			res = service.processReservation(reservationnVo);
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		if (res = true) {
			model.addAttribute("msg", "Reservation created successfully");
			return "create_reservation";
		} else {
			model.addAttribute("msg", "Reservation failed");
			return "landing";
		}
	}*/
	@ExceptionHandler(value = Exception.class)
	public String customException() {
		return "custom_page";
	}
	
}
