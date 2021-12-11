
package mono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    private Connection conexion;
    
    public UsuarioDAO(Connection conexion){
        this.conexion = conexion;
    }
    
    
    public Usuario login(Usuario usuario){
        
        try{
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setString(1, usuario.getUsuario());
            pst.setString(2, usuario.getPassword());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){                
                usuario.setId(rs.getInt("id"));
                
                usuario.setTipo(rs.getInt("tipo"));
                return usuario;
            }
        }
        catch(SQLException e){
            System.out.println("Error en SQL: "+e.getMessage());
        }
        
        return usuario;
    }
    
    
}
