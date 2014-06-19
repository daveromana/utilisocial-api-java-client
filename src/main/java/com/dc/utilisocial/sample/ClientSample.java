package com.dc.utilisocial.sample;

import com.dc.utilisocial.api.User;
import com.dc.utilisocial.api.UserTransfer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

@ClientEndpoint
public class ClientSample {
    private static final String WS_URL = "ws://54.186.19.14/datacapable/v1/streamer?token=";
    private static final String TOKEN_REQUEST_URL = "http://54.186.19.14/datacapable/v1/users/authenticate";
    private static CountDownLatch latch;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private File file = new File("utilisocial-data.txt");

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected...");
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
        latch.countDown();
    }
    @OnMessage
    public void onMessage(String message) {
        logger.info("Received msg: " + message);

        // write to file
        writeToFile(message);
    }

    public void connect() {
        latch = new CountDownLatch(1);

        ClientManager client = ClientManager.createClient();
        try {
            client.connectToServer(ClientSample.class, new URI(WS_URL+getToken()));
            latch.await();

        } catch (DeploymentException | URISyntaxException | InterruptedException | IOException e) {
            logger.log(Level.SEVERE, "Unable to establish connection to " + WS_URL, e);
            throw new RuntimeException(e);
        }
    }
    
    private String getToken() throws IOException {
        User user = new User("skta", "password", "general", "sktelecom");
        
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(TOKEN_REQUEST_URL);

        StringEntity input = new StringEntity(gson.toJson(user));
        input.setContentType("application/json");
        postRequest.setEntity(input);

        HttpResponse response = httpClient.execute(postRequest);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        UserTransfer userTransfer = gson.fromJson(result.toString(), UserTransfer.class);
        return userTransfer.getToken();
    }
    
    private void writeToFile(String content) {
        try {
            if(!file.exists()){
                file.createNewFile();
            }

            logger.info("Writing to file " + file.getAbsolutePath());

            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(content);
            bufferWritter.close();

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to file " + file.toString(), e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ClientSample sample = new ClientSample();
        sample.connect();
    }
}
