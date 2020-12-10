import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Данный класс определяет поведение потока, который отвечает
 * за считывание входящих с сервера даных.
 */

public class SocketInputThread implements Runnable {

    private Socket s;
    private Scanner in = null;
    private String inMessage = null;

    public SocketInputThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            in = new Scanner(s.getInputStream());
            while (true) {
                if (in.hasNext()) {
                    inMessage = in.nextLine();
                    System.out.println(inMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}