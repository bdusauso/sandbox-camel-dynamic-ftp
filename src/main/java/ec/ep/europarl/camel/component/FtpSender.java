package ec.ep.europarl.camel.component;

import org.apache.camel.Handler;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.MessageFormat;

/**
 * @author Bruno Dusausoy
 */
@Component
public class FtpSender {

    private static final String FTP_PATTERN = "ftp://{0}@{1}:{2}/{3}?fileName={4}";

    private final FtpProperties ftpProperties;

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    public FtpSender(FtpProperties ftpProperties) {
        this.ftpProperties = ftpProperties;
    }

    @Handler
    public String send(File file) throws InterruptedException {
        String fileName = file.getName();
        String uri = createInputFtpUri(fileName);
        producerTemplate.sendBody(uri, file);
        return fileName;
    }

    private String createInputFtpUri(String filename) {
        StringBuilder builder = new StringBuilder("ftp://")
                .append(ftpProperties.getUsername()).append('@')
                .append(ftpProperties.getHostname()).append(':')
                .append(ftpProperties.getPort()).append('/')
                .append(ftpProperties.getInputDirectory()).append('?')
                .append("password=").append(ftpProperties.getPassword()).append('&')
                .append("fileName=").append(filename).append('&')
                .append("delete=").append(ftpProperties.isDeleteAfterConsumption());

        return builder.toString();
    }
}
