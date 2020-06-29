package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Producto;
import ec.edu.ups.entidad.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public UsuarioFacade(){
        super(Usuario.class);
    }

    @Override
    protected  EntityManager getEntityManager(){
        return entityManager;
    }

}
