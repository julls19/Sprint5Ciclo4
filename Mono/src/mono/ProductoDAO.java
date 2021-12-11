
package mono;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO {
    private Connection conexion;
    
    public ProductoDAO(Connection conexion){
        this.conexion = conexion;
    }
    
    public List<Producto> getProductos(){
        List<Producto> productos = new ArrayList<>();
        try{
            String sql = "SELECT * FROM productos";
            PreparedStatement pst = this.conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
                productos.add(producto);
            }
        }
        catch(SQLException e){
            System.out.println("Error en SQL: "+e.getMessage());
        }
        
        return productos;
    }
    
    public Producto getProducto(int id){
        Producto producto = new Producto();
        try{
            String sql = "SELECT * FROM productos WHERE id = ?";
            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){                
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
                return producto;
            }
        }
        catch(SQLException e){
            System.out.println("Error en SQL: "+e.getMessage());
        }
        
        return producto;
    }
    
    public Producto addProducto(Producto producto){        
        try{
            String sql = "INSERT INTO productos VALUES (?,?,?,?,?)";
            PreparedStatement pst = this.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, 0);
            pst.setString(2, producto.getNombre());
            pst.setFloat(3, producto.getPrecio());
            pst.setFloat(4, producto.getCantidad());
            pst.setInt(5, 1);
            
            int registros = pst.executeUpdate();
            
            if(registros>0){
                ResultSet rs = pst.getGeneratedKeys();
                while(rs.next()){
                    producto.setId(rs.getInt(1));
                    return producto;
                }                
            }
        }
        catch(SQLException e){
            System.out.println("Error en SQL: "+e.getMessage());
        }
        
        return producto;
    }
    
    
    public boolean editProducto(Producto producto){        
        boolean resultado = false;
        try{
            String sql = "UPDATE productos SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?";
            PreparedStatement pst = this.conexion.prepareStatement(sql);
            
            pst.setString(1, producto.getNombre());
            pst.setFloat(2, producto.getPrecio());
            pst.setFloat(3, producto.getCantidad());
            pst.setInt(4, producto.getId());
            
            int registros = pst.executeUpdate();
            
            if(registros>0){
                resultado = true;
            }
        }
        catch(SQLException e){
            System.out.println("Error en SQL: "+e.getMessage());
        }
        
        return resultado;
    }
    
    public boolean deleteProducto(int id){        
        boolean resultado = false;
        try{
            String sql = "DELETE FROM productos WHERE id = ?";
            PreparedStatement pst = this.conexion.prepareStatement(sql);
                        
            pst.setInt(1, id);
            
            int registros = pst.executeUpdate();
            
            if(registros>0){
                resultado = true;
            }
        }
        catch(SQLException e){
            System.out.println("Error en SQL: "+e.getMessage());
        }
        
        return resultado;
    }
    
}
