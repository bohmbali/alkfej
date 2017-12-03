import { Component, OnInit, OnDestroy, Input, HostListener } from '@angular/core';

import { Scene, SceneObject } from '../../../models/Scene';

import { ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {
  @Input() featured: Scene[];

  @ViewChild('stage') stage: ElementRef;

  current: Scene;
  left: Scene;
  right: Scene;

  swiped: Scene;

  inanim = "none";
  swipedanim = "none";

  stageWidth: number;
  stageHeight: number;
  toPreload: string[] = [];

  constructor(
  ){}

  ngOnInit() {
    this.current = this.featured[0];
    this.right = this.featured[1];
    this.left = this.featured[2];
    this.swiped = this.featured[2];
    this.getDimensions()
  }
  ngOnDestroy(){
  }

  swipeLeft(){
    this.swiped = this.current;

    this.current = this.right;
    this.right = this.left;
    this.left = this.swiped;

    if(this.inanim === "swipe-left") {this.inanim = "swipe-leftC";}
    else {this.inanim = "swipe-left";}
    if(this.swipedanim === "swipe-out-left"){ this.swipedanim = "swipe-out-leftC";}
    else{ this.swipedanim = "swipe-out-left";}
  }

  swipeRight(){
    this.swiped = this.current;

    this.current = this.left;
    this.left = this.right;
    this.right = this.swiped;

    if(this.inanim === "swipe-right") {this.inanim = "swipe-rightC";}
    else {this.inanim = "swipe-right";}
    if(this.swipedanim === "swipe-out-right"){ this.swipedanim = "swipe-out-rightC";}
    else{ this.swipedanim = "swipe-out-right";}

  }

  getDimensions(){
    this.stageWidth = this.stage.nativeElement.offsetWidth;
    this.stageHeight = this.stage.nativeElement.offsetHeight;
  }
  @HostListener('window:resize', ['$event'])onResize(event){
    this.getDimensions();
  }
}
