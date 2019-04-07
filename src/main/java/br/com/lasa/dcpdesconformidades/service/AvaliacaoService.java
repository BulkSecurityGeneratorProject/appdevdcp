package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.*;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusAvaliacao;
import br.com.lasa.dcpdesconformidades.repository.AvaliacaoRepository;
import br.com.lasa.dcpdesconformidades.repository.PerdaQuebraAcumuladosAnoLojaRepository;
import br.com.lasa.dcpdesconformidades.service.dto.AvaliacaoDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.AvaliacaoMapper;
import br.com.lasa.dcpdesconformidades.service.mapper.ItemAuditadoMapper;
import br.com.lasa.dcpdesconformidades.service.mapper.ItemAvaliadoMapper;
import br.com.lasa.dcpdesconformidades.service.mapper.ItemSolicitadoAjusteMapper;
import br.com.lasa.dcpdesconformidades.web.rest.errors.ForbiddenException;
import br.com.lasa.dcpdesconformidades.web.rest.errors.InternalServerErrorException;
import br.com.lasa.dcpdesconformidades.web.rest.errors.NotFoundException;
import br.com.lasa.dcpdesconformidades.web.rest.errors.PreconditionFailedException;
import br.com.lasa.dcpdesconformidades.web.rest.vm.IniciarAvaliacaoInputVM;
import br.com.lasa.dcpdesconformidades.web.rest.vm.SubmeterAvaliacaoInputVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Avaliacao.
 */
