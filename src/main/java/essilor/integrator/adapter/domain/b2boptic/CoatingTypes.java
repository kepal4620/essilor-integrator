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
 * <p>Java class for CoatingTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CoatingTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="COLOR"/>
 *     &lt;enumeration value="UV"/>
 *     &lt;enumeration value="ANTIREFLEX"/>
 *     &lt;enumeration value="HARD"/>
 *     &lt;enumeration value="CLEAN"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CoatingTypes")
@XmlEnum
public enum CoatingTypes {

    COLOR,
    UV,
    ANTIREFLEX,
    HARD,
    CLEAN,
    OTHER;

    public String value() {
        return name();
    }

    public static CoatingTypes fromValue(String v) {
        return valueOf(v);
    }

}