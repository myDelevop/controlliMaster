package it.bz.prov.controlli.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication(scanBasePackages= {"it.bz.prov.controlli"})
public class ControlliEndpointApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ControlliEndpointApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ControlliEndpointApplication.class);
    }

}
