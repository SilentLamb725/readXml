package com.company;

import com.company.dom.DomTest;
import com.company.dom4j.Dom4jTest;
import com.company.jdom.JdomTest;
import com.company.sax.SaxTest;

public class Main {
    public static void main(String[] args) {
        long cur = System.currentTimeMillis();
        long time = 0;
        DomTest.readUnknownXml();
        time = System.currentTimeMillis() - cur;
        System.out.println("----------------------------Dom解析xml耗时：" + time);

        cur = System.currentTimeMillis();
        Dom4jTest.readXmlByDom4j();
        time = System.currentTimeMillis() - cur;
        System.out.println("----------------------------Dom4j解析xml耗时：" + time);

        cur = System.currentTimeMillis();
        JdomTest.readXmlByJdom();
        time = System.currentTimeMillis() - cur;
        System.out.println("----------------------------JDom解析xml耗时：" + time);

        cur = System.currentTimeMillis();
        SaxTest.readXmlBySax();
        time = System.currentTimeMillis() - cur;
        System.out.println("----------------------------Sax解析xml耗时：" + time);
    }
}
