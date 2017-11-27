import { Component, OnInit, HostListener } from '@angular/core';

import {ActivatedRoute, Router} from "@angular/router";

// import { SceneService } from '../../services/scene.service';
import { Module } from '../../models/Module';
import { ModulesService } from '../../services/modules.service';


@Component({
  selector: 'app-module',
  templateUrl: './module.component.html',
  styleUrls: ['./module.component.scss']
})
export class ModuleComponent implements OnInit {

  moduleName: string;
  module: Module;

  constructor(
    private moduleService: ModulesService,
    private route: ActivatedRoute
  ){
    this.route.params.subscribe(
      params => {
       this.moduleName = params['name'];
     },
      err => console.log(err)
    );
  }

  launch(){
      this.module = this.moduleService.getModuleByName(this.moduleName);

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
    this.launch();
  }
  @HostListener('window:resize', ['$event'])onResize(event){
    this.resize();
  }

}
