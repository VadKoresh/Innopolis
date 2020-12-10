import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Данный класс определяет поведение потока, который отвечает за ввод
 * данных клиентом с клавиатуры и отправку на сервер.
 */

public class SocketOutputThread implements Runnable {

    private Socket s;
    private PrintWriter out = null;
    private String outMessage = null;
    private String name = null;

    public SocketOutputThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(s.getOutputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

            while (name == null) {
                name = buffer.readLine();
                outMessage = "name = " + name;
                out.println(outMessage);
                out.flush();
            }
            while (true) {
                outMessage = buffer.readLine();
                out.println(outMessage);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}