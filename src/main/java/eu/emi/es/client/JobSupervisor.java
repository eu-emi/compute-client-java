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
import java.util.List;

import eu.emi.es.client.common.UserConfig;

/**
 * Job management
 * 
 * @author bjoernh
 * 
 *         17.09.2012 08:21:46
 * 
 */
public class JobSupervisor implements EntityConsumer<Job> {

	private final UserConfig uc;
	private final List<Job> jobs;

	/**
	 * 
	 * @param _uc
	 * @param _jobs
	 */
	public JobSupervisor(UserConfig _uc, List<Job> _jobs) {
		this.uc = _uc;
		this.jobs = _jobs;
	}

	/**
	 * 
	 * @param _job
	 * @return
	 */
	public boolean addJob(Job _job) {
		return jobs.add(_job);
	}

	/**
	 * 
	 * @see eu.emi.es.client.EntityConsumer#addEntity(java.lang.Object)
	 */
	public void addEntity(Job _job) {
		addJob(_job);
	}

	public void selectValid() {

	}

	public void selectByStatus(List<JobState> _states) {

	}

	public void selectById(List<URL> _ids) {

	}

	public List<Job> getSelectedJobs() {
		return null;
	}

	public List<Job> getAllJobs() {
		return null;
	}

	public void clearSelection() {

	}

	public void update() {

	}

	public boolean retrieve(String _downloadDirPrefix, boolean _useJobName,
			boolean _force, List<String> _downloadDirectories) {
		return false;
	}

	public boolean renew() {
		return false;
	}

	public boolean resume() {
		return false;
	}

	public boolean cancel() {
		return false;
	}

	public boolean clean() {
		return false;
	}

	public boolean resubmit(int _destination, List<Endpoint> _endpoints,
			List<Job> _resubmittedJobs,
			List<String> _requestedSubmissionInterfaces) {
		return false;
	}

	public List<URL> getIdsProcessed() {
		return null;
	}

	public List<URL> getIdsNotProcessed() {
		return null;
	}

}
