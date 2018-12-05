package br.com.lasa.dcpdesconformidades.service.mapper;

import org.mapstruct.Mapper;

import br.com.lasa.dcpdesconformidades.domain.Loja;
import br.com.lasa.dcpdesconformidades.service.dto.LojaRaioxDTO;

/**
 * Mapper for the entity Loja and its DTO LojaDTO.
 */
@Mapper(componentModel = "spring")
public interface LojaRaioxMapper extends EntityMapper<LojaRaioxDTO, Loja> {



    default Loja fromId(Long id) {
        if (id == null) {
            return null;
        }
        Loja loja = new Loja();
        loja.setId(id);
        return loja;
    }
}
