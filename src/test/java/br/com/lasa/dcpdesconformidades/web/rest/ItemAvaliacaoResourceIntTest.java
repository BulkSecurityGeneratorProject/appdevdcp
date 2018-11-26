package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao;
import br.com.lasa.dcpdesconformidades.repository.ItemAvaliacaoRepository;
import br.com.lasa.dcpdesconformidades.service.ItemAvaliacaoService;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAvaliacaoDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.ItemAvaliacaoMapper;
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

    private static final Integer DEFAULT_PONTOS_PROCEDIMENTO = 1;
    private static final Integer UPDATED_PONTOS_PROCEDIMENTO = 2;

    private static final Integer DEFAULT_PONTOS_PESSOA = 1;
    private static final Integer UPDATED_PONTOS_PESSOA = 2;

    private static final Integer DEFAULT_PONTOS_PROCESSO = 1;
    private static final Integer UPDATED_PONTOS_PROCESSO = 2;

    private static final Integer DEFAULT_PONTOS_PRODUTO = 1;
    private static final Integer UPDATED_PONTOS_PRODUTO = 2;

    @Autowired
    private ItemAvaliacaoRepository itemAvaliacaoRepository;

    @Autowired
    private ItemAvaliacaoMapper itemAvaliacaoMapper;

    @Autowired
    private ItemAvaliacaoService itemAvaliacaoService;

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
        final ItemAvaliacaoResource itemAvaliacaoResource = new ItemAvaliacaoResource(itemAvaliacaoService);
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
            .pontosProcedimento(DEFAULT_PONTOS_PROCEDIMENTO)
            .pontosPessoa(DEFAULT_PONTOS_PESSOA)
            .pontosProcesso(DEFAULT_PONTOS_PROCESSO)
            .pontosProduto(DEFAULT_PONTOS_PRODUTO);
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
        ItemAvaliacaoDTO itemAvaliacaoDTO = itemAvaliacaoMapper.toDto(itemAvaliacao);
        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemAvaliacao in the database
        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeCreate + 1);
        ItemAvaliacao testItemAvaliacao = itemAvaliacaoList.get(itemAvaliacaoList.size() - 1);
        assertThat(testItemAvaliacao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testItemAvaliacao.isAnexoObrigatorio()).isEqualTo(DEFAULT_ANEXO_OBRIGATORIO);
        assertThat(testItemAvaliacao.getPontosProcedimento()).isEqualTo(DEFAULT_PONTOS_PROCEDIMENTO);
        assertThat(testItemAvaliacao.getPontosPessoa()).isEqualTo(DEFAULT_PONTOS_PESSOA);
        assertThat(testItemAvaliacao.getPontosProcesso()).isEqualTo(DEFAULT_PONTOS_PROCESSO);
        assertThat(testItemAvaliacao.getPontosProduto()).isEqualTo(DEFAULT_PONTOS_PRODUTO);
    }

    @Test
    @Transactional
    public void createItemAvaliacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemAvaliacaoRepository.findAll().size();

        // Create the ItemAvaliacao with an existing ID
        itemAvaliacao.setId(1L);
        ItemAvaliacaoDTO itemAvaliacaoDTO = itemAvaliacaoMapper.toDto(itemAvaliacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoDTO)))
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
        ItemAvaliacaoDTO itemAvaliacaoDTO = itemAvaliacaoMapper.toDto(itemAvaliacao);

        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoDTO)))
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
        ItemAvaliacaoDTO itemAvaliacaoDTO = itemAvaliacaoMapper.toDto(itemAvaliacao);

        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosProcedimentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliacaoRepository.findAll().size();
        // set the field null
        itemAvaliacao.setPontosProcedimento(null);

        // Create the ItemAvaliacao, which fails.
        ItemAvaliacaoDTO itemAvaliacaoDTO = itemAvaliacaoMapper.toDto(itemAvaliacao);

        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosPessoaIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliacaoRepository.findAll().size();
        // set the field null
        itemAvaliacao.setPontosPessoa(null);

        // Create the ItemAvaliacao, which fails.
        ItemAvaliacaoDTO itemAvaliacaoDTO = itemAvaliacaoMapper.toDto(itemAvaliacao);

        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosProcessoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliacaoRepository.findAll().size();
        // set the field null
        itemAvaliacao.setPontosProcesso(null);

        // Create the ItemAvaliacao, which fails.
        ItemAvaliacaoDTO itemAvaliacaoDTO = itemAvaliacaoMapper.toDto(itemAvaliacao);

        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontosProdutoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliacaoRepository.findAll().size();
        // set the field null
        itemAvaliacao.setPontosProduto(null);

        // Create the ItemAvaliacao, which fails.
        ItemAvaliacaoDTO itemAvaliacaoDTO = itemAvaliacaoMapper.toDto(itemAvaliacao);

        restItemAvaliacaoMockMvc.perform(post("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoDTO)))
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
            .andExpect(jsonPath("$.[*].pontosProcedimento").value(hasItem(DEFAULT_PONTOS_PROCEDIMENTO)))
            .andExpect(jsonPath("$.[*].pontosPessoa").value(hasItem(DEFAULT_PONTOS_PESSOA)))
            .andExpect(jsonPath("$.[*].pontosProcesso").value(hasItem(DEFAULT_PONTOS_PROCESSO)))
            .andExpect(jsonPath("$.[*].pontosProduto").value(hasItem(DEFAULT_PONTOS_PRODUTO)));
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
            .andExpect(jsonPath("$.pontosProcedimento").value(DEFAULT_PONTOS_PROCEDIMENTO))
            .andExpect(jsonPath("$.pontosPessoa").value(DEFAULT_PONTOS_PESSOA))
            .andExpect(jsonPath("$.pontosProcesso").value(DEFAULT_PONTOS_PROCESSO))
            .andExpect(jsonPath("$.pontosProduto").value(DEFAULT_PONTOS_PRODUTO));
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
            .pontosProcedimento(UPDATED_PONTOS_PROCEDIMENTO)
            .pontosPessoa(UPDATED_PONTOS_PESSOA)
            .pontosProcesso(UPDATED_PONTOS_PROCESSO)
            .pontosProduto(UPDATED_PONTOS_PRODUTO);
        ItemAvaliacaoDTO itemAvaliacaoDTO = itemAvaliacaoMapper.toDto(updatedItemAvaliacao);

        restItemAvaliacaoMockMvc.perform(put("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoDTO)))
            .andExpect(status().isOk());

        // Validate the ItemAvaliacao in the database
        List<ItemAvaliacao> itemAvaliacaoList = itemAvaliacaoRepository.findAll();
        assertThat(itemAvaliacaoList).hasSize(databaseSizeBeforeUpdate);
        ItemAvaliacao testItemAvaliacao = itemAvaliacaoList.get(itemAvaliacaoList.size() - 1);
        assertThat(testItemAvaliacao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testItemAvaliacao.isAnexoObrigatorio()).isEqualTo(UPDATED_ANEXO_OBRIGATORIO);
        assertThat(testItemAvaliacao.getPontosProcedimento()).isEqualTo(UPDATED_PONTOS_PROCEDIMENTO);
        assertThat(testItemAvaliacao.getPontosPessoa()).isEqualTo(UPDATED_PONTOS_PESSOA);
        assertThat(testItemAvaliacao.getPontosProcesso()).isEqualTo(UPDATED_PONTOS_PROCESSO);
        assertThat(testItemAvaliacao.getPontosProduto()).isEqualTo(UPDATED_PONTOS_PRODUTO);
    }

    @Test
    @Transactional
    public void updateNonExistingItemAvaliacao() throws Exception {
        int databaseSizeBeforeUpdate = itemAvaliacaoRepository.findAll().size();

        // Create the ItemAvaliacao
        ItemAvaliacaoDTO itemAvaliacaoDTO = itemAvaliacaoMapper.toDto(itemAvaliacao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemAvaliacaoMockMvc.perform(put("/api/item-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoDTO)))
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

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAvaliacaoDTO.class);
        ItemAvaliacaoDTO itemAvaliacaoDTO1 = new ItemAvaliacaoDTO();
        itemAvaliacaoDTO1.setId(1L);
        ItemAvaliacaoDTO itemAvaliacaoDTO2 = new ItemAvaliacaoDTO();
        assertThat(itemAvaliacaoDTO1).isNotEqualTo(itemAvaliacaoDTO2);
        itemAvaliacaoDTO2.setId(itemAvaliacaoDTO1.getId());
        assertThat(itemAvaliacaoDTO1).isEqualTo(itemAvaliacaoDTO2);
        itemAvaliacaoDTO2.setId(2L);
        assertThat(itemAvaliacaoDTO1).isNotEqualTo(itemAvaliacaoDTO2);
        itemAvaliacaoDTO1.setId(null);
        assertThat(itemAvaliacaoDTO1).isNotEqualTo(itemAvaliacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(itemAvaliacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(itemAvaliacaoMapper.fromId(null)).isNull();
    }
}
