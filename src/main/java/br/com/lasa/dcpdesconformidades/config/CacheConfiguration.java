package br.com.lasa.dcpdesconformidades.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

import br.com.lasa.dcpdesconformidades.domain.enumeration.Authority;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(br.com.lasa.dcpdesconformidades.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.enumeration.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.User.class.getName() + ".avaliacoes", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.User.class.getName() + ".lojas", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Questionario.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Questionario.class.getName() + ".avaliacoesRealizadas", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Questionario.class.getName() + ".grupos", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Loja.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Loja.class.getName() + ".avaliadores", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Loja.class.getName() + ".avaliacoes", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Loja.class.getName() + ".perdaQuebraAcumuladosAnos", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Avaliacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Avaliacao.class.getName() + ".itensAvaliados", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Avaliacao.class.getName() + ".itensPerdaEQuebraAcumulados", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Avaliacao.class.getName() + ".itensAuditados", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.Avaliacao.class.getName() + ".itensComAjusteSolicitados", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao.class.getName() + ".itensAvaliados", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao.class.getName() + ".grupos", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.ItemAvaliado.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.ItemAvaliado.class.getName() + ".anexos", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.GrupoItens.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.GrupoItens.class.getName() + ".itens", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.GrupoItens.class.getName() + ".questionarios", jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.AnexoItem.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.ItemAuditado.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.ItemSolicitadoAjuste.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.lasa.dcpdesconformidades.domain.PerdaQuebraAcumuladosAnoLoja.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
