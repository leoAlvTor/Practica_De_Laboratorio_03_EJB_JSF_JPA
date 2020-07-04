package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Bodega;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BodegaFacade extends AbstractFacade<Bodega>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public BodegaFacade(){
        super(Bodega.class);
    }

    @Override
    protected  EntityManager getEntityManager(){
        return entityManager;
    }





}
