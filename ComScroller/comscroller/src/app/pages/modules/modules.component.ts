import { Component, OnInit, HostListener } from '@angular/core';

import { ModulesService } from '../../services/modules.service';
import { Module } from '../../models/Module';

@Component({
  selector: 'app-modules',
  templateUrl: './modules.component.html',
  styleUrls: ['./modules.component.scss']
})
export class ModulesComponent implements OnInit {

  constructor( private moduleService: ModulesService){}

  modules: Module[];
  loadModuls(){
    this.modules = this.moduleService.getModules();
  }

// min-height
  document_height = document.documentElement.clientHeight;
  container_min_height = 0;

  resize(){
    this.document_height = document.documentElement.clientHeight;
    this.container_min_height = this.document_height - 120;
  }
  ngOnInit() {
    this.resize();
    this.loadModuls();
  }
  @HostListener('window:resize', ['$event'])onResize(event){
    this.resize();
  }
}
