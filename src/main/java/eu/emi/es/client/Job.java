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
public class Job {
    private JobState state;

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

}
