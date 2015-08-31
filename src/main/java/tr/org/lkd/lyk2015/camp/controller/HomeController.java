package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.service.CourseService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private CourseService courseService;

	@RequestMapping("")
	public String home(Model model) {
		return "home";
	}

	@RequestMapping("/test/{id}")
	@ResponseBody
	public Course test(@PathVariable("id") Long id) {

		return this.courseService.getInstructorsOfCourse(id);
	}

}
