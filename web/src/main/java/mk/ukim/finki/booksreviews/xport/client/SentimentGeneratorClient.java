package mk.ukim.finki.booksreviews.xport.client;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SentimentGeneratorClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public SentimentGeneratorClient(@Value("${app.sentiment-analysis-engine.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public String generateSentiment(String reviewDescription) {
        try {
            String requestJson = String.format("{\"reviewDescription\":\"%s\"}", reviewDescription);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
            return restTemplate.postForObject(uri().path("/v1/api/model").build().toUri(), entity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }

}
