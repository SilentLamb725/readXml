package com.company.dom;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomTest {

    public static DocumentBuilder getDocumentBuilder() {
        DocumentBuilder db = null;
        try {
            // 加载xml文件
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db;
    }

    public static void main(String[] args) {
        createXMLByDom();
    }

    /**
     * 不知道book的属性
     */
    public static void readUnknownXml() {
        try {
            Document document = getDocumentBuilder().parse("book.xml");

            // 解析xml文件
            NodeList nodeList = document.getElementsByTagName("book");
            for(int i = 0; i < nodeList.getLength(); i++) {
                System.out.println("===============下面开始遍历第" + (i+1) + "本书：================");
                Node node = nodeList.item(i);
                NamedNodeMap attrs = node.getAttributes();
                for(int j = 0; j < attrs.getLength(); j++) {
                    Node attrNode = attrs.item(j);
                    String nodeName = attrNode.getNodeName();
                    System.out.println("属性名为：" + nodeName);
                    String nodeValue = attrNode.getNodeValue();
                    System.out.println("属性值为：" + nodeValue);
                }
                NodeList childNodes = node.getChildNodes();
                for(int k = 0; k < childNodes.getLength(); k++) {
                    if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        String nodeName = childNodes.item(k).getNodeName();
//                        String nodeValue = childNodes.item(k).getFirstChild().getNodeValue();
                        String nodeValue = childNodes.item(k).getTextContent();
                        System.out.print("节点名为：" + nodeName);
                        System.out.println(" 节点值为：" + nodeValue);
                    }
                }
                System.out.println("===============结束遍历===============");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 知道book有且只有一个id属性
     */
    private static void readKnownXml() {
        try {
            // 加载xml文件
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("book.xml");

            // 解析xml文件
            NodeList nodeList = document.getElementsByTagName("book");
            for(int i = 0; i < nodeList.getLength(); i++) {
                System.out.println("===============第" + (i+1) + "本书：================");
                Element element = (Element) nodeList.item(i);
                String attrValue = element.getAttribute("id");
                System.out.println("属性名为：id");
                System.out.println("属性值为: " + attrValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成xml
     */
    public static void createXMLByDom() {
        DocumentBuilder db = getDocumentBuilder();
        Document document = db.newDocument();
        document.setXmlStandalone(true);
        Element element = document.createElement("bookstore");
        // 向bookstore根节点添加子节点book
        Element book = document.createElement("book");
        Element name = document.createElement("name");
        name.setTextContent("小王子");
        book.appendChild(name);
        book.setAttribute("id", "1");
        element.appendChild(book);
        document.appendChild(element);

        // 将树结构转换为xml文件
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File("domBook.xml")));
            System.out.println("生成成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
