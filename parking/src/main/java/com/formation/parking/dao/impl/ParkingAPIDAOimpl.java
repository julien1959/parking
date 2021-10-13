package com.formation.parking.dao.impl;

import com.formation.parking.dao.ParkingAPIDAO;
import com.formation.parking.dao.entity.ReponseParkingAPIEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository // pour dire que la classe est un component pour le bean restTemplate dans ParkingApplication
public class ParkingAPIDAOimpl implements ParkingAPIDAO {

    private static final String URL_API_OPEN_DATA = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_parkings-publics-nantes-disponibilites&q=&facet=grp_nom&facet=grp_statut";

    @Autowired
    private RestTemplate restTemplate; //Pour appeler API OPEN DATA

    @Override
    public ReponseParkingAPIEntity getListParkings() {
        return restTemplate.getForEntity(URL_API_OPEN_DATA, ReponseParkingAPIEntity.class).getBody();
    }
}
