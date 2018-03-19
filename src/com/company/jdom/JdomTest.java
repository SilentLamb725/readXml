package com.company.jdom;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.List;

public class JdomTest {

    public static void main(String[] args) {
        createXmlByJDom();
    }

    public static void readXmlByJDom() {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            InputStream in = new FileInputStream("book.xml");

            // 做编码转换的处理
            InputStreamReader isr = new InputStreamReader(in, "UTF-8");
            Document document = saxBuilder.build(isr);
            // 获取根节点
            Element rootElement = document.getRootElement();

            // 获取根节点下的子节点集合
            List<Element> list = rootElement.getChildren();
            for(Element element : list) {
                System.out.println("===========开始解析第" + (list.indexOf(element) + 1) + "本书");

                List<Attribute> attrList = element.getAttributes();

                // 遍历属性集合，获取属性值
                for(Attribute attribute : attrList) {
                    String attrName = attribute.getName();
                    String attrValue = attribute.getValue();
                    System.out.print("属性名为：" + attrName);
                    System.out.println("----属性值为：" + attrValue);
                }

                // 遍历子节点
                List<Element> tagList = element.getChildren();
                for(Element tag : tagList) {
                    String tagName = tag.getName();
                    String tagValue = tag.getValue();
                    System.out.print("节点名为：" + tagName);
                    System.out.println("---节点值为：" + tagValue);
                }
                System.out.println("===========结束解析第" + (list.indexOf(element) + 1) + "本书");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createXmlByJDom() {
        Element rss = new Element("rss");
        rss.setAttribute("version", "2.0");
        Document document = new Document(rss);
        Element channel = new Element("channel");
        rss.addContent(channel);
        Element title = new Element("title");
        title.setText("这是标题");
        channel.addContent(title);

        Format format = Format.getPrettyFormat();
        // 将document对象转换成xml文件
        XMLOutputter outputter = new XMLOutputter(format);
        try {
            outputter.output(document, new FileOutputStream(new File("jDomRss.xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
