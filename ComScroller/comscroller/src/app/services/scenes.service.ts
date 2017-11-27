import { Injectable } from '@angular/core';

import { Scene, SceneObject } from '../models/Scene';

@Injectable()
export class ScenesService {

  constructor() { }

  scenes = [
    new Scene(
      0,
      'none',
      'none',
      'none',
      []
    ),
    new Scene(
      1,
      'test/bg1.gif',
      'fade',
      'none',
      [
        new SceneObject(
          'img',
           'none',
           900, 200, 800, 1200,
          'test/lol.gif',
          'scale: contain',
          [1,2]
        ),
        new SceneObject(
          'img',
           'scene:2',
           900, 200, 800, 1200,
          'test/lol.gif',
          'scale: contain',
          2
        ),
        new SceneObject(
          'img',
           'event:2',
           10, 10, 200, 300,
          'test/lol.gif',
          'scale: contain',
          1
        ),
        new SceneObject(
          'img',
           'event:1',
           10, 10, 200, 300,
          'test/dragon.gif',
          'scale: contain',
          2
        ),
        new SceneObject(
          'txt',
           'event:2',
           1350, 350, 400, 100,
          'We need to hurry! The dragon is not far.',
          'padding:1%; border-radius: 100%',
          2
        ),
        new SceneObject(
          'img',
           'event: 1',
           1700, 20, 200, 200,
          'images/smitd_logo.png',
          'border-radius: 50%',
           [1, 2]
        )
      ]
    ),
    new Scene(
      2,
      'test/bg2.gif',
      'swipe-up',
      'swipe-out-up',
      [
        new SceneObject('img', 'event:2', 600, 400, 600, 400, 'test/dragon.gif', 'scale: 100% 100%', [1,2]),
        new SceneObject('img', 'none', 0, 0, 100, 100, 'test/lol.gif', 'scale: 100% 100%', [1,2]),
        new SceneObject('img', 'event:1', 600, 400, 600, 400, 'test/dragon.gif', 'scale: 100% 100%', [2]),
        new SceneObject(
          'txt',
           'scene:1',
           1000, 450, 300, 50,
          'Sup mate?',
          'background:url(../../../../media/images/textbox.png) no-repeat center;'+
          'scale: contain; color: #000; font-family: CGB; padding: 2% 1% 2% 1%',
          2
        )
      ]
    )
  ];

  getSceneById(id: number): Scene{
    for(const scene of this.scenes){
      if(scene.id === id){
        return scene;
      }
    }
    return null;
  }

}
