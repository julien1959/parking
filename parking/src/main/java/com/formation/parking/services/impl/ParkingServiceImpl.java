package com.formation.parking.services.impl;

import com.formation.parking.dao.ParkingAPIDAO;
import com.formation.parking.dao.entity.RecordEntity;
import com.formation.parking.dao.entity.ReponseParkingAPIEntity;
import com.formation.parking.models.Parking;
import com.formation.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingAPIDAO parkingAPIDAO;

    @Override
    public List<Parking> getListParkings() {
        ReponseParkingAPIEntity reponse = parkingAPIDAO.getListParkings();
        return transformEntityModel(reponse);
    }

    //Méthode pour transformer ReponseParkingAPIEntity en List<Parking> pour la méthode getListParkings
    private List<Parking> transformEntityModel(ReponseParkingAPIEntity reponse) {
        List<Parking> resultat = new ArrayList<Parking>();
        for (RecordEntity record : reponse.getRecords()) {
            Parking parking = new Parking();
            parking.setIdentifiant(Integer.parseInt(record.getFields().getIdObj()));
            parking.setNom(record.getFields().getGrpNom());
            parking.setStatut(getLibelleStatut(record));
            parking.setNbPlacesDispo(record.getFields().getGrpDisponible());
            parking.setNbPlacesTotal(record.getFields().getGrpExploitation());
            parking.setHeureMaj(getHeureMaj(record));
            resultat.add(parking);
        }
        return resultat;
    }

    private String getHeureMaj(RecordEntity record) {
        OffsetDateTime dateMaj = OffsetDateTime.parse(record.getFields().getGrpHorodatage());
        OffsetDateTime dateMajWithOffsetPlus2 = dateMaj.withOffsetSameInstant(ZoneOffset.of("+02:00"));
        return dateMajWithOffsetPlus2.getHour() + "h" + dateMajWithOffsetPlus2.getMinute();
    }

    private String getLibelleStatut(RecordEntity record) {
        switch (record.getFields().getGrpStatut()){
            case "1" -> {
                return "FERMÉ";
            }
            case "2" -> {
                return "ABONNÉS";
            }
            case "5" -> {
                return "OUVERT";
            }
        }
        return "Données non disponibles";
    }
}
