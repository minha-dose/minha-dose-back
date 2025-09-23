package com.minhadose.demo.mapper;

import com.minhadose.demo.dto.MandatoryVaccinDTO;
import com.minhadose.demo.model.VaccinModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitária para mapear/converter objetos do tipo VaccinModel para DTOs.
 */
public class VaccinMapper {

    /**
     * Converte um único objeto VaccinModel para um MandatoryVaccinDTO.
     *
     * @param vaccin O objeto VaccinModel a ser convertido.
     * @return Um objeto MandatoryVaccinDTO contendo apenas o nome e a idade obrigatória.
     */
    public static MandatoryVaccinDTO toMandatoryDTO(VaccinModel vaccin) {
        // Cria um novo DTO usando os dados do modelo original
        return new MandatoryVaccinDTO(
                vaccin.getName(),
                vaccin.getMandatoryAge()
        );
    }

    /**
     * Converte uma lista de objetos VaccinModel para uma lista de MandatoryVaccinDTO.
     *
     * @param vaccins A lista de VaccinModel a ser convertida.
     * @return Uma lista de objetos MandatoryVaccinDTO.
     */
    public static List<MandatoryVaccinDTO> toMandatoryDTOList(List<VaccinModel> vaccins) {
        // Usa a API de Streams do Java para aplicar a conversão a cada item da lista
        return vaccins.stream()
                .map(VaccinMapper::toMandatoryDTO) // Para cada vacina na lista, chama o método de conversão singular
                .collect(Collectors.toList());    // Coleta os resultados em uma nova lista
    }
}