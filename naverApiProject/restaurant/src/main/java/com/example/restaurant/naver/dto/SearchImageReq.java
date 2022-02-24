package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageReq {

    private String query ="";

    private int display = 1;

    private int start = 1;

    private String sort = "sim";        // 정렬 옵션: sim (유사도순), date (날짜순)

    private String filter = "all";      // 사이즈 필터 옵션: all(전체), large(큰 사이즈), medium(중간 사이즈), small(작은 사이즈)

    //key, value 형태로 만들어서 미리 만들어서 queryParam 사용할 수 있도록 한다,
    public MultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query", query);
        map.add("display", String.valueOf(display));
        map.add("start",String.valueOf(start));
        map.add("sort", sort);
        map.add("filter",filter);

        return map;
    }

}
