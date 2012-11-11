package facilis.echo;

import java.io.PrintStream;

import com.google.common.base.Optional;

public interface Echo {

    void echo(Optional<String> msg);
    
    void setOut(PrintStream out);
    
}
