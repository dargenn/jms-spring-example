package io.dargenn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @JmsListener(destination = "jmsQueue", containerFactory = "deJmsContainerFactory")
    @Transactional(rollbackFor = RuntimeException.class)
    public void receiveMessage(String message) {
        LOGGER.info("Message received (FROM QUEUE): {}", message);
    }

    @JmsListener(destination = "jmsTopic", containerFactory = "deJmsContainerFactory")
    @Transactional(rollbackFor = RuntimeException.class)
    public void receiveAnotherMessage1(String message) {
        LOGGER.info("Message received (FROM TOPIC1): {}", message);
    }

    @JmsListener(destination = "jmsTopic", containerFactory = "deJmsContainerFactory")
    @Transactional(rollbackFor = RuntimeException.class)
    public void receiveAnotherMessage2(String message) {
        LOGGER.info("Message received (FROM TOPIC2): {}", message);
    }
}
