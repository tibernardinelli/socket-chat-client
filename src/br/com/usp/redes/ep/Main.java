package br.com.usp.redes.ep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

	private static Scanner scanner;
	private static Socket socket;
	private static BufferedReader bufferedReader;
	private static PrintWriter printWriter;

	public static void main(String[] args) throws UnknownHostException, IOException {
		scanner = new Scanner(System.in);

		socket = new Socket("localhost", 2020);
		printWriter = new PrintWriter(socket.getOutputStream(), true);
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		System.out.println("Nome do usuário: ");
		String usuario = scanner.nextLine();
		printWriter.println(usuario);
		System.out.println(receiveMessage());
		
		new Thread(() -> {
			printWriter.println("keepAlive");
		});
		
	}
	
	private static String receiveMessage() throws IOException{
		String s = "";
		String x = null;
		while (!(x = bufferedReader.readLine()).equals("$$D")){
			s += x;
		}
		return s;
	}
	
}
