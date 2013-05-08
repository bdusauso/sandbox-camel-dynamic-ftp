package ec.ep.europarl.camel.component;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.file.remote.FtpComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bruno Dusausoy
 */
@Component
public class FtpSender {

    public static final String INPUT_DIR = "input";
    public static final String OUTPUT_DIR = "output";
    public static final String FTP_PATTERN = "ftp://localhost:2121/{0}?fileName={1}";

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    ConsumerTemplate consumerTemplate;

    @PostConstruct
    public void send() throws InterruptedException {
        File file = new File("src/data/message1.xml");
        Map<String, Object> headers = new HashMap<String, Object>();
        String fileName = file.getName();
        headers.put("CamelFileName", fileName);
        producerTemplate.sendBody(createInputFtpUri(fileName), file);
        Thread.sleep(10000);
        Object received = consumerTemplate.receiveBody(createOutputFtpUri(fileName));
    }

    private String createInputFtpUri(String filename) {
        return MessageFormat.format(FTP_PATTERN, INPUT_DIR, filename);
    }

    private String createOutputFtpUri(String filename) {
        return MessageFormat.format(FTP_PATTERN, OUTPUT_DIR, filename) + "&delete=true";
    }
}
