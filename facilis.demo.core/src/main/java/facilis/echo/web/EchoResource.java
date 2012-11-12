package facilis.echo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Dictionary;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

import com.google.common.base.Optional;

import facilis.echo.Echo;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;
import aQute.bnd.annotation.component.Reference;

@Component
public class EchoResource {
    
    private static final String ALIAS = "/echo";

    private Echo echo;
    
    private HttpService httpService;
    
    private HttpServlet echoServlet = new HttpServlet() {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            echo.echo(Optional.of(req.getParameter("msg")));
            PrintWriter writer = resp.getWriter();
            writer.println("<html><body>Echo - OK</body></html>");
        }
    };
    
    @Activate
    public void init() throws ServletException, NamespaceException {
        HttpContext httpContext = httpService.createDefaultHttpContext();
        Dictionary initparams = new Properties();
        httpService.registerServlet(ALIAS, echoServlet, initparams , httpContext);
    }
    
    @Deactivate
    public void destroy() {
        httpService.unregister(ALIAS);
    }
    
    @Reference
    public void setEcho(Echo echo) {
        this.echo = echo;
    }
    
    public void unsetEcho(Echo echo) {
        this.echo = null;
    }

    @Reference
    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }
    
    public void unsetHttpService(HttpService httpService) {
        this.httpService = null;
    }
    
}
