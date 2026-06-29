public class Paciente extends Persona {
    private String fechaNacimiento;

    public Paciente(String nombre, String apellido, String fechaNacimiento, String direccion, String telefono, String correo) {
        super(nombre, apellido, direccion, telefono, correo);
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    @Override
    public String toString() {
        return super.toString() + " | Nacimiento: " + fechaNacimiento;
    }
}
