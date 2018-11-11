package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.GrupoItensDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GrupoItens and its DTO GrupoItensDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemAvaliacaoMapper.class})
public interface GrupoItensMapper extends EntityMapper<GrupoItensDTO, GrupoItens> {


    @Mapping(target = "questionarios", ignore = true)
    GrupoItens toEntity(GrupoItensDTO grupoItensDTO);

    default GrupoItens fromId(Long id) {
        if (id == null) {
            return null;
        }
        GrupoItens grupoItens = new GrupoItens();
        grupoItens.setId(id);
        return grupoItens;
    }
}
