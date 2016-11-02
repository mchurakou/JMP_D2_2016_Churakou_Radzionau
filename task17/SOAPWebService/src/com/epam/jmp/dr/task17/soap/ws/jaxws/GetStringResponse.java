
package com.epam.jmp.dr.task17.soap.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getStringResponse", namespace = "http://ws.soap.task17.dr.jmp.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStringResponse", namespace = "http://ws.soap.task17.dr.jmp.epam.com/")
public class GetStringResponse {

    @XmlElement(name = "return", namespace = "")
    private String _return;

    /**
     * 
     * @return
     *     returns String
     */
    public String getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(String _return) {
        this._return = _return;
    }

}
