package ec.edu.ups.controlador;

import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidad.Usuario;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class LogInBean implements Serializable {
    private static final long serialVersionUID=1L;
    private String correo;
    private String password;
    private String msg;
    @EJB
    private UsuarioFacade ejbUsuarioFacade;

    private String cookie;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;

    }

    public String getPassword() {
        return password;

    }

    public void setPassword(String password) {
        this.password = password;

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String validateUser(){
        Usuario user= ejbUsuarioFacade.logIn(correo,password);
        if(user == null){
            return "El atributo es nulo";
        }else{
            createCookie(user.getCorreo());
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/private/paginaAdministrador.xhtml");

        }
        return "";
    }

    private void createCookie(String correo){
        String name = "session";
        String value = correo;
        Map<String, Object> properties = new HashMap<>();
        properties.put("maxAge", 5000);
        properties.put("path", "/");
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            System.out.println("Se creo la cookie");
            externalContext.addResponseCookie(name, URLEncoder.encode(value, "UTF-8"), properties);
        }catch (Exception e){
            System.out.println("Algo salio mal Cookie");
            e.printStackTrace();
        }
    }

    public String getCookie() {
        try {
            Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get("cookie_session");
            String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
            return "Valor cookie: " + value;
        }catch (Exception e){
            e.printStackTrace();
            return "No existe la cookie!";
        }
    }
}