import java.util.*;
import java.sql.*;

public class SistemaGestionClinica {

    private PacienteDAO pacienteDAO = new PacienteDAO();
    private MedicoDAO medicoDAO = new MedicoDAO();
    private CitaDAO citaDAO = new CitaDAO();
    private ProductoDAO productoDAO = new ProductoDAO();
    private FacturaDAO facturaDAO = new FacturaDAO();
    private Inventario inventario = new Inventario();

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== Sistema de Gestion Integral para Clinicas de Salud ===");
            System.out.println("1. Gestion de Pacientes");
            System.out.println("2. Gestion de Medicos");
            System.out.println("3. Gestion de Citas");
            System.out.println("4. Historias Clinicas");
            System.out.println("5. Gestion de Inventarios");
            System.out.println("6. Facturacion y Pagos");
            System.out.println("7. Reportes de Gestion");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: gestionarPacientes(scanner); break;
                case 2: gestionarMedicos(scanner); break;
                case 3: gestionarCitas(scanner); break;
                case 4: gestionarHistoriasClinicas(scanner); break;
                case 5: gestionarInventarios(scanner); break;
                case 6: gestionarFacturacion(scanner); break;
                case 7: generarReportes(); break;
                case 8: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 8);

        scanner.close();
    }

    private void gestionarPacientes(Scanner scanner) {
        System.out.println("\n--- Gestion de Pacientes ---");
        System.out.println("1. Ver pacientes");
        System.out.println("2. Agregar paciente");
        System.out.print("Opcion: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        if (op == 1) {
            List<Paciente> lista = pacienteDAO.obtenerTodos();
            System.out.println("\nID | Nombre | Apellido | Telefono | Email");
            System.out.println("--------------------------------------------------");
            for (Paciente p : lista)
                System.out.printf("%d | %s | %s | %s | %s%n",
                    p.getId(), p.getNombre(), p.getApellido(), p.getTelefono(), p.getCorreo());
        } else if (op == 2) {
            System.out.print("Nombre: "); String nombre = scanner.nextLine();
            System.out.print("Apellido: "); String apellido = scanner.nextLine();
            System.out.print("Fecha nacimiento (yyyy-MM-dd): "); String fecha = scanner.nextLine();
            System.out.print("Direccion: "); String dir = scanner.nextLine();
            System.out.print("Telefono: "); String tel = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            pacienteDAO.insertar(new Paciente(nombre, apellido, fecha, dir, tel, email));
            System.out.println("Paciente agregado correctamente.");
        }
    }

    private void gestionarMedicos(Scanner scanner) {
        System.out.println("\n--- Gestion de Medicos ---");
        System.out.println("1. Ver medicos");
        System.out.println("2. Agregar medico");
        System.out.print("Opcion: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        if (op == 1) {
            List<Medico> lista = medicoDAO.obtenerTodos();
            System.out.println("\nID | Nombre | Apellido | Especialidad | Telefono");
            System.out.println("--------------------------------------------------");
            for (Medico m : lista)
                System.out.printf("%d | %s | %s | %s | %s%n",
                    m.getId(), m.getNombre(), m.getApellido(), m.getEspecialidad(), m.getTelefono());
        } else if (op == 2) {
            System.out.print("Nombre: "); String nombre = scanner.nextLine();
            System.out.print("Apellido: "); String apellido = scanner.nextLine();
            System.out.print("Especialidad: "); String esp = scanner.nextLine();
            System.out.print("Telefono: "); String tel = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            medicoDAO.insertar(new Medico(nombre, apellido, esp, tel, email));
            System.out.println("Medico agregado correctamente.");
        }
    }

    private void gestionarCitas(Scanner scanner) {
        System.out.println("\n--- Gestion de Citas ---");
        System.out.println("1. Ver citas");
        System.out.println("2. Agregar cita");
        System.out.print("Opcion: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        if (op == 1) {
            List<Cita> lista = citaDAO.obtenerTodos();
            System.out.println("\nID | PacienteID | MedicoID | Fecha | Estado");
            System.out.println("--------------------------------------------------");
            for (Cita c : lista)
                System.out.printf("%d | %d | %d | %s | %s%n",
                    c.getId(), c.getPacienteId(), c.getMedicoId(), c.getFechaHora(), c.getEstado());
        } else if (op == 2) {
            System.out.println("Pacientes disponibles:");
            for (Paciente p : pacienteDAO.obtenerTodos())
                System.out.printf("  [%d] %s %s%n", p.getId(), p.getNombre(), p.getApellido());
            System.out.print("ID Paciente: "); int pacId = scanner.nextInt();

            System.out.println("Medicos disponibles:");
            for (Medico m : medicoDAO.obtenerTodos())
                System.out.printf("  [%d] %s %s (%s)%n", m.getId(), m.getNombre(), m.getApellido(), m.getEspecialidad());
            System.out.print("ID Medico: "); int medId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Fecha y hora (yyyy-MM-dd HH:mm:ss): "); String fecha = scanner.nextLine();
            System.out.print("Estado (Pendiente/Confirmada/Cancelada): "); String estado = scanner.nextLine();
            citaDAO.insertar(new Cita(pacId, medId, fecha, estado));
            System.out.println("Cita agregada correctamente.");
        }
    }

    private void gestionarHistoriasClinicas(Scanner scanner) {
        System.out.println("\n--- Historias Clinicas ---");
        System.out.println("1. Ver historias");
        System.out.println("2. Agregar historia");
        System.out.print("Opcion: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        try (Connection con = ConexionDB.getConexion()) {
            if (op == 1) {
                String sql = "SELECT h.id, CONCAT(p.nombre,' ',p.apellido) AS paciente, " +
                             "CONCAT(m.nombre,' ',m.apellido) AS medico, h.fecha, h.detalles " +
                             "FROM historias_clinicas h JOIN pacientes p ON h.paciente_id=p.id " +
                             "JOIN medicos m ON h.medico_id=m.id";
                ResultSet rs = con.createStatement().executeQuery(sql);
                System.out.println("\nID | Paciente | Medico | Fecha | Detalles");
                System.out.println("--------------------------------------------------");
                while (rs.next())
                    System.out.printf("%d | %s | %s | %s | %s%n",
                        rs.getInt("id"), rs.getString("paciente"),
                        rs.getString("medico"), rs.getString("fecha"), rs.getString("detalles"));
            } else if (op == 2) {
                for (Paciente p : pacienteDAO.obtenerTodos())
                    System.out.printf("  [%d] %s %s%n", p.getId(), p.getNombre(), p.getApellido());
                System.out.print("ID Paciente: "); int pacId = scanner.nextInt();
                for (Medico m : medicoDAO.obtenerTodos())
                    System.out.printf("  [%d] %s %s%n", m.getId(), m.getNombre(), m.getApellido());
                System.out.print("ID Medico: "); int medId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Fecha (yyyy-MM-dd): "); String fecha = scanner.nextLine();
                System.out.print("Detalles: "); String detalles = scanner.nextLine();
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO historias_clinicas (paciente_id, medico_id, fecha, detalles) VALUES (?,?,?,?)");
                ps.setInt(1, pacId); ps.setInt(2, medId);
                ps.setString(3, fecha); ps.setString(4, detalles);
                ps.executeUpdate();
                System.out.println("Historia clinica agregada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void gestionarInventarios(Scanner scanner) {
        System.out.println("\n--- Gestion de Inventarios ---");
        System.out.println("1. Ver inventario");
        System.out.println("2. Agregar producto");
        System.out.print("Opcion: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        if (op == 1) {
            List<Producto> lista = productoDAO.obtenerTodos();
            inventario = new Inventario();
            for (Producto p : lista) inventario.agregarProducto(p);
            System.out.println("\nID | Producto | Cantidad | Vencimiento");
            System.out.println("--------------------------------------------------");
            inventario.mostrarInventario();
        } else if (op == 2) {
            System.out.print("Producto: "); String nombre = scanner.nextLine();
            System.out.print("Cantidad: "); int cantidad = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Fecha vencimiento (yyyy-MM-dd): "); String fecha = scanner.nextLine();
            System.out.print("ID Proveedor (0 para ninguno): "); int provId = scanner.nextInt();
            scanner.nextLine();
            Producto p = new Producto(nombre, cantidad, fecha, provId);
            productoDAO.insertar(p);
            inventario.agregarProducto(p);
            System.out.println("Producto agregado al inventario.");
        }
    }

    private void gestionarFacturacion(Scanner scanner) {
        System.out.println("\n--- Facturacion y Pagos ---");
        System.out.println("1. Ver facturas");
        System.out.println("2. Agregar factura");
        System.out.print("Opcion: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        if (op == 1) {
            List<Factura> lista = facturaDAO.obtenerTodos();
            System.out.println("\nID | PacienteID | Fecha | Total | Estado");
            System.out.println("--------------------------------------------------");
            for (Factura f : lista)
                System.out.printf("%d | %d | %s | $%.2f | %s%n",
                    f.getId(), f.getPacienteId(), f.getFecha(), f.getTotal(), f.getEstado());
        } else if (op == 2) {
            for (Paciente p : pacienteDAO.obtenerTodos())
                System.out.printf("  [%d] %s %s%n", p.getId(), p.getNombre(), p.getApellido());
            System.out.print("ID Paciente: "); int pacId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Fecha (yyyy-MM-dd): "); String fecha = scanner.nextLine();
            System.out.print("Total: "); double total = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Estado (Pendiente/Pagada): "); String estado = scanner.nextLine();
            facturaDAO.insertar(new Factura(pacId, fecha, total, estado));
            System.out.println("Factura agregada correctamente.");
        }
    }

    private void generarReportes() {
        System.out.println("\n--- Reportes de Gestion ---");
        System.out.println("Total pacientes: " + pacienteDAO.obtenerTodos().size());
        System.out.println("Total medicos: " + medicoDAO.obtenerTodos().size());
        long pendientes = citaDAO.obtenerTodos().stream().filter(c -> c.getEstado().equals("Pendiente")).count();
        System.out.println("Citas pendientes: " + pendientes);

        List<Producto> productos = productoDAO.obtenerTodos();
        if (!productos.isEmpty()) {
            Producto[] arr = productos.toArray(new Producto[0]);
            System.out.printf("Promedio cantidad inventario: %.2f%n", EstadisticasInventario.calcularPromedioCantidad(arr));
        }

        double ingresos = facturaDAO.obtenerTodos().stream()
            .filter(f -> f.getEstado().equals("Pagada"))
            .mapToDouble(Factura::getTotal).sum();
        System.out.printf("Ingresos totales: $%.2f%n", ingresos);
    }
}
