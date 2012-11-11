package facilis.echo.test;

import java.io.PrintStream;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.google.common.base.Optional;

import facilis.echo.Echo;

import junit.framework.TestCase;

public class EchoTest extends TestCase {

    String actualPrintedMsg = "";

    PrintStream dummyPrintStream = new PrintStream(System.out){
        @Override
        public void println(String msg) {
            actualPrintedMsg = msg;
        }
    };
    
    public void testEcho() throws Exception {
        String msg = "Hello World";
        BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
        ServiceReference<Echo> echoReference = context.getServiceReference(Echo.class);
        Echo echo = context.getService(echoReference);
        assertNotNull(echo);
        echo.setOut(dummyPrintStream);
        echo.echo(Optional.of(msg));
        assertEquals(msg, actualPrintedMsg);
    }
    
}
