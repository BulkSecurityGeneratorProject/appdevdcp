package br.com.lasa.dcpdesconformidades.service.mapper;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.service.dto.AvaliacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Avaliacao and its DTO AvaliacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, QuestionarioMapper.class, ItemAvaliadoMapper.class, ItemAuditadoMapper.class, ItemSolicitadoAjusteMapper.class})
public interface AvaliacaoMapper extends EntityMapper<AvaliacaoDTO, Avaliacao> {

    @Mapping(source = "avaliador.id", target = "avaliadorId")
    @Mapping(source = "avaliador.name", target = "avaliadorName")
    @Mapping(source = "avaliador.prontuario", target = "avaliadorProntuario")
    @Mapping(source = "loja.id", target = "lojaId")
    @Mapping(source = "loja.nome", target = "lojaNome")
    AvaliacaoDTO toDto(Avaliacao avaliacao);

    @Mapping(target = "itensAvaliados", ignore = true)
    @Mapping(target = "itensAuditados", ignore = true)
    @Mapping(target = "itensComAjusteSolicitados", ignore = true)
    @Mapping(source = "avaliadorId", target = "avaliador")
    @Mapping(source = "lojaId", target = "loja.id")
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
