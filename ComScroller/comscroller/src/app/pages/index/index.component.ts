import { Component, OnInit, HostListener } from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit{

  constructor() {
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
  }
  @HostListener('window:resize', ['$event'])onResize(event){
    this.resize();
  }
}
