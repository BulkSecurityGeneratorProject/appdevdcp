package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao;
import br.com.lasa.dcpdesconformidades.repository.ItemAvaliacaoRepository;
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
 * Test class for the ItemAvaliacaoResource REST controller.
 *
 * @see ItemAvaliacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class ItemAvaliacaoResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ANEXO_OBRIGATORIO = false;
    private static final Boolean UPDATED_ANEXO_OBRIGATORIO = true;

    private static final Instant DEFAULT_CRIADO_EM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CRIADO_EM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ItemAvaliacaoRepository itemAvaliacaoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemAvaliacaoMockMvc;

    private ItemAvaliacao itemAvaliacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemAvaliacaoResource itemAvaliacaoResource = new ItemAvaliacaoResource(itemAvaliacaoRepository);
        this.restItemAvaliacaoMockMvc = MockMvcBuilders.standaloneSetup(itemAvaliacaoResource)
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
    public static ItemAvaliacao createEntity(EntityManager em) {
        ItemAvaliacao itemAvaliacao = new ItemAvaliacao()
            .descricao(DEFAULT_DESCRICAO)
            .anexoObrigatorio(DEFAULT_ANEXO_OBRIGATORIO)
            .criadoEm(DEFAULT_CRIADO_EM);
        return itemAvaliacao;
    }

    @Before
    public void initTest() {
        itemAvaliacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemAvaliacao() throws Exception {
        int databaseSizeBeforeCreate = itemAvaliacaoRepository.findAll().size();

        // Create the ItemAvaliacao
        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacao)))
            .andExpect(status().isCreated());

        // Validate the ItemAvaliacao in the database
        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeCreate + 1);
        ItemAvaliacao testItemAvaliacao = itemAvaliacaoList.get(itemAvaliacaoList.size() - 1);
        assertThat(testItemAvaliacao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testItemAvaliacao.isAnexoObrigatorio()).isEqualTo(DEFAULT_ANEXO_OBRIGATORIO);
        assertThat(testItemAvaliacao.getCriadoEm()).isEqualTo(DEFAULT_CRIADO_EM);
    }

    @Test
    @Transactional
    public void createItemAvaliacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemAvaliacaoRepository.findAll().size();

        // Create the ItemAvaliacao with an existing ID
        itemAvaliacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacao)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAvaliacao in the database
        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliacaoRepository.findAll().size();
        // set the field null
        itemAvaliacao.setDescricao(null);

        // Create the ItemAvaliacao, which fails.

        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacao)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnexoObrigatorioIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliacaoRepository.findAll().size();
        // set the field null
        itemAvaliacao.setAnexoObrigatorio(null);

        // Create the ItemAvaliacao, which fails.

        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacao)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCriadoEmIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliacaoRepository.findAll().size();
        // set the field null
        itemAvaliacao.setCriadoEm(null);

        // Create the ItemAvaliacao, which fails.

        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacao)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemAvaliacaos() throws Exception {
        // Initialize the database
        itemAvaliacaoRepository.saveAndFlush(itemAvaliacao);

        // Get all the itemAvaliacaoList
        restItemAvaliacaoMockMvc.perform(get("/api/item-avaliacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemAvaliacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].anexoObrigatorio").value(hasItem(DEFAULT_ANEXO_OBRIGATORIO.booleanValue())))
            .andExpect(jsonPath("$.[*].criadoEm").value(hasItem(DEFAULT_CRIADO_EM.toString())));
    }
    
    @Test
    @Transactional
    public void getItemAvaliacao() throws Exception {
        // Initialize the database
        itemAvaliacaoRepository.saveAndFlush(itemAvaliacao);

        // Get the itemAvaliacao
        restItemAvaliacaoMockMvc.perform(get("/api/item-avaliacaos/{id}", itemAvaliacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemAvaliacao.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.anexoObrigatorio").value(DEFAULT_ANEXO_OBRIGATORIO.booleanValue()))
            .andExpect(jsonPath("$.criadoEm").value(DEFAULT_CRIADO_EM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemAvaliacao() throws Exception {
        // Get the itemAvaliacao
        restItemAvaliacaoMockMvc.perform(get("/api/item-avaliacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemAvaliacao() throws Exception {
        // Initialize the database
        itemAvaliacaoRepository.saveAndFlush(itemAvaliacao);

        int databaseSizeBeforeUpdate = itemAvaliacaoRepository.findAll().size();

        // Update the itemAvaliacao
        ItemAvaliacao updatedItemAvaliacao = itemAvaliacaoRepository.findById(itemAvaliacao.getId()).get();
        // Disconnect from session so that the updates on updatedItemAvaliacao are not directly saved in db
        em.detach(updatedItemAvaliacao);
        updatedItemAvaliacao
            .descricao(UPDATED_DESCRICAO)
            .anexoObrigatorio(UPDATED_ANEXO_OBRIGATORIO)
            .criadoEm(UPDATED_CRIADO_EM);

        restItemAvaliacaoMockMvc.perform(put("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemAvaliacao)))
            .andExpect(status().isOk());

        // Validate the ItemAvaliacao in the database
        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeUpdate);
        ItemAvaliacao testItemAvaliacao = itemAvaliacaoList.get(itemAvaliacaoList.size() - 1);
        assertThat(testItemAvaliacao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testItemAvaliacao.isAnexoObrigatorio()).isEqualTo(UPDATED_ANEXO_OBRIGATORIO);
        assertThat(testItemAvaliacao.getCriadoEm()).isEqualTo(UPDATED_CRIADO_EM);
    }

    @Test
    @Transactional
    public void updateNonExistingItemAvaliacao() throws Exception {
        int databaseSizeBeforeUpdate = itemAvaliacaoRepository.findAll().size();

        // Create the ItemAvaliacao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemAvaliacaoMockMvc.perform(put("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacao)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAvaliacao in the database
        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemAvaliacao() throws Exception {
        // Initialize the database
        itemAvaliacaoRepository.saveAndFlush(itemAvaliacao);

        int databaseSizeBeforeDelete = itemAvaliacaoRepository.findAll().size();

        // Get the itemAvaliacao
        restItemAvaliacaoMockMvc.perform(delete("/api/item-avaliacaos/{id}", itemAvaliacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAvaliacao.class);
        ItemAvaliacao itemAvaliacao1 = new ItemAvaliacao();
        itemAvaliacao1.setId(1L);
        ItemAvaliacao itemAvaliacao2 = new ItemAvaliacao();
        itemAvaliacao2.setId(itemAvaliacao1.getId());
        assertThat(itemAvaliacao1).isEqualTo(itemAvaliacao2);
        itemAvaliacao2.setId(2L);
        assertThat(itemAvaliacao1).isNotEqualTo(itemAvaliacao2);
        itemAvaliacao1.setId(null);
        assertThat(itemAvaliacao1).isNotEqualTo(itemAvaliacao2);
    }
}
