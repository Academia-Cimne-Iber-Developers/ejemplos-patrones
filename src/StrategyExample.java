// Interfaz Strategy
interface DiscountStrategy {
    double calculateDiscount(double amount);
}

// Estrategias concretas
class SeasonalDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.30; // 30% de descuento
    }
}

class PremiumDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.20; // 20% de descuento
    }
}

class BulkDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return amount >= 1000 ? amount * 0.15 : 0; // 15% en compras mayores a $1000
    }
}

class NoDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return 0; // Sin descuento
    }
}

// Contexto
class ShoppingCart {
    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    public double calculateTotal(double amount) {
        double discount = discountStrategy.calculateDiscount(amount);
        return amount - discount;
    }
}

// Ejemplo de uso
class StrategyExample {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        double amount = 1200.00;

        // Usando descuento de temporada
        cart.setDiscountStrategy(new SeasonalDiscount());
        System.out.println("Total con descuento de temporada: $" + cart.calculateTotal(amount));

        // Cambiando a descuento premium
        cart.setDiscountStrategy(new PremiumDiscount());
        System.out.println("Total con descuento premium: $" + cart.calculateTotal(amount));

        // Cambiando a descuento por cantidad
        cart.setDiscountStrategy(new BulkDiscount());
        System.out.println("Total con descuento por cantidad: $" + cart.calculateTotal(amount));
    }
}
