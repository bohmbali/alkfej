
export class Module {
  name: string;
  owner: string;
  type: string;
  description: string;
  image: string;

  public: boolean;
  finished: boolean;
  approved: boolean;
  approvedby: number;
  published: Date;

  views: number;
  rate: number;

  startscene: number;

  constructor(_name: string = 'error', _owner: string = 'error',
    _type: string = 'error', _description: string = 'error',
    _image: string = '/images/smitd_logo.png', _public: boolean = false,
    _finished: boolean = false, _approved: boolean = false,
    _approvedby: number = 0, _published: Date = new Date('2017-11-11'),
    _views: number = 0, _rate: number = 0, _startscene: number = 0
  ) {
    this.name = _name;
    this.owner = _owner;
    this.type = _type;
    this.description = _description;
    this.image = '../../../../media/'+_image;

    this.public = _public;
    this.finished = _finished;
    this.approved = _approved;
    this.approvedby = _approvedby;
    this.published = _published;

    this.views = _views;
    this.rate = _rate;

    this.startscene = _startscene;
  }
}
