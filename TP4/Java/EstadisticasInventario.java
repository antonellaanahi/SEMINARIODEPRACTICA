public class EstadisticasInventario {
    public static double calcularPromedioCantidad(Producto[] productos) {
        int total = 0;
        for (Producto producto : productos) {
            total += producto.getCantidad();
        }
        return total / (double) productos.length;
    }
}
