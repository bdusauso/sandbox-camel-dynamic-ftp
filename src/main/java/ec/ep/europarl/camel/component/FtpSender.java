package ec.ep.europarl.camel.component;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author Bruno Dusausoy
 */
@Component
public class FtpSender {

    @Autowired
    @Qualifier("producerTemplate")
    ProducerTemplate template;

    @PostConstruct
    public void send() {
        File file = new File("src/data/message1.xml");
        template.sendBody("seda:ftpInput", file);
    }
}
