import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientDemo {
	public static void main (String[] args) throws Exception{
		System.out.println("CLIENT");
		Socket theServer=new Socket("127.0.0.1",6400);
		System.out.println("Connected to Server");
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
		
		PrintWriter outToServer=new PrintWriter(theServer.getOutputStream());
		
		String line;
		while(!(line=inFromUser.readLine()).equals("exit")) {
			outToServer.println(line);
			outToServer.flush();
			System.out.println(inFromServer.readLine());
		}
		outToServer.println("exit");
		outToServer.flush();
		
		inFromServer.close();
		inFromUser.close();
		outToServer.close();
}

}
