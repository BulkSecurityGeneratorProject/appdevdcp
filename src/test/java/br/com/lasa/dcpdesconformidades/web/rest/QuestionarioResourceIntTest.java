package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.Questionario;
import br.com.lasa.dcpdesconformidades.repository.QuestionarioRepository;
import br.com.lasa.dcpdesconformidades.service.QuestionarioService;
import br.com.lasa.dcpdesconformidades.service.dto.QuestionarioDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.QuestionarioMapper;
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
 * Test class for the QuestionarioResource REST controller.
 *
 * @see QuestionarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class QuestionarioResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ATIVO = false;
    private static final Boolean UPDATED_ATIVO = true;

    @Autowired
    private QuestionarioRepository questionarioRepository;

    @Mock
    private QuestionarioRepository questionarioRepositoryMock;

    @Autowired
    private QuestionarioMapper questionarioMapper;

    @Mock
    private QuestionarioService questionarioServiceMock;

    @Autowired
    private QuestionarioService questionarioService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restQuestionarioMockMvc;

    private Questionario questionario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuestionarioResource questionarioResource = new QuestionarioResource(questionarioService);
        this.restQuestionarioMockMvc = MockMvcBuilders.standaloneSetup(questionarioResource)
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
    public static Questionario createEntity(EntityManager em) {
        Questionario questionario = new Questionario()
            .nome(DEFAULT_NOME)
            .descricao(DEFAULT_DESCRICAO)
            .ativo(DEFAULT_ATIVO);
        return questionario;
    }

    @Before
    public void initTest() {
        questionario = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuestionario() throws Exception {
        int databaseSizeBeforeCreate = questionarioRepository.findAll().size();

        // Create the Questionario
        QuestionarioDTO questionarioDTO = questionarioMapper.toDto(questionario);
        restQuestionarioMockMvc.perform(post("/api/questionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(questionarioDTO)))
            .andExpect(status().isCreated());

        // Validate the Questionario in the database
        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeCreate + 1);
        Questionario testQuestionario = questionarioList.get(questionarioList.size() - 1);
        assertThat(testQuestionario.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testQuestionario.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testQuestionario.isAtivo()).isEqualTo(DEFAULT_ATIVO);
    }

    @Test
    @Transactional
    public void createQuestionarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = questionarioRepository.findAll().size();

        // Create the Questionario with an existing ID
        questionario.setId(1L);
        QuestionarioDTO questionarioDTO = questionarioMapper.toDto(questionario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionarioMockMvc.perform(post("/api/questionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(questionarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Questionario in the database
        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = questionarioRepository.findAll().size();
        // set the field null
        questionario.setNome(null);

        // Create the Questionario, which fails.
        QuestionarioDTO questionarioDTO = questionarioMapper.toDto(questionario);

        restQuestionarioMockMvc.perform(post("/api/questionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(questionarioDTO)))
            .andExpect(status().isBadRequest());

        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAtivoIsRequired() throws Exception {
        int databaseSizeBeforeTest = questionarioRepository.findAll().size();
        // set the field null
        questionario.setAtivo(null);

        // Create the Questionario, which fails.
        QuestionarioDTO questionarioDTO = questionarioMapper.toDto(questionario);

        restQuestionarioMockMvc.perform(post("/api/questionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(questionarioDTO)))
            .andExpect(status().isBadRequest());

        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuestionarios() throws Exception {
        // Initialize the database
        questionarioRepository.saveAndFlush(questionario);

        // Get all the questionarioList
        restQuestionarioMockMvc.perform(get("/api/questionarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionario.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].ativo").value(hasItem(DEFAULT_ATIVO.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllQuestionariosWithEagerRelationshipsIsEnabled() throws Exception {
        QuestionarioResource questionarioResource = new QuestionarioResource(questionarioServiceMock);
        when(questionarioServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restQuestionarioMockMvc = MockMvcBuilders.standaloneSetup(questionarioResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restQuestionarioMockMvc.perform(get("/api/questionarios?eagerload=true"))
        .andExpect(status().isOk());

        verify(questionarioServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllQuestionariosWithEagerRelationshipsIsNotEnabled() throws Exception {
        QuestionarioResource questionarioResource = new QuestionarioResource(questionarioServiceMock);
            when(questionarioServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restQuestionarioMockMvc = MockMvcBuilders.standaloneSetup(questionarioResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restQuestionarioMockMvc.perform(get("/api/questionarios?eagerload=true"))
        .andExpect(status().isOk());

            verify(questionarioServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getQuestionario() throws Exception {
        // Initialize the database
        questionarioRepository.saveAndFlush(questionario);

        // Get the questionario
        restQuestionarioMockMvc.perform(get("/api/questionarios/{id}", questionario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(questionario.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.ativo").value(DEFAULT_ATIVO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQuestionario() throws Exception {
        // Get the questionario
        restQuestionarioMockMvc.perform(get("/api/questionarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuestionario() throws Exception {
        // Initialize the database
        questionarioRepository.saveAndFlush(questionario);

        int databaseSizeBeforeUpdate = questionarioRepository.findAll().size();

        // Update the questionario
        Questionario updatedQuestionario = questionarioRepository.findById(questionario.getId()).get();
        // Disconnect from session so that the updates on updatedQuestionario are not directly saved in db
        em.detach(updatedQuestionario);
        updatedQuestionario
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO)
            .ativo(UPDATED_ATIVO);
        QuestionarioDTO questionarioDTO = questionarioMapper.toDto(updatedQuestionario);

        restQuestionarioMockMvc.perform(put("/api/questionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(questionarioDTO)))
            .andExpect(status().isOk());

        // Validate the Questionario in the database
        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeUpdate);
        Questionario testQuestionario = questionarioList.get(questionarioList.size() - 1);
        assertThat(testQuestionario.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testQuestionario.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testQuestionario.isAtivo()).isEqualTo(UPDATED_ATIVO);
    }

    @Test
    @Transactional
    public void updateNonExistingQuestionario() throws Exception {
        int databaseSizeBeforeUpdate = questionarioRepository.findAll().size();

        // Create the Questionario
        QuestionarioDTO questionarioDTO = questionarioMapper.toDto(questionario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionarioMockMvc.perform(put("/api/questionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(questionarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Questionario in the database
        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuestionario() throws Exception {
        // Initialize the database
        questionarioRepository.saveAndFlush(questionario);

        int databaseSizeBeforeDelete = questionarioRepository.findAll().size();

        // Get the questionario
        restQuestionarioMockMvc.perform(delete("/api/questionarios/{id}", questionario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Questionario.class);
        Questionario questionario1 = new Questionario();
        questionario1.setId(1L);
        Questionario questionario2 = new Questionario();
        questionario2.setId(questionario1.getId());
        assertThat(questionario1).isEqualTo(questionario2);
        questionario2.setId(2L);
        assertThat(questionario1).isNotEqualTo(questionario2);
        questionario1.setId(null);
        assertThat(questionario1).isNotEqualTo(questionario2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionarioDTO.class);
        QuestionarioDTO questionarioDTO1 = new QuestionarioDTO();
        questionarioDTO1.setId(1L);
        QuestionarioDTO questionarioDTO2 = new QuestionarioDTO();
        assertThat(questionarioDTO1).isNotEqualTo(questionarioDTO2);
        questionarioDTO2.setId(questionarioDTO1.getId());
        assertThat(questionarioDTO1).isEqualTo(questionarioDTO2);
        questionarioDTO2.setId(2L);
        assertThat(questionarioDTO1).isNotEqualTo(questionarioDTO2);
        questionarioDTO1.setId(null);
        assertThat(questionarioDTO1).isNotEqualTo(questionarioDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(questionarioMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(questionarioMapper.fromId(null)).isNull();
    }
}
