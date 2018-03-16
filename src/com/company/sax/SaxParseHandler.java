package com.company.sax;

import com.company.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxParseHandler extends DefaultHandler {

    private static int bookIndex = 0;

    private static String nodeValue = "";

    private Book book = null;

    public List<Book> getBooks() {
        return books;
    }

    private static List<Book> books = new ArrayList<>();

    /**
     * 用来遍历xml文件的开始标签
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if(qName.equals("book")) {
            bookIndex++;
            System.out.println("============第" + bookIndex +"本书遍历开始");
            book = new Book();
            // 已知book元素下属性的名称，根据属性名称获取属性值
//            String attrValue = attributes.getValue("id");
//            System.out.println("id属性value为：" + attrValue);

            // 不知道book元素下属性的名称以及个数
            for(int i = 0; i < attributes.getLength(); i++) {
                String attrName = attributes.getQName(i);
                String attrValue = attributes.getValue(i);
                System.out.println("第" + (i+1) + "个属性的名称是：" + attrName);
                System.out.println("第" + (i+1) + "个属性的值是：" + attrValue);
                if(attributes.getQName(i).equals("id")) {
                    book.setId(attributes.getValue(i));
                }
            }
        } else if(!qName.equals("bookstore")) {
            System.out.print("节点名是：" + qName);
        }
    }

    /**
     * 用来遍历xml文件的结束标签
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        // 判断一本书是否遍历结束
        if(qName.equals("book")) {
            books.add(book);
            book = null;
            System.out.println("================遍历结束");
        } else if(qName.equals("name")) {
            book.setName(nodeValue);
        } else if(qName.equals("author")) {
            book.setAuthor(nodeValue);
        } else if(qName.equals("year")) {
            book.setYear(nodeValue);
        } else if(qName.equals("price")) {
            book.setPrice(nodeValue);
        } else if(qName.equals("language")) {
            book.setLanguage(nodeValue);
        }
    }

    /**
     * 用来标识解析开始
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX解析开始");
    }

    /**
     * 用来标识解析结束
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX解析结束");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        nodeValue = new String(ch, start, length);
        if(!nodeValue.trim().equals("")) {
            System.out.println("  节点值是：" + nodeValue);
        }
    }
}
