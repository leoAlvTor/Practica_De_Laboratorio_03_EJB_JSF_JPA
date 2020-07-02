package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Persona;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonaFacade extends AbstractFacade<Persona>{
    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public PersonaFacade(){
        super(Persona.class);
    }

    @Override
    protected  EntityManager getEntityManager(){
        return entityManager;
    }
}