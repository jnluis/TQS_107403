package deti.tqs.ua.HW1.controller;

import deti.tqs.ua.HW1.service.CurrExchangeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/currencies")
@Tag(name = "Currencies", description = "Operations for currencies")
public class CurrExchangeController {
    private static final Logger logger = LoggerFactory.getLogger(CurrExchangeController.class);

    @Autowired
    private CurrExchangeService currExchangeService;


    @GetMapping("/list")
    public ResponseEntity<List<String>> listCurrencies() throws Exception{
        logger.info("Currencies list requested");
        return ResponseEntity.ok(currExchangeService.listCurrencies());
    }

    @GetMapping("/exchange")
    public ResponseEntity<Double> exchange(String from, String to) throws Exception {
        return ResponseEntity.ok(currExchangeService.exchange(from, to));
    }
}