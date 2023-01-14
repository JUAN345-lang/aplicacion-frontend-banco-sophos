import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginGuard } from './guards/login.guard';
import { CreateAccountComponent } from './pages/create-account/create-account.component';
import { CreateClientComponent } from './pages/create-client/create-client.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ListClientsComponent } from './pages/list-clients/list-clients.component';
import { LoginComponent } from './pages/login/login.component';
import { MainComponent } from './pages/main/main.component';
import { SignupComponent } from './pages/signup/signup.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'manage', component: CreateClientComponent, canActivate: [LoginGuard] },
  { path: 'create-product', component: CreateAccountComponent, canActivate: [LoginGuard] },
  { path: 'list-clients', component: ListClientsComponent, canActivate: [LoginGuard] },
  { path: 'main', component: MainComponent, canActivate: [LoginGuard] },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
