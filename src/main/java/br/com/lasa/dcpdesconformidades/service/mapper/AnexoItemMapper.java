package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.AnexoItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AnexoItem and its DTO AnexoItemDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemAvaliadoMapper.class})
public interface AnexoItemMapper extends EntityMapper<AnexoItemDTO, AnexoItem> {

    @Mapping(source = "itemAvaliado.id", target = "itemAvaliadoId")
    AnexoItemDTO toDto(AnexoItem anexoItem);

    @Mapping(source = "itemAvaliadoId", target = "itemAvaliado")
    AnexoItem toEntity(AnexoItemDTO anexoItemDTO);

    default AnexoItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        AnexoItem anexoItem = new AnexoItem();
        anexoItem.setId(id);
        return anexoItem;
    }
}
