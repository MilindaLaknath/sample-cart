import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  getItems(){
    return this.http.get(environment.backend+environment.path_item)
  }

  getItem(itemId:number){
    return this.http.get(environment.backend+environment.path_item+itemId)
  }

  getItemTotal(itemId:number, quantity:number){    
    return this.http.get(environment.backend+environment.path_item+"calc/"+itemId+"?qty="+quantity)
  }

}
