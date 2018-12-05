package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

import br.com.lasa.dcpdesconformidades.domain.PerdaQuebraAcumuladosAnoLoja;
import br.com.lasa.dcpdesconformidades.domain.Loja;
import br.com.lasa.dcpdesconformidades.repository.PerdaQuebraAcumuladosAnoLojaRepository;
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
import java.math.BigDecimal;
import java.util.List;


import static br.com.lasa.dcpdesconformidades.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CategorizacaoPerdaQuebra;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CategorizacaoPerdaQuebra;
/**
 * Test class for the PerdaQuebraAcumuladosAnoLojaResource REST controller.
 *
 * @see PerdaQuebraAcumuladosAnoLojaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
public class PerdaQuebraAcumuladosAnoLojaResourceIntTest {

    private static final Integer DEFAULT_ANO = 1;
    private static final Integer UPDATED_ANO = 2;

    private static final Double DEFAULT_PERCENTUAL_PERDA = 1D;
    private static final Double UPDATED_PERCENTUAL_PERDA = 2D;

    private static final BigDecimal DEFAULT_FINANCEIRO_PERDA = new BigDecimal(1);
    private static final BigDecimal UPDATED_FINANCEIRO_PERDA = new BigDecimal(2);

    private static final Integer DEFAULT_PONTUACAO_PERDA = 1;
    private static final Integer UPDATED_PONTUACAO_PERDA = 2;

    private static final StatusItemAvaliado DEFAULT_STATUS_PERDA = StatusItemAvaliado.OK;
    private static final StatusItemAvaliado UPDATED_STATUS_PERDA = StatusItemAvaliado.NAO_OK;

    private static final CategorizacaoPerdaQuebra DEFAULT_CATEGORIZACAO_PERDA = CategorizacaoPerdaQuebra.INADMISSIVEL;
    private static final CategorizacaoPerdaQuebra UPDATED_CATEGORIZACAO_PERDA = CategorizacaoPerdaQuebra.CRITICO;

    private static final Double DEFAULT_PERCENTUAL_QUEBRA = 1D;
    private static final Double UPDATED_PERCENTUAL_QUEBRA = 2D;

    private static final BigDecimal DEFAULT_FINANCEIRO_QUEBRA = new BigDecimal(1);
    private static final BigDecimal UPDATED_FINANCEIRO_QUEBRA = new BigDecimal(2);

    private static final Integer DEFAULT_PONTUACAO_QUEBRA = 1;
    private static final Integer UPDATED_PONTUACAO_QUEBRA = 2;

    private static final StatusItemAvaliado DEFAULT_STATUS_QUEBRA = StatusItemAvaliado.OK;
    private static final StatusItemAvaliado UPDATED_STATUS_QUEBRA = StatusItemAvaliado.NAO_OK;

    private static final CategorizacaoPerdaQuebra DEFAULT_CATEGORIZACAO_QUEBRA = CategorizacaoPerdaQuebra.INADMISSIVEL;
    private static final CategorizacaoPerdaQuebra UPDATED_CATEGORIZACAO_QUEBRA = CategorizacaoPerdaQuebra.CRITICO;

    @Autowired
    private PerdaQuebraAcumuladosAnoLojaRepository perdaQuebraAcumuladosAnoLojaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPerdaQuebraAcumuladosAnoLojaMockMvc;

    private PerdaQuebraAcumuladosAnoLoja perdaQuebraAcumuladosAnoLoja;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PerdaQuebraAcumuladosAnoLojaResource perdaQuebraAcumuladosAnoLojaResource = new PerdaQuebraAcumuladosAnoLojaResource(perdaQuebraAcumuladosAnoLojaRepository);
        this.restPerdaQuebraAcumuladosAnoLojaMockMvc = MockMvcBuilders.standaloneSetup(perdaQuebraAcumuladosAnoLojaResource)
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
    public static PerdaQuebraAcumuladosAnoLoja createEntity(EntityManager em) {
        PerdaQuebraAcumuladosAnoLoja perdaQuebraAcumuladosAnoLoja = new PerdaQuebraAcumuladosAnoLoja()
            .ano(DEFAULT_ANO)
            .percentualPerda(DEFAULT_PERCENTUAL_PERDA)
            .financeiroPerda(DEFAULT_FINANCEIRO_PERDA)
            .pontuacaoPerda(DEFAULT_PONTUACAO_PERDA)
            .statusPerda(DEFAULT_STATUS_PERDA)
            .categorizacaoPerda(DEFAULT_CATEGORIZACAO_PERDA)
            .percentualQuebra(DEFAULT_PERCENTUAL_QUEBRA)
            .financeiroQuebra(DEFAULT_FINANCEIRO_QUEBRA)
            .pontuacaoQuebra(DEFAULT_PONTUACAO_QUEBRA)
            .statusQuebra(DEFAULT_STATUS_QUEBRA)
            .categorizacaoQuebra(DEFAULT_CATEGORIZACAO_QUEBRA);
        // Add required entity
        Loja loja = LojaResourceIntTest.createEntity(em);
        em.persist(loja);
        em.flush();
        perdaQuebraAcumuladosAnoLoja.setLoja(loja);
        return perdaQuebraAcumuladosAnoLoja;
    }

    @Before
    public void initTest() {
        perdaQuebraAcumuladosAnoLoja = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerdaQuebraAcumuladosAnoLoja() throws Exception {
        int databaseSizeBeforeCreate = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();

        // Create the PerdaQuebraAcumuladosAnoLoja
        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isCreated());

        // Validate the PerdaQuebraAcumuladosAnoLoja in the database
        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeCreate + 1);
        PerdaQuebraAcumuladosAnoLoja testPerdaQuebraAcumuladosAnoLoja = perdaQuebraAcumuladosAnoLojaList.get(perdaQuebraAcumuladosAnoLojaList.size() - 1);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getAno()).isEqualTo(DEFAULT_ANO);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getPercentualPerda()).isEqualTo(DEFAULT_PERCENTUAL_PERDA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getFinanceiroPerda()).isEqualTo(DEFAULT_FINANCEIRO_PERDA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getPontuacaoPerda()).isEqualTo(DEFAULT_PONTUACAO_PERDA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getStatusPerda()).isEqualTo(DEFAULT_STATUS_PERDA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getCategorizacaoPerda()).isEqualTo(DEFAULT_CATEGORIZACAO_PERDA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getPercentualQuebra()).isEqualTo(DEFAULT_PERCENTUAL_QUEBRA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getFinanceiroQuebra()).isEqualTo(DEFAULT_FINANCEIRO_QUEBRA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getPontuacaoQuebra()).isEqualTo(DEFAULT_PONTUACAO_QUEBRA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getStatusQuebra()).isEqualTo(DEFAULT_STATUS_QUEBRA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getCategorizacaoQuebra()).isEqualTo(DEFAULT_CATEGORIZACAO_QUEBRA);
    }

    @Test
    @Transactional
    public void createPerdaQuebraAcumuladosAnoLojaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();

        // Create the PerdaQuebraAcumuladosAnoLoja with an existing ID
        perdaQuebraAcumuladosAnoLoja.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        // Validate the PerdaQuebraAcumuladosAnoLoja in the database
        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setAno(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPercentualPerdaIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setPercentualPerda(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFinanceiroPerdaIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setFinanceiroPerda(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontuacaoPerdaIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setPontuacaoPerda(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusPerdaIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setStatusPerda(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategorizacaoPerdaIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setCategorizacaoPerda(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPercentualQuebraIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setPercentualQuebra(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFinanceiroQuebraIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setFinanceiroQuebra(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPontuacaoQuebraIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setPontuacaoQuebra(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusQuebraIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setStatusQuebra(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategorizacaoQuebraIsRequired() throws Exception {
        int databaseSizeBeforeTest = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();
        // set the field null
        perdaQuebraAcumuladosAnoLoja.setCategorizacaoQuebra(null);

        // Create the PerdaQuebraAcumuladosAnoLoja, which fails.

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(post("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPerdaQuebraAcumuladosAnoLojas() throws Exception {
        // Initialize the database
        perdaQuebraAcumuladosAnoLojaRepository.saveAndFlush(perdaQuebraAcumuladosAnoLoja);

        // Get all the perdaQuebraAcumuladosAnoLojaList
        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(get("/api/perda-quebra-acumulados-ano-lojas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(perdaQuebraAcumuladosAnoLoja.getId().intValue())))
            .andExpect(jsonPath("$.[*].ano").value(hasItem(DEFAULT_ANO)))
            .andExpect(jsonPath("$.[*].percentualPerda").value(hasItem(DEFAULT_PERCENTUAL_PERDA.doubleValue())))
            .andExpect(jsonPath("$.[*].financeiroPerda").value(hasItem(DEFAULT_FINANCEIRO_PERDA.intValue())))
            .andExpect(jsonPath("$.[*].pontuacaoPerda").value(hasItem(DEFAULT_PONTUACAO_PERDA)))
            .andExpect(jsonPath("$.[*].statusPerda").value(hasItem(DEFAULT_STATUS_PERDA.toString())))
            .andExpect(jsonPath("$.[*].categorizacaoPerda").value(hasItem(DEFAULT_CATEGORIZACAO_PERDA.toString())))
            .andExpect(jsonPath("$.[*].percentualQuebra").value(hasItem(DEFAULT_PERCENTUAL_QUEBRA.doubleValue())))
            .andExpect(jsonPath("$.[*].financeiroQuebra").value(hasItem(DEFAULT_FINANCEIRO_QUEBRA.intValue())))
            .andExpect(jsonPath("$.[*].pontuacaoQuebra").value(hasItem(DEFAULT_PONTUACAO_QUEBRA)))
            .andExpect(jsonPath("$.[*].statusQuebra").value(hasItem(DEFAULT_STATUS_QUEBRA.toString())))
            .andExpect(jsonPath("$.[*].categorizacaoQuebra").value(hasItem(DEFAULT_CATEGORIZACAO_QUEBRA.toString())));
    }
    
    @Test
    @Transactional
    public void getPerdaQuebraAcumuladosAnoLoja() throws Exception {
        // Initialize the database
        perdaQuebraAcumuladosAnoLojaRepository.saveAndFlush(perdaQuebraAcumuladosAnoLoja);

        // Get the perdaQuebraAcumuladosAnoLoja
        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(get("/api/perda-quebra-acumulados-ano-lojas/{id}", perdaQuebraAcumuladosAnoLoja.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(perdaQuebraAcumuladosAnoLoja.getId().intValue()))
            .andExpect(jsonPath("$.ano").value(DEFAULT_ANO))
            .andExpect(jsonPath("$.percentualPerda").value(DEFAULT_PERCENTUAL_PERDA.doubleValue()))
            .andExpect(jsonPath("$.financeiroPerda").value(DEFAULT_FINANCEIRO_PERDA.intValue()))
            .andExpect(jsonPath("$.pontuacaoPerda").value(DEFAULT_PONTUACAO_PERDA))
            .andExpect(jsonPath("$.statusPerda").value(DEFAULT_STATUS_PERDA.toString()))
            .andExpect(jsonPath("$.categorizacaoPerda").value(DEFAULT_CATEGORIZACAO_PERDA.toString()))
            .andExpect(jsonPath("$.percentualQuebra").value(DEFAULT_PERCENTUAL_QUEBRA.doubleValue()))
            .andExpect(jsonPath("$.financeiroQuebra").value(DEFAULT_FINANCEIRO_QUEBRA.intValue()))
            .andExpect(jsonPath("$.pontuacaoQuebra").value(DEFAULT_PONTUACAO_QUEBRA))
            .andExpect(jsonPath("$.statusQuebra").value(DEFAULT_STATUS_QUEBRA.toString()))
            .andExpect(jsonPath("$.categorizacaoQuebra").value(DEFAULT_CATEGORIZACAO_QUEBRA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPerdaQuebraAcumuladosAnoLoja() throws Exception {
        // Get the perdaQuebraAcumuladosAnoLoja
        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(get("/api/perda-quebra-acumulados-ano-lojas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerdaQuebraAcumuladosAnoLoja() throws Exception {
        // Initialize the database
        perdaQuebraAcumuladosAnoLojaRepository.saveAndFlush(perdaQuebraAcumuladosAnoLoja);

        int databaseSizeBeforeUpdate = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();

        // Update the perdaQuebraAcumuladosAnoLoja
        PerdaQuebraAcumuladosAnoLoja updatedPerdaQuebraAcumuladosAnoLoja = perdaQuebraAcumuladosAnoLojaRepository.findById(perdaQuebraAcumuladosAnoLoja.getId()).get();
        // Disconnect from session so that the updates on updatedPerdaQuebraAcumuladosAnoLoja are not directly saved in db
        em.detach(updatedPerdaQuebraAcumuladosAnoLoja);
        updatedPerdaQuebraAcumuladosAnoLoja
            .ano(UPDATED_ANO)
            .percentualPerda(UPDATED_PERCENTUAL_PERDA)
            .financeiroPerda(UPDATED_FINANCEIRO_PERDA)
            .pontuacaoPerda(UPDATED_PONTUACAO_PERDA)
            .statusPerda(UPDATED_STATUS_PERDA)
            .categorizacaoPerda(UPDATED_CATEGORIZACAO_PERDA)
            .percentualQuebra(UPDATED_PERCENTUAL_QUEBRA)
            .financeiroQuebra(UPDATED_FINANCEIRO_QUEBRA)
            .pontuacaoQuebra(UPDATED_PONTUACAO_QUEBRA)
            .statusQuebra(UPDATED_STATUS_QUEBRA)
            .categorizacaoQuebra(UPDATED_CATEGORIZACAO_QUEBRA);

        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(put("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPerdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isOk());

        // Validate the PerdaQuebraAcumuladosAnoLoja in the database
        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeUpdate);
        PerdaQuebraAcumuladosAnoLoja testPerdaQuebraAcumuladosAnoLoja = perdaQuebraAcumuladosAnoLojaList.get(perdaQuebraAcumuladosAnoLojaList.size() - 1);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getAno()).isEqualTo(UPDATED_ANO);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getPercentualPerda()).isEqualTo(UPDATED_PERCENTUAL_PERDA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getFinanceiroPerda()).isEqualTo(UPDATED_FINANCEIRO_PERDA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getPontuacaoPerda()).isEqualTo(UPDATED_PONTUACAO_PERDA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getStatusPerda()).isEqualTo(UPDATED_STATUS_PERDA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getCategorizacaoPerda()).isEqualTo(UPDATED_CATEGORIZACAO_PERDA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getPercentualQuebra()).isEqualTo(UPDATED_PERCENTUAL_QUEBRA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getFinanceiroQuebra()).isEqualTo(UPDATED_FINANCEIRO_QUEBRA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getPontuacaoQuebra()).isEqualTo(UPDATED_PONTUACAO_QUEBRA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getStatusQuebra()).isEqualTo(UPDATED_STATUS_QUEBRA);
        assertThat(testPerdaQuebraAcumuladosAnoLoja.getCategorizacaoQuebra()).isEqualTo(UPDATED_CATEGORIZACAO_QUEBRA);
    }

    @Test
    @Transactional
    public void updateNonExistingPerdaQuebraAcumuladosAnoLoja() throws Exception {
        int databaseSizeBeforeUpdate = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();

        // Create the PerdaQuebraAcumuladosAnoLoja

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(put("/api/perda-quebra-acumulados-ano-lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perdaQuebraAcumuladosAnoLoja)))
            .andExpect(status().isBadRequest());

        // Validate the PerdaQuebraAcumuladosAnoLoja in the database
        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerdaQuebraAcumuladosAnoLoja() throws Exception {
        // Initialize the database
        perdaQuebraAcumuladosAnoLojaRepository.saveAndFlush(perdaQuebraAcumuladosAnoLoja);

        int databaseSizeBeforeDelete = perdaQuebraAcumuladosAnoLojaRepository.findAll().size();

        // Get the perdaQuebraAcumuladosAnoLoja
        restPerdaQuebraAcumuladosAnoLojaMockMvc.perform(delete("/api/perda-quebra-acumulados-ano-lojas/{id}", perdaQuebraAcumuladosAnoLoja.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojaList = perdaQuebraAcumuladosAnoLojaRepository.findAll();
        assertThat(perdaQuebraAcumuladosAnoLojaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PerdaQuebraAcumuladosAnoLoja.class);
        PerdaQuebraAcumuladosAnoLoja perdaQuebraAcumuladosAnoLoja1 = new PerdaQuebraAcumuladosAnoLoja();
        perdaQuebraAcumuladosAnoLoja1.setId(1L);
        PerdaQuebraAcumuladosAnoLoja perdaQuebraAcumuladosAnoLoja2 = new PerdaQuebraAcumuladosAnoLoja();
        perdaQuebraAcumuladosAnoLoja2.setId(perdaQuebraAcumuladosAnoLoja1.getId());
        assertThat(perdaQuebraAcumuladosAnoLoja1).isEqualTo(perdaQuebraAcumuladosAnoLoja2);
        perdaQuebraAcumuladosAnoLoja2.setId(2L);
        assertThat(perdaQuebraAcumuladosAnoLoja1).isNotEqualTo(perdaQuebraAcumuladosAnoLoja2);
        perdaQuebraAcumuladosAnoLoja1.setId(null);
        assertThat(perdaQuebraAcumuladosAnoLoja1).isNotEqualTo(perdaQuebraAcumuladosAnoLoja2);
    }
}
