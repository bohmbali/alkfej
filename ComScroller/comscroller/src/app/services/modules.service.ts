import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";

import { Module } from '../models/Module';

@Injectable()
export class ModulesService {

  constructor() { }

  modules = [
    new Module(
      'test1', 'catchy','adventure', 'Description of this test adventure.',
      '/images/smitd_logo.png',
      false, false, false, 0,  new Date('2017-11-11'),
      0, 0, 1,
  ),
    new Module("empty")
  ];

  getModules(): Module[]{
    return this.modules;
  }
  getModuleByName(name: string): Module{
    for(const mod of this.modules){
      if(mod.name === name){
        return mod;
      }
    }
    return null;
  }

}
