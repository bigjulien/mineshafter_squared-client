package com.creatifcubed.simpleapi;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.creatifcubed.simpleapi.exceptions.SimpleDocException;

public class SimpleXMLSettings implements ISimpleSettings {
	private String path;
	private SimpleDoc source;
	
	public SimpleXMLSettings(String path) {
		this.path = path;
		this.load(path);
	}
	
	@Override
	public void load(String path) {
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

	@Override
	public void load() {
		this.load(this.path);
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public void save(String path) {
		this.source.save(path);
	}

	@Override
	public void save() {
		this.source.save(this.path);
	}

	@Override
	public void put(String key, Object value) {
		String[] levels = key.split("\\p{Punct}");
		Document doc = this.source.getDocument();
		
		Node curNode = followPath(levels, doc);
		
		if (curNode instanceof Element) {
			((Element) curNode).setAttribute("value", value.toString());
		}
		
	}

	@Override
	public String getString(String key) {
		return this.getString(key, null);
	}

	@Override
	public String getString(String key, String def) {
		String str = this.get(key);
		if (str == null) {
			return def;
		}
		return str;
	}

	@Override
	public Integer getInt(String key) {
		return this.getInt(key, null);
	}

	@Override
	public Integer getInt(String key, Integer def) {
		String bin = this.get(key);
		if (bin == null) {
			return def;
		}
		Integer x = def;
		try {
			x = Integer.parseInt(bin);
		} catch (NumberFormatException ignor) {
			// ignore
		}
		return x;
	}

	@Override
	public Double getDouble(String key) {
		return this.getDouble(key, null);
	}

	@Override
	public Double getDouble(String key, Double def) {
		String bin = this.get(key);
		if (bin == null) {
			return def;
		}
		Double d = def;
		try {
			d = Double.parseDouble(bin);
		} catch (NumberFormatException ignore) {
			//
		}
		return d;
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

	@Override
	public void putCollection(String key, Collection<Object> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Object> getCollection(String key) {
		throw new UnsupportedOperationException();
	}
}
