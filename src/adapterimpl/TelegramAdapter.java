package adapterimpl;
import external.ServicioTelegram;
import adapterinterface.MedioNotificacion;

// Adapter para Telegram
public class TelegramAdapter implements MedioNotificacion {
    private ServicioTelegram telegram;
    private String chatId;
    
    public TelegramAdapter(String chatId) {
        this.telegram = new ServicioTelegram();
        this.chatId = chatId;
    }
    
    @Override
    public void enviarNotificacion(String mensaje) {
        telegram.publicarMensaje(chatId, mensaje, false);
    }
}