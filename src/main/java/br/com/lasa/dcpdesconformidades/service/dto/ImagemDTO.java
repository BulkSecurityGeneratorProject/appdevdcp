package br.com.lasa.dcpdesconformidades.service.dto;

import java.io.Serializable;

public class ImagemDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2437932352454782374L;

	private String nome;
	private Long size;
	private String mimeType;
	private Long avaliacaoId;
	private byte[] arquivo;

	/**
	 * @return the arquivo
	 */
	public byte[] getArquivo() {
		return arquivo;
	}
	/**
	 * @param arquivo the arquivo to set
	 */
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}
	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}
	/**
	 * @param mimeType the mimeType to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	/**
	 * @return the avaliacaoId
	 */
	public Long getAvaliacaoId() {
		return avaliacaoId;
	}
	/**
	 * @param avaliacaoId the avaliacaoId to set
	 */
	public void setAvaliacaoId(Long avaliacaoId) {
		this.avaliacaoId = avaliacaoId;
	}



}
