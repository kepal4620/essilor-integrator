//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.03 at 09:48:07 PM CEST 
//


package essilor.integrator.adapter.domain.getorder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetOrderByPoNumTimestampResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getOrderByPoNumTimestampResult"
})
@XmlRootElement(name = "GetOrderByPoNumTimestampResponse")
public class GetOrderByPoNumTimestampResponse {

    @XmlElement(name = "GetOrderByPoNumTimestampResult")
    protected String getOrderByPoNumTimestampResult;

    /**
     * Gets the value of the getOrderByPoNumTimestampResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetOrderByPoNumTimestampResult() {
        return getOrderByPoNumTimestampResult;
    }

    /**
     * Sets the value of the getOrderByPoNumTimestampResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetOrderByPoNumTimestampResult(String value) {
        this.getOrderByPoNumTimestampResult = value;
    }

}