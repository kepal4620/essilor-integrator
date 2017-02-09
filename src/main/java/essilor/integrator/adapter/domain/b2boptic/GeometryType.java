//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 07:48:00 PM CEST 
//


package essilor.integrator.adapter.domain.b2boptic;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for GeometryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GeometryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="diameter" type="{}Diameter"/>
 *         &lt;element name="decentration" type="{}Decentration" minOccurs="0"/>
 *         &lt;element name="waveFrontOptimisation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="thickness" maxOccurs="unbounded" minOccurs="0">
 *               &lt;complexType>
 *                 &lt;simpleContent>
 *                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>float">
 *                     &lt;attribute name="reference" use="required" type="{}ThicknessReferences" />
 *                   &lt;/extension>
 *                 &lt;/simpleContent>
 *               &lt;/complexType>
 *             &lt;/element>
 *             &lt;element name="thicknessReduction" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="thicknessReductionThin" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="curve" type="{}Curve" minOccurs="0"/>
 *         &lt;element name="inset" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="upset" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="designType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="A"/>
 *               &lt;enumeration value="B"/>
 *               &lt;enumeration value="C"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="progressionLength" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *           &lt;sequence>
 *             &lt;element name="progressionFarVisionShiftDistance" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *             &lt;element name="progressionNearVisionShiftDistance" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *             &lt;element name="progressionMiddleVisionShiftDistance" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;element name="progressionZoneCalculationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeometryType", propOrder = {
    "diameter",
    "decentration",
    "waveFrontOptimisation",
    "thickness",
    "thicknessReduction",
    "thicknessReductionThin",
    "curve",
    "inset",
    "upset",
    "designType",
    "progressionLength",
    "progressionFarVisionShiftDistance",
    "progressionNearVisionShiftDistance",
    "progressionMiddleVisionShiftDistance",
    "progressionZoneCalculationType"
})
public class GeometryType {

    @XmlElement(required = true)
    protected Diameter diameter;
    protected Decentration decentration;
    @XmlElement(defaultValue = "false")
    protected Boolean waveFrontOptimisation;
    protected List<GeometryType.Thickness> thickness;
    @XmlElement(defaultValue = "false")
    protected Boolean thicknessReduction;
    @XmlElement(defaultValue = "false")
    protected Boolean thicknessReductionThin;
    protected Curve curve;
    protected Float inset;
    protected Float upset;
    protected String designType;
    protected Float progressionLength;
    protected Float progressionFarVisionShiftDistance;
    protected Float progressionNearVisionShiftDistance;
    protected Float progressionMiddleVisionShiftDistance;
    protected String progressionZoneCalculationType;

    /**
     * Gets the value of the diameter property.
     * 
     * @return
     *     possible object is
     *     {@link Diameter }
     *     
     */
    public Diameter getDiameter() {
        return diameter;
    }

    /**
     * Sets the value of the diameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Diameter }
     *     
     */
    public void setDiameter(Diameter value) {
        this.diameter = value;
    }

    /**
     * Gets the value of the decentration property.
     * 
     * @return
     *     possible object is
     *     {@link Decentration }
     *     
     */
    public Decentration getDecentration() {
        return decentration;
    }

    /**
     * Sets the value of the decentration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Decentration }
     *     
     */
    public void setDecentration(Decentration value) {
        this.decentration = value;
    }

    /**
     * Gets the value of the waveFrontOptimisation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWaveFrontOptimisation() {
        return waveFrontOptimisation;
    }

    /**
     * Sets the value of the waveFrontOptimisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWaveFrontOptimisation(Boolean value) {
        this.waveFrontOptimisation = value;
    }

    /**
     * Gets the value of the thickness property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the thickness property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThickness().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GeometryType.Thickness }
     * 
     * 
     */
    public List<GeometryType.Thickness> getThickness() {
        if (thickness == null) {
            thickness = new ArrayList<GeometryType.Thickness>();
        }
        return this.thickness;
    }

