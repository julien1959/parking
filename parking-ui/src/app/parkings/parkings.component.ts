import { Component, OnInit } from '@angular/core';
import { ParkingInfo } from '../parkinginfo';
import { ParkingService } from '../parking.service';

@Component({
  selector: 'app-parkings',
  templateUrl: './parkings.component.html',
  styleUrls: ['./parkings.component.scss']
})
export class ParkingsComponent implements OnInit {

  parkings: ParkingInfo[] = [];
  isLoaded: boolean = false;

  constructor(private parkingService: ParkingService) { }

  ngOnInit(): void {
    this.parkingService.getParkings().subscribe(
      reponse => {
        this.parkings = reponse;
        this.isLoaded = true;
      }
    );
  }

  calculStyleStatut(parking: ParkingInfo) {
    if (parking.statut === 'OUVERT') {
      return {color: 'green'}
    } else if(parking.statut === 'ABONNÉS') {
      return {color: 'orange'}
    } else if(parking.statut === 'FERMÉ') {
      return {color: 'red'}
    } else {
      return {'font-style': 'italic'}
    }
  }

}
