import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from 'src/app/models/admin.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) {}

  login(body: { email: string; password: string }): Observable<Admin> {
    return this.httpClient.post<Admin>("http://localhost:8090/administrators/login", body);
  }

  getAdmin(id: string): Observable<Admin> {
    return this.httpClient.get<Admin>("http://localhost:8090/administrators/" + id);
  }

  addAdmin(body: Admin): Observable<Admin> {
    return this.httpClient.post<Admin>("http://localhost:8090/administrators", body);
  }
}
