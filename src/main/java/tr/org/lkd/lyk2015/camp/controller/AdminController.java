package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.service.AdminService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAdmins(Model model) {
        model.addAttribute("admins", this.adminService.getAll());
        return "admin/adminList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getAdminCreateForm(@ModelAttribute Admin admin) {
        return "admin/adminCreateForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAdmin(@Valid @ModelAttribute Admin admin, BindingResult result,
                              @RequestParam("passwordAgain") String passwordAgain, Model model) {

        if (!passwordAgain.equals(admin.getPassword())) {
            model.addAttribute("message", "Sifreler uyusmuyor. Lutfen kontrol ediniz.");
            return "admin/adminCreateForm";
        }
        if (result.hasErrors()) {
            return "admin/adminCreateForm";
        } else {
            this.adminService.create(admin);
            return "redirect:/admins";
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getAdminUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("admin", this.adminService.getById(id));
        return "admin/adminUpdateForm";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateAdmin(@Valid @ModelAttribute Admin admin, BindingResult result,
                              @RequestParam("passwordAgain") String passwordAgain, @PathVariable("id") Long id, Model model) {

        if (!passwordAgain.equals(admin.getPassword())) {
            model.addAttribute("message", "Sifreler uyusmuyor. Lutfen kontrol ediniz.");
            return "admin/adminUpdateForm";
        }
        if (result.hasErrors()) {
            return "admin/adminUpdateForm";
        } else {
            this.adminService.update(admin);
            return "redirect:/admins";
        }
    }
}
