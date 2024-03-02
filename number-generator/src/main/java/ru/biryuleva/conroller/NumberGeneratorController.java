package ru.biryuleva.conroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.biryuleva.dto.NumberDto;
import ru.biryuleva.service.NumberGeneratorService;

@RestController
@RequestMapping("/number")
@RequiredArgsConstructor
@Slf4j
public class NumberGeneratorController {

    private final NumberGeneratorService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public NumberDto getTime() {
        log.debug("Получен GET-запрос на получение номера заказа");
        return service.getNumber();
    }
}
