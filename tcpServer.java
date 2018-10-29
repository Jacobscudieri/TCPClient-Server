/**
 * @author Jacob Scudieri
 * Date: 10-14-18
 * File: tcpServer.java
 * Description: TLV protocol using TCP. Server class.
 *
 * CSCI 437
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class tcpServer 
{

	private static ServerSocket listener;
	private static Socket connSocket;

	public static void main(String[] args)
	{

		// If no argument for the port number is sent, error msg.
		if(args.length != 1) 
		{
			System.out.println("Usage: PORT number should be specified");
			return;
		}

		// If connected to client, it performs the following actions.
		try 
		{
			listener = new ServerSocket(Integer.parseInt(args[0]));
			connSocket = listener.accept();
			int tlv = 0;

			BufferedReader input = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));

			Scanner keyboard = new Scanner(System.in);

			while(tlv != 64) 
			{

				tlv = input.read();

				if(tlv == 0)
					break;

				else if (tlv == 8 )
				{

					System.out.println();
					System.out.println("From client: " + tlv);
					int intVal = input.read();

					System.out.println ("Client input: " + intVal);

					System.out.println("Waiting for client...");
					System.out.println();

				}

				else if (tlv == 16)
				{

					System.out.println();
					System.out.println("From client: " + tlv);
					String inputFloat = input.readLine();
					float convert = Float.parseFloat(inputFloat);
					System.out.println("Client input: "+ inputFloat);

					System.out.println("Waiting for client...");
					System.out.println();

				}

				else if (tlv == 32)
				{

					System.out.println();
					System.out.println("From client: " + tlv);
					int length = input.read();
					String inputString = input.readLine();
					System.out.println("String length: " + length);
					System.out.println("Client's string: ");
					System.out.println();

					char [] charArray = new char[length];
					charArray = inputString.toCharArray();
					for (int i=0; i<charArray.length; i++)
					{
						Thread.sleep(500);
						System.out.println(charArray[i]);
					}

					System.out.println("Waiting for client...");
					System.out.println();

				}

			}

			input.close();
			connSocket.close();
			listener.close();

		}
		catch(IOException eio) 
		{
			System.out.println("IOException: " + eio);
		}
		catch(InterruptedException ie) 
		{
			System.out.println("InterruptedException: " + ie);
		}
	}
}
