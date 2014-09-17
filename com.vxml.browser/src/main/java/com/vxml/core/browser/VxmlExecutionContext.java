package com.vxml.core.browser;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.script.ScriptException;

import com.sun.istack.internal.logging.Logger;
import com.vxml.core.VxmlException;
import com.vxml.tag.Tag;

public class VxmlExecutionContext {

    private Stack<Tag> stack;
    
    private ScriptExecutionContext scriptExecutionContext;
    private static boolean isSkipTagExecute;
    private static boolean isTtsAllowed = true;
    private static String docBaseUrl = "http://localhost:8080/javscript/";
    private EventHandler eventHandler;
    
    //mainly for forms
    private Map<String, Tag> tagMap; 

    public VxmlExecutionContext() throws ScriptException {
        stack = new Stack<Tag>();
        scriptExecutionContext = new ScriptExecutionContext();
        eventHandler = new EventHandler();
        tagMap = new HashMap<String, Tag>();
    }

    public boolean isSkipTagExecute() {
        return isSkipTagExecute;
    }

    public void setSkipTagExecute(boolean isSkipTagExecute) {
        this.isSkipTagExecute = isSkipTagExecute;
    }

    public Tag push(Tag tag) {
        return stack.push(tag);
    }

    public Tag pop() {
        return stack.pop();
    }

    public Object executeScript(String script) {
        try {
            return scriptExecutionContext.executeScript(script);
        } catch (ScriptException e) {
            System.err.println("SCRIPT:" + script);
           throw new VxmlException("Script failure:" + script, e);
        }
    }
    
    public Object executeScriptNullIfUndefined(String script) {
        try {
            return scriptExecutionContext.executeScriptNullIfUndefined(script);
        } catch (ScriptException e) {
            
        }
        return null;
    }
    
    public Object executeScript(InputStream script) {
        try {
            return scriptExecutionContext.executeScript(script);
        } catch (ScriptException e) {
            throw new VxmlException("Script failure:" + script , e);
        }
    }
    
    public void assignScriptVar(String var, Object val) {
        scriptExecutionContext.put(var, val);
    }
    
    public Object getScriptVar(String var) {
        return scriptExecutionContext.get(var);
    }

    public static String getDocBaseUrl() {
        return docBaseUrl;
    }

    public static void setDocBaseUrl(String docBaseUrl) {
        VxmlExecutionContext.docBaseUrl = docBaseUrl;
    }

    public URI getFullUri(String uri) {
        URI u = null;
        try {
            u = new URI(uri);
            if (u.getScheme() == null) {
                u = new URI(docBaseUrl + uri);
            }
        } catch (URISyntaxException e) {
            throw new VxmlException(e);
        }
        return u;

    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void storeTag(String id, Tag tag) {
        tagMap.put(id, tag);
    }
    
    public Tag getTag(String id) {
        return tagMap.get(id);
    }

    public static boolean isTtsAllowed() {
        return isTtsAllowed;
    }

    public static void setTtsAllowed(boolean tts) {
        VxmlExecutionContext.isTtsAllowed = tts;
    }


}
