import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/client.model';
import { ClientsService } from 'src/app/services/clients/clients.service';

@Component({
  selector: 'app-list-clients',
  templateUrl: './list-clients.component.html',
  styleUrls: ['./list-clients.component.scss']
})
export class ListClientsComponent implements OnInit {
  clients: Client[] = [];
  columnNames: string[] = [ 'ID', 'ID Type', 'Identification', 'Name', 'Lastname', 'Email', 'Birthday' /* 'Phone' */, 'Creation Date', 'Manage' ];

  constructor(private clientService: ClientsService, private router: Router) { }

  ngOnInit(): void {
    this.clientService.listClients().subscribe((clients) =>  this.clients = clients);
  }

  manageClient(clientId: string): void {
    this.router.navigate(['manage'], {
      queryParams: {
        userId: clientId,
      }
    })
  }
}
