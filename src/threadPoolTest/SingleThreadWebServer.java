package threadPoolTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by WaterMelon on 2018/4/10.
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true){
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }

    public static void handleRequest(Socket connection){

    }
}
