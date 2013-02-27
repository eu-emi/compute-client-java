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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.ogf.schemas.glue._2009._03.spec_2.ServiceT;

import eu.emi.es.client.common.UserConfig;
import eu.unicore.hila.HiLA;

/**
 * Combines the functionality of the ServiceEndpointRetriever
 * 
 * The ComputingServiceRetriever retrieves information about computing services
 * by querying service registries (EMIR, EGIIS) and the local information system
 * of computing elements (through LDAP and WS). The discovery is recursive: if
 * querying a registry service returns more registries those will also be
 * queried, and all the computing services returned by a registry query will be
 * contacted.
 * 
 * <a href=
 * "http://svn.nordugrid.org/trac/nordugrid/browser/arc1/trunk/src/hed/libs/compute/ComputingServiceRetriever.h"
 * >ComputingServiceRetriever.h</a>
 * 
 * @author bjoernh
 * 
 *         17.09.2012 08:25:00
 * 
 */
public class ComputingServiceRetriever extends
		EntityContainer<ComputingServiceType> implements Runnable,
		EntityConsumer<ComputingServiceType> {

	private static final Logger log = Logger
			.getLogger(ComputingServiceRetriever.class);

	private final UserConfig uc;
	private final List<Endpoint> endpoints;
	private final List<String> rejectedServices;
	private final List<String> preferredInterfaceNames;
	private final List<String> capabilityFilter;

	private final List<EntityConsumer<ComputingServiceType>> consumers;

	private final List<ExecutionTarget> executionTargets;

	private boolean running;

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
		this.executionTargets = new ArrayList<ExecutionTarget>();

		if (endpoints == null || endpoints.isEmpty()) {
			running = false;
		} else {
			HiLA.getDaemonExecutor().scheduleAtFixedRate(this, 0, 60,
					TimeUnit.SECONDS);
			running = true;
		}
	}

	/**
	 * Add an endpoint to the list of endpoints.
	 * 
	 * @param _service
	 */
	public synchronized void addEndpoint(Endpoint _service) {
		endpoints.add(_service);
		if (!running) {
			HiLA.getDaemonExecutor().scheduleAtFixedRate(this, 0, 60,
					TimeUnit.SECONDS);
			running = true;
		}
	}

	/**
	 * Add a consumer to the list of consumers.
	 * 
	 * @param _consumer
	 */
	public void addConsumer(EntityConsumer<ComputingServiceType> _consumer) {
		consumers.add(_consumer);
	}

	/**
	 * 
	 * @return
	 */
	public List<ExecutionTarget> getExecutionTargets() {
		return executionTargets;
	}

	/**
	 * Remove a consumer from the list of consumers.
	 * 
	 * @param c
	 */
	public void removeConsumer(EntityConsumer<ComputingServiceType> c) {
		consumers.remove(c);
	}

	/**
	 * Block on this object to wait for the discovery process to finish.
	 * 
	 * This is the wait() method from the C++ API. However, {@link #wait()} is
	 * already defined final in {@link Object} and cannot be overridden.
	 */
	public void block() {
		// TODO method stub
	}

	/**
	 * Start the retrieval process. This is supposed
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		List<Future<List<ServiceT>>> cstFutures = new ArrayList<Future<List<ServiceT>>>();
		for (Endpoint endpoint : endpoints) {
			ComputingServiceQuery epq = new ComputingServiceQuery(endpoint, uc,
					this.capabilityFilter);
			cstFutures.add(HiLA.getDaemonExecutor().submit(epq));
		}

		for (Future<List<ServiceT>> future : cstFutures) {
			try {
				List<ServiceT> cst = future.get();
				for (ServiceT computingServiceType : cst) {
					createExecutionTarget(computingServiceType);
				}
			} catch (InterruptedException e) {
				log.error(
						"Something went wrong while generating list of computing services.",
						e);
			} catch (ExecutionException e) {
				log.error(
						"Something went wrong while generating list of computing services.",
						e);
			}
		}

	}

	/**
	 * @param computingServiceType
	 */
	private void createExecutionTarget(ServiceT computingServiceType) {

	}

}
