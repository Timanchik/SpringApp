package com.microrent.backend.models.searchingAndFiltering;

import java.io.Serializable;
import java.util.List;

public class FilterRequest implements Serializable {

    private String key;

    private Operator operator;

    private FieldType fieldType;

    private transient Object value;

    private transient Object valueTo;

    private transient List<Object> values;

    public FilterRequest(String key, Operator operator, FieldType fieldType, Object value, Object valueTo, List<Object> values) {
        this.key = key;
        this.operator = operator;
        this.fieldType = fieldType;
        this.value = value;
        this.valueTo = valueTo;
        this.values = values;
    }

    public FilterRequest() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValueTo() {
        return valueTo;
    }

    public void setValueTo(Object valueTo) {
        this.valueTo = valueTo;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
