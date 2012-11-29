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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.emi.es.client.common.UserConfig;

/**
 * Template class for doing resource discovery instantiated by the following
 * classes.
 * 
 * The EntityRetriever is a general template class querying Endpoints to
 * retrieve “entities” (the type of the entities is the type parameter of the
 * template). Querying of the Endpoints is done by entity specific plugins, and
 * it is performed parallel in separate threads. The results are sent to the
 * registered EntityConsumer objects. Currently there are three instances of
 * this template.
 * 
 * <ul>
 * <li>ServiceEndpointRetriever</li>
 * <li>TargetInformationRetriever</li>
 * <li>EntityRetriever</li>
 * </ul>
 * 
 * <a href=
 * "http://svn.nordugrid.org/trac/nordugrid/browser/arc1/trunk/src/hed/libs/compute/EntityRetriever.h"
 * >EntityRetriever.h</a>
 * 
 * @author bjoernh
 * 
 *         17.09.2012 08:24:34
 * 
 */
public class EntityRetriever<T> implements EntityConsumer<T> {

    private final UserConfig uc;
    private final EndpointQueryOptions<T> options;
    private final List<Endpoint> endpoints;
    private final List<EntityConsumer<T>> consumers;
    private final Map<Endpoint, EndpointQueryingStatus> queryingStatuses;

    /**
     * Create a new instance.
     * 
     * @param _uc
     * @param _options
     */
    public EntityRetriever(UserConfig _uc, EndpointQueryOptions<T> _options) {
        this.uc = _uc;
        this.options = _options;
        
        this.endpoints = new ArrayList<Endpoint>();
        this.consumers = new ArrayList<EntityConsumer<T>>();
        this.queryingStatuses = new HashMap<Endpoint, EndpointQueryingStatus>();
    }

    /**
     * 
     * @param _consumer
     */
    public void addConsumer(EntityConsumer<T> _consumer) {
        consumers.add(_consumer);
    }

    /**
     * 
     * @param _consumer
     */
    public void removeConsumer(EntityConsumer<T> _consumer) {
        consumers.remove(_consumer);
    }

    /**
     * 
     * @param _ep
     */
    public void addEndpoint(Endpoint _ep) {
        endpoints.add(_ep);
    }

    /**
     * @see eu.emi.es.client.EntityConsumer#addEntity(java.lang.Object)
     */
    public void addEntity(T _job) {

    }

    /**
     * 
     * @param _ep
     * @return
     */
    public EndpointQueryingStatus getStatusOfEndpoint(Endpoint _ep) {
        // TODO return meaningful values
        return EndpointQueryingStatus.UNKNOWN;
    }

    public Map<Endpoint, EndpointQueryingStatus> getAllStatuses() {
        return queryingStatuses;
    }

    /**
     * Wait for the query process to finish.
     * 
     * Equivalent of the wait() method of the C++ API
     */
    public void block() {

    }

    /**
     * Check whether the query process has finished.
     * 
     * @return
     */
    public boolean isDone() {
        // TODO return sth. meaningful
        return false;
    }

}
