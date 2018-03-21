package com.epam.fivethreads.data.utils.parsersXLSandCSV;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.fivethreads.constant.Constant.CSV_SPLITTER;

public class ParserCSV extends AbstractParser {
    private BufferedReader bufferedReader;
    private String filePath;

    public   <T> List<T> parseToList(String filePath, Class<T> clazz) {
        this.filePath=filePath;
        List<T> instancesList = new ArrayList<T>();
        createTitleMap( clazz);
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] wordsArray = line.split(CSV_SPLITTER);
                instancesList.add((T) createInstanceForRow(clazz, wordsArray));
            }
        } catch (IOException e) {
            System.out.println("Exception in parse()");
        } finally {
            closeFile();
        }
        return instancesList;
    }


    private <T> T createInstanceForRow(Class<T> clazz, String[] wordsArray) {
        T currentInstance = createEmptyInstance(clazz);
        for (int i = 0; i < wordsArray.length; i++) {
            String titleName = titleMap.get(i);
            if (titleName != null) {
                String currentCellValue = wordsArray[i];
                setValueIntoField(currentInstance, currentCellValue, titleName);
            }
        }
        return currentInstance;
    }

    private void createTitleMap(Class clazz) {
        String line = "";
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            line = bufferedReader.readLine();
            if (line != null) {
                String[] wordsArray = line.split(CSV_SPLITTER);
                for (int i = 0; i < wordsArray.length; i++) {
                    String title = wordsArray[i];
                    if (isTitleClassField(title, clazz)) {
                        titleMap.put(i, title);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Exception in createTitleMap()");
        } finally {
            closeFile();
        }
    }

    private void closeFile() {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Can not close file ");
            }
        }
    }
}
