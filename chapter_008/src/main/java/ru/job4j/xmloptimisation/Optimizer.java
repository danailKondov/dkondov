package ru.job4j.xmloptimisation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Class for XML-XSD-JDBC optimization .
 *
 * @since 19/11/2017
 * @version 1
 */
public class Optimizer {

    /**
     * Connection to DB.
     */
    private Connection connection;

    /**
     * URL to DB.
     */
    private String url;

    /**
     * Number N.
     */
    private int numN;


    /**
     * Mean value for sum of N numbers.
     */
    private double meanValue;

    public static void main(String[] args) {
        String url = "jdbc:sqlite:I:\\Ресурсы Java\\SQLite\\test_db";
        Optimizer optimizer = new Optimizer();

        // инициализируемся через сеттеры согласно условию
        optimizer.setUrl(url);
        int numN;
        if (args.length > 0) {
            numN = Integer.valueOf(args[0]);
        } else {
            numN = 1000000;
        }
        optimizer.setNumN(numN);

        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                optimizer.go();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long timeElapsed = (end - start)/1000/60;
        System.out.println(timeElapsed);
        System.out.println(optimizer.getMeanValue());
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNumN(int numN) {
        this.numN = numN;
    }

    /**
     * Starts optimization.
     */
    public void go() {
        initialiseDB();
        enterNumN();
        createXML();
        transformXMLWithXSLT();
        parseXMLandComputeMeanValue();
    }

    /**
     * Initialises DB by creating table if not exists.
     */
    public void initialiseDB() {
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS test (" +
                    "field INTEGER)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeQuietly(stmt);
        }
    }

    /**
     * Enters numbers to N to DB. Deletes all data before entering.
     */
    public void enterNumN() {
        String ps = "INSERT INTO test (field) VALUES (?)";
        try (Statement st = connection.createStatement();
             PreparedStatement stmt = connection.prepareStatement(ps)) {
            // удаляем предварительно все записи
            st.execute("DELETE FROM test");

            connection.setAutoCommit(false);
            // вводим новые записи по числу N
            for (int i = 1; i <= numN; i++) {
                stmt.setInt(1, i);
                stmt.addBatch();
            }
            stmt.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates XML-file with data from DB in root folder.
     */
    public void createXML() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT field FROM test");
            formXML(rs);
        } catch (SQLException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            Util.closeQuietly(stmt);
            Util.closeQuietly(rs);
        }
    }

    /**
     * Transforms ResultSet to XML file.
     * @param rs ResultSet
     * @throws ParserConfigurationException
     * @throws SQLException
     */
    private void formXML(ResultSet rs) throws ParserConfigurationException, SQLException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element entries = document.createElement("entries");
        document.appendChild(entries);

        while(rs.next()) {
            Element entry = document.createElement("entry");
            entries.appendChild(entry);
            Element field = document.createElement("field");
            entry.appendChild(field);
            field.appendChild(document.createTextNode(String.valueOf(rs.getInt(1))));
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("1.xml"));
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transformas XML to other XML with XSLT.
     */
    public void transformXMLWithXSLT() {
        StreamSource styleSource = new StreamSource(new File("testXSL2.xsl"));
        StreamSource source = new StreamSource(new File("1.xml"));
        StreamResult result = new StreamResult(new File("2.xml"));
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse second XML and compute mean value.
     */
    public void parseXMLandComputeMeanValue() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("2.xml");

            XPathFactory pathFactory = XPathFactory.newInstance();
            XPath xpath = pathFactory.newXPath();

            XPathExpression expr = xpath.compile("//entries/entry/@field");
            NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

            double sum = 0;
            for (int i = 0; i < nodes.getLength(); i++) {
                Node n = nodes.item(i);
                sum += Integer.parseInt(n.getTextContent());
            }
            meanValue = sum/numN;
            System.out.println("Mean value: " + meanValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeQuietly(connection);
        }
    }

    public double getMeanValue() {
        return meanValue;
    }
}
