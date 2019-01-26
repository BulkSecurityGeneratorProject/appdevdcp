package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.ItemSolicitadoAjusteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ItemSolicitadoAjuste and its DTO ItemSolicitadoAjusteDTO.
 */
@Mapper(componentModel = "spring", uses = {AvaliacaoMapper.class})
public interface ItemSolicitadoAjusteMapper extends EntityMapper<ItemSolicitadoAjusteDTO, ItemSolicitadoAjuste> {

    @Mapping(source = "avaliacao.id", target = "avaliacaoId")
    ItemSolicitadoAjusteDTO toDto(ItemSolicitadoAjuste itemSolicitadoAjuste);

    @Mapping(source = "avaliacaoId", target = "avaliacao")
    ItemSolicitadoAjuste toEntity(ItemSolicitadoAjusteDTO itemSolicitadoAjusteDTO);

    default ItemSolicitadoAjuste fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemSolicitadoAjuste itemSolicitadoAjuste = new ItemSolicitadoAjuste();
        itemSolicitadoAjuste.setId(id);
        return itemSolicitadoAjuste;
    }
}
