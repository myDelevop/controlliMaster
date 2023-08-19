import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestioneTemplateComponent } from './gestione-template.component';

describe('GestioneTemplateComponent', () => {
  let component: GestioneTemplateComponent;
  let fixture: ComponentFixture<GestioneTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestioneTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestioneTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
