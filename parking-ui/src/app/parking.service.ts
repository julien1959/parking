import { Injectable } from '@angular/core';
import { ParkingInfo } from './parkinginfo';
import { Observable } from 'rxjs'; // Asynchrone
import { HttpClient } from '@angular/common/http'; // Appel vers API Rest
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ParkingService {

  constructor(private httpClient: HttpClient) { }

  getParkings(): Observable<ParkingInfo[]> {
    return this.httpClient.get<ParkingInfo[]>(environment.apiUrl + '/parkings');
  }

  getParking(id: number): Observable<ParkingInfo> {
    return this.httpClient.get<ParkingInfo[]>(environment.apiUrl + '/parkings').pipe(
      map(parkings => parkings.find(parking => parking.identifiant === id)!)
    );      
  }
}
