/*********************************************************************************
 * Copyright (c) 2012 Forschungszentrum Juelich GmbH 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * (1) Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the disclaimer at the end. Redistributions in
 * binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution.
 * 
 * (2) Neither the name of Forschungszentrum Juelich GmbH nor the names of its 
 * contributors may be used to endorse or promote products derived from this 
 * software without specific prior written permission.
 * 
 * DISCLAIMER
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ********************************************************************************/
package eu.emi.es.client;

import java.net.URL;
import java.util.Set;

/**
 * This class represents an endpoint of a service with a given interface type
 * and capabilities. The type of the interface is described by a string called
 * InterfaceName (from GLUE2 specification). An Endpoint object must have a URL,
 * and it is quite useless without capabilities (the system has to know if an
 * Endpoint object represents a service registry or a computing element), but
 * the InterfaceName is optional. The Endpoint object also contains information
 * about the health state and quality level of the endpoint, and optionally the
 * requested submission interface name, which will be used later if a job will
 * be submitted to a computing element related to this endpoint.
 * 
 * <a href=
 * "http://svn.nordugrid.org/trac/nordugrid/browser/arc1/trunk/src/hed/libs/client/Endpoint.h"
 * >Endpoint.h</a>
 * 
 * @author bjoernh
 * 
 *         17.09.2012 08:34:38
 * 
 */
public class Endpoint {

    public enum CapabilityEnum {
        REGISTRY, COMPUTINGINFO, JOBLIST, JOBSUBMIT, JOBMANAGEMENT, UNSPECIFIED;
    }

    private final String interfaceName;
    private final URL url;
    private Set<CapabilityEnum> capabilities;

    /**
	 * 
	 */
    public Endpoint(URL _url, String _interfaceName) {
        this.url = _url;
        this.interfaceName = _interfaceName;
    }

    /**
	 * 
	 */
    public Endpoint(URL _url) {
        this(_url, null);
    }

    /**
     * @return the capabilities
     */
    public Set<CapabilityEnum> getCapabilities() {
        return capabilities;
    }

    /**
     * @param capabilities
     *            the capabilities to set
     */
    public void setCapabilities(Set<CapabilityEnum> capabilities) {
        this.capabilities = capabilities;
    }

    /**
     * @return the interfaceName
     */
    public String getInterfaceName() {
        return interfaceName;
    }

    /**
     * @return the url
     */
    public URL getUrl() {
        return url;
    }

}
