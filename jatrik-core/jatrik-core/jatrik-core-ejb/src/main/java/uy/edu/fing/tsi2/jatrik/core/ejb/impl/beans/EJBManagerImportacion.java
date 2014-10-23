package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import uy.edu.fing.tsi2.jatrik.core.domain.Habilidad;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.ejb.IImportacionDatos;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerImportacionDatosLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerImportacionDatosRemote;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoJugador;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMUsuariosLocal;

@Stateless
@Local(EJBManagerImportacionDatosLocal.class)
@Remote(EJBManagerImportacionDatosRemote.class)
public class EJBManagerImportacion implements IImportacionDatos {

	// @EJB
	// private EJBEMUsuariosLocal usuarios;
	
	private static final Logger logger = Logger
			.getLogger(EJBManagerImportacion.class);
	
	@EJB
	private EJBEMJugadoresLocal jugadores;
	
	@Override
	public void ImportarJugadores() {
		try {

			String pattern;
			Pattern r;
			Matcher m;

			// Hago un escaneo hasta 20000 pero hay menos jugadores
			for (int i = 1; i < 20000; i++) {
				try {
					Document doc = Jsoup.connect(
							"http://wefut.com/en/player/" + i).get();

					// PARSEO DE LA TABLA DE INFORMACION PERSONAL
					Element info = doc.select(".info-column").first();
					Elements filasInfo = info.select("table");

					// NOMBRE
					String nombre = "";
					// APELLIDO
					String apellido = "";
					// NACIMIENTO
					String nacimiento = "";
					// ALTURA
					String altura = "";
					// PESO
					String peso = "";
					// PIERNA PREFERIDA
					String pierna = "";
					// CLUB
					String club = "";
					// LIGA
					String liga = "";
					// NACIONALIDAD
					String nacionalidad = "";

					// CREO EL JUGADOR
					Jugador j = new Jugador();

					for (Iterator iterator = filasInfo.select("tr").iterator(); iterator
							.hasNext();) {
						Element tr = (Element) iterator.next();
						switch (tr.select("td").get(0).text()) {
						case "First name":
							nombre = tr.select("td").get(1).text();
							break;
						case "Last name":
							nombre += " " + tr.select("td").get(1).text();

							j.setNombre(nombre);
							break;
						case "Known as":

							break;
						case "Date of Birth":
							nacimiento = tr.select("td").get(1).text();

							pattern = "(.*) \\((.*)\\)";
							r = Pattern.compile(pattern);
							m = r.matcher(nacimiento);
							if (m.find()) {
								j.setEdad(Integer.parseInt(m.group(2)));
							}

							break;
						case "Height":
							altura = tr.select("td").get(1).text();

							pattern = "(...).*";
							r = Pattern.compile(pattern);
							m = r.matcher(altura);
							if (m.find()) {
								j.setAltura(Double.parseDouble(m.group(1)));
							}

							break;
						case "Weight":
							peso = tr.select("td").get(1).text();

							pattern = "(...).*";
							r = Pattern.compile(pattern);
							m = r.matcher(peso);
							if (m.find()) {
								j.setPeso(Double.parseDouble(m.group(1)));
							}

							break;
						case "Preferred foot":
							pierna = tr.select("td").get(1).text();
							break;
						case "Club":
							club = tr.select("td").get(1).text();
							break;
						case "League":
							liga = tr.select("td").get(1).text();
							break;
						case "Nationality":
							nacionalidad = tr.select("td").get(1).text();
							break;
						case "Weak foot":

							break;
						case "Skillmoves":

							break;
						case "Att. workrate":

							break;
						case "Def. workrate":

						default:
							break;
						}
					}

					// DATOS QUE ESTAN DISPONIBLES PARA SACAR:
					// PHYSICAL Acceleration Reactions Agility Sprint Speed
					// Balance Stamina Jumping Strength
					// MENTAL Aggression Att. Positioning Interceptions Vision
					// TECHNICAL Ball Control Long Shots Crossing Marking Curve
					// Penalties Dribbling Short Pass Finishing Shot Power Free
					// Kick Accuracy Slide Tackle Heading Accuracy Standing
					// Tackle Long Pass Volleys
					
					
					
					j.setEnVenta(false);
					j.setFechaEntrena(new Date());
					j.setHabilidades(new ArrayList<Habilidad>());//SETEAR 50 50 50 50
					j.setNro_Camiseta(0);

					// PARSEO DE LA TARJETA DEL JUGADOR
					Element card = doc.select(".card-column").first();

					// POSISION
					String posision = card.select(".position").first().text();
					// Taduccion de la posision fifa a la posision hattrik
					
					//POR AHORA TODOS DELANTEROS
					j.setPuesto(EnumPuestoJugador.DELANTERO);
					
					jugadores.add(j);

					// URL IMAGEN JUGADOR
					String urlImagen = card.select(".avatar").first()
							.attributes().get("src");
					saveImage(urlImagen, "jugadores", j.getId());

					// URL IMAGEN CLUB
					String urlImagenClub = card.select(".clubbadge").first()
							.attributes().get("src");
					saveImage(urlImagenClub, "clubs", j.getId());

					// URL IMAGEN PAIS NACIONALIDAD
					String urlImagenNacionalidad = card.select(".nationflag")
							.first().attributes().get("src");
					saveImage(urlImagenNacionalidad, "nacionalidades", j.getId());

					// URL FONDO TARJETA
					String urlImagenFondoTarjeta = card.select(".player-card")
							.first().attributes().get("src");




				} catch (Exception e) {
					break;
				}
			}

		} catch (Exception e) {

		}
	}

	public void saveImage(String imageUrl, String folder, long idJugador) {
		URL url;
		try {
			url = new URL(imageUrl);

			String extension = imageUrl.substring(imageUrl.lastIndexOf("."));
			
			
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream("C:\\Imagenes JT\\" + folder
					+ "\\" + Long.toString(idJugador) + "." + extension, false);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
