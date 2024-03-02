package ru.biryuleva.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.biryuleva.dto.NumberDto;

@FeignClient(name = "client", url = "${number-generator.url}")
public interface Client {

    @GetMapping(value = "/number")
    NumberDto getNumber();
}
