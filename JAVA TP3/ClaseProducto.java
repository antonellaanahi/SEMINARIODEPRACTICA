public class Producto {     private String nombre;     private int cantidad;     private String proveedor;     private String fechaVencimiento; 
 
    public Producto(String nombre, int cantidad, String proveedor, String fechaVencimiento) {         this.nombre = nombre;         this.cantidad = cantidad;         this.proveedor = proveedor; 
        this.fechaVencimiento = fechaVencimiento; 
    }  
    public String getNombre() {         return nombre; 
    }  
    public void setNombre(String nombre) {         this.nombre = nombre; 
    }      public int getCantidad() {         return cantidad; 
    }  
    public void setCantidad(int cantidad) {         this.cantidad = cantidad; 
    }  
    public String getProveedor() {         return proveedor; 
    }  
    public void setProveedor(String proveedor) {         this.proveedor = proveedor; 
    }  
    public String getFechaVencimiento() {         return fechaVencimiento; 
    }  
    public void setFechaVencimiento(String fechaVencimiento) {         this.fechaVencimiento = fechaVencimiento; 
    } 
 
    @Override 
    public String toString() { 
        return "Producto: " + nombre + ", Cantidad: " + cantidad + ", Proveedor: " + proveedor + ", Fecha de Vencimiento: " + fechaVencimiento; 
    } 
} 
 
