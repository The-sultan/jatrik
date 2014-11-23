
package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerLigaBeanLocal;

/**
 *
 * @author Farid
 */
@Startup
@Singleton
public class AppStartup {
	@EJB
	private EJBManagerLigaBeanLocal ligasEJB;
	
	@PostConstruct
	public void init(){
		ligasEJB.initLigas();
	}
	
}
