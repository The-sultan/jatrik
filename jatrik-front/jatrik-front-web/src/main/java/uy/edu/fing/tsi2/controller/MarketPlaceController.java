package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import uy.edu.fing.tsi2.front.ejb.interfaces.TransferenciaEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferencia;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;

@ManagedBean
@RequestScoped
public class MarketPlaceController
  implements Serializable
{

  @Inject
  SessionBeanJatrik sessionBean;

  @EJB
  private TransferenciaEJBLocal transferenciaEJB;
  private List<InfoTransferencia> transferencias;

  @PostConstruct
  public void init()
  {
    this.transferencias = obtenerJugadoresEnVenta();
  }

  public void venderJugador(Long idJugador, Double precio)
  {
    this.transferenciaEJB.ponerEnVentaJugador(this.sessionBean.getInfoUsuario().getInfoEquipo().getId(), idJugador, precio);
  }

  public void comprarJugador(Long idTransferencia)
  {
    this.transferenciaEJB.comprarJugador(this.sessionBean.getInfoUsuario().getInfoEquipo().getId(), idTransferencia);
  }

  public List<InfoTransferencia> obtenerJugadoresEnVenta()
  {
    return this.transferenciaEJB.getTransferencias();
  }

  public List<InfoTransferencia> getTransferencias()
  {
    return this.transferencias;
  }

  public void setTransferencias(List<InfoTransferencia> transferencias) {
    this.transferencias = transferencias;
  }
}