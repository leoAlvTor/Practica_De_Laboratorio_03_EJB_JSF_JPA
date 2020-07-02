package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Categoria;
import ec.edu.ups.entidad.Producto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.*;

@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public ProductoFacade(){
        super(Producto.class);
    }

    @Override
    protected  EntityManager getEntityManager(){
        return entityManager;
    }

    public Map<String, String> getProductosPorCategoria(Categoria categoria){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> productoCriteriaQuery = criteriaBuilder.createQuery(Producto.class);
        Root<Producto> productoRoot = productoCriteriaQuery.from(Producto.class);
        productoCriteriaQuery.select(productoRoot)
                .where(
                        criteriaBuilder.equal(productoRoot.get("categoria"), categoria));

         return entityManager.createQuery(productoCriteriaQuery).getResultList()
                 .parallelStream()
                 .collect(Collectors.toMap(Producto::getCodigo, Producto::getNombre)).entrySet()
                 .parallelStream()
                 .collect(Collectors.toMap(entry -> valueOf(entry.getKey()), Map.Entry::getValue));
    }
    private List<Integer> strings;
    public List<Integer> getProductosPorBodega(int codigoBodega){
        strings = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT productosList_CODIGO from PRODUCTO_BODEGA where bodegasList_CODIGO =" + valueOf(codigoBodega));
        query.getResultList().forEach(e-> {
            strings.add(Integer.valueOf(valueOf(e)));
        });
        return strings;
    }
}

















