package hr.fer.zari.or.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

/**
 * 
 *  
 *  TODO implement a response wrapper
 *  TODO testing of responses
 *
 */

@SpringBootApplication
@EnableCaching
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	
	@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager("images");
	}
}
