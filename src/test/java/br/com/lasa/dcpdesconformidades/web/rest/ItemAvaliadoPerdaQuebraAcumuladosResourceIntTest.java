package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.ItemAvaliadoPerdaQuebraAcumulados;
import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.repository.ItemAvaliadoPerdaQuebraAcumuladosRepository;
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

import br.com.lasa.dcpdesconformidades.domain.enumeration.TipoItemAvaliadoPerdaQuebra;
import br.com.lasa.dcpdesconformidades.domain.enumeration.ClassificacaoPerdaQuebra;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CategorizacaoPerdaQuebra;
/**
 * Test class for the ItemAvaliadoPerdaQuebraAcumuladosResource REST controller.
 *
 * @see ItemAvaliadoPerdaQuebraAcumuladosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class ItemAvaliadoPerdaQuebraAcumuladosResourceIntTest {

    private static final TipoItemAvaliadoPerdaQuebra DEFAULT_TIPO = TipoItemAvaliadoPerdaQuebra.PERDA;
    private static final TipoItemAvaliadoPerdaQuebra UPDATED_TIPO = TipoItemAvaliadoPerdaQuebra.QUEBRA;

    private static final Instant DEFAULT_RESPONDIDO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RESPONDIDO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ULTIMA_ATUALIZACAO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ULTIMA_ATUALIZACAO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_PERCENTUAL = 1D;
    private static final Double UPDATED_PERCENTUAL = 2D;

    private static final BigDecimal DEFAULT_FINANCEIRO = new BigDecimal(1);
    private static final BigDecimal UPDATED_FINANCEIRO = new BigDecimal(2);

    private static final Integer DEFAULT_PONTUACAO = 1;
    private static final Integer UPDATED_PONTUACAO = 2;

    private static final Double DEFAULT_LATITUDE_LOCAL_RESPOSTA = 1D;
    private static final Double UPDATED_LATITUDE_LOCAL_RESPOSTA = 2D;

    private static final Double DEFAULT_LONGITUDE_LOCAL_RESPOSTA = 1D;
    private static final Double UPDATED_LONGITUDE_LOCAL_RESPOSTA = 2D;

    private static final ClassificacaoPerdaQuebra DEFAULT_CLASSIFICACAO = ClassificacaoPerdaQuebra.CONFORMIDADE;
    private static final ClassificacaoPerdaQuebra UPDATED_CLASSIFICACAO = ClassificacaoPerdaQuebra.DESCONFORMIDADE;

    private static final CategorizacaoPerdaQuebra DEFAULT_CATEGORIZACAO = CategorizacaoPerdaQuebra.INADMISSIVEL;
    private static final CategorizacaoPerdaQuebra UPDATED_CATEGORIZACAO = CategorizacaoPerdaQuebra.CRITICO;

    @Autowired
    private ItemAvaliadoPerdaQuebraAcumuladosRepository itemAvaliadoPerdaQuebraAcumuladosRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemAvaliadoPerdaQuebraAcumuladosMockMvc;

    private ItemAvaliadoPerdaQuebraAcumulados itemAvaliadoPerdaQuebraAcumulados;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemAvaliadoPerdaQuebraAcumuladosResource itemAvaliadoPerdaQuebraAcumuladosResource = new ItemAvaliadoPerdaQuebraAcumuladosResource(itemAvaliadoPerdaQuebraAcumuladosRepository);
        this.restItemAvaliadoPerdaQuebraAcumuladosMockMvc = MockMvcBuilders.standaloneSetup(itemAvaliadoPerdaQuebraAcumuladosResource)
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
    public static ItemAvaliadoPerdaQuebraAcumulados createEntity(EntityManager em) {
        ItemAvaliadoPerdaQuebraAcumulados itemAvaliadoPerdaQuebraAcumulados = new ItemAvaliadoPerdaQuebraAcumulados()
            .tipo(DEFAULT_TIPO)
            .respondidoEm(DEFAULT_RESPONDIDO_EM)
            .ultimaAtualizacaoEm(DEFAULT_ULTIMA_ATUALIZACAO_EM)
            .percentual(DEFAULT_PERCENTUAL)
            .financeiro(DEFAULT_FINANCEIRO)
            .pontuacao(DEFAULT_PONTUACAO)
            .latitudeLocalResposta(DEFAULT_LATITUDE_LOCAL_RESPOSTA)
            .longitudeLocalResposta(DEFAULT_LONGITUDE_LOCAL_RESPOSTA)
            .classificacao(DEFAULT_CLASSIFICACAO)
            .categorizacao(DEFAULT_CATEGORIZACAO);
        // Add required entity
        Avaliacao avaliacao = AvaliacaoResourceIntTest.createEntity(em);
        em.persist(avaliacao);
        em.flush();
        itemAvaliadoPerdaQuebraAcumulados.setAvaliacao(avaliacao);
        return itemAvaliadoPerdaQuebraAcumulados;
    }

    @Before
    public void initTest() {
        itemAvaliadoPerdaQuebraAcumulados = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemAvaliadoPerdaQuebraAcumulados() throws Exception {
        int databaseSizeBeforeCreate = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();

        // Create the ItemAvaliadoPerdaQuebraAcumulados
        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isCreated());

        // Validate the ItemAvaliadoPerdaQuebraAcumulados in the database
        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeCreate + 1);
        ItemAvaliadoPerdaQuebraAcumulados testItemAvaliadoPerdaQuebraAcumulados = itemAvaliadoPerdaQuebraAcumuladosList.get(itemAvaliadoPerdaQuebraAcumuladosList.size() - 1);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getRespondidoEm()).isEqualTo(DEFAULT_RESPONDIDO_EM);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getUltimaAtualizacaoEm()).isEqualTo(DEFAULT_ULTIMA_ATUALIZACAO_EM);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getPercentual()).isEqualTo(DEFAULT_PERCENTUAL);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getFinanceiro()).isEqualTo(DEFAULT_FINANCEIRO);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getPontuacao()).isEqualTo(DEFAULT_PONTUACAO);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getLatitudeLocalResposta()).isEqualTo(DEFAULT_LATITUDE_LOCAL_RESPOSTA);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getLongitudeLocalResposta()).isEqualTo(DEFAULT_LONGITUDE_LOCAL_RESPOSTA);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getClassificacao()).isEqualTo(DEFAULT_CLASSIFICACAO);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getCategorizacao()).isEqualTo(DEFAULT_CATEGORIZACAO);
    }

    @Test
    @Transactional
    public void createItemAvaliadoPerdaQuebraAcumuladosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();

        // Create the ItemAvaliadoPerdaQuebraAcumulados with an existing ID
        itemAvaliadoPerdaQuebraAcumulados.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAvaliadoPerdaQuebraAcumulados in the database
        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();
        // set the field null
        itemAvaliadoPerdaQuebraAcumulados.setTipo(null);

        // Create the ItemAvaliadoPerdaQuebraAcumulados, which fails.

        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRespondidoEmIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();
        // set the field null
        itemAvaliadoPerdaQuebraAcumulados.setRespondidoEm(null);

        // Create the ItemAvaliadoPerdaQuebraAcumulados, which fails.

        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPercentualIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();
        // set the field null
        itemAvaliadoPerdaQuebraAcumulados.setPercentual(null);

        // Create the ItemAvaliadoPerdaQuebraAcumulados, which fails.

        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFinanceiroIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();
        // set the field null
        itemAvaliadoPerdaQuebraAcumulados.setFinanceiro(null);

        // Create the ItemAvaliadoPerdaQuebraAcumulados, which fails.

        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontuacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();
        // set the field null
        itemAvaliadoPerdaQuebraAcumulados.setPontuacao(null);

        // Create the ItemAvaliadoPerdaQuebraAcumulados, which fails.

        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatitudeLocalRespostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();
        // set the field null
        itemAvaliadoPerdaQuebraAcumulados.setLatitudeLocalResposta(null);

        // Create the ItemAvaliadoPerdaQuebraAcumulados, which fails.

        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudeLocalRespostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();
        // set the field null
        itemAvaliadoPerdaQuebraAcumulados.setLongitudeLocalResposta(null);

        // Create the ItemAvaliadoPerdaQuebraAcumulados, which fails.

        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClassificacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();
        // set the field null
        itemAvaliadoPerdaQuebraAcumulados.setClassificacao(null);

        // Create the ItemAvaliadoPerdaQuebraAcumulados, which fails.

        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategorizacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();
        // set the field null
        itemAvaliadoPerdaQuebraAcumulados.setCategorizacao(null);

        // Create the ItemAvaliadoPerdaQuebraAcumulados, which fails.

        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(post("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemAvaliadoPerdaQuebraAcumulados() throws Exception {
        // Initialize the database
        itemAvaliadoPerdaQuebraAcumuladosRepository.saveAndFlush(itemAvaliadoPerdaQuebraAcumulados);

        // Get all the itemAvaliadoPerdaQuebraAcumuladosList
        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(get("/api/item-avaliado-perda-quebra-acumulados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemAvaliadoPerdaQuebraAcumulados.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].respondidoEm").value(hasItem(DEFAULT_RESPONDIDO_EM.toString())))
            .andExpect(jsonPath("$.[*].ultimaAtualizacaoEm").value(hasItem(DEFAULT_ULTIMA_ATUALIZACAO_EM.toString())))
            .andExpect(jsonPath("$.[*].percentual").value(hasItem(DEFAULT_PERCENTUAL.doubleValue())))
            .andExpect(jsonPath("$.[*].financeiro").value(hasItem(DEFAULT_FINANCEIRO.intValue())))
            .andExpect(jsonPath("$.[*].pontuacao").value(hasItem(DEFAULT_PONTUACAO)))
            .andExpect(jsonPath("$.[*].latitudeLocalResposta").value(hasItem(DEFAULT_LATITUDE_LOCAL_RESPOSTA.doubleValue())))
            .andExpect(jsonPath("$.[*].longitudeLocalResposta").value(hasItem(DEFAULT_LONGITUDE_LOCAL_RESPOSTA.doubleValue())))
            .andExpect(jsonPath("$.[*].classificacao").value(hasItem(DEFAULT_CLASSIFICACAO.toString())))
            .andExpect(jsonPath("$.[*].categorizacao").value(hasItem(DEFAULT_CATEGORIZACAO.toString())));
    }
    
    @Test
    @Transactional
    public void getItemAvaliadoPerdaQuebraAcumulados() throws Exception {
        // Initialize the database
        itemAvaliadoPerdaQuebraAcumuladosRepository.saveAndFlush(itemAvaliadoPerdaQuebraAcumulados);

        // Get the itemAvaliadoPerdaQuebraAcumulados
        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(get("/api/item-avaliado-perda-quebra-acumulados/{id}", itemAvaliadoPerdaQuebraAcumulados.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemAvaliadoPerdaQuebraAcumulados.getId().intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.respondidoEm").value(DEFAULT_RESPONDIDO_EM.toString()))
            .andExpect(jsonPath("$.ultimaAtualizacaoEm").value(DEFAULT_ULTIMA_ATUALIZACAO_EM.toString()))
            .andExpect(jsonPath("$.percentual").value(DEFAULT_PERCENTUAL.doubleValue()))
            .andExpect(jsonPath("$.financeiro").value(DEFAULT_FINANCEIRO.intValue()))
            .andExpect(jsonPath("$.pontuacao").value(DEFAULT_PONTUACAO))
            .andExpect(jsonPath("$.latitudeLocalResposta").value(DEFAULT_LATITUDE_LOCAL_RESPOSTA.doubleValue()))
            .andExpect(jsonPath("$.longitudeLocalResposta").value(DEFAULT_LONGITUDE_LOCAL_RESPOSTA.doubleValue()))
            .andExpect(jsonPath("$.classificacao").value(DEFAULT_CLASSIFICACAO.toString()))
            .andExpect(jsonPath("$.categorizacao").value(DEFAULT_CATEGORIZACAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemAvaliadoPerdaQuebraAcumulados() throws Exception {
        // Get the itemAvaliadoPerdaQuebraAcumulados
        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(get("/api/item-avaliado-perda-quebra-acumulados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemAvaliadoPerdaQuebraAcumulados() throws Exception {
        // Initialize the database
        itemAvaliadoPerdaQuebraAcumuladosRepository.saveAndFlush(itemAvaliadoPerdaQuebraAcumulados);

        int databaseSizeBeforeUpdate = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();

        // Update the itemAvaliadoPerdaQuebraAcumulados
        ItemAvaliadoPerdaQuebraAcumulados updatedItemAvaliadoPerdaQuebraAcumulados = itemAvaliadoPerdaQuebraAcumuladosRepository.findById(itemAvaliadoPerdaQuebraAcumulados.getId()).get();
        // Disconnect from session so that the updates on updatedItemAvaliadoPerdaQuebraAcumulados are not directly saved in db
        em.detach(updatedItemAvaliadoPerdaQuebraAcumulados);
        updatedItemAvaliadoPerdaQuebraAcumulados
            .tipo(UPDATED_TIPO)
            .respondidoEm(UPDATED_RESPONDIDO_EM)
            .ultimaAtualizacaoEm(UPDATED_ULTIMA_ATUALIZACAO_EM)
            .percentual(UPDATED_PERCENTUAL)
            .financeiro(UPDATED_FINANCEIRO)
            .pontuacao(UPDATED_PONTUACAO)
            .latitudeLocalResposta(UPDATED_LATITUDE_LOCAL_RESPOSTA)
            .longitudeLocalResposta(UPDATED_LONGITUDE_LOCAL_RESPOSTA)
            .classificacao(UPDATED_CLASSIFICACAO)
            .categorizacao(UPDATED_CATEGORIZACAO);

        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(put("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isOk());

        // Validate the ItemAvaliadoPerdaQuebraAcumulados in the database
        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeUpdate);
        ItemAvaliadoPerdaQuebraAcumulados testItemAvaliadoPerdaQuebraAcumulados = itemAvaliadoPerdaQuebraAcumuladosList.get(itemAvaliadoPerdaQuebraAcumuladosList.size() - 1);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getRespondidoEm()).isEqualTo(UPDATED_RESPONDIDO_EM);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getUltimaAtualizacaoEm()).isEqualTo(UPDATED_ULTIMA_ATUALIZACAO_EM);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getPercentual()).isEqualTo(UPDATED_PERCENTUAL);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getFinanceiro()).isEqualTo(UPDATED_FINANCEIRO);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getPontuacao()).isEqualTo(UPDATED_PONTUACAO);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getLatitudeLocalResposta()).isEqualTo(UPDATED_LATITUDE_LOCAL_RESPOSTA);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getLongitudeLocalResposta()).isEqualTo(UPDATED_LONGITUDE_LOCAL_RESPOSTA);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getClassificacao()).isEqualTo(UPDATED_CLASSIFICACAO);
        assertThat(testItemAvaliadoPerdaQuebraAcumulados.getCategorizacao()).isEqualTo(UPDATED_CATEGORIZACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingItemAvaliadoPerdaQuebraAcumulados() throws Exception {
        int databaseSizeBeforeUpdate = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();

        // Create the ItemAvaliadoPerdaQuebraAcumulados

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(put("/api/item-avaliado-perda-quebra-acumulados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoPerdaQuebraAcumulados)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAvaliadoPerdaQuebraAcumulados in the database
        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemAvaliadoPerdaQuebraAcumulados() throws Exception {
        // Initialize the database
        itemAvaliadoPerdaQuebraAcumuladosRepository.saveAndFlush(itemAvaliadoPerdaQuebraAcumulados);

        int databaseSizeBeforeDelete = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll().size();

        // Get the itemAvaliadoPerdaQuebraAcumulados
        restItemAvaliadoPerdaQuebraAcumuladosMockMvc.perform(delete("/api/item-avaliado-perda-quebra-acumulados/{id}", itemAvaliadoPerdaQuebraAcumulados.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumuladosList = itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
        assertThat(itemAvaliadoPerdaQuebraAcumuladosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAvaliadoPerdaQuebraAcumulados.class);
        ItemAvaliadoPerdaQuebraAcumulados itemAvaliadoPerdaQuebraAcumulados1 = new ItemAvaliadoPerdaQuebraAcumulados();
        itemAvaliadoPerdaQuebraAcumulados1.setId(1L);
        ItemAvaliadoPerdaQuebraAcumulados itemAvaliadoPerdaQuebraAcumulados2 = new ItemAvaliadoPerdaQuebraAcumulados();
        itemAvaliadoPerdaQuebraAcumulados2.setId(itemAvaliadoPerdaQuebraAcumulados1.getId());
        assertThat(itemAvaliadoPerdaQuebraAcumulados1).isEqualTo(itemAvaliadoPerdaQuebraAcumulados2);
        itemAvaliadoPerdaQuebraAcumulados2.setId(2L);
        assertThat(itemAvaliadoPerdaQuebraAcumulados1).isNotEqualTo(itemAvaliadoPerdaQuebraAcumulados2);
        itemAvaliadoPerdaQuebraAcumulados1.setId(null);
        assertThat(itemAvaliadoPerdaQuebraAcumulados1).isNotEqualTo(itemAvaliadoPerdaQuebraAcumulados2);
    }
}
