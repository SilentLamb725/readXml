package com.company.sax;

import com.company.Book;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class SaxTest {
    public static void main(String[] args) {
        createXmlBySax();
    }

    public static List<Book> readXmlBySax() {
        SaxParseHandler handler = null;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            // 创建SAXParseHandler对象
            handler = new SaxParseHandler();
//            DefaultHandler defaultHandler = new DefaultHandler();
            parser.parse("book.xml", handler);
            System.out.println("~!~!~! 共有" + handler.getBooks().size() + "本书、");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler.getBooks();
    }

    public static void createXmlBySax() {
        List<Book> bookList = readXmlBySax();
        // 生成xml
        SAXTransformerFactory stf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        try {
            TransformerHandler handler = stf.newTransformerHandler();
            Transformer transformer = handler.getTransformer();
            // 设置xml的编码和换行
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            File file = new File("saxBook.xml");
            if(!file.exists()) {
                file.createNewFile();
            }
            Result result = new StreamResult(new FileOutputStream(file));
            handler.setResult(result);
            // 利用handler进行xml文件内容的编写
            handler.startDocument();
            AttributesImpl attr = new AttributesImpl();
            handler.startElement("", "", "bookstore", attr);

            for(Book book : bookList) {
                attr.clear();
                attr.addAttribute("", "", "id", "", book.getId());
                handler.startElement("", "", "book", attr);

                attr.clear();
                handler.startElement("", "", "name", attr);
                handler.characters(book.getName().toCharArray(), 0, book.getName().length());
                handler.endElement("", "", "name");

                handler.startElement("", "", "year", attr);
                handler.characters(book.getYear().toCharArray(), 0, book.getYear().length());
                handler.endElement("", "", "year");

                handler.startElement("", "", "price", attr);
                handler.characters(book.getPrice().toCharArray(), 0, book.getPrice().length());
                handler.endElement("", "", "price");

                handler.endElement("", "", "book");
            }
            handler.endElement("", "", "bookstore");
            handler.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
