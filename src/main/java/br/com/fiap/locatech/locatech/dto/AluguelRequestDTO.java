package br.com.fiap.locatech.locatech.dto;

import java.time.LocalDate;

public record AluguelRequestDTO(
        Long pessoaId,
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
