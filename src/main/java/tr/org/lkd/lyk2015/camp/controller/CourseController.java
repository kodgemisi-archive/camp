package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String form(@ModelAttribute Course course) {
		return "admin/courseCreateForm";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("courses", this.courseService.getAll());
		return "admin/courseList";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@ModelAttribute Course course) {

		this.courseService.create(course);

		return "redirect:/courses";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String updateForm(Model model, @PathVariable("id") Long id,
			@RequestParam(value = "message", required = false) String message) {

		model.addAttribute("course", this.courseService.getById(id));
		model.addAttribute("message", message);
		return "admin/courseUpdateForm";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute Course course, @PathVariable("id") Long id, Model model) {

		this.courseService.update(course);
		model.addAttribute("message", "Success");

		return "redirect:/courses/" + id;
	}
}
