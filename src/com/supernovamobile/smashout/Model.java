package com.supernovamobile.smashout;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.util.Log;

public class Model extends Mesh {
	
	boolean mLoaded = false;
	
	public Model(int fileResource, Context context, String name) throws IOException, ParserConfigurationException, SAXException {
		mName = mName + ":" + name;
		
		InputStream fis = context.getResources().openRawResource(fileResource);
		
		SAXParserFactory 	saxParserFactory 	= SAXParserFactory.newInstance();
		SAXParser 			saxParser 			= saxParserFactory.newSAXParser();
		XMLReader 			xmlReader 			= saxParser.getXMLReader();
		ModelFormatHandler	modelHandler		= new ModelFormatHandler();
		
		xmlReader.setContentHandler(modelHandler);
		
		InputSource inputSource = new InputSource(fis);
		xmlReader.parse(inputSource);
		
		add(modelHandler.getModel());
		mLoaded = true;
	}
}
