package deti.tqs.controller;

import deti.tqs.service.CurrExchangeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/currency")
@Tag(name = "Currency", description = "Operations for currencies")
public class CurrExchangeController {
    private static final Logger logger = LoggerFactory.getLogger(CurrExchangeController.class);

    @Autowired
    private CurrExchangeService currExchangeService;


    @GetMapping("/list")
    public ResponseEntity<List<String>> listCurrencies() {
        logger.info("Currencies list requested");
        return ResponseEntity.ok(currExchangeService.listCurrencies());
    }

    @GetMapping("/exchange")
    public ResponseEntity<Double> exchange(String from, String to) throws Exception {
        return ResponseEntity.ok(currExchangeService.exchange(from, to));
    }
}
