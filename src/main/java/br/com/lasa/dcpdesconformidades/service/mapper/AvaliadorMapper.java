package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.AvaliadorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Avaliador and its DTO AvaliadorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AvaliadorMapper extends EntityMapper<AvaliadorDTO, Avaliador> {


    @Mapping(target = "avaliacoes", ignore = true)
    @Mapping(target = "lojas", ignore = true)
    Avaliador toEntity(AvaliadorDTO avaliadorDTO);

    default Avaliador fromId(Long id) {
        if (id == null) {
            return null;
        }
        Avaliador avaliador = new Avaliador();
        avaliador.setId(id);
        return avaliador;
    }
}
