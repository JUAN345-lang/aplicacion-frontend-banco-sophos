import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountsService } from 'src/app/services/accounts/accounts.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss']
})
export class CreateAccountComponent implements OnInit {
  productForm : FormGroup = new FormGroup(
    {
      type: new FormControl('',Validators.required),
      balance: new FormControl(0,Validators.required),
      gmfExempt: new FormControl(false ,Validators.required),
    }
  );

  constructor(
    private accountService: AccountsService,
    private activatedRoute: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit(): void {
  }

  addAccount() {
    this.accountService.addAccount(
      {
        ...this.productForm.value,
        state: "ACTIVE",
        availableBalance: 0,
        creationUser: {
          id: localStorage.getItem('admin'),
        },
        modificationUser: {
          id: localStorage.getItem('admin'),
        },
        client: {
          id: this.activatedRoute.snapshot.queryParams['userId'],
        }
      }).subscribe((account) => {
        this.router.navigate(['manage'], {
          queryParams: {
            userId: account.client?.id
          }
        })
      })
  }
}
