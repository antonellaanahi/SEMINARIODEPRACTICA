public class Medico extends Persona {     private String especialidad; 
 
    public Medico(String nombre, String direccion, String telefono, String especialidad) 
{         super(nombre, direccion, telefono);         this.especialidad = especialidad; 
    }  
    public String getEspecialidad() {         return especialidad; 
    }  
    public void setEspecialidad(String especialidad) {         this.especialidad = especialidad; 
    } 
 
    @Override 
    public String toString() { 
        return super.toString() + ", Especialidad: " + especialidad; 
    } 
} 
