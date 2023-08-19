import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-utenti-gruppi',
  templateUrl: './utenti-gruppi.component.html',
  styleUrls: ['./utenti-gruppi.component.css']
})
export class UtentiGruppiComponent implements OnInit {
  @Output() myEvent = new EventEmitter();

  constructor() { }

  ngOnInit() {
    this.myEvent.emit(null);
  }

}
