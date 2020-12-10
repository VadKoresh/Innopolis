import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Данный класс отвечаеи за создание потоков для каждого вновь
 * создаваемого клиента
 */

public class Chat {
    public static void main(String[] args) {
        System.out.println("Program starting...");
        try {
            ServerSocket ss = new ServerSocket(7887);
            System.out.println("Server starting...");
            while (true) {
                Socket s = ss.accept();
                SocketThread socketThread = new SocketThread(s);
                Thread t = new Thread(socketThread);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}