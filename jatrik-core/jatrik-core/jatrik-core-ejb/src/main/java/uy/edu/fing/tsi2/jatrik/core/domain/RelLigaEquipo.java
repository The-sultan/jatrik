package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TABLA_POSICIONES")
public class RelLigaEquipo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1047801663078063137L;

    @EmbeddedId
    private IdRelLigaEquipo id = new IdRelLigaEquipo();

    @ManyToOne(targetEntity = Liga.class)
    @JoinColumn(name = "LIGA_ID", insertable = false, updatable = false)
    private Liga liga;

    @ManyToOne(targetEntity = Equipo.class)
    @JoinColumn(name = "EQUIPO_ID", insertable = false, updatable = false)
    private Equipo equipo;

    @Column(name = "PARTIDOS_JUGADOS")
    private int partidosJugados;

    @Column(name = "PUNTOS")
    private int ptos;

    @Column(name = "GF")
    private int golesAFavor;

    @Column(name = "GC")
    private int golesEnContra;

    @Column(name = "DIFERENCIA")
    private int diferencia;

    @Column(name = "PARTIDOS_GANADOS")
    private int partidosGanados;

    @Column(name = "PARTIDOS_PERDIDOS")
    private int partidosPerdidos;
    
    @Column(name = "PARTIDOS_EMPATADOS")
    private int partidosEmpatados;

    public RelLigaEquipo() {
        super();
    }

    public RelLigaEquipo(Liga liga, Equipo equipo) {
        this.liga = liga;
        this.equipo = equipo;
        this.ptos = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
        this.diferencia = 0;
        this.partidosJugados = 0;
        this.partidosGanados = 0;
        this.partidosPerdidos = 0;
        this.partidosEmpatados = 0;
        this.id.setLigaId(liga.getId());
        this.id.setEquipoId(equipo.getId());
    }

    public IdRelLigaEquipo getId() {
        return id;
    }

    public void setId(IdRelLigaEquipo id) {
        this.id = id;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPtos() {
        return ptos;
    }

    public void setPtos(int ptos) {
        this.ptos = ptos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public int getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(int diferencia) {
        this.diferencia = diferencia;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelLigaEquipo)) {
            return false;
        }
        RelLigaEquipo other = (RelLigaEquipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uy.com.fing.tsi2.entidades.RelLigaEquipo[ id=" + id + " ]";
    }
}
