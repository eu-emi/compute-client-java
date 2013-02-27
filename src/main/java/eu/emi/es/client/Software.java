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

/**
 * @author bjoernh
 * 
 *         29.11.2012 10:52:16
 * 
 */
public class Software {
    private final String family;
    private final String name;
    private final String version;

    private final List<String> tokenizedVersion;
    private final List<String> options;

    /**
     * Create a Software object from a single string composed of a name and
     * a version part. The created object will contain a empty family part.
     * The name and version part of the string will be split at the first
     * occurence of a dash (-) which is followed by a digit (0-9). If the
     * string does not contain such a pattern, the passed string will be
     * taken to be the name and version will be empty.
     * 
     * @param _nameVersion
     *            should be a string composed of the name and version of the
     *            software to represent.
     */
    public Software(String _nameVersion) {
        this.family = "";
        int dashIndex = _nameVersion.indexOf('-');
        if (dashIndex != -1) {
            this.name = _nameVersion.substring(0, dashIndex);
            this.version = _nameVersion.substring(dashIndex + 1);
        } else {
            this.name = _nameVersion;
            this.version = "";
        }
        this.options = new ArrayList<String>();
        this.tokenizedVersion = new ArrayList<String>();
    }

    /**
     * Create a Software object with the specified name and version. The
     * family part will be left empty.
     * 
     * @param _name
     *            the software name to represent.
     * @param _version
     *            the software version to represent.
     */
    public Software(String _name, String _version) {
        this.family = "";
        this.name = _name;
        this.version = _version;
        this.options = new ArrayList<String>();
        this.tokenizedVersion = new ArrayList<String>();
    }

    /**
     * Create a Software object with the specified family, name and version.
     * 
     * @param _family
     *            the software family to represent.
     * @param _name
     *            the software name to represent.
     * @param _version
     *            the software version to represent.
     */
    public Software(String _family, String _name, String _version) {
        this.family = _family;
        this.name = _name;
        this.version = _version;
        this.options = new ArrayList<String>();
        this.tokenizedVersion = new ArrayList<String>();
    }

    enum ComparisonOperatorEnum {
        NOTEQUAL, EQUAL, GREATERTHAN, LESSTHAN, GREATERTHANOREQUAL, LESSTHANOREQUAL
    }

    /**
     * @return
     */
    public Object getName() {
        return name;
    }

    /**
     * @return the family
     */
    public String getFamily() {
        return family;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    public boolean lessThan(Software _other) {
        if (this.getName().equals(_other.getName())
                && this.getFamily().equals(_other.getFamily())) {
            return this.getVersion().compareTo(_other.getVersion()) < 0;
        }
        return false;
    }

    public boolean lessThanOrEqual(Software _other) {
        return equals(_other) || lessThan(_other);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Software _other) {
        Software _otherSoftware = (Software) _other;
        return this.getFamily().equals(_otherSoftware.getFamily())
                && this.getName().equals(_otherSoftware.getName())
                && this.getVersion().equals(_otherSoftware.getVersion());
    }

    public boolean greaterThan(Software _other) {
        if (this.getName().equals(_other.getName())
                && this.getFamily().equals(_other.getFamily())) {
            return this.getVersion().compareTo(_other.getVersion()) > 0;
        }
        return false;
    }

    public boolean greaterThanOrEqual(Software _other) {
        return equals(_other) || greaterThan(_other);
    }

    public boolean empty() {
        return this.getName().equals("");
    }

    /**
     * @return the options
     */
    public List<String> getOptions() {
        return options;
    }

    public void addOption(String _opt) {
        options.add(_opt);
    }

    public void addOptions(List<String> _opts) {
        options.addAll(_opts);
    }

}