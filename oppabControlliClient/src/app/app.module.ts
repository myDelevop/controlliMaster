import { DeleteAziendaStazForestaleComponent } from './components/dialog/delete/delete-azienda-staz-forestale/delete-azienda-staz-forestale.component';
import { AddAziendaStazForestaleComponent } from './components/dialog/add/add-azienda-staz-forestale/add-azienda-staz-forestale.component';
import { GesioneRegoleChecklistComponent } from './components/tabs/gesione-regole-checklist/gesione-regole-checklist.component';
import { UpdateControlloreStazForestaleComponent } from './components/dialog/update/update-controllore-staz-forestale/update-controllore-staz-forestale.component';
import { UpdateChecklistTemplateComponent } from './components/dialog/update/update-checklist-template/update-checklist-template.component';
import { DeleteChecklistTemplateComponent } from './components/dialog/delete/delete-checklist-template/delete-checklist-template.component';
import { AddChecklistTemplateComponent } from './components/dialog/add/add-checklist-template/add-checklist-template.component';
import { ChecklistTemplateComponent } from './components/checklist-template/checklist-template.component';
import { AddAnagraficaComponent } from './components/dialog/add/add-anagrafica/add-anagrafica.component';
import { GestioneAnagraficheComponent } from './components/tabs/gestione-anagrafiche/gestione-anagrafiche.component';
import { AnagraficheComponent } from './components/anagrafiche/anagrafiche.component';
import { AddRegolaChecklistComponent } from './components/dialog/add/add-regola-checklist/add-regola-checklist.component';
import { RegoleChecklistComponent } from './components/regole-checklist/regole-checklist.component';
import { AssegnamentoControlliComponent } from './components/tabs/assegnamento-controlli/assegnamento-controlli.component';
import { StazForestaliControlloriComponent } from './components/tabs/staz-forestali-controllori/staz-forestali-controllori.component';
import { UtentiGruppiComponent } from './components/tabs/utenti-gruppi/utenti-gruppi.component';
import { SideNavComponent } from './components/side-nav/side-nav.component';
import { FilterPipe } from './components/azienda-stazione-forestale/filter.pipe';
// tslint:disable-next-line: max-line-length
import { AddControlloreStazForestaleComponent } from './components/dialog/add/add-controllore-staz-forestale/add-controllore-staz-forestale.component';
// tslint:disable-next-line: max-line-length
import { DeleteControlloreStazForestaleComponent } from './components/dialog/delete/delete-controllore-staz-forestale/delete-controllore-staz-forestale.component';
import { ControlloriStazforestaliComponent } from './components/controllori-stazforestali/controllori-stazforestali.component';
import { DeleteStazioneForestaleComponent } from './components/dialog/delete/delete-stazione-forestale/delete-stazione-forestale.component';
import { AddStazioneForestaleComponent } from './components/dialog/add/add-stazione-forestale/add-stazione-forestale.component';
import { DeleteControlloreComponent } from './components/dialog/delete/delete-controllore/delete-controllore.component';
import { AddControlloreComponent } from './components/dialog/add/add-controllore/add-controllore.component';
import { ControlloriComponent } from './components/controllori/controllori.component';
import { AddUserGroupComponent } from './components/dialog/add/add-user-group/add-user-group.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS  } from '@angular/common/http';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UtentiComponent } from './components/utenti/utenti.component';
import { GruppiComponent } from './components/gruppi/gruppi.component';
import { AddUserComponent } from './components/dialog/add/add-user/add-user.component';
import { UpdateUserComponent } from './components/dialog/update/update-user/update-user.component';
import { ToastrModule } from 'ngx-toastr';
import { MatCardModule, MatCheckboxModule, MatProgressSpinnerModule, MatMenuModule } from '@angular/material';
import { MatListModule, MatButtonModule, MatInputModule, MatSelectModule } from '@angular/material';
import { MatTableModule, MatSidenavModule, MatTabsModule } from '@angular/material';
import { MatDatepickerModule, MatNativeDateModule, MatDialogModule } from '@angular/material';
import { MatPaginatorModule, MatToolbarModule, MatIconModule } from '@angular/material';
import { MatSortModule } from '@angular/material';
import { DeleteUserComponent } from './components/dialog/delete/delete-user/delete-user.component';
import { DeleteGroupComponent } from './components/dialog/delete/delete-group/delete-group.component';
import { AddGroupComponent } from './components/dialog/add/add-group/add-group.component';
import { UpdateGroupComponent } from './components/dialog/update/update-group/update-group.component';
import { UtenteGruppoComponent } from './components/utente-gruppo/utente-gruppo.component';
import { DeleteUserGroupComponent } from './components/dialog/delete/delete-user-group/delete-user-group.component';
import { UpdateControlloreComponent } from './components/dialog/update/update-controllore/update-controllore.component';
import { UpdateStazioneForestaleComponent } from './components/dialog/update/update-stazione-forestale/update-stazione-forestale.component';
import { StazioneForestaleComponent } from './components/stazione-forestale/stazione-forestale.component';
import { AziendaStazioneForestaleComponent } from './components/azienda-stazione-forestale/azienda-stazione-forestale.component';
import { BasicAuthInterceptor } from './services/auth/basic-auth-interceptor.service';
import { ErrorInterceptor } from './services/auth/error-interceptor.service';
import { LoginComponent } from './components/login/login.component';
import { GestioneTemplateComponent } from './components/tabs/gestione-template/gestione-template.component';
import { DeleteRegolaChecklistComponent } from './components/dialog/delete/delete-regola-checklist/delete-regola-checklist.component';
import { UpdateRegolaChecklistComponent } from './components/dialog/update/update-regola-checklist/update-regola-checklist.component';
import { DeleteAnagraficaComponent } from './components/dialog/delete/delete-anagrafica/delete-anagrafica.component';
import { UpdateAnagraficaComponent } from './components/dialog/update/update-anagrafica/update-anagrafica.component';
import { UpdateAziendaStazForestaleComponent } from './components/dialog/update/update-azienda-staz-forestale/update-azienda-staz-forestale.component';
@NgModule({
  declarations: [
    SideNavComponent,
    RegoleChecklistComponent,
    AnagraficheComponent,
    ChecklistTemplateComponent,
    AssegnamentoControlliComponent,
    UtentiGruppiComponent,
    GestioneTemplateComponent,
    AppComponent,
    UtentiGruppiComponent,
    StazForestaliControlloriComponent,
    UtentiComponent,
    GruppiComponent,
    UtenteGruppoComponent,
    AddUserComponent,
    DeleteUserComponent,
    UpdateUserComponent,
    AddGroupComponent,
    DeleteGroupComponent,
    UpdateGroupComponent,
    AddUserGroupComponent,
    DeleteUserGroupComponent,
    GestioneAnagraficheComponent,
    GesioneRegoleChecklistComponent,
    ControlloriComponent,
    AddControlloreComponent,
    DeleteControlloreComponent,
    UpdateControlloreComponent,
    StazioneForestaleComponent,
    AddStazioneForestaleComponent,
    DeleteStazioneForestaleComponent,
    UpdateStazioneForestaleComponent,
    ControlloriStazforestaliComponent,
    AddAziendaStazForestaleComponent,
    DeleteAziendaStazForestaleComponent,
    UpdateAziendaStazForestaleComponent,
    AddControlloreStazForestaleComponent,
    DeleteControlloreStazForestaleComponent,
    UpdateControlloreStazForestaleComponent,
    AziendaStazioneForestaleComponent,
    AddRegolaChecklistComponent,
    DeleteRegolaChecklistComponent,
    UpdateRegolaChecklistComponent,
    AddAnagraficaComponent,
    DeleteAnagraficaComponent,
    UpdateAnagraficaComponent,
    AddChecklistTemplateComponent,
    DeleteChecklistTemplateComponent,
    UpdateChecklistTemplateComponent,
    FilterPipe,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatCheckboxModule,
    DragDropModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    MatButtonModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatTableModule,
    MatSidenavModule,
    MatTabsModule,
    MatTabsModule,
    MatDialogModule,
    MatMenuModule,
    MatCardModule,
    MatIconModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  entryComponents: [
    AddUserComponent,
    DeleteUserComponent,
    UpdateUserComponent,
    AddGroupComponent,
    DeleteGroupComponent,
    UpdateGroupComponent,
    UtenteGruppoComponent,
    AddUserGroupComponent,
    DeleteUserGroupComponent,
    AddControlloreComponent,
    DeleteControlloreComponent,
    UpdateControlloreComponent,
    AddStazioneForestaleComponent,
    DeleteStazioneForestaleComponent,
    UpdateStazioneForestaleComponent,
    AddControlloreStazForestaleComponent,
    DeleteControlloreStazForestaleComponent,
    UpdateControlloreStazForestaleComponent,
    AddRegolaChecklistComponent,
    DeleteRegolaChecklistComponent,
    UpdateRegolaChecklistComponent,
    AddAnagraficaComponent,
    DeleteAnagraficaComponent,
    UpdateAnagraficaComponent,
    AddChecklistTemplateComponent,
    DeleteChecklistTemplateComponent,
    UpdateChecklistTemplateComponent,
    AddAziendaStazForestaleComponent,
    DeleteAziendaStazForestaleComponent,
    UpdateAziendaStazForestaleComponent,

  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
