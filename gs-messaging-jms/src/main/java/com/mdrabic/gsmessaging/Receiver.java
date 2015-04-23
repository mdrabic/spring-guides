package com.mdrabic.gsmessaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.File;

@Component
public class Receiver {

    /**
     * Get a copy of the application context
     */
    @Autowired
    ConfigurableApplicationContext context;

    @JmsListener(destination = "mailbox-dest", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        context.close();
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    }
}
