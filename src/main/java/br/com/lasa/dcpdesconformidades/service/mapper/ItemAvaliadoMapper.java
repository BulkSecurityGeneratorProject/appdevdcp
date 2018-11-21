package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAvaliadoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ItemAvaliado and its DTO ItemAvaliadoDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemAvaliacaoMapper.class, AvaliacaoMapper.class})
public interface ItemAvaliadoMapper extends EntityMapper<ItemAvaliadoDTO, ItemAvaliado> {

    @Mapping(source = "itemAvaliacao.id", target = "itemAvaliacaoId")
    @Mapping(source = "itemAvaliacao.descricao", target = "itemAvaliacaoDescricao")
    @Mapping(source = "avaliacao.id", target = "avaliacaoId")
    ItemAvaliadoDTO toDto(ItemAvaliado itemAvaliado);

    @Mapping(target = "anexos", ignore = true)
    @Mapping(source = "itemAvaliacaoId", target = "itemAvaliacao")
    @Mapping(source = "avaliacaoId", target = "avaliacao")
    ItemAvaliado toEntity(ItemAvaliadoDTO itemAvaliadoDTO);

    default ItemAvaliado fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemAvaliado itemAvaliado = new ItemAvaliado();
        itemAvaliado.setId(id);
        return itemAvaliado;
    }
}
