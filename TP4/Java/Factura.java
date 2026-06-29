public class Factura {
    private int id;
    private int pacienteId;
    private String fecha;
    private double total;
    private String estado;

    public Factura(int pacienteId, String fecha, double total, String estado) {
        this.pacienteId = pacienteId;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Factura [" + id + "] Paciente:" + pacienteId + " | $" + total + " | " + estado;
    }
}
