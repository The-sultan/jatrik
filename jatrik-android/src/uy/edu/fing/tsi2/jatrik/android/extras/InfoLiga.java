package uy.edu.fing.tsi2.jatrik.android.extras;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class InfoLiga {

    
    
    private Long id;
    
    private Date fechaInicio;

    private Date fechaFin;

    private String descripcion;
    
   
    // Un map entre el numero de etapa (fecha) y la lista de partidos de dicha
    // etapa
    private Map<Integer, List<InfoPartido>> partidos;

    private List<EquipoPosicionLiga> posisiones;

   

    public Map<Integer, List<InfoPartido>> getPartidos() {
        return partidos;
    }

    public void setPartidos(Map<Integer, List<InfoPartido>> partidos) {
        this.partidos = partidos;
    }

    public List<EquipoPosicionLiga> getPosisiones() {
        return posisiones;
    }

    public void setPosisiones(List<EquipoPosicionLiga> posisiones) {
        this.posisiones = posisiones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    

}
