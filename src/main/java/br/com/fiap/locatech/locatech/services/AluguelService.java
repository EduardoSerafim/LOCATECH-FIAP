package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.repositories.AluguelRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {
    private final AluguelRepository alguelRepository;

    public AluguelService(AluguelRepository alguelRepository) {
        this.alguelRepository = alguelRepository;
    }


    public List<Aluguel> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return this.alguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findById(Long id) {
        return this.alguelRepository.findById(id);
    }

    public void saveAluguel(Aluguel alguel) {
        var save = this.alguelRepository.save(alguel);
        Assert.state(save == 1, "Erro ao salvar alguel " + alguel.getPessoaNome());
    }

    public void updateAluguel(Aluguel alguel, Long id) {
        var update = this.alguelRepository.update(alguel, id);
        if (update == 0){
            throw new RuntimeException("Aluguel não encontrada");
        }
    }

    public void deleteAluguel(Long id) {
        var delete = this.alguelRepository.delete(id);
        if (delete == 0){
            throw new RuntimeException("Aluguel não encontrada");
        }
    }
}
