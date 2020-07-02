package ec.edu.ups.controlador;

import ec.edu.ups.ejb.PaisFacade;
import ec.edu.ups.entidad.Pais;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PaisBean {
    private PaisFacade paisFacade;
    private String nombre;


    public PaisBean(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Pais consultarPais(String nombre){
        Pais p=paisFacade.find(nombre);
        if (p ==null){
            paisFacade.create(new Pais(nombre,nombre));
            return (Pais) paisFacade.find(nombre);
        }
        return p;
    }
}
