package it.bz.prov.controlli.config;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories({"it.bz.prov.controlli"})
@EntityScan({"it.bz.prov.controlli"})
@Configuration
public class PersistenceConfig {
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = null;
		
		try {
			Properties props = new Properties();
			Context initialContext = new InitialContext(props);
			dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/OPPAB_CONTROLLI_DS");
			
			if(dataSource != null) {
				dataSource.getConnection();
			}
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return dataSource;
	}

}
