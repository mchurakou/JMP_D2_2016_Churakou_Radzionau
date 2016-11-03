
package com.epam.jmp.dr.task17.soap.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateUser", namespace = "http://ws.soap.task17.dr.jmp.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateUser", namespace = "http://ws.soap.task17.dr.jmp.epam.com/")
public class UpdateUser {

    @XmlElement(name = "arg0", namespace = "")
    private com.epam.jmp.dr.task17.entities.User arg0;

    /**
     * 
     * @return
     *     returns User
     */
    public com.epam.jmp.dr.task17.entities.User getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.epam.jmp.dr.task17.entities.User arg0) {
        this.arg0 = arg0;
    }

}
