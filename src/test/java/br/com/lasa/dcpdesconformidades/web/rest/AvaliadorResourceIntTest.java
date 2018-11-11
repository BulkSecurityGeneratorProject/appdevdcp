package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.Avaliador;
import br.com.lasa.dcpdesconformidades.repository.AvaliadorRepository;
import br.com.lasa.dcpdesconformidades.service.AvaliadorService;
import br.com.lasa.dcpdesconformidades.service.dto.AvaliadorDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.AvaliadorMapper;
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
 * Test class for the AvaliadorResource REST controller.
 *
 * @see AvaliadorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class AvaliadorResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRONTUARIO = 1;
    private static final Integer UPDATED_PRONTUARIO = 2;

    @Autowired
    private AvaliadorRepository avaliadorRepository;

    @Autowired
    private AvaliadorMapper avaliadorMapper;

    @Autowired
    private AvaliadorService avaliadorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAvaliadorMockMvc;

    private Avaliador avaliador;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AvaliadorResource avaliadorResource = new AvaliadorResource(avaliadorService);
        this.restAvaliadorMockMvc = MockMvcBuilders.standaloneSetup(avaliadorResource)
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
    public static Avaliador createEntity(EntityManager em) {
        Avaliador avaliador = new Avaliador()
            .nome(DEFAULT_NOME)
            .login(DEFAULT_LOGIN)
            .prontuario(DEFAULT_PRONTUARIO);
        return avaliador;
    }

    @Before
    public void initTest() {
        avaliador = createEntity(em);
    }

    @Test
    @Transactional
    public void createAvaliador() throws Exception {
        int databaseSizeBeforeCreate = avaliadorRepository.findAll().size();

        // Create the Avaliador
        AvaliadorDTO avaliadorDTO = avaliadorMapper.toDto(avaliador);
        restAvaliadorMockMvc.perform(post("/api/avaliadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliadorDTO)))
            .andExpect(status().isCreated());

        // Validate the Avaliador in the database
        List<Avaliador> avaliadorList = avaliadorRepository.findAll();
        assertThat(avaliadorList).hasSize(databaseSizeBeforeCreate + 1);
        Avaliador testAvaliador = avaliadorList.get(avaliadorList.size() - 1);
        assertThat(testAvaliador.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAvaliador.getLogin()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testAvaliador.getProntuario()).isEqualTo(DEFAULT_PRONTUARIO);
    }

    @Test
    @Transactional
    public void createAvaliadorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avaliadorRepository.findAll().size();

        // Create the Avaliador with an existing ID
        avaliador.setId(1L);
        AvaliadorDTO avaliadorDTO = avaliadorMapper.toDto(avaliador);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvaliadorMockMvc.perform(post("/api/avaliadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliadorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Avaliador in the database
        List<Avaliador> avaliadorList = avaliadorRepository.findAll();
        assertThat(avaliadorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliadorRepository.findAll().size();
        // set the field null
        avaliador.setNome(null);

        // Create the Avaliador, which fails.
        AvaliadorDTO avaliadorDTO = avaliadorMapper.toDto(avaliador);

        restAvaliadorMockMvc.perform(post("/api/avaliadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliadorDTO)))
            .andExpect(status().isBadRequest());

        List<Avaliador> avaliadorList = avaliadorRepository.findAll();
        assertThat(avaliadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoginIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliadorRepository.findAll().size();
        // set the field null
        avaliador.setLogin(null);

        // Create the Avaliador, which fails.
        AvaliadorDTO avaliadorDTO = avaliadorMapper.toDto(avaliador);

        restAvaliadorMockMvc.perform(post("/api/avaliadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliadorDTO)))
            .andExpect(status().isBadRequest());

        List<Avaliador> avaliadorList = avaliadorRepository.findAll();
        assertThat(avaliadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProntuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliadorRepository.findAll().size();
        // set the field null
        avaliador.setProntuario(null);

        // Create the Avaliador, which fails.
        AvaliadorDTO avaliadorDTO = avaliadorMapper.toDto(avaliador);

        restAvaliadorMockMvc.perform(post("/api/avaliadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliadorDTO)))
            .andExpect(status().isBadRequest());

        List<Avaliador> avaliadorList = avaliadorRepository.findAll();
        assertThat(avaliadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAvaliadors() throws Exception {
        // Initialize the database
        avaliadorRepository.saveAndFlush(avaliador);

        // Get all the avaliadorList
        restAvaliadorMockMvc.perform(get("/api/avaliadors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avaliador.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN.toString())))
            .andExpect(jsonPath("$.[*].prontuario").value(hasItem(DEFAULT_PRONTUARIO)));
    }
    
    @Test
    @Transactional
    public void getAvaliador() throws Exception {
        // Initialize the database
        avaliadorRepository.saveAndFlush(avaliador);

        // Get the avaliador
        restAvaliadorMockMvc.perform(get("/api/avaliadors/{id}", avaliador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(avaliador.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN.toString()))
            .andExpect(jsonPath("$.prontuario").value(DEFAULT_PRONTUARIO));
    }

    @Test
    @Transactional
    public void getNonExistingAvaliador() throws Exception {
        // Get the avaliador
        restAvaliadorMockMvc.perform(get("/api/avaliadors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAvaliador() throws Exception {
        // Initialize the database
        avaliadorRepository.saveAndFlush(avaliador);

        int databaseSizeBeforeUpdate = avaliadorRepository.findAll().size();

        // Update the avaliador
        Avaliador updatedAvaliador = avaliadorRepository.findById(avaliador.getId()).get();
        // Disconnect from session so that the updates on updatedAvaliador are not directly saved in db
        em.detach(updatedAvaliador);
        updatedAvaliador
            .nome(UPDATED_NOME)
            .login(UPDATED_LOGIN)
            .prontuario(UPDATED_PRONTUARIO);
        AvaliadorDTO avaliadorDTO = avaliadorMapper.toDto(updatedAvaliador);

        restAvaliadorMockMvc.perform(put("/api/avaliadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliadorDTO)))
            .andExpect(status().isOk());

        // Validate the Avaliador in the database
        List<Avaliador> avaliadorList = avaliadorRepository.findAll();
        assertThat(avaliadorList).hasSize(databaseSizeBeforeUpdate);
        Avaliador testAvaliador = avaliadorList.get(avaliadorList.size() - 1);
        assertThat(testAvaliador.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAvaliador.getLogin()).isEqualTo(UPDATED_LOGIN);
        assertThat(testAvaliador.getProntuario()).isEqualTo(UPDATED_PRONTUARIO);
    }

    @Test
    @Transactional
    public void updateNonExistingAvaliador() throws Exception {
        int databaseSizeBeforeUpdate = avaliadorRepository.findAll().size();

        // Create the Avaliador
        AvaliadorDTO avaliadorDTO = avaliadorMapper.toDto(avaliador);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvaliadorMockMvc.perform(put("/api/avaliadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliadorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Avaliador in the database
        List<Avaliador> avaliadorList = avaliadorRepository.findAll();
        assertThat(avaliadorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAvaliador() throws Exception {
        // Initialize the database
        avaliadorRepository.saveAndFlush(avaliador);

        int databaseSizeBeforeDelete = avaliadorRepository.findAll().size();

        // Get the avaliador
        restAvaliadorMockMvc.perform(delete("/api/avaliadors/{id}", avaliador.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Avaliador> avaliadorList = avaliadorRepository.findAll();
        assertThat(avaliadorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Avaliador.class);
        Avaliador avaliador1 = new Avaliador();
        avaliador1.setId(1L);
        Avaliador avaliador2 = new Avaliador();
        avaliador2.setId(avaliador1.getId());
        assertThat(avaliador1).isEqualTo(avaliador2);
        avaliador2.setId(2L);
        assertThat(avaliador1).isNotEqualTo(avaliador2);
        avaliador1.setId(null);
        assertThat(avaliador1).isNotEqualTo(avaliador2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvaliadorDTO.class);
        AvaliadorDTO avaliadorDTO1 = new AvaliadorDTO();
        avaliadorDTO1.setId(1L);
        AvaliadorDTO avaliadorDTO2 = new AvaliadorDTO();
        assertThat(avaliadorDTO1).isNotEqualTo(avaliadorDTO2);
        avaliadorDTO2.setId(avaliadorDTO1.getId());
        assertThat(avaliadorDTO1).isEqualTo(avaliadorDTO2);
        avaliadorDTO2.setId(2L);
        assertThat(avaliadorDTO1).isNotEqualTo(avaliadorDTO2);
        avaliadorDTO1.setId(null);
        assertThat(avaliadorDTO1).isNotEqualTo(avaliadorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(avaliadorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(avaliadorMapper.fromId(null)).isNull();
    }
}
