package com.company.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxTest {
    public static void main(String[] args) {
        readXmlBySax();
    }

    private static void readXmlBySax() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            // 创建SAXParseHandler对象
            SaxParseHandler handler = new SaxParseHandler();
//            DefaultHandler defaultHandler = new DefaultHandler();
            parser.parse("book.xml", handler);
            System.out.println("~!~!~! 共有" + handler.getBooks().size() + "本书、");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
