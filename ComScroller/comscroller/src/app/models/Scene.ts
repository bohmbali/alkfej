export class Scene {
  id: number;
  background: string;
  scale = 'cover';
  animin: string;
  animout: string;
  objects: SceneObject[];
  toPreload: number[] = [];

  constructor(_id: number, _background: string, _animin: string, _animout: string, _objects: SceneObject[]) {
    this.id = _id;
    if(_background !== 'none'){
      this.background = '../../../../media/' + _background;
    }else{
      this.background = '#000';
    }
    this.animin = _animin;
    this.animout = _animout;
    this.objects = _objects;
    for( const o of this.objects ){
      const check = o.action.split(':');
      if(check[0] === 'scene'){
        const id = +check[1];
        this.toPreload.push(id);
      }
    }
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

  constructor(_type: string, _action: string, _x: number, _y: number, _w: number,
     _h: number, _cont: string, _style, _event: number
  ) {
    this.type = _type;
    this.action = _action;
    this.x = _x;
    this.y = _y;
    this.w = _w;
    this.h = _h;
    this.cont = _cont;
    this.style = _style;
    this.event = _event;
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
