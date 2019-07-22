package types;

import logger.TALogger;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeClass;

/***
 * Base class for API as well as UI Tests.
 */
public class TestBase {
    protected Logger Log;

    @BeforeClass
    public void initialization() {
        //Load the Logger class configuration using spring framework.
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TALogger.class);
        TALogger taLogger = context.getBean(TALogger.class);

        // Get Logger object which is used by sub classes.
        Log = taLogger.getLogger(getClass());
    }
}
