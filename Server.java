import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter port number: ");
        int port = scnr.nextInt();

        try(ServerSocket serverSocket = new ServerSocket(port)) {
            while(true) {
                Socket socket = serverSocket.accept();

                //Set up input/output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String request = in.readLine();
                
                if (request == null) {
                    System.out.println("Client disconnected.");
                    socket.close();
                    continue;
                }

                int command = Integer.parseInt(request);
                switch (command) {
                    case 1:
                        executeCommand("date", out);
                        break;
                    case 2:
                        executeCommand("uptime", out);
                        break;
                    case 3:
                        executeCommand("free -m", out);
                        break;
                    case 4:
                        executeCommand("netstat -an", out);
                        break;
                    case 5:
                        executeCommand("who", out);
                        break;
                    case 6:
                        executeCommand("ps -e", out);
                        break;
                    default:
                        out.println("Invalid command number. Please enter 1-7.");
                }

                socket.close();

                if (command == 7) {
                    break;
                }

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        scnr.close();
    }

    public static void executeCommand(String command, PrintWriter out) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
            reader.close();
            process.waitFor();
        } catch (Exception e) {
            out.println("Error executing command: " + e.getMessage());
        }
    }
}