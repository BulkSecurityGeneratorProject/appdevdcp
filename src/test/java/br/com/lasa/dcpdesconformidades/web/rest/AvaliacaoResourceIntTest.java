package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.domain.User;
import br.com.lasa.dcpdesconformidades.domain.Questionario;
import br.com.lasa.dcpdesconformidades.domain.Loja;
import br.com.lasa.dcpdesconformidades.repository.AvaliacaoRepository;
import br.com.lasa.dcpdesconformidades.service.AvaliacaoService;
import br.com.lasa.dcpdesconformidades.service.dto.AvaliacaoDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.AvaliacaoMapper;
import br.com.lasa.dcpdesconformidades.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static br.com.lasa.dcpdesconformidades.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusAvaliacao;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CriticidadePainel;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CategorizacaoPerdaQuebra;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CategorizacaoPerdaQuebra;
/**
 * Test class for the AvaliacaoResource REST controller.
 *
 * @see AvaliacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class AvaliacaoResourceIntTest {

    private static final Instant DEFAULT_INICIADA_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INICIADA_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_LATITUDE_INICIO_AVALIACAO = 1D;
    private static final Double UPDATED_LATITUDE_INICIO_AVALIACAO = 2D;

    private static final Double DEFAULT_LONGITUDE_INICIO_AVALIACAO = 1D;
    private static final Double UPDATED_LONGITUDE_INICIO_AVALIACAO = 2D;

    private static final String DEFAULT_NOME_RESPONSAVEL_LOJA = "AAAAAAAAAA";
    private static final String UPDATED_NOME_RESPONSAVEL_LOJA = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRONTUARIO_RESPONSAVEL_LOJA = 1;
    private static final Integer UPDATED_PRONTUARIO_RESPONSAVEL_LOJA = 2;

    private static final Instant DEFAULT_SUBMETIDO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBMETIDO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_LATITUDE_SUBMISSAO_AVALIACAO = 1D;
    private static final Double UPDATED_LATITUDE_SUBMISSAO_AVALIACAO = 2D;

    private static final Double DEFAULT_LONGITUDE_SUBMISSAO_AVALIACAO = 1D;
    private static final Double UPDATED_LONGITUDE_SUBMISSAO_AVALIACAO = 2D;

    private static final String DEFAULT_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA = "BBBBBBBBBB";

    private static final StatusAvaliacao DEFAULT_STATUS = StatusAvaliacao.INICIADA;
    private static final StatusAvaliacao UPDATED_STATUS = StatusAvaliacao.CHECKLIST_FINALIZADO;

    private static final CriticidadePainel DEFAULT_CRITICIDADE_PAINEL = CriticidadePainel.INADMISSIVEL;
    private static final CriticidadePainel UPDATED_CRITICIDADE_PAINEL = CriticidadePainel.CONTROLE;

    private static final NivelEficiencia DEFAULT_NIVEL_EFICIENCIA_GERAL = NivelEficiencia.A;
    private static final NivelEficiencia UPDATED_NIVEL_EFICIENCIA_GERAL = NivelEficiencia.B;

    private static final NivelEficiencia DEFAULT_NIVEL_EFICIENCIA_PROCEDIMENTO = NivelEficiencia.A;
    private static final NivelEficiencia UPDATED_NIVEL_EFICIENCIA_PROCEDIMENTO = NivelEficiencia.B;

    private static final NivelEficiencia DEFAULT_NIVEL_EFICIENCIA_PESSOA = NivelEficiencia.A;
    private static final NivelEficiencia UPDATED_NIVEL_EFICIENCIA_PESSOA = NivelEficiencia.B;

    private static final NivelEficiencia DEFAULT_NIVEL_EFICIENCIA_PROCESSO = NivelEficiencia.A;
    private static final NivelEficiencia UPDATED_NIVEL_EFICIENCIA_PROCESSO = NivelEficiencia.B;

    private static final NivelEficiencia DEFAULT_NIVEL_EFICIENCIA_PRODUTO = NivelEficiencia.A;
    private static final NivelEficiencia UPDATED_NIVEL_EFICIENCIA_PRODUTO = NivelEficiencia.B;

    private static final Instant DEFAULT_CANCELADO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CANCELADO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MOTIVO_CANCELAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_MOTIVO_CANCELAMENTO = "BBBBBBBBBB";

    private static final Double DEFAULT_PERCENTUAL_PERDA = 1D;
    private static final Double UPDATED_PERCENTUAL_PERDA = 2D;

    private static final BigDecimal DEFAULT_FINANCEIRO_PERDA = new BigDecimal(1);
    private static final BigDecimal UPDATED_FINANCEIRO_PERDA = new BigDecimal(2);

    private static final Integer DEFAULT_PONTUACAO_PERDA = 1;
    private static final Integer UPDATED_PONTUACAO_PERDA = 2;

    private static final StatusItemAvaliado DEFAULT_STATUS_PERDA = StatusItemAvaliado.OK;
    private static final StatusItemAvaliado UPDATED_STATUS_PERDA = StatusItemAvaliado.NAO_OK;

    private static final CategorizacaoPerdaQuebra DEFAULT_CATEGORIZACAO_PERDA = CategorizacaoPerdaQuebra.INADMISSIVEL;
    private static final CategorizacaoPerdaQuebra UPDATED_CATEGORIZACAO_PERDA = CategorizacaoPerdaQuebra.CRITICO;

    private static final Double DEFAULT_PERCENTUAL_QUEBRA = 1D;
    private static final Double UPDATED_PERCENTUAL_QUEBRA = 2D;

    private static final BigDecimal DEFAULT_FINANCEIRO_QUEBRA = new BigDecimal(1);
    private static final BigDecimal UPDATED_FINANCEIRO_QUEBRA = new BigDecimal(2);

    private static final Integer DEFAULT_PONTUACAO_QUEBRA = 1;
    private static final Integer UPDATED_PONTUACAO_QUEBRA = 2;

    private static final StatusItemAvaliado DEFAULT_STATUS_QUEBRA = StatusItemAvaliado.OK;
    private static final StatusItemAvaliado UPDATED_STATUS_QUEBRA = StatusItemAvaliado.NAO_OK;

    private static final CategorizacaoPerdaQuebra DEFAULT_CATEGORIZACAO_QUEBRA = CategorizacaoPerdaQuebra.INADMISSIVEL;
    private static final CategorizacaoPerdaQuebra UPDATED_CATEGORIZACAO_QUEBRA = CategorizacaoPerdaQuebra.CRITICO;

    private static final Boolean DEFAULT_IMPORTADO_VIA_PLANILHA = false;
    private static final Boolean UPDATED_IMPORTADO_VIA_PLANILHA = true;

    private static final String DEFAULT_CAMINHO_ARQUIVO_PLANILHA = "AAAAAAAAAA";
    private static final String UPDATED_CAMINHO_ARQUIVO_PLANILHA = "BBBBBBBBBB";

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoMapper avaliacaoMapper;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAvaliacaoMockMvc;

    private Avaliacao avaliacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AvaliacaoResource avaliacaoResource = new AvaliacaoResource(avaliacaoService);
        this.restAvaliacaoMockMvc = MockMvcBuilders.standaloneSetup(avaliacaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Avaliacao createEntity(EntityManager em) {
        Avaliacao avaliacao = new Avaliacao()
            .iniciadaEm(DEFAULT_INICIADA_EM)
            .latitudeInicioAvaliacao(DEFAULT_LATITUDE_INICIO_AVALIACAO)
            .longitudeInicioAvaliacao(DEFAULT_LONGITUDE_INICIO_AVALIACAO)
            .nomeResponsavelLoja(DEFAULT_NOME_RESPONSAVEL_LOJA)
            .prontuarioResponsavelLoja(DEFAULT_PRONTUARIO_RESPONSAVEL_LOJA)
            .submetidoEm(DEFAULT_SUBMETIDO_EM)
            .latitudeSubmissaoAvaliacao(DEFAULT_LATITUDE_SUBMISSAO_AVALIACAO)
            .longitudeSubmissaoAvaliacao(DEFAULT_LONGITUDE_SUBMISSAO_AVALIACAO)
            .observacaoSubmissaoEnviadaForaDaLoja(DEFAULT_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA)
            .status(DEFAULT_STATUS)
            .criticidadePainel(DEFAULT_CRITICIDADE_PAINEL)
            .nivelEficienciaGeral(DEFAULT_NIVEL_EFICIENCIA_GERAL)
            .nivelEficienciaProcedimento(DEFAULT_NIVEL_EFICIENCIA_PROCEDIMENTO)
            .nivelEficienciaPessoa(DEFAULT_NIVEL_EFICIENCIA_PESSOA)
            .nivelEficienciaProcesso(DEFAULT_NIVEL_EFICIENCIA_PROCESSO)
            .nivelEficienciaProduto(DEFAULT_NIVEL_EFICIENCIA_PRODUTO)
            .canceladoEm(DEFAULT_CANCELADO_EM)
            .motivoCancelamento(DEFAULT_MOTIVO_CANCELAMENTO)
            .percentualPerda(DEFAULT_PERCENTUAL_PERDA)
            .financeiroPerda(DEFAULT_FINANCEIRO_PERDA)
            .pontuacaoPerda(DEFAULT_PONTUACAO_PERDA)
            .statusPerda(DEFAULT_STATUS_PERDA)
            .categorizacaoPerda(DEFAULT_CATEGORIZACAO_PERDA)
            .percentualQuebra(DEFAULT_PERCENTUAL_QUEBRA)
            .financeiroQuebra(DEFAULT_FINANCEIRO_QUEBRA)
            .pontuacaoQuebra(DEFAULT_PONTUACAO_QUEBRA)
            .statusQuebra(DEFAULT_STATUS_QUEBRA)
            .categorizacaoQuebra(DEFAULT_CATEGORIZACAO_QUEBRA)
            .importadoViaPlanilha(DEFAULT_IMPORTADO_VIA_PLANILHA)
            .caminhoArquivoPlanilha(DEFAULT_CAMINHO_ARQUIVO_PLANILHA);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        avaliacao.setAvaliador(user);
        // Add required entity
        Questionario questionario = QuestionarioResourceIntTest.createEntity(em);
        em.persist(questionario);
        em.flush();
        avaliacao.setQuestionario(questionario);
        // Add required entity
        Loja loja = LojaResourceIntTest.createEntity(em);
        em.persist(loja);
        em.flush();
        avaliacao.setLoja(loja);
        return avaliacao;
    }

    @Before
    public void initTest() {
        avaliacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createAvaliacao() throws Exception {
        int databaseSizeBeforeCreate = avaliacaoRepository.findAll().size();

        // Create the Avaliacao
        AvaliacaoDTO avaliacaoDTO = avaliacaoMapper.toDto(avaliacao);
        restAvaliacaoMockMvc.perform(post("/api/avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Avaliacao in the database
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Avaliacao testAvaliacao = avaliacaoList.get(avaliacaoList.size() - 1);
        assertThat(testAvaliacao.getIniciadaEm()).isEqualTo(DEFAULT_INICIADA_EM);
        assertThat(testAvaliacao.getLatitudeInicioAvaliacao()).isEqualTo(DEFAULT_LATITUDE_INICIO_AVALIACAO);
        assertThat(testAvaliacao.getLongitudeInicioAvaliacao()).isEqualTo(DEFAULT_LONGITUDE_INICIO_AVALIACAO);
        assertThat(testAvaliacao.getNomeResponsavelLoja()).isEqualTo(DEFAULT_NOME_RESPONSAVEL_LOJA);
        assertThat(testAvaliacao.getProntuarioResponsavelLoja()).isEqualTo(DEFAULT_PRONTUARIO_RESPONSAVEL_LOJA);
        assertThat(testAvaliacao.getSubmetidoEm()).isEqualTo(DEFAULT_SUBMETIDO_EM);
        assertThat(testAvaliacao.getLatitudeSubmissaoAvaliacao()).isEqualTo(DEFAULT_LATITUDE_SUBMISSAO_AVALIACAO);
        assertThat(testAvaliacao.getLongitudeSubmissaoAvaliacao()).isEqualTo(DEFAULT_LONGITUDE_SUBMISSAO_AVALIACAO);
        assertThat(testAvaliacao.getObservacaoSubmissaoEnviadaForaDaLoja()).isEqualTo(DEFAULT_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA);
        assertThat(testAvaliacao.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAvaliacao.getCriticidadePainel()).isEqualTo(DEFAULT_CRITICIDADE_PAINEL);
        assertThat(testAvaliacao.getNivelEficienciaGeral()).isEqualTo(DEFAULT_NIVEL_EFICIENCIA_GERAL);
        assertThat(testAvaliacao.getNivelEficienciaProcedimento()).isEqualTo(DEFAULT_NIVEL_EFICIENCIA_PROCEDIMENTO);
        assertThat(testAvaliacao.getNivelEficienciaPessoa()).isEqualTo(DEFAULT_NIVEL_EFICIENCIA_PESSOA);
        assertThat(testAvaliacao.getNivelEficienciaProcesso()).isEqualTo(DEFAULT_NIVEL_EFICIENCIA_PROCESSO);
        assertThat(testAvaliacao.getNivelEficienciaProduto()).isEqualTo(DEFAULT_NIVEL_EFICIENCIA_PRODUTO);
        assertThat(testAvaliacao.getCanceladoEm()).isEqualTo(DEFAULT_CANCELADO_EM);
        assertThat(testAvaliacao.getMotivoCancelamento()).isEqualTo(DEFAULT_MOTIVO_CANCELAMENTO);
        assertThat(testAvaliacao.getPercentualPerda()).isEqualTo(DEFAULT_PERCENTUAL_PERDA);
        assertThat(testAvaliacao.getFinanceiroPerda()).isEqualTo(DEFAULT_FINANCEIRO_PERDA);
        assertThat(testAvaliacao.getPontuacaoPerda()).isEqualTo(DEFAULT_PONTUACAO_PERDA);
        assertThat(testAvaliacao.getStatusPerda()).isEqualTo(DEFAULT_STATUS_PERDA);
        assertThat(testAvaliacao.getCategorizacaoPerda()).isEqualTo(DEFAULT_CATEGORIZACAO_PERDA);
        assertThat(testAvaliacao.getPercentualQuebra()).isEqualTo(DEFAULT_PERCENTUAL_QUEBRA);
        assertThat(testAvaliacao.getFinanceiroQuebra()).isEqualTo(DEFAULT_FINANCEIRO_QUEBRA);
        assertThat(testAvaliacao.getPontuacaoQuebra()).isEqualTo(DEFAULT_PONTUACAO_QUEBRA);
        assertThat(testAvaliacao.getStatusQuebra()).isEqualTo(DEFAULT_STATUS_QUEBRA);
        assertThat(testAvaliacao.getCategorizacaoQuebra()).isEqualTo(DEFAULT_CATEGORIZACAO_QUEBRA);
        assertThat(testAvaliacao.isImportadoViaPlanilha()).isEqualTo(DEFAULT_IMPORTADO_VIA_PLANILHA);
        assertThat(testAvaliacao.getCaminhoArquivoPlanilha()).isEqualTo(DEFAULT_CAMINHO_ARQUIVO_PLANILHA);
    }

    @Test
    @Transactional
    public void createAvaliacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avaliacaoRepository.findAll().size();

        // Create the Avaliacao with an existing ID
        avaliacao.setId(1L);
        AvaliacaoDTO avaliacaoDTO = avaliacaoMapper.toDto(avaliacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvaliacaoMockMvc.perform(post("/api/avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Avaliacao in the database
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIniciadaEmIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliacaoRepository.findAll().size();
        // set the field null
        avaliacao.setIniciadaEm(null);

        // Create the Avaliacao, which fails.
        AvaliacaoDTO avaliacaoDTO = avaliacaoMapper.toDto(avaliacao);

        restAvaliacaoMockMvc.perform(post("/api/avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatitudeInicioAvaliacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliacaoRepository.findAll().size();
        // set the field null
        avaliacao.setLatitudeInicioAvaliacao(null);

        // Create the Avaliacao, which fails.
        AvaliacaoDTO avaliacaoDTO = avaliacaoMapper.toDto(avaliacao);

        restAvaliacaoMockMvc.perform(post("/api/avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudeInicioAvaliacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliacaoRepository.findAll().size();
        // set the field null
        avaliacao.setLongitudeInicioAvaliacao(null);

        // Create the Avaliacao, which fails.
        AvaliacaoDTO avaliacaoDTO = avaliacaoMapper.toDto(avaliacao);

        restAvaliacaoMockMvc.perform(post("/api/avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliacaoRepository.findAll().size();
        // set the field null
        avaliacao.setStatus(null);

        // Create the Avaliacao, which fails.
        AvaliacaoDTO avaliacaoDTO = avaliacaoMapper.toDto(avaliacao);

        restAvaliacaoMockMvc.perform(post("/api/avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkImportadoViaPlanilhaIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliacaoRepository.findAll().size();
        // set the field null
        avaliacao.setImportadoViaPlanilha(null);

        // Create the Avaliacao, which fails.
        AvaliacaoDTO avaliacaoDTO = avaliacaoMapper.toDto(avaliacao);

        restAvaliacaoMockMvc.perform(post("/api/avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAvaliacaos() throws Exception {
        // Initialize the database
        avaliacaoRepository.saveAndFlush(avaliacao);

        // Get all the avaliacaoList
        restAvaliacaoMockMvc.perform(get("/api/avaliacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avaliacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].iniciadaEm").value(hasItem(DEFAULT_INICIADA_EM.toString())))
            .andExpect(jsonPath("$.[*].latitudeInicioAvaliacao").value(hasItem(DEFAULT_LATITUDE_INICIO_AVALIACAO.doubleValue())))
            .andExpect(jsonPath("$.[*].longitudeInicioAvaliacao").value(hasItem(DEFAULT_LONGITUDE_INICIO_AVALIACAO.doubleValue())))
            .andExpect(jsonPath("$.[*].nomeResponsavelLoja").value(hasItem(DEFAULT_NOME_RESPONSAVEL_LOJA.toString())))
            .andExpect(jsonPath("$.[*].prontuarioResponsavelLoja").value(hasItem(DEFAULT_PRONTUARIO_RESPONSAVEL_LOJA)))
            .andExpect(jsonPath("$.[*].submetidoEm").value(hasItem(DEFAULT_SUBMETIDO_EM.toString())))
            .andExpect(jsonPath("$.[*].latitudeSubmissaoAvaliacao").value(hasItem(DEFAULT_LATITUDE_SUBMISSAO_AVALIACAO.doubleValue())))
            .andExpect(jsonPath("$.[*].longitudeSubmissaoAvaliacao").value(hasItem(DEFAULT_LONGITUDE_SUBMISSAO_AVALIACAO.doubleValue())))
            .andExpect(jsonPath("$.[*].observacaoSubmissaoEnviadaForaDaLoja").value(hasItem(DEFAULT_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].criticidadePainel").value(hasItem(DEFAULT_CRITICIDADE_PAINEL.toString())))
            .andExpect(jsonPath("$.[*].nivelEficienciaGeral").value(hasItem(DEFAULT_NIVEL_EFICIENCIA_GERAL.toString())))
            .andExpect(jsonPath("$.[*].nivelEficienciaProcedimento").value(hasItem(DEFAULT_NIVEL_EFICIENCIA_PROCEDIMENTO.toString())))
            .andExpect(jsonPath("$.[*].nivelEficienciaPessoa").value(hasItem(DEFAULT_NIVEL_EFICIENCIA_PESSOA.toString())))
            .andExpect(jsonPath("$.[*].nivelEficienciaProcesso").value(hasItem(DEFAULT_NIVEL_EFICIENCIA_PROCESSO.toString())))
            .andExpect(jsonPath("$.[*].nivelEficienciaProduto").value(hasItem(DEFAULT_NIVEL_EFICIENCIA_PRODUTO.toString())))
            .andExpect(jsonPath("$.[*].canceladoEm").value(hasItem(DEFAULT_CANCELADO_EM.toString())))
            .andExpect(jsonPath("$.[*].motivoCancelamento").value(hasItem(DEFAULT_MOTIVO_CANCELAMENTO.toString())))
            .andExpect(jsonPath("$.[*].percentualPerda").value(hasItem(DEFAULT_PERCENTUAL_PERDA.doubleValue())))
            .andExpect(jsonPath("$.[*].financeiroPerda").value(hasItem(DEFAULT_FINANCEIRO_PERDA.intValue())))
            .andExpect(jsonPath("$.[*].pontuacaoPerda").value(hasItem(DEFAULT_PONTUACAO_PERDA)))
            .andExpect(jsonPath("$.[*].statusPerda").value(hasItem(DEFAULT_STATUS_PERDA.toString())))
            .andExpect(jsonPath("$.[*].categorizacaoPerda").value(hasItem(DEFAULT_CATEGORIZACAO_PERDA.toString())))
            .andExpect(jsonPath("$.[*].percentualQuebra").value(hasItem(DEFAULT_PERCENTUAL_QUEBRA.doubleValue())))
            .andExpect(jsonPath("$.[*].financeiroQuebra").value(hasItem(DEFAULT_FINANCEIRO_QUEBRA.intValue())))
            .andExpect(jsonPath("$.[*].pontuacaoQuebra").value(hasItem(DEFAULT_PONTUACAO_QUEBRA)))
            .andExpect(jsonPath("$.[*].statusQuebra").value(hasItem(DEFAULT_STATUS_QUEBRA.toString())))
            .andExpect(jsonPath("$.[*].categorizacaoQuebra").value(hasItem(DEFAULT_CATEGORIZACAO_QUEBRA.toString())))
            .andExpect(jsonPath("$.[*].importadoViaPlanilha").value(hasItem(DEFAULT_IMPORTADO_VIA_PLANILHA.booleanValue())))
            .andExpect(jsonPath("$.[*].caminhoArquivoPlanilha").value(hasItem(DEFAULT_CAMINHO_ARQUIVO_PLANILHA.toString())));
    }
    
    @Test
    @Transactional
    public void getAvaliacao() throws Exception {
        // Initialize the database
        avaliacaoRepository.saveAndFlush(avaliacao);

        // Get the avaliacao
        restAvaliacaoMockMvc.perform(get("/api/avaliacaos/{id}", avaliacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(avaliacao.getId().intValue()))
            .andExpect(jsonPath("$.iniciadaEm").value(DEFAULT_INICIADA_EM.toString()))
            .andExpect(jsonPath("$.latitudeInicioAvaliacao").value(DEFAULT_LATITUDE_INICIO_AVALIACAO.doubleValue()))
            .andExpect(jsonPath("$.longitudeInicioAvaliacao").value(DEFAULT_LONGITUDE_INICIO_AVALIACAO.doubleValue()))
            .andExpect(jsonPath("$.nomeResponsavelLoja").value(DEFAULT_NOME_RESPONSAVEL_LOJA.toString()))
            .andExpect(jsonPath("$.prontuarioResponsavelLoja").value(DEFAULT_PRONTUARIO_RESPONSAVEL_LOJA))
            .andExpect(jsonPath("$.submetidoEm").value(DEFAULT_SUBMETIDO_EM.toString()))
            .andExpect(jsonPath("$.latitudeSubmissaoAvaliacao").value(DEFAULT_LATITUDE_SUBMISSAO_AVALIACAO.doubleValue()))
            .andExpect(jsonPath("$.longitudeSubmissaoAvaliacao").value(DEFAULT_LONGITUDE_SUBMISSAO_AVALIACAO.doubleValue()))
            .andExpect(jsonPath("$.observacaoSubmissaoEnviadaForaDaLoja").value(DEFAULT_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.criticidadePainel").value(DEFAULT_CRITICIDADE_PAINEL.toString()))
            .andExpect(jsonPath("$.nivelEficienciaGeral").value(DEFAULT_NIVEL_EFICIENCIA_GERAL.toString()))
            .andExpect(jsonPath("$.nivelEficienciaProcedimento").value(DEFAULT_NIVEL_EFICIENCIA_PROCEDIMENTO.toString()))
            .andExpect(jsonPath("$.nivelEficienciaPessoa").value(DEFAULT_NIVEL_EFICIENCIA_PESSOA.toString()))
            .andExpect(jsonPath("$.nivelEficienciaProcesso").value(DEFAULT_NIVEL_EFICIENCIA_PROCESSO.toString()))
            .andExpect(jsonPath("$.nivelEficienciaProduto").value(DEFAULT_NIVEL_EFICIENCIA_PRODUTO.toString()))
            .andExpect(jsonPath("$.canceladoEm").value(DEFAULT_CANCELADO_EM.toString()))
            .andExpect(jsonPath("$.motivoCancelamento").value(DEFAULT_MOTIVO_CANCELAMENTO.toString()))
            .andExpect(jsonPath("$.percentualPerda").value(DEFAULT_PERCENTUAL_PERDA.doubleValue()))
            .andExpect(jsonPath("$.financeiroPerda").value(DEFAULT_FINANCEIRO_PERDA.intValue()))
            .andExpect(jsonPath("$.pontuacaoPerda").value(DEFAULT_PONTUACAO_PERDA))
            .andExpect(jsonPath("$.statusPerda").value(DEFAULT_STATUS_PERDA.toString()))
            .andExpect(jsonPath("$.categorizacaoPerda").value(DEFAULT_CATEGORIZACAO_PERDA.toString()))
            .andExpect(jsonPath("$.percentualQuebra").value(DEFAULT_PERCENTUAL_QUEBRA.doubleValue()))
            .andExpect(jsonPath("$.financeiroQuebra").value(DEFAULT_FINANCEIRO_QUEBRA.intValue()))
            .andExpect(jsonPath("$.pontuacaoQuebra").value(DEFAULT_PONTUACAO_QUEBRA))
            .andExpect(jsonPath("$.statusQuebra").value(DEFAULT_STATUS_QUEBRA.toString()))
            .andExpect(jsonPath("$.categorizacaoQuebra").value(DEFAULT_CATEGORIZACAO_QUEBRA.toString()))
            .andExpect(jsonPath("$.importadoViaPlanilha").value(DEFAULT_IMPORTADO_VIA_PLANILHA.booleanValue()))
            .andExpect(jsonPath("$.caminhoArquivoPlanilha").value(DEFAULT_CAMINHO_ARQUIVO_PLANILHA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAvaliacao() throws Exception {
        // Get the avaliacao
        restAvaliacaoMockMvc.perform(get("/api/avaliacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAvaliacao() throws Exception {
        // Initialize the database
        avaliacaoRepository.saveAndFlush(avaliacao);

        int databaseSizeBeforeUpdate = avaliacaoRepository.findAll().size();

        // Update the avaliacao
        Avaliacao updatedAvaliacao = avaliacaoRepository.findById(avaliacao.getId()).get();
        // Disconnect from session so that the updates on updatedAvaliacao are not directly saved in db
        em.detach(updatedAvaliacao);
        updatedAvaliacao
            .iniciadaEm(UPDATED_INICIADA_EM)
            .latitudeInicioAvaliacao(UPDATED_LATITUDE_INICIO_AVALIACAO)
            .longitudeInicioAvaliacao(UPDATED_LONGITUDE_INICIO_AVALIACAO)
            .nomeResponsavelLoja(UPDATED_NOME_RESPONSAVEL_LOJA)
            .prontuarioResponsavelLoja(UPDATED_PRONTUARIO_RESPONSAVEL_LOJA)
            .submetidoEm(UPDATED_SUBMETIDO_EM)
            .latitudeSubmissaoAvaliacao(UPDATED_LATITUDE_SUBMISSAO_AVALIACAO)
            .longitudeSubmissaoAvaliacao(UPDATED_LONGITUDE_SUBMISSAO_AVALIACAO)
            .observacaoSubmissaoEnviadaForaDaLoja(UPDATED_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA)
            .status(UPDATED_STATUS)
            .criticidadePainel(UPDATED_CRITICIDADE_PAINEL)
            .nivelEficienciaGeral(UPDATED_NIVEL_EFICIENCIA_GERAL)
            .nivelEficienciaProcedimento(UPDATED_NIVEL_EFICIENCIA_PROCEDIMENTO)
            .nivelEficienciaPessoa(UPDATED_NIVEL_EFICIENCIA_PESSOA)
            .nivelEficienciaProcesso(UPDATED_NIVEL_EFICIENCIA_PROCESSO)
            .nivelEficienciaProduto(UPDATED_NIVEL_EFICIENCIA_PRODUTO)
            .canceladoEm(UPDATED_CANCELADO_EM)
            .motivoCancelamento(UPDATED_MOTIVO_CANCELAMENTO)
            .percentualPerda(UPDATED_PERCENTUAL_PERDA)
            .financeiroPerda(UPDATED_FINANCEIRO_PERDA)
            .pontuacaoPerda(UPDATED_PONTUACAO_PERDA)
            .statusPerda(UPDATED_STATUS_PERDA)
            .categorizacaoPerda(UPDATED_CATEGORIZACAO_PERDA)
            .percentualQuebra(UPDATED_PERCENTUAL_QUEBRA)
            .financeiroQuebra(UPDATED_FINANCEIRO_QUEBRA)
            .pontuacaoQuebra(UPDATED_PONTUACAO_QUEBRA)
            .statusQuebra(UPDATED_STATUS_QUEBRA)
            .categorizacaoQuebra(UPDATED_CATEGORIZACAO_QUEBRA)
            .importadoViaPlanilha(UPDATED_IMPORTADO_VIA_PLANILHA)
            .caminhoArquivoPlanilha(UPDATED_CAMINHO_ARQUIVO_PLANILHA);
        AvaliacaoDTO avaliacaoDTO = avaliacaoMapper.toDto(updatedAvaliacao);

        restAvaliacaoMockMvc.perform(put("/api/avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Avaliacao in the database
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeUpdate);
        Avaliacao testAvaliacao = avaliacaoList.get(avaliacaoList.size() - 1);
        assertThat(testAvaliacao.getIniciadaEm()).isEqualTo(UPDATED_INICIADA_EM);
        assertThat(testAvaliacao.getLatitudeInicioAvaliacao()).isEqualTo(UPDATED_LATITUDE_INICIO_AVALIACAO);
        assertThat(testAvaliacao.getLongitudeInicioAvaliacao()).isEqualTo(UPDATED_LONGITUDE_INICIO_AVALIACAO);
        assertThat(testAvaliacao.getNomeResponsavelLoja()).isEqualTo(UPDATED_NOME_RESPONSAVEL_LOJA);
        assertThat(testAvaliacao.getProntuarioResponsavelLoja()).isEqualTo(UPDATED_PRONTUARIO_RESPONSAVEL_LOJA);
        assertThat(testAvaliacao.getSubmetidoEm()).isEqualTo(UPDATED_SUBMETIDO_EM);
        assertThat(testAvaliacao.getLatitudeSubmissaoAvaliacao()).isEqualTo(UPDATED_LATITUDE_SUBMISSAO_AVALIACAO);
        assertThat(testAvaliacao.getLongitudeSubmissaoAvaliacao()).isEqualTo(UPDATED_LONGITUDE_SUBMISSAO_AVALIACAO);
        assertThat(testAvaliacao.getObservacaoSubmissaoEnviadaForaDaLoja()).isEqualTo(UPDATED_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA);
        assertThat(testAvaliacao.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAvaliacao.getCriticidadePainel()).isEqualTo(UPDATED_CRITICIDADE_PAINEL);
        assertThat(testAvaliacao.getNivelEficienciaGeral()).isEqualTo(UPDATED_NIVEL_EFICIENCIA_GERAL);
        assertThat(testAvaliacao.getNivelEficienciaProcedimento()).isEqualTo(UPDATED_NIVEL_EFICIENCIA_PROCEDIMENTO);
        assertThat(testAvaliacao.getNivelEficienciaPessoa()).isEqualTo(UPDATED_NIVEL_EFICIENCIA_PESSOA);
        assertThat(testAvaliacao.getNivelEficienciaProcesso()).isEqualTo(UPDATED_NIVEL_EFICIENCIA_PROCESSO);
        assertThat(testAvaliacao.getNivelEficienciaProduto()).isEqualTo(UPDATED_NIVEL_EFICIENCIA_PRODUTO);
        assertThat(testAvaliacao.getCanceladoEm()).isEqualTo(UPDATED_CANCELADO_EM);
        assertThat(testAvaliacao.getMotivoCancelamento()).isEqualTo(UPDATED_MOTIVO_CANCELAMENTO);
        assertThat(testAvaliacao.getPercentualPerda()).isEqualTo(UPDATED_PERCENTUAL_PERDA);
        assertThat(testAvaliacao.getFinanceiroPerda()).isEqualTo(UPDATED_FINANCEIRO_PERDA);
        assertThat(testAvaliacao.getPontuacaoPerda()).isEqualTo(UPDATED_PONTUACAO_PERDA);
        assertThat(testAvaliacao.getStatusPerda()).isEqualTo(UPDATED_STATUS_PERDA);
        assertThat(testAvaliacao.getCategorizacaoPerda()).isEqualTo(UPDATED_CATEGORIZACAO_PERDA);
        assertThat(testAvaliacao.getPercentualQuebra()).isEqualTo(UPDATED_PERCENTUAL_QUEBRA);
        assertThat(testAvaliacao.getFinanceiroQuebra()).isEqualTo(UPDATED_FINANCEIRO_QUEBRA);
        assertThat(testAvaliacao.getPontuacaoQuebra()).isEqualTo(UPDATED_PONTUACAO_QUEBRA);
        assertThat(testAvaliacao.getStatusQuebra()).isEqualTo(UPDATED_STATUS_QUEBRA);
        assertThat(testAvaliacao.getCategorizacaoQuebra()).isEqualTo(UPDATED_CATEGORIZACAO_QUEBRA);
        assertThat(testAvaliacao.isImportadoViaPlanilha()).isEqualTo(UPDATED_IMPORTADO_VIA_PLANILHA);
        assertThat(testAvaliacao.getCaminhoArquivoPlanilha()).isEqualTo(UPDATED_CAMINHO_ARQUIVO_PLANILHA);
    }

    @Test
    @Transactional
    public void updateNonExistingAvaliacao() throws Exception {
        int databaseSizeBeforeUpdate = avaliacaoRepository.findAll().size();

        // Create the Avaliacao
        AvaliacaoDTO avaliacaoDTO = avaliacaoMapper.toDto(avaliacao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvaliacaoMockMvc.perform(put("/api/avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Avaliacao in the database
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAvaliacao() throws Exception {
        // Initialize the database
        avaliacaoRepository.saveAndFlush(avaliacao);

        int databaseSizeBeforeDelete = avaliacaoRepository.findAll().size();

        // Get the avaliacao
        restAvaliacaoMockMvc.perform(delete("/api/avaliacaos/{id}", avaliacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Avaliacao.class);
        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setId(1L);
        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setId(avaliacao1.getId());
        assertThat(avaliacao1).isEqualTo(avaliacao2);
        avaliacao2.setId(2L);
        assertThat(avaliacao1).isNotEqualTo(avaliacao2);
        avaliacao1.setId(null);
        assertThat(avaliacao1).isNotEqualTo(avaliacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvaliacaoDTO.class);
        AvaliacaoDTO avaliacaoDTO1 = new AvaliacaoDTO();
        avaliacaoDTO1.setId(1L);
        AvaliacaoDTO avaliacaoDTO2 = new AvaliacaoDTO();
        assertThat(avaliacaoDTO1).isNotEqualTo(avaliacaoDTO2);
        avaliacaoDTO2.setId(avaliacaoDTO1.getId());
        assertThat(avaliacaoDTO1).isEqualTo(avaliacaoDTO2);
        avaliacaoDTO2.setId(2L);
        assertThat(avaliacaoDTO1).isNotEqualTo(avaliacaoDTO2);
        avaliacaoDTO1.setId(null);
        assertThat(avaliacaoDTO1).isNotEqualTo(avaliacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(avaliacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(avaliacaoMapper.fromId(null)).isNull();
    }
}
