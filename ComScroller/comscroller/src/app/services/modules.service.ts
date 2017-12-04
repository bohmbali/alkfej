import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {ActivatedRoute, Router} from "@angular/router";
import {Routes, Server} from "../utils/ServerRoutes";
import "rxjs/add/operator/map";

import { Module } from '../models/Module';
import { Scenes } from '../models/Scene';

@Injectable()
export class ModulesService {
    
    modullist: Module[];
    Module: Module;

  constructor(private http: Http,private route: ActivatedRoute) {
    
  }

  listModules(): Observable<Module[]> {
    return this.http.get(Server.routeTo(Routes.LISTG))
      .map(res => res.json())
  } 
  
  
  view(id: number): Observable<Module>{
      return this.http.get(Server.routeTo(Routes.VIEW) + '/' + id)
      .map(res => res.json())
  }
  
  play(id: number): Observable<Scenes>{
      return this.http.get(Server.routeTo(Routes.VIEW) + '/' + id)
      .map(res => res.json())
  }
  
  
  next(id: number, scene: number): Observable<Scenes>{
      return this.http.get(Server.routeTo(Routes.VIEW) + '/' + id + '/' + scene)
      .map(res => res.json())
  }

  getModules(): Module[]{
    
       this.listModules().subscribe(modules => {
        this.modullist = modules as Module[]
        
    });
    return this.modullist;
    
  }
  
    getModuleByName(id: number): Module{
        this.view(id).subscribe(modules => {
        this.Module = modules as Module
        
    });
    return this.Module;
    }
    

}
