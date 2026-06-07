public class Paciente extends Persona {     private String numeroHistoriaClinica; 
 
    public Paciente(String nombre, String direccion, String telefono, String numeroHistoriaClinica) { 
        super(nombre, direccion, telefono); 
        this.numeroHistoriaClinica = numeroHistoriaClinica;     }  
    public String getNumeroHistoriaClinica() {         return numeroHistoriaClinica; 
    }  
    public void setNumeroHistoriaClinica(String numeroHistoriaClinica) {         this.numeroHistoriaClinica = numeroHistoriaClinica;     } 
 
    @Override 
    public String toString() { 
        return super.toString() + ", Número de Historia Clínica: " + numeroHistoriaClinica; 
    } 
} 
