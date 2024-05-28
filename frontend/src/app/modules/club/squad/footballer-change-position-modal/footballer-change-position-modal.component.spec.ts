import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FootballerChangePositionModalComponent } from './footballer-change-position-modal.component';

describe('FootballerChangePositionModalComponent', () => {
  let component: FootballerChangePositionModalComponent;
  let fixture: ComponentFixture<FootballerChangePositionModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FootballerChangePositionModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FootballerChangePositionModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
