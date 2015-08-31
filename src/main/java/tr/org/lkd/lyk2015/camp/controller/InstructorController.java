package tr.org.lkd.lyk2015.camp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.service.CourseService;
import tr.org.lkd.lyk2015.camp.service.InstructorService;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String form(@ModelAttribute Instructor instructor, Model model) {

		model.addAttribute("courses", this.courseService.getAll());
		return "admin/instructorCreateForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@ModelAttribute Instructor instructor, @RequestParam("courseIds") List<Long> ids) {
		this.instructorService.create(instructor, ids);
		return "redirect:/instructors";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("instructors", this.instructorService.getAll());
		return "admin/instructorList";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("instructor", this.instructorService.getInstructorWithCourses(id));
		model.addAttribute("courses", this.courseService.getAll());
		return "admin/instructorUpdateForm";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute Instructor instructor, @PathVariable("id") Long id, Model model,
			@RequestParam("courseIds") List<Long> ids) {

		this.instructorService.update(instructor, ids);

		model.addAttribute("courses", this.courseService.getAll());
		return "admin/instructorUpdateForm";
	}
}
