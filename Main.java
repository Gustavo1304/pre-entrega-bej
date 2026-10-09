import java.util.Scanner;

import exception.TerapiaNoEncontradoException;
import exception.StockInsuficienteException;
import model.Terapia;
import service.TerapiaService;
import ui.MenuTerapia;
import util.Validador;

public class Main {
    public static void main(String[] args) {

        TerapiaService service = new TerapiaService();
        Scanner sc = new Scanner(System.in);
        MenuTerapia menu = new MenuTerapia(sc, service);
        cargarDatosDePrueba(service);

        int opcion;

        do {
            menu.mostrarMenu();
            opcion = Validador.leerEntero(sc, "Elija una opción: ");

            try {
                switch (opcion) {
                    case 1 -> menu.agregarTerapia();
                    case 2 -> menu.listarTerapias();
                    case 3 -> menu.buscarTerapia();
                    case 4 -> menu.actualizarTerapia();
                    case 5 -> menu.eliminarTerapia();
                    case 6 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción inválida. Elija un número del 1 al 6.");
                }
            } catch (TerapiaNoEncontradoException | StockInsuficienteException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (opcion != 6);

        sc.close();

    }

    private static void cargarDatosDePrueba(TerapiaService service) {
        service.guardar(new Terapia("Reiki", 15000, 5, "Sanación Espiritual"));
        service.guardar(new Terapia("Magnified Healing", 15000, 5, "Sanación Espiritual"));
        service.guardar(new Terapia("Registros Akashicos", 12000, 2, "Guia Espiritual"));
        service.guardar(new Terapia("Tarot", 10000, 2, "Guia Espiritual"));
        service.guardar(new Terapia("Meditación", 9000, 3, "Relajación"));
        System.out.println("✔ Se cargaron 5 Terapias de prueba.\n");
    }
}