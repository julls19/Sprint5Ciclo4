
package mono;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/productos")
public class ProductoControlador {
     
    private Connection conexion = Conexion.getInstancia().conectar();
    private ProductoDAO productoDAO = new ProductoDAO(conexion);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta<Producto> getProductos(){
        Respuesta<Producto> respuesta = new Respuesta<>();
        List<Producto> productos = this.productoDAO.getProductos();
        
        respuesta.setEstado("OK");
        respuesta.setMensaje("");
        respuesta.setDatos(productos);
        
        return respuesta;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta<Producto> getProducto(@PathParam("id") int id){
        Respuesta<Producto> respuesta = new Respuesta<>();
        Producto producto = this.productoDAO.getProducto(id);
        List<Producto> productos = new ArrayList<>();
        
        if(producto.getId()!=0){
            respuesta.setEstado("OK");
            respuesta.setMensaje("OK");
            productos.add(producto);
            respuesta.setDatos(productos);
        }
        else{
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No se encontraron registros");
        }
        
        
        return respuesta;
    }
    
    /*
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto getProducto(@PathParam("id") int id){       
        return this.productoDAO.getProducto(id);
    }
    
    */
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Producto addProducto(Producto producto){
        return this.productoDAO.addProducto(producto);
    }
    
    
}
