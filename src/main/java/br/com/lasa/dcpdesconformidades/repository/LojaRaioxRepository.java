package br.com.lasa.dcpdesconformidades.repository;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import br.com.lasa.dcpdesconformidades.config.ApplicationProperties;
import br.com.lasa.dcpdesconformidades.service.dto.LojaRaioxDTO;

@SuppressWarnings("unused")
@Repository
public class LojaRaioxRepository {

  private RestTemplate restTemplate;
  private ApplicationProperties applicationProperties;

  public LojaRaioxRepository(RestTemplate restTemplate, ApplicationProperties applicationProperties) {
    this.restTemplate = restTemplate;
    this.applicationProperties = applicationProperties;
  }

  public Page<LojaRaioxDTO> findAll(Pageable pageable) { //TODO enviar os parametros para fazer a paginação
    ResponseEntity<List<LojaRaioxDTO>> response = restTemplate.exchange(applicationProperties.getUrlRaioxListarTodasLojas(), HttpMethod.GET, new HttpEntity<List<LojaRaioxDTO>>(createHeaders()), new ParameterizedTypeReference<List<LojaRaioxDTO>>() {});
    List<LojaRaioxDTO> lojas = response.getBody();
    return new PageImpl<>(lojas, PageRequest.of(0, lojas.size()), lojas.size());
  }
  
  public List<LojaRaioxDTO> findAll() {
    ResponseEntity<List<LojaRaioxDTO>> response = restTemplate.exchange(applicationProperties.getUrlRaioxListarTodasLojas(), HttpMethod.GET, new HttpEntity<List<LojaRaioxDTO>>(createHeaders()), new ParameterizedTypeReference<List<LojaRaioxDTO>>() {});
    return response.getBody();
  }

  public Optional<LojaRaioxDTO> findOne(Long id) {
    String url = applicationProperties.getUrlRaioxObterLojaPorCodigo() + id;
    
    ResponseEntity<List<LojaRaioxDTO>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<List<LojaRaioxDTO>>(createHeaders()), new ParameterizedTypeReference<List<LojaRaioxDTO>>() {});

    return Optional.of(response.getBody().get(0));
  }

  private HttpHeaders createHeaders() {
    return new HttpHeaders() {
      private static final long serialVersionUID = -6578539131364413332L;

      {
        String auth = applicationProperties.getUsuarioRaiox() + ":" + applicationProperties.getSenhaRaiox();
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("UTF-8")));
        String authHeader = "Basic " + new String(encodedAuth);
        set("Authorization", authHeader);
      }
    };
  }


}
