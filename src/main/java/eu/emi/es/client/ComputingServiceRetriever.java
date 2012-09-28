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

import eu.emi.es.client.common.UserConfig;

/**
 * Combines the functionality of the ServiceEndpointRetriever
 * 
 * @author bjoernh
 * 
 *         17.09.2012 08:25:00
 * 
 */
public class ComputingServiceRetriever extends
        EntityContainer<ComputingServiceType> implements
        EntityConsumer<Endpoint> {

    private final UserConfig uc;
    private final List<Endpoint> endpoints;
    private final List<String> rejectedServices;
    private final List<String> preferredInterfaceNames;
    private final List<String> capabilityFilter;

    private final List<EntityConsumer<ComputingServiceType>> consumers;

    /**
     * 
     * @param _uc
     */
    public ComputingServiceRetriever(UserConfig _uc) {
        this(_uc, new ArrayList<Endpoint>(), new ArrayList<String>(),
                new ArrayList<String>(), new ArrayList<String>());
    }

    /**
     * 
     * @param _uc
     * @param _services
     * @param _rejectedServices
     * @param _preferredInterfaceNames
     * @param _capabilityFilter
     */
    public ComputingServiceRetriever(UserConfig _uc, List<Endpoint> _services,
            List<String> _rejectedServices,
            List<String> _preferredInterfaceNames,
            List<String> _capabilityFilter) {
        this.uc = _uc;
        this.endpoints = _services;
        this.rejectedServices = _rejectedServices;
        this.preferredInterfaceNames = _preferredInterfaceNames;
        this.capabilityFilter = _capabilityFilter;

        this.consumers = new ArrayList<EntityConsumer<ComputingServiceType>>();
    }

    public void addEndpoint(Endpoint _service) {
        endpoints.add(_service);
    }

    public void addConsumer(EntityConsumer<ComputingServiceType> c) {
        consumers.add(c);
    }

    public void removeConsumer(EntityConsumer<ComputingServiceType> c) {
        consumers.remove(c);
    }

    /**
     * @see eu.emi.es.client.EntityConsumer#addEntity(java.lang.Object)
     */
    public void addEntity(Endpoint _entity) {
        addEndpoint(_entity);
    }

}
