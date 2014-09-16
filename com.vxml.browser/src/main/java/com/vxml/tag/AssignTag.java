package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlBrowser;

public class AssignTag extends AbstractTag {

	public AssignTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    String name = getAttribute("name");
	    
	    //subdialog variables are prefixed with the subdialog name. (Name scope)
	    if (getSubdialogName() != null) {
            name = getSubdialogName() + "." + name;
        }
	    String expr = getAttribute("expr");
	    if (expr == null) {
	        VxmlBrowser.getContext().executeScript("var " + name + ";");
	    } else {
	        Object exprResult = expr;
	        if (!(expr.endsWith("'") && expr.startsWith("'"))) {
	            exprResult = VxmlBrowser.getContext().executeScript(expr);
	        }
	        if (exprResult instanceof String) {
	            String e = (String)exprResult;
	            if (!(e.startsWith("'") && e.startsWith("'"))) {
	                e = "'" + e + "'";
	            }
	            VxmlBrowser.getContext().executeScript(name + "=" + e + "");
	        } else {
	            VxmlBrowser.getContext().assignScriptVar(name, exprResult);
	        }
	    }
		
	}


}
