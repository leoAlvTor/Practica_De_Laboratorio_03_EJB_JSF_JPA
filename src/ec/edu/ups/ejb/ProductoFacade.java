package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Producto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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
}
