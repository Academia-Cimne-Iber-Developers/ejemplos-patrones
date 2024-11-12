// Interfaz Component
interface Decorator {
    String enviar(String mensaje);
}

// Componente Concreto
class MensajeBase implements Decorator {
    @Override
    public String enviar(String mensaje) {
        return mensaje;
    }
}

// Decorador Base
abstract class MensajeDecorator implements Decorator {
    protected Decorator componente;
    
    public MensajeDecorator(Decorator componente) {
        this.componente = componente;
    }
    
    @Override
    public String enviar(String mensaje) {
        return componente.enviar(mensaje);
    }
}

// Decorador Concreto - Convierte el mensaje a mayúsculas
class MayusculasDecorator extends MensajeDecorator {
    public MayusculasDecorator(Decorator componente) {
        super(componente);
    }
    
    @Override
    public String enviar(String mensaje) {
        String mensajeOriginal = super.enviar(mensaje);
        // Aplicando decoración
        return mensajeOriginal.toUpperCase();
    }
}

// Decorador Concreto - Agrega etiquetas HTML
class EtiquetasDecorator extends MensajeDecorator {
    public EtiquetasDecorator(Decorator componente) {
        super(componente);
    }
    
    @Override
    public String enviar(String mensaje) {
        String mensajeOriginal = super.enviar(mensaje);
        return "<p>" + mensajeOriginal + "</p>";
    }
}

// Decorador Concreto - Agrega fecha actual
class FechaDecorator extends MensajeDecorator {
    public FechaDecorator(Decorator componente) {
        super(componente);
    }
    
    @Override
    public String enviar(String mensaje) {
        String mensajeOriginal = super.enviar(mensaje);
        return "[" + java.time.LocalDate.now() + "] " + mensajeOriginal;
    }
}

// Ejemplo de uso
public class DecoratorMainExample {
    public static void main(String[] args) {
        // Mensaje básico
        // Usamos la interfaz Decorator para poder cambiar el comportamiento de los mensajes
        Decorator mensaje = new MensajeBase();
        System.out.println("Básico: " + mensaje.enviar("Hola Mundo"));
        
        // Mensaje con mayúsculas y etiquetas
        mensaje = new MayusculasDecorator(
                    new EtiquetasDecorator(
                        new MensajeBase()));

        /*
            1. `mensaje` tiene una instancia de MayusculasDecorator que a su vez tiene una instancia de EtiquetasDecorator
            2. `mensaje` llama al método enviar de MayusculasDecorator, que a su vez llama al método enviar de EtiquetasDecorator
            3. EtiquetasDecorator llama al método enviar de MensajeBase
            4. MensajeBase devuelve el mensaje original
            5. EtiquetasDecorator agrega las etiquetas HTML
            6. MayusculasDecorator convierte el mensaje a mayúsculas
            7. Se devuelve el mensaje decorado a mensaje
        */
        System.out.println("Decorado: " + mensaje.enviar("Hola Mundo"));

        /*
            1. Etiqueta llama al enviar de MayusculasDecorator componente.enviar(mensaje) (mayúsculas)
            2. Mayúsculas llama al enviar de MensajeBase componente.enviar(mensaje) (mensaje original)
            3. MensajeBase devuelve el mensaje original
        */

        // Mensaje con fecha, mayúsculas y etiquetas - probar combinación de decoradores
        
        mensaje = new FechaDecorator(
                    new EtiquetasDecorator(
                        new MayusculasDecorator(
                            new MensajeBase())));
        System.out.println("Completamente decorado: " + mensaje.enviar("Hola Mundo"));
        
    }
}
