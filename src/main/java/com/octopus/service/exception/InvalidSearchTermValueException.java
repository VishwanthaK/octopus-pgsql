/*
 * Copyright 2018 Impelsys India Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octopus.service.exception;

/**
 * This is a subclass of {@link SearchException}, which is thrown when
 * the value of a valid search term is invalid.
 *
 * @author Dheeraj Gopinath
 */
public class InvalidSearchTermValueException extends SearchException {

    private String term;
    private String value;

    public InvalidSearchTermValueException(String term, String value) {
        super(String.format("Invalid value for the search term '%s' having value %s", term, value));
        this.term = term;
        this.value = value;
    }

    /**
     * Get the search term
     * @return search term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Get the invalid search term value
     * @return invalid search term value
     */
    public String getValue() {
        return value;
    }

}