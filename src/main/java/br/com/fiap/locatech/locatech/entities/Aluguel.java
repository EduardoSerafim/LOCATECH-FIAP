package br.com.fiap.locatech.locatech.entities;

import br.com.fiap.locatech.locatech.dto.AluguelRequestDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Aluguel {
    private Long id;
    private Long pessoaId;
    private Long veiculoId;
    private String veiculoModelo;
    private String pessoaCpf;
    private String pessoaNome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorTotal;

    public Aluguel(AluguelRequestDTO aluguelDto, BigDecimal valorTotal){
        this.pessoaId = aluguelDto.pessoaId();
        this.veiculoId = aluguelDto.veiculoId();
        this.dataInicio = aluguelDto.dataInicio();
        this.dataFim = aluguelDto.dataFim();
        this.valorTotal = valorTotal;
    }

}
