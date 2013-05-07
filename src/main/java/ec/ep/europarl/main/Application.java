package ec.ep.europarl.main;

import org.apache.camel.spring.Main;

/**
 * @author Bruno Dusausoy
 */
public class Application {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.setFileApplicationContextUri("classpath:/META-INF/spring/camel-context.xml");
        main.enableHangupSupport();
        main.run();
    }
}
