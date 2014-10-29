package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoJugador;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMDatosJugadoresLocal;
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

	@EJB
	private EJBEMDatosJugadoresLocal datosJugadores;

	@Override
	public void ImportarJugadores(int i) {
		try {

			String pattern;
			Pattern r;
			Matcher m;
			Random rnd = new Random();

			ArrayList<String> posGolero = new ArrayList<String>();
			posGolero.add("GK");
			posGolero.add("POR");

			ArrayList<String> posDefensa = new ArrayList<String>();
			posDefensa.add("CAD");
			posDefensa.add("RWB");
			posDefensa.add("LTD");
			posDefensa.add("RB");
			posDefensa.add("DFC");
			posDefensa.add("CB");
			posDefensa.add("LTI");
			posDefensa.add("LF");
			posDefensa.add("CAI");
			posDefensa.add("LWB");
			posDefensa.add("CB");
			posDefensa.add("LCB");
			posDefensa.add("RCB");
			posDefensa.add("LB");
			posDefensa.add("RB");

			ArrayList<String> posMediocampista = new ArrayList<String>();
			posMediocampista.add("MCD");
			posMediocampista.add("CDM");
			posMediocampista.add("MD");
			posMediocampista.add("RM");
			posMediocampista.add("MC");
			posMediocampista.add("CM");
			posMediocampista.add("MI");
			posMediocampista.add("LM");
			posMediocampista.add("MCO");
			posMediocampista.add("CAM");
			posMediocampista.add("CM");
			posMediocampista.add("LDM");
			posMediocampista.add("RDM");
			posMediocampista.add("CDM");
			posMediocampista.add("CAM");
			posMediocampista.add("LM");
			posMediocampista.add("RM");

			ArrayList<String> posDelantero = new ArrayList<String>();
			posDelantero.add("SDD");
			posDelantero.add("RF");
			posDelantero.add("MP");
			posDelantero.add("CF");
			posDelantero.add("SDI");
			posDelantero.add("LF");
			posDelantero.add("ED");
			posDelantero.add("RW");
			posDelantero.add("DC");
			posDelantero.add("ST");
			posDelantero.add("EI");
			posDelantero.add("LW");

			// Hago un escaneo hasta 20000 pero hay menos jugadores
			// for (int i = 1; i < 13000; i++) {
			try {
				Document doc = Jsoup.connect("http://wefut.com/en/player/" + i + "/14")
						.get();

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

				List<Habilidad> habilidades = new LinkedList<Habilidad>();
				Habilidad habilidad = null;

				habilidad = new Habilidad(rnd.nextInt(40) + 30,
						EnumHabilidad.VELOCIDAD, "Velocidad");
				habilidades.add(habilidad);
				habilidad = new Habilidad(rnd.nextInt(40) + 30,
						EnumHabilidad.PORTERIA, "Porteria");
				habilidades.add(habilidad);

				habilidad = new Habilidad(rnd.nextInt(40) + 30,
						EnumHabilidad.DEFENSA, "Defensa");
				habilidades.add(habilidad);

				habilidad = new Habilidad(rnd.nextInt(40) + 30,
						EnumHabilidad.ATAQUE, "Ataque");
				habilidades.add(habilidad);

				habilidad = new Habilidad(rnd.nextInt(40) + 30,
						EnumHabilidad.TECNICA, "Tecnica");
				habilidades.add(habilidad);
				
				j.setHabilidades(habilidades);

				// PARSEO DE LA TARJETA DEL JUGADOR
				Element card = doc.select(".card-column").first();

				// POSISION
				String posicion = card.select(".position").first().text();
				// Taduccion de la posision fifa a la posision hattrik

				if (posGolero.contains(posicion)) {
					j.setPuesto(EnumPuestoJugador.ARQUERO);
					j.setNro_Camiseta(1);
				} else if (posDefensa.contains(posicion)) {
					j.setPuesto(EnumPuestoJugador.DEFENSA);
					j.setNro_Camiseta(rnd.nextInt(4) + 2);// Camisetas entre
															// 2 y 5
				} else if (posMediocampista.contains(posicion)) {
					j.setPuesto(EnumPuestoJugador.MEDIOCAMPISTA);
					j.setNro_Camiseta(rnd.nextInt(3) + 6);// Camisetas entre
															// 6 y 8
				} else if (posDelantero.contains(posicion)) {
					j.setPuesto(EnumPuestoJugador.DELANTERO);
					j.setNro_Camiseta(rnd.nextInt(3) + 9);// Camisetas entre
															// 9 y 11
				} else {
					// En caso de no matchear va como defensa
					j.setPuesto(EnumPuestoJugador.DEFENSA);
					j.setNro_Camiseta(rnd.nextInt(4) + 2);// Camisetas entre
															// 2 y 5
				}

				jugadores.add(j);

				// URL IMAGEN JUGADOR
				String urlImagen = card.select(".avatar").first().attributes()
						.get("src");
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
				//break;
				throw e;
			}
			// }

		} catch (Exception e) {
			System.out.println("Error " + " General"); 
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

			System.out.println("Error " + imageUrl); 
		}
	}

}
