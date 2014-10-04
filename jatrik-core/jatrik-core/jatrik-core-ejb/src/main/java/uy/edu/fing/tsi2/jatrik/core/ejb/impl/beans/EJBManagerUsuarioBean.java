package uy.edu.fing.tsi2.jatrik.core.ejb.impl;


import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;
import uy.edu.fing.tsi2.jatrik.core.ejb.UsuarioEJBLocal;




/**
 *
 * @author c753388
 */
@Stateless
public class UsuarioEJB implements UsuarioEJBLocal {

    @PersistenceContext(name = "Jatrik-ejbPU")
    private EntityManager em;
    
    private static final Logger logger = Logger.getLogger(UsuarioEJB.class.getName());
        
	@Override
    public Long crearUsuario(String nombre,String mail,String nick,String password){
       try{ 
            Usuario usr = new Usuario();
            usr.setNick(nick);
            usr.setNombre(nombre);
            usr.setEmail(mail);
            usr.setPassword(password);
            em.persist(usr);
			return usr.getId();
       }catch(Exception e){
            logger.severe(e.getMessage());
			throw e;
            
       }
    }
    
    public Usuario findUsuarioById(Long id){
        Usuario user = null;
        try {
             user = em.find(Usuario.class, id);
        } catch (Exception e) {
             logger.severe(e.getMessage());
        }       
        return user;
    }
    
    public Boolean findUsuarioByNickPassword(String nick,String pass){
        try {
            Query queryUsuario  = em.createNamedQuery("findUsuarioByNickPass");
            List <Usuario> usuarios= queryUsuario.getResultList();
            return (usuarios != null && usuarios.size()>0);
        } catch (Exception e) {
        }
        return false;
    }
    
    public Usuario findUsuarioByNick(String nick){
       Usuario user = null;
         try {
              Query queryUsuario = em.createNamedQuery("findUsuarioByNick");
              queryUsuario.setParameter("nick",nick);
              return (Usuario) queryUsuario.getSingleResult();
         } catch (Exception e) {
              logger.severe(e.getMessage());
         }
         return user;
    }
    
    public boolean findUsuarioByUser(String nick){
        
          try {
               Query queryUsuario = em.createNamedQuery("findUsuarioByNick");
               queryUsuario.setParameter("nick",nick);
               return queryUsuario.getResultList().isEmpty();
              
          } catch (Exception e) {
               logger.severe(e.getMessage());
          }
          return false;
     }
    
    public void borrarUsuario(Usuario user){
        try {
            em.remove(user);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
     }
    
    
}
