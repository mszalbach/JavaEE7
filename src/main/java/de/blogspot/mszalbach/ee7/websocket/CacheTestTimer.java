package de.blogspot.mszalbach.ee7.websocket;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by foobar on 07.05.15.
 */
@Startup
@Singleton
@LocalBean
public class CacheTestTimer {

    @Resource(lookup = "java:app/AppName")
    String appName;


    @Schedule(minute = "*" ,hour="*", second = "*/10")
    public void refreshCache()  {
        URL url = null;
        try {
            //TODO how to get Port or call the servlet directly?
            url = new URL("http://localhost:8080/"+appName+"/test");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("cacheme", "true");
            connection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
