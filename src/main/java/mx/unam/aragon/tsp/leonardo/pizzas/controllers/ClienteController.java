package mx.unam.aragon.tsp.leonardo.pizzas.controllers;

import mx.unam.aragon.tsp.leonardo.pizzas.models.Cliente;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private List<Cliente> clientes = new ArrayList<>();
    private int contadorId = 1;

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        cliente.setId(contadorId++);
        clientes.add(cliente);
        return cliente;
    }

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable int id, @RequestBody Cliente clienteActualizado) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                c.setNombre(clienteActualizado.getNombre());
                c.setTelefono(clienteActualizado.getTelefono());
                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable int id) {
        clientes.removeIf(c -> c.getId() == id);
        return "Cliente con ID " + id + " eliminado.";
    }
}