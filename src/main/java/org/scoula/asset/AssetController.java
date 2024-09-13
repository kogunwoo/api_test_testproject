package org.scoula.asset;

import lombok.extern.slf4j.Slf4j;
import org.scoula.dto.assetDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/asset")
@PropertySource({"classpath:/application.properties"})
public class AssetController {

    @Value("${asset.key}")
    private String apiKey;


    private final RestTemplate restTemplate;

    public AssetController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("")
    public ResponseEntity<String> getAssetData() {
        try {
            // API 호출
            String key = "9IhpFVlXDAZ2Jyhne9THkTF2opzaS7h21DAwbHda0JcqG%2BGzSVHaGfyu9O3NtaEuXKCaBoOvH6WLl0EElbgPHg%3D%3D";
//            key ="9IhpFVlXDAZ2Jyhne9THkTF2opzaS7h21DAwbHda0JcqG+GzSVHaGfyu9O3NtaEuXKCaBoOvH6WLl0EElbgPHg==";


            String apiUrl= "https://apis.data.go.kr/1160100/service/GetBondSecuritiesInfoService/getBondPriceInfo?"
                     + "serviceKey="+ key + "&pageNo=1&numOfRows=10&resultType=json";

            System.out.println(apiUrl);
            String response = restTemplate.getForObject(apiUrl, String.class);

            // 응답 내용을 로그로 출력
            log.info("Response from API: " + response);

            // 성공적으로 받은 응답을 그대로 반환
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error occurred while fetching data from API", e);
            return new ResponseEntity<>("Error fetching data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}