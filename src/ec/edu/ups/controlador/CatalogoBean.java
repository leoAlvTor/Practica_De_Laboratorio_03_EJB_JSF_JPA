package ec.edu.ups.controlador;


import ec.edu.ups.ejb.*;
import ec.edu.ups.entidad.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.FacesConfig;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@ViewScoped
public class CatalogoBean implements Serializable{
    private static final long serialVersionUID = 1L;

    @EJB
    private ProductoFacade productoFacade;

    private Map<String, String> mapaCodigoNombreProductos;
    private List<Producto> productosList;
    private List<Producto> filtrado;
    // Mapa Codigo <-> Nombre
    private Map<String, String> mapaCodigoNombreProducto;
    // Mapa Codigo <-> Producto
    private Map<Integer, Producto> mapaCodigoProducto;
    private Producto producto;
    private boolean render;

    @PostConstruct
    public void init(){
        mapaCodigoNombreProducto = new HashMap<>();
        mapaCodigoProducto = new HashMap<>();
        productosList = productoFacade.findAll();
        productosList.forEach(e->{mapaCodigoProducto.put(e.getCodigo(), e);});

        mapaCodigoNombreProducto = new TreeMap<>();
        mapaCodigoNombreProductos = null;
        producto = new Producto();
    }

    public Producto getProducto(){return this.producto; }
    public void setProducto(Producto producto){this.producto = producto;}

    public CatalogoBean(){
        filtrado = new ArrayList<>();
    }

    public void filtrarProductos(AjaxBehaviorEvent event){
        mapaCodigoNombreProductos = buscarProducto((String) ((UIOutput) event.getSource()).getValue());
        System.out.println(mapaCodigoNombreProductos.isEmpty());

    }

    public void abrirProducto(String param){
        producto = mapaCodigoProducto.get(Integer.parseInt(param));
        System.out.println(producto);
    }

    public Map<String, String> getProductos() {
        System.out.println("<-------------->");
        System.out.println(mapaCodigoNombreProductos != null);
        return mapaCodigoNombreProductos;
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

        if(mapaCodigoNombreProducto.isEmpty()) {
            render=false;
            return null;
        }else
            return mapaCodigoNombreProducto;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean getRender(){
        System.out.println(mapaCodigoNombreProducto);
        render=  !mapaCodigoNombreProducto.isEmpty();
        return render;
    }
}