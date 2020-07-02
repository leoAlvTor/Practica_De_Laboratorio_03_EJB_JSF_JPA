package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getProductosBodega",
                query = "SELECT productosList_CODIGO from PRODUCTO_BODEGA where bodegasList_CODIGO = ?",
                resultClass = String.class
        )
})
public class Bodega implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    private String nombre;

    @ManyToOne
    private Ciudad ciudad;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "bodegasList")
    private List<Producto> productosList;

    public Bodega(){}

    public Bodega(String nombre, Ciudad ciudad, List<Producto> productosList) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.productosList = productosList;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Producto> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Producto> productosList) {
        this.productosList = productosList;
    }

    public boolean agregarProducto(Producto producto){
        return this.productosList.add(producto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bodega)) return false;
        Bodega bodega = (Bodega) o;
        return codigo == bodega.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", ciudad=" + ciudad +
                ", productosList=" + productosList +
                '}';
    }
}
