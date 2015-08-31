package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;

@Service
public class MockExamCheckServiceImpl implements ExamCheckService {

	@Override
	public boolean userPassed(Long tckn, String email) {

		if (tckn.equals(33333333333L)) {
			return false;
		}

		return true;
	}

}
