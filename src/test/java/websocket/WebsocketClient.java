package websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: ms
 * Date: 6/24/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
@ClientEndpoint
public class WebsocketClient {

    @OnMessage
    public void onMessage( String message, Session client )
            throws IOException,
            EncodeException {
        System.out.println( "Got message: " + message );
    }



    public static void main( String[] args )
            throws URISyntaxException, IOException, DeploymentException, InterruptedException {
        WebSocketContainer c = ContainerProvider.getWebSocketContainer();
        Session mySession = c.connectToServer( WebsocketClient.class,
                                               new URI( "ws://localhost:8080/JavaEE7-1.0-SNAPSHOT/websocket" ) );
        mySession.getBasicRemote().sendText( "Hallo ich bin ein Bot" );
        Thread.sleep( 100 );
    }

}
