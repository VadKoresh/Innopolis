import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Данный класс отвечает за соединение клиента к серверу,
 * а также за создание потоков для получения и отправления даных.
 */

public class ChatClient {

    public static void main(String[] args) {
        try {
            System.out.println("Client starting...");
            Socket s = new Socket("localhost", 7887);
            System.out.println("Connect to server...");
            System.out.println("Введите ваше имя:");
            Thread threadIn = new Thread(new SocketInputThread(s));
            Thread threadOut = new Thread(new SocketOutputThread(s));
            threadIn.start();
            threadOut.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}