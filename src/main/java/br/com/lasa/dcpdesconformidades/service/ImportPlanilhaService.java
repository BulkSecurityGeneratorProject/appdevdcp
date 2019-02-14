package br.com.lasa.dcpdesconformidades.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.lasa.dcpdesconformidades.config.ApplicationProperties;
import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.domain.ItemAuditado;
import br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao;
import br.com.lasa.dcpdesconformidades.domain.ItemAvaliado;
import br.com.lasa.dcpdesconformidades.domain.ItemSolicitadoAjuste;
import br.com.lasa.dcpdesconformidades.domain.Loja;
import br.com.lasa.dcpdesconformidades.domain.Questionario;
import br.com.lasa.dcpdesconformidades.domain.User;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CategorizacaoPerdaQuebra;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CriticidadePainel;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusAvaliacao;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;
import br.com.lasa.dcpdesconformidades.domain.enumeration.TipoItemAuditado;
import br.com.lasa.dcpdesconformidades.repository.AvaliacaoRepository;
import br.com.lasa.dcpdesconformidades.repository.ItemAvaliacaoRepository;
import br.com.lasa.dcpdesconformidades.repository.LojaRepository;
import br.com.lasa.dcpdesconformidades.repository.QuestionarioRepository;
import br.com.lasa.dcpdesconformidades.repository.UserRepository;

/**
 * Service Implementation
 */
@Service
public class ImportPlanilhaService {

  private final Logger log = LoggerFactory.getLogger(ImportPlanilhaService.class);

  private final ApplicationProperties applicationProperties;

  private final AvaliacaoRepository avaliacaoRepository;

  private final UserRepository userRepository;

  private final QuestionarioRepository questionarioRepository;

  private final ItemAvaliacaoRepository itemAvaliacaoRepository;

  private final LojaRepository lojaRepository;

  // private final HSSFDataFormatter _formatter = new HSSFDataFormatter(new Locale("pt", "BR"));
  private final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

  private static final List<Integer> LINHAS_COM_ITENS_DE_CHECKLIST =
      Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 19, 20, 21, 22, 23, 24, 25, 26, 30, 31, 32, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 53, 54, 55, 56, 57, 58, 59, 60, 64, 65, 66, 67,
          68, 69, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 101, 102, 103, 104, 105, 106, 107, 108);
  private static final Integer LINHA_INICIO_TOP_5_PERDAS_AUDITORIA = 4;
  private static final Integer LINHA_FIM_TOP_5_PERDAS_AUDITORIA = 13;
  private static final Integer LINHA_INICIO_AR_AUDITORIA = 14;
  private static final Integer LINHA_FIM_AR_AUDITORIA = 23;
  private static final Integer LINHA_INICIO_TROCA_CANCELAMENTO_DVC_AUDITORIA = 24;
  private static final Integer LINHA_FIM_TROCA_CANCELAMENTO_DVC_AUDITORIA = 29;
  private static final Integer LINHA_INICIO_SOLICITACAO_AJUSTE = 4;
  private static final Integer LINHA_FIM_SOLICITACAO_AJUSTE = 22;

  public ImportPlanilhaService(ApplicationProperties applicationProperties, UserRepository userRepository, QuestionarioRepository questionarioRepository,
      ItemAvaliacaoRepository itemAvaliacaoRepository, LojaRepository lojaRepository, AvaliacaoRepository avaliacaoRepository) {
    this.applicationProperties = applicationProperties;
    this.userRepository = userRepository;
    this.questionarioRepository = questionarioRepository;
    this.itemAvaliacaoRepository = itemAvaliacaoRepository;
    this.lojaRepository = lojaRepository;
    this.avaliacaoRepository = avaliacaoRepository;
  }

