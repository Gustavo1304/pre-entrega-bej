package ui;

import java.util.List;
import java.util.Scanner;

import model.Terapia;
import service.TerapiaService;
import util.Validador;

public class MenuTerapia {

    private final Scanner sc;
    private final TerapiaService service;

    public MenuTerapia(Scanner sc, TerapiaService service) {
        this.sc = sc;
        this.service = service;
    }

    public void mostrarMenu() {
        System.out.println("======= Tu-Bienestar - Gestión de Terapias =======");
        System.out.println("");
        System.out.println("1) Agregar Terapia");
        System.out.println("2) Listar Terapias");
        System.out.println("3) Buscar Terapia por ID");
        System.out.println("4) Actualizar Terapia");
        System.out.println("5) Eliminar Terapia");
        System.out.println("6) Salir");
        System.out.println("==================================================");
        System.out.println("");
    }

    public void agregarTerapia() {
        System.out.println("--- Nueva Terapia ---");
        String nombre = Validador.leerTexto(sc, "Nombre: ");
        double precio = Validador.leerDouble(sc, "Precio: ");
        int stock = Validador.leerEntero(sc, "Stock: ");
        String categoria = Validador.leerTexto(sc, "Categoría: ");
 
        Terapia t = new Terapia(nombre,precio,stock,categoria);
        Terapia guardado = service.guardar(t); // El servicio se encarga de validar y asignar el id
        
        System.out.println("Terapia agregada con el id " + guardado.getId());

    }

    public void listarTerapias(){
        // Recibimos un List ( ver TerapiasService )
        List<Terapia> lista = service.listarTodos();
        if (lista.isEmpty()){
            System.out.println("No hay trapias cargadas.");
            return;
        }
    
        System.out.println("--- Catálogo ---");
        for(Terapia t : lista){
            t.mostrar();
        }
    }

    public void buscarTerapia(){
        int id = Validador.leerEntero(sc, "Ingrese el id de la Terapia: ");

        Terapia t = service.obtenerPorId(id);
        System.out.println("Encontrada: " + t);
    }

    public void actualizarTerapia() {
        int id = Validador.leerEntero(sc, "Ingrese el id de la Terapia a actualizar: ");

        Terapia actual = service.obtenerPorId(id);
        System.out.println("Datos actuales: " + actual);

        System.out.println("--- Ingrese los nuevos datos ---");
        String nombre = Validador.leerTexto(sc, "Nombre: ");
        double precio = Validador.leerDouble(sc, "Precio: ");
        int stock = Validador.leerEntero(sc, "Stock: ");
        String categoria = Validador.leerTexto(sc, "Categoría: ");

        Terapia datos = new Terapia(nombre, precio, stock, categoria);
        Terapia actualizada = service.actualizar(id, datos);

        System.out.println("Terapia actualizada: " + actualizada);
    }

    public void eliminarTerapia() {
        int id = Validador.leerEntero(sc, "Ingrese el id del terapia a eliminar: ");
        service.eliminar(id);
        System.out.println("Terapia eliminada.");
    }


}