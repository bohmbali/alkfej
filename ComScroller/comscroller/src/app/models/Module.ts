
export class Module {
  id: number;
  name: String;
  owner: number;
  ispublic: boolean;
  finished: boolean;
  types: String;  
  startscene: number;  
  endscene1: number;
  
  description: String;
  approved: boolean;
  endscene2: number;
  endscene3: number;
  mainchar: number;
  approvedby: number;
  published: String;  
  views: number;
  rate: number;
  image: String;

  

  constructor(startscene: number, endscene1: number, name?: String, owner?: number, ispublic?: boolean, finished?: boolean, types?: String, 
  description?: String,
  approved?: boolean,
  endscene2?: number,
  endscene3?: number,
  mainchar?: number,
  approvedby?: number,
  published?: String,  
  views?: number,
  rate?: number,
  image?: String
  ) {
    this.name = name || "";
    this.owner = owner || 1;
    this.ispublic = ispublic || false;
    this.finished = finished || false;
    this.types = types || "";  
    this.startscene = startscene;  
    this.endscene1 = endscene1;
  
    this.description = description || "";
    this.approved = approved || false;
    this.endscene2 = endscene2 || 0;
    this.endscene3 = endscene3 || 0;
    this.mainchar =  mainchar || 1;
    this.approvedby = approvedby || 0;
    this.published = published || "2017-12-24";  
    this.views = views || 0;
    this.rate = rate || 0;
    this.image = "../../../../media" + image || "../../../../media/images/smitd_logo.png";
  }
}
