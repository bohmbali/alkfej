import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { IndexComponent } from './pages/index/index.component';
import { ModulesComponent } from './pages/modules/modules.component';
import { EditorComponent } from './pages/editor/editor.component';
import { ModuleComponent } from './pages/module/module.component';
import { AccountComponent } from './pages/account/account.component';
import { AdminComponent } from './pages/admin/admin.component';

const routes: Routes = [
  { path: '',  component: IndexComponent },
  { path: 'modules',     component: ModulesComponent },
  { path: 'module/:name',     component: ModuleComponent },
  // { path: 'editor',     component: EditorComponent },
  // { path: 'account/:id',     component: AccountComponent },
  // { path: 'admin',     component: AdminComponent },
  { path: '**',     redirectTo: '', pathMatch: 'full' }

];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
