package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.ItemAuditado;
import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.repository.ItemAuditadoRepository;
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

import br.com.lasa.dcpdesconformidades.domain.enumeration.TipoItemAuditado;
/**
 * Test class for the ItemAuditadoResource REST controller.
 *
 * @see ItemAuditadoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class ItemAuditadoResourceIntTest {

    private static final Instant DEFAULT_RESPONDIDO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RESPONDIDO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ULTIMA_ATUALIZACAO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ULTIMA_ATUALIZACAO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final TipoItemAuditado DEFAULT_TIPO = TipoItemAuditado.TOP_5_PERDAS;
    private static final TipoItemAuditado UPDATED_TIPO = TipoItemAuditado.ALTO_RISCO;

    private static final Integer DEFAULT_DEPARTAMENTO = 1;
    private static final Integer UPDATED_DEPARTAMENTO = 2;

    private static final Integer DEFAULT_CODIGO_SAP = 1;
    private static final Integer UPDATED_CODIGO_SAP = 2;

    private static final String DEFAULT_DESCRICAO_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO_ITEM = "BBBBBBBBBB";

    private static final Integer DEFAULT_SALDO_SAP = 1;
    private static final Integer UPDATED_SALDO_SAP = 2;

    private static final Integer DEFAULT_SALDO_FISICO = 1;
    private static final Integer UPDATED_SALDO_FISICO = 2;

    private static final String DEFAULT_MOTIVO_DIVERGENCIA = "AAAAAAAAAA";
    private static final String UPDATED_MOTIVO_DIVERGENCIA = "BBBBBBBBBB";

    @Autowired
    private ItemAuditadoRepository itemAuditadoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemAuditadoMockMvc;

    private ItemAuditado itemAuditado;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemAuditadoResource itemAuditadoResource = new ItemAuditadoResource(itemAuditadoRepository);
        this.restItemAuditadoMockMvc = MockMvcBuilders.standaloneSetup(itemAuditadoResource)
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
    public static ItemAuditado createEntity(EntityManager em) {
        ItemAuditado itemAuditado = new ItemAuditado()
            .respondidoEm(DEFAULT_RESPONDIDO_EM)
            .ultimaAtualizacaoEm(DEFAULT_ULTIMA_ATUALIZACAO_EM)
            .tipo(DEFAULT_TIPO)
            .departamento(DEFAULT_DEPARTAMENTO)
            .codigoSap(DEFAULT_CODIGO_SAP)
            .descricaoItem(DEFAULT_DESCRICAO_ITEM)
            .saldoSap(DEFAULT_SALDO_SAP)
            .saldoFisico(DEFAULT_SALDO_FISICO)
            .motivoDivergencia(DEFAULT_MOTIVO_DIVERGENCIA);
        // Add required entity
        Avaliacao avaliacao = AvaliacaoResourceIntTest.createEntity(em);
        em.persist(avaliacao);
        em.flush();
        itemAuditado.setAvaliacao(avaliacao);
        return itemAuditado;
    }

    @Before
    public void initTest() {
        itemAuditado = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemAuditado() throws Exception {
        int databaseSizeBeforeCreate = itemAuditadoRepository.findAll().size();

        // Create the ItemAuditado
        restItemAuditadoMockMvc.perform(post("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAuditado)))
            .andExpect(status().isCreated());

        // Validate the ItemAuditado in the database
        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeCreate + 1);
        ItemAuditado testItemAuditado = itemAuditadoList.get(itemAuditadoList.size() - 1);
        assertThat(testItemAuditado.getRespondidoEm()).isEqualTo(DEFAULT_RESPONDIDO_EM);
        assertThat(testItemAuditado.getUltimaAtualizacaoEm()).isEqualTo(DEFAULT_ULTIMA_ATUALIZACAO_EM);
        assertThat(testItemAuditado.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testItemAuditado.getDepartamento()).isEqualTo(DEFAULT_DEPARTAMENTO);
        assertThat(testItemAuditado.getCodigoSap()).isEqualTo(DEFAULT_CODIGO_SAP);
        assertThat(testItemAuditado.getDescricaoItem()).isEqualTo(DEFAULT_DESCRICAO_ITEM);
        assertThat(testItemAuditado.getSaldoSap()).isEqualTo(DEFAULT_SALDO_SAP);
        assertThat(testItemAuditado.getSaldoFisico()).isEqualTo(DEFAULT_SALDO_FISICO);
        assertThat(testItemAuditado.getMotivoDivergencia()).isEqualTo(DEFAULT_MOTIVO_DIVERGENCIA);
    }

    @Test
    @Transactional
    public void createItemAuditadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemAuditadoRepository.findAll().size();

        // Create the ItemAuditado with an existing ID
        itemAuditado.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAuditadoMockMvc.perform(post("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAuditado)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAuditado in the database
        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRespondidoEmIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAuditadoRepository.findAll().size();
        // set the field null
        itemAuditado.setRespondidoEm(null);

        // Create the ItemAuditado, which fails.

        restItemAuditadoMockMvc.perform(post("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAuditado)))
            .andExpect(status().isBadRequest());

        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAuditadoRepository.findAll().size();
        // set the field null
        itemAuditado.setTipo(null);

        // Create the ItemAuditado, which fails.

        restItemAuditadoMockMvc.perform(post("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAuditado)))
            .andExpect(status().isBadRequest());

        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDepartamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAuditadoRepository.findAll().size();
        // set the field null
        itemAuditado.setDepartamento(null);

        // Create the ItemAuditado, which fails.

        restItemAuditadoMockMvc.perform(post("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAuditado)))
            .andExpect(status().isBadRequest());

        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoSapIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAuditadoRepository.findAll().size();
        // set the field null
        itemAuditado.setCodigoSap(null);

        // Create the ItemAuditado, which fails.

        restItemAuditadoMockMvc.perform(post("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAuditado)))
            .andExpect(status().isBadRequest());

        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescricaoItemIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAuditadoRepository.findAll().size();
        // set the field null
        itemAuditado.setDescricaoItem(null);

        // Create the ItemAuditado, which fails.

        restItemAuditadoMockMvc.perform(post("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAuditado)))
            .andExpect(status().isBadRequest());

        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSaldoSapIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAuditadoRepository.findAll().size();
        // set the field null
        itemAuditado.setSaldoSap(null);

        // Create the ItemAuditado, which fails.

        restItemAuditadoMockMvc.perform(post("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAuditado)))
            .andExpect(status().isBadRequest());

        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSaldoFisicoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAuditadoRepository.findAll().size();
        // set the field null
        itemAuditado.setSaldoFisico(null);

        // Create the ItemAuditado, which fails.

        restItemAuditadoMockMvc.perform(post("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAuditado)))
            .andExpect(status().isBadRequest());

        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemAuditados() throws Exception {
        // Initialize the database
        itemAuditadoRepository.saveAndFlush(itemAuditado);

        // Get all the itemAuditadoList
        restItemAuditadoMockMvc.perform(get("/api/item-auditados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemAuditado.getId().intValue())))
            .andExpect(jsonPath("$.[*].respondidoEm").value(hasItem(DEFAULT_RESPONDIDO_EM.toString())))
            .andExpect(jsonPath("$.[*].ultimaAtualizacaoEm").value(hasItem(DEFAULT_ULTIMA_ATUALIZACAO_EM.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].departamento").value(hasItem(DEFAULT_DEPARTAMENTO)))
            .andExpect(jsonPath("$.[*].codigoSap").value(hasItem(DEFAULT_CODIGO_SAP)))
            .andExpect(jsonPath("$.[*].descricaoItem").value(hasItem(DEFAULT_DESCRICAO_ITEM.toString())))
            .andExpect(jsonPath("$.[*].saldoSap").value(hasItem(DEFAULT_SALDO_SAP)))
            .andExpect(jsonPath("$.[*].saldoFisico").value(hasItem(DEFAULT_SALDO_FISICO)))
            .andExpect(jsonPath("$.[*].motivoDivergencia").value(hasItem(DEFAULT_MOTIVO_DIVERGENCIA.toString())));
    }
    
    @Test
    @Transactional
    public void getItemAuditado() throws Exception {
        // Initialize the database
        itemAuditadoRepository.saveAndFlush(itemAuditado);

        // Get the itemAuditado
        restItemAuditadoMockMvc.perform(get("/api/item-auditados/{id}", itemAuditado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemAuditado.getId().intValue()))
            .andExpect(jsonPath("$.respondidoEm").value(DEFAULT_RESPONDIDO_EM.toString()))
            .andExpect(jsonPath("$.ultimaAtualizacaoEm").value(DEFAULT_ULTIMA_ATUALIZACAO_EM.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.departamento").value(DEFAULT_DEPARTAMENTO))
            .andExpect(jsonPath("$.codigoSap").value(DEFAULT_CODIGO_SAP))
            .andExpect(jsonPath("$.descricaoItem").value(DEFAULT_DESCRICAO_ITEM.toString()))
            .andExpect(jsonPath("$.saldoSap").value(DEFAULT_SALDO_SAP))
            .andExpect(jsonPath("$.saldoFisico").value(DEFAULT_SALDO_FISICO))
            .andExpect(jsonPath("$.motivoDivergencia").value(DEFAULT_MOTIVO_DIVERGENCIA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemAuditado() throws Exception {
        // Get the itemAuditado
        restItemAuditadoMockMvc.perform(get("/api/item-auditados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemAuditado() throws Exception {
        // Initialize the database
        itemAuditadoRepository.saveAndFlush(itemAuditado);

        int databaseSizeBeforeUpdate = itemAuditadoRepository.findAll().size();

        // Update the itemAuditado
        ItemAuditado updatedItemAuditado = itemAuditadoRepository.findById(itemAuditado.getId()).get();
        // Disconnect from session so that the updates on updatedItemAuditado are not directly saved in db
        em.detach(updatedItemAuditado);
        updatedItemAuditado
            .respondidoEm(UPDATED_RESPONDIDO_EM)
            .ultimaAtualizacaoEm(UPDATED_ULTIMA_ATUALIZACAO_EM)
            .tipo(UPDATED_TIPO)
            .departamento(UPDATED_DEPARTAMENTO)
            .codigoSap(UPDATED_CODIGO_SAP)
            .descricaoItem(UPDATED_DESCRICAO_ITEM)
            .saldoSap(UPDATED_SALDO_SAP)
            .saldoFisico(UPDATED_SALDO_FISICO)
            .motivoDivergencia(UPDATED_MOTIVO_DIVERGENCIA);

        restItemAuditadoMockMvc.perform(put("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemAuditado)))
            .andExpect(status().isOk());

        // Validate the ItemAuditado in the database
        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeUpdate);
        ItemAuditado testItemAuditado = itemAuditadoList.get(itemAuditadoList.size() - 1);
        assertThat(testItemAuditado.getRespondidoEm()).isEqualTo(UPDATED_RESPONDIDO_EM);
        assertThat(testItemAuditado.getUltimaAtualizacaoEm()).isEqualTo(UPDATED_ULTIMA_ATUALIZACAO_EM);
        assertThat(testItemAuditado.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testItemAuditado.getDepartamento()).isEqualTo(UPDATED_DEPARTAMENTO);
        assertThat(testItemAuditado.getCodigoSap()).isEqualTo(UPDATED_CODIGO_SAP);
        assertThat(testItemAuditado.getDescricaoItem()).isEqualTo(UPDATED_DESCRICAO_ITEM);
        assertThat(testItemAuditado.getSaldoSap()).isEqualTo(UPDATED_SALDO_SAP);
        assertThat(testItemAuditado.getSaldoFisico()).isEqualTo(UPDATED_SALDO_FISICO);
        assertThat(testItemAuditado.getMotivoDivergencia()).isEqualTo(UPDATED_MOTIVO_DIVERGENCIA);
    }

    @Test
    @Transactional
    public void updateNonExistingItemAuditado() throws Exception {
        int databaseSizeBeforeUpdate = itemAuditadoRepository.findAll().size();

        // Create the ItemAuditado

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemAuditadoMockMvc.perform(put("/api/item-auditados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAuditado)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAuditado in the database
        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemAuditado() throws Exception {
        // Initialize the database
        itemAuditadoRepository.saveAndFlush(itemAuditado);

        int databaseSizeBeforeDelete = itemAuditadoRepository.findAll().size();

        // Get the itemAuditado
        restItemAuditadoMockMvc.perform(delete("/api/item-auditados/{id}", itemAuditado.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemAuditado> itemAuditadoList = itemAuditadoRepository.findAll();
        assertThat(itemAuditadoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAuditado.class);
        ItemAuditado itemAuditado1 = new ItemAuditado();
        itemAuditado1.setId(1L);
        ItemAuditado itemAuditado2 = new ItemAuditado();
        itemAuditado2.setId(itemAuditado1.getId());
        assertThat(itemAuditado1).isEqualTo(itemAuditado2);
        itemAuditado2.setId(2L);
        assertThat(itemAuditado1).isNotEqualTo(itemAuditado2);
        itemAuditado1.setId(null);
        assertThat(itemAuditado1).isNotEqualTo(itemAuditado2);
    }
}
