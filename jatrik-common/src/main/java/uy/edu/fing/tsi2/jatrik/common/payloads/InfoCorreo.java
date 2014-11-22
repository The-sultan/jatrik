package uy.edu.fing.tsi2.jatrik.common.payloads;

import java.util.Date;

public class InfoCorreo {

    private Long id;

    private Long to;

    private Long from;

    private String nickTo;

    private String nickFrom;

    private String asunto;

    private String mensaje;

    private boolean leido;

    private Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNickTo() {
        return nickTo;
    }

    public void setNickTo(String nickTo) {
        this.nickTo = nickTo;
    }

    public String getNickFrom() {
        return nickFrom;
    }

    public void setNickFrom(String nickFrom) {
        this.nickFrom = nickFrom;
    }

}
