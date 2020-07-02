package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    private String nombre;
    private String imagen;
    private double precioCompra;
    private double precioVenta;
    private char iva;
    private int stock;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Bodega> bodegasList;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<FacturaDetalle> facturasDetallesList;

    public Producto(){}

    public Producto( String nombre, String imagen, double precioCompra, double precioVenta, char iva, int stock, List<Bodega> bodegasList, Categoria categoria, List<FacturaDetalle> facturasDetallesList) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.iva = iva;
        this.stock = stock;
        this.bodegasList = bodegasList;
        this.categoria = categoria;
        this.facturasDetallesList = facturasDetallesList;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public char getIva() {
        return iva;
    }

    public void setIva(char iva) {
        this.iva = iva;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Bodega> getBodegasList() {
        return bodegasList;
    }

    public void setBodegasList(List<Bodega> bodegasList) {
        this.bodegasList = bodegasList;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<FacturaDetalle> getFacturasDetallesList() {
        return facturasDetallesList;
    }

    public void setFacturasDetallesList(List<FacturaDetalle> facturasDetallesList) {
        this.facturasDetallesList = facturasDetallesList;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return codigo == producto.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", iva=" + iva +
                ", stock=" + stock +
                ", bodegasList=" + bodegasList +
                ", categoria=" + categoria +
                ", facturasDetallesList=" + facturasDetallesList +
                '}';
    }
}
