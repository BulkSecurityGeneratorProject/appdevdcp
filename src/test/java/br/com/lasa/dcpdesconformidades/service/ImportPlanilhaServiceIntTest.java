package br.com.lasa.dcpdesconformidades.service;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;

/**
 * Test class for the ImportPlanilhaService
 *
 * @see ImportPlanilhaService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
@Transactional
public class ImportPlanilhaServiceIntTest {

  @Autowired
  private ImportPlanilhaService importPlanilhaService;


  @Before
  public void init() {
  }

  @Test
  @Transactional
  public void testImportacaoDePlanilha() {


    File file = null;
    try {
      file = ResourceUtils.getFile("classpath:planilhas/Relatório de Desconformidade - L723 17.09.2018.xls");
      importPlanilhaService.importar(file);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
