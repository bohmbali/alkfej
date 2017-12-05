import { Injectable } from '@angular/core';

import { ModulesService } from '../services/modules.service';
import { Scene,Scenes, SceneObject } from '../models/Scene';

@Injectable()
export class ScenesService {

   scenes: Scenes; 
    
  constructor(private moduleService: ModulesService) { }

 

  getSceneById(id: number, game: number): Scene{
    this.moduleService.next(game,id).subscribe(scenes => {
        this.scenes = scenes as Scenes
        
    });
    return new Scene(this.scenes);
  }

}
