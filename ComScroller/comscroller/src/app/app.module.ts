import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';import{RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { IndexComponent } from './pages/index/index.component';
import { ModulesComponent } from './pages/modules/modules.component';
import { EditorComponent } from './pages/editor/editor.component';
import { ModuleComponent } from './pages/module/module.component';
import { AccountComponent } from './pages/account/account.component';
import { AdminComponent } from './pages/admin/admin.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { DropdownmenuComponent } from './components/header/dropdownmenu/dropdownmenu.component';
import { NavigationComponent } from './components/header/navigation/navigation.component';
import { AppRoutingModule } from './app-routing.module';
import { ModuleListComponent } from './components/modulelist/modulelist.component';

import { ModulesService } from './services/modules.service';
import { ScenesService } from './services/scenes.service';
import { StageComponent } from './pages/module/stage/stage.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ModulesComponent,
    EditorComponent,
    ModuleComponent,
    AccountComponent,
    AdminComponent,
    HeaderComponent,
    FooterComponent,
    DropdownmenuComponent,
    NavigationComponent,
    ModuleListComponent,
    StageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [
    ModulesService,
    ScenesService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }