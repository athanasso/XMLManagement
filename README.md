# XMLManagement
In this project raw text data are to be converted to an XML document. The suitable schema will
be designed by the developer. The corresponding classes will be derived and the suitable
validators.

## Introduction
The text document, a sample of which is given in the appendix, must be converted to an XML file
with the structure: book, chapters, paragraphs, lines, and statistics.

The statistics are the number of paragraphs, lines, words, and distinct words of the document,
the date/time of the creation of the document, the author name, and the application class name.

The application will be standalone without interaction with the user but will only run various usecases
to demonstrate its functionality.

## Functional requirements
* Parser from txt to XML
* Reader and writer of the XML file
* Creating XML with selected paragraphs from an existing XML
* Creating statistics for an XML
* Provide an XSD for the XML
* Validator
* Integrate all the above to an application

## Project

When you run the project you can choose one or multiple of these options to perform some use cases
* Parse the text file into an XML
* Create and Print Statistics for the book
* Create an XML of the Statistics
* Create an XML with selected paragraphs from an existing XML
* Generate XSD Schema for your statistics XML
* Validate the XML

## Technologies

* Java 17
* Maven
* Jakarta XML Bind, jaxb core and jaxb impl
* Dom and Sax manipulation
* Marshaller and Unmarshaller
* XML Parsers

## Input-Output Files

* raw.txt
* book.xml
* statistics.xml
* book.xsd
* statistics.xsd
* book2.0.xml
