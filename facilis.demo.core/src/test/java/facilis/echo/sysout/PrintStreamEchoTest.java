package facilis.echo.sysout;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.base.Optional;

public class PrintStreamEchoTest {

    @Mock
    PrintStream mockPrintStream;

    @InjectMocks
    PrintStreamEcho printStreamEcho = new PrintStreamEcho();
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testEcho() {
        String msg = "Hello World";
        printStreamEcho.echo(Optional.of(msg));
        verify(mockPrintStream).println(msg);
    }

}
