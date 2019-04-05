package br.com.lasa.dcpdesconformidades.web.rest.vm;

import javax.validation.constraints.NotNull;

public class IniciarAvaliacaoInputVM {

    @NotNull
    private Long idLoja;
    private Double latitude;
    private Double longitude;

    @NotNull
    private String nomeResponsavelLoja;

    @NotNull
    private Integer prontuarioResponsavelLoja;

    public Long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNomeResponsavelLoja() {
        return nomeResponsavelLoja;
    }

    public void setNomeResponsavelLoja(String nomeResponsavelLoja) {
        this.nomeResponsavelLoja = nomeResponsavelLoja;
    }

    public Integer getProntuarioResponsavelLoja() {
        return prontuarioResponsavelLoja;
    }

    public void setProntuarioResponsavelLoja(Integer prontuarioResponsavelLoja) {
        this.prontuarioResponsavelLoja = prontuarioResponsavelLoja;
    }
}
