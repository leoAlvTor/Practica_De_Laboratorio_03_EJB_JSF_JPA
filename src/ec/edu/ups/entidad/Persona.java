package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Persona implements Serializable {

    @Id
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<FacturaCabecera> facturasCabeceraList;

    public Persona(){}

    public Persona(String cedula, String nombre, String apellido, String direccion, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean agregarFacturaCabecera(FacturaCabecera facturaCabecera){
        return facturasCabeceraList.add(facturaCabecera);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return cedula.equals(persona.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
}
