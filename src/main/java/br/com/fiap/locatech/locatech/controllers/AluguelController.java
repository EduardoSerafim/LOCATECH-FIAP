package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dto.AluguelRequestDTO;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.services.AluguelService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {
    private static final Logger logger = LoggerFactory.getLogger(AluguelController.class);

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }


    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAlugueis(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("GET -> /alugueis?page={}&size={}", page, size);

        var alugueis = aluguelService.findAll(page, size);
        return ResponseEntity.ok(alugueis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguel(@PathVariable Long id) {
        logger.info("GET -> /alugueis/{}", id);

        var aluguel = aluguelService.findById(id);
        return ResponseEntity.ok(aluguel);
    }

    @PostMapping
    public ResponseEntity<Void> saveAluguel(@Valid @RequestBody AluguelRequestDTO aluguel) {
        logger.info("POST -> /alugueis");

        this.aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(@PathVariable Long id, @RequestBody Aluguel aluguel) {
        logger.info("PUT -> /alugueis");

        this.aluguelService.updateAluguel(aluguel, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(@PathVariable Long id) {
        logger.info("DELETE -> /alugueis");

        this.aluguelService.deleteAluguel(id);
        return ResponseEntity.noContent().build();
    }
}
