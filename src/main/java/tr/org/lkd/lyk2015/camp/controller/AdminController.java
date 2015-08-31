package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.service.AdminService;

@Controller
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String form(@ModelAttribute Admin admin) {
		return "admin/adminCreateForm";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateForm(Model model, @PathVariable("id") Long id,
			@RequestParam(value = "message", required = false) String message) {

		model.addAttribute("admin", this.adminService.getById(id));
		model.addAttribute("message", message);
		return "admin/adminUpdateForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@ModelAttribute Admin admin, @RequestParam("passwordAgain") String passwordAgain) {

		if (!passwordAgain.equals(admin.getPassword())) {
			// TODO error
		}

		this.adminService.create(admin);
		return "redirect:/admins";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute Admin admin, @RequestParam("passwordAgain") String passwordAgain,
			@PathVariable("id") Long id, Model model) {

		if (!passwordAgain.equals(admin.getPassword())) {
			// TODO error
		}

		this.adminService.update(admin);
		model.addAttribute("message", "Successful");
		return "redirect:/admins/update/" + id;
	}

	@RequestMapping()
	public String list(Model model) {

		model.addAttribute("admins", this.adminService.getAll());

		return "admin/adminList";
	}

}
