package com.taf.sample.framework.test_dev.context;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test context for storing data within test steps.
 */
@Component
public class TestContext {

    private Map<String, Object> testContext = new HashMap<>();
    private Map<String, List<Object>> testListContext = new HashMap<>();

    public void setTestContext(Context key, Object value) {
        testContext.put(key.toString(), value);
    }

    public void setTestListContext(Context key, List<Object> value) {
        List<Object> existedValue = testListContext.get(key.toString());
        if (existedValue == null) {
            testListContext.put(key.toString(), value);
        } else if (!existedValue.contains(value)) {
            existedValue.addAll(value);
            testListContext.put(key.toString(), existedValue);
        }
    }

    public void setTestListContext(Context key, List<Object> value, boolean rewrite) {
        List<Object> existedValue = testListContext.get(key.toString());
        if (existedValue == null) {
            testListContext.put(key.toString(), value);
        } else if (!existedValue.contains(value) && !rewrite) {
            existedValue.addAll(value);
            testListContext.put(key.toString(), existedValue);
        } else if (rewrite) {
            testListContext.put(key.toString(), value);
        }
    }

    public Object getContext(Context key) {
        return testContext.get(key.toString());
    }

    public List<Object> getListContext(Context key) {
        return testListContext.get(key.toString());
    }

    public Boolean isContains(Context key) {
        return testContext.containsKey(key.toString());
    }

    public void clean() {
        testContext.clear();
        testListContext.clear();
    }

    public void cleanTestListContext(Context key) {
        if (testContext.containsKey(key.toString())) {
            testListContext.get(key).clear();
        }
    }
}
