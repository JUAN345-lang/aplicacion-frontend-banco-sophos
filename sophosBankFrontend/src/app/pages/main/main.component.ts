import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/models/admin.model';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  public adminUser: Admin = { id: 'admin', email: '', address: '', name: '', phone: '' };

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
    const id = localStorage.getItem('admin') ?? '';
    this.loginService.getAdmin(id).subscribe((admin) => this.adminUser = admin);
  }

  public logout() {
    localStorage.removeItem('admin');
    this.router.navigate(['dashboard']);
  }
}
