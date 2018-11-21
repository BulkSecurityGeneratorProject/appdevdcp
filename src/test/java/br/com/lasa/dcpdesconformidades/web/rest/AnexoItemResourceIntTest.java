package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.AnexoItem;
import br.com.lasa.dcpdesconformidades.domain.ItemAvaliado;
import br.com.lasa.dcpdesconformidades.repository.AnexoItemRepository;
import br.com.lasa.dcpdesconformidades.service.AnexoItemService;
import br.com.lasa.dcpdesconformidades.service.dto.AnexoItemDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.AnexoItemMapper;
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

import br.com.lasa.dcpdesconformidades.domain.enumeration.TipoAnexoItem;
/**
 * Test class for the AnexoItemResource REST controller.
 *
 * @see AnexoItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class AnexoItemResourceIntTest {

    private static final TipoAnexoItem DEFAULT_TIPO = TipoAnexoItem.IMAGEM;
    private static final TipoAnexoItem UPDATED_TIPO = TipoAnexoItem.IMAGEM;

    private static final String DEFAULT_CAMINHO_ARQUIVO = "AAAAAAAAAA";
    private static final String UPDATED_CAMINHO_ARQUIVO = "BBBBBBBBBB";

    @Autowired
    private AnexoItemRepository anexoItemRepository;

    @Autowired
    private AnexoItemMapper anexoItemMapper;

    @Autowired
    private AnexoItemService anexoItemService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAnexoItemMockMvc;

    private AnexoItem anexoItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AnexoItemResource anexoItemResource = new AnexoItemResource(anexoItemService);
        this.restAnexoItemMockMvc = MockMvcBuilders.standaloneSetup(anexoItemResource)
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
    public static AnexoItem createEntity(EntityManager em) {
        AnexoItem anexoItem = new AnexoItem()
            .tipo(DEFAULT_TIPO)
            .caminhoArquivo(DEFAULT_CAMINHO_ARQUIVO);
        // Add required entity
        ItemAvaliado itemAvaliado = ItemAvaliadoResourceIntTest.createEntity(em);
        em.persist(itemAvaliado);
        em.flush();
        anexoItem.setItemAvaliado(itemAvaliado);
        return anexoItem;
    }

    @Before
    public void initTest() {
        anexoItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnexoItem() throws Exception {
        int databaseSizeBeforeCreate = anexoItemRepository.findAll().size();

        // Create the AnexoItem
        AnexoItemDTO anexoItemDTO = anexoItemMapper.toDto(anexoItem);
        restAnexoItemMockMvc.perform(post("/api/anexo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anexoItemDTO)))
            .andExpect(status().isCreated());

        // Validate the AnexoItem in the database
        List<AnexoItem> anexoItemList = anexoItemRepository.findAll();
        assertThat(anexoItemList).hasSize(databaseSizeBeforeCreate + 1);
        AnexoItem testAnexoItem = anexoItemList.get(anexoItemList.size() - 1);
        assertThat(testAnexoItem.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testAnexoItem.getCaminhoArquivo()).isEqualTo(DEFAULT_CAMINHO_ARQUIVO);
    }

    @Test
    @Transactional
    public void createAnexoItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = anexoItemRepository.findAll().size();

        // Create the AnexoItem with an existing ID
        anexoItem.setId(1L);
        AnexoItemDTO anexoItemDTO = anexoItemMapper.toDto(anexoItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnexoItemMockMvc.perform(post("/api/anexo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anexoItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnexoItem in the database
        List<AnexoItem> anexoItemList = anexoItemRepository.findAll();
        assertThat(anexoItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = anexoItemRepository.findAll().size();
        // set the field null
        anexoItem.setTipo(null);

        // Create the AnexoItem, which fails.
        AnexoItemDTO anexoItemDTO = anexoItemMapper.toDto(anexoItem);

        restAnexoItemMockMvc.perform(post("/api/anexo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anexoItemDTO)))
            .andExpect(status().isBadRequest());

        List<AnexoItem> anexoItemList = anexoItemRepository.findAll();
        assertThat(anexoItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCaminhoArquivoIsRequired() throws Exception {
        int databaseSizeBeforeTest = anexoItemRepository.findAll().size();
        // set the field null
        anexoItem.setCaminhoArquivo(null);

        // Create the AnexoItem, which fails.
        AnexoItemDTO anexoItemDTO = anexoItemMapper.toDto(anexoItem);

        restAnexoItemMockMvc.perform(post("/api/anexo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anexoItemDTO)))
            .andExpect(status().isBadRequest());

        List<AnexoItem> anexoItemList = anexoItemRepository.findAll();
        assertThat(anexoItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAnexoItems() throws Exception {
        // Initialize the database
        anexoItemRepository.saveAndFlush(anexoItem);

        // Get all the anexoItemList
        restAnexoItemMockMvc.perform(get("/api/anexo-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anexoItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].caminhoArquivo").value(hasItem(DEFAULT_CAMINHO_ARQUIVO.toString())));
    }
    
    @Test
    @Transactional
    public void getAnexoItem() throws Exception {
        // Initialize the database
        anexoItemRepository.saveAndFlush(anexoItem);

        // Get the anexoItem
        restAnexoItemMockMvc.perform(get("/api/anexo-items/{id}", anexoItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(anexoItem.getId().intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.caminhoArquivo").value(DEFAULT_CAMINHO_ARQUIVO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAnexoItem() throws Exception {
        // Get the anexoItem
        restAnexoItemMockMvc.perform(get("/api/anexo-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnexoItem() throws Exception {
        // Initialize the database
        anexoItemRepository.saveAndFlush(anexoItem);

        int databaseSizeBeforeUpdate = anexoItemRepository.findAll().size();

        // Update the anexoItem
        AnexoItem updatedAnexoItem = anexoItemRepository.findById(anexoItem.getId()).get();
        // Disconnect from session so that the updates on updatedAnexoItem are not directly saved in db
        em.detach(updatedAnexoItem);
        updatedAnexoItem
            .tipo(UPDATED_TIPO)
            .caminhoArquivo(UPDATED_CAMINHO_ARQUIVO);
        AnexoItemDTO anexoItemDTO = anexoItemMapper.toDto(updatedAnexoItem);

        restAnexoItemMockMvc.perform(put("/api/anexo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anexoItemDTO)))
            .andExpect(status().isOk());

        // Validate the AnexoItem in the database
        List<AnexoItem> anexoItemList = anexoItemRepository.findAll();
        assertThat(anexoItemList).hasSize(databaseSizeBeforeUpdate);
        AnexoItem testAnexoItem = anexoItemList.get(anexoItemList.size() - 1);
        assertThat(testAnexoItem.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testAnexoItem.getCaminhoArquivo()).isEqualTo(UPDATED_CAMINHO_ARQUIVO);
    }

    @Test
    @Transactional
    public void updateNonExistingAnexoItem() throws Exception {
        int databaseSizeBeforeUpdate = anexoItemRepository.findAll().size();

        // Create the AnexoItem
        AnexoItemDTO anexoItemDTO = anexoItemMapper.toDto(anexoItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnexoItemMockMvc.perform(put("/api/anexo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anexoItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnexoItem in the database
        List<AnexoItem> anexoItemList = anexoItemRepository.findAll();
        assertThat(anexoItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnexoItem() throws Exception {
        // Initialize the database
        anexoItemRepository.saveAndFlush(anexoItem);

        int databaseSizeBeforeDelete = anexoItemRepository.findAll().size();

        // Get the anexoItem
        restAnexoItemMockMvc.perform(delete("/api/anexo-items/{id}", anexoItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AnexoItem> anexoItemList = anexoItemRepository.findAll();
        assertThat(anexoItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnexoItem.class);
        AnexoItem anexoItem1 = new AnexoItem();
        anexoItem1.setId(1L);
        AnexoItem anexoItem2 = new AnexoItem();
        anexoItem2.setId(anexoItem1.getId());
        assertThat(anexoItem1).isEqualTo(anexoItem2);
        anexoItem2.setId(2L);
        assertThat(anexoItem1).isNotEqualTo(anexoItem2);
        anexoItem1.setId(null);
        assertThat(anexoItem1).isNotEqualTo(anexoItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnexoItemDTO.class);
        AnexoItemDTO anexoItemDTO1 = new AnexoItemDTO();
        anexoItemDTO1.setId(1L);
        AnexoItemDTO anexoItemDTO2 = new AnexoItemDTO();
        assertThat(anexoItemDTO1).isNotEqualTo(anexoItemDTO2);
        anexoItemDTO2.setId(anexoItemDTO1.getId());
        assertThat(anexoItemDTO1).isEqualTo(anexoItemDTO2);
        anexoItemDTO2.setId(2L);
        assertThat(anexoItemDTO1).isNotEqualTo(anexoItemDTO2);
        anexoItemDTO1.setId(null);
        assertThat(anexoItemDTO1).isNotEqualTo(anexoItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(anexoItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(anexoItemMapper.fromId(null)).isNull();
    }
}
