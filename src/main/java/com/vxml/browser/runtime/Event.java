package com.vxml.browser.runtime;

public class Event {

	private String outputType;
	private String output;
	
	public Event (String outputType, String output) {
	    this.outputType = outputType;
	    this.setOutput(output);
	}

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    
    @Override
    public String toString() {
        return outputType + ":" + output;
    }
}
