package com.michal.mqtt.engine.notifications;

import com.michal.dao.model.rule.Action;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

public class EmailAction implements NotificationAction{

    final static Logger logger = LogManager.getLogger(EmailAction.class);

    private Environment env;


    public EmailAction(Environment env) {
        this.env = env;
    }

    @Override
    public void sendNotification(Action action, String mqttData, String topic) {
        logger.setLevel(Level.ALL);
        try {
            Email email = new SimpleEmail();
            email.setHostName(env.getProperty("email.host"));
            email.setSmtpPort(new Integer(env.getProperty("email.port")));
            email.setAuthenticator(new DefaultAuthenticator((env.getProperty("email.user")), (env.getProperty("email.password"))));
            email.setSSL(true);
            email.setFrom(env.getProperty("email.user"));
            email.setSubject(action.getSubject()+" "+topic);
            //TODO add message extractor
            email.setMsg(action.getMessage());
            email.addTo(action.getAddressee());
            email.send();
        }catch(Exception ex){
            logger.error(ex);
        }
    }
}
