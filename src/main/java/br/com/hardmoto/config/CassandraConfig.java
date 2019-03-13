package br.com.hardmoto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(
        basePackages = "br.com.hardmoto.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    private static final Integer PORT = 9042;
    private static final String CONTATCT_POINT = "localhost";
    private static final String KEYSPACE = "hardmoto";


    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster =
                new CassandraClusterFactoryBean();
        cluster.setContactPoints(CONTATCT_POINT);
        cluster.setPort(PORT);
        return cluster;
    }

    @Override
    @Bean
    public CassandraMappingContext cassandraMapping() {
        return new CassandraMappingContext();
    }
}