package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;

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
    @Transient
    private boolean editable;

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