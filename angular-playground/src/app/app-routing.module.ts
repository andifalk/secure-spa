import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InsecureComponent } from './insecure/insecure.component';
import { SecureComponent } from './secure/secure.component';

const routes: Routes = [
  { path: 'secure', component: SecureComponent },
  { path: 'insecure', component: InsecureComponent },
  { path: '',   redirectTo: '/secure', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
