import { Injectable } from '@angular/core';

import { Module } from '../models/Module';
import { Scene,Scenes, SceneObject } from '../models/Scene';
import {Http} from "@angular/http";
import { ModulesService } from '../services/modules.service';

@Injectable()
export class EditorService {

  constructor(private http: Http) { }
    
    scenes: Scenes[] = [];
    modules: Module;    
     
     create(){
         
     }
  
   
  //
  updateModule(id : number, create : Module){}
  updateScene(id : number, create : Scene){}
  //
  deleteModule(id : number){}
  deleteScene(id : number){}

}
