import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ParkingService } from '../parking.service';
import { ParkingInfo } from '../parkinginfo';

@Component({
  selector: 'app-parking-detail',
  templateUrl: './parking-detail.component.html',
  styleUrls: ['./parking-detail.component.scss']
})
export class ParkingDetailComponent implements OnInit {

  parking: ParkingInfo;
  isLoaded: boolean = false;

  constructor(private route: ActivatedRoute, private parkingService: ParkingService) { }

  ngOnInit(): void {
    const parkingId = parseInt(this.route.snapshot.paramMap.get('id')!) ;
    this.parkingService.getParking(parkingId).subscribe(
      reponse => {
        this.parking = reponse;
        this.isLoaded = true;
      }
    );    
  }

}
