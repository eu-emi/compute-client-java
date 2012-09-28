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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.emi.es.client.common.UserConfig;

/**
 * Job submission
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
 */
    public Submitter(UserConfig _uc) {
        this.uc = _uc;
        this.consumers = new HashSet<EntityConsumer<Job>>();
    }

    void addConsumer(EntityConsumer<Job> _jc) {
        consumers.add(_jc);
    }

    void removeConsumer(EntityConsumer<Job> _jc) {
        consumers.remove(_jc);
    }

    public boolean submit(ExecutionTarget _target, JobDescription _desc) {
        // TODO: empty method stub
        return false;
    }

    public boolean submit(ExecutionTarget _target, JobDescription _desc,
            Job _job) {
        // TODO empty method stub
        return false;
    }

    public boolean submit(ExecutionTarget _target, List<JobDescription> _descs) {
        // TODO empty method stub
        return false;
    }

    public boolean submit(ExecutionTarget _target, List<JobDescription> _descs,
            List<Job> _jobs) {
        // TODO empty method stub
        return false;
    }

    // Java type erasure does not allow these alternative methods, as they
    // essentially
    // have the same signature
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

    public EndpointSubmissionStatus brokeredSubmit(List<Endpoint> _endpoints,
            List<JobDescription> _descs,
            List<String> _requestedSubmissionInterfaces) {
        return EndpointSubmissionStatus.UNKNOWN;
    }

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
