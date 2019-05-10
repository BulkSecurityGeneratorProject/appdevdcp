package br.com.lasa.dcpdesconformidades.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import br.com.lasa.dcpdesconformidades.service.dto.ImagemDTO;

/**
 * Service Implementation for managing Imagem.
 */
@Service
public class ImagemService {

	private final Logger log = LoggerFactory.getLogger(ImagemService.class);

	@Value("${spring.azure.storage.defaultEndpointsProtocol}")
	private String defaultEndpointsProtocol;

	@Value("${spring.azure.storage.account}")
	private String accountName;

	@Value("${spring.azure.storage.key1}")
	private String accountKey;
	
	private static final DecimalFormat df = new DecimalFormat("000000000000000");

	/**
	 * Get one imagem by nome and itemAvaliadoId.
	 *
	 * @param nome o nome da imagem
	 * @param itemAvaliadoId itemAvaliadoId da imagem
	 * @return the ImagemDTO
	 * @throws Throwable 
	 */
	public ImagemDTO findOne(String nome, Long itemAvaliadoId) throws Throwable {
		log.debug("Request to get Imagem : {}/{}", itemAvaliadoId, nome);
		return downloadFile(itemAvaliadoId, nome);
	}

	/**
	 * Save a loja.
	 *
	 * @param loja the entity to save
	 * @return the persisted entity
	 * @throws Throwable 
	 */
	public void save(ImagemDTO imagemDTO) throws Throwable {
		log.debug("Request to save ImagemDTO : {}", imagemDTO);
		uploadFile(imagemDTO);
	}

	/**
	 * Delete one imagem by nome and itemAvaliadoId.
	 *
	 * @param nome o nome da imagem
	 * @param itemAvaliadoId itemAvaliadoId da imagem
	 * @throws Throwable 
	 */
	public void deleteOne(String nome, Long itemAvaliadoId) throws Throwable {
		log.debug("Request to delete a Imagem : {} / {}", itemAvaliadoId, nome);
		deleteFile(itemAvaliadoId, nome);
	}

	/**
	 * Delete conteiner of imagens itemAvaliadoId.
	 *
	 * @param itemAvaliadoId itemAvaliadoId da imagem
	 * @throws Throwable 
	 */
	public void deleteConteiner(Long itemAvaliadoId) throws Throwable {
		log.debug("Request to delete a Conteiner of Imagens : {}", itemAvaliadoId);
        // Container name must be lower case.
        CloudBlobContainer container = getContainer(df.format(itemAvaliadoId));

       	container.delete();
	}

	
	private void uploadFile(ImagemDTO imagemDTO) throws Throwable  {
        // Container name must be lower case.
        CloudBlobContainer container = getContainer(df.format(imagemDTO.getAvaliacaoId()));
        container.createIfNotExists();

        // Upload an image file.
        CloudBlockBlob blob = container.getBlockBlobReference(imagemDTO.getNome());
        
        HashMap<String, String> meta = new HashMap<>();
        meta.put("mimeType", imagemDTO.getMimeType());
        meta.put("size", Integer.toString(imagemDTO.getArquivo().length));

		blob.setMetadata(meta);
        InputStream inputStream = new ByteArrayInputStream(imagemDTO.getArquivo());
		blob.upload(inputStream, imagemDTO.getArquivo().length);
	}
	
	private ImagemDTO downloadFile(Long itemAvaliadoId, String nome) throws Throwable  {
        // Container name must be lower case.
        CloudBlobContainer container = getContainer(df.format(itemAvaliadoId));

        // Upload an image file.
        CloudBlockBlob blob = container.getBlockBlobReference(nome);

        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setNome(nome);
        imagemDTO.setAvaliacaoId(itemAvaliadoId);
        blob.downloadAttributes();
        Map<String, String> meta = blob.getMetadata();

        imagemDTO.setMimeType(meta.get("mimeType"));
        imagemDTO.setSize(Long.parseLong(meta.get("size")));
        // Download the image file.
        imagemDTO.setArquivo(new byte[imagemDTO.getSize().intValue()]);
        blob.downloadToByteArray(imagemDTO.getArquivo(), 0);
        
        return imagemDTO;
	}

	private void deleteFile(Long itemAvaliadoId, String nome) throws Throwable  {
        // Container name must be lower case.
        CloudBlobContainer container = getContainer(df.format(itemAvaliadoId));

        // Upload an image file.
        CloudBlockBlob blob = container.getBlockBlobReference(nome);

        blob.delete();
        
        if (!container.listBlobs().iterator().hasNext()) {
        	container.delete();
        }
	}
	
	private CloudBlobContainer getContainer(String nome) throws Throwable {
		String storageConnectionString =
				"DefaultEndpointsProtocol=" + defaultEndpointsProtocol + ";"
				+ "AccountName=" + accountName + ";"
				+ "AccountKey=" + accountKey;
		CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);

        CloudBlobClient serviceClient = account.createCloudBlobClient();
        
        // Container name must be lower case.
        return serviceClient.getContainerReference(nome);
	}
}
