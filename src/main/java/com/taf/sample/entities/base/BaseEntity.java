package com.taf.sample.entities.base;

import com.taf.sample.framework.utils.ConvertUtil;

import java.io.IOException;

public class BaseEntity {

    /**
     * Converts this object to JSON String.
     *
     * @return JSON String.
     */
    public String toJson() throws IOException {
        return ConvertUtil.toJson(this);
    }
}