    /**
     * Gets the value of the thicknessReduction property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isThicknessReduction() {
        return thicknessReduction;
    }

    /**
     * Sets the value of the thicknessReduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setThicknessReduction(Boolean value) {
        this.thicknessReduction = value;
    }

    /**
     * Gets the value of the thicknessReductionThin property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isThicknessReductionThin() {
        return thicknessReductionThin;
    }

    /**
     * Sets the value of the thicknessReductionThin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setThicknessReductionThin(Boolean value) {
        this.thicknessReductionThin = value;
    }

    /**
     * Gets the value of the curve property.
     * 
     * @return
     *     possible object is
     *     {@link Curve }
     *     
     */
    public Curve getCurve() {
        return curve;
    }

    /**
     * Sets the value of the curve property.
     * 
     * @param value
     *     allowed object is
     *     {@link Curve }
     *     
     */
    public void setCurve(Curve value) {
        this.curve = value;
    }

    /**
     * Gets the value of the inset property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getInset() {
        return inset;
    }

    /**
     * Sets the value of the inset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setInset(Float value) {
        this.inset = value;
    }

    /**
     * Gets the value of the upset property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getUpset() {
        return upset;
    }

    /**
     * Sets the value of the upset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setUpset(Float value) {
        this.upset = value;
    }

    /**
     * Gets the value of the designType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignType() {
        return designType;
    }

    /**
     * Sets the value of the designType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignType(String value) {
        this.designType = value;
    }

    /**
     * Gets the value of the progressionLength property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getProgressionLength() {
        return progressionLength;
    }

    /**
     * Sets the value of the progressionLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setProgressionLength(Float value) {
        this.progressionLength = value;
    }

    /**
     * Gets the value of the progressionFarVisionShiftDistance property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getProgressionFarVisionShiftDistance() {
        return progressionFarVisionShiftDistance;
    }

    /**
     * Sets the value of the progressionFarVisionShiftDistance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setProgressionFarVisionShiftDistance(Float value) {
        this.progressionFarVisionShiftDistance = value;
    }

    /**
     * Gets the value of the progressionNearVisionShiftDistance property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getProgressionNearVisionShiftDistance() {
        return progressionNearVisionShiftDistance;
    }

    /**
     * Sets the value of the progressionNearVisionShiftDistance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setProgressionNearVisionShiftDistance(Float value) {
        this.progressionNearVisionShiftDistance = value;
    }

    /**
     * Gets the value of the progressionMiddleVisionShiftDistance property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getProgressionMiddleVisionShiftDistance() {
        return progressionMiddleVisionShiftDistance;
    }

    /**
     * Sets the value of the progressionMiddleVisionShiftDistance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setProgressionMiddleVisionShiftDistance(Float value) {
        this.progressionMiddleVisionShiftDistance = value;
    }

    /**
     * Gets the value of the progressionZoneCalculationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgressionZoneCalculationType() {
        return progressionZoneCalculationType;
    }

    /**
     * Sets the value of the progressionZoneCalculationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgressionZoneCalculationType(String value) {
        this.progressionZoneCalculationType = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>float">
     *       &lt;attribute name="reference" use="required" type="{}ThicknessReferences" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Thickness {

        @XmlValue
        protected float value;
        @XmlAttribute(name = "reference", required = true)
        protected ThicknessReferences reference;

        /**
         * Gets the value of the value property.
         * 
         */
        public float getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         */
        public void setValue(float value) {
            this.value = value;
        }

        /**
         * Gets the value of the reference property.
         * 
         * @return
         *     possible object is
         *     {@link ThicknessReferences }
         *     
         */
        public ThicknessReferences getReference() {
            return reference;
        }

        /**
         * Sets the value of the reference property.
         * 
         * @param value
         *     allowed object is
         *     {@link ThicknessReferences }
         *     
         */
        public void setReference(ThicknessReferences value) {
            this.reference = value;
        }

    }

}