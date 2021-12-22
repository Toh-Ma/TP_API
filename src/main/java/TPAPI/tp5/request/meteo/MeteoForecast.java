package TPAPI.tp5.request.meteo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoForecast {

	private MeteoData item[];
	
	public MeteoForecast() {
		
	}

	public MeteoData[] getItem() {
		return item;
	}

	public void setItem(MeteoData[] item) {
		this.item = item;
	}
	
}