//  public void importar(File file) {
//
//  }

  public void importar(File file) {
    HSSFWorkbook workbook = null;
    try {
      // activate logging to console
      // System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.SystemOutLogger");
      // System.setProperty("poi.log.level", POILogger.INFO + "");

      workbook = (HSSFWorkbook) WorkbookFactory.create(file);
      workbook.setMissingCellPolicy(MissingCellPolicy.RETURN_BLANK_AS_NULL);
      // FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
      // evaluator.evaluateAll();

      // ExcelExtractor extractor = new ExcelExtractor(workbook);
      //
      // extractor.setIncludeSheetNames(true);
      // extractor.setIncludeCellComments(true);
      // String text = extractor.getText();
      // System.out.println(text);



      Avaliacao avaliacao = obterAvaliacao(workbook, file);

//      Path copied = Paths.get(applicationProperties.getFilesBasePath() + avaliacao.getCaminhoArquivoPlanilha());
//      Files.copy(file.toPath(), copied, StandardCopyOption.REPLACE_EXISTING);



      avaliacaoRepository.save(avaliacao);



      // TODO comentarios nao estao retornando porque o POI nao está identificando a coluna dos
      // comentários (está retornando null)
      // extractCellValueWithComments(sheetChecklist, "J11");
      //
      // sheetChecklist.getCellComments().forEach((cellAddress, o) ->
      // sheetChecklist.getRow(cellAddress.getRow()).getCell(cellAddress.getColumn(),
      // Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));



    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (workbook != null) {
        try {
          workbook.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }

  }

  private Avaliacao obterAvaliacao(HSSFWorkbook workbook, File file) {
    HSSFSheet sheetChecklist = workbook.getSheet("RD");
    HSSFSheet sheetAuditoria = workbook.getSheet("Anexo 1 - Auditoria");
    HSSFSheet sheetSolicitacaoAjuste = workbook.getSheet("Anexo 2 - Solicitação de Ajuste");
    HSSFSheet sheetEtiqueta = workbook.getSheet("Anexo 3 - Etiqueta");

    String codigoLoja = extractCellValueWithCommentsAsString(sheetChecklist, "D137");
    String nomeResponsavelLoja = extractCellValueWithCommentsAsString(sheetChecklist, "D5");
    Integer prontuarioResponsavelLoja = Integer.valueOf(extractCellValueWithCommentsAsString(sheetChecklist, "F5"));
    String nomeAvaliador = extractCellValueWithCommentsAsString(sheetChecklist, "D4");
    Integer prontuarioAvaliador = Integer.valueOf(extractCellValueWithCommentsAsString(sheetChecklist, "F4"));
    CriticidadePainel criticidadePainel = CriticidadePainel.fromDescricao(extractCellValueWithCommentsAsString(sheetChecklist, "G3"));
    String dataAsString = extractCellValueWithCommentsAsString(sheetChecklist, "I3");
    Instant dataAvaliacao = LocalDate.parse(dataAsString, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("America/Sao_Paulo")).toInstant();


    Double percentualPerda = Double.valueOf(extractCellValueWithCommentsAsString(sheetChecklist, "E112"));
    BigDecimal financeiroPerda = new BigDecimal(extractCellValueWithCommentsAsString(sheetChecklist, "F112"));
    Integer pontuacaoPerda = Float.valueOf(extractCellValueWithCommentsAsString(sheetChecklist, "H112")).intValue();
    StatusItemAvaliado statusPerda = StatusItemAvaliado.fromClassificacao(extractCellValueWithCommentsAsString(sheetChecklist, "C112"));
    CategorizacaoPerdaQuebra categorizacaoPerda = CategorizacaoPerdaQuebra.fromDescricao(extractCellValueWithCommentsAsString(sheetChecklist, "G112"));
    Double percentualQuebra = Double.valueOf(extractCellValueWithCommentsAsString(sheetChecklist, "E113"));
    BigDecimal financeiroQuebra = new BigDecimal(extractCellValueWithCommentsAsString(sheetChecklist, "F113"));
    Integer pontuacaoQuebra = Float.valueOf(extractCellValueWithCommentsAsString(sheetChecklist, "H113")).intValue();
    StatusItemAvaliado statusQuebra = StatusItemAvaliado.fromClassificacao(extractCellValueWithCommentsAsString(sheetChecklist, "C113"));
    CategorizacaoPerdaQuebra categorizacaoQuebra = CategorizacaoPerdaQuebra.fromDescricao(extractCellValueWithCommentsAsString(sheetChecklist, "G113"));

    NivelEficiencia nivelEficienciaGeral = NivelEficiencia.valueOf(extractCellValueWithCommentsAsString(sheetEtiqueta, "V3"));
    NivelEficiencia nivelEficienciaProcedimento = NivelEficiencia.valueOf(extractCellValueWithCommentsAsString(sheetEtiqueta, "J12"));
    NivelEficiencia nivelEficienciaPessoa = NivelEficiencia.valueOf(extractCellValueWithCommentsAsString(sheetEtiqueta, "K28"));
    NivelEficiencia nivelEficienciaProcesso = NivelEficiencia.valueOf(extractCellValueWithCommentsAsString(sheetEtiqueta, "E28"));
    NivelEficiencia nivelEficienciaProduto = NivelEficiencia.valueOf(extractCellValueWithCommentsAsString(sheetEtiqueta, "H28"));

    // Considerando que só temos um Questionário
    Questionario questionario = questionarioRepository.findAllWithEagerRelationshipsWhereAtivoTrue().orElseThrow(() -> new RuntimeException("Não há questionários ativos."));
    
    List<ItemAvaliacao> itens = itemAvaliacaoRepository.findByGrupoIn(questionario.getGrupos());

    User avaliador = userRepository.findOneByProntuario(prontuarioAvaliador) //
        .orElseThrow(() -> new RuntimeException("Avaliador de prontuário \"" + prontuarioAvaliador + "\" não encontrado."));

    Loja loja = lojaRepository.findById(Long.valueOf(codigoLoja.replaceAll("[^\\d]", ""))).orElseThrow(() -> new RuntimeException("Loja de código \"" + codigoLoja + "\" não encontrada."));

    Avaliacao avaliacao = new Avaliacao();
    avaliacao.nomeResponsavelLoja(nomeResponsavelLoja);
    avaliacao.prontuarioResponsavelLoja(prontuarioResponsavelLoja);
    avaliacao.avaliador(avaliador);
    avaliacao.loja(loja);
    avaliacao.questionario(questionario);
    avaliacao.criticidadePainel(criticidadePainel);
    avaliacao.iniciadaEm(dataAvaliacao);
    avaliacao.latitudeInicioAvaliacao(loja.getLatitude());
    avaliacao.longitudeInicioAvaliacao(loja.getLongitude());
    avaliacao.latitudeSubmissaoAvaliacao(loja.getLatitude());
    avaliacao.longitudeSubmissaoAvaliacao(loja.getLongitude());
    avaliacao.status(StatusAvaliacao.SUBMETIDA);
    avaliacao.criticidadePainel(criticidadePainel);
    avaliacao.nivelEficienciaGeral(nivelEficienciaGeral);
    avaliacao.nivelEficienciaProcedimento(nivelEficienciaProcedimento);
    avaliacao.nivelEficienciaPessoa(nivelEficienciaPessoa);
    avaliacao.nivelEficienciaProcesso(nivelEficienciaProcesso);
    avaliacao.nivelEficienciaProduto(nivelEficienciaProduto);
    avaliacao.submetidaEm(dataAvaliacao);
    avaliacao.percentualPerda(percentualPerda);
    avaliacao.financeiroPerda(financeiroPerda);
    avaliacao.pontuacaoPerda(pontuacaoPerda);
    avaliacao.statusPerda(statusPerda);
    avaliacao.categorizacaoPerda(categorizacaoPerda);
    avaliacao.percentualQuebra(percentualQuebra);
    avaliacao.financeiroQuebra(financeiroQuebra);
    avaliacao.pontuacaoQuebra(pontuacaoQuebra);
    avaliacao.statusQuebra(statusQuebra);
    avaliacao.categorizacaoQuebra(categorizacaoQuebra);
    avaliacao.importadoViaPlanilha(true);
    String nomeArquivo =
        String.format("RD L%d - %s.%s", loja.getId(), DateTimeFormatter.ofPattern("dd.MM.yyyy").withZone(ZoneOffset.UTC).format(dataAvaliacao), FilenameUtils.getExtension(file.getName()));
    avaliacao.caminhoArquivoPlanilha(loja.getId() + File.separator + nomeArquivo);


    avaliacao.setItensAvaliados(obterItensAvaliados(sheetChecklist, itens, loja, dataAvaliacao));
    avaliacao.setItensAuditados(obterItensAuditados(sheetAuditoria, dataAvaliacao));
    avaliacao.setItensComAjusteSolicitados(obterItensComAjusteSolicitado(sheetSolicitacaoAjuste, dataAvaliacao));

    avaliacao.getItensAvaliados().forEach(i -> i.setAvaliacao(avaliacao));
    avaliacao.getItensAuditados().forEach(i -> i.setAvaliacao(avaliacao));
    avaliacao.getItensComAjusteSolicitados().forEach(i -> i.setAvaliacao(avaliacao));


    return avaliacao;
  }

  private Set<ItemAvaliado> obterItensAvaliados(HSSFSheet sheetChecklist, List<ItemAvaliacao> itens, Loja loja, Instant dataAvaliacao) {
    Set<ItemAvaliado> itensAvaliados = new HashSet<>();
    for (int rowNum = sheetChecklist.getFirstRowNum(); rowNum <= sheetChecklist.getLastRowNum(); rowNum++) {
      HSSFRow row = sheetChecklist.getRow(rowNum);
      if (row == null || !LINHAS_COM_ITENS_DE_CHECKLIST.contains(rowNum + 1)) {
        continue;
      }


      StatusItemAvaliado status = StatusItemAvaliado.fromClassificacao(extractCellValueWithCommentsAsString(row.getCell(2)));
      String descricao = extractCellValueWithCommentsAsString(row.getCell(3));


      String observacoes = extractCellValueWithCommentsAsString(row.getCell(9));

      ItemAvaliacao itemAvaliacao = itens.stream() //
          .filter(i -> i.getDescricao().equals(descricao)) //
          .findFirst() //
          .orElseThrow(() -> new RuntimeException("Item \"" + descricao + "\" não encontrado no Questionário."));

      ItemAvaliado itemAvaliado = new ItemAvaliado();
      itemAvaliado.itemAvaliacao(itemAvaliacao);
      itemAvaliado.respondidoEm(dataAvaliacao);
      itemAvaliado.status(status);
      itemAvaliado.observacoes(observacoes);
      itemAvaliado.latitudeLocalResposta(loja.getLatitude());
      itemAvaliado.longitudeLocalResposta(loja.getLongitude());

      // N/A não tem pontos válidos / obtidos.
      if (status != StatusItemAvaliado.N_A) {
        
        Integer pontosProcedimento = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(15))).intValue();
        Integer pontosPessoa = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(16))).intValue();
        Integer pontosProcesso = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(17))).intValue();
        Integer pontosProduto = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(18))).intValue();
        Integer pontosObtidosProcedimento = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(4))).intValue();
        Integer pontosObtidosPessoa = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(5))).intValue();
        Integer pontosObtidosProcesso = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(6))).intValue();
        Integer pontosObtidosProduto = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(7))).intValue();

        itemAvaliado.pontosProcedimento(pontosProcedimento);
        itemAvaliado.pontosPessoa(pontosPessoa);
        itemAvaliado.pontosProcesso(pontosProcesso);
        itemAvaliado.pontosProduto(pontosProduto);
        itemAvaliado.pontosObtidosProcedimento(pontosObtidosProcedimento);
        itemAvaliado.pontosObtidosPessoa(pontosObtidosPessoa);
        itemAvaliado.pontosObtidosProcesso(pontosObtidosProcesso);
        itemAvaliado.pontosObtidosProduto(pontosObtidosProduto);
      }

      itensAvaliados.add(itemAvaliado);
    }

    return itensAvaliados;
  }

  private Set<ItemAuditado> obterItensAuditados(HSSFSheet sheetAuditoria, Instant dataAvaliacao) {
    Set<ItemAuditado> itensAuditados = new HashSet<>();
    for (int rowNum = sheetAuditoria.getFirstRowNum(); rowNum <= sheetAuditoria.getLastRowNum(); rowNum++) {
      HSSFRow row = sheetAuditoria.getRow(rowNum);
      if (row == null) {
        continue;
      }

      TipoItemAuditado tipoItemAuditado = null;
      if (rowNum + 1 >= LINHA_INICIO_TOP_5_PERDAS_AUDITORIA && rowNum + 1 <= LINHA_FIM_TOP_5_PERDAS_AUDITORIA) {
        tipoItemAuditado = TipoItemAuditado.TOP_5_PERDAS;
      } else if (rowNum + 1 >= LINHA_INICIO_AR_AUDITORIA && rowNum + 1 <= LINHA_FIM_AR_AUDITORIA) {
        tipoItemAuditado = TipoItemAuditado.ALTO_RISCO;
      } else if (rowNum + 1 >= LINHA_INICIO_TROCA_CANCELAMENTO_DVC_AUDITORIA && rowNum + 1 <= LINHA_FIM_TROCA_CANCELAMENTO_DVC_AUDITORIA) {
        tipoItemAuditado = TipoItemAuditado.TROCA_CANCELAMENTO_DVC;
      } else {
        continue;
      }

      Integer codigoDepartamento = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(3))).intValue();
      Integer codigoSap = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(4))).intValue();
      String descricaoItem = extractCellValueWithCommentsAsString(row.getCell(5));
      Integer saldoSap0001 = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(6))).intValue();
      Integer saldoFisico0001 = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(7))).intValue();
      Integer saldoSap9000 = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(9))).intValue();
      Integer saldoFisico9000 = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(10))).intValue();
      String motivoDivergencia = extractCellValueWithCommentsAsString(row.getCell(12));

      ItemAuditado itemAuditado = new ItemAuditado();
      itemAuditado.tipo(tipoItemAuditado);
      itemAuditado.respondidoEm(dataAvaliacao);
      itemAuditado.codigoDepartamento(codigoDepartamento);
      itemAuditado.codigoSap(codigoSap);
      itemAuditado.descricaoItem(descricaoItem);
      itemAuditado.saldoSap0001(saldoSap0001);
      itemAuditado.saldoFisico0001(saldoFisico0001);
      itemAuditado.saldoSap9000(saldoSap9000);
      itemAuditado.saldoFisico9000(saldoFisico9000);
      itemAuditado.motivoDivergencia(motivoDivergencia);

      itensAuditados.add(itemAuditado);
    }

    return itensAuditados;
  }

  private Set<ItemSolicitadoAjuste> obterItensComAjusteSolicitado(HSSFSheet sheetSolicitacaoAjuste, Instant dataAvaliacao) {
    Set<ItemSolicitadoAjuste> itensComAjusteSolicitado = new HashSet<>();
    for (int rowNum = sheetSolicitacaoAjuste.getFirstRowNum(); rowNum <= sheetSolicitacaoAjuste.getLastRowNum(); rowNum++) {
      HSSFRow row = sheetSolicitacaoAjuste.getRow(rowNum);
      if (row == null) {
        continue;
      }

      if (rowNum + 1 < LINHA_INICIO_SOLICITACAO_AJUSTE || rowNum + 1 > LINHA_FIM_SOLICITACAO_AJUSTE) {
        continue;
      }

      String codigoSapAsString = extractCellValueWithCommentsAsString(row.getCell(1));
      if (codigoSapAsString == null) { // Ignora as linhas vazias
        continue;
      }

      Integer codigoSap = Float.valueOf(codigoSapAsString).intValue();
      String descricaoItem = extractCellValueWithCommentsAsString(row.getCell(2));
      Integer saldoSap0001 = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(3))).intValue();
      Integer saldoFisico0001 = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(4))).intValue();
      Integer saldoSap9000 = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(6))).intValue();
      Integer saldoFisico9000 = Float.valueOf(extractCellValueWithCommentsAsString(row.getCell(7))).intValue();
      String motivoDivergencia = extractCellValueWithCommentsAsString(row.getCell(9));
      String acaoCorretivaOuPreventiva = extractCellValueWithCommentsAsString(row.getCell(10));
      String responsavel = extractCellValueWithCommentsAsString(row.getCell(11));

      ItemSolicitadoAjuste itemSolicitadoAjuste = new ItemSolicitadoAjuste();
      itemSolicitadoAjuste.respondidoEm(dataAvaliacao);
      itemSolicitadoAjuste.codigoSap(codigoSap);
      itemSolicitadoAjuste.descricaoItem(descricaoItem);
      itemSolicitadoAjuste.saldoSap0001(saldoSap0001);
      itemSolicitadoAjuste.saldoFisico0001(saldoFisico0001);
      itemSolicitadoAjuste.saldoSap9000(saldoSap9000);
      itemSolicitadoAjuste.saldoFisico9000(saldoFisico9000);
      itemSolicitadoAjuste.motivoDivergencia(motivoDivergencia);
      itemSolicitadoAjuste.acaoCorretivaOuPreventiva(acaoCorretivaOuPreventiva);
      itemSolicitadoAjuste.responsavel(responsavel);

      itensComAjusteSolicitado.add(itemSolicitadoAjuste);
    }

    return itensComAjusteSolicitado;
  }

  private String extractCellValueWithCommentsAsString(Sheet sheet, String cellCode) {
    CellReference cellReference = new CellReference(cellCode);
    Row row = sheet.getRow(cellReference.getRow());
    Cell cell = row.getCell(cellReference.getCol());

    return extractCellValueWithCommentsAsString((HSSFCell) cell);
  }

  private String extractCellValueWithCommentsAsString(HSSFCell cell) {
    String output = null;

    if (cell == null) {
      return null;
    }
    switch (cell.getCellType()) {
      case STRING:
        output = cell.getRichStringCellValue().getString();
        break;
      case NUMERIC:
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
          output = DATE_FORMATTER.format(cell.getDateCellValue());
        } else {
          // output = _formatter.formatCellValue(cell);
          output = String.valueOf(cell.getNumericCellValue());
        }
        break;
      case BOOLEAN:
        output = String.valueOf(cell.getBooleanCellValue());
        break;
      case ERROR:
        throw new RuntimeException(ErrorEval.getText(cell.getErrorCellValue()));
      case FORMULA:
        switch (cell.getCachedFormulaResultType()) {
          case STRING:
            HSSFRichTextString str = cell.getRichStringCellValue();
            if (str != null && str.length() > 0) {
              output = str.toString();
            }
            break;
          case NUMERIC:
            // HSSFCellStyle style = cell.getCellStyle();
            // double nVal = cell.getNumericCellValue();
            // short df = style.getDataFormat();
            // String dfs = style.getDataFormatString();
            // output = _formatter.formatRawCellContents(nVal, df, dfs);
            output = String.valueOf(cell.getNumericCellValue());
            break;
          case BOOLEAN:
            output = String.valueOf(cell.getBooleanCellValue());
            break;
          case ERROR:
            throw new RuntimeException(ErrorEval.getText(cell.getErrorCellValue()));
          default:
            throw new IllegalStateException("Unexpected cell cached formula result type: " + cell.getCachedFormulaResultType());

        }
        break;
      default:
        throw new RuntimeException("Unexpected cell type (" + cell.getCellType() + ")");
    }

    // Output the comment, if requested and exists
    HSSFComment comment = cell.getCellComment();
    if (comment != null) {
      // Concatena o comentário
      output += " - " + comment.getString().getString();
    }
    return output;
  }

}
