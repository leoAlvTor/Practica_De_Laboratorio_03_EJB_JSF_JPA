package ec.edu.ups.ejb;

import ec.edu.ups.entidad.FacturaCabecera;
import ec.edu.ups.entidad.Producto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FacturaCabeceraFacade extends AbstractFacade<FacturaCabecera> {
    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public FacturaCabeceraFacade(){
        super(FacturaCabecera.class);
    }

    @Override
    protected  EntityManager getEntityManager(){
        return entityManager;
    }
}
