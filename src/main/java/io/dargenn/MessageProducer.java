package io.dargenn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class MessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    void send(final String message)  {
        MessageCreator messageCreator = session -> session.createTextMessage(message);

        LOGGER.info("Sending message: {}", message);
        this.jmsTemplate.send("jmsQueue", messageCreator);
        this.jmsTemplate.send("jmsTopic", messageCreator);
        this.jmsTemplate.send("jmsTopic2", messageCreator);
    }

}
