import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Admin } from 'src/app/models/admin.model';
import { Client } from 'src/app/models/client.model';
import { ClientsService } from 'src/app/services/clients/clients.service';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.scss']
})
export class CreateClientComponent implements OnInit {
  clientFound: Client = {};
  title: string = 'Create Client';
  showAccount: boolean = false;
  userId: string = '';
  admin: Admin = {
    address: '',
    email: '',
    id: '',
    name: '',
    phone: ''
  };
  clientForm : FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    // phone: new FormControl('', Validators.required),
    birthday: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    identification: new FormControl('', Validators.required),
    typeIdentification: new FormControl('', Validators.required)
  })

  constructor(
    private clientService: ClientsService,
    private loginService: LoginService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const params = this.activatedRoute.snapshot.queryParams;
    if(Object.keys(params).length > 0) {
      this.userId = params['userId'];
      this.showAccount = true;
      this.title = 'Edit client'
      this.clientService.getClient(params['userId']).subscribe((client) => {
        this.clientFound = client;
        this.clientForm.patchValue(client)
      });

    }
    else {

      this.loginService.getAdmin(localStorage.getItem('admin') ?? '').subscribe((admin) => this.admin = admin )

    }
  }

  public addClient() {
    const params = this.activatedRoute.snapshot.queryParams;

    if(Object.keys(params).length > 0) {

      this.clientService.updateClient(this.clientFound.id ?? '', {...this.clientFound, ...this.clientForm.value })
      .subscribe(() => this.router.navigate(['list-clients']));

    }
    else {

    this.clientService.addClient(
      {...this.clientForm.value, creationUser: this.admin, modificationUser: this.admin })
      .subscribe(() =>
        this.router.navigate(['list-clients'])
      );
    }

  }

}