@Service
@Transactional
public class AvaliacaoService {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoService.class);

    private final AvaliacaoRepository avaliacaoRepository;

    private final AvaliacaoMapper avaliacaoMapper;

    private final UserService userService;

    private final QuestionarioService questionarioService;

    private final PerdaQuebraAcumuladosAnoLojaService perdaQuebraService;


    public AvaliacaoService(
        AvaliacaoRepository avaliacaoRepository,
        AvaliacaoMapper avaliacaoMapper,
        UserService userService,
        QuestionarioService questionarioService,
        PerdaQuebraAcumuladosAnoLojaService perdaQuebraService
    ) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.avaliacaoMapper = avaliacaoMapper;
        this.userService = userService;
        this.questionarioService = questionarioService;
        this.perdaQuebraService = perdaQuebraService;
    }

    /**
     * Save a avaliacao.
     *
     * @param avaliacaoDTO the entity to save
     * @return the persisted entity
     */
    public AvaliacaoDTO save(AvaliacaoDTO avaliacaoDTO) {
        log.debug("Request to save Avaliacao : {}", avaliacaoDTO);

        Avaliacao avaliacao = avaliacaoMapper.toEntity(avaliacaoDTO);
        avaliacao = avaliacaoRepository.save(avaliacao);
        return avaliacaoMapper.toDto(avaliacao);
    }

    /**
     * Get all the avaliacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AvaliacaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Avaliacaos");
        return avaliacaoRepository.findAll(pageable)
            .map(avaliacaoMapper::toDto);
    }


    /**
     * Get one avaliacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AvaliacaoDTO> findOne(Long id) {
        log.debug("Request to get Avaliacao : {}", id);
        return avaliacaoRepository.findById(id)
            .map(avaliacaoMapper::toDto);
    }

    /**
     * Delete the avaliacao by id.
     *
     * @param id the id of the entity
     */
    public void cancel(Long id, String motivoCancelamento) {
        log.debug("Request to delete Avaliacao : {}", id);
        avaliacaoRepository.setStatusAsCancelledFor(StatusAvaliacao.CANCELADA, Instant.now(), motivoCancelamento, id);
    }

    /**
     * Cria uma nova avaliação para determinada loja. Se já existir uma avaliação ativa do usuário para a loja, retorna esta avaliação ao invés de criar uma nova.
     * @param avaliacaoInput Parametros de criação da avaliação
     * @return Avaliação do usuário para a loja
     */
    public Avaliacao iniciarAvaliacaoPara(IniciarAvaliacaoInputVM avaliacaoInput) {
        final User user = userService.getUserWithAuthorities().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
        Loja loja = user.getLoja(avaliacaoInput.getIdLoja());
        if (loja == null)
            throw new ForbiddenException(MessageFormat.format("Usuário {0} não tem permissão para avaliar a loja {1}", user.getName(), avaliacaoInput.getIdLoja()));

        List<Avaliacao> avaliacoesAtivasAvaliadorLoja = avaliacaoRepository.findActiveByAvaliadorLoja(user.getLogin(), loja.getId());

        if(!avaliacoesAtivasAvaliadorLoja.isEmpty()){
            return avaliacoesAtivasAvaliadorLoja.get(0);
        }else{
            Avaliacao avaliacao = criarNovaAvaliacao(loja, user, avaliacaoInput);
            return  avaliacaoRepository.save(avaliacao);
        }
    }

    /**
     * Cria um novo objeto de Avaliacao para determinada loja e avaliador
     * @param loja loja da avaliação
     * @param user avaliador
     * @param avaliacaoInput parametros de criação
     * @return Nova avaliação com dados de input e perda
     */
    private Avaliacao criarNovaAvaliacao(Loja loja, User user, IniciarAvaliacaoInputVM avaliacaoInput) {
        Questionario questionario = questionarioService.buscaQuestionarioAtivo().orElseThrow(() -> new InternalServerErrorException("Nenhum questionário ativo no momento"));
        PerdaQuebraAcumuladosAnoLoja perdaQuebra = perdaQuebraService.ultimoAplicavelParaLoja(loja.getId()).orElseThrow(() -> new InternalServerErrorException("Loja sem perda/quebra cadastrada."));
        ;
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setLoja(loja);
        avaliacao.setAvaliador(user);
        avaliacao.setQuestionario(questionario);
        avaliacao.setIniciadaEm(Instant.now());
        avaliacao.setLatitudeInicioAvaliacao(avaliacaoInput.getLatitude());
        avaliacao.setLongitudeInicioAvaliacao(avaliacaoInput.getLongitude());
        avaliacao.setNomeResponsavelLoja(avaliacaoInput.getNomeResponsavelLoja());
        avaliacao.setProntuarioResponsavelLoja(avaliacaoInput.getProntuarioResponsavelLoja());
        avaliacao.importadoViaPlanilha(false);
        avaliacao.setStatus(StatusAvaliacao.INICIADA);

        avaliacao.setCategorizacaoPerda(perdaQuebra.getCategorizacaoPerda());
        avaliacao.setCategorizacaoQuebra(perdaQuebra.getCategorizacaoQuebra());
        avaliacao.setFinanceiroPerda(perdaQuebra.getFinanceiroPerda());
        avaliacao.setFinanceiroQuebra(perdaQuebra.getFinanceiroQuebra());
        avaliacao.setPercentualPerda(perdaQuebra.getPercentualPerda());
        avaliacao.setPercentualQuebra(perdaQuebra.getPercentualQuebra());
        avaliacao.setPontuacaoPerda(perdaQuebra.getPontuacaoPerda());
        avaliacao.setPontuacaoQuebra(perdaQuebra.getPontuacaoQuebra());
        avaliacao.setStatusPerda(perdaQuebra.getStatusPerda());
        avaliacao.setStatusQuebra(perdaQuebra.getStatusQuebra());

        return avaliacao;
    }

    /**
     * Submete determinada avaliação
     * @param avaliacaoInput Dados da avaliação realizada
     * @return avaliação submetida atualizada
     */
    public Avaliacao submeterAvaliacao(SubmeterAvaliacaoInputVM avaliacaoInput) {
        Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoInput.getId())
            .orElseThrow(() -> new NotFoundException("Nenhuma avaliação encontrada para o id:" + avaliacaoInput.getId()));
        final User user = userService.getUserWithAuthorities().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));

        if (!user.getId().equals(avaliacao.getAvaliador().getId()))
            throw new ForbiddenException(MessageFormat.format("Usuário {0} não tem permissão para realizar esta avaliação", user.getName()));

        if (!StatusAvaliacao.INICIADA.equals(avaliacao.getStatus()))
            throw new PreconditionFailedException(MessageFormat.format("Esta avaliação está na situação \"{0}\" e não pode ser submetida.", avaliacao.getStatus().getDescricao()));

        avaliacao = getAvaliacaoSubmetida(avaliacao, avaliacaoInput);

        avaliacao = avaliacaoRepository.save(avaliacao);
        return avaliacao;
    }

    /**
     * Retorna Avaliacao com dados de submissão
     * @param avaliacao Entidade de avaliação a ser alimentada
     * @param avaliacaoInput  dados de submissão
     * @return Avaliacao com dados de submissão
     */
    private Avaliacao getAvaliacaoSubmetida(Avaliacao avaliacao, SubmeterAvaliacaoInputVM avaliacaoInput) {
        avaliacaoInput.getItensAuditados().forEach(i -> i.setAvaliacaoId(avaliacaoInput.getId()));
        avaliacaoInput.getItensAvaliados().forEach(i -> i.setAvaliacaoId(avaliacaoInput.getId()));
        avaliacaoInput.getItensComAjusteSolicitados().forEach(i -> i.setAvaliacaoId(avaliacaoInput.getId()));

        AvaliacaoDTO avaliacaoDto = avaliacaoMapper.toDto(avaliacao);
        avaliacaoDto.setCriticidadePainel(avaliacaoInput.getCriticidadePainel());
        avaliacaoDto.setStatus(StatusAvaliacao.SUBMETIDA);
        avaliacaoDto.setItensAuditados(avaliacaoInput.getItensAuditados());
        avaliacaoDto.setItensAvaliados(avaliacaoInput.getItensAvaliados());
        avaliacaoDto.setItensComAjusteSolicitados(avaliacaoInput.getItensComAjusteSolicitados());
        avaliacaoDto.setLatitudeSubmissaoAvaliacao(avaliacaoInput.getLatitude());
        avaliacaoDto.setLongitudeSubmissaoAvaliacao(avaliacaoInput.getLongitude());
        avaliacaoDto.setSubmetidaEm(Instant.now());
        avaliacaoDto.setObservacaoSubmissaoEnviadaForaDaLoja(avaliacaoInput.getObservacaoSubmissaoEnviadaForaDaLoja());
        return avaliacaoMapper.toEntity(avaliacaoDto);
    }
}
