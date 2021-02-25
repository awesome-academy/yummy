package com.example.yummy.data.source.remote.utlis

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

private const val UNICODE = "UTF-8"

class XMLDOMParser {
    fun getDocument(xml: String?): Document? {
        val document: Document
        val factory = DocumentBuilderFactory.newInstance()
        try {
            val db = factory.newDocumentBuilder()
            val inputSource = InputSource()
            inputSource.characterStream = StringReader(xml)
            inputSource.encoding = UNICODE
            document = db.parse(inputSource)
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
            return null
        }
        return document
    }

    fun getValue(element: Element, name: String?) =
        getTextNodeValue(element.getElementsByTagName(name).item(0))

    private fun getTextNodeValue(element: Node?): String? {
        var child: Node?
        element?.let {
            if (it.hasChildNodes()) {
                child = element.firstChild
                while (child != null) {
                    if (child?.nodeType == Node.TEXT_NODE) {
                        return child?.nodeValue
                    }
                    child = child?.nextSibling
                }
            }
        }
        return ""
    }
}
