/**
 * Implementación del patrón Singleton para la configuración de un videojuego
 */
class GameConfig {
    // Instancia única
    private static GameConfig instance;
    
    // Atributos de configuración
    private int volume;
    private String difficulty;
    private int brightness;
    
    // Constructor privado
    private GameConfig() {
        // Valores por defecto
        this.volume = 50;
        this.difficulty = "normal";
        this.brightness = 70;
    }
    
    // Método de acceso global [Principal]
    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }
    
    // Métodos de configuración
    public void setVolume(int volume) {
        if (volume < 0 || volume > 100) {
            throw new IllegalArgumentException("Volumen debe estar entre 0 y 100");
        }
        this.volume = volume;
    }
    
    public int getVolume() {
        return volume;
    }
    
    public void setDifficulty(String difficulty) {
        if (!difficulty.equalsIgnoreCase("easy") && 
            !difficulty.equalsIgnoreCase("normal") && 
            !difficulty.equalsIgnoreCase("hard")) {
            throw new IllegalArgumentException("Dificultad debe ser: easy, normal o hard");
        }
        this.difficulty = difficulty.toLowerCase();
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    
    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("Brillo debe estar entre 0 y 100");
        }
        this.brightness = brightness;
    }
    
    public int getBrightness() {
        return brightness;
    }

    // Método para mostrar la configuración actual
    public String getConfigInfo() {
        return String.format(
            "Configuración actual:\n" +
            "- Volumen: %d%%\n" +
            "- Dificultad: %s\n" +
            "- Brillo: %d%%",
            volume, difficulty, brightness
        );
    }
}

/**
 * Clase principal para demostrar el uso del Singleton GameConfig
 */
public class GameConfigMain {
    public static void main(String[] args) {
        System.out.println("=== Demo del Sistema de Configuración del Juego ===\n");

        try {
            // Simulamos diferentes partes del juego accediendo a la configuración
            
            // Pantalla de configuración inicial
            System.out.println("1. Accediendo desde la pantalla de configuración:");
            GameConfig configScreen = GameConfig.getInstance();
            System.out.println("Configuración inicial:");
            System.out.println(configScreen.getConfigInfo());
            System.out.println();

            // Modificando configuración
            System.out.println("2. Modificando configuración:");
            configScreen.setVolume(75);
            configScreen.setDifficulty("hard");
            configScreen.setBrightness(85);
            System.out.println("Configuración actualizada:");
            System.out.println(configScreen.getConfigInfo());
            System.out.println();

            // Accediendo desde el nivel del juego
            System.out.println("3. Accediendo desde el nivel del juego:");
            GameConfig gameLevel = GameConfig.getInstance();
            System.out.println("¿Es la misma instancia? " + (configScreen == gameLevel));
            System.out.println("Configuración en el nivel:");
            System.out.println(gameLevel.getConfigInfo());
            System.out.println();

            // Intentando usar valores inválidos
            System.out.println("4. Intentando usar valores inválidos:");
            try {
                gameLevel.setVolume(150);
            } catch (IllegalArgumentException e) {
                System.out.println("Error controlado: " + e.getMessage());
            }

            try {
                gameLevel.setDifficulty("impossible");
            } catch (IllegalArgumentException e) {
                System.out.println("Error controlado: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
