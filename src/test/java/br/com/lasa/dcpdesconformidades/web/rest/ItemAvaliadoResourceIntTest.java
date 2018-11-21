package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.ItemAvaliado;
import br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao;
import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.repository.ItemAvaliadoRepository;
import br.com.lasa.dcpdesconformidades.service.ItemAvaliadoService;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAvaliadoDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.ItemAvaliadoMapper;
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

import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;
/**
 * Test class for the ItemAvaliadoResource REST controller.
 *
 * @see ItemAvaliadoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class ItemAvaliadoResourceIntTest {

    private static final Instant DEFAULT_RESPONDIDO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RESPONDIDO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ULTIMA_ATUALIZACAO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ULTIMA_ATUALIZACAO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final StatusItemAvaliado DEFAULT_STATUS = StatusItemAvaliado.OK;
    private static final StatusItemAvaliado UPDATED_STATUS = StatusItemAvaliado.NAO_OK;

    private static final String DEFAULT_OBSERVACOES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACOES = "BBBBBBBBBB";

    private static final Double DEFAULT_LATITUDE_LOCAL_RESPOSTA = 1D;
    private static final Double UPDATED_LATITUDE_LOCAL_RESPOSTA = 2D;

    private static final Double DEFAULT_LONGITUDE_LOCAL_RESPOSTA = 1D;
    private static final Double UPDATED_LONGITUDE_LOCAL_RESPOSTA = 2D;

    private static final Integer DEFAULT_PONTOS_PROCEDIMENTO = 1;
    private static final Integer UPDATED_PONTOS_PROCEDIMENTO = 2;

    private static final Integer DEFAULT_PONTOS_PESSOA = 1;
    private static final Integer UPDATED_PONTOS_PESSOA = 2;

    private static final Integer DEFAULT_PONTOS_PROCESSO = 1;
    private static final Integer UPDATED_PONTOS_PROCESSO = 2;

    private static final Integer DEFAULT_PONTOS_PRODUTO = 1;
    private static final Integer UPDATED_PONTOS_PRODUTO = 2;

    private static final Integer DEFAULT_PONTOS_OBTIDOS_PROCEDIMENTO = 1;
    private static final Integer UPDATED_PONTOS_OBTIDOS_PROCEDIMENTO = 2;

    private static final Integer DEFAULT_PONTOS_OBTIDOS_PESSOA = 1;
    private static final Integer UPDATED_PONTOS_OBTIDOS_PESSOA = 2;

    private static final Integer DEFAULT_PONTOS_OBTIDOS_PROCESSO = 1;
    private static final Integer UPDATED_PONTOS_OBTIDOS_PROCESSO = 2;

    private static final Integer DEFAULT_PONTOS_OBTIDOS_PRODUTO = 1;
    private static final Integer UPDATED_PONTOS_OBTIDOS_PRODUTO = 2;

    @Autowired
    private ItemAvaliadoRepository itemAvaliadoRepository;

    @Autowired
    private ItemAvaliadoMapper itemAvaliadoMapper;

    @Autowired
    private ItemAvaliadoService itemAvaliadoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemAvaliadoMockMvc;

    private ItemAvaliado itemAvaliado;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemAvaliadoResource itemAvaliadoResource = new ItemAvaliadoResource(itemAvaliadoService);
        this.restItemAvaliadoMockMvc = MockMvcBuilders.standaloneSetup(itemAvaliadoResource)
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
    public static ItemAvaliado createEntity(EntityManager em) {
        ItemAvaliado itemAvaliado = new ItemAvaliado()
            .respondidoEm(DEFAULT_RESPONDIDO_EM)
            .ultimaAtualizacaoEm(DEFAULT_ULTIMA_ATUALIZACAO_EM)
            .status(DEFAULT_STATUS)
            .observacoes(DEFAULT_OBSERVACOES)
            .latitudeLocalResposta(DEFAULT_LATITUDE_LOCAL_RESPOSTA)
            .longitudeLocalResposta(DEFAULT_LONGITUDE_LOCAL_RESPOSTA)
            .pontosProcedimento(DEFAULT_PONTOS_PROCEDIMENTO)
            .pontosPessoa(DEFAULT_PONTOS_PESSOA)
            .pontosProcesso(DEFAULT_PONTOS_PROCESSO)
            .pontosProduto(DEFAULT_PONTOS_PRODUTO)
            .pontosObtidosProcedimento(DEFAULT_PONTOS_OBTIDOS_PROCEDIMENTO)
            .pontosObtidosPessoa(DEFAULT_PONTOS_OBTIDOS_PESSOA)
            .pontosObtidosProcesso(DEFAULT_PONTOS_OBTIDOS_PROCESSO)
            .pontosObtidosProduto(DEFAULT_PONTOS_OBTIDOS_PRODUTO);
        // Add required entity
        ItemAvaliacao itemAvaliacao = ItemAvaliacaoResourceIntTest.createEntity(em);
        em.persist(itemAvaliacao);
        em.flush();
        itemAvaliado.setItemAvaliacao(itemAvaliacao);
        // Add required entity
        Avaliacao avaliacao = AvaliacaoResourceIntTest.createEntity(em);
        em.persist(avaliacao);
        em.flush();
        itemAvaliado.setAvaliacao(avaliacao);
        return itemAvaliado;
    }

    @Before
    public void initTest() {
        itemAvaliado = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemAvaliado() throws Exception {
        int databaseSizeBeforeCreate = itemAvaliadoRepository.findAll().size();

        // Create the ItemAvaliado
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);
        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemAvaliado in the database
        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeCreate + 1);
        ItemAvaliado testItemAvaliado = itemAvaliadoList.get(itemAvaliadoList.size() - 1);
        assertThat(testItemAvaliado.getRespondidoEm()).isEqualTo(DEFAULT_RESPONDIDO_EM);
        assertThat(testItemAvaliado.getUltimaAtualizacaoEm()).isEqualTo(DEFAULT_ULTIMA_ATUALIZACAO_EM);
        assertThat(testItemAvaliado.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemAvaliado.getObservacoes()).isEqualTo(DEFAULT_OBSERVACOES);
        assertThat(testItemAvaliado.getLatitudeLocalResposta()).isEqualTo(DEFAULT_LATITUDE_LOCAL_RESPOSTA);
        assertThat(testItemAvaliado.getLongitudeLocalResposta()).isEqualTo(DEFAULT_LONGITUDE_LOCAL_RESPOSTA);
        assertThat(testItemAvaliado.getPontosProcedimento()).isEqualTo(DEFAULT_PONTOS_PROCEDIMENTO);
        assertThat(testItemAvaliado.getPontosPessoa()).isEqualTo(DEFAULT_PONTOS_PESSOA);
        assertThat(testItemAvaliado.getPontosProcesso()).isEqualTo(DEFAULT_PONTOS_PROCESSO);
        assertThat(testItemAvaliado.getPontosProduto()).isEqualTo(DEFAULT_PONTOS_PRODUTO);
        assertThat(testItemAvaliado.getPontosObtidosProcedimento()).isEqualTo(DEFAULT_PONTOS_OBTIDOS_PROCEDIMENTO);
        assertThat(testItemAvaliado.getPontosObtidosPessoa()).isEqualTo(DEFAULT_PONTOS_OBTIDOS_PESSOA);
        assertThat(testItemAvaliado.getPontosObtidosProcesso()).isEqualTo(DEFAULT_PONTOS_OBTIDOS_PROCESSO);
        assertThat(testItemAvaliado.getPontosObtidosProduto()).isEqualTo(DEFAULT_PONTOS_OBTIDOS_PRODUTO);
    }

    @Test
    @Transactional
    public void createItemAvaliadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemAvaliadoRepository.findAll().size();

        // Create the ItemAvaliado with an existing ID
        itemAvaliado.setId(1L);
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAvaliado in the database
        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRespondidoEmIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setRespondidoEm(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setStatus(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatitudeLocalRespostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setLatitudeLocalResposta(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudeLocalRespostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setLongitudeLocalResposta(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosProcedimentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setPontosProcedimento(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosPessoaIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setPontosPessoa(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosProcessoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setPontosProcesso(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosProdutoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setPontosProduto(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosObtidosProcedimentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setPontosObtidosProcedimento(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosObtidosPessoaIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setPontosObtidosPessoa(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosObtidosProcessoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setPontosObtidosProcesso(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosObtidosProdutoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliadoRepository.findAll().size();
        // set the field null
        itemAvaliado.setPontosObtidosProduto(null);

        // Create the ItemAvaliado, which fails.
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemAvaliados() throws Exception {
        // Initialize the database
        itemAvaliadoRepository.saveAndFlush(itemAvaliado);

        // Get all the itemAvaliadoList
        restItemAvaliadoMockMvc.perform(get("/api/item-avaliados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemAvaliado.getId().intValue())))
            .andExpect(jsonPath("$.[*].respondidoEm").value(hasItem(DEFAULT_RESPONDIDO_EM.toString())))
            .andExpect(jsonPath("$.[*].ultimaAtualizacaoEm").value(hasItem(DEFAULT_ULTIMA_ATUALIZACAO_EM.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].observacoes").value(hasItem(DEFAULT_OBSERVACOES.toString())))
            .andExpect(jsonPath("$.[*].latitudeLocalResposta").value(hasItem(DEFAULT_LATITUDE_LOCAL_RESPOSTA.doubleValue())))
            .andExpect(jsonPath("$.[*].longitudeLocalResposta").value(hasItem(DEFAULT_LONGITUDE_LOCAL_RESPOSTA.doubleValue())))
            .andExpect(jsonPath("$.[*].pontosProcedimento").value(hasItem(DEFAULT_PONTOS_PROCEDIMENTO)))
            .andExpect(jsonPath("$.[*].pontosPessoa").value(hasItem(DEFAULT_PONTOS_PESSOA)))
            .andExpect(jsonPath("$.[*].pontosProcesso").value(hasItem(DEFAULT_PONTOS_PROCESSO)))
            .andExpect(jsonPath("$.[*].pontosProduto").value(hasItem(DEFAULT_PONTOS_PRODUTO)))
            .andExpect(jsonPath("$.[*].pontosObtidosProcedimento").value(hasItem(DEFAULT_PONTOS_OBTIDOS_PROCEDIMENTO)))
            .andExpect(jsonPath("$.[*].pontosObtidosPessoa").value(hasItem(DEFAULT_PONTOS_OBTIDOS_PESSOA)))
            .andExpect(jsonPath("$.[*].pontosObtidosProcesso").value(hasItem(DEFAULT_PONTOS_OBTIDOS_PROCESSO)))
            .andExpect(jsonPath("$.[*].pontosObtidosProduto").value(hasItem(DEFAULT_PONTOS_OBTIDOS_PRODUTO)));
    }
    
    @Test
    @Transactional
    public void getItemAvaliado() throws Exception {
        // Initialize the database
        itemAvaliadoRepository.saveAndFlush(itemAvaliado);

        // Get the itemAvaliado
        restItemAvaliadoMockMvc.perform(get("/api/item-avaliados/{id}", itemAvaliado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemAvaliado.getId().intValue()))
            .andExpect(jsonPath("$.respondidoEm").value(DEFAULT_RESPONDIDO_EM.toString()))
            .andExpect(jsonPath("$.ultimaAtualizacaoEm").value(DEFAULT_ULTIMA_ATUALIZACAO_EM.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.observacoes").value(DEFAULT_OBSERVACOES.toString()))
            .andExpect(jsonPath("$.latitudeLocalResposta").value(DEFAULT_LATITUDE_LOCAL_RESPOSTA.doubleValue()))
            .andExpect(jsonPath("$.longitudeLocalResposta").value(DEFAULT_LONGITUDE_LOCAL_RESPOSTA.doubleValue()))
            .andExpect(jsonPath("$.pontosProcedimento").value(DEFAULT_PONTOS_PROCEDIMENTO))
            .andExpect(jsonPath("$.pontosPessoa").value(DEFAULT_PONTOS_PESSOA))
            .andExpect(jsonPath("$.pontosProcesso").value(DEFAULT_PONTOS_PROCESSO))
            .andExpect(jsonPath("$.pontosProduto").value(DEFAULT_PONTOS_PRODUTO))
            .andExpect(jsonPath("$.pontosObtidosProcedimento").value(DEFAULT_PONTOS_OBTIDOS_PROCEDIMENTO))
            .andExpect(jsonPath("$.pontosObtidosPessoa").value(DEFAULT_PONTOS_OBTIDOS_PESSOA))
            .andExpect(jsonPath("$.pontosObtidosProcesso").value(DEFAULT_PONTOS_OBTIDOS_PROCESSO))
            .andExpect(jsonPath("$.pontosObtidosProduto").value(DEFAULT_PONTOS_OBTIDOS_PRODUTO));
    }

    @Test
    @Transactional
    public void getNonExistingItemAvaliado() throws Exception {
        // Get the itemAvaliado
        restItemAvaliadoMockMvc.perform(get("/api/item-avaliados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemAvaliado() throws Exception {
        // Initialize the database
        itemAvaliadoRepository.saveAndFlush(itemAvaliado);

        int databaseSizeBeforeUpdate = itemAvaliadoRepository.findAll().size();

        // Update the itemAvaliado
        ItemAvaliado updatedItemAvaliado = itemAvaliadoRepository.findById(itemAvaliado.getId()).get();
        // Disconnect from session so that the updates on updatedItemAvaliado are not directly saved in db
        em.detach(updatedItemAvaliado);
        updatedItemAvaliado
            .respondidoEm(UPDATED_RESPONDIDO_EM)
            .ultimaAtualizacaoEm(UPDATED_ULTIMA_ATUALIZACAO_EM)
            .status(UPDATED_STATUS)
            .observacoes(UPDATED_OBSERVACOES)
            .latitudeLocalResposta(UPDATED_LATITUDE_LOCAL_RESPOSTA)
            .longitudeLocalResposta(UPDATED_LONGITUDE_LOCAL_RESPOSTA)
            .pontosProcedimento(UPDATED_PONTOS_PROCEDIMENTO)
            .pontosPessoa(UPDATED_PONTOS_PESSOA)
            .pontosProcesso(UPDATED_PONTOS_PROCESSO)
            .pontosProduto(UPDATED_PONTOS_PRODUTO)
            .pontosObtidosProcedimento(UPDATED_PONTOS_OBTIDOS_PROCEDIMENTO)
            .pontosObtidosPessoa(UPDATED_PONTOS_OBTIDOS_PESSOA)
            .pontosObtidosProcesso(UPDATED_PONTOS_OBTIDOS_PROCESSO)
            .pontosObtidosProduto(UPDATED_PONTOS_OBTIDOS_PRODUTO);
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(updatedItemAvaliado);

        restItemAvaliadoMockMvc.perform(put("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isOk());

        // Validate the ItemAvaliado in the database
        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeUpdate);
        ItemAvaliado testItemAvaliado = itemAvaliadoList.get(itemAvaliadoList.size() - 1);
        assertThat(testItemAvaliado.getRespondidoEm()).isEqualTo(UPDATED_RESPONDIDO_EM);
        assertThat(testItemAvaliado.getUltimaAtualizacaoEm()).isEqualTo(UPDATED_ULTIMA_ATUALIZACAO_EM);
        assertThat(testItemAvaliado.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemAvaliado.getObservacoes()).isEqualTo(UPDATED_OBSERVACOES);
        assertThat(testItemAvaliado.getLatitudeLocalResposta()).isEqualTo(UPDATED_LATITUDE_LOCAL_RESPOSTA);
        assertThat(testItemAvaliado.getLongitudeLocalResposta()).isEqualTo(UPDATED_LONGITUDE_LOCAL_RESPOSTA);
        assertThat(testItemAvaliado.getPontosProcedimento()).isEqualTo(UPDATED_PONTOS_PROCEDIMENTO);
        assertThat(testItemAvaliado.getPontosPessoa()).isEqualTo(UPDATED_PONTOS_PESSOA);
        assertThat(testItemAvaliado.getPontosProcesso()).isEqualTo(UPDATED_PONTOS_PROCESSO);
        assertThat(testItemAvaliado.getPontosProduto()).isEqualTo(UPDATED_PONTOS_PRODUTO);
        assertThat(testItemAvaliado.getPontosObtidosProcedimento()).isEqualTo(UPDATED_PONTOS_OBTIDOS_PROCEDIMENTO);
        assertThat(testItemAvaliado.getPontosObtidosPessoa()).isEqualTo(UPDATED_PONTOS_OBTIDOS_PESSOA);
        assertThat(testItemAvaliado.getPontosObtidosProcesso()).isEqualTo(UPDATED_PONTOS_OBTIDOS_PROCESSO);
        assertThat(testItemAvaliado.getPontosObtidosProduto()).isEqualTo(UPDATED_PONTOS_OBTIDOS_PRODUTO);
    }

    @Test
    @Transactional
    public void updateNonExistingItemAvaliado() throws Exception {
        int databaseSizeBeforeUpdate = itemAvaliadoRepository.findAll().size();

        // Create the ItemAvaliado
        ItemAvaliadoDTO itemAvaliadoDTO = itemAvaliadoMapper.toDto(itemAvaliado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemAvaliadoMockMvc.perform(put("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliadoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAvaliado in the database
        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemAvaliado() throws Exception {
        // Initialize the database
        itemAvaliadoRepository.saveAndFlush(itemAvaliado);

        int databaseSizeBeforeDelete = itemAvaliadoRepository.findAll().size();

        // Get the itemAvaliado
        restItemAvaliadoMockMvc.perform(delete("/api/item-avaliados/{id}", itemAvaliado.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemAvaliado> itemAvaliadoList = itemAvaliadoRepository.findAll();
        assertThat(itemAvaliadoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAvaliado.class);
        ItemAvaliado itemAvaliado1 = new ItemAvaliado();
        itemAvaliado1.setId(1L);
        ItemAvaliado itemAvaliado2 = new ItemAvaliado();
        itemAvaliado2.setId(itemAvaliado1.getId());
        assertThat(itemAvaliado1).isEqualTo(itemAvaliado2);
        itemAvaliado2.setId(2L);
        assertThat(itemAvaliado1).isNotEqualTo(itemAvaliado2);
        itemAvaliado1.setId(null);
        assertThat(itemAvaliado1).isNotEqualTo(itemAvaliado2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAvaliadoDTO.class);
        ItemAvaliadoDTO itemAvaliadoDTO1 = new ItemAvaliadoDTO();
        itemAvaliadoDTO1.setId(1L);
        ItemAvaliadoDTO itemAvaliadoDTO2 = new ItemAvaliadoDTO();
        assertThat(itemAvaliadoDTO1).isNotEqualTo(itemAvaliadoDTO2);
        itemAvaliadoDTO2.setId(itemAvaliadoDTO1.getId());
        assertThat(itemAvaliadoDTO1).isEqualTo(itemAvaliadoDTO2);
        itemAvaliadoDTO2.setId(2L);
        assertThat(itemAvaliadoDTO1).isNotEqualTo(itemAvaliadoDTO2);
        itemAvaliadoDTO1.setId(null);
        assertThat(itemAvaliadoDTO1).isNotEqualTo(itemAvaliadoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(itemAvaliadoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(itemAvaliadoMapper.fromId(null)).isNull();
    }
}
