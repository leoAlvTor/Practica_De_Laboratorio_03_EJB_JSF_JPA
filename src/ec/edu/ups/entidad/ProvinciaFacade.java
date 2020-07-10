package ec.edu.ups.entidad;

import ec.edu.ups.ejb.AbstractFacade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProvinciaFacade extends AbstractFacade<Provincia> {
    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public ProvinciaFacade(){
        super(Provincia.class);
    }

    @Override
    protected EntityManager getEntityManager(){
        return entityManager;
    }
}