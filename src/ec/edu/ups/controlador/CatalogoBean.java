package ec.edu.ups.controlador;


import ec.edu.ups.ejb.*;
import ec.edu.ups.entidad.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class CatalogoBean implements Serializable{
    private static final long serialVersionUID = 1L;

    @EJB
    private ProductoFacade productoFacade;

    @EJB
    private BodegaFacade bodegaFacade;

    private Map<String, String> producto;
    private List<Producto> productosList;
    private List<Producto> filtrado;
    // Mapa Codigo <-> Nombre
    private Map<String, String> mapaCodigoNombreProducto;

    @PostConstruct
    public void init(){
        productosList = productoFacade.findAll();
        mapaCodigoNombreProducto = new TreeMap<>();
        producto = new TreeMap<>();
    }

    public CatalogoBean(){
        filtrado = new ArrayList<>();
    }

    public void llamar(AjaxBehaviorEvent event){
        producto = buscarProducto((String) ((UIOutput) event.getSource()).getValue());
    }

    public Map<String, String> getProductos() {
        return producto;
    }

    public String[] getFiltrado() {
        return new String[]{"Leo", "Alvarado"};
    }

    public void setFiltrado(List<Producto> filtrado) {
        this.filtrado = filtrado;
    }

    private Map<String, String> buscarProducto(String productoNombre){
        mapaCodigoNombreProducto = new TreeMap<>();
        filtrado = productosList.stream().filter(value -> value.getNombre().toUpperCase().contains(productoNombre.toUpperCase())).collect(Collectors.toList());
        filtrado.forEach(e ->{mapaCodigoNombreProducto.put(String.valueOf(e.getCodigo()), e.getNombre()); });

        if(mapaCodigoNombreProducto.isEmpty())
            return new TreeMap<>();
        else
            return mapaCodigoNombreProducto;
    }

}