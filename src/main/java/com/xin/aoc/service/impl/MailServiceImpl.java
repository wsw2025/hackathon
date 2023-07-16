package com.xin.aoc.service.impl;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.xin.aoc.service.MailService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    static Credential credential;

    private Credential getCredential() {
        if (credential != null)
            return credential;

        try {
            String userId = "admin";
            Properties prop = new Properties();
            prop.load(MailServiceImpl.class.getResourceAsStream("/credential.properties"));
            String clientId = prop.getProperty("client_id", "");
            String clientSecret = prop.getProperty("client_secret", "");
            String refreshToken = prop.getProperty("refresh_token", "");
            DataStoreFactory dataStoreFactory = MemoryDataStoreFactory.getDefaultInstance();
            AuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(),
                    GsonFactory.getDefaultInstance(), clientId, clientSecret,
                    Collections.singletonList(GmailScopes.GMAIL_SEND))
                    .setDataStoreFactory(dataStoreFactory)
                    .setAccessType("offline").build();
            TokenResponse response = new TokenResponse();
            response.setRefreshToken(refreshToken);
            flow.createAndStoreCredential(response, userId);
            credential = flow.loadCredential(userId);
            logger.info("Load:" + credential);
        } catch (Exception e) {
            logger.error("Error", e);
        }
        return credential;
    }

    @Override
    public boolean sendMessage(String toEmailAddress, String subject, String bodyText) {
        try {
            Gmail service = new Gmail.Builder(new NetHttpTransport(),
                    GsonFactory.getDefaultInstance(), getCredential())
                    .setApplicationName("scamx")
                    .build();

            // Encode as MIME message
            String fromEmailAddress = "scamx <noreply@gmail.com>";
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress(fromEmailAddress));
            email.addRecipient(javax.mail.Message.RecipientType.TO,
                    new InternetAddress(toEmailAddress));
            email.setSubject(subject);
            email.setContent(bodyText, "text/html; charset=utf-8");

            // Encode and wrap the MIME message into a gmail message
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
            Message message = new Message();
            message.setRaw(encodedEmail);

            // Create send message
            message = service.users().messages().send("me", message).execute();
            if (message != null)
                return true;
        } catch (Exception e) {
            logger.error("Error", e);
        }
        return false;
    }
}
