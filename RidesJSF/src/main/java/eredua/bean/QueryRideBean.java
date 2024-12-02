package eredua.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import businessLogic.*;
import domain.*;

public class QueryRideBean {
	private BLFacade facadeBL;
	private String dCity;
	private String aCity;
	private Date data;
	private List<String> departCities;
	private List<String> arrivalCities;
	private List<Ride> bidaiaEskuragarriak;

	public QueryRideBean() {
		facadeBL = FacadeBean.getBusinessLogic();
		departCities = facadeBL.getDepartCities();
	}

	public String getdCity() {
		return dCity;
	}

	public void setdCity(String dCity) {
		this.dCity = dCity;
	}

	public String getaCity() {
		return aCity;
	}

	public void setaCity(String aCity) {
		this.aCity = aCity;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<String> getDepartCities() {
		return departCities;
	}

	public List<String> getArrivalCities() {
		return arrivalCities;
	}

	public List<Ride> getBidaiaEskuragarriak() {
		return bidaiaEskuragarriak;
	}

	public void updateArrivalCities(AjaxBehaviorEvent event) {
		if (dCity != null && !dCity.isEmpty()) {
			arrivalCities = facadeBL.getDestinationCities(dCity);
		} else {
			arrivalCities = null;
		}
	}

	public String bilatuBidaiak() {
		try {
			bidaiaEskuragarriak = facadeBL.getRides(dCity, aCity, data);
			if (bidaiaEskuragarriak == null || bidaiaEskuragarriak.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Ez dago bidaiarik", "Saiatu berriz."));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errorea bilatzean", e.getMessage()));
		}
		return null;
	}
}
