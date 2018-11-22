package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.LojaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Loja and its DTO LojaDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface LojaMapper extends EntityMapper<LojaDTO, Loja> {



    default Loja fromId(Long id) {
        if (id == null) {
            return null;
        }
        Loja loja = new Loja();
        loja.setId(id);
        return loja;
    }
}
