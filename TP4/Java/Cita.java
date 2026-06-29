public class Cita {
    private int id;
    private int pacienteId;
    private int medicoId;
    private String fechaHora;
    private String estado;

    public Cita(int pacienteId, int medicoId, String fechaHora, String estado) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }
    public int getMedicoId() { return medicoId; }
    public void setMedicoId(int medicoId) { this.medicoId = medicoId; }
    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Cita [" + id + "] Paciente:" + pacienteId + " Medico:" + medicoId + " | " + fechaHora + " | " + estado;
    }
}
