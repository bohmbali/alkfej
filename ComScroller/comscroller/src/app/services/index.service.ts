import { Injectable } from '@angular/core';

import { Scene,Scenes, SceneObject } from '../models/Scene';

@Injectable()
export class IndexService {

  constructor() { }

  featured = [
    new Scene( new Scenes(0,"")),
    new Scene( new Scenes(0,"")),
    new Scene( new Scenes(0,""))

  ];

  news = [
    {
      title: "title",
      date: new Date("2017-12-03"),
      text : "description",
      img : "../../media/images/smitd_logo.png"

    }
  ];

  getFeaturedById(id: number): Scene{
    for(const scene of this.featured){
      if(scene.id === id){
        return scene;
      }
    }
    return null;
  }

  getFeatured(): Scene[]{
    return this.featured;
  }

}
