/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uy.edu.fing.tsi2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import javax.inject.Named;

import uy.edu.fing.tsi2.model.Member;
//import uy.edu.fing.tsi2.service.MemberRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberController {

	
	//Si pongo esto me da un problema
    //@Inject
    //private FacesContext facesContext;

    //@Inject
    //private MemberRegistration memberRegistration;

    @Produces
    @Named
    private Member newMember;

    
	private List<SelectItem> paises;
    
    
    @PostConstruct
    public void initNewMember() {
        newMember = new Member();
       
        SelectItemGroup g1 = new SelectItemGroup("America");
        g1.setSelectItems(new SelectItem[] {new SelectItem(1, "Argentina"), new SelectItem(2, "Brasil"), new SelectItem(3, "Uruguay")});
         
        SelectItemGroup g2 = new SelectItemGroup("Europa");
        g2.setSelectItems(new SelectItem[] {new SelectItem(4, "Alemania"), new SelectItem(5, "España"), new SelectItem(6, "Inglaterra")});
         
        paises = new ArrayList<SelectItem>();
        paises.add(g1);
        paises.add(g2);
        
        
    }

    public void register() throws Exception {
        try {

            
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            //facesContext.addMessage(null, m);
        }
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
    
    
	public List<SelectItem> getPaises() {
		return paises;
	}

	public void setPaises(List<SelectItem> paises) {
		this.paises = paises;
	}
    

}