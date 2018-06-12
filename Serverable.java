
import ClientHandler.ClientHandler;

public interface Serverable {
	
	public void start(ClientHandler ch) throws Exception;
	
	public void stop();
}
