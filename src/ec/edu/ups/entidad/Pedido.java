package ec.edu.ups.entidad;

import org.primefaces.json.JSONObject;


import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@Entity
public class Pedido implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String estado;

    private GregorianCalendar fecha_emision;
    @ManyToOne
    private Persona persona;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "pedido")
    private FacturaCabecera facturaCabecera;



    public Pedido() {

    }

    public Pedido(String estado, GregorianCalendar fecha_emision, Persona persona, FacturaCabecera facturaCabecera) {
        this.estado = estado;
        this.fecha_emision = fecha_emision;
        this.persona = persona;
        this.facturaCabecera = facturaCabecera;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @JsonbDateFormat (value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    public GregorianCalendar getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(GregorianCalendar fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public FacturaCabecera getFacturaCabecera() {
        return facturaCabecera;
    }

    public void setFacturaCabecera(FacturaCabecera facturaCabecera) {
        this.facturaCabecera = facturaCabecera;
    }


    public static List<Pedido> serializePedidos(List<Pedido> pedidos){
        List<Pedido> pedidoList = new ArrayList<>();

        pedidos.forEach(
                pedido -> {


                    int codigo = pedido.getCodigo();
                    String estado = pedido.getEstado();
                    Persona persona = pedido.getPersona();
                    persona.setFacturasCabeceraList(null);
                    persona.setPedidos(null);
                    FacturaCabecera facturaCabecera2 = pedido.facturaCabecera;
                    facturaCabecera2.setFecha(GregorianCalendar.from(ZonedDateTime.now()));
                    pedido.setFecha_emision(GregorianCalendar.from(ZonedDateTime.now()));
                    pedido.setCodigo(codigo);
                    pedido.setEstado(estado);
                    pedido.setPersona(persona);
                    pedido.setFacturaCabecera(facturaCabecera2);

                    pedidoList.add(pedido);
                }
        );
        return pedidoList;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return codigo == pedido.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigo=" + codigo +
                ", estado='" + estado + '\'' +
                ", fecha_emision=" + fecha_emision +
                ", persona=" + persona +
                ", facturaCabecera=" + facturaCabecera +
                '}';
    }
}