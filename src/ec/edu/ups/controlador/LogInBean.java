package ec.edu.ups.controlador;

import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidad.Usuario;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import java.io.Serializable;

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
        return "El atributo es nulo";
        }


    }

