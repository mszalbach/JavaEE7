package de.blogspot.mszalbach.ee7.websocket;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ms
 * Date: 6/24/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
@ServerEndpoint( "/websocket" )
public class WebsocketEndpoint {

    // Create a Set to hold client sessions
    private static final Set<Session> clientSessions = Collections.synchronizedSet( new HashSet<Session>() );



    @OnOpen
    public void onOpen( Session aClientSession )
            throws IOException {
        clientSessions.add( aClientSession );
        aClientSession.getBasicRemote().sendText( "Login: " + new Date() );
    }



    @OnClose
    public void onClose( Session aClientSession ) {
        clientSessions.remove( aClientSession );
    }



    @OnMessage
    public void onMessage( String message, Session client )
            throws IOException,
            EncodeException {

        for ( Session clientSession : clientSessions ) {
            clientSession.getAsyncRemote().sendText( message );
        }
    }



    @OnError
    public void onError( Session aclientSession, Throwable aThrowable ) {
        System.out.println( "Error : " + aclientSession + " " + aThrowable );

    }


}
