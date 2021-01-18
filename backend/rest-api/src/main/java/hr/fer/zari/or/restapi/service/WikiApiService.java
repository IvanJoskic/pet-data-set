package hr.fer.zari.or.restapi.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WikiApiService {
	private final RestTemplate restTemplate;
	
	public WikiApiService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Cacheable(cacheNames = "images", key = "#wikiHandle")
	public byte[] getImage(String wikiHandle) {
		String url = "https://en.wikipedia.org/api/rest_v1/page/summary/{wiki_handle}";
		
		ObjectMapper objMappr = new ObjectMapper();
		String imageUrl = null;
		byte[] image = null;
		try {
			JsonNode json = objMappr.readTree(this.restTemplate.getForObject(url, String.class, wikiHandle));
			System.out.println(json.path("thumbnail").path("source"));
			imageUrl = json.path("thumbnail").path("source").asText();
			image = this.restTemplate.getForObject(new URI(imageUrl), byte[].class);
		} catch (RestClientException | IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}
}
