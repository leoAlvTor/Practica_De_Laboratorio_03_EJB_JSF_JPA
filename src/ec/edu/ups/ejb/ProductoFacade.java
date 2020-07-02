package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Producto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public ProductoFacade(){
        super(Producto.class);
        this.entityManager = this.entityManager;
    }

    public Producto buscarProducto(String nombre){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> criteriaQuery = criteriaBuilder.createQuery(Producto.class);
        Root<Producto> usuarioRoot=criteriaQuery.from(Producto.class);
        Predicate predicate = criteriaBuilder.equal(usuarioRoot.get("nombre"),nombre);
        criteriaQuery.select(usuarioRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected  EntityManager getEntityManager(){
        return entityManager;
    }

}
