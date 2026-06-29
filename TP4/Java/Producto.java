public class Producto {
    private int id;
    private String nombre;
    private int cantidad;
    private String fechaVencimiento;
    private int proveedorId;

    public Producto(String nombre, int cantidad, String fechaVencimiento, int proveedorId) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
        this.proveedorId = proveedorId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String f) { this.fechaVencimiento = f; }
    public int getProveedorId() { return proveedorId; }
    public void setProveedorId(int proveedorId) { this.proveedorId = proveedorId; }

    @Override
    public String toString() {
        return "Producto: " + nombre + " | Cantidad: " + cantidad + " | Vencimiento: " + fechaVencimiento;
    }
}
