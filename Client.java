import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Please Enter Network Address: ");
        String ipAddress = scnr.nextLine();

        System.out.print("Please Enter Port: ");
        int port = scnr.nextInt();
        scnr.nextLine(); // consume leftover newline

        while (true) {
            System.out.println("Please Enter Number Corresponding to Desired Command");
            System.out.println("1 - Date and Time");
            System.out.println("2 - Uptime");
            System.out.println("3 - Memory Use");
            System.out.println("4 - Netstat");
            System.out.println("5 - Current Users");
            System.out.println("6 - Running Process");
            System.out.println("7 - Exit Program");

            String command = scnr.nextLine().trim();
            if (!command.matches("[1-7]")) {
                System.out.println("Invalid input. Enter a number 1-7.");
                continue;
            }

            if (command.equals("7")) {
                System.out.println("Exiting program...");
                break;
            }

            System.out.println("Please Enter Number of Requests");
            System.out.println("Options: 1, 5, 10, 15, 20, 25");
            int numClients = scnr.nextInt();
            scnr.nextLine(); // consume leftover newline

            List<Long> turnAroundTimes = Collections.synchronizedList(new ArrayList<>());
            Thread[] threads = new Thread[numClients];

            for (int i = 0; i < numClients; i++) {
                int id = i + 1;
                threads[i] = new Thread(() -> {
                    long start = System.currentTimeMillis();
                    try(Socket socket = new Socket(ipAddress, port)) {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        out.println(command);

                        String line;
                        while((line = in.readLine()) != null) {
                            System.out.println(line);
                        }

                        //record end time and calculate turn-around
                        long end = System.currentTimeMillis();
                        long turnAround = end - start;
                        turnAroundTimes.add(turnAround);

                        System.out.println("Turn-around time for client request number " + id + ": " + (end - start) + " ms\n");
                    } catch(IOException e) {
                        System.out.println("Client " + id + " error: " + e.getMessage());
                    }
                });
                threads[i].start();
            }

            // Wait for all threads to finish before showing the next prompt
            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

             // Compute total and average turn-around time
            long total = 0;
            for (long t : turnAroundTimes) total += t;
            double average = total / (double) turnAroundTimes.size();

            System.out.println("\n===== Summary =====");
            System.out.println("Total Turn-around Time: " + total + " ms");
            System.out.println("Average Turn-around Time: " + String.format("%.2f", average) + " ms");
        }

        scnr.close();
    }
}
