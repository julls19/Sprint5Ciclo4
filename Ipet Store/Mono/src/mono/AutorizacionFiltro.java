
package mono;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.io.IOException;
import java.security.Key;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutorizacionFiltro implements Filter {
    public static final Key KEY = MacProvider.generateKey();

    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest peticion = (HttpServletRequest)request;
        
        String url = peticion.getRequestURI();
        
        HttpServletResponse respuesta = (HttpServletResponse)response;
        respuesta.addHeader("Access-Control-Allow-Origin", "*");
        respuesta.addHeader("Access-Control-Allow-Methods", "*");
        respuesta.addHeader("Access-Control-Allow-Headers", "*");
        
        if(url.contains("/usuarios/login") || url.contains("/usuarios/registro")){
            chain.doFilter(request, response);
        }
        else{
            String token = peticion.getHeader("Authorization");
            //System.out.println("Token: "+token);
            //System.out.println("KEY: "+KEY);
            
            if(token==null || token.trim().equals("")){
                response.setContentType("application/json");
                String salida = "{\"AUTORIZACION\": \"NO_TOKEN\"}";
                response.getWriter().write(salida);
            }
            else{      
                //Bearer 19028109821098290
                //System.out.println("Token al inicio:"+token);
                token = token.substring(7, token.length());
                //System.out.println("Token al final:"+token);
                try{
                    Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
                    
                    //if(url.contains("/admin")){
                        //verificacion de los claims
                    //}
                    
                    chain.doFilter(request, response);
                }
                catch(MalformedJwtException e){
                    response.setContentType("application/json");
                    String salida = "{\"AUTORIZACION\": \"MAL_FORMADO\"}";
                    response.getWriter().write(salida);
                }
                            
            }
            
            
        }
    }

    public void destroy() {
        
    }
    
}
