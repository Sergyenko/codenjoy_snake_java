import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;

public class WebSocketSnakeClient {

//    private static final String SERVER = "ws://localhost:8080/codenjoy-contest/ws";
	private static final String SERVER = "ws://172.16.101.141:8080/codenjoy-contest/ws";

    private static String userName = "amogh@epam.com";

    private static WebSocketSnakeClient client;

    private WebSocket.Connection connection;
    private SnakeSolver solver;
    private WebSocketClientFactory factory;

    public WebSocketSnakeClient(SnakeSolver solver) {
        this.solver = solver;
    }

    public static void main(String[] args) throws Exception {
        client = new WebSocketSnakeClient(new SnakeSolver());
        client.start();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    client.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void stop() throws Exception {
        connection.close();
        factory.stop();
    }

    private void start() throws Exception {
        factory = new WebSocketClientFactory();
        factory.start();

        WebSocketClient client = factory.newWebSocketClient();
        connection = client.open(new URI(SERVER + "?user=" + userName), new WebSocket.OnTextMessage() {
            public void onOpen(Connection connection) {
                System.out.println("Opened");
            }

            public void onClose(int closeCode, String message) {
                System.out.println("Closed");
            }

            public void onMessage(String data) {
            	data = new String(data.getBytes(StandardCharsets.UTF_8), Charset.forName(StandardCharsets.UTF_8.toString()));
            	System.out.println("data = " + data);
                String answer = solver.answer(data);
                try {
                    connection.sendMessage(answer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private int getX() {
            	
            	return 0;
            }
        }).get(5000, TimeUnit.MILLISECONDS);
    }
}
