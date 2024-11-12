package external;

// Clase existente a adaptar con interfaz incompatible
public class ServicioTelegram {
  public void publicarMensaje(String chatId, String texto, boolean prioritario) {
      System.out.println("Telegram -> " + chatId + ": " + texto + 
                       (prioritario ? " [PRIORITARIO]" : ""));
  }
}