import java.net.Socket;
import java.util.HashMap;

/**
 * Данный класс сохраняет в mapSocket ссылки на все сокеты,
 * а также имена клиентов, которые есть на данный момент.
 */

public class MapSocket {

    private static HashMap<Socket, String> mapSocket = new HashMap<>();

    public synchronized static HashMap<Socket, String> getMapSocket() {
        return MapSocket.mapSocket;
    }

    public synchronized static void addSocketToMap(Socket s) {
        MapSocket.mapSocket.put(s, null);
    }

    public synchronized static void addNameSocketToMap(Socket s, String name) {
        MapSocket.mapSocket.put(s, name);
    }

    public synchronized static void removeSocketWithMap(Socket s) {
        MapSocket.mapSocket.remove(s);
    }
}