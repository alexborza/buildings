package com.alexborza.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geoapifyClient", url = "https://api.geoapify.com/v1/geocode")
public interface GeoapifyClient {

    @GetMapping("/search")
    GeoapifyResponse search(@RequestParam("text") String text,
                            @RequestParam("format") String format,
                            @RequestParam("apiKey") String apiKey);
}
