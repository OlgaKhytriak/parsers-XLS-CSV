package com.epam.fivethreads.data.utils.parsersXLSandCSV;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ParserXLS extends AbstractParser {
    private String filePath;

    public   <T> List<T> parseToList(String filePath, Class<T> clazz) {
        this.filePath=filePath;
        List<T> instancesList = new ArrayList<T>();
        createTitleMap(clazz);
        Iterator<Row> rowIterator = moveToSecondRow();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cells = row.iterator();
            instancesList.add((T) createInstanceForRow(clazz, cells));
        }
        return instancesList;
    }

    private Iterator<Row> moveToSecondRow() {
        Sheet sheet = createSheet();
        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next();
        } else {
            System.out.println("Empty file");
        }
        return rowIterator;
    }

    private <T> T createInstanceForRow(Class<T> clazz, Iterator<Cell> cells) {
        T currentInstance = createEmptyInstance(clazz);
        while (cells.hasNext()) {
            Cell cell = cells.next();
            Integer cellNumber = cell.getColumnIndex();
            String titleName = titleMap.get(cellNumber);
            if (titleName != null) {
                String currentCellValue = readCell(cell);
                setValueIntoField(currentInstance, currentCellValue, titleName);
            }
        }
        return currentInstance;
    }


    private void createTitleMap(Class clazz) {
        Sheet sheet = createSheet();
        Iterator<Row> rowIterator = sheet.iterator();
        Row row = rowIterator.next();
        Iterator<Cell> cells = row.iterator();
        while (cells.hasNext()) {
            Cell cell = cells.next();
            String title = readCell(cell);
            if (isTitleClassField(title, clazz)) {
                titleMap.put(cell.getColumnIndex(), title);
            }
        }
    }

    private Sheet createSheet() {
        InputStream inputStream = null;
        HSSFWorkbook hssfWorkbook = null;
        try {
            inputStream = new FileInputStream(filePath);
            hssfWorkbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = hssfWorkbook.getSheetAt(0);
        return sheet;
    }

    private String readCell(Cell cell) {
        String result = "";
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:
                result += cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                result += cell.getNumericCellValue();
                break;

            case Cell.CELL_TYPE_FORMULA:
                result += cell.getNumericCellValue();
                break;
            default:
                result += "";
                break;
        }
        return result;
    }
}
