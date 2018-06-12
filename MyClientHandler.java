package ClientHandler;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import Cache.CacheManager;
import Solver.Solver;

public class MyClientHandler implements ClientHandler {

		private CacheManager cache;
		private Solver solver;
		
	public MyClientHandler(CacheManager cache, Solver solver) {
		this.cache = cache;
		this.solver=solver;
	}
	
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) throws IOException {
		// input stream to string
		String problem = GetProblemString(inFromClient);
		// get solution from cache manager
		String solution = cache.load(problem);
		//if no solution solve
		if (solution == null)
		{
			//solve problem
			solution = solver.solveProblem(problem);
			//save solution to cache manager
			cache.store(problem, solution);
		}
		//write solution to output stream
		WriteSolutionToClient(outToClient,solution);
		
		//Close streams
		inFromClient.close();
		outToClient.close();
	}
	
	private void WriteSolutionToClient(OutputStream outToClient, String solution) {
		PrintWriter outTC=new PrintWriter(outToClient);
		outTC.println(solution);
		//TODO send as (int,int,int)
	}

	private String GetProblemString(InputStream input) {				
		BufferedReader inFC= new BufferedReader(new InputStreamReader(input));
		StringBuilder sb = new StringBuilder();		
		//build string to each line
		inFC.lines().forEach((line) -> sb.append(line));
		//connect all the strings to one string
		return sb.toString();
		
	}

	
}
