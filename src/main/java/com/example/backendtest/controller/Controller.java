package com.example.backendtest.controller;
import com.example.backendtest.service.service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final service Service;

    @Autowired
    public Controller(service Service) {
        this.Service = Service;
    }

    @Operation(
            summary = "Поиск N-ого меньшего числа в XLSX файле",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Число найдено"),
                    @ApiResponse(responseCode = "400", description = "Неверный ввод или формат файла"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка")
            }
    )
    @GetMapping("/nth-min")
    public ResponseEntity<Long> getNthMin(
            @Parameter(required = true)
            @RequestParam String filePath,

            @Parameter(required = true)
            @RequestParam int n
    ) {
        try {
            long result = Service.findNthMin(filePath, n);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
