# Patrones de Diseño - Ejemplos de Implementación

Este repositorio contiene implementaciones prácticas de varios patrones de diseño en Java. A continuación se presenta un resumen de cada patrón implementado y su diagrama de clases correspondiente.


## 1. Patrón Adapter

Permite la adaptación de interfaces incompatibles entre servicios de mensajería (WhatsApp y Telegram).

```mermaid
classDiagram
    class MedioNotificacion {
        <<interface>>
        +enviarNotificacion(mensaje: String)
    }
    class ServicioWhatsApp {
        +enviarMensajeWhatsApp(telefono: String, contenido: String)
    }
    class ServicioTelegram {
        +publicarMensaje(chatId: String, texto: String, prioritario: boolean)
    }
    class WhatsAppAdapter {
        -whatsapp: ServicioWhatsApp
        -numeroDestino: String
        +enviarNotificacion(mensaje: String)
    }
    class TelegramAdapter {
        -telegram: ServicioTelegram
        -chatId: String
        +enviarNotificacion(mensaje: String)
    }
    
    MedioNotificacion <|.. WhatsAppAdapter
    MedioNotificacion <|.. TelegramAdapter
    WhatsAppAdapter --> ServicioWhatsApp
    TelegramAdapter --> ServicioTelegram
```

## 2. Patrón Decorator

Permite añadir funcionalidades adicionales a mensajes de forma dinámica.

```mermaid
classDiagram
    class Decorator {
        <<interface>>
        +enviar(mensaje: String): String
    }
    class MensajeBase {
        +enviar(mensaje: String): String
    }
    class MensajeDecorator {
        <<abstract>>
        #componente: Decorator
        +enviar(mensaje: String): String
    }
    class MayusculasDecorator {
        +enviar(mensaje: String): String
    }
    class EtiquetasDecorator {
        +enviar(mensaje: String): String
    }
    class FechaDecorator {
        +enviar(mensaje: String): String
    }
    
    Decorator <|.. MensajeBase
    Decorator <|.. MensajeDecorator
    MensajeDecorator <|-- MayusculasDecorator
    MensajeDecorator <|-- EtiquetasDecorator
    MensajeDecorator <|-- FechaDecorator
```

## 3. Patrón Singleton

Garantiza una única instancia de configuración del juego.

```mermaid
classDiagram
    class GameConfig {
        -static instance: GameConfig
        -volume: int
        -difficulty: String
        -brightness: int
        -GameConfig()
        +static getInstance(): GameConfig
        +setVolume(volume: int): void
        +setBrightness(brightness: int): void
        +setDifficulty(difficulty: String): void
        +getConfigInfo(): String
    }
```

## 4. Patrón Strategy

Permite intercambiar algoritmos de descuento en tiempo de ejecución.

```mermaid
classDiagram
    class DiscountStrategy {
        <<interface>>
        +calculateDiscount(amount: double): double
    }
    class SeasonalDiscount {
        +calculateDiscount(amount: double): double
    }
    class PremiumDiscount {
        +calculateDiscount(amount: double): double
    }
    class BulkDiscount {
        +calculateDiscount(amount: double): double
    }
    class ShoppingCart {
        -discountStrategy: DiscountStrategy
        +setDiscountStrategy(strategy: DiscountStrategy): void
        +calculateTotal(amount: double): double
    }
    
    DiscountStrategy <|.. SeasonalDiscount
    DiscountStrategy <|.. PremiumDiscount
    DiscountStrategy <|.. BulkDiscount
    ShoppingCart o-- DiscountStrategy
```

## 5. Patrón Observer

Implementa un sistema de notificaciones para actualizaciones meteorológicas.

```mermaid
classDiagram
    class WeatherObserver {
        <<interface>>
        +update(temperature: float, humidity: float, pressure: float): void
    }
    class WeatherStation {
        -observers: List<WeatherObserver>
        -temperature: float
        -humidity: float
        -pressure: float
        +attach(observer: WeatherObserver): void
        +detach(observer: WeatherObserver): void
        +notifyObservers(): void
        +setMeasurements(temperature: float, humidity: float, pressure: float): void
    }
    class WeatherDisplay {
        -location: String
        +update(temperature: float, humidity: float, pressure: float): void
    }
    
    WeatherObserver <|.. WeatherDisplay
    WeatherStation o-- WeatherObserver
```