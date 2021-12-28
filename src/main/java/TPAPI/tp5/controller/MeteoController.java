package TPAPI.tp5.controller;

import TPAPI.tp5.request.address.AddressQueryResult;
import TPAPI.tp5.request.meteo.MeteoQueryResult;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class MeteoController {

    @PostMapping(path = "/meteo")
    public String meteo(@RequestBody String saisie, Model model) {
        //public String postBody(@RequestParam(value = "address") String addressMeteo, Model model) {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build(); //Obtenir lattitude et longitude à partir d'une adresse
        final String adresseUri = "https://api-adresse.data.gouv.fr/search/?q=" + saisie + "&limit=1";
        AddressQueryResult addr = restTemplate.getForObject(adresseUri, AddressQueryResult.class);
        String city = addr.getFeatures()[0].getProperties().getCity();          //récupérer la ville de l'adresse (on prend uniquement la première ville renvoyé par l'API. Il n'y a pas de vérification de l'adresse avant)
        double[] coor = addr.getFeatures()[0].getGeometry().getCoordinates();  //récupérer les coordonnées géographiques de l'adresse
        
        final String token = "d18f921d5f46154d423ff92b679e5b3bc7710647956ea5538e36d49aab4e0572";  //token d'identification de MeteoConcept api
        final String meteoUri = "https://api.meteo-concept.com/api/forecast/daily?token=" + token +
                "&latlng=" + coor[1] + "," + coor[0]; //url de l'api météo permettant de récupérer les prévisions météo avec les coordonnées géographiques
        MeteoQueryResult meteoresult = restTemplate.getForObject(meteoUri, MeteoQueryResult.class); //on récupère les prévisions météo du jour
        int weather, tmin, tmax, probarain;
        weather = meteoresult.getForecast()[0].getWeather();     //code météo
        tmin = meteoresult.getForecast()[0].getTmin();                //température minimale
        tmax = meteoresult.getForecast()[0].getTmax();                //température maximale
        probarain = meteoresult.getForecast()[0].getProbarain();      //probabilité de pluie (%)
        model.addAttribute("city", city);
        model.addAttribute("probarain", probarain);
        model.addAttribute("tmin", tmin);
        model.addAttribute("tmax", tmax);
        model.addAttribute("weather", weather);
        model.addAttribute("image", weatherImage(weather));
        return "meteo";
    }

    /**
     * lie une image à un groupe de codes météo
     * @param weather
     * @return image file name
     */
    String weatherImage(int weather) {
        //soleil
        if (weather <= 3) {
            return "sunny.svg";
        }
        //nuages
        else if (weather >= 4 && weather <= 7) {
            return "cloudy.svg";
        }
        //pluie
        else if ((weather >= 10 && weather <= 16) || (weather >= 40 && weather <= 48) || (weather >= 210 && weather <= 212) || weather == 235) {
            return "rainy.svg";
        }
        //neige
        else if ((weather >= 20 && weather <= 32) || (weather >= 60 && weather <= 78) || (weather >= 220 && weather <= 232)) {
            return "snowy.svg";
        }
        //orages
        else if (weather >= 100 && weather <= 142) {
            return "thunder.svg";
        } else return "";
    }
}