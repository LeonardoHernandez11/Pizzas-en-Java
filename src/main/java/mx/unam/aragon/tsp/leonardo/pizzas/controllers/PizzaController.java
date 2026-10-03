package mx.unam.aragon.tsp.leonardo.pizzas.controllers;

import mx.unam.aragon.tsp.leonardo.pizzas.models.Pizza;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pizzas")
public class PizzaController {

    private List<Pizza> pizzas = new ArrayList<>();
    private int contadorId = 1;

    @PostMapping
    public Pizza crearPizza(@RequestBody Pizza pizza) {
        pizza.setId(contadorId++);
        pizzas.add(pizza);
        return pizza;
    }

    @GetMapping
    public List<Pizza> obtenerPizzas() {
        return pizzas;
    }

    @GetMapping("/{id}")
    public Pizza obtenerPizzaPorId(@PathVariable int id) {
        return pizzas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Pizza actualizarPizza(@PathVariable int id, @RequestBody Pizza pizzaActualizada) {
        for (Pizza p : pizzas) {
            if (p.getId() == id) {
                p.setNombre(pizzaActualizada.getNombre());
                p.setPrecio(pizzaActualizada.getPrecio());
                return p;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarPizza(@PathVariable int id) {
        pizzas.removeIf(p -> p.getId() == id);
        return "Pizza con ID " + id + " eliminada.";
    }
}