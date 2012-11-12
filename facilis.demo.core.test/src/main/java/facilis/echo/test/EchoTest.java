package facilis.echo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import junit.framework.TestCase;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.google.common.base.Optional;

import facilis.echo.Echo;

public class EchoTest extends TestCase {

    String actualPrintedMsg = "";

    PrintStream dummyPrintStream = new PrintStream(System.out) {
        @Override
        public void println(String msg) {
            actualPrintedMsg = msg;
        }
    };

    public void testEcho() throws Exception {
        String msg = "Hello World";
        BundleContext context = FrameworkUtil.getBundle(getClass())
                .getBundleContext();
        ServiceReference<Echo> echoReference = context
                .getServiceReference(Echo.class);
        Echo echo = context.getService(echoReference);
        assertNotNull(echo);
        echo.setOut(dummyPrintStream);
        echo.echo(Optional.of(msg));
        assertEquals(msg, actualPrintedMsg);
    }

    public void testHttpEchoResource() throws Exception {
        BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
        ServiceReference<Echo> echoReference = context.getServiceReference(Echo.class);
        Echo echo = context.getService(echoReference);
        assertNotNull(echo);
        echo.setOut(dummyPrintStream);
        
        URL url = new URL("http://localhost:8080/echo?msg=Hello");
        InputStream inputStream = connect(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
        assertEquals("Hello", actualPrintedMsg);
    }

    InputStream connect(URL url) throws InterruptedException {
        InputStream inputStream = null;
        boolean retry = false;
        int numberOfTrials = 0;
        do {
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(3000);
                inputStream = connection.getInputStream();
                retry = false;
            }catch(IOException read){
                Thread.sleep(100);
                retry = numberOfTrials++ < 10;
            }
        } while(retry);
        return inputStream;
    }

}
