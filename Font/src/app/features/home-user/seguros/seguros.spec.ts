import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Seguros } from './seguros';

describe('Seguros', () => {
  let component: Seguros;
  let fixture: ComponentFixture<Seguros>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Seguros]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Seguros);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
