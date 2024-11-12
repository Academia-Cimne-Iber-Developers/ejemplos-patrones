package adapterimpl;

import external.ServicioWhatsApp;
import adapterinterface.MedioNotificacion;

// Adapter para WhatsApp
public class WhatsAppAdapter implements MedioNotificacion {
  private ServicioWhatsApp whatsapp;
  private String numeroDestino;
  
  public WhatsAppAdapter(String numeroDestino) {
      this.whatsapp = new ServicioWhatsApp();
      this.numeroDestino = numeroDestino;
  }
  
  @Override
  public void enviarNotificacion(String mensaje) {
      whatsapp.enviarMensajeWhatsApp(numeroDestino, mensaje);
  }
}
