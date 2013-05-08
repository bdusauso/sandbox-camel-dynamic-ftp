package ec.ep.europarl.camel.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Bruno Dusausoy
 */
@Component
public class FtpProperties {

    private final String inputDirectory;
    private final String outputDirectory;
    private final String hostname;
    private final String username;
    private final String password;
    private final boolean deleteAfterConsumption;
    private final int port;

    @Autowired
    public FtpProperties(@Value("${hostname:localhost}") String hostname,
                         @Value("${inputDir:input}") String inputDirectory,
                         @Value("${outputDir:output}") String outputDirectory,
                         @Value("${user:}") String username,
                         @Value("${password:}") String password,
                         @Value("${port:21}") int port,
                         @Value("${deleteAfter:true}") boolean deleteAfterConsumption) {
        this.hostname = hostname;
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
        this.username = username;
        this.password = password;
        this.port = port;
        this.deleteAfterConsumption = deleteAfterConsumption;
    }

    public String getHostname() {
        return hostname;
    }

    public String getInputDirectory() {
        return inputDirectory;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDeleteAfterConsumption() {
        return deleteAfterConsumption;
    }
}
