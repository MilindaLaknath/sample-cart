import { Component, OnInit } from '@angular/core';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  items: Object;

  constructor(private _itemService: ItemService) { }

  ngOnInit() {
    this._itemService.getItems().subscribe(
      data => {
        this.items = data;
        console.log(this.items);
      }
    );
  }

  

}
