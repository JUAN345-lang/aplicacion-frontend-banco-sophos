import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Admin } from 'src/app/models/admin.model';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup(
    {
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    }
  );

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    this.loginService.login(this.loginForm.value).subscribe(({ id }) => {
      localStorage.setItem('admin', id)
      this.router.navigate(['main']);
    })
  }

}
