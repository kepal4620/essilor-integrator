//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 07:48:00 PM CEST 
//


package essilor.integrator.adapter.domain.b2boptic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Pair complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Pair">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="patient" type="{}Patient" minOccurs="0"/>
 *         &lt;element name="lens" maxOccurs="2">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{}Lens">
 *                 &lt;attribute name="quantity" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="frame" type="{}Frame" minOccurs="0"/>
 *         &lt;element name="edging" type="{}Edging" minOccurs="0"/>
 *         &lt;element name="fitting" type="{}Fitting" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="thicknessMatching" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="weightMatching" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="prismMatching" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="baseMatching" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pair", propOrder = {
    "patient",
    "lens",
    "frame",
    "edging",
    "fitting",
    "remark",
    "thicknessMatching",
    "weightMatching",
    "prismMatching",
    "baseMatching"
})
public class Pair {

    protected Patient patient;
    @XmlElement(required = true)
    protected List<Pair.Lens> lens;
    protected Frame frame;
    protected Edging edging;
    protected Fitting fitting;
    protected String remark;
    @XmlElement(defaultValue = "false")
    protected Boolean thicknessMatching;
    @XmlElement(defaultValue = "false")
    protected Boolean weightMatching;
    @XmlElement(defaultValue = "false")
    protected Boolean prismMatching;
    @XmlElement(defaultValue = "false")
    protected Boolean baseMatching;

    /**
     * Gets the value of the patient property.
     * 
     * @return
     *     possible object is
     *     {@link Patient }
     *     
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patient }
     *     
     */
    public void setPatient(Patient value) {
        this.patient = value;
    }

    /**
     * Gets the value of the lens property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lens property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLens().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Pair.Lens }
     * 
     * 
     */
    public List<Pair.Lens> getLens() {
        if (lens == null) {
            lens = new ArrayList<Pair.Lens>();
        }
        return this.lens;
    }

    /**
     * Gets the value of the frame property.
     * 
     * @return
     *     possible object is
     *     {@link Frame }
     *     
     */
    public Frame getFrame() {
        return frame;
    }

    /**
     * Sets the value of the frame property.
     * 
     * @param value
     *     allowed object is
     *     {@link Frame }
     *     
     */
    public void setFrame(Frame value) {
        this.frame = value;
    }

    /**
     * Gets the value of the edging property.
     * 
     * @return
     *     possible object is
     *     {@link Edging }
     *     
     */
    public Edging getEdging() {
        return edging;
    }

    /**
     * Sets the value of the edging property.
     * 
     * @param value
     *     allowed object is
     *     {@link Edging }
     *     
     */
    public void setEdging(Edging value) {
        this.edging = value;
    }

    /**
     * Gets the value of the fitting property.
     * 
     * @return
     *     possible object is
     *     {@link Fitting }
     *     
     */
    public Fitting getFitting() {
        return fitting;
    }

    /**
     * Sets the value of the fitting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fitting }
     *     
     */
    public void setFitting(Fitting value) {
        this.fitting = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the thicknessMatching property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isThicknessMatching() {
        return thicknessMatching;
    }

    /**
     * Sets the value of the thicknessMatching property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setThicknessMatching(Boolean value) {
        this.thicknessMatching = value;
    }

    /**
     * Gets the value of the weightMatching property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWeightMatching() {
        return weightMatching;
    }

    /**
     * Sets the value of the weightMatching property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWeightMatching(Boolean value) {
        this.weightMatching = value;
    }

    /**
     * Gets the value of the prismMatching property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrismMatching() {
        return prismMatching;
    }

    /**
     * Sets the value of the prismMatching property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrismMatching(Boolean value) {
        this.prismMatching = value;
    }

    /**
     * Gets the value of the baseMatching property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBaseMatching() {
        return baseMatching;
    }

    /**
     * Sets the value of the baseMatching property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBaseMatching(Boolean value) {
        this.baseMatching = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{}Lens">
     *       &lt;attribute name="quantity" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Lens
        extends essilor.integrator.adapter.domain.b2boptic.Lens
    {

        @XmlAttribute(name = "quantity", required = true)
        protected BigInteger quantity;

        /**
         * Gets the value of the quantity property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getQuantity() {
            return quantity;
        }

        /**
         * Sets the value of the quantity property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setQuantity(BigInteger value) {
            this.quantity = value;
        }

    }

}