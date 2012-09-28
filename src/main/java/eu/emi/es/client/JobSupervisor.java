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
import java.util.ArrayList;
import java.util.List;

import eu.emi.es.client.common.UserConfig;

/**
 * The JobSupervisor class manages a list of existing jobs (status,
 * cancellation, etc.). It uses specialised plugins in order to obtain
 * information and request execution of job related commands. The specialised
 * plugins support different interfaces and middlewares (the JobControllerPlugin
 * classes).
 * 
 * A {@link JobSupervisor} keeps track of selected jobs. Various selection
 * criteria can be applied to do the selection.
 * 
 * <a href=
 * "http://svn.nordugrid.org/trac/nordugrid/browser/arc1/trunk/src/hed/libs/client/JobSupervisor.h"
 * >JobSupervisor.h</a>
 * 
 * The idea is that the Resubmit, Retrieve, Renew, Resume, Cancel and Clean
 * methods works on the selected jobs. If any of these method fails to complete
 * a request for a particular job, that job will be deselected.
 * 
 * @author bjoernh
 * 
 *         17.09.2012 08:21:46
 * 
 */
public class JobSupervisor implements EntityConsumer<Job> {

    private final UserConfig uc;
    private final List<Job> jobs;

    private final List<Job> selected;

    private final List<URL> processed;
    private final List<URL> notProcessed;

    /**
     * To create a JobSupervisor object, you need a UserConfig (which contains
     * attributes on how to locate user's credentials), and a list of Job
     * objects.
     * 
     * @param _uc
     * @param _jobs
     */
    public JobSupervisor(UserConfig _uc, List<Job> _jobs) {
        this.uc = _uc;
        if (_jobs != null) {
            this.jobs = _jobs;
        } else {
            this.jobs = new ArrayList<Job>();
        }
        this.selected = new ArrayList<Job>();

        this.processed = new ArrayList<URL>();
        this.notProcessed = new ArrayList<URL>();
    }

    /**
     * Add a job to the list of supervised Jobs.
     * 
     * @param _job
     * @return
     */
    public boolean addJob(Job _job) {
        return jobs.add(_job);
    }

    /**
     * Implementation of the EntityConsumer interface for Jobs. This delegates
     * to the {@link #addJob(Job)} method.
     * 
     * @see eu.emi.es.client.EntityConsumer#addEntity(java.lang.Object)
     */
    public void addEntity(Job _job) {
        addJob(_job);
    }

    /**
     * Select jobs that are valid.
     */
    public void selectValid() {

    }

    /**
     * Select jobs in any of the given states.
     * 
     * @param _states
     */
    public void selectByStatus(List<JobState> _states) {
        for (Job job : jobs) {
            if (_states.contains(job.getState())) {
                selected.add(job);
            }
        }
    }

    public void selectById(List<URL> _ids) {
        clearSelection();
        // then add matching jobs
    }

    public List<Job> getSelectedJobs() {
        return selected;
    }

    public List<Job> getAllJobs() {
        return jobs;
    }

    public void clearSelection() {
        selected.clear();
    }

    /**
     * Update all {@link Job}s' states from the compute element.
     */
    public void update() {

    }

    /**
     * To retrieve the output files of selected jobs.
     * 
     * 
     * @param _downloadDirPrefix
     * @param _useJobName
     * @param _force
     * @param _downloadDirectories
     * @return
     */
    public boolean retrieve(String _downloadDirPrefix, boolean _useJobName,
            boolean _force, List<String> _downloadDirectories) {
        return false;
    }

    /**
     * Renew job credentials.
     * 
     * @return
     */
    public boolean renew() {
        return false;
    }

    /**
     * Resume a job.
     * 
     * @return
     */
    public boolean resume() {
        return false;
    }

    /**
     * Cancel a job.
     * 
     * @return
     */
    public boolean cancel() {
        return false;
    }

    /**
     * Clean jobs.
     * 
     * @return
     */
    public boolean clean() {
        return false;
    }

    /**
     * To resubmit jobs (where destination is 1 if the job should be resubmitted
     * to the same target, 2 if anywhere but not the same, and any other if can
     * be resubmitted to any target including the same).
     * 
     * @param _destination
     * @param _endpoints
     * @param _resubmittedJobs
     * @param _rejectedEndpoints
     * @return
     */
    public boolean resubmit(int _destination, List<Endpoint> _endpoints,
            List<Job> _resubmittedJobs, List<String> _rejectedEndpoints) {
        return false;
    }

    /**
     * After a job management command the IDs of jobs successfully processed and
     * the IDs of jobs not processed can be retrieved with these methods.
     * 
     * @return
     */
    public List<URL> getIdsProcessed() {
        return processed;
    }

    /**
     * After a job management command the IDs of jobs successfully processed and
     * the IDs of jobs not processed can be retrieved with these methods.
     * 
     * @return
     */
    public List<URL> getIdsNotProcessed() {
        return notProcessed;
    }

}
