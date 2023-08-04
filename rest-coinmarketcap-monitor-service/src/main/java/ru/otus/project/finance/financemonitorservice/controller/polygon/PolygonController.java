package ru.otus.project.finance.financemonitorservice.controller.polygon;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.finance.financemonitorservice.dto.StockDto;
import ru.otus.project.finance.financemonitorservice.service.polygon.PolygonService;

import java.util.List;

@RestController
@RequestMapping("/api/polygon")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PolygonController {
    PolygonService polygonService;

    @GetMapping("/stock")
    public ResponseEntity<String> getStock() {
        polygonService.getHistoryStocks();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}