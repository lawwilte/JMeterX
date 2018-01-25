/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.apache.jmeter.functions;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.util.JMeterUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Function to encode a string to a application/x-www-form-urlencoded string.
 * 
 * @since 2.10
 */
public class MD5 extends AbstractFunction {

    private static final String CHARSET_ENCODING = StandardCharsets.UTF_8.name();

    private static final List<String> desc = new LinkedList<>();

    private static final String KEY = "__MD5"; //$NON-NLS-1$

        static {
            desc.add(JMeterUtils.getResString("md5_string")); //$NON-NLS-1$
        }

        private Object[] values;

    public MD5() {
        }

        /** {@inheritDoc} */
        @Override
        public String execute(SampleResult previousResult, Sampler currentSampler)
            throws InvalidVariableException {
        String str = ""; //$NON-NLS-1$
        try {
            String strV0 = ((CompoundVariable) values[0]).execute();
            str = DigestUtils.md5Hex(strV0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return str;
    }

    /** {@inheritDoc} */
    @Override
    public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
        checkParameterCount(parameters, 1);
        values = parameters.toArray();
    }

    /** {@inheritDoc} */
    @Override
    public String getReferenceKey() {
        return KEY;
    }

    /** {@inheritDoc} */
    @Override
    public List<String> getArgumentDesc() {
        return desc;
    }
}