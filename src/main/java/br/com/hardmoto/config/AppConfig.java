package br.com.hardmoto.config;

import br.com.hardmoto.mapper.CityMapper;
import br.com.hardmoto.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
@ComponentScan(basePackages = {"br.com.hardmoto.service"})
public class AppConfig {

    @Bean
    public CityService cityService() {
        return new CityService();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Logger logger(){
        return Logger.getAnonymousLogger();
    }

    @Bean
    public CityMapper cityMapper(){
        return new CityMapper();
    }
}

