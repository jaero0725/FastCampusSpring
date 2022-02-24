package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    //yaml을 code에서 사용할 수 있는 방법 @Value 어노테이션을 사용
    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    // 지역검색 메서드
    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq){
        //요청하고자 하는 URI
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        /*  ## Header 셋팅 -> 들어갈 값
            -H "X-Naver-Client-Id: {애플리케이션 등록 시 발급받은 client id 값}" \
            -H "X-Naver-Client-Secret: {애플리케이션 등록 시 발급받은 client secret 값}" -v
         */
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Entity에 header에 담아 요청을 보냄.
        var httpEntity = new HttpEntity<>(headers); //header
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};

        var responseEntity= new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }

    // 이미지를 검색하는 메서드
    public SearchImageRes searchImage(SearchImageReq searchImageReq){
            //요청하고자 하는 URI
            var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                    .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        /*  ## Header 셋팅 -> 들어갈 값
            -H "X-Naver-Client-Id: {애플리케이션 등록 시 발급받은 client id 값}" \
            -H "X-Naver-Client-Secret: {애플리케이션 등록 시 발급받은 client secret 값}" -v
         */

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Entity에 header에 담아 요청을 보냄.
        var httpEntity = new HttpEntity<>(headers); //header
        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};

        var responseEntity= new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }
}
