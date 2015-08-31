package tr.org.lkd.lyk2015.camp.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class MockBlackListServiceImpl implements BlackListService {

	@Override
	public boolean inBlacklist(Long tckn, String email, String name, String surname) {

		if (tckn.equals(22222222222L)) {
			return true;
		}

		return false;
	}

}
