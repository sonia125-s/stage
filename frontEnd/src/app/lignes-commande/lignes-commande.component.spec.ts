import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LignesCommandeComponent } from './lignes-commande.component';

describe('LignesCommandeComponent', () => {
  let component: LignesCommandeComponent;
  let fixture: ComponentFixture<LignesCommandeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LignesCommandeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LignesCommandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
