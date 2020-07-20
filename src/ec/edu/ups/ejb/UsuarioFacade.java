package ec.edu.ups.ejb;
import ec.edu.ups.entidad.Usuario;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.*;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;


    public UsuarioFacade(){
        super(Usuario.class);
    }


    public Usuario logIn(String correo, String password){
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery= criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> usuarioRoot= criteriaQuery.from(Usuario.class);
        Predicate predicate= criteriaBuilder.equal(usuarioRoot.get("correo"),correo);
        Predicate predicate1= criteriaBuilder.equal(usuarioRoot.get("password"),password);
        Predicate validaciones= criteriaBuilder.and(predicate,predicate1);
        criteriaQuery.select(usuarioRoot).where(validaciones);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected  EntityManager getEntityManager(){
        return entityManager;
    }


}
