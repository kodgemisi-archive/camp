package tr.org.lkd.lyk2015.camp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.config.Layout;
import tr.org.lkd.lyk2015.camp.controller.validation.ApplicationFormValidator;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.model.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.service.ApplicationService;
import tr.org.lkd.lyk2015.camp.service.ApplicationService.ValidationResult;
import tr.org.lkd.lyk2015.camp.service.CourseService;

@Controller
public class ApplicationController {

	@Autowired
	private ApplicationFormValidator applicationFormValidator;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ApplicationService applicationService;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(this.applicationFormValidator);
	}

	@Layout("layouts/public")
	@RequestMapping(value = "/basvuru", method = RequestMethod.GET)
	public String form(Model model, Authentication authentication) {

		Student student = null;
		if (authentication != null && authentication.getPrincipal() instanceof Student) {
			student = (Student) authentication.getPrincipal();
		}

		if (student != null) {
			// user is updating his/her form
			ApplicationFormDto formDto = this.applicationService.createApplicationDto(student);
			model.addAttribute("form", formDto);
			model.addAttribute("update", true);
		} else {
			// new user creating a new application
			model.addAttribute("form", new ApplicationFormDto());
		}

		model.addAttribute("courses", this.courseService.getAllActive());
		return "applicationForm";
	}

	@Layout("layouts/public")
	@RequestMapping(value = "/basvuru", method = RequestMethod.POST)
	public String create(@ModelAttribute("form") @Valid ApplicationFormDto applicationFormDto, BindingResult bindingResult, Model model,
			Authentication authentication) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("courses", this.courseService.getAllActive());
			return "applicationForm";
		} else {

			if (applicationFormDto.getApplication().getId() == null) {
				this.applicationService.create(applicationFormDto);
				model.addAttribute("message", "Başvurunuz başarıyla alındı, epostanızı kontrol ediniz.");
			} else {

				Student student = null;
				if (authentication != null && authentication.getPrincipal() instanceof Student) {
					student = (Student) authentication.getPrincipal();
				} else {
					return "error";
				}
				this.applicationService.isUserAuthorizedForForm(student, applicationFormDto.getApplication());

				this.applicationService.update(applicationFormDto);
				model.addAttribute("message", "Başvurunuz başarıyla gucellendi.");
			}

			return "applicationSuccess";
		}
	}

	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	public String list(Model model) {

		model.addAttribute("applications", this.applicationService.getAll());
		return "admin/applicationList";
	}

	@RequestMapping(value = "/applications/validate/{uuid}", method = RequestMethod.GET)
	public String validation(@PathVariable("uuid") String uuid, Model model) {

		ValidationResult result = this.applicationService.validate(uuid);

		model.addAttribute("message", result.toString());

		return "applicationSuccess";
	}

}
