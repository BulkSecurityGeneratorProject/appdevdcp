package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.QuestionarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Questionario and its DTO QuestionarioDTO.
 */
@Mapper(componentModel = "spring", uses = {GrupoItensMapper.class})
public interface QuestionarioMapper extends EntityMapper<QuestionarioDTO, Questionario> {


    @Mapping(target = "avaliacoesRealizadas", ignore = true)
    Questionario toEntity(QuestionarioDTO questionarioDTO);

    default Questionario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Questionario questionario = new Questionario();
        questionario.setId(id);
        return questionario;
    }
}
