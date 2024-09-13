package org.scoula.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.scoula.dto.InstallmentSavingsDTO;
import org.scoula.service.InstallmentSavingsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/installment-savings")
@PropertySource("classpath:application.properties")
public class InstallmentSavingsController {

    @Value("${fss.api.key}")
    private String apiKey;

    @Value("${fss.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final InstallmentSavingsService installmentSavingsService;

    public InstallmentSavingsController(InstallmentSavingsService installmentSavingsService) {
        this.restTemplate = new RestTemplate();
        this.installmentSavingsService = installmentSavingsService;
        this.restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    @GetMapping("")
    public ResponseEntity<String> getAssetData() {
        try {
            // API URL에 인증키 추가
            String url = apiUrl + "?auth=" + apiKey + "&topFinGrpNo=020000&pageNo=1";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAcceptCharset(List.of(StandardCharsets.UTF_8));
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // 응답 데이터를 처리하고 DTO 리스트로 변환
            JSONObject jsonResponse = new JSONObject(response.getBody());
            JSONObject jsonResponse2 = jsonResponse.getJSONObject("result");
            JSONArray baseList = jsonResponse2.getJSONArray("baseList");
            JSONArray optionList = jsonResponse2.getJSONArray("optionList");

            List<InstallmentSavingsDTO> productList = new ArrayList<>();

            for (int i = 0; i < baseList.length(); i++) {
                JSONObject product = baseList.getJSONObject(i);
                JSONObject productOptions = optionList.getJSONObject(i);

                InstallmentSavingsDTO installmentSavingsDTO = new InstallmentSavingsDTO(
                        product.getString("fin_co_no"),
                        product.getString("kor_co_nm"),
                        product.getString("fin_prdt_cd"),
                        product.getString("fin_prdt_nm"),
                        productOptions.getString("rsrv_type_nm"),
                        productOptions.getLong("save_trm"),
                        productOptions.getDouble("intr_rate"),
                        productOptions.getDouble("intr_rate2")
                );

                productList.add(installmentSavingsDTO);
            }

            // 서비스 계층을 통해 데이터 저장
            installmentSavingsService.saveProducts(productList);

            return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error fetching data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
