import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Данный класс отвечает за поведение поток в процессе обмена данными
 * между сервером и клиентами.
 */

public class SocketThread implements Runnable {

    private Socket s;
    private Scanner in = null;
    private PrintWriter out = null;
    private boolean quit = true;
    private String inMessage = null;
    private HashMap<Socket, String> mapSocket = null;
    private String nameSocket = null;

    public SocketThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            System.out.println("User connect: " + s.toString());
            MapSocket.addSocketToMap(s);
            in = new Scanner(s.getInputStream());
            while (quit) {
                inMessage = in.nextLine();
                mapSocket = MapSocket.getMapSocket();
                if (inMessage.replaceAll("[^name]", "").equals("name")) {
                    nameSocket = inMessage.substring(inMessage.lastIndexOf("=") + 1);
                    MapSocket.addNameSocketToMap(s, nameSocket);
                    for (Socket socket : mapSocket.keySet()) {
                        if (!socket.equals(s)) {
                            out = new PrintWriter(socket.getOutputStream());
                            out.println(nameSocket + " - connect to server");
                            out.flush();
                        }
                    }
                    continue;
                }
                for (Socket socket : mapSocket.keySet()) {
                    if (!socket.equals(s)) {
                        out = new PrintWriter(socket.getOutputStream());
                        out.println(nameSocket + ": " + inMessage);
                        out.flush();
                    }
                }
                if (inMessage.trim().equals("quit")) {
                    quit = false;
                    break;
                }
            }
            MapSocket.removeSocketWithMap(s);
            System.out.println("User disconnect: " + s.toString());
        } catch (IOException e) {
            try {
                s.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}