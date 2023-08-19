import { GesioneRegoleChecklistComponent } from './components/tabs/gesione-regole-checklist/gesione-regole-checklist.component';
import { GestioneAnagraficheComponent } from './components/tabs/gestione-anagrafiche/gestione-anagrafiche.component';
import { UtentiGruppiComponent } from './components/tabs/utenti-gruppi/utenti-gruppi.component';
import { AssegnamentoControlliComponent } from './components/tabs/assegnamento-controlli/assegnamento-controlli.component';
import { GestioneTemplateComponent } from './components/tabs/gestione-template/gestione-template.component';
import { StazForestaliControlloriComponent } from './components/tabs/staz-forestali-controllori/staz-forestali-controllori.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegoleChecklistComponent } from './components/regole-checklist/regole-checklist.component';
import { AuthorizationGuard } from './services/auth/authorization.guard';

const routes: Routes = [
  { path: 'app-staz-forestali-controllori', component: StazForestaliControlloriComponent, canActivate: [AuthorizationGuard] },
  { path: 'app-utenti-gruppi', component: UtentiGruppiComponent, canActivate: [AuthorizationGuard] },
  { path: 'app-regole-checklist', component: GesioneRegoleChecklistComponent, canActivate: [AuthorizationGuard] },
  { path: 'app-gestione-template', component: GestioneTemplateComponent, canActivate: [AuthorizationGuard] },
  { path: 'assegnamento-controllori', component: AssegnamentoControlliComponent, canActivate: [AuthorizationGuard] },
  { path: 'app-anagrafiche', component: GestioneAnagraficheComponent, canActivate: [AuthorizationGuard] },
  { path: 'login', component: LoginComponent },
  // otherwise redirect to home
  { path: '**', redirectTo: '/', canActivate: [AuthorizationGuard] },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }