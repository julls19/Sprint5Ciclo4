
package mono;
import java.util.List;
public class Respuesta<T> {
    
    //OK o ERROR
    private String estado;
    private String mensaje;
    private List<T> datos;

    public Respuesta() {
    }

    public Respuesta(String estado, String mensaje, List<T> datos) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<T> getDatos() {
        return datos;
    }

    public void setDatos(List<T> datos) {
        this.datos = datos;
    }
    
    
}
