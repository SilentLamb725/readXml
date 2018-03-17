package com.company.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class Dom4jTest {

    public static void main(String[] args) {
        readXmlByDom4j();
    }

    private static void readXmlByDom4j() {
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
}
