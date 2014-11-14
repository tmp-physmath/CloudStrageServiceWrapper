package system;

public interface IAuthorization {
	String authURL();
	boolean auth(String key);
}
