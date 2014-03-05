/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Yeison
 */
public class XMLDocument {                             
    
    public static void addContent(Document pDoc, String pNodeName, String pValue){
        Element instruction = new Element(pNodeName).setText(String.valueOf(pValue));
        pDoc.getRootElement().addContent(instruction);
    }
    
    public static Document createDocument(Element pRoot){
        Document doc = new Document(pRoot);
        return doc;
    }
    
    public static Document createDocument(){
        Document doc = new Document(createElement("root"));
        return doc;
    }
    
    public static Element createElement(String pName){
        Element element = new Element(pName);
        return element;
    }
    
    /**
     * A partir de un documento contenedor de información se crea un archivo XML
     *
     * @param pName - Nombre del archivo XML
     * @param pDoc - Documento contenedor
     */
    public static void writeDocument(String pName, Document pDoc){
        try{            
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());                                    
            
            FileOutputStream file = new FileOutputStream("src/XMLDocuments/" + pName + ".xml");
            out.output(pDoc,file);
            file.flush();
            file.close();
            System.out.println("write succesful");
            //out.output(_doc,System.out);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Lee un archivo XML en un determinado nodo hijo del root
     *
     * @param pXml - Nombre del XML
     * @param pIndex - Index que se desea obtener
     * @return Valor del nodo
     * @throws IOException
     */
    public static NodeList readXML(String pXml, String pPath) throws IOException {                        
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            
            org.w3c.dom.Document dom = db.parse("src/XMLDocuments/" + pXml + ".xml");

            org.w3c.dom.Element doc = dom.getDocumentElement();            
            return doc.getElementsByTagName(pPath);

        } catch (ParserConfigurationException | SAXException pce) {
            System.out.println(pce.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return null;
    }    
    
    public static void addXMLNode(String pXML, String pPath, String pNodeName, String pValue){
        try {
 
            SAXBuilder builder = new SAXBuilder();            

            Document doc = builder.build("src/XMLDocuments/" + pXML + ".xml");
            Element rootNode = doc.getRootElement();

            Element elmnt = new Element(pNodeName).setText(pValue);
            if (pPath.equals("root")){
                rootNode.addContent(elmnt);
            } else {
                
            }
            
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("src/XMLDocuments/" + pXML + ".xml"));

            System.out.println("File updated!");
        } catch (JDOMException ex) {
            Logger.getLogger(XMLDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
		io.printStackTrace();
        }
    }
    
    public static void insertElement(String pXML, String pPath, String pNodeName, String pValue) throws ParserConfigurationException, SAXException, IOException {
        org.w3c.dom.Document dom;
        org.w3c.dom.Element e = null;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        // use factory to get an instance of document builder
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        // parse XML
        dom = db.parse("src/XMLDocuments/" + pXML + ".xml");

        // take the root element
        org.w3c.dom.Element rootEle = dom.getDocumentElement();                

        // create data elements and place them under root
        e = dom.createElement(pNodeName);
        e.appendChild(dom.createTextNode(pValue));
        
        if (pPath.equals("root")){
            rootEle.appendChild(e);
        } else {
            rootEle.getElementsByTagName(pPath).item(0).appendChild(e);
        }                

        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");                        
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");                        

            // send DOM to file
            tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream("src/XMLDocuments/" + pXML + ".xml")));

        } catch (TransformerException | IOException te) {
            System.out.println(te.getMessage());
        }
    }
    
}   

