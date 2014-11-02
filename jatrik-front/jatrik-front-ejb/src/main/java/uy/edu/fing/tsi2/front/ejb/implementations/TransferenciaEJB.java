package uy.edu.fing.tsi2.front.ejb.implementations;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import uy.edu.fing.tsi2.front.ejb.interfaces.TransferenciaEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferencia;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferenciaCompra;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferenciaVenta;

@Stateless
public class TransferenciaEJB  implements TransferenciaEJBLocal{

  @EJB
  private RestClientEJBLocal jatrikCoreClient;

  public List<InfoTransferencia> getTransferencias()
  {
    return this.jatrikCoreClient.getTransferencias();
  }

  public void ponerEnVentaJugador(Long idEquipoVende, Long idJugador, Double precio)
  {
    InfoTransferenciaVenta venta = new InfoTransferenciaVenta();
    venta.setIdEquipoVende(idEquipoVende);
    venta.setIdJugador(idJugador);
    venta.setPrecio(precio);
    this.jatrikCoreClient.postTransferenciaVenta(venta);
  }

  public void comprarJugador(Long idEquipoCompra, Long idTransferencia)
  {
    InfoTransferenciaCompra compra = new InfoTransferenciaCompra();
    compra.setIdEquipoCompra(idEquipoCompra);
    compra.setIdTransferencia(idTransferencia);

    this.jatrikCoreClient.postTransferenciaCompra(compra);
  }
}