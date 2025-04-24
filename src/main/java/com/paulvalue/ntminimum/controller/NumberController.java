package com.paulvalue.ntminimum.controller;

import com.paulvalue.ntminimum.parser.ExcelParser;
import com.paulvalue.ntminimum.service.NumberService;
import com.paulvalue.ntminimum.validation.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class NumberController {
    private final NumberService numberService;
    private final ExcelParser excelParser;

    public NumberController(NumberService numberService, ExcelParser excelParser) {
        this.numberService = numberService;
        this.excelParser = excelParser;
    }

    @PostMapping(value ="/findNthMin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Find Nth minimum number")
    public ResponseEntity<Integer> findNthMin(
            @Parameter(
                    name = "file",
                    description = "XLSX file with numbers",
                    required = true,
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestParam("file") MultipartFile file,
            @RequestParam("n") int n) {

        ValidationUtils.validateInput(file, n);
        var numbers = excelParser.parse(file);
        ValidationUtils.validateNValue(n, numbers.size());

        return ResponseEntity.ok(numberService.findNthMinimum(numbers, n));
    }
}
