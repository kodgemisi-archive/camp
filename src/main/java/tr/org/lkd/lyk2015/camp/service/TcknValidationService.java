package tr.org.lkd.lyk2015.camp.service;

public interface TcknValidationService {

	public abstract boolean validate(String name, String surname, Integer birthDate, Long tckn);
}
