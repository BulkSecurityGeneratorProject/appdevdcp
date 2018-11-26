package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.GrupoItens;
import br.com.lasa.dcpdesconformidades.repository.GrupoItensRepository;
import br.com.lasa.dcpdesconformidades.service.GrupoItensService;
import br.com.lasa.dcpdesconformidades.service.dto.GrupoItensDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.GrupoItensMapper;
import br.com.lasa.dcpdesconformidades.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static br.com.lasa.dcpdesconformidades.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GrupoItensResource REST controller.
 *
 * @see GrupoItensResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class GrupoItensResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private GrupoItensRepository grupoItensRepository;

    @Mock
    private GrupoItensRepository grupoItensRepositoryMock;

    @Autowired
    private GrupoItensMapper grupoItensMapper;

    @Mock
    private GrupoItensService grupoItensServiceMock;

    @Autowired
    private GrupoItensService grupoItensService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGrupoItensMockMvc;

    private GrupoItens grupoItens;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GrupoItensResource grupoItensResource = new GrupoItensResource(grupoItensService);
        this.restGrupoItensMockMvc = MockMvcBuilders.standaloneSetup(grupoItensResource)
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
    public static GrupoItens createEntity(EntityManager em) {
        GrupoItens grupoItens = new GrupoItens()
            .nome(DEFAULT_NOME);
        return grupoItens;
    }

    @Before
    public void initTest() {
        grupoItens = createEntity(em);
    }

    @Test
    @Transactional
    public void createGrupoItens() throws Exception {
        int databaseSizeBeforeCreate = grupoItensRepository.findAll().size();

        // Create the GrupoItens
        GrupoItensDTO grupoItensDTO = grupoItensMapper.toDto(grupoItens);
        restGrupoItensMockMvc.perform(post("/api/grupo-itens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoItensDTO)))
            .andExpect(status().isCreated());

        // Validate the GrupoItens in the database
        List<GrupoItens> grupoItensList = grupoItensRepository.findAll();
        assertThat(grupoItensList).hasSize(databaseSizeBeforeCreate + 1);
        GrupoItens testGrupoItens = grupoItensList.get(grupoItensList.size() - 1);
        assertThat(testGrupoItens.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createGrupoItensWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = grupoItensRepository.findAll().size();

        // Create the GrupoItens with an existing ID
        grupoItens.setId(1L);
        GrupoItensDTO grupoItensDTO = grupoItensMapper.toDto(grupoItens);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrupoItensMockMvc.perform(post("/api/grupo-itens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoItensDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GrupoItens in the database
        List<GrupoItens> grupoItensList = grupoItensRepository.findAll();
        assertThat(grupoItensList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = grupoItensRepository.findAll().size();
        // set the field null
        grupoItens.setNome(null);

        // Create the GrupoItens, which fails.
        GrupoItensDTO grupoItensDTO = grupoItensMapper.toDto(grupoItens);

        restGrupoItensMockMvc.perform(post("/api/grupo-itens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoItensDTO)))
            .andExpect(status().isBadRequest());

        List<GrupoItens> grupoItensList = grupoItensRepository.findAll();
        assertThat(grupoItensList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGrupoItens() throws Exception {
        // Initialize the database
        grupoItensRepository.saveAndFlush(grupoItens);

        // Get all the grupoItensList
        restGrupoItensMockMvc.perform(get("/api/grupo-itens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grupoItens.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllGrupoItensWithEagerRelationshipsIsEnabled() throws Exception {
        GrupoItensResource grupoItensResource = new GrupoItensResource(grupoItensServiceMock);
        when(grupoItensServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restGrupoItensMockMvc = MockMvcBuilders.standaloneSetup(grupoItensResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restGrupoItensMockMvc.perform(get("/api/grupo-itens?eagerload=true"))
        .andExpect(status().isOk());

        verify(grupoItensServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllGrupoItensWithEagerRelationshipsIsNotEnabled() throws Exception {
        GrupoItensResource grupoItensResource = new GrupoItensResource(grupoItensServiceMock);
            when(grupoItensServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restGrupoItensMockMvc = MockMvcBuilders.standaloneSetup(grupoItensResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restGrupoItensMockMvc.perform(get("/api/grupo-itens?eagerload=true"))
        .andExpect(status().isOk());

            verify(grupoItensServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getGrupoItens() throws Exception {
        // Initialize the database
        grupoItensRepository.saveAndFlush(grupoItens);

        // Get the grupoItens
        restGrupoItensMockMvc.perform(get("/api/grupo-itens/{id}", grupoItens.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(grupoItens.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGrupoItens() throws Exception {
        // Get the grupoItens
        restGrupoItensMockMvc.perform(get("/api/grupo-itens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrupoItens() throws Exception {
        // Initialize the database
        grupoItensRepository.saveAndFlush(grupoItens);

        int databaseSizeBeforeUpdate = grupoItensRepository.findAll().size();

        // Update the grupoItens
        GrupoItens updatedGrupoItens = grupoItensRepository.findById(grupoItens.getId()).get();
        // Disconnect from session so that the updates on updatedGrupoItens are not directly saved in db
        em.detach(updatedGrupoItens);
        updatedGrupoItens
            .nome(UPDATED_NOME);
        GrupoItensDTO grupoItensDTO = grupoItensMapper.toDto(updatedGrupoItens);

        restGrupoItensMockMvc.perform(put("/api/grupo-itens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoItensDTO)))
            .andExpect(status().isOk());

        // Validate the GrupoItens in the database
        List<GrupoItens> grupoItensList = grupoItensRepository.findAll();
        assertThat(grupoItensList).hasSize(databaseSizeBeforeUpdate);
        GrupoItens testGrupoItens = grupoItensList.get(grupoItensList.size() - 1);
        assertThat(testGrupoItens.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingGrupoItens() throws Exception {
        int databaseSizeBeforeUpdate = grupoItensRepository.findAll().size();

        // Create the GrupoItens
        GrupoItensDTO grupoItensDTO = grupoItensMapper.toDto(grupoItens);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrupoItensMockMvc.perform(put("/api/grupo-itens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoItensDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GrupoItens in the database
        List<GrupoItens> grupoItensList = grupoItensRepository.findAll();
        assertThat(grupoItensList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGrupoItens() throws Exception {
        // Initialize the database
        grupoItensRepository.saveAndFlush(grupoItens);

        int databaseSizeBeforeDelete = grupoItensRepository.findAll().size();

        // Get the grupoItens
        restGrupoItensMockMvc.perform(delete("/api/grupo-itens/{id}", grupoItens.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GrupoItens> grupoItensList = grupoItensRepository.findAll();
        assertThat(grupoItensList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrupoItens.class);
        GrupoItens grupoItens1 = new GrupoItens();
        grupoItens1.setId(1L);
        GrupoItens grupoItens2 = new GrupoItens();
        grupoItens2.setId(grupoItens1.getId());
        assertThat(grupoItens1).isEqualTo(grupoItens2);
        grupoItens2.setId(2L);
        assertThat(grupoItens1).isNotEqualTo(grupoItens2);
        grupoItens1.setId(null);
        assertThat(grupoItens1).isNotEqualTo(grupoItens2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrupoItensDTO.class);
        GrupoItensDTO grupoItensDTO1 = new GrupoItensDTO();
        grupoItensDTO1.setId(1L);
        GrupoItensDTO grupoItensDTO2 = new GrupoItensDTO();
        assertThat(grupoItensDTO1).isNotEqualTo(grupoItensDTO2);
        grupoItensDTO2.setId(grupoItensDTO1.getId());
        assertThat(grupoItensDTO1).isEqualTo(grupoItensDTO2);
        grupoItensDTO2.setId(2L);
        assertThat(grupoItensDTO1).isNotEqualTo(grupoItensDTO2);
        grupoItensDTO1.setId(null);
        assertThat(grupoItensDTO1).isNotEqualTo(grupoItensDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(grupoItensMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(grupoItensMapper.fromId(null)).isNull();
    }
}
