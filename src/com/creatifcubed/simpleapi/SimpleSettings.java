package com.creatifcubed.simpleapi;

import java.io.FileNotFoundException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.creatifcubed.simpleapi.exceptions.SimpleDocException;

public class SimpleSettings {
	private String path;
	private SimpleDoc source;
	
	public SimpleSettings(String path) throws SimpleDocException {
		this.load(path);
	}
	
	public void load(String path) throws SimpleDocException {
		this.path = path;
		try {
			this.source = new SimpleDoc(path);
		} catch (SimpleDocException ex) {
			if (FileNotFoundException.class.isInstance(ex.getCause())) {
				this.source = new SimpleDoc();
				Document doc = this.source.getDocument();
				doc.appendChild(doc.createElement("root"));
			}
		}
	}
	
	public void save(String path) throws SimpleDocException {
		this.source.save(path);
	}
	
	public SimpleSettings put(String key, String value) {
		String[] levels = key.split("\\p{Punct}");
		Document doc = this.source.getDocument();
		
		Node curNode = followPath(levels, doc);
		
		if (curNode instanceof Element) {
			((Element) curNode).setAttribute("value", value);
		}
		
		return this;
	}
	
	public String get(String key) {
		String[] levels = key.split("\\p{Punct}");
		Document doc = this.source.getDocument();
		
		Node curNode = followPath(levels, doc);
		
		if (curNode instanceof Element) {
			Element e = (Element) curNode;
			if (e.hasAttribute("value")) {
				return e.getAttribute("value");
			}
		}
		
		return null;
	}
	
	private static Node followPath(String[] path, Document doc) {
		Node root = doc.getDocumentElement();
		Node curNode = root;
		
		for (String separator : path) {
			Node nextNode = findNode(curNode.getChildNodes(), separator);
			if (nextNode == null) {
				nextNode = doc.createElement(separator);
				curNode.appendChild(nextNode);
				curNode = nextNode;
			}
			curNode = nextNode;
		}
		
		return curNode;
	}
	
	private static Node findNode(NodeList list, String key) {
		if (list != null) {
			for (int i = 0; i < list.getLength(); i++) {
				Node n = list.item(i);
				if (n instanceof Element) {
					Element e = (Element) n;
					if (e.getTagName().equals(key)) {
						return e;
					}
				}
			}
		}
		return null;
	}
}
