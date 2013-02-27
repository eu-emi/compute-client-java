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

import java.io.Serializable;
import java.net.URL;

import eu.emi.es.client.common.UserConfig;

/**
 * Represents an already submitted Job
 * 
 * The Job class represents an already submitted job. It is returned by the
 * submission methods (see Submitter class) or by the JobListRetriever class (to
 * get a list of jobs from a computing element) and it is needed for batch job
 * management (see the JobSupervisor class), but it also provides methods to
 * manage the single job it represents. Most of the member attributes contained
 * in this class are directly linked to the ComputingActivity defined in the
 * GLUE2 Specification. A Job object can be serialized into an XML document,
 * then later recreated from that. So the Job object created by the job
 * submission process can be saved to disk and later can be recreated for job
 * management.
 * 
 * <a href=
 * "http://svn.nordugrid.org/trac/nordugrid/browser/arc1/trunk/src/hed/libs/compute/Job.h"
 * >Job.h</a>
 * 
 * 
 * @author bjoernh
 * 
 *         17.09.2012 08:32:26
 * 
 */
public class Job implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6452874697439455159L;

    private JobState state;

    // mandatory attributes
    private final String jobId;
    private final String serviceInformationUrl;
    private final String serviceInformationEndpointInterfaceName;
    private final String jobStatusUrl;
    private final String jobStatusInterfaceName;
    private final String jobManagementUrl;
    private final String jobMangementInterfaceName;
    private final String stageInDir;
    private final String stageOutDir;
    private final String sessionDir;

    // optional attributes
    private final String name;
    private final String idFromEndpoint;

    /**
     * 
     */
    public Job(String _jobId, String _serviceInformationUrl,
            String _serviceInformationEndpointInterfaceName,
            String _jobStatusUrl, String _jobStatusInterfaceName,
            String _jobManagementUrl, String _jobManagementInterfaceName,
            String _stageInDir, String _stageOutDir, String _sessionDir,
            String _name, String _idFromEndpoint) {
        this.jobId = _jobId;
        this.serviceInformationUrl = _serviceInformationUrl;
        this.serviceInformationEndpointInterfaceName = _serviceInformationEndpointInterfaceName;
        this.jobStatusUrl = _jobStatusUrl;
        this.jobStatusInterfaceName = _jobStatusInterfaceName;
        this.jobManagementUrl = _jobManagementUrl;
        this.jobMangementInterfaceName = _jobManagementInterfaceName;
        this.stageInDir = _stageInDir;
        this.stageOutDir = _stageOutDir;
        this.sessionDir = _sessionDir;

        this.name = _name;
        this.idFromEndpoint = _idFromEndpoint;
    }

    /**
     * The C++ implementation takes an XMLNode object, need to check how this
     * fits in.
     * 
     * @param job
     */
    public void update(Object job) {

    }

    public boolean update() {
        return false;
    }

    public boolean clean() {
        return false;
    }

    public boolean cancel() {
        return false;
    }

    public boolean resume() {
        return false;
    }

    public boolean renew() {
        return false;
    }

    public boolean prepareHandler(UserConfig _uc) {
        return false;
    }

    public URL getUrlToResource(ResourcesType _resource) {
        return null;
    }

    public boolean retrieve(UserConfig _uc, URL _destination, boolean _force) {
        return false;
    }

    /**
     * @return the state
     */
    public JobState getState() {
        return state;
    }

    /**
     * @return the jobId
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * @return the serviceInformationUrl
     */
    public String getServiceInformationUrl() {
        return serviceInformationUrl;
    }

    /**
     * @return the serviceInformationEndpointInterfaceName
     */
    public String getServiceInformationEndpointInterfaceName() {
        return serviceInformationEndpointInterfaceName;
    }

    /**
     * @return the jobStatusUrl
     */
    public String getJobStatusUrl() {
        return jobStatusUrl;
    }

    /**
     * @return the jobStatusInterfaceName
     */
    public String getJobStatusInterfaceName() {
        return jobStatusInterfaceName;
    }

    /**
     * @return the jobManagementUrl
     */
    public String getJobManagementUrl() {
        return jobManagementUrl;
    }

    /**
     * @return the jobMangementInterfaceName
     */
    public String getJobMangementInterfaceName() {
        return jobMangementInterfaceName;
    }

    /**
     * @return the stageInDir
     */
    public String getStageInDir() {
        return stageInDir;
    }

    /**
     * @return the stageOutDir
     */
    public String getStageOutDir() {
        return stageOutDir;
    }

    /**
     * @return the sessionDir
     */
    public String getSessionDir() {
        return sessionDir;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the idFromEndpoint
     */
    public String getIdFromEndpoint() {
        return idFromEndpoint;
    }

}
