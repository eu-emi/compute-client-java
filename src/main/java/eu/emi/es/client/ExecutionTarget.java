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

import org.ogf.schemas.glue.x2009.x03.spec20R1.AdminDomainT;
import org.ogf.schemas.glue.x2009.x03.spec20R1.ApplicationEnvironmentT;
import org.ogf.schemas.glue.x2009.x03.spec20R1.ComputingEndpointT;
import org.ogf.schemas.glue.x2009.x03.spec20R1.ComputingManagerT;
import org.ogf.schemas.glue.x2009.x03.spec20R1.ComputingServiceT;
import org.ogf.schemas.glue.x2009.x03.spec20R1.ExecutionEnvironmentT;
import org.ogf.schemas.glue.x2009.x03.spec20R1.LocationT;

import eu.emi.es.client.common.UserConfig;

/**
 * This class describes a target which accepts computing jobs. This is usually a
 * queue on a computing element accessible through a single interface. If a
 * computing element has multiple interfaces (e.g. NorduGrid’s GridFTP, XBES and
 * EMIES), one queue would be represented by multiple ExecutionTarget objects,
 * one for each interface. All of the members contained in this class, with a
 * few exceptions, are directly linked to attributes defined in the GLUE2
 * Specification. ExecutionTarget objects are generated from
 * ComputingServiceType objects.
 * 
 * <a href=
 * "http://svn.nordugrid.org/trac/nordugrid/browser/arc1/trunk/src/hed/libs/compute/ExecutionTarget.h"
 * >ExecutionTarget.h</a>
 * 
 * @author bjoernh
 * 
 *         17.09.2012 08:33:56
 * 
 */
public class ExecutionTarget {
    private final LocationT location;
    private final AdminDomainT adminDomain;
    private final ComputingServiceT computingService;
    private final ComputingEndpointT computingEndpoint;
    private final ComputingManagerT computingManager;
    private final ExecutionEnvironmentT executionEnvironment;
    private final Map<String, Double> benchmarks;
    private final List<ApplicationEnvironmentT> applicationEnvironments;

    /**
     * 
     */
    public ExecutionTarget() {
        this(LocationT.Factory.newInstance(), AdminDomainT.Factory
                .newInstance(), ComputingServiceT.Factory.newInstance(),
                ComputingEndpointT.Factory.newInstance(),
                ComputingManagerT.Factory.newInstance(),
                ExecutionEnvironmentT.Factory.newInstance(),
                new HashMap<String, Double>(),
                new ArrayList<ApplicationEnvironmentT>());
    }

    /**
     * 
     * @param _location
     * @param _adminDomain
     * @param _computingService
     * @param _computingEndpoint
     * @param _computingManager
     * @param _executionEnvironment
     * @param _benchmarks
     * @param _applicationEnvironments
     */
    public ExecutionTarget(LocationT _location, AdminDomainT _adminDomain,
            ComputingServiceT _computingService,
            ComputingEndpointT _computingEndpoint,
            ComputingManagerT _computingManager,
            ExecutionEnvironmentT _executionEnvironment,
            Map<String, Double> _benchmarks,
            List<ApplicationEnvironmentT> _applicationEnvironments) {
        this.location = _location;
        this.adminDomain = _adminDomain;
        this.computingService = _computingService;
        this.computingEndpoint = _computingEndpoint;
        this.computingManager = _computingManager;
        this.executionEnvironment = _executionEnvironment;
        this.benchmarks = _benchmarks;
        this.applicationEnvironments = _applicationEnvironments;
    }

    /**
     * 
     * @param _uc
     * @param _jd
     * @return
     */
    public Job submit(UserConfig _uc, JobDescription _jd) {
        return null;
    }

    /**
     * 
     * @param _jd
     */
    public void registerJobSubmission(JobDescription _jd) {

    }
}
