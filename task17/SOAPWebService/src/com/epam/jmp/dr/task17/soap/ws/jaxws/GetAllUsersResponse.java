
package com.epam.jmp.dr.task17.soap.ws.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllUsersResponse", namespace = "http://ws.soap.task17.dr.jmp.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllUsersResponse", namespace = "http://ws.soap.task17.dr.jmp.epam.com/")
public class GetAllUsersResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.epam.jmp.dr.task17.entities.User> _return;

    /**
     * 
     * @return
     *     returns List<User>
     */
    public List<com.epam.jmp.dr.task17.entities.User> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.epam.jmp.dr.task17.entities.User> _return) {
        this._return = _return;
    }

}
