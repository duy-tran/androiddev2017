package vn.edu.usth.emailclient;

import android.content.Context;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Store;

/**
 * Created by duy on 12/4/16.
 */

public class Shared {

    private static Shared instance;

    public static Shared getInstance(){
        if (instance == null) {
            instance = new Shared();
        }
        return instance;
    }

    private String userName = "";
    private String userEmail;
    private String userPassword;
    private Store store;

    private HashMap<String, Message[]> messagesFolder = new HashMap<>();

    private HashMap<String, MailItem[]> mailItems = new HashMap<>();

    public static final int MAX_MESSAGES = 10;

    public static final String folderInbox = "Inbox";
    public static final String folderSent = "[Gmail]/Sent Mail";
    public static final String folderDraft = "[Gmail]/Drafts";
    public static final String folderSpam = "[Gmail]/Spam";
    public static final String folderTrash = "[Gmail]/Trash";

    public static HashMap<String, Integer> titles;
    static
    {
        titles = new HashMap<>();
        titles.put(folderInbox,R.string.menu_inbox);
        titles.put(folderSent,R.string.menu_sent);
        titles.put(folderDraft,R.string.menu_draft);
        titles.put(folderSpam,R.string.menu_spam);
        titles.put(folderTrash,R.string.menu_trash);
    }

    public String getFolderName(Context context, String label){
        return context.getString(titles.get(label));
    }

    public static final String[] folderNames = {folderInbox,folderSent,folderDraft,folderSpam,folderTrash};

    public MailItem[] getMailItems(String folderName) {
        return mailItems.get(folderName);
    }

    public void setMailItems(String folderName, MailItem[] mailList) {
        mailItems.put(folderName,mailList);
    }

    public Message[] getMessagesFolder(String folderName){
        if (messagesFolder.get(folderName) != null) {
            return messagesFolder.get(folderName);
        }
        return null;
    }

    public void setMessagesFolder(String folderName, Message[] messages){
        messagesFolder.put(folderName,messages);
    }

    public Authenticator getAuthenticator(){
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userEmail,userPassword);
            }
        };
    }

    public Properties sendProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        return props;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
