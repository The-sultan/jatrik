package uy.edu.fing.tsi2.core.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import org.jboss.resteasy.plugins.providers.atom.Content;
import org.jboss.resteasy.plugins.providers.atom.Entry;
import org.jboss.resteasy.plugins.providers.atom.Feed;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerPartidoLocal;

/**
 * @author Farid
 */

@RequestScoped
public class RssResource {
	
	@EJB
	private EJBManagerPartidoLocal partidosEJB;
	
	@GET
	@Produces("application/atom+xml")
	public Feed getLigaNews(){
		List<Partido> partidos = partidosEJB.obtenerPartidosJugados();
		Feed feed = new Feed();
		feed.setTitle("Noticias liga");
		//feed.setDescription("Resultados de los partidos juntados en la liga");
		//feed.setLink("http://jatrik.com");
		for(Partido partido : partidos){
			Entry entry = new Entry();
			entry.setTitle(String.format("%s vs %s en %s", partido.getLocal().getNombre(), partido.getVisitante().getNombre(),
					partido.getLocal().getEstadio()));
			Content content = new Content();
			String contenido = "";
			if(partido.getGolesLocal() == partido.getGolesVisitante()){
				contenido = String.format("Empate entre %s y %s %d a %d",partido.getLocal().getNombre(), partido.getVisitante().getNombre(),
						partido.getGolesLocal(), partido.getGolesVisitante());
			}else if(partido.getGolesLocal() > partido.getGolesVisitante()){
				contenido = String.format("Victoria de %s de local ante  %s %d a %d",partido.getLocal().getNombre(), partido.getVisitante().getNombre(),
						partido.getGolesLocal(), partido.getGolesVisitante());
			}else{
				contenido = String.format("Victoria de %s de visitante ante  %s %d a %d",partido.getVisitante().getNombre(), partido.getLocal().getNombre(),
						partido.getGolesLocal(), partido.getGolesVisitante());
			}
			content.setText(contenido);
			entry.setContent(content);
			feed.getEntries().add(entry);
		}
		return feed;
		
	}
}
