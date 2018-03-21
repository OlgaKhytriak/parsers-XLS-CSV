package com.epam.fivethreads.data.utils.parsersXLSandCSV;

import com.epam.fivethreads.data.utils.parsersXLSandCSV.anotations.ParserElement;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractParser {
    protected Map<Integer, String> titleMap = new HashMap<>();

    public abstract <T> List<T> parseToList(String filePath, Class<T> clazz);

    public <T> T parse(String filePath, Class<T> containerClazz){
        Class fieldClass=null;
        T currentInstance = createEmptyInstance(containerClazz);
        Field[] fields = containerClazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(ParserElement.class)) {
                Type genericFieldType = fields[i].getGenericType();
                if (genericFieldType instanceof ParameterizedType) {
                    ParameterizedType pType = (ParameterizedType) genericFieldType;
                    Type[] fieldArgTypes = pType.getActualTypeArguments();
                    for (Type fieldArgType : fieldArgTypes){
                        fieldClass = (Class) fieldArgType;
                    }
                }
                List<?> parsedList = parseToList(filePath, fieldClass);
                setField(currentInstance, fields[i], parsedList);
                continue;
            }

        }
        return currentInstance;
    }

    protected <T> T createEmptyInstance(Class<T> clazz) {
        T currentInstance = null;
        try {
            currentInstance = (T) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Error in class instance creation");
        }
        return currentInstance;
    }

    protected <T> void setField(Object obj, Field field, T value) {
        try {
            Class<?> clazz = obj.getClass();
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            System.err.println("In FieldsInvoker method  exception. Set is failed");
        }

    }

    protected <T> void setValueIntoField(T currentInstance, String currentCellValue, String titleName) {
        Field[] fields = currentInstance.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String anotName = getAnnotationName(fields[i]);
            if (titleName.equals(anotName)) {
                setField(currentInstance, fields[i], currentCellValue);
            }
        }
    }

    private String getAnnotationName(Field field) {
        String anotName = null;
        if (field.isAnnotationPresent(ParserElement.class)) {
            ParserElement fieldAnnotations = field.getDeclaredAnnotation(ParserElement.class);
            anotName = fieldAnnotations.value();

        }
        return anotName;
    }

    protected boolean isTitleClassField(String title, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String anotName = getAnnotationName(fields[i]);
            if (title.equals(anotName)) {
                return true;
            }
        }
        return false;
    }
}
