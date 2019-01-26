package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAvaliacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ItemAvaliacao and its DTO ItemAvaliacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {GrupoItensMapper.class})
public interface ItemAvaliacaoMapper extends EntityMapper<ItemAvaliacaoDTO, ItemAvaliacao> {

    @Mapping(source = "grupo.id", target = "grupoId")
    @Mapping(source = "grupo.nome", target = "grupoNome")
    ItemAvaliacaoDTO toDto(ItemAvaliacao itemAvaliacao);

    @Mapping(target = "itensAvaliados", ignore = true)
    @Mapping(source = "grupoId", target = "grupo")
    ItemAvaliacao toEntity(ItemAvaliacaoDTO itemAvaliacaoDTO);

    default ItemAvaliacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemAvaliacao itemAvaliacao = new ItemAvaliacao();
        itemAvaliacao.setId(id);
        return itemAvaliacao;
    }
}
