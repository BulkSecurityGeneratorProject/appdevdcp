package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static br.com.lasa.dcpdesconformidades.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusAvaliacao;
/**
 * Test class for the AvaliacaoResource REST controller.
 *
 * @see AvaliacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class AvaliacaoResourceIntTest {

    private static final Instant DEFAULT_DATA_INICIO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_INICIO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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
    private static final StatusAvaliacao UPDATED_STATUS = StatusAvaliacao.RELATORIO_FINALIZADO;

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
            .dataInicio(DEFAULT_DATA_INICIO)
            .latitudeInicioAvaliacao(DEFAULT_LATITUDE_INICIO_AVALIACAO)
            .longitudeInicioAvaliacao(DEFAULT_LONGITUDE_INICIO_AVALIACAO)
            .nomeResponsavelLoja(DEFAULT_NOME_RESPONSAVEL_LOJA)
            .prontuarioResponsavelLoja(DEFAULT_PRONTUARIO_RESPONSAVEL_LOJA)
            .submetidoEm(DEFAULT_SUBMETIDO_EM)
            .latitudeSubmissaoAvaliacao(DEFAULT_LATITUDE_SUBMISSAO_AVALIACAO)
            .longitudeSubmissaoAvaliacao(DEFAULT_LONGITUDE_SUBMISSAO_AVALIACAO)
            .observacaoSubmissaoEnviadaForaDaLoja(DEFAULT_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA)
            .status(DEFAULT_STATUS);
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
        assertThat(testAvaliacao.getDataInicio()).isEqualTo(DEFAULT_DATA_INICIO);
        assertThat(testAvaliacao.getLatitudeInicioAvaliacao()).isEqualTo(DEFAULT_LATITUDE_INICIO_AVALIACAO);
        assertThat(testAvaliacao.getLongitudeInicioAvaliacao()).isEqualTo(DEFAULT_LONGITUDE_INICIO_AVALIACAO);
        assertThat(testAvaliacao.getNomeResponsavelLoja()).isEqualTo(DEFAULT_NOME_RESPONSAVEL_LOJA);
        assertThat(testAvaliacao.getProntuarioResponsavelLoja()).isEqualTo(DEFAULT_PRONTUARIO_RESPONSAVEL_LOJA);
        assertThat(testAvaliacao.getSubmetidoEm()).isEqualTo(DEFAULT_SUBMETIDO_EM);
        assertThat(testAvaliacao.getLatitudeSubmissaoAvaliacao()).isEqualTo(DEFAULT_LATITUDE_SUBMISSAO_AVALIACAO);
        assertThat(testAvaliacao.getLongitudeSubmissaoAvaliacao()).isEqualTo(DEFAULT_LONGITUDE_SUBMISSAO_AVALIACAO);
        assertThat(testAvaliacao.getObservacaoSubmissaoEnviadaForaDaLoja()).isEqualTo(DEFAULT_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA);
        assertThat(testAvaliacao.getStatus()).isEqualTo(DEFAULT_STATUS);
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
    public void checkDataInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliacaoRepository.findAll().size();
        // set the field null
        avaliacao.setDataInicio(null);

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
    public void getAllAvaliacaos() throws Exception {
        // Initialize the database
        avaliacaoRepository.saveAndFlush(avaliacao);

        // Get all the avaliacaoList
        restAvaliacaoMockMvc.perform(get("/api/avaliacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avaliacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataInicio").value(hasItem(DEFAULT_DATA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].latitudeInicioAvaliacao").value(hasItem(DEFAULT_LATITUDE_INICIO_AVALIACAO.doubleValue())))
            .andExpect(jsonPath("$.[*].longitudeInicioAvaliacao").value(hasItem(DEFAULT_LONGITUDE_INICIO_AVALIACAO.doubleValue())))
            .andExpect(jsonPath("$.[*].nomeResponsavelLoja").value(hasItem(DEFAULT_NOME_RESPONSAVEL_LOJA.toString())))
            .andExpect(jsonPath("$.[*].prontuarioResponsavelLoja").value(hasItem(DEFAULT_PRONTUARIO_RESPONSAVEL_LOJA)))
            .andExpect(jsonPath("$.[*].submetidoEm").value(hasItem(DEFAULT_SUBMETIDO_EM.toString())))
            .andExpect(jsonPath("$.[*].latitudeSubmissaoAvaliacao").value(hasItem(DEFAULT_LATITUDE_SUBMISSAO_AVALIACAO.doubleValue())))
            .andExpect(jsonPath("$.[*].longitudeSubmissaoAvaliacao").value(hasItem(DEFAULT_LONGITUDE_SUBMISSAO_AVALIACAO.doubleValue())))
            .andExpect(jsonPath("$.[*].observacaoSubmissaoEnviadaForaDaLoja").value(hasItem(DEFAULT_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
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
            .andExpect(jsonPath("$.dataInicio").value(DEFAULT_DATA_INICIO.toString()))
            .andExpect(jsonPath("$.latitudeInicioAvaliacao").value(DEFAULT_LATITUDE_INICIO_AVALIACAO.doubleValue()))
            .andExpect(jsonPath("$.longitudeInicioAvaliacao").value(DEFAULT_LONGITUDE_INICIO_AVALIACAO.doubleValue()))
            .andExpect(jsonPath("$.nomeResponsavelLoja").value(DEFAULT_NOME_RESPONSAVEL_LOJA.toString()))
            .andExpect(jsonPath("$.prontuarioResponsavelLoja").value(DEFAULT_PRONTUARIO_RESPONSAVEL_LOJA))
            .andExpect(jsonPath("$.submetidoEm").value(DEFAULT_SUBMETIDO_EM.toString()))
            .andExpect(jsonPath("$.latitudeSubmissaoAvaliacao").value(DEFAULT_LATITUDE_SUBMISSAO_AVALIACAO.doubleValue()))
            .andExpect(jsonPath("$.longitudeSubmissaoAvaliacao").value(DEFAULT_LONGITUDE_SUBMISSAO_AVALIACAO.doubleValue()))
            .andExpect(jsonPath("$.observacaoSubmissaoEnviadaForaDaLoja").value(DEFAULT_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
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
            .dataInicio(UPDATED_DATA_INICIO)
            .latitudeInicioAvaliacao(UPDATED_LATITUDE_INICIO_AVALIACAO)
            .longitudeInicioAvaliacao(UPDATED_LONGITUDE_INICIO_AVALIACAO)
            .nomeResponsavelLoja(UPDATED_NOME_RESPONSAVEL_LOJA)
            .prontuarioResponsavelLoja(UPDATED_PRONTUARIO_RESPONSAVEL_LOJA)
            .submetidoEm(UPDATED_SUBMETIDO_EM)
            .latitudeSubmissaoAvaliacao(UPDATED_LATITUDE_SUBMISSAO_AVALIACAO)
            .longitudeSubmissaoAvaliacao(UPDATED_LONGITUDE_SUBMISSAO_AVALIACAO)
            .observacaoSubmissaoEnviadaForaDaLoja(UPDATED_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA)
            .status(UPDATED_STATUS);
        AvaliacaoDTO avaliacaoDTO = avaliacaoMapper.toDto(updatedAvaliacao);

        restAvaliacaoMockMvc.perform(put("/api/avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Avaliacao in the database
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();
        assertThat(avaliacaoList).hasSize(databaseSizeBeforeUpdate);
        Avaliacao testAvaliacao = avaliacaoList.get(avaliacaoList.size() - 1);
        assertThat(testAvaliacao.getDataInicio()).isEqualTo(UPDATED_DATA_INICIO);
        assertThat(testAvaliacao.getLatitudeInicioAvaliacao()).isEqualTo(UPDATED_LATITUDE_INICIO_AVALIACAO);
        assertThat(testAvaliacao.getLongitudeInicioAvaliacao()).isEqualTo(UPDATED_LONGITUDE_INICIO_AVALIACAO);
        assertThat(testAvaliacao.getNomeResponsavelLoja()).isEqualTo(UPDATED_NOME_RESPONSAVEL_LOJA);
        assertThat(testAvaliacao.getProntuarioResponsavelLoja()).isEqualTo(UPDATED_PRONTUARIO_RESPONSAVEL_LOJA);
        assertThat(testAvaliacao.getSubmetidoEm()).isEqualTo(UPDATED_SUBMETIDO_EM);
        assertThat(testAvaliacao.getLatitudeSubmissaoAvaliacao()).isEqualTo(UPDATED_LATITUDE_SUBMISSAO_AVALIACAO);
        assertThat(testAvaliacao.getLongitudeSubmissaoAvaliacao()).isEqualTo(UPDATED_LONGITUDE_SUBMISSAO_AVALIACAO);
        assertThat(testAvaliacao.getObservacaoSubmissaoEnviadaForaDaLoja()).isEqualTo(UPDATED_OBSERVACAO_SUBMISSAO_ENVIADA_FORA_DA_LOJA);
        assertThat(testAvaliacao.getStatus()).isEqualTo(UPDATED_STATUS);
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
