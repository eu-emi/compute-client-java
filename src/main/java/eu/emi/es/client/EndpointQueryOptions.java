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

import java.util.ArrayList;
import java.util.List;

/**
 * @author bjoernh
 * 
 *         17.09.2012 13:49:55
 * 
 */
public class EndpointQueryOptions<T> {

    private final boolean recursive;
    private final List<String> capabilityFilter;
    private final List<String> rejectedServices;
    private final List<String> preferredInterfaceNames;

    public EndpointQueryOptions(List<String> _preferredInterfaceNames) {
        if (_preferredInterfaceNames != null) {
            this.preferredInterfaceNames = _preferredInterfaceNames;
        } else {
            this.preferredInterfaceNames = new ArrayList<String>();
        }
        this.capabilityFilter = new ArrayList<String>();
        this.rejectedServices = new ArrayList<String>();

        this.recursive = false;
    }

    /**
	 * 
	 */
    public EndpointQueryOptions(boolean _recursive,
            List<String> _capabilityFilter, List<String> _rejectedServices) {
        this.recursive = _recursive;
        this.capabilityFilter = _capabilityFilter;
        this.rejectedServices = _rejectedServices;

        this.preferredInterfaceNames = new ArrayList<String>();
    }

    /**
     * @return the recursive
     */
    public boolean isRecursive() {
        return recursive;
    }

    /**
     * @return the capabilityFilter
     */
    public List<String> getCapabilityFilter() {
        return capabilityFilter;
    }

    /**
     * @return the rejectedServices
     */
    public List<String> getRejectedServices() {
        return rejectedServices;
    }

    /**
     * @return the preferredInterfaceNames
     */
    public List<String> getPreferredInterfaceNames() {
        return preferredInterfaceNames;
    }
}
