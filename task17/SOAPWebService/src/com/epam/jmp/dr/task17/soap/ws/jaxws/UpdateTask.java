
package com.epam.jmp.dr.task17.soap.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateTask", namespace = "http://ws.soap.task17.dr.jmp.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateTask", namespace = "http://ws.soap.task17.dr.jmp.epam.com/", propOrder = {
    "arg0",
    "arg1"
})
public class UpdateTask {

    @XmlElement(name = "arg0", namespace = "")
    private int arg0;
    @XmlElement(name = "arg1", namespace = "")
    private com.epam.jmp.dr.task17.entities.Task arg1;

    /**
     * 
     * @return
     *     returns int
     */
    public int getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(int arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns Task
     */
    public com.epam.jmp.dr.task17.entities.Task getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(com.epam.jmp.dr.task17.entities.Task arg1) {
        this.arg1 = arg1;
    }

}
