import adapterimpl.*;
import adapterinterface.MedioNotificacion;


// Cliente que usa el sistema de notificaciones
class SistemaNotificaciones {
    private MedioNotificacion notificador;
    
    public SistemaNotificaciones(MedioNotificacion notificador) {
        this.notificador = notificador;
    }
    
    public void alertarUsuario(String mensaje) {
        notificador.enviarNotificacion(mensaje);
    }
}

// Ejemplo de uso
public class AdapterExample {
    public static void main(String[] args) {
        // Crear adaptadores para diferentes servicios
        MedioNotificacion notificadorWhatsApp = new WhatsAppAdapter("+123456789");
        MedioNotificacion notificadorTelegram = new TelegramAdapter("grupo_soporte");
        
        // Usar el sistema con WhatsApp
        SistemaNotificaciones sistema1 = new SistemaNotificaciones(notificadorWhatsApp);
        sistema1.alertarUsuario("¡Servidor reiniciado exitosamente!");
        
        // Usar el mismo sistema con Telegram
        SistemaNotificaciones sistema2 = new SistemaNotificaciones(notificadorTelegram);
        sistema2.alertarUsuario("¡Actualización completada!");
    }
}