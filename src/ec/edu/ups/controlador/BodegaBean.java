package ec.edu.ups.controlador;


import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.entidad.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@FacesConfig
@Named
@SessionScoped
public class BodegaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private BodegaFacade ejbBodegaFacade;
    private List<Bodega> bodegas;
    private String nombre;



    public BodegaBean(){

    }

    @PostConstruct
    public void init(){
        bodegas=ejbBodegaFacade.findAll();

    }



    public Bodega[] getBodegas() {
        return bodegas.toArray(new Bodega[0]);
    }



    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }


    public String add (){
        ejbBodegaFacade.create(new Bodega(this.nombre));

        return  null;
    }

    public String deleted(Bodega b){
        ejbBodegaFacade.remove(b);
        bodegas=ejbBodegaFacade.findAll();
        return null;
    }

    public String edit (Bodega b){
        b.setEditable(true);
        return null;
    }

    public String save(Bodega b){
        ejbBodegaFacade.edit(b);
        b.setEditable(false);
        return null;
    }



}
