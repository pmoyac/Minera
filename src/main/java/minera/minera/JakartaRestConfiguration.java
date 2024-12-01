package minera.minera;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author moya
 */
@ApplicationPath("/api")
public class JakartaRestConfiguration extends Application {
    public JakartaRestConfiguration() {
        System.out.println("Clase configuradora de JAX-RS cargada exitosamente en /api");
    }
}
