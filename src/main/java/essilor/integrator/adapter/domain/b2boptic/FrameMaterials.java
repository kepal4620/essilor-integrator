//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 07:48:00 PM CEST 
//


package essilor.integrator.adapter.domain.b2boptic;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FrameMaterials.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FrameMaterials">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="METAL"/>
 *     &lt;enumeration value="PLASTIC"/>
 *     &lt;enumeration value="OPTYL"/>
 *     &lt;enumeration value="NYLOR"/>
 *     &lt;enumeration value="DRILLED"/>
 *     &lt;enumeration value="SPECIAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FrameMaterials")
@XmlEnum
public enum FrameMaterials {

    METAL,
    PLASTIC,
    OPTYL,
    NYLOR,
    DRILLED,
    SPECIAL;

    public String value() {
        return name();
    }

    public static FrameMaterials fromValue(String v) {
        return valueOf(v);
    }

}