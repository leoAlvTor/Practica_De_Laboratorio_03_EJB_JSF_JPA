package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Categoria;
import ec.edu.ups.entidad.Producto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public CategoriaFacade(){
        super(Categoria.class);
    }

    @Override
    protected  EntityManager getEntityManager(){
        return entityManager;
    }

    public Categoria getCategoryByName(String nombreCategoria){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Categoria> categoriaCriteriaQuery = criteriaBuilder.createQuery(Categoria.class);
        Root<Categoria> categoriaRoot = categoriaCriteriaQuery.from(Categoria.class);

        categoriaCriteriaQuery = categoriaCriteriaQuery.select(categoriaRoot)
                .where(criteriaBuilder.equal(categoriaRoot.get("nombre"), nombreCategoria));
        return entityManager.createQuery(categoriaCriteriaQuery).getSingleResult();
    }

}
