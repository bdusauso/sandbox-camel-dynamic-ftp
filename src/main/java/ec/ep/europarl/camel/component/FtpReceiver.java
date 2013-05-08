package ec.ep.europarl.camel.component;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Bruno Dusausoy
 */
@Component
public class FtpReceiver {

    @Autowired
    ConsumerTemplate consumerTemplate;

    private final FtpProperties ftpProperties;

    @Autowired
    public FtpReceiver(FtpProperties ftpProperties) {
        this.ftpProperties = ftpProperties;
    }

    @Handler
    public void receive(String fileName) {
        String uri = createOutputFtpUri(fileName);
        consumerTemplate.receiveBody(uri);
    }

    private String createOutputFtpUri(String filename) {
        StringBuilder builder = new StringBuilder("ftp://")
                .append(ftpProperties.getUsername()).append('@')
                .append(ftpProperties.getHostname()).append(':')
                .append(ftpProperties.getPort()).append('/')
                .append(ftpProperties.getOutputDirectory()).append('?')
                .append("password=").append(ftpProperties.getPassword()).append('&')
                .append("fileName=").append(filename).append('&')
                .append("delete=true");

        return builder.toString();
    }
}
