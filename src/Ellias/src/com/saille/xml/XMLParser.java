package com.saille.xml;

import java.io.PrintStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler {
    private XMLTreeNode tree;
    private int level;
    private XMLTreeNode currentNode;
    private boolean nodeEnd = true;
    private boolean started = false;

    public void startDocument() throws SAXException {
        this.tree = new XMLTreeNode();
    }

    public void endDocument() throws SAXException {
        System.out.println("end");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("path")) {
            System.out.println(uri);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
    }
}