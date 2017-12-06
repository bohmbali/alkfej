export class Scene{
  scene: Scenes;
  id: number;
  background: string;
  scale = 'cover';
  animin: String;
  animout: String;
  objects: SceneObject[] = [];
  toPreload: number[] = [];
  constructor(scene: Scenes) {
    if(scene.background !== 'none'){
      this.background = '../../../../media/' + scene.background;
    }else{
      this.background = '#000';
    }
    this.animin = scene.animin;
    this.animout = scene.animout;
    var a = scene.object1.split("&");
       
    this.objects.push(new SceneObject(
         a[0],
          a[1],
           a[2], a[3], a[4], a[5],
          a[6],
          a[7],
          a[8]
        ));
    var check = a[2].split(':');    
    if(check[0] === 'scene'){
        const id = +check[1];
        this.toPreload.push(id);
      }
    if (scene.object2 != null){    
    a = scene.object2.split("&");
       
    this.objects.push(new SceneObject(
         a[0],
          a[1],
           a[2], a[3], a[4], a[5],
          a[6],
          a[7],
          a[8]
        ));
    check = a[2].split(':');    
    if(check[0] === 'scene'){
        const id = +check[1];
        this.toPreload.push(id);
      }    
    }
    if (scene.object3 != null){    
    a = scene.object3.split("&");
       
    this.objects.push(new SceneObject(
         a[0],
          a[1],
           a[2], a[3], a[4], a[5],
          a[6],
          a[7],
          a[8]
        ));
    check = a[2].split(':');    
    if(check[0] === 'scene'){
        const id = +check[1];
        this.toPreload.push(id);
      }
    }
    
    if (scene.object3 != null){ 
    a = scene.object4.split("&");
       
    this.objects.push(new SceneObject(
         a[0],
          a[1],
           a[2], a[3], a[4], a[5],
          a[6],
          a[7],
          a[8]
        ));  
    check = a[2].split(':');    
    if(check[0] === 'scene'){
        const id = +check[1];
        this.toPreload.push(id);
      }
    }
    if (scene.object3 != null){     
    a = scene.object5.split("&");
       
    this.objects.push(new SceneObject(
         a[0],
          a[1],
           a[2], a[3], a[4], a[5],
          a[6],
          a[7],
          a[8]
        ));
    check = a[2].split(':');    
    if(check[0] === 'scene'){
        const id = +check[1];
        this.toPreload.push(id);
      }           
    }
    if (scene.object3 != null){     
    a = scene.object6.split("&");
       
    this.objects.push(new SceneObject(
         a[0],
          a[1],
           a[2], a[3], a[4], a[5],
          a[6],
          a[7],
          a[8]
        )); 
    check = a[2].split(':');    
    if(check[0] === 'scene'){
        const id = +check[1];
        this.toPreload.push(id);
      }    
    }
           
  }
}

    
export class Scenes {
  id: number;
  gameid: number;
  background: String;
  animin: String;
  animout: String;
  object1: String;
  object2: String;
  object3: String;
  object4: String;
  object5: String;
  object6: String;
   

  constructor(gameid: number,
  object1: String,  
  background?: String,
  animin?: String,
  animout?: String,  
  object3?: String,
  object4?: String,
  object2?: String,  
  object5?: String,
  object6?: String){
    this.gameid = gameid,
    this.object4 = object4 || null,
    this.object1 = object1,
    this.object2 = object2 || null,
    this.background = background || "none",
    this.animin = animin || "none",
    this.animout = animout || "none",  
    this.object3 = object3 || null,  
    this.object5 = object5 || null,
    this.object6 = object6 || null
  }

}

export class SceneObject {

  type: string; // display type: text or image
  action: string; // action
  x: number; // normalized x coordinates
  y: number; // normalized y coordinates
  w: number; // normalized width
  h: number; // normalized height
  cont: string; // content for text or image url
  style: string;
  event: number; // event number
  imageToPreload: string = '';

  constructor(_type: string, _action: string, _x: string, _y: string, _w: string,
     _h: string, _cont: string, _style, _event: string
  ) {
    this.type = _type;
    this.action = _action;
    this.x = Number(_x);
    this.y = Number(_y);
    this.w = Number(_w);
    this.h = Number(_h);
    this.cont = _cont;
    this.style = _style;
    this.event = Number(_event);
  }

  getStyle(w: number, h: number): any{
    const x = this.stageX(this.x, w) + 'px';
    const y = this.stageY(this.y, h) + 'px';
    const width = this.stageX(this.w, w) + 'px';
    const height = this.stageY(this.h, h) + 'px';

    let bg : string = '#FFF';
    let bgs = 'cover';

    let color = '#000';
    let size = this.stageX(30, w) + 'px';
    let family = 'CGR';
    let textShadow = '';

    let border = '';
    let radius = '';
    let boxShadow = '';
    let margin = '';
    let padding = '';

    const attributes = this.style.split(';');
    for(const atr of attributes){
      const datas = atr.split(":");
      if(datas[0].trim() === 'color'){
        color = datas[1];
      }
      if(datas[0].trim() === 'font-size'){
        size = datas[1];
      }
      if(datas[0].trim() === 'font-family'){
        family = datas[1];
      }
      if(datas[0].trim() === 'text-shadow'){
        textShadow = datas[1];
      }
      if(datas[0].trim() === 'border'){
        border = datas[1];
      }
      if(datas[0].trim() === 'border-radius'){
        radius = datas[1];
      }
      if(datas[0].trim() === 'box-shadow'){
        boxShadow = datas[1];
      }
      if(datas[0].trim() === 'margin'){
        margin = datas[1];
      }
      if(datas[0].trim() === 'padding'){
        padding = datas[1];
      }

      if(this.type === 'img'){
        bg = 'url(../../../../media/' + this.cont + ') no-repeat center';
        bgs = 'contain';
      }
      if(datas[0] === 'background'){
        bg = datas[1];
      }
      if(datas[0] === 'scale'){
        bgs = datas[1];
      }

    }

    if(bg !== ''){
      let prep = bg.split("(");
      if(prep.length > 1){
        prep = prep[1].split(")");
        this.imageToPreload = prep[0];
      }
    }

    return {
      'position'        : 'absolute',
      'left'            : x,
      'top'             : y,
      'width'           : width,
      'height'          : height,

      'color'           : color,
      'font-size'       : size,
      'font-family'     : family,
      'text-shadow'     : textShadow,

      'border'          : border,
      'border-radius'   : radius,
      'box-shadow'      : boxShadow,
      'margin'          : margin,
      'padding'          : padding,
      'background'      : bg,
      'background-size' : bgs,
      'display'         : 'flex',
      'justify-content' : 'center',
      'align-items'     : 'center'
    }
  }

  stageX(c: number, w: number): number{
    const document_width = 1920;//normalized width
    const stage_width = w;
    return c * stage_width / document_width;
  }
  stageY(c: number, h: number): number{
    const document_height = 1080;//normalized height
    const stage_height = h;
    return c * stage_height / document_height;
  }

}
