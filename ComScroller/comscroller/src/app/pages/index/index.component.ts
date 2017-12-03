import { Component, OnInit, HostListener } from '@angular/core';

import { Scene, SceneObject } from '../../models/Scene';
import { IndexService } from '../../services/index.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit{

  featured : Scene[];

  constructor( private indexservice: IndexService ) {}

// min-height
  document_height = document.documentElement.clientHeight;
  container_min_height = 0;

  resize(){
    this.document_height = document.documentElement.clientHeight;
    this.container_min_height = this.document_height - 120;
  }
  ngOnInit() {
    this.featured = this.indexservice.getFeatured();
    this.resize();
  }
  @HostListener('window:resize', ['$event'])onResize(event){
    this.resize();
  }
}
