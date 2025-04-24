package com.paulvalue.ntminimum.parser;

import com.paulvalue.ntminimum.exception.FileProcessingException;
import com.paulvalue.ntminimum.exception.InvalidInputException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelParser {

    public List<Integer> parse(MultipartFile file) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            List<Integer> numbers = processWorkbook(workbook);
            if (numbers.isEmpty()) {
                throw new InvalidInputException("File contains no valid numbers");
            }
            return numbers;
        } catch (IOException e) {
            throw new FileProcessingException("Error processing Excel file", e);
        }
    }

    private List<Integer> processWorkbook(Workbook workbook) {
        List<Integer> numbers = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            processRow(row, numbers);
        }
        return numbers;
    }

    private void processRow(Row row, List<Integer> numbers) {
        Cell cell = row.getCell(0);
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            numbers.add((int) cell.getNumericCellValue());
        }
    }
}
