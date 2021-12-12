
package mono;

public class Usuario {
    private int id;
    private String usuario;
    private String password;
    private int tipo;
    private String token;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String password, int tipo, String token) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
    
    
    
    
}
