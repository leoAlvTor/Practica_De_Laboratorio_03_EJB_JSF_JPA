package ec.edu.ups.controlador;


import ec.edu.ups.ejb.PedidoFacade;
import ec.edu.ups.entidad.Pedido;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class PedidoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Pedido> listPedidos;
    private String estadoSelect;
    @EJB
    private PedidoFacade pedidoFacade;
    private  String[] estados={"Receptado", "En proceso", "En camino", "Finalizado"};
    public PedidoBean(){

    }
    @PostConstruct
    public void init(){
        this.listPedidos=pedidoFacade.findAll();

    }

    public String getEstadoSelect() {
        return estadoSelect;
    }

    public void setEstadoSelect(String estadoSelect) {
        this.estadoSelect = estadoSelect;
    }

    public List<Pedido> getListPedidos() {
        return listPedidos;
    }

    public void setListPedidos(List<Pedido> listPedidos) {
        this.listPedidos = listPedidos;
    }

    public PedidoFacade getPedidoFacade() {
        return pedidoFacade;
    }

    public void setPedidoFacade(PedidoFacade pedidoFacade) {
        this.pedidoFacade = pedidoFacade;
    }

    public String[] getEstados() {
        return estados;
    }

    public void setEstados(String[] estados) {
        this.estados = estados;
    }
}
