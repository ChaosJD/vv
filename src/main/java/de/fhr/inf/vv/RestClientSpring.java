package de.fhr.inf.vv;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public class RestClientSpring {
    public static final String REST_SERVICE_URI = "http://localhost:8080";

    private static void listAllKunden(){
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>>
                kundenMap = restTemplate.getForObject(REST_SERVICE_URI+"/kunden/", List.class);

        if(kundenMap!=null){
            for(LinkedHashMap<String, Object> map : kundenMap){
                System.out.println("Kunde : id="+map.get("id")
                        + ", Vorname="+map.get("vorname")
                        + ", Nachname="+map.get("nachname")
                        + ", Geburtstag="+map.get("geburtstag"));
            }
        }else{
            System.out.println("Keine Kunden");
        }
    }


    private static void getKunde(){
        RestTemplate restTemplate = new RestTemplate();
        Kunde kunde = restTemplate.getForObject(REST_SERVICE_URI+"/kunden/1", Kunde.class);
        System.out.println(kunde);
    }

    private static void createKunde() {
        RestTemplate restTemplate = new RestTemplate();
        Kunde klaus = new Kunde("Klaus","Kaiser", "1980-12-10");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/kunden/"
                , klaus, Kunde.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateKunde() {
        RestTemplate restTemplate = new RestTemplate();
        Kunde tommy  = new Kunde("Tommy", "Pinnball", "1960-01-01");
        restTemplate.put(REST_SERVICE_URI+"/kunden/1", tommy);
        System.out.println(tommy);
    }

    private static void deleteKunde() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/kunden/3");
    }


    public static void main(String args[]){
        listAllKunden();
        getKunde();
        createKunde();
        listAllKunden();
        updateKunde();
        listAllKunden();
        deleteKunde();
        listAllKunden();
    }
}

