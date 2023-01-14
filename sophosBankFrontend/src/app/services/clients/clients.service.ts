import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from 'src/app/models/client.model';

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  constructor(private httpClient: HttpClient) {}

  addClient(body: Client): Observable<Client> {
    return this.httpClient.post<Client>("http://localhost:8090/clients", body);
  }

  listClients(): Observable<Client[]> {
    return this.httpClient.get<Client[]>("http://localhost:8090/clients");
  }

  getClient(userId: string): Observable<Client> {
    return this.httpClient.get<Client>(`http://localhost:8090/clients/${userId}`)
  }

  updateClient(userId: string, client: Client): Observable<Client> {
    return this.httpClient.patch<Client>(`http://localhost:8090/clients/${userId}`, client);
  }
}
