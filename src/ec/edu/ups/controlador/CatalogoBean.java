package ec.edu.ups.controlador;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class CatalogoBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private String producto;
    private List<String> stringas;
    private List<String> filtrado;

    public CatalogoBean(){
        producto = "";
        stringas = new ArrayList<>();
        filtrado = new ArrayList<>();

        stringas.add("Leo");
        stringas.add("Leonardo");
        stringas.add("Leito");
        stringas.add("Isra");
        stringas.add("Israel");
        stringas.add("Israel Chuchuca");
        stringas.add("Jonnathan");
        stringas.add("Jonnathan Sicha");
        stringas.add("Pedro");
        stringas.add("Pedro Illaisaca");

    }

    public void llamar(AjaxBehaviorEvent event){
        producto = buscarProducto((String) ((UIOutput) event.getSource()).getValue());
    }

    public String getText() {
        return producto;
    }

    public String[] getFiltrado() {
        return new String[]{"Leo", "Alvarado"};
    }

    public void setFiltrado(List<String> filtrado) {
        this.filtrado = filtrado;
    }

    private String buscarProducto(String producto){
        filtrado = stringas.stream().filter(value -> value.toUpperCase().contains(producto.toUpperCase())).collect(Collectors.toList());

        if(filtrado.isEmpty())
            return "No se ha encontrado ningun coincidencia";
        return filtrado.toString();
    }

    public void setText(String producto) {
        this.producto = producto;
    }
}