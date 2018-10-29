/**
 * @author: Jacob Scudieri
 * Date: 10-14-18
 * File: tcpClient.java
 * Description: Simple TLV protocol using TCP. Client class.
 * 
 * CSCI 437
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class tcpClient 
{

	// Declare Socket object.
	private static Socket connSocket;

	public static void main(String[] args) 
	{

		// If two arguments are not passed at compilation, error msg.
		if(args.length != 2) 
		{
			System.out.println("Usage: Server Address and PORT number should be specified");
			System.out.println("Note: Specify Server Address first, then PORT number");
			return;
		}

		// If connected to server, performs the following actions.
		try {

			// Initializes socket object with 1 argument.
			connSocket = new Socket(args[0], Integer.parseInt(args[1]));

			// Creates scanner object for input.
			Scanner keyboard = new Scanner(System.in);

			int clientInput;			// Hold client input.

			// Creates buffered writer objects.
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(connSocket.getOutputStream()));

			// Menu options and grab user input.
			System.out.println("1. Input integer");
			System.out.println("2. Input floating point number");
			System.out.println("3. Input a string");
			System.out.println("4. Exit");
			System.out.println("Please select one of those options (1-4): ");
			clientInput = keyboard.nextInt();

			// While the input is not our exit condition, the loop continues.
			while(clientInput != 4) 
			{
				
				// Client wants to send an integer.
				if(clientInput == 1)
				{

					int toSend;			// Int user input to send to server.
					int tlv = 8;

					// Prompts and stores integer user input.
					System.out.print("Please input an integer you want to send: ");
					toSend = keyboard.nextInt();

					output.write(tlv);
					output.write(toSend);
					output.newLine();
					output.flush();

				}
				// Client wants to send a floating-point number.
				else if(clientInput == 2)
				{

					int tlv = 16;

					// Prompts and stores floating-point user input.
					System.out.println("Please input a floating point number you want to send: ");
					keyboard.nextLine();
					String toSend = keyboard.nextLine();

					output.write(tlv);
					output.write(toSend, 0, toSend.length());
					output.newLine();
					output.flush();

				}
				// Client wants to send a String.
				else if(clientInput == 3)
				{

					String toSend;		// String user input to send to server.
					int tlv = 32;
					
					// Prompts and stores String user input.
					System.out.println("Please input a string you want to send: ");
					keyboard.nextLine();
					toSend = keyboard.nextLine();

					output.write(tlv);
					output.write(toSend.length());
					output.write(toSend, 0, toSend.length());
					output.newLine();
					output.flush();

				}
				// If any other value is entered, it is invalid, error msg.
				else
					System.out.println("Invalid input: " + clientInput);

				// Menu options and grab user input.
				System.out.println("1. Input integer");
				System.out.println("2. Input floating point number");
				System.out.println("3. Input a string");
				System.out.println("4. Exit");
				System.out.println("Please select one of those options (1-4): ");
				clientInput = keyboard.nextInt();

			}

			// User has selected 4, so the connection terminates.
			output.write(64);
			output.newLine();
			output.flush();

			// Closes BufferedReader and Scanner stream and closes client Socket.
			output.close();
			connSocket.close();
		
		}
		catch(IOException ioe) 
		{
			System.out.println("IOException: " + ioe);
		}
	}
}
