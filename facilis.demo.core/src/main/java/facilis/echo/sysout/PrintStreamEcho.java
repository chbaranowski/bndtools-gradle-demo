package facilis.echo.sysout;

import java.io.PrintStream;

import com.google.common.base.Optional;

import aQute.bnd.annotation.component.Component;

import facilis.echo.Echo;

@Component
public class PrintStreamEcho implements Echo {

    private PrintStream out = System.out;
    
    @Override
    public void echo(Optional<String> msg) {
        out.println(msg.get());
    }

    @Override
    public void setOut(PrintStream out) {
        this.out = out;
    }

}
