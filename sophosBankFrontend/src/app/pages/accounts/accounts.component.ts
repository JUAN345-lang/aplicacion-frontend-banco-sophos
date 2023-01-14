import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from 'src/app/models/account.model';
import { AccountsService } from 'src/app/services/accounts/accounts.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss']
})
export class AccountsComponent implements OnInit {
  @Input() userId: string = '';
  accounts: Account[] = [];

  constructor(private router: Router, private accountsService: AccountsService) { }

  ngOnInit(): void {

    this.accountsService.listAccounts(this.userId).subscribe((accounts) => {
      console.log(accounts);
      this.accounts = accounts;
    })
  }

  public createProduct() {
    this.router.navigate(['create-product'], {
      queryParams : {
        userId: this.userId
      }
    })
  }

}
