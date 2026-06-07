 public class SistemaGestionClinica {     private ArrayList<Paciente> pacientes;     private ArrayList<Medico> medicos;     private ArrayList<Cita> citas;     private Inventario inventario;     private Facturacion facturacion; 
     public SistemaGestionClinica() {         pacientes = new ArrayList<>();         medicos = new ArrayList<>();         citas = new ArrayList<>();         inventario = new Inventario();         facturacion = new Facturacion(); 
    }  
    public void mostrarMenu() { 
        Scanner scanner = new Scanner(System.in);         int opcion; 
 
        do { 
            System.out.println("Bienvenido al Sistema de Gestión Integral para Clínicas de Salud"); 
            System.out.println("1. Gestión de Citas"); 
            System.out.println("2. Historias Clínicas Electrónicas"); 
            System.out.println("3. Gestión de Inventarios"); 
            System.out.println("4. Facturación y Pagos"); 
            System.out.println("5. Reportes de Gestión"); 
            System.out.println("6. Salir"); 
            System.out.print("Seleccione una opción: ");             opcion = scanner.nextInt(); 
             switch (opcion) {                 case 1: 
                    gestionarCitas(scanner);                     break;                 case 2:                     gestionarHistoriasClinicas(scanner);                     break;                 case 3: 
                    gestionarInventarios(scanner);                     break;                 case 4:                     gestionarFacturacion(scanner);                     break;                 case 5: 
                    generarReportes(scanner);                     break;                 case 6: 
                    System.out.println("Saliendo del sistema..."); 
                    break;                 default: 
                    System.out.println("Opción no válida. Intente de nuevo.");             } 
        } while (opcion != 6); 
 
        scanner.close(); 
    }      private void gestionarCitas(Scanner scanner) { 
        // Implementar lógica de gestión de citas 
    }  
    private void gestionarHistoriasClinicas(Scanner scanner) { 
        // Implementar lógica de gestión de historias clínicas electrónicas 
    }      private void gestionarInventarios(Scanner scanner) { 
        // Implementar lógica de gestión de inventarios 
    }      private void gestionarFacturacion(Scanner scanner) { 
        // Implementar lógica de facturación y pagos 
    }      private void generarReportes(Scanner scanner) {         // Implementar lógica de generación de reportes 
    } 
} 
