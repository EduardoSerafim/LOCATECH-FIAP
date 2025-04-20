package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dto.AluguelRequestDTO;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.repositories.AluguelRepository;
import br.com.fiap.locatech.locatech.repositories.VeiculoRepository;
import br.com.fiap.locatech.locatech.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {
    private final AluguelRepository alguelRepository;
    private final VeiculoRepository veiculoRepository;

    public AluguelService(AluguelRepository alguelRepository, VeiculoRepository veiculoRepository) {
        this.alguelRepository = alguelRepository;
        this.veiculoRepository = veiculoRepository;
    }


    public List<Aluguel> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return this.alguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findById(Long id) {
        return Optional.ofNullable(this.alguelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado")));
    }

    public void saveAluguel(AluguelRequestDTO aluguel) {
        var aluguelEntity = calculaAluguel(aluguel);
        var save = this.alguelRepository.save(aluguelEntity);
        Assert.state(save == 1, "Erro ao salvar alguel " + aluguel.pessoaId());
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

    private Aluguel calculaAluguel(AluguelRequestDTO aluguelRequestDTO) {
        var veiculo = veiculoRepository.findById(aluguelRequestDTO.veiculoId()).orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        var quantidadeDias = BigDecimal.valueOf(aluguelRequestDTO.dataFim().getDayOfYear() - aluguelRequestDTO.dataInicio().getDayOfYear());
        var valor = veiculo.getValorDiaria().multiply(quantidadeDias);

        return new Aluguel(aluguelRequestDTO, valor);
    }
}
