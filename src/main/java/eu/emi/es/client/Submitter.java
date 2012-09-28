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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.emi.es.client.common.UserConfig;

/**
 * The Submitter class can be used to submit job descriptions to computing
 * elements. It returns a Job object for each successful submission. It can
 * submit to a single ExecutionTarget or can invoke resource discovery, do
 * match-making and sorting of the computing resources, and then submit to
 * appropriate resource. The latter option utilizes the
 * ComputingServiceRetriever and Broker classes.
 * 
 * <a href=
 * "http://svn.nordugrid.org/trac/nordugrid/browser/arc1/trunk/src/hed/libs/client/Submitter.h"
 * >Submitter.h</a>
 * 
 * @author bjoernh
 * 
 *         17.09.2012 08:21:10
 * 
 */
public class Submitter {
    private final UserConfig uc;

    private final Set<EntityConsumer<Job>> consumers;

    /**
     * 
     * @param _uc
     */
    public Submitter(UserConfig _uc) {
        this.uc = _uc;
        this.consumers = new HashSet<EntityConsumer<Job>>();
    }

    /**
     * The Submitter class can send the submitted Job objects to registered
     * consumers (e.g. a JobSupervisor object)
     * 
     * @param _jc
     */
    void addConsumer(EntityConsumer<Job> _jc) {
        consumers.add(_jc);
    }

    /**
     * The Submitter class can send the submitted Job objects to registered
     * consumers (e.g. a JobSupervisor object).
     * 
     * @param _jc
     * @see #addConsumer(EntityConsumer)
     */
    void removeConsumer(EntityConsumer<Job> _jc) {
        consumers.remove(_jc);
    }

    /**
     * To submit one or more job descriptions (strings or objects) to a single
     * ExecutionTarget object (with getting the resulting Job object as an
     * output argument or through registered consumers).
     * 
     * @param _target
     * @param _desc
     * @return
     */
    public Job submit(ExecutionTarget _target, JobDescription _desc) {
        return _target.submit(uc, _desc);
    }

    /**
     * To submit one or more job descriptions (strings or objects) to a single
     * ExecutionTarget object (with getting the resulting Job object as an
     * output argument or through registered consumers).
     * 
     * @param _target
     * @param _descs
     * @return
     */
    public boolean submit(ExecutionTarget _target, List<JobDescription> _descs) {
        List<Job> jobs = new ArrayList<Job>();
        return submit(_target, _descs, jobs);
    }

    /**
     * To submit one or more job descriptions (strings or objects) to a single
     * ExecutionTarget object (with getting the resulting Job object as an
     * output argument or through registered consumers).
     * 
     * @param _target
     * @param _descs
     * @param _jobs
     * @return
     */
    public boolean submit(ExecutionTarget _target, List<JobDescription> _descs,
            List<Job> _jobs) {
        boolean success = true;
        for (JobDescription jobDescription : _descs) {
            try {
                _jobs.add(_target.submit(uc, jobDescription));
            } catch (Exception e) {
                success = false;
            }
        }
        return success;
    }

    // Java type erasure does not allow these alternative methods, as they
    // essentially
    // have the same signature as the ones below. We opted for offering the ones
    // accepting JobDescriptions rather than Strings.
    // public EndpointSubmissionStatus brokeredSubmit(List<String> _endpoints,
    // List<JobDescription> _descs,
    // List<String> _requestedSubmissionInterfaces) {
    // return EndpointSubmissionStatus.UNKNOWN;
    // }
    //
    // public EndpointSubmissionStatus brokeredSubmit(List<String> _endpoints,
    // List<JobDescription> _descs, List<Job> _jobs,
    // List<String> _requestedSubmissionInterfaces) {
    // return EndpointSubmissionStatus.UNKNOWN;
    // }

    /**
     * To do brokering among several endpoints (strings or objects) and submit
     * multiple JobDescription objects (with getting the resulting Job objects
     * as output argument or through registered consumers).
     * 
     * @param _endpoints
     * @param _descs
     * @param _requestedSubmissionInterfaces
     * @return
     */
    public EndpointSubmissionStatus brokeredSubmit(List<Endpoint> _endpoints,
            List<JobDescription> _descs,
            List<String> _requestedSubmissionInterfaces) {
        return EndpointSubmissionStatus.UNKNOWN;
    }

    /**
     * To do brokering among several endpoints (strings or objects) and submit
     * multiple JobDescription objects (with getting the resulting Job objects
     * as output argument or through registered consumers).
     * 
     * @param _endpoints
     * @param _descs
     * @param _jobs
     * @param _requestedSubmissionInterfaces
     * @return
     */
    public EndpointSubmissionStatus brokeredSubmit(List<Endpoint> _endpoints,
            List<JobDescription> _descs, List<Job> _jobs,
            List<String> _requestedSubmissionInterfaces) {
        return EndpointSubmissionStatus.UNKNOWN;
    }

    public List<JobDescription> getJobDescriptionsNotSubmitted() {
        // TODO empty method stub
        return null;
    }

    public void clearNotSubmittedDescriptions() {

    }

    public Map<Endpoint, EndpointQueryingStatus> getEndpointQueryingStatuses() {
        return null;
    }

    public void clearEndpointQueryingStatuses() {

    }

    public Map<Endpoint, EndpointSubmissionStatus> getEndpointSubmissionStatuses() {
        return null;
    }

    public void clearEndpointSubmissionStatuses() {

    }

    public void clearAllStatuses() {

        clearEndpointQueryingStatuses();
        clearEndpointSubmissionStatuses();
    }

    public void clearAll() {
        clearNotSubmittedDescriptions();
        clearAllStatuses();
    }

}
