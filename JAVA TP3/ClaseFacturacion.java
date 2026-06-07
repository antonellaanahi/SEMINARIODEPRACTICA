public class Facturacion {     private ArrayList<Factura> facturas; 
     public Facturacion() {         facturas = new ArrayList<>(); 
    } 
 
    public void agregarFactura(Factura factura) {         facturas.add(factura); 
    }  
    public void mostrarFacturas() {         for (Factura factura : facturas) { 
            System.out.println(factura); 
        } 
    } 
} 
