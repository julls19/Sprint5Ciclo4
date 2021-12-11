
package mono;
import cco.deivisjoro.apirestg22.bd.Conexion;
import cco.deivisjoro.apirestg22.dao.UsuarioDAO;
import cco.deivisjoro.apirestg22.modelos.Usuario;
import filtros.AutorizacionFiltro;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class UsuarioControlador {
    private Connection conexion = Conexion.getInstancia().conectar();
    private UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Usuario login(Usuario usuario){
        
        Usuario u = this.usuarioDAO.login(usuario);
        String token = "";
        if(u.getId()!=0){
            usuario.setId(u.getId());
            
            long tiempo = System.currentTimeMillis();
            
            //generar un token
            token = Jwts.builder()
                        .signWith(SignatureAlgorithm.HS256, AutorizacionFiltro.KEY)
                        .setSubject(usuario.getUsuario())
                        .setIssuedAt(new Date(tiempo))
                        .setExpiration(new Date(tiempo + 900000))
                        .claim("usuario", usuario.getUsuario())
                        .claim("tipo", usuario.getTipo())
                        .compact();
                        
        }
        usuario.setToken(token);
        usuario.setPassword("");
        
        return usuario;
    }
    
}
