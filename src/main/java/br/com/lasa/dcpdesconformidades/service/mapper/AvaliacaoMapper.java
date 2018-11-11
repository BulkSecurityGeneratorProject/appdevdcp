package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.AvaliacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Avaliacao and its DTO AvaliacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {QuestionarioMapper.class, AvaliadorMapper.class})
public interface AvaliacaoMapper extends EntityMapper<AvaliacaoDTO, Avaliacao> {

    @Mapping(source = "questionario.id", target = "questionarioId")
    @Mapping(source = "questionario.nome", target = "questionarioNome")
    @Mapping(source = "avaliador.id", target = "avaliadorId")
    @Mapping(source = "avaliador.nome", target = "avaliadorNome")
    AvaliacaoDTO toDto(Avaliacao avaliacao);

    @Mapping(target = "itemAvaliados", ignore = true)
    @Mapping(target = "itensPerdaEQuebraAcumulados", ignore = true)
    @Mapping(target = "itensAuditados", ignore = true)
    @Mapping(target = "itensComAjusteSolicitados", ignore = true)
    @Mapping(source = "questionarioId", target = "questionario")
    @Mapping(source = "avaliadorId", target = "avaliador")
    Avaliacao toEntity(AvaliacaoDTO avaliacaoDTO);

    default Avaliacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        return avaliacao;
    }
}
