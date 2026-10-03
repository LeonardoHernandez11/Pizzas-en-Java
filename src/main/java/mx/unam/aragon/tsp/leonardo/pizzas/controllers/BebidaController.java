package mx.unam.aragon.tsp.leonardo.pizzas.controllers;

import mx.unam.aragon.tsp.leonardo.pizzas.models.Bebida;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bebidas")
public class BebidaController {

    private List<Bebida> bebidas = new ArrayList<>();
    private int contadorId = 1;

    @PostMapping
    public Bebida crearBebida(@RequestBody Bebida bebida) {
        bebida.setId(contadorId++);
        bebidas.add(bebida);
        return bebida;
    }

    @GetMapping
    public List<Bebida> obtenerBebidas() {
        return bebidas;
    }

    @GetMapping("/{id}")
    public Bebida obtenerBebidaPorId(@PathVariable int id) {
        return bebidas.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Bebida actualizarBebida(@PathVariable int id, @RequestBody Bebida bebidaActualizada) {
        for (Bebida b : bebidas) {
            if (b.getId() == id) {
                b.setNombre(bebidaActualizada.getNombre());
                b.setPrecio(bebidaActualizada.getPrecio());
                return b;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarBebida(@PathVariable int id) {
        bebidas.removeIf(b -> b.getId() == id);
        return "Bebida con ID " + id + " eliminada.";
    }
}