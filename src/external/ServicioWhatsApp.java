package external;

// Clase existente a adaptar con interfaz incompatible
public class ServicioWhatsApp {
  public void enviarMensajeWhatsApp(String telefono, String contenido) {
      System.out.println("WhatsApp -> " + telefono + ": " + contenido);
  }
}