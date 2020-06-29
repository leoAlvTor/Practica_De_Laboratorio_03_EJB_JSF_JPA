package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Categoria;
import ec.edu.ups.entidad.Producto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
