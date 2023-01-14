import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from 'src/app/models/account.model';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private httpClient: HttpClient) { }

  public addAccount(account: Account) {
    return this.httpClient.post<Account>('http://localhost:8090/accounts', account);
  }

  public listAccounts(userId: string) {
    return this.httpClient.get<Account[]>('http://localhost:8090/accounts/user/' + userId )
  }
}
