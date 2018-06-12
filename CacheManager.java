package Cache;

public interface CacheManager {
	
	public String load (String problem);
	public void store (String problem, String solution);
}
