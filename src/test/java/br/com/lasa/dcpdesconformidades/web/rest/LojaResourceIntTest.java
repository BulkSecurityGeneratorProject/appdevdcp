package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.Loja;
import br.com.lasa.dcpdesconformidades.repository.LojaRepository;
import br.com.lasa.dcpdesconformidades.service.LojaService;
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
 * Test class for the LojaResource REST controller.
 *
 * @see LojaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class LojaResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_RESPONSAVEL = "AAAAAAAAAA";
    private static final String UPDATED_NOME_RESPONSAVEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRONTUARIO_RESPONSAVEL = 1;
    private static final Integer UPDATED_PRONTUARIO_RESPONSAVEL = 2;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    @Autowired
    private LojaRepository lojaRepository;

    @Mock
    private LojaRepository lojaRepositoryMock;

    @Mock
    private LojaService lojaServiceMock;

    @Autowired
    private LojaService lojaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLojaMockMvc;

    private Loja loja;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LojaResource lojaResource = new LojaResource(lojaService);
        this.restLojaMockMvc = MockMvcBuilders.standaloneSetup(lojaResource)
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
    public static Loja createEntity(EntityManager em) {
        Loja loja = new Loja()
            .nome(DEFAULT_NOME)
            .nomeResponsavel(DEFAULT_NOME_RESPONSAVEL)
            .prontuarioResponsavel(DEFAULT_PRONTUARIO_RESPONSAVEL)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return loja;
    }

    @Before
    public void initTest() {
        loja = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoja() throws Exception {
        int databaseSizeBeforeCreate = lojaRepository.findAll().size();

        // Create the Loja
        restLojaMockMvc.perform(post("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loja)))
            .andExpect(status().isCreated());

        // Validate the Loja in the database
        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeCreate + 1);
        Loja testLoja = lojaList.get(lojaList.size() - 1);
        assertThat(testLoja.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testLoja.getNomeResponsavel()).isEqualTo(DEFAULT_NOME_RESPONSAVEL);
        assertThat(testLoja.getProntuarioResponsavel()).isEqualTo(DEFAULT_PRONTUARIO_RESPONSAVEL);
        assertThat(testLoja.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testLoja.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    public void createLojaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lojaRepository.findAll().size();

        // Create the Loja with an existing ID
        loja.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLojaMockMvc.perform(post("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loja)))
            .andExpect(status().isBadRequest());

        // Validate the Loja in the database
        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = lojaRepository.findAll().size();
        // set the field null
        loja.setNome(null);

        // Create the Loja, which fails.

        restLojaMockMvc.perform(post("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loja)))
            .andExpect(status().isBadRequest());

        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeResponsavelIsRequired() throws Exception {
        int databaseSizeBeforeTest = lojaRepository.findAll().size();
        // set the field null
        loja.setNomeResponsavel(null);

        // Create the Loja, which fails.

        restLojaMockMvc.perform(post("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loja)))
            .andExpect(status().isBadRequest());

        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProntuarioResponsavelIsRequired() throws Exception {
        int databaseSizeBeforeTest = lojaRepository.findAll().size();
        // set the field null
        loja.setProntuarioResponsavel(null);

        // Create the Loja, which fails.

        restLojaMockMvc.perform(post("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loja)))
            .andExpect(status().isBadRequest());

        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = lojaRepository.findAll().size();
        // set the field null
        loja.setLatitude(null);

        // Create the Loja, which fails.

        restLojaMockMvc.perform(post("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loja)))
            .andExpect(status().isBadRequest());

        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = lojaRepository.findAll().size();
        // set the field null
        loja.setLongitude(null);

        // Create the Loja, which fails.

        restLojaMockMvc.perform(post("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loja)))
            .andExpect(status().isBadRequest());

        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLojas() throws Exception {
        // Initialize the database
        lojaRepository.saveAndFlush(loja);

        // Get all the lojaList
        restLojaMockMvc.perform(get("/api/lojas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loja.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].nomeResponsavel").value(hasItem(DEFAULT_NOME_RESPONSAVEL.toString())))
            .andExpect(jsonPath("$.[*].prontuarioResponsavel").value(hasItem(DEFAULT_PRONTUARIO_RESPONSAVEL)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllLojasWithEagerRelationshipsIsEnabled() throws Exception {
        LojaResource lojaResource = new LojaResource(lojaServiceMock);
        when(lojaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restLojaMockMvc = MockMvcBuilders.standaloneSetup(lojaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restLojaMockMvc.perform(get("/api/lojas?eagerload=true"))
        .andExpect(status().isOk());

        verify(lojaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllLojasWithEagerRelationshipsIsNotEnabled() throws Exception {
        LojaResource lojaResource = new LojaResource(lojaServiceMock);
            when(lojaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restLojaMockMvc = MockMvcBuilders.standaloneSetup(lojaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restLojaMockMvc.perform(get("/api/lojas?eagerload=true"))
        .andExpect(status().isOk());

            verify(lojaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getLoja() throws Exception {
        // Initialize the database
        lojaRepository.saveAndFlush(loja);

        // Get the loja
        restLojaMockMvc.perform(get("/api/lojas/{id}", loja.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loja.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.nomeResponsavel").value(DEFAULT_NOME_RESPONSAVEL.toString()))
            .andExpect(jsonPath("$.prontuarioResponsavel").value(DEFAULT_PRONTUARIO_RESPONSAVEL))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLoja() throws Exception {
        // Get the loja
        restLojaMockMvc.perform(get("/api/lojas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoja() throws Exception {
        // Initialize the database
        lojaService.save(loja);

        int databaseSizeBeforeUpdate = lojaRepository.findAll().size();

        // Update the loja
        Loja updatedLoja = lojaRepository.findById(loja.getId()).get();
        // Disconnect from session so that the updates on updatedLoja are not directly saved in db
        em.detach(updatedLoja);
        updatedLoja
            .nome(UPDATED_NOME)
            .nomeResponsavel(UPDATED_NOME_RESPONSAVEL)
            .prontuarioResponsavel(UPDATED_PRONTUARIO_RESPONSAVEL)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);

        restLojaMockMvc.perform(put("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLoja)))
            .andExpect(status().isOk());

        // Validate the Loja in the database
        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeUpdate);
        Loja testLoja = lojaList.get(lojaList.size() - 1);
        assertThat(testLoja.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testLoja.getNomeResponsavel()).isEqualTo(UPDATED_NOME_RESPONSAVEL);
        assertThat(testLoja.getProntuarioResponsavel()).isEqualTo(UPDATED_PRONTUARIO_RESPONSAVEL);
        assertThat(testLoja.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testLoja.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void updateNonExistingLoja() throws Exception {
        int databaseSizeBeforeUpdate = lojaRepository.findAll().size();

        // Create the Loja

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLojaMockMvc.perform(put("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loja)))
            .andExpect(status().isBadRequest());

        // Validate the Loja in the database
        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLoja() throws Exception {
        // Initialize the database
        lojaService.save(loja);

        int databaseSizeBeforeDelete = lojaRepository.findAll().size();

        // Get the loja
        restLojaMockMvc.perform(delete("/api/lojas/{id}", loja.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Loja.class);
        Loja loja1 = new Loja();
        loja1.setId(1L);
        Loja loja2 = new Loja();
        loja2.setId(loja1.getId());
        assertThat(loja1).isEqualTo(loja2);
        loja2.setId(2L);
        assertThat(loja1).isNotEqualTo(loja2);
        loja1.setId(null);
        assertThat(loja1).isNotEqualTo(loja2);
    }
}
