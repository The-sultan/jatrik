package uy.edu.fing.tsi2.navigation;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * @author Farid
 */
@Named("ajaxNav")
@SessionScoped
public class AjaxNavigator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String TEMPLATES_PATH = "/WEB-INF/templates/";

	private String content = TEMPLATES_PATH + "home.xhtml";

	private boolean error = false;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = TEMPLATES_PATH + content + ".xhtml";
	}

	public void navigate() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		String requestedPage = params.get("page");
		setContent(requestedPage);
	}

	public void controllerNavigate(String requestedPage) {
		setContent(requestedPage);
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

}
