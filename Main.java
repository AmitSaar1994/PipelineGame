import Cache.MyCacheManager;
import ClientHandler.*;
import Solver.MySolver;

public class Main {

	public static void main(String[] args) { 

		Serverable nserver = new MyServer(6400);

	try {
		ClientHandler ch = new MyClientHandler(new MyCacheManager(),new MySolver());
		nserver.start(ch);
 		System.in.read();
		nserver.stop();
		} catch (Exception e) {			
			System.out.println("oops");
		}	
		
	}

}

