package TPAPI.tp5.request.meteo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoQueryResult {

	private MeteoData forecast[];
	
	public MeteoQueryResult() {
	}

	public MeteoData[] getForecast() {
		return forecast;
	}

	public void setForecast(MeteoData forecast[]) {
		this.forecast = forecast;
	}
	
}
