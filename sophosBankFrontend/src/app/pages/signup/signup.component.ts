import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup = new FormGroup(
    {
      name: new FormControl('', Validators.minLength(1)),
      phone: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    }
  );

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  public signUp(): void {
    this.loginService.addAdmin(this.signupForm.value).subscribe((admin) => {
      localStorage.setItem('admin', admin.id);
      this.router.navigate(['main']);
    })
  }


}
