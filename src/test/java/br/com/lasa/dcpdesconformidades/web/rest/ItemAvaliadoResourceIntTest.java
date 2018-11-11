package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.ItemAvaliado;
import br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao;
import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.repository.ItemAvaliadoRepository;
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

    @Autowired
    private ItemAvaliadoRepository itemAvaliadoRepository;

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
        final ItemAvaliadoResource itemAvaliadoResource = new ItemAvaliadoResource(itemAvaliadoRepository);
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
            .longitudeLocalResposta(DEFAULT_LONGITUDE_LOCAL_RESPOSTA);
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
        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliado)))
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
    }

    @Test
    @Transactional
    public void createItemAvaliadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemAvaliadoRepository.findAll().size();

        // Create the ItemAvaliado with an existing ID
        itemAvaliado.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliado)))
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

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliado)))
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

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliado)))
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

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliado)))
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

        restItemAvaliadoMockMvc.perform(post("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliado)))
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
            .andExpect(jsonPath("$.[*].longitudeLocalResposta").value(hasItem(DEFAULT_LONGITUDE_LOCAL_RESPOSTA.doubleValue())));
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
            .andExpect(jsonPath("$.longitudeLocalResposta").value(DEFAULT_LONGITUDE_LOCAL_RESPOSTA.doubleValue()));
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
            .longitudeLocalResposta(UPDATED_LONGITUDE_LOCAL_RESPOSTA);

        restItemAvaliadoMockMvc.perform(put("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemAvaliado)))
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
    }

    @Test
    @Transactional
    public void updateNonExistingItemAvaliado() throws Exception {
        int databaseSizeBeforeUpdate = itemAvaliadoRepository.findAll().size();

        // Create the ItemAvaliado

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemAvaliadoMockMvc.perform(put("/api/item-avaliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliado)))
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
}
