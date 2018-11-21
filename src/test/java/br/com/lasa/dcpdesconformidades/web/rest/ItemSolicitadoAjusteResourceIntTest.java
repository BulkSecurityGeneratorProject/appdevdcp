package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.ItemSolicitadoAjuste;
import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.repository.ItemSolicitadoAjusteRepository;
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

/**
 * Test class for the ItemSolicitadoAjusteResource REST controller.
 *
 * @see ItemSolicitadoAjusteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class ItemSolicitadoAjusteResourceIntTest {

    private static final Instant DEFAULT_RESPONDIDO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RESPONDIDO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ULTIMA_ATUALIZACAO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ULTIMA_ATUALIZACAO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_CODIGO_DEPARTAMENTO = 1;
    private static final Integer UPDATED_CODIGO_DEPARTAMENTO = 2;

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

    private static final String DEFAULT_ACAO_CORRETIVA_OU_PREVENTIVA = "AAAAAAAAAA";
    private static final String UPDATED_ACAO_CORRETIVA_OU_PREVENTIVA = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSAVEL = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSAVEL = "BBBBBBBBBB";

    @Autowired
    private ItemSolicitadoAjusteRepository itemSolicitadoAjusteRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemSolicitadoAjusteMockMvc;

    private ItemSolicitadoAjuste itemSolicitadoAjuste;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemSolicitadoAjusteResource itemSolicitadoAjusteResource = new ItemSolicitadoAjusteResource(itemSolicitadoAjusteRepository);
        this.restItemSolicitadoAjusteMockMvc = MockMvcBuilders.standaloneSetup(itemSolicitadoAjusteResource)
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
    public static ItemSolicitadoAjuste createEntity(EntityManager em) {
        ItemSolicitadoAjuste itemSolicitadoAjuste = new ItemSolicitadoAjuste()
            .respondidoEm(DEFAULT_RESPONDIDO_EM)
            .ultimaAtualizacaoEm(DEFAULT_ULTIMA_ATUALIZACAO_EM)
            .codigoDepartamento(DEFAULT_CODIGO_DEPARTAMENTO)
            .codigoSap(DEFAULT_CODIGO_SAP)
            .descricaoItem(DEFAULT_DESCRICAO_ITEM)
            .saldoSap(DEFAULT_SALDO_SAP)
            .saldoFisico(DEFAULT_SALDO_FISICO)
            .motivoDivergencia(DEFAULT_MOTIVO_DIVERGENCIA)
            .acaoCorretivaOuPreventiva(DEFAULT_ACAO_CORRETIVA_OU_PREVENTIVA)
            .responsavel(DEFAULT_RESPONSAVEL);
        // Add required entity
        Avaliacao avaliacao = AvaliacaoResourceIntTest.createEntity(em);
        em.persist(avaliacao);
        em.flush();
        itemSolicitadoAjuste.setAvaliacao(avaliacao);
        return itemSolicitadoAjuste;
    }

    @Before
    public void initTest() {
        itemSolicitadoAjuste = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemSolicitadoAjuste() throws Exception {
        int databaseSizeBeforeCreate = itemSolicitadoAjusteRepository.findAll().size();

        // Create the ItemSolicitadoAjuste
        restItemSolicitadoAjusteMockMvc.perform(post("/api/item-solicitado-ajustes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemSolicitadoAjuste)))
            .andExpect(status().isCreated());

        // Validate the ItemSolicitadoAjuste in the database
        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeCreate + 1);
        ItemSolicitadoAjuste testItemSolicitadoAjuste = itemSolicitadoAjusteList.get(itemSolicitadoAjusteList.size() - 1);
        assertThat(testItemSolicitadoAjuste.getRespondidoEm()).isEqualTo(DEFAULT_RESPONDIDO_EM);
        assertThat(testItemSolicitadoAjuste.getUltimaAtualizacaoEm()).isEqualTo(DEFAULT_ULTIMA_ATUALIZACAO_EM);
        assertThat(testItemSolicitadoAjuste.getCodigoDepartamento()).isEqualTo(DEFAULT_CODIGO_DEPARTAMENTO);
        assertThat(testItemSolicitadoAjuste.getCodigoSap()).isEqualTo(DEFAULT_CODIGO_SAP);
        assertThat(testItemSolicitadoAjuste.getDescricaoItem()).isEqualTo(DEFAULT_DESCRICAO_ITEM);
        assertThat(testItemSolicitadoAjuste.getSaldoSap()).isEqualTo(DEFAULT_SALDO_SAP);
        assertThat(testItemSolicitadoAjuste.getSaldoFisico()).isEqualTo(DEFAULT_SALDO_FISICO);
        assertThat(testItemSolicitadoAjuste.getMotivoDivergencia()).isEqualTo(DEFAULT_MOTIVO_DIVERGENCIA);
        assertThat(testItemSolicitadoAjuste.getAcaoCorretivaOuPreventiva()).isEqualTo(DEFAULT_ACAO_CORRETIVA_OU_PREVENTIVA);
        assertThat(testItemSolicitadoAjuste.getResponsavel()).isEqualTo(DEFAULT_RESPONSAVEL);
    }

    @Test
    @Transactional
    public void createItemSolicitadoAjusteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemSolicitadoAjusteRepository.findAll().size();

        // Create the ItemSolicitadoAjuste with an existing ID
        itemSolicitadoAjuste.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemSolicitadoAjusteMockMvc.perform(post("/api/item-solicitado-ajustes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemSolicitadoAjuste)))
            .andExpect(status().isBadRequest());

        // Validate the ItemSolicitadoAjuste in the database
        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRespondidoEmIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemSolicitadoAjusteRepository.findAll().size();
        // set the field null
        itemSolicitadoAjuste.setRespondidoEm(null);

        // Create the ItemSolicitadoAjuste, which fails.

        restItemSolicitadoAjusteMockMvc.perform(post("/api/item-solicitado-ajustes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemSolicitadoAjuste)))
            .andExpect(status().isBadRequest());

        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoDepartamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemSolicitadoAjusteRepository.findAll().size();
        // set the field null
        itemSolicitadoAjuste.setCodigoDepartamento(null);

        // Create the ItemSolicitadoAjuste, which fails.

        restItemSolicitadoAjusteMockMvc.perform(post("/api/item-solicitado-ajustes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemSolicitadoAjuste)))
            .andExpect(status().isBadRequest());

        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoSapIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemSolicitadoAjusteRepository.findAll().size();
        // set the field null
        itemSolicitadoAjuste.setCodigoSap(null);

        // Create the ItemSolicitadoAjuste, which fails.

        restItemSolicitadoAjusteMockMvc.perform(post("/api/item-solicitado-ajustes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemSolicitadoAjuste)))
            .andExpect(status().isBadRequest());

        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescricaoItemIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemSolicitadoAjusteRepository.findAll().size();
        // set the field null
        itemSolicitadoAjuste.setDescricaoItem(null);

        // Create the ItemSolicitadoAjuste, which fails.

        restItemSolicitadoAjusteMockMvc.perform(post("/api/item-solicitado-ajustes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemSolicitadoAjuste)))
            .andExpect(status().isBadRequest());

        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSaldoSapIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemSolicitadoAjusteRepository.findAll().size();
        // set the field null
        itemSolicitadoAjuste.setSaldoSap(null);

        // Create the ItemSolicitadoAjuste, which fails.

        restItemSolicitadoAjusteMockMvc.perform(post("/api/item-solicitado-ajustes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemSolicitadoAjuste)))
            .andExpect(status().isBadRequest());

        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSaldoFisicoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemSolicitadoAjusteRepository.findAll().size();
        // set the field null
        itemSolicitadoAjuste.setSaldoFisico(null);

        // Create the ItemSolicitadoAjuste, which fails.

        restItemSolicitadoAjusteMockMvc.perform(post("/api/item-solicitado-ajustes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemSolicitadoAjuste)))
            .andExpect(status().isBadRequest());

        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemSolicitadoAjustes() throws Exception {
        // Initialize the database
        itemSolicitadoAjusteRepository.saveAndFlush(itemSolicitadoAjuste);

        // Get all the itemSolicitadoAjusteList
        restItemSolicitadoAjusteMockMvc.perform(get("/api/item-solicitado-ajustes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemSolicitadoAjuste.getId().intValue())))
            .andExpect(jsonPath("$.[*].respondidoEm").value(hasItem(DEFAULT_RESPONDIDO_EM.toString())))
            .andExpect(jsonPath("$.[*].ultimaAtualizacaoEm").value(hasItem(DEFAULT_ULTIMA_ATUALIZACAO_EM.toString())))
            .andExpect(jsonPath("$.[*].codigoDepartamento").value(hasItem(DEFAULT_CODIGO_DEPARTAMENTO)))
            .andExpect(jsonPath("$.[*].codigoSap").value(hasItem(DEFAULT_CODIGO_SAP)))
            .andExpect(jsonPath("$.[*].descricaoItem").value(hasItem(DEFAULT_DESCRICAO_ITEM.toString())))
            .andExpect(jsonPath("$.[*].saldoSap").value(hasItem(DEFAULT_SALDO_SAP)))
            .andExpect(jsonPath("$.[*].saldoFisico").value(hasItem(DEFAULT_SALDO_FISICO)))
            .andExpect(jsonPath("$.[*].motivoDivergencia").value(hasItem(DEFAULT_MOTIVO_DIVERGENCIA.toString())))
            .andExpect(jsonPath("$.[*].acaoCorretivaOuPreventiva").value(hasItem(DEFAULT_ACAO_CORRETIVA_OU_PREVENTIVA.toString())))
            .andExpect(jsonPath("$.[*].responsavel").value(hasItem(DEFAULT_RESPONSAVEL.toString())));
    }
    
    @Test
    @Transactional
    public void getItemSolicitadoAjuste() throws Exception {
        // Initialize the database
        itemSolicitadoAjusteRepository.saveAndFlush(itemSolicitadoAjuste);

        // Get the itemSolicitadoAjuste
        restItemSolicitadoAjusteMockMvc.perform(get("/api/item-solicitado-ajustes/{id}", itemSolicitadoAjuste.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemSolicitadoAjuste.getId().intValue()))
            .andExpect(jsonPath("$.respondidoEm").value(DEFAULT_RESPONDIDO_EM.toString()))
            .andExpect(jsonPath("$.ultimaAtualizacaoEm").value(DEFAULT_ULTIMA_ATUALIZACAO_EM.toString()))
            .andExpect(jsonPath("$.codigoDepartamento").value(DEFAULT_CODIGO_DEPARTAMENTO))
            .andExpect(jsonPath("$.codigoSap").value(DEFAULT_CODIGO_SAP))
            .andExpect(jsonPath("$.descricaoItem").value(DEFAULT_DESCRICAO_ITEM.toString()))
            .andExpect(jsonPath("$.saldoSap").value(DEFAULT_SALDO_SAP))
            .andExpect(jsonPath("$.saldoFisico").value(DEFAULT_SALDO_FISICO))
            .andExpect(jsonPath("$.motivoDivergencia").value(DEFAULT_MOTIVO_DIVERGENCIA.toString()))
            .andExpect(jsonPath("$.acaoCorretivaOuPreventiva").value(DEFAULT_ACAO_CORRETIVA_OU_PREVENTIVA.toString()))
            .andExpect(jsonPath("$.responsavel").value(DEFAULT_RESPONSAVEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemSolicitadoAjuste() throws Exception {
        // Get the itemSolicitadoAjuste
        restItemSolicitadoAjusteMockMvc.perform(get("/api/item-solicitado-ajustes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemSolicitadoAjuste() throws Exception {
        // Initialize the database
        itemSolicitadoAjusteRepository.saveAndFlush(itemSolicitadoAjuste);

        int databaseSizeBeforeUpdate = itemSolicitadoAjusteRepository.findAll().size();

        // Update the itemSolicitadoAjuste
        ItemSolicitadoAjuste updatedItemSolicitadoAjuste = itemSolicitadoAjusteRepository.findById(itemSolicitadoAjuste.getId()).get();
        // Disconnect from session so that the updates on updatedItemSolicitadoAjuste are not directly saved in db
        em.detach(updatedItemSolicitadoAjuste);
        updatedItemSolicitadoAjuste
            .respondidoEm(UPDATED_RESPONDIDO_EM)
            .ultimaAtualizacaoEm(UPDATED_ULTIMA_ATUALIZACAO_EM)
            .codigoDepartamento(UPDATED_CODIGO_DEPARTAMENTO)
            .codigoSap(UPDATED_CODIGO_SAP)
            .descricaoItem(UPDATED_DESCRICAO_ITEM)
            .saldoSap(UPDATED_SALDO_SAP)
            .saldoFisico(UPDATED_SALDO_FISICO)
            .motivoDivergencia(UPDATED_MOTIVO_DIVERGENCIA)
            .acaoCorretivaOuPreventiva(UPDATED_ACAO_CORRETIVA_OU_PREVENTIVA)
            .responsavel(UPDATED_RESPONSAVEL);

        restItemSolicitadoAjusteMockMvc.perform(put("/api/item-solicitado-ajustes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemSolicitadoAjuste)))
            .andExpect(status().isOk());

        // Validate the ItemSolicitadoAjuste in the database
        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeUpdate);
        ItemSolicitadoAjuste testItemSolicitadoAjuste = itemSolicitadoAjusteList.get(itemSolicitadoAjusteList.size() - 1);
        assertThat(testItemSolicitadoAjuste.getRespondidoEm()).isEqualTo(UPDATED_RESPONDIDO_EM);
        assertThat(testItemSolicitadoAjuste.getUltimaAtualizacaoEm()).isEqualTo(UPDATED_ULTIMA_ATUALIZACAO_EM);
        assertThat(testItemSolicitadoAjuste.getCodigoDepartamento()).isEqualTo(UPDATED_CODIGO_DEPARTAMENTO);
        assertThat(testItemSolicitadoAjuste.getCodigoSap()).isEqualTo(UPDATED_CODIGO_SAP);
        assertThat(testItemSolicitadoAjuste.getDescricaoItem()).isEqualTo(UPDATED_DESCRICAO_ITEM);
        assertThat(testItemSolicitadoAjuste.getSaldoSap()).isEqualTo(UPDATED_SALDO_SAP);
        assertThat(testItemSolicitadoAjuste.getSaldoFisico()).isEqualTo(UPDATED_SALDO_FISICO);
        assertThat(testItemSolicitadoAjuste.getMotivoDivergencia()).isEqualTo(UPDATED_MOTIVO_DIVERGENCIA);
        assertThat(testItemSolicitadoAjuste.getAcaoCorretivaOuPreventiva()).isEqualTo(UPDATED_ACAO_CORRETIVA_OU_PREVENTIVA);
        assertThat(testItemSolicitadoAjuste.getResponsavel()).isEqualTo(UPDATED_RESPONSAVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingItemSolicitadoAjuste() throws Exception {
        int databaseSizeBeforeUpdate = itemSolicitadoAjusteRepository.findAll().size();

        // Create the ItemSolicitadoAjuste

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemSolicitadoAjusteMockMvc.perform(put("/api/item-solicitado-ajustes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemSolicitadoAjuste)))
            .andExpect(status().isBadRequest());

        // Validate the ItemSolicitadoAjuste in the database
        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemSolicitadoAjuste() throws Exception {
        // Initialize the database
        itemSolicitadoAjusteRepository.saveAndFlush(itemSolicitadoAjuste);

        int databaseSizeBeforeDelete = itemSolicitadoAjusteRepository.findAll().size();

        // Get the itemSolicitadoAjuste
        restItemSolicitadoAjusteMockMvc.perform(delete("/api/item-solicitado-ajustes/{id}", itemSolicitadoAjuste.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemSolicitadoAjuste> itemSolicitadoAjusteList = itemSolicitadoAjusteRepository.findAll();
        assertThat(itemSolicitadoAjusteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemSolicitadoAjuste.class);
        ItemSolicitadoAjuste itemSolicitadoAjuste1 = new ItemSolicitadoAjuste();
        itemSolicitadoAjuste1.setId(1L);
        ItemSolicitadoAjuste itemSolicitadoAjuste2 = new ItemSolicitadoAjuste();
        itemSolicitadoAjuste2.setId(itemSolicitadoAjuste1.getId());
        assertThat(itemSolicitadoAjuste1).isEqualTo(itemSolicitadoAjuste2);
        itemSolicitadoAjuste2.setId(2L);
        assertThat(itemSolicitadoAjuste1).isNotEqualTo(itemSolicitadoAjuste2);
        itemSolicitadoAjuste1.setId(null);
        assertThat(itemSolicitadoAjuste1).isNotEqualTo(itemSolicitadoAjuste2);
    }
}
