package Cache;

import java.util.Hashtable;

public class MyCacheManager implements CacheManager {

	private Hashtable<Integer, String> solutions;
	
	
	public MyCacheManager() {
		solutions = new Hashtable<Integer, String>();
	}

	public String load(String problem) {
		int problemKey = problem.hashCode();
		return solutions.get(problemKey);
	}

	public void store (String problem, String solution) {
		int problemKey = problem.hashCode();
		solutions.put(problemKey, solution);
	}

}
