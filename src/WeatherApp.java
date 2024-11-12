import java.util.ArrayList;
import java.util.List;

// Interface Observer
interface WeatherObserver {
    void update(float temperature, float humidity, float pressure);
}

// Clase Subject (Observable)
class WeatherStation {
    private List<WeatherObserver> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    public void attach(WeatherObserver observer) {
        observers.add(observer);
    }

    public void detach(WeatherObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
}

// Implementaciones concretas de Observer
class WeatherDisplay implements WeatherObserver {
    private String location;

    public WeatherDisplay(String location) {
        this.location = location;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("Ubicación: " + location);
        System.out.println("Temperatura: " + temperature + "°C");
        System.out.println("Humedad: " + humidity + "%");
        System.out.println("Presión: " + pressure + " hPa\n");
    }
}

// Ejemplo de uso
public class WeatherApp {
    public static void main(String[] args) {
        // Reporta el clima en una ciudad a través de una estación meteorológica
        WeatherStation station = new WeatherStation();
        
        // Pantalla ubicada en diferentes lugares
        WeatherDisplay displayShopping = new WeatherDisplay("Shopping");
        WeatherDisplay displayPlaza = new WeatherDisplay("Plaza");
        
        station.attach(displayShopping);
        station.attach(displayPlaza);
        
        // Simulamos cambios en el clima
        System.out.println("Primera actualización del clima:");
        station.setMeasurements(25.2f, 65.0f, 1013.1f);
        
        System.out.println("Segunda actualización del clima:");
        station.setMeasurements(26.7f, 70.0f, 1012.5f);
        
        // Removemos un observador
        station.detach(displayPlaza);
        
        System.out.println("Tercera actualización del clima (solo Centro):");
        station.setMeasurements(24.5f, 68.0f, 1014.2f);
    }
}
