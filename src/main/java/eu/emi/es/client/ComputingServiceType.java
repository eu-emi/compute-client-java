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

import org.ogf.schemas.glue._2009._03.spec_2.ComputingEndpointT;
import org.ogf.schemas.glue._2009._03.spec_2.ComputingManagerT;
import org.ogf.schemas.glue._2009._03.spec_2.ComputingShareT;

/**
 * 
 * The {@link ComputingServiceType} class is composed of several classes which
 * together gives the full description of a computing service as defined in the
 * GLUE2 Specification: one LocationType, one AdminDomainType, multiple
 * {@link ComputingEndpointT} objects, multiple {@link ComputingShareT} objects
 * and multiple {@link ComputingManagerT} objects. The
 * {@link ComputingServiceType} objects are generated by the
 * {@link TargetInformationRetriever} or the {@link ComputingServiceRetriever}
 * classes, by doing resource discovery against infomation systems and
 * index/registry services.
 * 
 * @author bjoernh
 * 
 *         17.09.2012 08:34:06
 * 
 */
public class ComputingServiceType {

}
