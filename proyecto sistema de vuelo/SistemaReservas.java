import java.util.Scanner;

public class SistemaReservas {

    static final int ASIENTOS_TOTALES = 10;
    static final int TIEMPO_LIMITE = 24; // en horas antes del vuelo
    static final double MULTA_CANCELACION = 0.20;
    static final double PESO_MAX_EQUIPAJE = 23.0; // en kg
    static final int CANTIDAD_MAX_EQUIPAJE = 2;

    static int asientosDisponibles = ASIENTOS_TOTALES;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n=== Menú de Reservas de Vuelo ===");
            System.out.println("1. Reservar vuelo");
            System.out.println("2. Cancelar vuelo");
            System.out.println("3. Ver asientos disponibles");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    reservarVuelo();
                    break;
                case 2:
                    cancelarVuelo();
                    break;
                case 3:
                    System.out.println("Asientos disponibles: " + asientosDisponibles);
                    break;
                case 4:
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }

    static void reservarVuelo() {
        if (asientosDisponibles <= 0) {
            System.out.println("Lo sentimos, no hay asientos disponibles.");
            return;
        }

        System.out.print("Ingrese cuántas horas antes del vuelo está haciendo la reserva: ");
        int horas = scanner.nextInt();
        if (horas < TIEMPO_LIMITE) {
            System.out.println("Debe reservar al menos " + TIEMPO_LIMITE + " horas antes del vuelo.");
            return;
        }

        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        mostrarRutas();
        System.out.print("Seleccione ruta (1-3): ");
        int ruta = scanner.nextInt();
        

        System.out.print("Tipo de boleto (1. Económico, 2. Ejecutivo): ");
        int tipo = scanner.nextInt();

        System.out.print("Ingrese número de maletas: ");
        int numMaletas = scanner.nextInt();
        System.out.print("Ingrese peso total de equipaje (kg): ");
        double peso = scanner.nextDouble();

        if (!verificarEquipaje(numMaletas, peso)) {
            return;
        }

        double precio = (tipo == 1) ? 100.0 : 200.0;

        asientosDisponibles--;
        System.out.println("Reserva completada para " + nombre + ". Precio: $" + precio);
    }

    static void cancelarVuelo() {
        System.out.print("Ingrese cuántas horas antes del vuelo está cancelando: ");
        int horas = scanner.nextInt();
        double precioOriginal = 100.0; // Supuesto

        if (horas < TIEMPO_LIMITE) {
            double multa = precioOriginal * MULTA_CANCELACION;
            double reembolso = precioOriginal - multa;
            System.out.println("Cancelación tardía. Se aplica multa del 20%.");
            System.out.println("Reembolso: $" + reembolso);
        } else {
            System.out.println("Cancelación aceptada sin multa. Reembolso: $" + precioOriginal);
        }

        if (asientosDisponibles < ASIENTOS_TOTALES) {
            asientosDisponibles++;
        }
    }

    static void mostrarRutas() {
        System.out.println("\nRutas disponibles:");
        System.out.println("1. Quito - Guayaquil");
        System.out.println("2. Quito - Cuenca");
        System.out.println("3. Quito - Loja");
    }

    static boolean verificarEquipaje(int cantidad, double peso) {
        if (cantidad > CANTIDAD_MAX_EQUIPAJE) {
            System.out.println("No se permiten más de " + CANTIDAD_MAX_EQUIPAJE + " maletas.");
            return false;
        }
        if (peso > PESO_MAX_EQUIPAJE) {
            System.out.println("El peso máximo permitido es de " + PESO_MAX_EQUIPAJE + " kg.");
            return false;
        }
        return true;
    }
}