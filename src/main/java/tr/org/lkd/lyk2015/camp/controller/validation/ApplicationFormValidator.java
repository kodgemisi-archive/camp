package tr.org.lkd.lyk2015.camp.controller.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import tr.org.lkd.lyk2015.camp.model.Application.WorkStatus;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.model.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.service.BlackListService;
import tr.org.lkd.lyk2015.camp.service.ExamCheckService;
import tr.org.lkd.lyk2015.camp.service.TcknValidationService;

@Component
public class ApplicationFormValidator implements Validator {

	@Autowired
	private TcknValidationService tcknValidationService;

	@Autowired
	private ExamCheckService examCheckService;

	@Autowired
	private BlackListService blackListService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ApplicationFormDto.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ApplicationFormDto application = (ApplicationFormDto) target;

		System.out.println("================== validation ===========================");
		System.out.println(application);

		// Prevent inconsistent working status
		if (application.getApplication().getWorkStatus() == WorkStatus.NOT_WORKING
				&& application.getApplication().isOfficer()) {
			errors.rejectValue("workStatus", "error.notWorkingOfficer", "Hem calismayip hem nasil memursun");
		}

		// Check course selection size
		List<Long> copyOfPreffered = new ArrayList<>();
		copyOfPreffered.add(null);
		copyOfPreffered.add(null);
		copyOfPreffered.add(null);
		Collections.copy(copyOfPreffered, application.getPreferredCourseIds());

		copyOfPreffered.removeAll(Collections.singleton(null));
		if (copyOfPreffered.size() == 0) {
			errors.rejectValue("preferredCourseIds", "error.preferredCourseNoSelection",
					"En az bir kurs secmelisiniz.");
		}

		// Prevent same course selection
		int listSize = copyOfPreffered.size();
		Set<Long> set = new HashSet<>(copyOfPreffered);
		int setSize = set.size();

		if (listSize != setSize) {
			errors.rejectValue("preferredCourseIds", "error.preferredCourseSameSelection",
					"Ayni kursu b kez secemezsiniz.");
		}

		// Validate Tckn from web service
		Student student = application.getStudent();
		boolean tcknValid = this.tcknValidationService.validate(student.getName(), student.getSurname(),
				student.getBirthDate(), student.getTckn());

		if (!tcknValid) {
			errors.rejectValue("student.tckn", "error.tcknInvalid", "TC kimlik no hatali.");
		}

		// Validate Blacklist
		boolean userInBlacklist = this.blackListService.inBlacklist(student.getTckn(), student.getEmail(),
				student.getName(), student.getSurname());

		if (userInBlacklist) {
			errors.rejectValue("student.tckn", "error.inBlacklist", "Kara listedesiniz.");
		}

		// Exam passed?
		boolean userPassedExam = this.examCheckService.userPassed(student.getTckn(), student.getEmail());

		if (!userPassedExam) {
			errors.rejectValue("student.tckn", "error.examFail", "Sinavi gecmelisiniz.");
		}

	}

}
