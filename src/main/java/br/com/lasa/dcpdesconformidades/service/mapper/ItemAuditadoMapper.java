package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAuditadoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ItemAuditado and its DTO ItemAuditadoDTO.
 */
@Mapper(componentModel = "spring", uses = {AvaliacaoMapper.class})
public interface ItemAuditadoMapper extends EntityMapper<ItemAuditadoDTO, ItemAuditado> {

    @Mapping(source = "avaliacao.id", target = "avaliacaoId")
    ItemAuditadoDTO toDto(ItemAuditado itemAuditado);

    @Mapping(source = "avaliacaoId", target = "avaliacao")
    ItemAuditado toEntity(ItemAuditadoDTO itemAuditadoDTO);

    default ItemAuditado fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemAuditado itemAuditado = new ItemAuditado();
        itemAuditado.setId(id);
        return itemAuditado;
    }
}
