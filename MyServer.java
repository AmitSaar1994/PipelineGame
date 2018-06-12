  import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import ClientHandler.ClientHandler;

	public class MyServer implements Serverable{
		private int port;
		private volatile boolean stop;
		
		public MyServer (int port){
			this.port=port;
			stop=false;
		}
	
		
		private void startServer (ClientHandler ch) throws IOException{
			ServerSocket nsockserver = new ServerSocket(port);
			System.out.println("Server is open waiting for client");
			nsockserver.setSoTimeout(1000);
			
			while (!stop) {
			try{
				Socket aClient=nsockserver.accept();
				System.out.println("client connected");
				try{
					ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
					
					//Close the socket
					aClient.close();
				} catch (IOException e) {
					System.out.println("Client Handler Error");
				}
			} catch (SocketTimeoutException e) {
				System.out.println("Client did not connect...");
			}
			
			}
			nsockserver.close();
			System.out.println("Server stopped sucessfully");
		}

		public void start(ClientHandler ch) { 
			new Thread(()->{
				try {
					startServer(ch);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();
			
		}
	
		public void stop() {
			stop=true;
		}
	}
	


