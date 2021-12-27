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
        model.addAttribute("city", city);


        //token d'identification de MeteoConcept api
        final String token = "d18f921d5f46154d423ff92b679e5b3bc7710647956ea5538e36d49aab4e0572";
        //url de l'api météo permettant de récupérer les prévisions météo selon les coordonnées géographiques
        final String meteoUri = "https://api.meteo-concept.com/api/forecast/daily?token=" + token +
                "&latlng=" + coor[1] + "," + coor[0];



        // Résultat de la requête
        MeteoQueryResult meteoresult = restTemplate.getForObject(meteoUri, MeteoQueryResult.class);

        LocalDate date = LocalDate.now();       //récupérer la date d'aujourd'hui
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");    //Permet de formatter la date sous la forme jour mois année


        //on récupère les prévisions météo de chaque jour pour les envoyer au template
        for (int i = 0; i < meteoresult.getForecast().length; i++) {
            int weather, tmin, tmax, probarain;

            weather = meteoresult.getForecast()[i].getWeather();     //code météo du jour (cf documentation API météo)
            tmin = meteoresult.getForecast()[i].getTmin();                //température minimale
            tmax = meteoresult.getForecast()[i].getTmax();                //température maximale
            probarain = meteoresult.getForecast()[i].getProbarain();      //probabilité de pluie en %

            //envoyer chaque attribut au template (le numéro _i permet de les différencier)
            model.addAttribute("probarain", probarain);
            model.addAttribute("tmin", tmin);
            model.addAttribute("tmax", tmax);
            model.addAttribute("weather", weather);
            model.addAttribute("probarain_" + i, probarain);
            model.addAttribute("date_" + i, date.plusDays(i).format(formatter));    //.format(formatter) permet d'envoyer la date au formattage défini précédement
            model.addAttribute("icon_" + i, weatherIconConverter(weather));

        }

        return "meteo";
    }


    /**
     * fonction permettant de lier le code météo fournit par l'api meteo avec l'icone correspondant
     *
     * @param weatherCode
     * @return icon file name
     */
    String weatherIconConverter(int weatherCode) {
        //ensoleillé
        if (weatherCode <= 3) {
            return "sunny.svg";
        }
        //nuageux
        else if (weatherCode >= 4 && weatherCode <= 7) {
            return "cloudy.svg";
        }
        //pluie
        else if ((weatherCode >= 10 && weatherCode <= 16) || (weatherCode >= 40 && weatherCode <= 48) || (weatherCode >= 210 && weatherCode <= 212) || weatherCode == 235) {
            return "rainy.svg";
        }
        //neige
        else if ((weatherCode >= 20 && weatherCode <= 32) || (weatherCode >= 60 && weatherCode <= 78) || (weatherCode >= 220 && weatherCode <= 232)) {
            return "snowy.svg";
        }
        //orage
        else if (weatherCode >= 100 && weatherCode <= 142) {
            return "thunder.svg";
        } else return "";
    }
}