package com.example.backendtest.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

@Service
public class service {
    public static long findNthMin(String filePath, int n) throws IOException {
        if (n <= 0) {
            throw new IllegalArgumentException("N должно быть не отрицательным");
        }

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            PriorityQueue<Long> maxHeap = new PriorityQueue<>((a, b) -> Long.compare(b, a));

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell == null) continue;

                long value;
                switch (cell.getCellType()) {
                    case NUMERIC:
                        value = (long) cell.getNumericCellValue();
                        break;
                    case STRING:
                        try {
                            value = Long.parseLong(cell.getStringCellValue().trim());
                        } catch (NumberFormatException e) {
                            continue;
                        }
                        break;
                    default:
                        continue;
                }

                if (maxHeap.size() < n) {
                    maxHeap.offer(value);
                } else if (value < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.offer(value);
                }
            }

            if (maxHeap.size() < n) {
                throw new IllegalArgumentException("Недостаточно чисел для поиска" + n + "-его минимального");
            }

            return maxHeap.peek();
        }
    }

}
