package tr.org.lkd.lyk2015.camp.service;

public interface BlackListService {

	boolean inBlacklist(Long tckn, String email, String name, String surname);
}
