package service;

import java.util.ArrayList;
import java.util.List;

import exception.TerapiaNoEncontradoException;
import model.Terapia;
import util.Validador;

public class TerapiaService {

    private List<Terapia> productos = new ArrayList<>();

    private static int contadorId = 1;

    public Terapia guardar (Terapia t) {

        Validador.validarNombre(t.getNombre());
        Validador.validarPrecio(t.getPrecio());
        Validador.validarStock(t.getStock());
        Validador.validarCategoria(t.getCategoria());

        t.setId(contadorId);
        contadorId++;

        productos.add(t);

        return t;

    }

    public List<Terapia> listarTodos(){
        return productos;
    }

    public Terapia obtenerPorId(int id) {
        for(Terapia t : productos){
            if(t.getId() == id){
                return t;
            }
        }

        throw new TerapiaNoEncontradoException("No se encontro la Terapia con el id " + id);
    }

    public Terapia actualizar(int id,Terapia datos){

        Terapia t = obtenerPorId(id);

        Validador.validarNombre(datos.getNombre());
        Validador.validarPrecio(datos.getPrecio());
        Validador.validarStock(datos.getStock());
        Validador.validarCategoria(datos.getCategoria());

        t.setNombre(datos.getNombre());
        t.setPrecio(datos.getPrecio());
        t.setStock(datos.getStock());
        t.setCategoria(datos.getCategoria());

        return t;

    }

    public void eliminar(int id){
        Terapia t = obtenerPorId(id);
        productos.remove(t);
    }

    
}