
package com.epam.jmp.dr.task17.soap.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getTaskResponse", namespace = "http://ws.soap.task17.dr.jmp.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTaskResponse", namespace = "http://ws.soap.task17.dr.jmp.epam.com/")
public class GetTaskResponse {

    @XmlElement(name = "return", namespace = "")
    private com.epam.jmp.dr.task17.entities.Task _return;

    /**
     * 
     * @return
     *     returns Task
     */
    public com.epam.jmp.dr.task17.entities.Task getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.epam.jmp.dr.task17.entities.Task _return) {
        this._return = _return;
    }

}
