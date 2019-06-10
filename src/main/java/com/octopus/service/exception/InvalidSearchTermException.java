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
 * the search term (i.e. selector) is invalid.
 *
 * @author Dheeraj Gopinath
 */
public class InvalidSearchTermException extends SearchException {

    private String term;

    public InvalidSearchTermException(String term) {
        super(String.format("Invalid search term: %s", term));
        this.term = term;
    }

    /**
     * Get the invalid search term
     * @return invalid term
     */
    public String getTerm() {
        return term;
    }

}
