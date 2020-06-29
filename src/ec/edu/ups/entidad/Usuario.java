package ec.edu.ups.entidad;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Usuario extends Persona implements Serializable {

    private String correo;
    private String password;

    @ManyToOne
    private Rol rol;

    public Usuario(){}

    public Usuario(String correo, String password, Rol rol) {
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String cedula, String nombre, String apellido, String direccion, String telefono, String correo, String password, Rol rol) {
        super(cedula, nombre, apellido, direccion, telefono);
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + rol +
                '}';
    }
}
