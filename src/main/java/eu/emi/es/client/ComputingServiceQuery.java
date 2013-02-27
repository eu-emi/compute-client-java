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
import java.util.concurrent.Callable;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.ogf.schemas.glue._2009._03.spec_2.ServiceT;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import eu.emi.emir.client.EMIRClient;
import eu.emi.emir.client.ServiceBasicAttributeNames;
import eu.emi.es.client.common.UserConfig;
import eu.eu_emi.emiregistry.QueryResult;

/**
 * This {@link Callable} runs a query on an EMIR service to retrieve
 * {@link ComputingServiceType}s with given capabilities.
 * 
 * @author bjoernh
 * 
 *         29.11.2012 17:16:04
 * @param <V>
 * 
 */
public class ComputingServiceQuery implements Callable<List<ServiceT>> {

    private final static Logger log = Logger
            .getLogger(ComputingServiceQuery.class);

    private final UserConfig uc;
    private final Endpoint emirEndpoint;
    private final List<String> capabilityFilter;

    /**
     * 
     */
    public ComputingServiceQuery(Endpoint _emirEndpoint, UserConfig _uc,
            List<String> _capabilityFilter) {
        this.uc = _uc;
        this.emirEndpoint = _emirEndpoint;
        this.capabilityFilter = _capabilityFilter;
    }

    /**
     * @see java.util.concurrent.Callable#call()
     */
    public List<ServiceT> call() throws Exception {
        List<ServiceT> computingServices = new ArrayList<ServiceT>();

        EMIRClient emirClient = new EMIRClient(emirEndpoint.getUrl().toString());

        MultivaluedMap<String, String> params = new MultivaluedMapImpl();

        params.put(ServiceBasicAttributeNames.SERVICE_CAPABILITY
                .getAttributeName(), capabilityFilter);

        QueryResult result = emirClient.queryXML(params, 0, 0);
        List<ServiceT> services = result.getService();

        computingServices.addAll(services);

        return computingServices;
    }
}
