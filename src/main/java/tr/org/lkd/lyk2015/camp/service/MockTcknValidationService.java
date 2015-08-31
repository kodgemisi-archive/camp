package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;

@Service
public class MockTcknValidationService implements TcknValidationService {

	@Override
	public boolean validate(String name, String surname, Integer birthDate, Long tckn) {

		if (tckn == null && tckn.equals(11111111111L)) {
			return false;
		}

		return true;
	}

}
