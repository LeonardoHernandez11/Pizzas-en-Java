package mx.unam.aragon.tsp.leonardo.pizzas.controllers;

import mx.unam.aragon.tsp.leonardo.pizzas.models.Tamano;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tamanos")
public class TamanoController {

    private List<Tamano> tamanos = new ArrayList<>();
    private int contadorId = 1;

    @PostMapping
    public Tamano crearTamano(@RequestBody Tamano tamano) {
        tamano.setId(contadorId++);
        tamanos.add(tamano);
        return tamano;
    }

    @GetMapping
    public List<Tamano> obtenerTamanos() {
        return tamanos;
    }

    @GetMapping("/{id}")
    public Tamano obtenerTamanoPorId(@PathVariable int id) {
        return tamanos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Tamano actualizarTamano(@PathVariable int id, @RequestBody Tamano tamanoActualizado) {
        for (Tamano t : tamanos) {
            if (t.getId() == id) {
                t.setNombre(tamanoActualizado.getNombre());
                t.setRebanadas(tamanoActualizado.getRebanadas());
                return t;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarTamano(@PathVariable int id) {
        tamanos.removeIf(t -> t.getId() == id);
        return "Tamaño con ID " + id + " eliminado.";
    }
}