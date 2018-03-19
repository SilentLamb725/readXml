package com.company.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

public class Dom4jTest {

    public static void main(String[] args) {
        createXmlByDom4j();
    }

    public static void readXmlByDom4j() {
        try {
            // 加载文件
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File("book.xml"));
            // 获取根节点
            Element element = document.getRootElement();
            Iterator it = element.elementIterator();
            while(it.hasNext()) {
                Element book = (Element) it.next();
                List<Attribute> attrsList = book.attributes();
                for(Attribute attribute : attrsList) {
                    System.out.println("节点属性：" + attribute.getName());
                    System.out.println("节点值：" + attribute.getValue());
                }
                Iterator iterator = book.elementIterator();
                while(iterator.hasNext()) {
                    Element bookChild = (Element) iterator.next();
                    String tagName = bookChild.getName();
                    String tagValue = bookChild.getStringValue();
                    System.out.print("节点名为：" + tagName);
                    System.out.println("----节点值为：" + tagValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createXmlByDom4j() {
        // 创建document对象，代表整个xml
        Document document = DocumentHelper.createDocument();
        Element rss = document.addElement("rss");
        rss.addAttribute("version", "2.0");
        Element channel = rss.addElement("channel");
        Element title = channel.addElement("title");
        title.setText("这是标题");

        try {
            // 生成xml文件
            File file = new File("dom4jRss.xml");
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            // 设置是否转义，默认true
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
