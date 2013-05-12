package ec.ep.europarl.camel.routebuilder;

import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Bruno Dusausoy
 */
@Component
public class SimpleRouteBuilder extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler()
                .maximumRedeliveries(5)
                .backOffMultiplier(2)
                .retryAttemptedLogLevel(LoggingLevel.WARN));

        from("file:src/data?noop=true")
                .to("ftp://${hostname}:${properties.port}/${properties.inputDir}?username=${properties.user}&password=${properties.password}&fileName=${header.CamelFileNameOnly}")
                .to("direct:ftpSender");

/*
        from("direct:ftpSender")
                .delayer(10000)
                .to("ftp://admin@localhost:2121/output?password=admin&fileName=${header.CamelFileNameOnly}")
                .log("End route");
*/
    }
}
