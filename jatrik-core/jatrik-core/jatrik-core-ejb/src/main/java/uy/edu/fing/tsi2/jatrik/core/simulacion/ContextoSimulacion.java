/**
 * Para definir que evento simular se utiliza el patrón strategy.
 * Esta clase es el contexto donde se selecciona la estrategia a utilizar.
 * En el contexto contamos con todas las estrategias posibles, y con un peso
 * asociado a cada una. El cliente del contexto sortea un numero y se lo pasa al contexto
 * para que este elija una estrategia en funcion del numero sorteado y del peso de 
 * las estrategias.
 * 
 * El numero se sortea en el cliente y no en el contexto, para lograr que este 
 * contexto no tenga estado. El cliente es el que tiene estado.
 */
package uy.edu.fing.tsi2.jatrik.core.simulacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.reflections.Reflections;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias.EstrategiaHito;
import uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias.EstrategiaSimulacion;

/**
 *
 * @author Farid
 */

@Stateless
public class ContextoSimulacion {
	
	List<EstrategiaSimulacion> estrategias = new ArrayList<>();
	
	@PostConstruct
	public void setup(){
		Reflections reflections = 
				new Reflections("uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias");
		Set<Class<? extends EstrategiaSimulacion>> clasesEstrategias = 
				  reflections.getSubTypesOf(EstrategiaSimulacion.class);
		for(Class claseEstrategia : clasesEstrategias){
			EstrategiaSimulacion estrategia = 
					(EstrategiaSimulacion) getContextualBeanInstance(claseEstrategia);
			estrategias.add(estrategia);

		}
	}
	
	public int getPesoTotal(){
		int total = 0;
		for(EstrategiaSimulacion estrategia : estrategias){
			total += estrategia.getPeso();
		}
		return total;
	}
	
	public void ejecutarEstrategia(int random, Partido partido){
		EstrategiaSimulacion estrategia = null;
		if(partido.getMinuto() == 1 
				|| partido.getMinuto() == 45
				|| partido.getMinuto() == 46 
				|| partido.getMinuto() == 90){
			estrategia = getContextualBeanInstance(EstrategiaHito.class);
		}
		else{
		
			int pesoActual = 0;
			for(EstrategiaSimulacion estrategiaSorteo : estrategias){
				pesoActual += estrategiaSorteo.getPeso();
				if(random < pesoActual){
					estrategia = estrategiaSorteo;
					break;
				}
			}
			
		
		}
		estrategia.manejarEvento(partido);
	}
	
	public static <B> B getContextualBeanInstance(Class<B> type) {
    try {
        BeanManager beanManager = InitialContext.doLookup("java:comp/BeanManager");
        Set<Bean<?>> beans = beanManager.getBeans(type);
        Bean<?> bean = beanManager.resolve(beans);
        CreationalContext<?> cc = beanManager.createCreationalContext(bean);
        return (B) beanManager.getReference(bean, type, cc);
    } catch (NamingException e) {
        throw new RuntimeException("", e);
    }
}
}
