package com.epam.fivethreads.data.handlers;

import com.epam.fivethreads.data.model.Letter;
import com.epam.fivethreads.data.model.Letters;
import com.epam.fivethreads.data.utils.parserJAXB.JAXBContextProcessor;
import com.epam.fivethreads.data.utils.*;
import com.epam.fivethreads.data.utils.parserJAXB.Object2Xml;
import com.epam.fivethreads.data.utils.parsersXLSandCSV.ParserCSV;
import com.epam.fivethreads.data.utils.parsersXLSandCSV.ParserXLS;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

import static com.epam.fivethreads.constant.Constant.LETTERS_CSV_FILE_PATH;

public class LattersDataCreator {
    private static final Logger LOG = Logger.getLogger(LattersDataCreator.class);
    private final ParserCSV parserCSV ;
    private final ParserXLS parserXLS;
    public LattersDataCreator(){
        parserCSV = new ParserCSV();
        parserXLS = new ParserXLS();
    }

    public Letters getLetters(String filePath) {
        Letters letters = null;
        String fileType = filePath.substring(filePath.length() - 3);
        if (fileType.equals("xml")) {
            letters = readLettersFromXML(filePath);
        } else if (fileType.equals("xls")) {
            letters = readLettersFromXLS(filePath);
        } else if (fileType.equals("csv")) {
            letters = readLettersFromCSV(filePath);
        } else {
            return null;
        }
        setRandomSubjects(letters);
       // LOG.info("\n" + letters.toString());
        return letters;
    }

    private Letters readLettersFromCSV(String filePath) {
        List<Letter> parsedLetters = parserCSV.parseToList(filePath, Letter.class);
        Letters letters = new Letters();
        letters.setLetters(parsedLetters);
        return letters;
    }

    private Letters readLettersFromXLS(String filePath) {
        List<Letter> parsedLetters = parserXLS.parseToList(filePath, Letter.class);
        Letters letters = new Letters();
        letters.setLetters(parsedLetters);
        return letters;
    }

    private Letters readLettersFromXML(String filePath) {
        JAXBContextProcessor jAXBContextProcessor = new JAXBContextProcessor(new Class[]{Letters.class});
        Object2Xml xmlAdapter = new Object2Xml(jAXBContextProcessor);
        Letters letters = xmlAdapter.load(new File(filePath));
        return letters;
    }

    private void setRandomSubjects(Letters letters) {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        for (int i = 0; i < letters.size(); i++) {
            Letter currentLetter = letters.getLetter(i);
            String letterSubject = randomStringGenerator.generate(10);
            currentLetter.setMessageSubject(letterSubject);
        }
    }

}
