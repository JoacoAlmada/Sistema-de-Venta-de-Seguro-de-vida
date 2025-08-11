import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeUserDatos } from './home-user-datos';

describe('HomeUserDatos', () => {
  let component: HomeUserDatos;
  let fixture: ComponentFixture<HomeUserDatos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeUserDatos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeUserDatos);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
