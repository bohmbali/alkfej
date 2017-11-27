import { Component, OnInit, OnDestroy, Input, HostListener } from '@angular/core';

import { Scene, SceneObject } from '../../../models/Scene';
import { ScenesService } from '../../../services/scenes.service';

import { ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-stage',
  templateUrl: './stage.component.html',
  styleUrls: ['./stage.component.scss']
})
export class StageComponent implements OnInit, OnDestroy {

  @Input() beginwith: number;

  @ViewChild('stage') stage: ElementRef;

  // audio = new Audio();

  previous: Scene = new Scene(
    0,
    'none',
    'none',
    'none',
    []
  );
  previousAct: number = 1;
  current: Scene;
  currentAct: number = 1;
  stageWidth: number;
  stageHeight: number;
  toPreload: string[] = [];

  constructor(
    private sceneService: ScenesService
  ){}

  ngOnInit() {
    this.current = this.sceneService.getSceneById(this.beginwith);
    this.getDimensions()
    // this.audio.src = "../../../../media/music/02-oakvale.mp3";
    // this.audio.load();
    // this.audio.play();
    this.preload();
  }
  ngOnDestroy(){
  //   this.audio.pause();
  }

  handleScene(input: string){
    const action = input.split(':');
    if( action[0] === 'scene' ){
      const id : number = +action[1];
      if(this.current.animout === this.previous.animout){
        this.current.animout = this.current.animout + "C";
      }
      this.previous = this.current;
      this.previousAct = this.currentAct;
      this.currentAct = 1;
      this.current = this.sceneService.getSceneById(id);
      if(this.current.animin === this.previous.animin){
        this.current.animin = this.current.animin + "C";
      }
      this.preload();
    }
    if(action[0] === 'event'){
      this.currentAct = +action[1];
    }
  }
  handleEvent(input: number): string{
    return "event" + input;
  }
  getCurrentAct():string{
    this.init_events = false;
    return "act" + this.currentAct;
  }
  getPreviousAct():string{
    return "pas" + this.previousAct;
  }

  getDimensions(){
    this.stageWidth = this.stage.nativeElement.offsetWidth;
    this.stageHeight = this.stage.nativeElement.offsetHeight;
  }
  @HostListener('window:resize', ['$event'])onResize(event){
    this.getDimensions();
  }

  preload(){
    for(const pre of this.current.toPreload){
      const preloading = this.sceneService.getSceneById(pre);

      if( preloading.background !== 'none') this.toPreload.push(preloading.background);

      for( const obj of preloading.objects ){
        if(obj.cont.split("/")[0] === 'test' || obj.cont.split("/")[0] === 'images'){
          this.toPreload.push("../../../../media/" + obj.cont);
        }
      }
    }
  }
}
