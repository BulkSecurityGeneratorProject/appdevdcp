package br.com.lasa.dcpdesconformidades.web.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;
import com.google.common.net.HttpHeaders;

import br.com.lasa.dcpdesconformidades.service.ImagemService;
import br.com.lasa.dcpdesconformidades.service.ItemAvaliacaoService;
import br.com.lasa.dcpdesconformidades.service.dto.ImagemDTO;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAvaliacaoDTO;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ItemAvaliacao.
 */
@RestController
@RequestMapping("/api")
public class ItemAvaliacaoResource {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliacaoResource.class);

    private static final String ENTITY_NAME = "itemAvaliacao";

    private final ItemAvaliacaoService itemAvaliacaoService;
    
    private final ImagemService imagemService;

    public ItemAvaliacaoResource(ItemAvaliacaoService itemAvaliacaoService, ImagemService imagemService) {
        this.itemAvaliacaoService = itemAvaliacaoService;
        this.imagemService = imagemService;
    }

    /**
     * POST  /item-avaliacaos : Create a new itemAvaliacao.
     *
     * @param itemAvaliacaoDTO the itemAvaliacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAvaliacaoDTO, or with status 400 (Bad Request) if the itemAvaliacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-avaliacaos")
    @Timed
    public ResponseEntity<ItemAvaliacaoDTO> createItemAvaliacao(@Valid @RequestBody ItemAvaliacaoDTO itemAvaliacaoDTO) throws URISyntaxException {
        log.debug("REST request to save ItemAvaliacao : {}", itemAvaliacaoDTO);
        if (itemAvaliacaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemAvaliacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAvaliacaoDTO result = itemAvaliacaoService.save(itemAvaliacaoDTO);
        return ResponseEntity.created(new URI("/api/item-avaliacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getDescricao()))
            .body(result);
    }

    /**
     * PUT  /item-avaliacaos : Updates an existing itemAvaliacao.
     *
     * @param itemAvaliacaoDTO the itemAvaliacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAvaliacaoDTO,
     * or with status 400 (Bad Request) if the itemAvaliacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the itemAvaliacaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-avaliacaos")
    @Timed
    public ResponseEntity<ItemAvaliacaoDTO> updateItemAvaliacao(@Valid @RequestBody ItemAvaliacaoDTO itemAvaliacaoDTO) throws URISyntaxException {
        log.debug("REST request to update ItemAvaliacao : {}", itemAvaliacaoDTO);
        if (itemAvaliacaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemAvaliacaoDTO result = itemAvaliacaoService.save(itemAvaliacaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAvaliacaoDTO.getDescricao()))
            .body(result);
    }

    /**
     * GET  /item-avaliacaos : get all the itemAvaliacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemAvaliacaos in body
     */
    @GetMapping("/item-avaliacaos")
    @Timed
    public List<ItemAvaliacaoDTO> getAllItemAvaliacaos() {
        log.debug("REST request to get all ItemAvaliacaos");
        return itemAvaliacaoService.findAll();
    }

    /**
     * GET  /item-avaliacaos/:id : get the "id" itemAvaliacao.
     *
     * @param id the id of the itemAvaliacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAvaliacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/item-avaliacaos/{id}")
    @Timed
    public ResponseEntity<ItemAvaliacaoDTO> getItemAvaliacao(@PathVariable Long id) {
        log.debug("REST request to get ItemAvaliacao : {}", id);
        Optional<ItemAvaliacaoDTO> itemAvaliacaoDTO = itemAvaliacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemAvaliacaoDTO);
    }

    /**
     * DELETE  /item-avaliacaos/:id : delete the "id" itemAvaliacao.
     *
     * @param id the id of the itemAvaliacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-avaliacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAvaliacao(@PathVariable Long id) {
        log.debug("REST request to delete ItemAvaliacao : {}", id);
        itemAvaliacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * PUT  /item-avaliacaos/:id/anexo : upload anexo of itemAvaliacao.
     *
     * @param id the id of the itemAvaliacaoDTO 
     * @param anexo the file
     * @return the ResponseEntity with status 200 (OK)
     * @throws Throwable 
     */
    @PutMapping("/item-avaliacaos/{id}/anexo")
    @Timed
    public ResponseEntity<Void> uploadAnexoItemAvaliacao(@PathVariable Long id, @RequestPart MultipartFile anexo) throws Throwable {
        log.debug("REST request to put ItemAvaliacao : {} anexo {}", id, anexo.getOriginalFilename());
        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setItemAvaliadoId(id);
        imagemDTO.setNome(anexo.getOriginalFilename());
        imagemDTO.setSize(anexo.getSize());
        imagemDTO.setMimeType(anexo.getContentType());
        try {
			imagemDTO.setArquivo(anexo.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        imagemService.save(imagemDTO);
        
        return ResponseEntity
        		.created(new URI("/api/item-avaliacaos/" + imagemDTO.getItemAvaliadoId() + "/anexo/" + imagemDTO.getNome()))
                .headers(HeaderUtil.createEntityCreationAlert("anexo", imagemDTO.getNome()))
                .body(null);
    }

    /**
     * GET  /item-avaliacaos/:id/anexo : upload anexo of itemAvaliacao.
     *
     * @param id the id of the itemAvaliacaoDTO 
     * @param anexo the file
     * @return the ResponseEntity with status 200 (OK)
     * @throws Throwable 
     */
    @GetMapping("/item-avaliacaos/{id}/anexo/{nome}")
    @Timed
    public ResponseEntity<byte[]> downloadAnexoItemAvaliacao(@PathVariable Long id, @PathVariable String nome, HttpServletResponse response) throws Throwable {
        log.debug("REST request to put ItemAvaliacao : {}", id);
        
        
        ImagemDTO imagemDTO = imagemService.findOne(nome, id);
        
        return ResponseEntity.ok()
        	       .contentType(MediaType.parseMediaType(imagemDTO.getMimeType()))
        	       .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imagemDTO.getNome() + "\"")
        	       .body(imagemDTO.getArquivo());
    }

    /**
     * DELETE  /item-avaliacaos/:id/anexo : delete anexo of itemAvaliacao.
     *
     * @param id the id of the itemAvaliacaoDTO 
     * @param anexo the file
     * @return the ResponseEntity with status 200 (OK)
     * @throws Throwable 
     */
    @DeleteMapping("/item-avaliacaos/{id}/anexo/{nome}")
    @Timed
    public ResponseEntity<Void> deleteAnexoItemAvaliacao(@PathVariable Long id, @PathVariable String nome) throws Throwable {
        log.debug("REST request to put ItemAvaliacao : {}", id);
        
        imagemService.deleteOne(nome, id);
        
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("anexo", id.toString() + "/" + nome)).build();
    }
}
