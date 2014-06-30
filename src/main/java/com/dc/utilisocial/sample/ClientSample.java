package com.dc.utilisocial.sample;

import com.dc.utilisocial.api.Audit;
import com.dc.utilisocial.api.User;
import com.dc.utilisocial.api.UserTransfer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

@ClientEndpoint
public class ClientSample {
    private static final String DC_IP = "utilisocial.com";
//    private static final String DC_IP = "localhost";
    private static final String WS_URL = "ws://"+DC_IP+"/datacapable/v1/streamer?token=";
    private static final String TOKEN_REQUEST_URL = "http://"+DC_IP+"/datacapable/v1/users/authenticate";
    private static final String GET_AUDITS_URL = "http://"+DC_IP+"/datacapable/v1/audits/groups/sktelecom";
    private static CountDownLatch latch;

    private static Gson gson;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private DefaultHttpClient httpClient;
    private String token;
    
    public ClientSample() throws IOException {
        this.httpClient = new DefaultHttpClient();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.token = getToken();
        String date = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
        this.file = new File("utilisocial-data-" + date + ".txt");
        this.fileWriter = new FileWriter(file.getName(), true);
        this.bufferedWriter = new BufferedWriter(fileWriter);
    }
    
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
            logger.info("Using token: " + token);
            client.connectToServer(ClientSample.class, new URI(WS_URL + token));
            latch.await();

        } catch (DeploymentException | URISyntaxException | InterruptedException | IOException e) {
            logger.log(Level.SEVERE, "Unable to establish connection to " + WS_URL + token, e);
            throw new RuntimeException(e);
        }
    }

    private List<Audit> getAudits() throws IOException {
        HttpGet request = new HttpGet(GET_AUDITS_URL);

        // add request header
        request.addHeader("X-Auth-Token", token);
        logger.info("Getting all posts...");
        HttpResponse response = httpClient.execute(request);

        logger.info("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        List<Audit> audits = gson.fromJson(result.toString(), new TypeToken<List<Audit>>(){}.getType());
        return audits;
    }

    private String getToken() throws IOException {
        User user = new User("skta", "password", "general", "sktelecom");

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
    
    public void writeToFile(String content) {
        try {
            if(!file.exists()){
                file.createNewFile();
            }

            logger.info("Writing to file " + file.getAbsolutePath() + ": \n\t" + content);

            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to file " + file.toString(), e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        ClientSample sample = new ClientSample();

        // get the posts and write them to a file
        List<Audit> audits = sample.getAudits();
        for (Audit audit : audits) {

            // strip out fields that aren't needed
            audit.setGroups(null);
            sample.writeToFile(gson.toJson(audit));
        }

        // connect to the streaming api.  all new posts received will be appended to the file
        sample.connect();
    }
}
