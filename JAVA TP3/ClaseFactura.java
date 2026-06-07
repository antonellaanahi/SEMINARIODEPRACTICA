public class Factura { 
    private String numeroFactura;     private Paciente paciente;     private double monto; 
 
    public Factura(String numeroFactura, Paciente paciente, double monto) {         this.numeroFactura = numeroFactura;         this.paciente = paciente;         this.monto = monto; 
    }  
    public String getNumeroFactura() {         return numeroFactura; 
    }  
    public void setNumeroFactura(String numeroFactura) {         this.numeroFactura = numeroFactura; 
    }  
    public Paciente getPaciente() {         return paciente; 
    }  
    public void setPaciente(Paciente paciente) {         this.paciente = paciente; 
    }  
    public double getMonto() {         return monto; 
    }  
    public void setMonto(double monto) {         this.monto = monto; 
    } 
 
    @Override 
    public String toString() { 
        return "Factura No: " + numeroFactura + ", Paciente: " + paciente + ", Monto: $" 
+ monto; 
    } 
} 
 
